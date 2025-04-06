package edu.cuny.chen.whereismystuff.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import java.util.List;

import edu.cuny.chen.whereismystuff.model.ItemRepository;
import edu.cuny.chen.whereismystuff.model.ItemWithLocation;

public class ItemViewModel extends AndroidViewModel {
    private static final String TAG = "ItemViewModel";
    private final MutableLiveData<String> searchQuery = new MutableLiveData<>();
    private final LiveData<List<ItemWithLocation>> itemData;

    public ItemViewModel(@NonNull Application application) {
        super(application);
        ItemRepository itemRepository = new ItemRepository(application);
        itemData = Transformations.switchMap(
                searchQuery,
                itemRepository::searchItems
        );
    }

    public void searchItems(String query) {
        searchQuery.setValue(query);
    }

    public LiveData<List<ItemWithLocation>> getItemData() {
        return itemData;
    }
}
