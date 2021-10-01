package com.wony.remind.main.list

import androidx.lifecycle.LiveData
import com.wony.remind.base.BaseVM
import com.wony.remind.db.RemindData

class ListVM(private val repo: ListRepo): BaseVM() {

    var remindItems = repo.selectRemindItems()

}