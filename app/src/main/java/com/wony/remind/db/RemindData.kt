package com.wony.remind.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.wony.remind.base.BaseDAO

object RemindData {

    const val TABLE_NAME = "remind"

    @Entity(tableName = "remind")
    data class Item(
        @PrimaryKey(autoGenerate = true) var id: Long? = null,
        var title: String,                  // 리마인드 타이틀
        var alarmTime: Long,                // 알람 시간
        var updateTime: Long,               // 최종 수정 시간
        var bell: String,                   // 벨소리
        var isActive: Boolean = true        // 활성화 여부
    )


    @Dao
    interface DAO : BaseDAO<Item> {

        @Query("SELECT * FROM $TABLE_NAME")
        fun select(): LiveData<List<Item>>
    }
}