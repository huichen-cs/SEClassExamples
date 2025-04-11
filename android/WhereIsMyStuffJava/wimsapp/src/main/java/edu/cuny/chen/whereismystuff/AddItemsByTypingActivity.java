package edu.cuny.chen.whereismystuff;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.snackbar.Snackbar;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import edu.cuny.chen.whereismystuff.model.ItemRepository;
import edu.cuny.chen.whereismystuff.viewmodel.ItemViewModel;

public class AddItemsByTypingActivity extends AppCompatActivity {
    private final static String TAG = "AddItemsByTypingActivity";
    private ItemRepository itemRepository;
    private ItemViewModel itemViewModel;
    private final Executor executor = Executors.newSingleThreadExecutor();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_items_by_typing);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.add_items_by_typing_layout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        itemViewModel = new ViewModelProvider(this).get(ItemViewModel.class);

        NumberPicker picker = findViewById(R.id.add_item_quantity_number_picker);
        picker.setMinValue(1);
        picker.setMaxValue(Integer.MAX_VALUE);
        picker.setValue(1);

        Button addItemsButton = findViewById(R.id.add_item_by_typing_button);
        addItemsButton.setOnClickListener(
                this::onAddItemsByTypingClicked
        );
    }

    private void onAddItemsByTypingClicked(View view) {
        Log.d(TAG, "Add Items Activity: adding an item");
        EditText descEditText = findViewById(R.id.add_item_desc_edittext);
        EditText locationEditText = findViewById(R.id.add_item_location_area_edittext);
        EditText subareaEditText = findViewById(R.id.add_item_location_subarea_edittext);
        NumberPicker quantityPicker = findViewById(R.id.add_item_quantity_number_picker);

        String desc = descEditText.getText().toString().trim();
        String location = locationEditText.getText().toString().trim();
        String subarea = subareaEditText.getText().toString().trim();
        int quantity = quantityPicker.getValue();

        itemViewModel.addItem(desc, location, subarea, quantity, new ItemRepository.OnAddItemCallback() {
            @Override
            public void onSuccess(long itemId) {
                Resources res = getResources();
                String text = res.getString(R.string.added_item_id, quantity);
                Toast.makeText(getApplicationContext(),
                        text, Toast.LENGTH_LONG).show();
                Log.d(TAG, "Add Items Activity: added items!");
            }

            @Override
            public void onError(Exception e) {
                Resources res = getResources();
                String text = res.getString(R.string.added_item_failed, e.getMessage());
                Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
                Log.d(TAG, "Add Items Activity: failed to adds items!");
            }
        });

        descEditText.getText().clear();
        locationEditText.getText().clear();
        subareaEditText.getText().clear();
        quantityPicker.setValue(1);
    }
}