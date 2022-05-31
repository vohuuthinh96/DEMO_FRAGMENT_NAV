package com.example.demo_kotlin.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData

class CModel {
    private var data= MutableLiveData<String>()
    init {
        Log.d("thinhvh", "${this.javaClass.name} init: ")
    }

    fun loadData() {
        Thread.sleep(2000)
        data.postValue(this.javaClass.name)
    }
}