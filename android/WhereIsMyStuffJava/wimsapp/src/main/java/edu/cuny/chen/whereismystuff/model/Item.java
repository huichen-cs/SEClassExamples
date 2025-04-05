package edu.cuny.chen.whereismystuff.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "Items",
        foreignKeys = @ForeignKey(
                entity = Location.class,
                parentColumns = "id",
                childColumns = "locationId",
                onDelete = ForeignKey.CASCADE, // Optional: Cascade delete
                onUpdate = ForeignKey.CASCADE
        ),
        indices = {@Index("locationId")} // Improves query performance
)
public class Item {
    @PrimaryKey(autoGenerate = true)
    private long id;

    private String description;
    private int quantity;

    @ColumnInfo(name = "locationId")
    private long locationId; // Foreign key

    public Item(String description, long locationId, int quantity) {
        this.description = description;
        this.locationId = locationId;
        this.quantity = quantity;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public long getLocationId() { return locationId; }
    public void setLocationId(long locationId) { this.locationId = locationId; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}