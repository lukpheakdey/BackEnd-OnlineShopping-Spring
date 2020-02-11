package com.group.backend.demo.reports.controller;


import com.group.backend.demo.authentication.model.User;
import com.group.backend.demo.authentication.repository.UserRepository;
import com.group.backend.demo.reports.service.impl.AllOrders;
import com.group.backend.demo.reports.service.impl.AllProduct;
import com.group.backend.demo.reports.service.impl.GSTReport;
import com.group.backend.demo.reports.service.impl.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
@RestController
public class ReportController {

    @Autowired
    private ReportService reportServiceImpl ;

    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = "/vendor/product", produces = "application/json")
    public List<AllProduct> allProduct() {

        List<AllProduct> list=reportServiceImpl.reportAllProduct();
        return list;
    }
    @GetMapping(value = "/admin/orderByCategory/{category}", produces = "application/json")
    public List<AllOrders> orderByCategory(@PathVariable("category")  String category) {
        return reportServiceImpl.reportOrderByCategory(category);
    }

    @GetMapping(value = "/admin/orders", produces = "application/json")
    public List<AllOrders> allOrdes() {
        return reportServiceImpl.reportAllOrders();
    }

    @GetMapping(value = "/admin/allvendors", produces = "application/json")
    public List<User> allVendor() {
        return userRepository.findVendor();
    }

    @GetMapping(value = "/admin/allRegitrations", produces = "application/json")
    public List<User> allRegistration() {
        return userRepository.findAll();
    }

    @GetMapping(value = "/admin/orderDate/{date}", produces = "application/json")
    public List<AllOrders> orderByDate(@PathVariable("date") @DateTimeFormat(pattern = "ddMMyyyy") Date date) {
        return reportServiceImpl.reportOrderDate(date);
    }

    @GetMapping(value = "/admin/orderPeriode/{dateDebut}/{dateFin}", produces = "application/json")
    public List<AllOrders> orderByPeriode(@PathVariable("dateDebut") @DateTimeFormat(pattern = "ddMMyyyy") Date dateDebut,
                                          @PathVariable("dateFin") @DateTimeFormat(pattern = "ddMMyyyy") Date dateFin) {
        return reportServiceImpl.reportOrderPeriode(dateDebut, dateFin);
    }

    @GetMapping(value = "/admin_gstReport", produces = "application/json")
    public List<GSTReport> gstReport() {
        return reportServiceImpl.getALLGstReport();
    }



    @GetMapping(value = "/admin/gstReportByDate/{dateDebut}/{dateFin}", produces = "application/json")
    public List<GSTReport> gstReportByDate(@PathVariable("dateDebut") @DateTimeFormat(pattern = "ddMMyyyy") Date dateDebut,
                                           @PathVariable("dateFin") @DateTimeFormat(pattern = "ddMMyyyy") Date dateFin) {
        return reportServiceImpl.gstReportByDate(dateDebut, dateFin);
    }





}
