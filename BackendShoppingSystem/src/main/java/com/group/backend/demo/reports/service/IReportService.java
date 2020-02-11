package com.group.backend.demo.reports.service;

import com.group.backend.demo.authentication.model.User;
import com.group.backend.demo.reports.service.impl.AllOrders;
import com.group.backend.demo.reports.service.impl.AllProduct;
import com.group.backend.demo.reports.service.impl.GSTReport;

import java.util.Date;
import java.util.List;

public interface IReportService {

    public List<AllProduct> reportAllProduct();
    public List<AllOrders> reportAllOrders();
    public List<AllOrders> reportOrderDate(Date date);
    public List<AllOrders> reportOrderPeriode(Date dateDebut, Date dateFin);
    public List<AllOrders> reportOrderByCategory(String category);
    public List<User> reportAllVendor();
    public List<GSTReport> reportGTS();
    public List<GSTReport>  gstReportByDate(Date dateDebut, Date dateFin);

    public List<User> reportAllRegitration();
    public List<Object> overAllReport();
}
