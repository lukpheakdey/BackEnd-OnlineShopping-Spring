package com.group.backend.demo.reports.service.impl;

import java.time.LocalDate;
import java.util.Date;

public class GSTReport {
    private long order_number;
    private String product_name;
    private Double product_price;
    private Integer product_quantity;
    private String vendor_name;
    private Double company_service_fee;
    private Double vendor_amount;
    private Double tax;
    private Double total_amount;
    private Date order_date;

    public GSTReport(){

    }



    public GSTReport(long order_number, String vendor_name, String product_name, Double product_price, Integer product_quantity, Double company_service_fee, Double vendor_amount, Double tax, Double total_amount, Date order_date) {
        this.order_number = order_number;
        this.product_name = product_name;
        this.product_price = product_price;
        this.product_quantity = product_quantity;
        this.vendor_name = vendor_name;
        this.company_service_fee = company_service_fee;
        this.vendor_amount = vendor_amount;
        this.tax = tax;
        this.total_amount = total_amount;
        this.order_date = order_date;
    }

    public GSTReport(long order_number, String vendor_name, String product_name, Double product_price, Integer product_quantity, Double company_service_fee, Double vendor_amount, Double tax, Double total_amount) {
        this.order_number = order_number;
        this.product_name = product_name;
        this.product_price = product_price;
        this.product_quantity = product_quantity;
        this.vendor_name = vendor_name;
        this.company_service_fee = company_service_fee;
        this.vendor_amount = vendor_amount;
        this.tax = tax;
        this.total_amount = total_amount;

    }

    public long getOrder_number() {
        return order_number;
    }

    public void setOrder_number(long order_number) {
        this.order_number = order_number;
    }

    public void setOrder_number(Integer order_number) {
        this.order_number = order_number;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public Double getProduct_price() {
        return product_price;
    }

    public void setProduct_price(Double product_price) {
        this.product_price = product_price;
    }

    public Integer getProduct_quantity() {
        return product_quantity;
    }

    public void setProduct_quantity(Integer product_quantity) {
        this.product_quantity = product_quantity;
    }

    public String getVendor_name() {
        return vendor_name;
    }

    public void setVendor_name(String vendor_name) {
        this.vendor_name = vendor_name;
    }

    public Double getCompany_service_fee() {
        return company_service_fee;
    }

    public void setCompany_service_fee(Double company_service_fee) {
        this.company_service_fee = company_service_fee;
    }

    public Double getVendor_amount() {
        return vendor_amount;
    }

    public void setVendor_amount(Double vendor_amount) {
        this.vendor_amount = vendor_amount;
    }

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    public Double getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(Double total_amount) {
        this.total_amount = total_amount;
    }

    public Date getOrder_date() {
        return order_date;
    }

    public void setOrder_date(Date order_date) {
        this.order_date = order_date;
    }

    @Override
    public String toString() {
        return "GSTReport{" +
                "order_number=" + order_number +
                ", product_name='" + product_name + '\'' +
                ", product_price=" + product_price +
                ", product_quantity=" + product_quantity +
                ", vendor_name='" + vendor_name + '\'' +
                ", company_service_fee=" + company_service_fee +
                ", vendor_amount=" + vendor_amount +
                ", tax=" + tax +
                ", total_amount=" + total_amount +
                ", order_date=" + order_date +
                '}';
    }
}
