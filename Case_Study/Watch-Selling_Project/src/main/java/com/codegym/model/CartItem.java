package com.codegym.model;

public class CartItem {
    private Watch watch;
    private int quantity;

    public double getTotalPrice() {
        return watch.getPrice() * quantity;
    }


    public CartItem(Watch watch, int quantity) {
        this.watch = watch;
        this.quantity = quantity;
    }

    public Watch getWatch() {
        return watch;
    }

    public void setWatch(Watch watch) {
        this.watch = watch;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
