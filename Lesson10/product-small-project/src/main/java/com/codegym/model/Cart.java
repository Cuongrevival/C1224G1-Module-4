package com.codegym.model;


public class Cart {
    private Product product;
    private int quantity;
    private String formattedPrice;
    private String formattedTotal;


    public String getFormattedPrice() {
        return formattedPrice;
    }

    public void setFormattedPrice(String formattedPrice) {
        this.formattedPrice = formattedPrice;
    }

    public String getFormattedTotal() {
        return formattedTotal;
    }

    public void setFormattedTotal(String formattedTotal) {
        this.formattedTotal = formattedTotal;
    }
    public Cart(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public double getTotal() {
        double priceToUse = (product.getDiscountPrice() > 0) ? product.getDiscountPrice() : product.getPrice();
        return priceToUse * quantity;
    }

}
