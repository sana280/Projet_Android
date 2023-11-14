package com.example.myapplication.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.myapplication.dao.AdDao;
import com.example.myapplication.entities.Ad;

@Database(entities = Ad.class, version = 1 , exportSchema = false)
public abstract class AdsDatabase extends RoomDatabase {

    private static AdsDatabase adsDatabase;
    public static synchronized AdsDatabase getDatabase(Context context){
        if (adsDatabase==null) {
            adsDatabase = Room.databaseBuilder(
                    context,
                    AdsDatabase.class,
                    "ads_db"
            ).build();
        }
        return adsDatabase ;
    }
     public abstract AdDao adDao();
}

