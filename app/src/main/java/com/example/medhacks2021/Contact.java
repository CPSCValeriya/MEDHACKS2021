package com.example.medhacks2021;

public class Contact {

    private String name;
    private long points;
    private String phoneNumber;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public Contact(String name, Integer points, String phoneNumber) {
        this.name = name;
        this.points = points;
        this.phoneNumber =phoneNumber;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "name='" + name + '\'' +
                ", points=" + points +
                '}';
    }


    
}
