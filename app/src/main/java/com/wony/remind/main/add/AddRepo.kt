package com.wony.remind.main.add

import android.content.Context
import com.wony.remind.db.DB
import com.wony.remind.db.RemindData

class AddRepo(private val context: Context) {
    var dao = DB.getInstance(context).remindDao()

    fun insertItem(item: RemindData.Item){
        dao.insert(item)
    }
}