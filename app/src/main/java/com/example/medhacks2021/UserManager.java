package com.example.medhacks2021;

import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class UserManager {

    private List<String> uniquePasswords;
    private static UserManager instance;
    private List<User> users;
    private User currentUser;
    private String currentPassword;

    public List<User> getUsers() {
        return users;
    }

    public List<String> getUniquePasswords() {
        return uniquePasswords;
    }


    @Override
    public String toString() {
       for (int i = 0 ; i < users.size(); i++){
           System.out.println("user: " + users.get(i).getName() + " with " + users.get(i).getPoints() + " points" + " and password: " + users.get(i).getPassword());
       }
       return "";
    }

    public void addUsers(User user) {
        users.add(user);
    }

    public static UserManager getInstance(){

        if(instance==null){
            instance = new UserManager();
        }
        return instance;
    }

    private UserManager(){
        users = new ArrayList();
        uniquePasswords = new ArrayList();
    }

    public static boolean getStatus(){
        return instance == null;
    }
    public void addContact(Contact contact){
        System.out.print("CURRENT USER: " + currentUser.getName());
        System.out.print("ADDING CONTACT " + contact.getName());
        currentUser.getContacts().add(contact);
    }

    public User getCurrentUser(){
        return currentUser;
    }

    public void setCurrentUser(User user){
        currentUser = user;
    }

    public String getCurrentPassword(){
        return currentPassword;
    }

    public void setCurrentPassword(String password){
        currentPassword = password;
    }

    public User getUserFromPassword(String password) {
        for(int i = 0 ; i < users.size() ; i++){
            if(password.equals(uniquePasswords.get(i))){
                return users.get(i);
            }
        }
        return null;
    }
}
