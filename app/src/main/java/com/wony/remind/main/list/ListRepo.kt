package com.wony.remind.main.list

import android.content.Context
import android.util.Log
import com.wony.remind.db.DB

class ListRepo(private val context: Context) {
    var dao = DB.getInstance(context).remindDao()

    fun selectRemindItems() = dao.select()

    fun updateRemindActive(id: Long, isActive: Boolean) = dao.updateActive(id, isActive)
}