package com.example.binarchapter7.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Entity(tableName = "Battle")
@Parcelize
data class Battle(
    @PrimaryKey(autoGenerate = true) var id: Int?,
    @ColumnInfo(name = "result") var result: String?,
    @ColumnInfo(name = "date") var date: String?
) : Parcelable