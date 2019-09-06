package com.example.p440;

public class Customer {
    String name;
    String phone;
    int imgId;

    public Customer() {
    }

    public Customer(String name, String phone, int imgId) {
        this.name = name;
        this.phone = phone;
        this.imgId = imgId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getImgid() {
        return imgId;
    }

    public void setImgid(int imgId) {
        this.imgId = imgId;
    }
}
