package com.group.backend.demo.reports.service.impl;

import com.group.backend.demo.authentication.model.User;
import com.group.backend.demo.authentication.repository.Vendor;
import com.group.backend.demo.authentication.repository.VendorRepository;
import com.group.backend.demo.reports.repository.IReportRepository;
import com.group.backend.demo.reports.service.IReportService;
import com.group.backend.demo.shoppingcart.domain.Order;
import com.group.backend.demo.shoppingcart.domain.OrderItem;
import com.group.backend.demo.shoppingcart.repository.OrderRepository;
import com.group.backend.demo.vendor.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class ReportService implements IReportService {
    @Autowired
    private IReportRepository reportRepository;


    @Autowired
    private OrderRepository repository;


    @Autowired
    private VendorRepository vendorRepository;


    public List<AllProduct> reportAllProduct() {

        return reportRepository.reportAllProduct();
    }
    @Override
    public List<AllOrders> reportAllOrders() {

        return reportRepository.reportallOrders();
    }


    @Override
    public List<GSTReport> reportGTS() {

        return reportRepository.gstReport();
    }

    @Override
    public List<GSTReport>  gstReportByDate(Date dateDebut, Date dateFin) {
        return reportRepository. gstReportByDate(dateDebut,dateFin);
    }


    @Override
    public List<AllOrders> reportOrderDate(Date date) {

        return reportRepository.reportOrderDate(date);
    }

    @Override
    public List<AllOrders> reportOrderPeriode(Date dateDebut, Date dateFin){

        return reportRepository.reportOrderPeriode(dateDebut,dateFin);
    }

    @Override
    public List<AllOrders> reportOrderByCategory(String category) {
        return reportRepository.reportOrderCategory(category);
    }

    @Override
    public List<User> reportAllVendor() {
        return null;
    }

    @Override
    public List<User> reportAllRegitration() {
        return null;
    }

    @Override
    public List<Object> overAllReport() {
        return null;
    }



    public List<GSTReport> getALLGstReport(){

        List<GSTReport> gstReportList = new ArrayList<>();

        System.out.println("reached here!");

        List<Order> orders = repository.findAll();   //get all ther orders.


        System.out.println(orders);
        for(Order order : orders){
            List<OrderItem> itemList = order.getOrderItems();
            for(OrderItem item: itemList){
                Product product = item.getProduct();
                //company service fee.
                double company_fee = product.getPrice() * item.getQuantity()   * 0.25;
                //vendor amount.
                double vendor_amount = product.getPrice() * item.getQuantity()   * 0.75;
                //tax

                double tax = product.getPrice() * item.getQuantity()   * 0.07;

                Vendor vendor = vendorRepository.findByUserID(product.getUser().getId());
                System.out.println(vendor);
                GSTReport gstReport = new GSTReport(order.getOrderID(),vendor.getFullName(), product.getProductName()
                , product.getPrice(),item.getQuantity(), company_fee, vendor_amount,tax, order.getTotalPrice(), order.getOrderDate());
                gstReportList.add(gstReport);
            }

        }

        return gstReportList;

    }












}