package com.example.sendapp.ui.entity.send;

public class SendInfo {
    private String id;
    //送货单号
    private String sendNo;
    //送货总数
    private int sumQty;
    //装车箱数
    private int basketQty;
    //司机
    private String driver;
    //车牌号
    private String carNo;
    //目的地
    private String destination;
    //出发地
    private String startPlace;
    //送货状态
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSendNo() {
        return sendNo;
    }

    public void setSendNo(String sendNo) {
        this.sendNo = sendNo;
    }

    public int getSumQty() {
        return sumQty;
    }

    public void setSumQty(int sumQty) {
        this.sumQty = sumQty;
    }

    public int getBasketQty() {
        return basketQty;
    }

    public void setBasketQty(int basketQty) {
        this.basketQty = basketQty;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getStartPlace() {
        return startPlace;
    }

    public void setStartPlace(String startPlace) {
        this.startPlace = startPlace;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
