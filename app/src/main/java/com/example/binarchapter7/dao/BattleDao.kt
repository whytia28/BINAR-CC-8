package com.example.binarchapter7.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.example.binarchapter7.database.Battle

@Dao
interface BattleDao {

    @Query("SELECT * FROM Battle")
    fun getAllBattle(): List<Battle>

    @Insert(onConflict = REPLACE)
    fun insert(battle: Battle): Long

    @Delete
    fun deleteHistory(battle: Battle): Int
}