package com.example.binarchapter7.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.binarchapter7.dao.BattleDao

@Database(entities = [Battle::class], version = 1)
abstract class BattleDatabase : RoomDatabase() {

    abstract fun battleDao(): BattleDao

    companion object {
        private var INSTANCE: BattleDatabase? = null

        fun getInstance(context: Context): BattleDatabase? {
            if (INSTANCE == null) {
                synchronized(BattleDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        BattleDatabase::class.java,
                        "history.db"
                    ).build()
                }
            }
            return INSTANCE
        }

    }
}