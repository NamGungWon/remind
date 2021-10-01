package com.wony.remind.main.list

import android.content.Context
import com.wony.remind.db.DB

class ListRepo(private val context: Context) {
    var dao = DB.getInstance(context).remindDao()

    fun selectRemindItems() = dao.select()
}