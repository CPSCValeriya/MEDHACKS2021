package com.example.medhacks2021;

import java.util.ArrayList;
import java.util.List;

public class UserManager {

    private static UserManager instance;

    public List<User> getUsers() {
        return users;
    }

    @Override
    public String toString() {
       for (int i = 0 ; i < users.size(); i++){
           System.out.println("user: " + users.get(i).getName() + " with " + users.get(i).getPoints() + " points" );
       }
       return "";
    }


    public void addUsers(User user) {
        users.add(user);
    }

    private List<User> users;

    public static UserManager getInstance(){

        if(instance==null){
            instance = new UserManager();
        }
        return instance;
    }

    private UserManager(){
        users = new ArrayList();
    }

    public static boolean getStatus(){
        return instance == null;
    }

}
