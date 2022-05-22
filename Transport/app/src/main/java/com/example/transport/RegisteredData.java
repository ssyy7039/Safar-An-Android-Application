package com.example.transport;

public class RegisteredData {
    private String Name;
    private String Email;
    private String Contact;
    private String Password;
    private String Address;


    public RegisteredData() {
    }


    public RegisteredData(String name, String email, String contact, String password, String address) {
        Name = name;
        Email = email;
        Contact = contact;
        Password = password;
        Address = address;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        Contact = contact;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }
}
