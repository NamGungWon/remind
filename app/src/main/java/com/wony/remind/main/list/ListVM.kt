package com.wony.remind.main.list

import androidx.lifecycle.LiveData
import com.wony.remind.base.BaseVM
import com.wony.remind.db.RemindData
import kotlinx.coroutines.launch

class ListVM(private val repo: ListRepo): BaseVM() {

    var remindItems = repo.selectRemindItems()

    fun updateActive(id: Long, isActive: Boolean){

        ioScope.launch {
            repo.updateRemindActive(id, isActive)
        }
    }

}