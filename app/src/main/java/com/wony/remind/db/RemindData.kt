package com.wony.remind.db

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.wony.remind.base.BaseDAO

object RemindData {


    @Entity(tableName = "remind")
    data class Item(
        @PrimaryKey(autoGenerate = true) var id: Long,
        var title: String,                  // 리마인드 타이틀
        var alarmTime: Long,                // 알람 시간
        var updateTime: Long,               // 최종 수정 시간
        var bell: String,                   // 벨소리
        var isActive: Boolean               // 활성화 여부
    )


    @Dao
    interface DAO : BaseDAO<Item> {

    }
}