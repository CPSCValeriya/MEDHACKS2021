package com.example.medhacks2021;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class User {

    private String name;
    private long points;
    private String phoneNumber;
    private List<Contact> contacts;
    private String uniqueID;
    private UserManager userManager;

    public User(String name, Integer points, String phoneNumber) {
        this.name = name;
        this.points = points;
        this.phoneNumber =phoneNumber;
        this.contacts = new ArrayList();
        this.uniqueID = generateUniquePassword();
        userManager.setCurrentPassword(uniqueID);
    }

   public String getPassword(){
        return uniqueID;
    }

    private String generateUniquePassword() {
        userManager = UserManager.getInstance();

        String password = createPassword();

           while(userManager.getUniquePasswords().contains(password.toString())){
               password = createPassword();
            }

           return password;
    }

    private String createPassword(){
        String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz!@#$%&";
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(5);
        for (int i = 0; i < 5; i++)
            sb.append(chars.charAt(rnd.nextInt(chars.length())));
        return sb.toString();
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPoints() {
        return points;
    }

    public void setPoints(long points) {
        this.points = points;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", points=" + points +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", contacts=" + contacts +
                ", uniqueID='" + uniqueID + '\'' +
                ", userManager=" + userManager +
                '}';
    }


}
