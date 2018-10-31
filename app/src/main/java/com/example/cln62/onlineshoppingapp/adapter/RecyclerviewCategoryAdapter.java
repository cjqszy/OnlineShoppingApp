package com.example.cln62.onlineshoppingapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cln62.onlineshoppingapp.R;
import com.example.cln62.onlineshoppingapp.pojo.Category;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerviewCategoryAdapter extends RecyclerView.Adapter<RecyclerviewCategoryAdapter.ViewHolder> {

    List<Category> mList;
    Context context;

    public RecyclerviewCategoryAdapter(List<Category> mList, Context context) {
        this.mList = mList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.category_itemlayout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Category category = mList.get(position);
        viewHolder.textViewCategory.setText(category.getCname());
        Picasso.with(context)
                .load(category.getCimagerl())
                .into(viewHolder.imageViewCategory);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageViewCategory;
        TextView textViewCategory;

        public ViewHolder(View itemView) {
            super(itemView);
            imageViewCategory = itemView.findViewById(R.id.image_category);
            textViewCategory = itemView.findViewById(R.id.text_category);
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
