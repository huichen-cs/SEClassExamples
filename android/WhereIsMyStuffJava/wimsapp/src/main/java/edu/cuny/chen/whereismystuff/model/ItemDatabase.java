package edu.cuny.chen.whereismystuff.model;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.Executors;

@Database(entities = {Item.class, Location.class}, version = 1)
public abstract class ItemDatabase extends RoomDatabase {
    private static volatile ItemDatabase INSTANCE;

    public abstract ItemDao itemDao();
    public abstract LocationDao locationDao();

    public static ItemDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ItemDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                                    context.getApplicationContext(),
                                    ItemDatabase.class,
                                    "whereismystuff_db"
                            )
                            .addCallback(roomCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public static void setDataBase(ItemDatabase db) {
        INSTANCE = db;
    }

    private static final RoomDatabase.Callback roomCallback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            Executors.newSingleThreadExecutor().execute(() -> {
                // Insert test data
                LocationDao locationDao = INSTANCE.locationDao();
                ItemDao dao = INSTANCE.itemDao();
                long locationId = locationDao.insert(new Location("Office Desk", "top drawer"));
                dao.insert(new Item("Screwdriver", locationId, 5));
                dao.insert(new Item("Hammer", locationId, 2));
            });
        }
    };
}
