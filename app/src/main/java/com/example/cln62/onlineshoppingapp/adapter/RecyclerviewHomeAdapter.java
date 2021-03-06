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
import com.example.cln62.onlineshoppingapp.pojo.CatSubcategory;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerviewHomeAdapter extends RecyclerView.Adapter<RecyclerviewHomeAdapter.ViewHolder> {

    List<CatSubcategory> mList;
    Context context;
    private OnItemClickListener mOnItemClickListener, mOnItemClickListener2;
    String cid;

    public RecyclerviewHomeAdapter(List<CatSubcategory> mList, Context context) {
        this.mList = mList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.category_itemlayout, viewGroup, false);

        final ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        CatSubcategory category = mList.get(position);
        viewHolder.textViewCategory.setText(category.getCname());
        Picasso.with(context)
                .load(category.getCimagerl())
                .into(viewHolder.imageViewCategory);
    }



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageViewCategory;
        TextView textViewCategory;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            imageViewCategory = itemView.findViewById(R.id.image_category);
            textViewCategory = itemView.findViewById(R.id.text_category);
        }

        @Override
        public void onClick(View v) {
            if(mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(v, getAdapterPosition());
            }
            if(mOnItemClickListener2 != null) {
                mOnItemClickListener2.onItemClick(v, getAdapterPosition(), cid);
            }
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }



    public void setOnItemClickListener(OnItemClickListener itemClickListener){
        mOnItemClickListener = itemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener, String cid){
        mOnItemClickListener2 = itemClickListener;
        this.cid = cid;
    }

    public interface OnItemClickListener{
        void onItemClick(View view, int position);

        void onItemClick(View view, int position, String cid);
    }
}
