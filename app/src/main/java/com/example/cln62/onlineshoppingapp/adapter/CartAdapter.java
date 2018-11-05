package com.example.cln62.onlineshoppingapp.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import com.example.cln62.onlineshoppingapp.ui.checkout.CartActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

//    Context context;
    CartActivity cartActivity;
    List<Product> mList;
    private CartAdapter.ClickListener clickListener;


    public CartAdapter(CartActivity cartActivity, List<Product> mList) {
        this.mList = mList;
//        this.context = context;
        this.cartActivity = cartActivity;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cart_layout,viewGroup,false);
//        tv_quantity = viewGroup.findViewById(R.id.textview_quantity);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder cartViewHolder, int position) {
        Product product = mList.get(position);
        cartViewHolder.tv_pname.setText(product.getPname());
        cartViewHolder.tv_price.setText("$" + product.getPrize());
        cartViewHolder.tv_shipping.setText(Constants.FREESHIPPING);
        cartViewHolder.tv_quantity.setText(product.getQuantity());
        Picasso.with(cartActivity)
                .load(product.getImage())
                .into(cartViewHolder.iv);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView iv;
        TextView tv_pname,tv_price,tv_shipping, tv_quantity,
                textViewAdd, textViewDelete;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_pname = itemView.findViewById(R.id.textview_productname2);
            tv_price = itemView.findViewById(R.id.textview_productprice2);
            tv_shipping = itemView.findViewById(R.id.textview_shippingdetail2);
            iv = itemView.findViewById(R.id.imageview_productimage2);
            tv_quantity = itemView.findViewById(R.id.textview_quantity);
            textViewAdd = itemView.findViewById(R.id.tv_add);
            textViewDelete = itemView.findViewById(R.id.tv_delete);

            textViewAdd.setOnClickListener(this);
            textViewDelete.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (clickListener == null) {
                return;
            }
            int id = v.getId();
            switch (id){
                case R.id.tv_add:
                    int quantity = Integer.parseInt(tv_quantity.getText().toString());
                    quantity += 1;
                    tv_quantity.setText(String.valueOf(quantity));
                    mList.get(getLayoutPosition()).setQuantity(String.valueOf(quantity));
                    cartActivity.refreshPayment();
                    clickListener.addClicked(v,getAdapterPosition(), quantity + 1);
                    break;
                case R.id.tv_delete:
                    int quantity1 = Integer.parseInt(tv_quantity.getText().toString());
                    quantity1 -= 1;
                    clickListener.deleteClicked(v,getAdapterPosition(), quantity1 - 1);
                    mList.get(getLayoutPosition()).setQuantity(String.valueOf(quantity1));
                    tv_quantity.setText(String.valueOf(quantity1));
                    cartActivity.refreshPayment();
                    if(quantity1 == 0){
                        clickListener.removeClicked(v,getAdapterPosition());
                        mList.remove(getLayoutPosition());
                        notifyDataSetChanged();
                    }
                    break;
            }
        }
    }

    public interface ClickListener
    {
        void addClicked(View view, int position, int quantity);

        void deleteClicked(View view, int position, int quantity);

        void removeClicked(View v, int adapterPosition);
    }

    public void setOnItemClickListener(CartAdapter.ClickListener clickListener){
        this.clickListener = clickListener;
    }
}
