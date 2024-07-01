package org.example.pos;

import javafx.scene.control.Alert;
import org.example.pos.Item;
import org.example.pos.UserServices;

import java.io.Serializable;
import java.util.ArrayList;


import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Invoice implements Serializable {


    private int invoiceNumber = 0;
    private String customerName;
    private String date;
    private double totalPrice;
    private double cash;
    private double balance;
    private boolean isPending = true;

    public boolean isPending() {
        return isPending;
    }

    public void setPending(boolean pending) {
        isPending = pending;
    }

    public Invoice(int invoiceNumber, String customerName, String date) {
        this.invoiceNumber = invoiceNumber;
        this.customerName = customerName;
        this.date = date;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
    public double getCash() {
        return cash;
    }

    public void setCash(double cash) {
        this.cash = cash;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getInvoiceNumber() {
        return invoiceNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<Item> itemsToBill = new ArrayList<>();
    public double calculateTotalPrice(){
        totalPrice = 0;
        for (int i = 0; i < itemsToBill.size(); i++) {
            totalPrice = totalPrice + itemsToBill.get(i).getNetPrice();
        }
        return totalPrice;
    }

    public void generatePDF() throws FileNotFoundException {
        String pdfName= UserServices.getCurrentUser().currentInvoice.invoiceNumber+"_"+UserServices.getCurrentUser().currentInvoice.getCustomerName()+".pdf";

        CodingErrorPdfInvoiceCreator cepdf=new CodingErrorPdfInvoiceCreator(pdfName);

        cepdf.createDocument();

        //Create Header start
        HeaderDetails header=new HeaderDetails();
        header.setInvoiceNo(String.valueOf(UserServices.getCurrentUser().currentInvoice.invoiceNumber)).setInvoiceDate(UserServices.getCurrentUser().currentInvoice.getDate()).build();
        cepdf.createHeader(header);
        //Header End

        //Create Address start
        AddressDetails addressDetails=new AddressDetails();
        addressDetails.setBillingCompanyText("Shop Name");
        addressDetails.setShippingAddressText("");
        addressDetails.setShippingInfoText("Customer Details");
        addressDetails.setBillingNameText("Tel");
        addressDetails
                .setBillingCompany("Welgama Hardware")
                .setBillingName("077 027 0660 / 077 232 9207")
                .setBillingAddress("Main Street\n Athura\nBulathsinhala")
                .setBillingEmail("jayawardanathilani@gmail.com")
                .setShippingName(UserServices.getCurrentUser().currentInvoice.customerName+"\n")
                .setShippingAddress("")
                .build();

        cepdf.createAddress(addressDetails);
        //Address end

        //Product Start
        ProductTableHeader productTableHeader=new ProductTableHeader();
        cepdf.createTableHeader(productTableHeader);
        List<Product> productList= new ArrayList<>();
        for (int i = 0; i < UserServices.getCurrentUser().currentInvoice.itemsToBill.size(); i++) {
            productList.add(new Product(UserServices.getCurrentUser().currentInvoice.itemsToBill.get(i).getItemName(),UserServices.getCurrentUser().currentInvoice.itemsToBill.get(i).getQuantity(),UserServices.getCurrentUser().currentInvoice.itemsToBill.get(i).getNetPrice()));

        }
        cepdf.createProduct(productList);
        //Product End

        //Term and Condition Start
        List<String> TncList=new ArrayList<>();
        TncList.add("");
        TncList.add("");
        String imagePath="src/main/resources/ce_logo_circle_transparent.png";
        cepdf.createTnc(TncList,false,imagePath);
        // Term and condition end
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Successful");
        alert.setContentText("PDF was generated successfully!");
        alert.showAndWait();

    }


}
