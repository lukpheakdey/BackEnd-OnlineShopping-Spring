package com.group.backend.demo.authentication.repository;

import com.group.backend.demo.authentication.model.Address;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
public class Vendor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //user details.
    private String fullName;
    private String phoneNumber;
    private String telephoneNumber;
    private String vendorEmail;
    private String website;
    private long userID;
    private String businessType;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Long getUserID() {
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

    public List<Address> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
    }

    public Vendor(String fullName, String phoneNumber, String telephoneNumber, String vendorEmail, String website, long userID, String businessType) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.telephoneNumber = telephoneNumber;
        this.vendorEmail = vendorEmail;
        this.website = website;
        this.userID = userID;
        this.businessType = businessType;
    }

    @OneToMany(cascade = CascadeType.ALL)
    private List<Address> addressList = new ArrayList<>();

    public void addAddress(Address address){
        this.addressList.add(address);
    }

}
