/*
 * Copyright (c) 2018. Verizon Connect Ireland Limited. All rights reserved.
 */

package com.verizonconnect.graduate.android.mynotes.data.database

import android.arch.persistence.room.TypeConverter
import java.util.Date

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}