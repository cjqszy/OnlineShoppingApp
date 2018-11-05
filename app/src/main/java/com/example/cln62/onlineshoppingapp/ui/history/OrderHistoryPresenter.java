package com.example.cln62.onlineshoppingapp.ui.history;

public class OrderHistoryPresenter implements OrderHistoryInterface.Presenter {

    OrderHistoryInterface.FragmentView fragmentView;

    public OrderHistoryPresenter(OrderHistoryFragment orderHistoryFragment) {
        fragmentView = orderHistoryFragment;
    }

    @Override
    public void intiFragView() {
        fragmentView.intiFragViewConfirm();
    }
}
