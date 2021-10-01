package com.wony.remind.main.list

import android.util.Log
import androidx.lifecycle.LiveData
import com.wony.remind.base.BaseVM
import com.wony.remind.db.RemindData
import com.wony.remind.utils.AlarmUtils
import kotlinx.coroutines.launch

class ListVM(private val repo: ListRepo): BaseVM() {

    var remindItems = repo.selectRemindItems()

    fun updateActive(id: Long, alarmTime: Long, isActive: Boolean){

        ioScope.launch {
            repo.updateRemindActive(id, !isActive)
        }
        
        if(!isActive){
            AlarmUtils.register(id, alarmTime)
        }else{
            AlarmUtils.remove(id)
        }
    }

}