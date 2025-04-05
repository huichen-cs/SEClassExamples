package edu.cuny.chen.whereismystuff.model;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface LocationDao {
    @Insert
    long insert(Location location); // Returns generated ID

    @Query("SELECT * FROM locations WHERE id = :id")
    Location getById(long id);
}