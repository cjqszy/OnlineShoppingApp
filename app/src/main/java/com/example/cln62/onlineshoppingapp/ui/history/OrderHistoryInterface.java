package com.example.cln62.onlineshoppingapp.ui.history;

import com.example.cln62.onlineshoppingapp.pojo.OrderHistory;

import java.util.List;

public interface OrderHistoryInterface {
    
    interface Presenter{
        void intiFragView();
    }
    
    interface View{}
    
    interface FragmentView{
        void intiFragViewConfirm();

        void showOrderHistory(List<OrderHistory> orderHistory);

        void addShipmentTracking(List<OrderHistory> mList);

        void ShipmentTrackingAdded(OrderHistory orderHistory);

        void showShipmentInfo();
    }
}
