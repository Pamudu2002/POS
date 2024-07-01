package org.example.pos;

public class ProductTableHeader {
    String description = ConstantUtil.PRODUCT_TABLE_DESCRIPTION;
    String quantity = ConstantUtil.PRODUCT_TABLE_QUANTITY;
    String price = ConstantUtil.PRODUCT_TABLE_PRICE;

    public ProductTableHeader(String description, String quantity, String price) {
        this.description = description;
        this.quantity = quantity;
        this.price = price;
    }

    public ProductTableHeader() {
    }

    public ProductTableHeader setDescription(String description) {
        this.description = description;
        return this;
    }

    public ProductTableHeader setQuantity(String quantity) {
        this.quantity = quantity;
        return this;
    }

    public ProductTableHeader setPrice(String price) {
        this.price = price;
        return this;
    }

    public ProductTableHeader build() {
        return this;
    }

    public String getDescription() {
        return this.description;
    }

    public String getQuantity() {
        return this.quantity;
    }

    public String getPrice() {
        return this.price;
    }

    public String toString() {
        return "ProductTableHeader(description=" + this.getDescription() + ", quantity=" + this.getQuantity() + ", price=" + this.getPrice() + ")";
    }
}
