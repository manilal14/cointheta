package com.example.mani.cointheta;

import java.io.Serializable;

public class Users implements Serializable{

    int user_id;
    String contact_no;
    String name,address;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getContact_no() {
        return contact_no;
    }

    public void setContact_no(String contact_no) {
        this.contact_no = contact_no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    // This function will be called whenever we try to print user directly
    @Override
    public String toString() {
        return
                "user_id=" + user_id +
                ", contact_no=" + contact_no +
                ", name='" + name + '\'' +
                ", address='" + address + '\'';
    }
}
