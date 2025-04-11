package edu.cuny.chen.whereismystuff.viewmodel;

import android.app.Application;

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
    private final ItemRepository itemRepository;

    public ItemViewModel(@NonNull Application application) {
        super(application);
        itemRepository = new ItemRepository(application);
        itemData = Transformations.switchMap(
                searchQuery,
                itemRepository::searchItems
        );
    }

    public void addItem(String desc, String location, String subarea, int quantity, ItemRepository.OnAddItemCallback callback) {
        itemRepository.addItem(desc, location, subarea, quantity, callback);
    }

    public void searchItems(String query) {
        searchQuery.setValue(query);
    }

    public LiveData<List<ItemWithLocation>> getItemData() {
        return itemData;
    }
}
