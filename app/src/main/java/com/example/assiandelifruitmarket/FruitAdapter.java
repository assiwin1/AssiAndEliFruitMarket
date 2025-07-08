package com.example.assiandelifruitmarket;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.FruitViewHolder> {
    public ArrayList<FruitItem> dataList;
    public FruitAdapter(ArrayList<FruitItem> dataList){
        this.dataList = dataList;
    }
    public class FruitViewHolder extends RecyclerView.ViewHolder{
        public final TextView tvDesc;
        public final TextView tvName;
        public final ImageView ivImage;
        public final ImageView ivFav;
        public FruitViewHolder(View view)
        {
            super(view);
            //init your views here
            tvDesc = view.findViewById(R.id.textView_description);
            tvName = view.findViewById(R.id.textView_title);
            ivImage = view.findViewById(R.id.imageViewItem);
            ivFav = view.findViewById(R.id.imageViewFav);
        }
    }
    @NonNull
    @Override
    public FruitAdapter.FruitViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.item_row, null);
        return new FruitViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FruitAdapter.FruitViewHolder viewHolder, int position) {
        FruitItem currItem = dataList.get(position);
        viewHolder.tvName.setText(currItem.getName());
        viewHolder.tvDesc.setText(currItem.getDescription());
        viewHolder.ivImage.setImageResource(currItem.getImageResource());
        viewHolder.ivFav.setImageResource(currItem.isFavorite() ?R.drawable.heart :R.drawable.heartless);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Selection->", "onClick: "+ currItem.getName());
            }
        });
        viewHolder.ivFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Selection->", "onClickFav: before "+ currItem.isFavorite());
                currItem.setFavorite(!currItem.isFavorite());
                viewHolder.ivFav.setImageResource(currItem.isFavorite() ?R.drawable.heart :R.drawable.heartless);
                Log.d("Selection->", "onClickFav: after "+ currItem.isFavorite());
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
