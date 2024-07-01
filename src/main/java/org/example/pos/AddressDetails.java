package org.example.pos;

import com.itextpdf.kernel.color.Color;

public class AddressDetails {
    private String billingInfoText = ConstantUtil.BILLING_INFO;
    private String shippingInfoText = ConstantUtil.SHIPPING_INFO;
    private String billingCompanyText = ConstantUtil.BILLING_COMPANY;
    private String billingCompany = ConstantUtil.EMPTY;
    private String billingNameText = ConstantUtil.BILLING_NAME;
    private String billingName = ConstantUtil.EMPTY;
    private String billingAddressText = ConstantUtil.BILLING_ADDRESS;
    private String billingAddress = ConstantUtil.EMPTY;
    private String billingEmailText = ConstantUtil.BILLING_EMAIL;
    private String billingEmail = ConstantUtil.EMPTY;

    private String shippingNameText = ConstantUtil.SHIPPING_NAME;
    private String shippingName = ConstantUtil.EMPTY;
    private String shippingAddressText = ConstantUtil.SHIPPING_ADDRESS;
    private String shippingAddress = ConstantUtil.EMPTY;
    private Color borderColor = Color.GRAY;

    public AddressDetails(String billingInfoText, String shippingInfoText, String billingCompanyText, String billingCompany, String billingNameText, String billingName, String billingAddressText, String billingAddress, String billingEmailText, String billingEmail, String shippingNameText, String shippingName, String shippingAddressText, String shippingAddress, Color borderColor) {
        this.billingInfoText = billingInfoText;
        this.shippingInfoText = shippingInfoText;
        this.billingCompanyText = billingCompanyText;
        this.billingCompany = billingCompany;
        this.billingNameText = billingNameText;
        this.billingName = billingName;
        this.billingAddressText = billingAddressText;
        this.billingAddress = billingAddress;
        this.billingEmailText = billingEmailText;
        this.billingEmail = billingEmail;
        this.shippingNameText = shippingNameText;
        this.shippingName = shippingName;
        this.shippingAddressText = shippingAddressText;
        this.shippingAddress = shippingAddress;
        this.borderColor = borderColor;
    }

    public AddressDetails() {
    }

    public AddressDetails setBillingInfoText(String billingInfoText) {
        this.billingInfoText = billingInfoText;
        return this;
    }

    public AddressDetails setShippingInfoText(String shippingInfoText) {
        this.shippingInfoText = shippingInfoText;
        return this;
    }

    public AddressDetails setBillingCompanyText(String billingCompanyText) {
        this.billingCompanyText = billingCompanyText;
        return this;
    }

    public AddressDetails setBillingCompany(String billingCompany) {
        this.billingCompany = billingCompany;
        return this;
    }

    public AddressDetails setBillingNameText(String billingNameText) {
        this.billingNameText = billingNameText;
        return this;
    }

    public AddressDetails setBillingName(String billingName) {
        this.billingName = billingName;
        return this;
    }

    public AddressDetails setBillingAddressText(String billingAddressText) {
        this.billingAddressText = billingAddressText;
        return this;
    }

    public AddressDetails setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
        return this;
    }

    public AddressDetails setBillingEmailText(String billingEmailText) {
        this.billingEmailText = billingEmailText;
        return this;
    }

    public AddressDetails setBillingEmail(String billingEmail) {
        this.billingEmail = billingEmail;
        return this;
    }

    public AddressDetails setShippingNameText(String shippingNameText) {
        this.shippingNameText = shippingNameText;
        return this;
    }

    public AddressDetails setShippingName(String shippingName) {
        this.shippingName = shippingName;
        return this;
    }

    public AddressDetails setShippingAddressText(String shippingAddressText) {
        this.shippingAddressText = shippingAddressText;
        return this;
    }

    public AddressDetails setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
        return this;
    }

    public AddressDetails setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
        return this;
    }

    public AddressDetails build() {
        return this;
    }

    public String getBillingInfoText() {
        return this.billingInfoText;
    }

    public String getShippingInfoText() {
        return this.shippingInfoText;
    }

    public String getBillingCompanyText() {
        return this.billingCompanyText;
    }

    public String getBillingCompany() {
        return this.billingCompany;
    }

    public String getBillingNameText() {
        return this.billingNameText;
    }

    public String getBillingName() {
        return this.billingName;
    }

    public String getBillingAddressText() {
        return this.billingAddressText;
    }

    public String getBillingAddress() {
        return this.billingAddress;
    }

    public String getBillingEmailText() {
        return this.billingEmailText;
    }

    public String getBillingEmail() {
        return this.billingEmail;
    }

    public String getShippingNameText() {
        return this.shippingNameText;
    }

    public String getShippingName() {
        return this.shippingName;
    }

    public String getShippingAddressText() {
        return this.shippingAddressText;
    }

    public String getShippingAddress() {
        return this.shippingAddress;
    }

    public Color getBorderColor() {
        return this.borderColor;
    }

    public String toString() {
        return "AddressDetails(billingInfoText=" + this.getBillingInfoText() + ", shippingInfoText=" + this.getShippingInfoText() + ", billingCompanyText=" + this.getBillingCompanyText() + ", billingCompany=" + this.getBillingCompany() + ", billingNameText=" + this.getBillingNameText() + ", billingName=" + this.getBillingName() + ", billingAddressText=" + this.getBillingAddressText() + ", billingAddress=" + this.getBillingAddress() + ", billingEmailText=" + this.getBillingEmailText() + ", billingEmail=" + this.getBillingEmail() + ", shippingNameText=" + this.getShippingNameText() + ", shippingName=" + this.getShippingName() + ", shippingAddressText=" + this.getShippingAddressText() + ", shippingAddress=" + this.getShippingAddress() + ", borderColor=" + this.getBorderColor() + ")";
    }
}
