package edu.cuny.chen.whereismystuff.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Locations")
public class Location {
    @PrimaryKey(autoGenerate = true)
    private long id;

    private String description; // e.g., "Office desk"
    private String area; // e.g., "bottom right drawer"

    // Constructor
    public Location(String description, String area) {
        this.description = description;
        this.area = area;
    }

    // Getters and Setters
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getArea() { return area; }
    public void setArea(String area) { this.area = area; }
}