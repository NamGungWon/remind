package com.wony.remind.base

import androidx.lifecycle.ViewModel
import com.wony.remind.utils.SingleLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

open class BaseVM: ViewModel() {


    private val job = Job()

    var ioScope = CoroutineScope(Dispatchers.IO + job)
    val toastFlag  = SingleLiveData<String>()

    override fun onCleared() {
        job.cancel()
        super.onCleared()
    }
}