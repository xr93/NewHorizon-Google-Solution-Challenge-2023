package com.ascent.newhorizon;

public class CommissionForm {

    private String orderName, orderPhoneNum, orderEmail;

    public CommissionForm() {
    }

    public CommissionForm(String orderName, String orderPhoneNum, String orderEmail) {
        this.orderName = orderName;
        this.orderPhoneNum = orderPhoneNum;
        this.orderEmail = orderEmail;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getOrderPhoneNum() {
        return orderPhoneNum;
    }

    public void setOrderPhoneNum(String orderPhoneNum) {
        this.orderPhoneNum = orderPhoneNum;
    }

    public String getOrderEmail() {
        return orderEmail;
    }

    public void setOrderEmail(String orderEmail) {
        this.orderEmail = orderEmail;
    }
}
