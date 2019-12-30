package com.android_demo.interfaces

import com.android_demo.model.DataModel

interface DataResponse {
    fun onResponse(body: DataModel?)
    fun onError()
}