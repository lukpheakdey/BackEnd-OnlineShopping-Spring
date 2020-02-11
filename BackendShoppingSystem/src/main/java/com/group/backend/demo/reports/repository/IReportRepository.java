package com.group.backend.demo.reports.repository;

import com.group.backend.demo.reports.service.ReportData;
import com.group.backend.demo.reports.service.impl.AllOrders;
import com.group.backend.demo.reports.service.impl.AllProduct;
import com.group.backend.demo.reports.service.impl.GSTReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
@Repository
public interface IReportRepository extends JpaRepository<ReportData, Long> {
    @Query(nativeQuery = true)
    public List<AllProduct> reportAllProduct() ;

    @Query(nativeQuery = true)
    public List<AllOrders> reportOrderDate(@Param("date")Date date) ;@Query(nativeQuery = true)
    public List<AllOrders> reportOrderPeriode(@Param("dateDebut")Date dateDebut, @Param("dateFin")Date dateFin) ;

    @Query(nativeQuery = true)
    public List<GSTReport> gstReport() ;

    @Query(nativeQuery = true)
    public List<GSTReport>  gstReportByDate(@Param("dateDebut")Date dateDebut, @Param("dateFin")Date dateFin) ;


    @Query(nativeQuery = true)
    public List<AllOrders> reportallOrders() ;

    @Query(nativeQuery = true)
    public List<AllOrders> reportOrderCategory(@Param("category")String category) ;

}