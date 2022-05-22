package com.example.transport;

public class VehicaleData2 {
    private String category;
    private String Vehicale_Medel;
    private String Vehicale_Number;
    private String RCBookNumber;
    private String Available_Seats;
    private String Vehicale_Location;
    private String itemImage;
    private String Ownername;
    private String Contact;
    private String key;

    public VehicaleData2() {
    }

    public VehicaleData2(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public VehicaleData2(String category, String vehicale_Medel, String vehicale_Number, String RCBookNumber, String available_Seats, String vehicale_Location, String itemImage, String ownername, String contact) {
        this.category = category;
        Vehicale_Medel = vehicale_Medel;
        Vehicale_Number = vehicale_Number;
        this.RCBookNumber = RCBookNumber;
        Available_Seats = available_Seats;
        Vehicale_Location = vehicale_Location;
        this.itemImage = itemImage;
        Ownername = ownername;
        Contact = contact;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getVehicale_Medel() {
        return Vehicale_Medel;
    }

    public void setVehicale_Medel(String vehicale_Medel) {
        Vehicale_Medel = vehicale_Medel;
    }

    public String getVehicale_Number() {
        return Vehicale_Number;
    }

    public void setVehicale_Number(String vehicale_Number) {
        Vehicale_Number = vehicale_Number;
    }

    public String getRCBookNumber() {
        return RCBookNumber;
    }

    public void setRCBookNumber(String RCBookNumber) {
        this.RCBookNumber = RCBookNumber;
    }

    public String getAvailable_Seats() {
        return Available_Seats;
    }

    public void setAvailable_Seats(String available_Seats) {
        Available_Seats = available_Seats;
    }

    public String getVehicale_Location() {
        return Vehicale_Location;
    }

    public void setVehicale_Location(String vehicale_Location) {
        Vehicale_Location = vehicale_Location;
    }

    public String getItemImage() {
        return itemImage;
    }

    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
    }

    public String getOwnername() {
        return Ownername;
    }

    public void setOwnername(String ownername) {
        Ownername = ownername;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        Contact = contact;
    }
}
