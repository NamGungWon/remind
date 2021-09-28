package com.wony.remind.base

import androidx.room.*


interface BaseDAO<T>{
    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    fun insert(obj : T) : Long

    @Delete
    fun delete(obj : T)

    @Update(onConflict = OnConflictStrategy.ABORT)
    fun update(obj : T) : Int


}
