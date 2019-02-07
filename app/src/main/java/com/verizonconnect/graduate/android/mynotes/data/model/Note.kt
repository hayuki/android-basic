package com.verizonconnect.graduate.android.mynotes.data.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*

@Entity
data class Note(
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "value") var value: String,
    @ColumnInfo(name = "timestamp") var timestamp: Date,
    @ColumnInfo(name = "favourite") var favourite: Boolean,
    @PrimaryKey var uid: String = UUID.randomUUID().toString())