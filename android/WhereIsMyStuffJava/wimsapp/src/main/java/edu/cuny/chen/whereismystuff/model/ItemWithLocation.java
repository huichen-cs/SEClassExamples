package edu.cuny.chen.whereismystuff.model;


import androidx.room.ColumnInfo;
import androidx.room.Embedded;

// POJO for JOIN queries
public class ItemWithLocation {
    @Embedded
    public Item item;

    @ColumnInfo(name = "locationDescription")
    public String locationDescription;
    @ColumnInfo(name = "locationArea")
    public String locationArea;
}