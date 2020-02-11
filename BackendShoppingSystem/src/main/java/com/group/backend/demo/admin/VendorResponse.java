package com.group.backend.demo.admin;

import com.group.backend.demo.authentication.model.User;
import com.group.backend.demo.authentication.repository.Vendor;

public class VendorResponse {
    private String username;
    private String fullName;
    private String phoneNumber;
    private String email;
    private String status;
    private String role;

    private String telephoneNumber;
    private String vendorEmail;
    private String website;
    private long userID;
    private String businessType;

    public VendorResponse addUser(User user){
        this.username = user.getUsername();
        this.fullName = user.getFullName();
        this.phoneNumber =user.getPhoneNumber();
        this.email = user.getEmail();
        this.status = user.getStatus();
        this.role = user.getRole();
        return this;
    }

    public  VendorResponse addVendor(Vendor vendor){
        this.fullName = vendor.getFullName();
        this.phoneNumber = vendor.getPhoneNumber();
        this.telephoneNumber = vendor.getTelephoneNumber();
        this.vendorEmail = vendor.getVendorEmail();
        this.website =vendor.getWebsite();
        this.userID = vendor.getUserID();
        this.businessType = vendor.getBusinessType();
        return this;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getVendorEmail() {
        return vendorEmail;
    }

    public void setVendorEmail(String vendorEmail) {
        this.vendorEmail = vendorEmail;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }
}

