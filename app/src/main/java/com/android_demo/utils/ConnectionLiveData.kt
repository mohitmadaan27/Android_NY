package com.android_demo.utils

import android.annotation.SuppressLint
import android.arch.lifecycle.LiveData
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.ConnectivityManager.CONNECTIVITY_ACTION
import android.net.Network
import android.net.NetworkCapabilities.TRANSPORT_CELLULAR
import android.net.NetworkCapabilities.TRANSPORT_WIFI
import android.net.NetworkInfo
import android.net.NetworkRequest
import android.os.Build

/**
 *ConnectionLiveData class use for tracking network connectivity
 */

class ConnectionLiveData(private val context: Context) : LiveData<Boolean>() {

    private var connectivityManager: ConnectivityManager
    private lateinit var networkCallback: NetworkCallback

    init {
        connectivityManager = context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            networkCallback = NetworkCallback(this)
        }
    }

    override fun onActive() {
        super.onActive()
        updateConnection()
        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.N -> connectivityManager.registerDefaultNetworkCallback(
                networkCallback
            )
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP -> {
                val builder =
                    NetworkRequest.Builder().addTransportType(TRANSPORT_CELLULAR).addTransportType(TRANSPORT_WIFI)
                connectivityManager.registerNetworkCallback(builder.build(), networkCallback)
            }
            else -> {
                context.registerReceiver(networkReceiver, IntentFilter(CONNECTIVITY_ACTION))
            }
        }
    }

    /*Check if network is available*/
    fun isConnected(context: Context): Boolean {
        val connMgr = context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connMgr.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

    override fun onInactive() {
        super.onInactive()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            connectivityManager.unregisterNetworkCallback(networkCallback)
        } else {
            context.unregisterReceiver(networkReceiver)
        }
    }

    /*Trigger when network connectivity change*/
    private val networkReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            updateConnection()
        }
    }

    /*Update Network status*/
    fun updateConnection() {
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        postValue(activeNetwork?.isConnected == true)
    }


    @SuppressLint("NewApi")
    class NetworkCallback(private val liveData: ConnectionLiveData) : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network?) {
            liveData.postValue(true)
        }

        override fun onLost(network: Network?) {
            liveData.postValue(false)
        }
    }
}