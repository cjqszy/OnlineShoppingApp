package com.example.cln62.onlineshoppingapp.pojo;

public class OrderHistory {

    private String orderId;
    private String orderTime;
    private String orderPrice;
    private String orderName;
    private String orderBas;
    private String orderMobile;
    private String shipmentId;
    private String shipmentTracking;

    public OrderHistory(String orderId, String orderTime, String orderPrice, String orderName, String orderBas, String orderMobile) {
        this.orderId = orderId;
        this.orderTime = orderTime;
        this.orderPrice = orderPrice;
        this.orderName = orderName;
        this.orderBas = orderBas;
        this.orderMobile = orderMobile;
    }

    public OrderHistory(String orderId, String orderTime, String orderPrice, String orderName,
                        String orderBas, String orderMobile, String shipmentId, String shipmentTracking) {
        this.orderId = orderId;
        this.orderTime = orderTime;
        this.orderPrice = orderPrice;
        this.orderName = orderName;
        this.orderBas = orderBas;
        this.orderMobile = orderMobile;
        this.shipmentId = shipmentId;
        this.shipmentTracking = shipmentTracking;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(String orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getOrderBas() {
        return orderBas;
    }

    public void setOrderBas(String orderBas) {
        this.orderBas = orderBas;
    }

    public String getOrderMobile() {
        return orderMobile;
    }

    public void setOrderMobile(String orderMobile) {
        this.orderMobile = orderMobile;
    }

    public String getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(String shipmentId) {
        this.shipmentId = shipmentId;
    }

    public String getShipmentTracking() {
        return shipmentTracking;
    }

    public void setShipmentTracking(String shipmentTracking) {
        this.shipmentTracking = shipmentTracking;
    }
}
