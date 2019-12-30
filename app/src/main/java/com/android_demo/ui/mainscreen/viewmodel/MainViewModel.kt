package com.android_demo.ui.mainscreen.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.android_demo.model.DataModel
import com.android_demo.network.CommonNetworkRequest
import com.android_demo.interfaces.DataResponse

/**
 * MainViewModel
 * @desc - View model for MainActivity
 * */
class MainViewModel : ViewModel(), DataResponse {

    private var rowList: MutableLiveData<ArrayList<DataModel.Result>>? = null
    private var comonRequest = CommonNetworkRequest(this)


    /*To get the Row data*/
    val fetchData: MutableLiveData<ArrayList<DataModel.Result>>
        get() {
            if (rowList == null) {
                rowList = MutableLiveData()
                comonRequest.loadNetworkData()  /**loadNetworkData - Using Retrofit to get the JSON data from URL*/

            }
            return rowList as MutableLiveData<ArrayList<DataModel.Result>>
        }

    /*Handling Response*/
    private fun setListData(body: DataModel?) {
        if (body != null) {

            rowList?.value = body.results as ArrayList<DataModel.Result>?
        }
    }

    override fun onResponse(body: DataModel?) {
        setListData(body)
    }

    override fun onError() {
        setListData(null)
    }


}
