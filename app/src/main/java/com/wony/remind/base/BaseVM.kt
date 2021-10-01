package com.wony.remind.base

import androidx.lifecycle.ViewModel
import com.wony.remind.utils.SingleLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

open class BaseVM: ViewModel() {


    private val job = Job()

    var ioScope = CoroutineScope(Dispatchers.IO + job)
    var uiScope = CoroutineScope(Dispatchers.Main + job)


    val toastFlag  = SingleLiveData<String>()
    val backStackFlag  = SingleLiveData<Boolean>()

    override fun onCleared() {
        job.cancel()
        super.onCleared()
    }
}