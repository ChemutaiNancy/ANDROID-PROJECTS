package com.chemutai.project;

import android.widget.Button;
import android.widget.EditText;

import java.util.Date;

public class Passenger {
    private String name, email, tfrom, tto;
    private int phoneNo, id;
    private Date tDate;

    public Passenger() {//empty constructor
    }

    public Passenger(String name, String email, int phoneNo, Date tDate, String tfrom, String tto) {
        this.name = name;
        this.email = email;
        this.phoneNo = phoneNo;
        this.tfrom = tfrom;
        this.tto = tto;
        this.phoneNo = phoneNo;
        this.tDate = tDate;
    }

    public Passenger(int passId, String name, String email, int phoneNo, Date tDate, String tfrom, String tto) {
        this.id=passId;
        this.name = name;
        this.email = email;
        this.phoneNo = phoneNo;
        this.tfrom = tfrom;
        this.tto = tto;
        this.phoneNo = phoneNo;
        this.tDate = tDate;
    }

    public int getPassId() {
        return id;
    }

    public void setPassId(int passId) {
        this.id = passId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(int phoneNo) {
        this.phoneNo = phoneNo;
    }

    public Date gettDate() {
        return tDate;
    }

    public void settDate(Date tDate) {
        this.tDate = tDate;
    }

    public String getTfrom() {
        return tfrom;
    }

    public void setTfrom(String tfrom) {
        this.tfrom = tfrom;
    }

    public String getTto() {
        return tto;
    }

    public void setTto(String tto) {
        this.tto = tto;
    }

}
