package com.example.cln62.onlineshoppingapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cln62.onlineshoppingapp.R;
import com.example.cln62.onlineshoppingapp.constants.Constants;
import com.example.cln62.onlineshoppingapp.pojo.Product;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder> {

    private ClickListener clickListener;
    List<Product> productList;
    Context ctx;

    public ProductListAdapter(Context ctx, List<Product> productListDataList) {
        this.ctx = ctx;
        this.productList = productListDataList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.productlist_layout,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        Product product = productList.get(position);
        viewHolder.tv_pname.setText(product.getPname());
        viewHolder.tv_price.setText(product.getPrize());
        viewHolder.tv_shipping.setText(Constants.FREESHIPPING);
        Picasso.with(ctx)
                .load(product.getImage())
                .into(viewHolder.iv);
    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        ImageView iv;
        TextView tv_pname,tv_price,tv_shipping;

        public ViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
            tv_pname = itemView.findViewById(R.id.textview_productname);
            tv_price = itemView.findViewById(R.id.textview_productprice);
            tv_shipping = itemView.findViewById(R.id.textview_shippingdetail);
            iv = itemView.findViewById(R.id.imageview_productimage);

        }

        @Override
        public void onClick(View v) {
            if(clickListener != null)
            {
                clickListener.itemClicked(v,getAdapterPosition());
            }
        }
    }

    public interface ClickListener
    {
        public void itemClicked(View view, int position);
    }
}
