package org.example.pos;

import com.itextpdf.kernel.color.Color;

public class HeaderDetails {
    String invoiceTitle = ConstantUtil.INVOICE_TITLE;
    String invoiceNoText = ConstantUtil.INVOICE_NO_TEXT;
    String invoiceDateText = ConstantUtil.INVOICE_DATE_TEXT;
    String invoiceNo = ConstantUtil.EMPTY;
    String invoiceDate = ConstantUtil.EMPTY;
    Color borderColor = Color.GRAY;

    public HeaderDetails(String invoiceTitle, String invoiceNoText, String invoiceDateText, String invoiceNo, String invoiceDate, Color borderColor) {
        this.invoiceTitle = invoiceTitle;
        this.invoiceNoText = invoiceNoText;
        this.invoiceDateText = invoiceDateText;
        this.invoiceNo = invoiceNo;
        this.invoiceDate = invoiceDate;
        this.borderColor = borderColor;
    }

    public HeaderDetails() {
    }

    public HeaderDetails setInvoiceTitle(String invoiceTitle) {
        this.invoiceTitle = invoiceTitle;
        return this;
    }

    public HeaderDetails setInvoiceNoText(String invoiceNoText) {
        this.invoiceNoText = invoiceNoText;
        return this;
    }

    public HeaderDetails setInvoiceDateText(String invoiceDateText) {
        this.invoiceDateText = invoiceDateText;
        return this;
    }

    public HeaderDetails setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
        return this;
    }

    public HeaderDetails setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
        return this;
    }

    public HeaderDetails setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
        return this;
    }

    public HeaderDetails build() {
        return this;
    }

    public String getInvoiceTitle() {
        return this.invoiceTitle;
    }

    public String getInvoiceNoText() {
        return this.invoiceNoText;
    }

    public String getInvoiceDateText() {
        return this.invoiceDateText;
    }

    public String getInvoiceNo() {
        return this.invoiceNo;
    }

    public String getInvoiceDate() {
        return this.invoiceDate;
    }

    public Color getBorderColor() {
        return this.borderColor;
    }

    public String toString() {
        return "HeaderDetails(invoiceTitle=" + this.getInvoiceTitle() + ", invoiceNoText=" + this.getInvoiceNoText() + ", invoiceDateText=" + this.getInvoiceDateText() + ", invoiceNo=" + this.getInvoiceNo() + ", invoiceDate=" + this.getInvoiceDate() + ", borderColor=" + this.getBorderColor() + ")";
    }
}
