package org.example.pos;

import javafx.scene.control.Alert;
import org.example.pos.UserServices;

import java.io.*;
import java.util.*;

public class User implements Serializable {


    private static final long serialVersionUID = 1L;
    public  ArrayList<Item> itemArray = new ArrayList<>();
    public  ArrayList<Invoice> invoiceArray = new ArrayList<>();
    public transient Invoice currentInvoice;

    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void addNewItem(String itemName, String itemCategory, String itemUnit, double cost, double price, double availableStock){
        int a = 1;
        if(!UserServices.getCurrentUser().itemArray.isEmpty()){
            a = UserServices.getCurrentUser().itemArray.get(itemArray.size()-1).getItemCode()+1;
        }
        Item item = new Item(a,itemName, itemCategory, itemUnit, cost, price, availableStock);
        UserServices.getCurrentUser().itemArray.add(item);
        UserServices.updateFile();

    }

    public Item findItem(String name){
        for(int i=0; i<UserServices.getCurrentUser().itemArray.size(); i++){
            if(name.equals(UserServices.getCurrentUser().itemArray.get(i).getItemName())){
                return UserServices.getCurrentUser().itemArray.get(i);
            }
        }

        return null;
    }

    public void deleteItem(String name){
        for(int i=0; i<UserServices.getCurrentUser().itemArray.size(); i++){
            if(name.equals(UserServices.getCurrentUser().itemArray.get(i).getItemName())){
                UserServices.getCurrentUser().itemArray.remove(i);
                UserServices.updateFile();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText("Successful");
                alert.setContentText("Item was deleted successfully!");
                alert.showAndWait();
                return;

            }
        }
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Dialog");
        alert.setHeaderText("Not Found Error");
        alert.setContentText("Your Item can not be find! Try Again!");
        alert.showAndWait();
    }

    public  void addStock(String name,double newstock){
        Item item = findItem(name);
        item.setAvailableStock(item.getAvailableStock()+newstock);
        UserServices.updateFile();
    }

    public void createNewBill(String customerName, String date){
        int b = 1;
        if(!UserServices.getCurrentUser().invoiceArray.isEmpty()){
            b = UserServices.getCurrentUser().invoiceArray.get(invoiceArray.size()-1).getInvoiceNumber()+1;
        }
        Invoice invoice =  new Invoice(b,customerName,date);
        UserServices.getCurrentUser().invoiceArray.add(invoice);
        UserServices.updateFile();

    }

    public double calculateDailyProfit(String date){
        double dailyProfit = 0;
        for(int i=0; i<UserServices.getCurrentUser().invoiceArray.size();i++){
            if(Objects.equals(UserServices.getCurrentUser().invoiceArray.get(i).getDate(), date)){
                for(int j=0; j<UserServices.getCurrentUser().invoiceArray.get(i).itemsToBill.size();j++){
                    dailyProfit = dailyProfit + (UserServices.getCurrentUser().invoiceArray.get(i).itemsToBill.get(j).getNetPrice()-(UserServices.getCurrentUser().invoiceArray.get(i).itemsToBill.get(j).getCost()*UserServices.getCurrentUser().invoiceArray.get(i).itemsToBill.get(j).getQuantity()));
                }
            }
        }
        return dailyProfit;
    }





}
