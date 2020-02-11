package com.group.backend.demo.payment.model;


import com.group.backend.demo.authentication.model.Address;

public class PaymentForm {

    //address fields.
    private String street;
    private String city;
    private String state;
    private String zip;
    private String country;

    //card information.
    private String cardNo;
    private String cardName;
    private int ccv;
    private String expirationDate;
    private String cardType;


        public Address getAddress() {
            Address address = new Address(street, city, state, zip, country);
            return address;
        }


    public PaymentForm() {

    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public int getCcv() {
        return ccv;
    }

    public void setCcv(int ccv) {
        this.ccv = ccv;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    @Override
    public String toString() {
        return "PaymentForm{" +
                "street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zip='" + zip + '\'' +
                ", country='" + country + '\'' +
                ", cardNo='" + cardNo + '\'' +
                ", cardName='" + cardName + '\'' +
                ", ccv='" + ccv + '\'' +
                ", expirationDate='" + expirationDate + '\'' +
                ", cardType='" + cardType + '\'' +
                '}';
    }
}
