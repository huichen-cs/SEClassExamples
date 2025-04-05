package edu.cuny.chen.whereismystuff.view;

import android.annotation.SuppressLint;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import edu.cuny.chen.whereismystuff.R;

import edu.cuny.chen.whereismystuff.model.ItemWithLocation;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemViewHolder> {
    private List<ItemWithLocation> items = new ArrayList<>();

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        final TextView itemDescription;
        final TextView locationText;

        final TextView locationSubareaText;

        final TextView itemQuantity;

        public ItemViewHolder(View itemView) {
            super(itemView);
            itemDescription = itemView.findViewById(R.id.item_description_textview);
            locationText = itemView.findViewById(R.id.item_location_textview);
            locationSubareaText = itemView.findViewById(R.id.item_location_subarea_textview);
            itemQuantity = itemView.findViewById(R.id.item_quantity_textview);
        }
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_row, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        ItemWithLocation current = items.get(position);
        holder.itemDescription.setText(current.item.getDescription());
        holder.locationText.setText(current.locationDescription);
        holder.locationSubareaText.setText(current.locationArea);
        holder.itemQuantity.setText(String.format("%s", current.item.getQuantity()));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setItems(List<ItemWithLocation> items) {
        this.items = items;
        notifyDataSetChanged();
    }
}
