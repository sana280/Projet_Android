package com.example.myapplication.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.myapplication.entities.Ad;

import java.util.List;

@Dao
public interface AdDao {
    @Query("SELECT * FROM ads ORDER BY id DESC ")
    List<Ad> getAllAds();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAd(Ad ad);

    @Delete
    void deleteAd(Ad ad);
}