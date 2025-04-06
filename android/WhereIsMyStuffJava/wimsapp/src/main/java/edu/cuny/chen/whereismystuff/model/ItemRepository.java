package edu.cuny.chen.whereismystuff.model;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ItemRepository {
    private final ItemDao itemDao;

    public ItemRepository(Application application) {
        ItemDatabase db = ItemDatabase.getDatabase(application);
        this.itemDao = db.itemDao();
    }

    public LiveData<List<ItemWithLocation>> searchItems(String searchTERM) {
        return itemDao.searchItems(searchTERM);
    }
}
