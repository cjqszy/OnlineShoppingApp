package com.example.cln62.onlineshoppingapp.ui.checkout;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cln62.onlineshoppingapp.R;
import com.example.cln62.onlineshoppingapp.data.CartDao;
import com.example.cln62.onlineshoppingapp.network.NetworkOrderSummary;
import com.example.cln62.onlineshoppingapp.pojo.Product;
import com.example.cln62.onlineshoppingapp.utils.MySharedPrefences;

import java.util.ArrayList;
import java.util.List;

public class CheckoutFragment extends Fragment {

    private List<Product> myList;
    CartDao cartDao;
    MySharedPrefences mySharedPrefences;
    TextView textViewOrderSummary;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_checkout, container, false);

        String bAddress = getArguments().getString("bAddress");
        String dAddress = getArguments().getString("dAddress");
        String payment = getArguments().getString("payment");
        myList = new ArrayList<>();
        cartDao = new CartDao(getActivity());
        myList = cartDao.getCartList();
        mySharedPrefences = new MySharedPrefences();
        textViewOrderSummary = view.findViewById(R.id.textview_ordersummary);

        int subtotal =0;
        String itemIds = "";
        String itemNames ="";
        int itemQty = 0;

        for(int i=0; i<myList.size();i++)
        {
            itemIds +=  myList.get(i).getId() + ",";
            itemNames += myList.get(i).getPname()+ ",";
            itemQty += Integer.parseInt(myList.get(i).getQuantity());
        }
        int totPrice = (int) (subtotal + (subtotal * 0.08));
        String tp = "" + totPrice;
        String iQty = "" + itemQty;

        String url = "http://rjtmobile.com/aamir/e-commerce/android-app/orders.php?" +
                "?&item_id="+itemIds+
                "&item_names="+itemNames+
                "&item_quantity="+iQty+
                "&final_price="+payment+
                "&&api_key="+mySharedPrefences.getApiKey(getActivity())+
                "&user_id="+mySharedPrefences.getId(getActivity())+
                "&user_name="+mySharedPrefences.getFName(getActivity())+
                "&billingadd="+bAddress+
                "&deliveryadd="+dAddress+
                "&mobile="+mySharedPrefences.getMobile(getActivity())+
                "&email="+mySharedPrefences.getEmail(getActivity());

        new NetworkOrderSummary().getOrderSummary(url, this);

        return view;
    }

    public void showOrderSummary(String orderSummary) {

        textViewOrderSummary.setText(orderSummary);
        ((CheckOutActivity) getActivity()).hideEditText();
    }
}
