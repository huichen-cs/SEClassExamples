package edu.cuny.chen.whereismystuff.model;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ItemRepository {
    private final ItemDao itemDao;
    private final LocationDao locationDao;
    private final Executor executor = Executors.newSingleThreadExecutor();
    private final Handler mainHandler = new Handler(Looper.getMainLooper());

    public ItemRepository(Application application) {
        ItemDatabase db = ItemDatabase.getDatabase(application);
        this.itemDao = db.itemDao();
        this.locationDao = db.locationDao();
    }

    public LiveData<List<ItemWithLocation>> searchItems(String searchTERM) {
        return itemDao.searchItems(searchTERM);
    }

    public void addItem(String desc, String location, String subarea, int quantity, OnAddItemCallback callback) {
        executor.execute(() -> {
            try {
                long locId = locationDao.insert(new Location(location, subarea));
                long itemId = itemDao.insert(new Item(desc, locId, quantity));
                mainHandler.post(() -> callback.onSuccess(itemId));
            } catch (Exception e) {
                mainHandler.post(() -> callback.onError(e));
            }
        });
    }


    public interface OnAddItemCallback {
        void onSuccess(long itemId);

        void onError(Exception e);
    }
}
