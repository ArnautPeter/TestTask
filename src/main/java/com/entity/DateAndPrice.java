package com.entity;


public class DateAndPrice {

    private String date;
    private int price;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


    @Override
    public String toString() {
        return "DateAndPrice{" +
                "date=" + date +
                ", price=" + price +
                '}';
    }
}
