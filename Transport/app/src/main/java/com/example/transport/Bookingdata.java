package com.example.transport;

public class Bookingdata {
    private String PickupPoint;
    private String Duration;
    private String PancardNumber;
    private String AadharcardNumber;
    private String DrivingLiscenceNumber;
    private String Name;
    private String Contact;
    private String VehicaleNo;
    private String key;

    public Bookingdata() {
    }

    public Bookingdata(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Bookingdata(String pickupPoint, String duration, String pancardNumber, String aadharcardNumber, String drivingLiscenceNumber, String name, String contact, String vehicaleNo) {
        PickupPoint = pickupPoint;
        Duration = duration;
        PancardNumber = pancardNumber;
        AadharcardNumber = aadharcardNumber;
        DrivingLiscenceNumber = drivingLiscenceNumber;
        Name = name;
        Contact = contact;
        VehicaleNo = vehicaleNo;
    }

    public String getPickupPoint() {
        return PickupPoint;
    }

    public void setPickupPoint(String pickupPoint) {
        PickupPoint = pickupPoint;
    }

    public String getDuration() {
        return Duration;
    }

    public void setDuration(String duration) {
        Duration = duration;
    }

    public String getPancardNumber() {
        return PancardNumber;
    }

    public void setPancardNumber(String pancardNumber) {
        PancardNumber = pancardNumber;
    }

    public String getAadharcardNumber() {
        return AadharcardNumber;
    }

    public void setAadharcardNumber(String aadharcardNumber) {
        AadharcardNumber = aadharcardNumber;
    }

    public String getDrivingLiscenceNumber() {
        return DrivingLiscenceNumber;
    }

    public void setDrivingLiscenceNumber(String drivingLiscenceNumber) {
        DrivingLiscenceNumber = drivingLiscenceNumber;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        Contact = contact;
    }

    public String getVehicaleNo() {
        return VehicaleNo;
    }

    public void setVehicaleNo(String vehicaleNo) {
        VehicaleNo = vehicaleNo;
    }
}
