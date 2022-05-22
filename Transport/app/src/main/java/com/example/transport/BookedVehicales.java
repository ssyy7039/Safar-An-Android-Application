package com.example.transport;

public class BookedVehicales {
    private String Type;
    private String Model;
    private String Location;
    private String OwnerName;
    private String Contact;
    private String Vehicale_Number;
    private String RcBook_Number;
    private String Seats;
    private String ImageUrl;
    private String Key;

    public BookedVehicales() {
    }

    public BookedVehicales(String key) {
        Key = key;
    }

    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }

    public BookedVehicales(String type, String model, String location, String ownerName, String contact, String vehicale_Number, String rcBook_Number, String seats, String imageUrl) {
        Type = type;
        Model = model;
        Location = location;
        OwnerName = ownerName;
        Contact = contact;
        Vehicale_Number = vehicale_Number;
        RcBook_Number = rcBook_Number;
        Seats = seats;
        ImageUrl = imageUrl;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getOwnerName() {
        return OwnerName;
    }

    public void setOwnerName(String ownerName) {
        OwnerName = ownerName;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        Contact = contact;
    }

    public String getVehicale_Number() {
        return Vehicale_Number;
    }

    public void setVehicale_Number(String vehicale_Number) {
        Vehicale_Number = vehicale_Number;
    }

    public String getRcBook_Number() {
        return RcBook_Number;
    }

    public void setRcBook_Number(String rcBook_Number) {
        RcBook_Number = rcBook_Number;
    }

    public String getSeats() {
        return Seats;
    }

    public void setSeats(String seats) {
        Seats = seats;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }
}
