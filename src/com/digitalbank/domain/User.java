package com.digitalbank.domain;
import java.util.UUID;

public class User {
    private UUID id;
    private String name;
    private String email;
    private String address;
    private String password;

    public User(String name, String address, String email, String password) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.password = password;
        this.id = UUID.randomUUID();
    }

    public UUID getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress(){
        return address;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String n){
        this.name = n;
    }

    public void setEmail(String e) {
        this.email = e;
    }

    public void setAddress(String a){
        this.address = a;
    }

    public void setPassword(String p) {
        this.password = p;
    }

}