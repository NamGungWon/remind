package com.wony.remind.main.add

import android.content.Context
import com.wony.remind.db.DB
import com.wony.remind.db.RemindData

class AddRepo(private val context: Context) {
    var dao = DB.getInstance(context).remindDao()

    fun insertItem(item: RemindData.Item): Long = dao.insert(item)

    fun selectItem(id: Long): RemindData.Item? = dao.select(id)

}