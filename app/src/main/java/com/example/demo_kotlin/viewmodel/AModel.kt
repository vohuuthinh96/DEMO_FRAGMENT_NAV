package com.example.demo_kotlin.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AModel :ViewModel(){
     var data= MutableLiveData<String>()
    var oriData = "null"
    init {
        Log.d("thinhvh", "AModel init: ")
        oriData = "new data"
        loadData()
    }

    fun loadData() {
        Log.d("thinhvh", "AModel loadData: ")
        data.postValue(oriData)
    }

}