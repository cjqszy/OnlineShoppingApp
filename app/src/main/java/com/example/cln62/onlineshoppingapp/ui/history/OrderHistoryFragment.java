package com.example.cln62.onlineshoppingapp.ui.history;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cln62.onlineshoppingapp.R;
import com.example.cln62.onlineshoppingapp.adapter.OrderHistoryAdapter;
import com.example.cln62.onlineshoppingapp.network.NetworkOrderHistory;
import com.example.cln62.onlineshoppingapp.network.NetworkShipmentTracking;
import com.example.cln62.onlineshoppingapp.pojo.OrderHistory;

import java.util.ArrayList;
import java.util.List;

public class OrderHistoryFragment extends Fragment implements OrderHistoryInterface.FragmentView{

    private RecyclerView recyclerViewOrder;
//    private List<OrderHistory> orderHistoryItem;
    OrderHistoryPresenter orderHistoryPresenter;
    NetworkOrderHistory networkOrderHistory;
    NetworkShipmentTracking networkShipmentTracking;
    OrderHistoryAdapter orderHistoryAdapter;
    private static final String TAG = "OrderHistoryFragment";
    List<OrderHistory> oldList, newList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_orderhistory, container, false);
        recyclerViewOrder = view.findViewById(R.id.recyclerview_orderhistory);
        networkOrderHistory = new NetworkOrderHistory(this);
        networkShipmentTracking = new NetworkShipmentTracking();
        newList = new ArrayList<>();

        orderHistoryPresenter = new OrderHistoryPresenter(this);
        orderHistoryPresenter.intiFragView();
        return view;
    }

    @Override
    public void intiFragViewConfirm() {
        Log.i(TAG, "2");
        networkOrderHistory.getOrderHistory();
    }

    @Override
    public void showOrderHistory(List<OrderHistory> orderHistoryList) {
//        orderHistoryItem.add(new OrderHistory(orderHistory));
        Log.i(TAG, "3");
        if (orderHistoryList == null || orderHistoryList.size() == 0) {
            Log.i(TAG, "4");
            return;
        }
        Log.i(TAG, orderHistoryList.get(0).getOrderId());
        orderHistoryAdapter = new OrderHistoryAdapter(getActivity(), orderHistoryList);
        recyclerViewOrder.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewOrder.setAdapter(orderHistoryAdapter);
    }

    @Override
    public void addShipmentTracking(List<OrderHistory> mList) {
        networkShipmentTracking.addShipmentTracking(getActivity(), this, mList);
        oldList = mList;
    }

    @Override
    public void showShipmentInfo() {
        Log.i(TAG, "error2");
        OrderHistoryAdapter orderHistoryAdapter2 = new OrderHistoryAdapter(getActivity(), newList);
        recyclerViewOrder.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewOrder.setAdapter(orderHistoryAdapter2);
    }

    @Override
    public void ShipmentTrackingAdded(OrderHistory orderHistory) {
        Log.i(TAG, "error");
        newList.add(orderHistory);
        if (newList.size() == oldList.size()) {
            showShipmentInfo();
        }
    }
}
