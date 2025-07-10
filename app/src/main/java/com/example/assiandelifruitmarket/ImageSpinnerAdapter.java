package com.example.assiandelifruitmarket;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ImageSpinnerAdapter extends ArrayAdapter<SpinnerItem> {

    private final LayoutInflater inflater;
    private final List<SpinnerItem> items;

    public ImageSpinnerAdapter(@NonNull Context context, List<SpinnerItem> items) {
        // We pass 0 as resource ID here because we're overriding getView/getDropDownView completely
        super(context, 0, items);
        this.inflater = LayoutInflater.from(context);
        this.items = items;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // This method is for the view shown when the spinner is *closed*
        return createCustomView(position, convertView, parent, false);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // This method is for the views shown in the *dropdown list* when the spinner is open
        return createCustomView(position, convertView, parent, true);
    }

    private View createCustomView(int position, View convertView, ViewGroup parent, boolean isDropdown) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.spinner_image_item, parent, false);
            holder = new ViewHolder();
            holder.imageView = convertView.findViewById(R.id.itemImageView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        SpinnerItem currentItem = items.get(position);
        holder.imageView.setImageResource(currentItem.getImageResId());

        // You might want slightly different styling for the selected item vs dropdown items
        // For example, if isDropdown is false (selected item), you might hide the image
        // or change its size/position depending on your design.
        // For simplicity, we're using the same layout for both here.

        return convertView;
    }

    // ViewHolder pattern for performance
    private static class ViewHolder {
        ImageView imageView;
    }
}
