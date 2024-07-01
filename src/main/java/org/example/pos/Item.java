package org.example.pos;

import java.io.Serializable;

public class Item implements Serializable {


    private String itemName;
    private String category;
    private int itemCode = 0;
    private String unit;
    private double cost;
    private double price;
    private double availableStock;
    private double netPrice;
    private double quantity;
    private double discount;



    @Override
    public String toString() {
        return "Item{" +
                "itemName='" + itemName + '\'' +
                ", itemCode=" + itemCode +
                ", unit='" + unit + '\'' +
                ", cost=" + cost +
                ", price=" + price +
                ", availableStock=" + availableStock +
                ", netPrice=" + netPrice +
                '}';
    }
    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    public double getNetPrice() {
        return netPrice;
    }

    public void setNetPrice(double netPrice) {
        this.netPrice = netPrice;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public Item(int itemCode,String itemName, String itemCategory, String unit,double cost, double price, double availableStock) {
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.category = itemCategory;
        this.unit = unit;
        this.cost = cost;
        this.price = price;
        this.availableStock = availableStock;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemCode() {
        return itemCode;
    }

    public void setItemCode(int itemCode) {
        this.itemCode = itemCode;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getAvailableStock() {
        return availableStock;
    }

    public void setAvailableStock(double availableStock) {
        this.availableStock = availableStock;
    }
}
