package com.example.room.database

import android.content.Context
import android.provider.CalendarContract.Instances
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.room.adapter.ContactDao
import com.example.room.models.Contact

@Database(entities = arrayOf(Contact::class), version = 1)
abstract class AppDataBase: RoomDatabase() {
    //patron singleton para no abrir multiples instancias de la DB

    abstract fun getDao(): ContactDao
    companion object{
        private var INSTANCE: AppDataBase ?= null

        fun getInstance(context:Context):AppDataBase{
            if(INSTANCE == null){
                //creo DB
                INSTANCE = Room
                    .databaseBuilder(context, AppDataBase::class.java, "mycontact.db")
                    .allowMainThreadQueries()
                    .build()
            }

            return INSTANCE as AppDataBase
        }
    }
}