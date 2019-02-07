package com.verizonconnect.graduate.android.mynotes.di

import android.arch.persistence.room.Room
import com.verizonconnect.graduate.android.mynotes.data.database.DataStore
import com.verizonconnect.graduate.android.mynotes.data.database.MyDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module

val databaseModule = module {
    single {
        Room.databaseBuilder(androidContext(), MyDatabase::class.java, MyDatabase.DATABASE_NAME)
            .build()
    }
    
    single<DataStore> {
        get<MyDatabase>()
    }
    
    factory { get<MyDatabase>().getNoteDao() }
}