package edu.cuny.chen.whereismystuff.model;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ItemDao {
    @Insert
    long insert(Item item);

    // Query with JOIN to get item + location data
    @Query("SELECT I.*, L.description as locationDescription, L.area as locationArea " +
            "FROM Items as I INNER JOIN Locations AS L ON I.locationId = L.id " +
            "WHERE I.description LIKE '%' || :searchTerm || '%'")
    List<ItemWithLocation> searchItems(String searchTerm);
}
