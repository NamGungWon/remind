package com.wony.remind.main.alarm

import android.content.Context
import com.wony.remind.db.DB
import com.wony.remind.db.RemindData

class AlarmRepo(private val context: Context) {
    var dao = DB.getInstance(context).remindDao()

    fun selectItem(id: Long): RemindData.Item? = dao.select(id)

    fun updateRemindActive(id: Long, isActive: Boolean) = dao.updateActive(id, isActive)
}