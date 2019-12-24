package com.android_demo.ui.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.android_demo.network.ApiInterface
import com.android_demo.network.RetrofitClientInstance
import com.android_demo.model.DataModel
import com.android_demo.utils.CONSTANTS
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * MainViewModel
 * @desc - View model for MainActivity
 * */
class MainViewModel : ViewModel() {

    private var rowList: MutableLiveData<ArrayList<DataModel.Row>>? = null
    private var title = MutableLiveData<String>()


    /*To get the Row data*/
    val fetchData: MutableLiveData<ArrayList<DataModel.Row>>
        get() {
            if (rowList == null) {
                rowList = MutableLiveData()
                loadNetworkData()
            }
            return rowList as MutableLiveData<ArrayList<DataModel.Row>>
        }

    /*To set title on Actionbar*/
    val getTitle: MutableLiveData<String>
        get() {
            return title
        }

    /**loadNetworkData - Using Retrofit to get the JSON data from URL*/
    private fun loadNetworkData() {
        val api = RetrofitClientInstance().getClient().create(ApiInterface::class.java)
        val call = api.getListData(CONSTANTS.GETDATA_URL)

        call.enqueue(object : Callback<DataModel> {
            override fun onResponse(call: Call<DataModel>, response: Response<DataModel>) {
                setListData(response.body())
            }

            override fun onFailure(call: Call<DataModel>, t: Throwable) {
                setListData(null)
            }
        })
    }

/*Handling Response*/
    private fun setListData(body: DataModel?) {
        if (body != null) {
            title.value=body.title
            val alistDataTemp: ArrayList<DataModel.Row> = ArrayList()
            body.rows?.let { alistDataTemp.addAll(it) }
            for (item in body.rows as ArrayList<DataModel.Row>) {
                if (item.title == null) {
                    alistDataTemp.remove(item)
                }
            }
            rowList?.value = alistDataTemp
        }
    }


}
