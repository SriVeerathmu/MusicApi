package com.example.demo.model;

public class User {
    String username;
    String phoneno;
    String age;
    String gender;
    String location;
    String address;

    public  User(String username, String phoneno ,String age ,String gender , String location, String address )
    {
        this.age=age;
        this.gender= gender;
        this.phoneno= phoneno;
        this.username=username;
        this.location=location;
        this.address=address;
    }

    public String getName(){
        return username;
    }
    public void setName(String username) {
        this.username = username;
    }

    public String getgender(){
        return gender;
    }
    public void setgender(String gender) {
        this.gender = gender;
    }

    public String getage(){
        return age;
    }
    public void setage(String age) {
        this.age = age;
    }

    public String getphoneno(){
        return phoneno;
    }
    public void setphoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getlocation(){
        return location;
    }
    public void setlocation(String location) {
        this.location = location;
    }

    public String getaddress(){
        return address;
    }
    public void setaddress(String address) {
        this.address = address;
    }
}
