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
import com.example.cln62.onlineshoppingapp.constants.Constants;
import com.example.cln62.onlineshoppingapp.pojo.Product;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    Context context;
    List<Product> mList;

    public CartAdapter(Context context, List<Product> mList) {
        this.mList = mList;
        this.context = context;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cart_layout,viewGroup,false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder cartViewHolder, int position) {
        Product product = mList.get(position);
        cartViewHolder.tv_pname.setText(product.getPname());
        cartViewHolder.tv_price.setText("$" + product.getPrize());
        cartViewHolder.tv_shipping.setText(Constants.FREESHIPPING);
        cartViewHolder.tv_quantity.setText("quantity: " + product.getQuantity());
        Picasso.with(context)
                .load(product.getImage())
                .into(cartViewHolder.iv);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView iv;
        TextView tv_pname,tv_price,tv_shipping, tv_quantity;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
            tv_pname = itemView.findViewById(R.id.textview_productname2);
            tv_price = itemView.findViewById(R.id.textview_productprice2);
            tv_shipping = itemView.findViewById(R.id.textview_shippingdetail2);
            iv = itemView.findViewById(R.id.imageview_productimage2);
            tv_quantity = itemView.findViewById(R.id.textview_quantity);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
