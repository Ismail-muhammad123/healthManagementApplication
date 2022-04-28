package com.example.hospitalmanagement;

import android.widget.TextView;

public class User {
    public User() {
    }

    String idType;

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getIdName() {
        return idName;
    }

    public void setIdName(String idName) {
        this.idName = idName;
    }

    public String getIdPhoneNumber() {
        return idPhoneNumber;
    }

    public void setIdPhoneNumber(String idPhoneNumber) {
        this.idPhoneNumber = idPhoneNumber;
    }

    public String getIdEmail() {
        return idEmail;
    }

    public void setIdEmail(String idEmail) {
        this.idEmail = idEmail;
    }

    public String getIdNumberID() {
        return idNumberID;
    }

    public void setIdNumberID(String idNumberID) {
        this.idNumberID = idNumberID;
    }

    public String getIdDepartment() {
        return idDepartment;
    }

    public void setIdDepartment(String idDepartment) {
        this.idDepartment = idDepartment;
    }

    public String getIdAvailability() {
        return idAvailability;
    }

    public void setIdAvailability(String idAvailability) {
        this.idAvailability = idAvailability;
    }

    public User(String idType, String idName, String idPhoneNumber, String idEmail, String idNumberID, String idDepartment, String idAvailability) {
        this.idType = idType;
        this.idName = idName;
        this.idPhoneNumber = idPhoneNumber;
        this.idEmail = idEmail;
        this.idNumberID = idNumberID;
        this.idDepartment = idDepartment;
        this.idAvailability = idAvailability;
    }

    String idName;
    String idPhoneNumber;
    String idEmail;
    String idNumberID;
    String idDepartment;
    String idAvailability;
}
