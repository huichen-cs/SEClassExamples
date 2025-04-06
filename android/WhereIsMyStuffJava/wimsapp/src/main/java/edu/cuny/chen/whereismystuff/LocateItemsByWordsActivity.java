package edu.cuny.chen.whereismystuff;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.concurrent.Executors;

import edu.cuny.chen.whereismystuff.view.ItemsAdapter;
import edu.cuny.chen.whereismystuff.viewmodel.ItemViewModel;
import edu.cuny.chen.whereismystuff.model.ItemWithLocation;

public class LocateItemsByWordsActivity extends AppCompatActivity {
    private ItemsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_locate_items_by_words);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.locate_items_by_words_layout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Setup RecyclerView
        RecyclerView recyclerView = findViewById(R.id.results_container_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ItemsAdapter();
        recyclerView.setAdapter(adapter);

        ItemViewModel viewModel = new ViewModelProvider(this).get(ItemViewModel.class);
        viewModel.getItemData().observe(this, items -> {
            adapter.setItems(items);
        });

        Button button = findViewById(R.id.locate_items_button);
        TextView textView = findViewById((R.id.description_input_textview));
        button.setOnClickListener(
                v -> {
                    String searchTerm = textView.getText().toString().trim();
                    viewModel.searchItems(searchTerm);
                }
        );
    }
}