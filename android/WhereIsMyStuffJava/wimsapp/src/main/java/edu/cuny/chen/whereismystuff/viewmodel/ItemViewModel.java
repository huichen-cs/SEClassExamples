package edu.cuny.chen.whereismystuff.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import java.util.List;

import edu.cuny.chen.whereismystuff.model.ItemDatabase;
import edu.cuny.chen.whereismystuff.model.ItemWithLocation;

public class ItemViewModel extends AndroidViewModel {
    private static final String TAG = "ItemViewModel";
    private final ItemDatabase database;

    public ItemViewModel(@NonNull Application application) {
        super(application);
        database = ItemDatabase.getDatabase(application);
    }

    public List<ItemWithLocation> searchItems(String searchTerm) {
        List<ItemWithLocation> searchResults = database.itemDao().searchItems(searchTerm);
        Log.d(TAG, "searchTerm: " + searchTerm);
        Log.d(TAG, "searchResults: " + searchResults);
        return searchResults;
    }

//    public LiveData<List<ItemWithLocation>> searchItems(String searchTerm) {
//        return database.itemDao().searchItems(searchTerm);
//    }
//    public LiveData<List<ItemWithLocation>> getSearchResults() {
//        return searchResults;
//    }
}
