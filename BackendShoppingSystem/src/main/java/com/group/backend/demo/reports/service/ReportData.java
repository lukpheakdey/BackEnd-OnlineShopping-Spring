
package com.group.backend.demo.reports.service;

import com.group.backend.demo.reports.service.impl.AllOrders;
import com.group.backend.demo.reports.service.impl.AllProduct;
import com.group.backend.demo.reports.service.impl.GSTReport;

import java.util.Date;

import javax.persistence.*;
@Entity(name = "ReportData")
@SqlResultSetMappings({
        @SqlResultSetMapping(
                name="allProduct",
                classes=@ConstructorResult(
                        targetClass=AllProduct.class,
                        columns ={@ColumnResult(name = "category_name", type = String.class),
                                @ColumnResult(name = "product_number", type = Integer.class),
                                @ColumnResult(name = "product_name", type = String.class),
                                @ColumnResult(name = "description", type = String.class),
                                @ColumnResult(name = "price", type = Double.class),
                                @ColumnResult(name = "quantity", type = Integer.class),
                                @ColumnResult(name = "total", type = Double.class),
                                @ColumnResult(name = "summ", type = Double.class)})),

        @SqlResultSetMapping(
                name="allOrders",
                classes=@ConstructorResult(
                        targetClass=AllOrders.class,
                        columns ={@ColumnResult(name = "order_number", type = Integer.class),
                                @ColumnResult(name = "category_name", type = String.class),
                                @ColumnResult(name = "product_number", type = Integer.class),
                                @ColumnResult(name = "product_name", type = String.class),
                                @ColumnResult(name = "description", type = String.class),
                                @ColumnResult(name = "total_price", type = Double.class),
                                @ColumnResult(name = "quantity", type = Integer.class),
                                @ColumnResult(name = "order_date", type = Date.class),
                                @ColumnResult(name = "summ", type = Double.class)})),

        @SqlResultSetMapping(
                name="orderDate",
                classes=@ConstructorResult(
                        targetClass=AllOrders.class,
                        columns ={@ColumnResult(name = "order_number", type = Integer.class),
                                @ColumnResult(name = "category_name", type = String.class),
                                @ColumnResult(name = "product_number", type = Integer.class),
                                @ColumnResult(name = "product_name", type = String.class),
                                @ColumnResult(name = "description", type = String.class),
                                @ColumnResult(name = "total_price", type = Double.class),
                                @ColumnResult(name = "quantity", type = Integer.class),
                                @ColumnResult(name = "order_date", type = Date.class),
                                @ColumnResult(name = "summ", type = Double.class)})),

        @SqlResultSetMapping(
                name="orderCategory",
                classes=@ConstructorResult(
                        targetClass=AllOrders.class,
                        columns ={@ColumnResult(name = "order_number", type = Integer.class),
                                @ColumnResult(name = "category_name", type = String.class),
                                @ColumnResult(name = "product_number", type = Integer.class),
                                @ColumnResult(name = "product_name", type = String.class),
                                @ColumnResult(name = "description", type = String.class),
                                @ColumnResult(name = "total_price", type = Double.class),
                                @ColumnResult(name = "quantity", type = Integer.class),
                                @ColumnResult(name = "order_date", type = Date.class),
                                @ColumnResult(name = "summ", type = Double.class)})),

        @SqlResultSetMapping(
                name="orderPeriode",
                classes=@ConstructorResult(
                        targetClass=AllOrders.class,
                        columns ={@ColumnResult(name = "order_number", type = Integer.class),
                                @ColumnResult(name = "category_name", type = String.class),
                                @ColumnResult(name = "product_number", type = Integer.class),
                                @ColumnResult(name = "product_name", type = String.class),
                                @ColumnResult(name = "description", type = String.class),
                                @ColumnResult(name = "total_price", type = Double.class),
                                @ColumnResult(name = "quantity", type = Integer.class),
                                @ColumnResult(name = "order_date", type = Date.class),
                                @ColumnResult(name = "summ", type = Double.class)})),

        @SqlResultSetMapping(
                name="gstReport",
                classes=@ConstructorResult(
                        targetClass= GSTReport.class,
                        columns ={@ColumnResult(name = "order_number", type = Integer.class),
                                @ColumnResult(name = "vendor_name", type = String.class),
                                @ColumnResult(name = "product_name", type = String.class),
                                @ColumnResult(name = "product_price", type = Double.class),
                                @ColumnResult(name = "product_quantity", type = Integer.class),
                                @ColumnResult(name = "company_service_fee", type = Double.class),
                                @ColumnResult(name = "vendor_amount", type = Double.class),
                                @ColumnResult(name = "tax", type = Double.class),
                                @ColumnResult(name = "total_amount", type = Double.class)})),

////  implemenet by date
        @SqlResultSetMapping(
                name="gstReportByDate",
                classes=@ConstructorResult(
                        targetClass= GSTReport.class,
                        columns ={@ColumnResult(name = "order_number", type = Integer.class),
                                @ColumnResult(name = "vendor_name", type = String.class),
                                @ColumnResult(name = "product_name", type = String.class),
                                @ColumnResult(name = "product_price", type = Double.class),
                                @ColumnResult(name = "product_quantity", type = Integer.class),
                                @ColumnResult(name = "company_service_fee", type = Double.class),
                                @ColumnResult(name = "vendor_amount", type = Double.class),
                                @ColumnResult(name = "tax", type = Double.class),
                                @ColumnResult(name = "total_amount", type = Double.class),
                                @ColumnResult(name = "order_date", type = Date.class)})),

})
@NamedNativeQueries({
        //**********************
        @NamedNativeQuery(
                name="ReportData.reportAllProduct",
                query="select c.category_name as category_name,p.product_number as product_number, p.product_name as product_name," +
                        " p.description as description,p.price as price, d.quantity as quantity,p.price*d.quantity as total" +
                        "  , (Select sum(product.price*product_detail.quantity) From product, product_detail where product.product_id=product_detail.product_id) as summ from product_detail as d join Product as p on d.product_id =p.product_id " +
                        "join category as c on p.category_id=c.category_id",
                resultSetMapping="allProduct"),

        @NamedNativeQuery(
                name="ReportData.reportallOrders",
                query="  select od.order_number as order_number,c.category_name as category_name,p.product_number as product_number, p.product_name as product_name,p.description as description,od.total_price as total_price, oi.quantity as quantity,od.order_date\r\n" +
                        "		, (Select sum(total_price) From order_item, orders where order_item.order_id=orders.id) as summ from product_detail as d join Product as p on d.product_id =p.product_id \r\n" +
                        "	join category as c on p.category_id=c.category_id\r\n" +
                        "    join order_item as oi on oi.product_id=p.product_id\r\n" +
                        "    join Orders as od on od.id=oi.order_id order by od.order_date DESC",
                resultSetMapping="allOrders"),

        @NamedNativeQuery(
                name="ReportData.reportOrderDate",
                query="  select od.order_number as order_number,c.category_name as category_name,p.product_number as product_number, p.product_name as product_name,p.description as description,od.total_price as total_price, oi.quantity as quantity,od.order_date\r\n" +
                        "			, (Select sum(total_price) From order_item join orders on order_item.order_id=orders.id Where order_date=:date) as summ from product_detail as d join Product as p on d.product_id =p.product_id \r\n" +
                        "		join category as c on p.category_id=c.category_id\r\n" +
                        "        join order_item as oi on oi.product_id=p.product_id\r\n" +
                        "        join Orders as od on od.id=oi.order_id And od.order_date=:date order by od.order_date DESC;\r\n" ,

                resultSetMapping="orderDate"),


        @NamedNativeQuery(
                name="ReportData.reportOrderCategory",
                query="  select od.order_number as order_number,c.category_name as category_name,p.product_number as product_number, " +
                        "p.product_name as product_name,p.description as description,od.total_price as total_price," +
                        " oi.quantity as quantity,od.order_date\r\n" +
                        "			, (Select sum(total_price) From order_item join orders on order_item.order_id=orders.id Where category_name=:category) as summ from product_detail as d join Product as p on d.product_id =p.product_id \r\n" +
                        "		join category as c on p.category_id=c.category_id\r\n" +
                        "        join order_item as oi on oi.product_id=p.product_id\r\n" +
                        "        join Orders as od on od.id=oi.order_id And c.category_name=:category order by c.category_name DESC;\r\n" ,

                resultSetMapping="orderCategory"),


        @NamedNativeQuery(
                name="ReportData.reportOrderPeriode",
                query="  select od.order_number as order_number,c.category_name as category_name,p.product_number as product_number, " +
                        "p.product_name as product_name,p.description as description,od.total_price as total_price," +
                        " oi.quantity as quantity,od.order_date\r\n" +
                        "			, (Select sum(total_price) From order_item join orders on order_item.order_id=orders.id" +
                        " Where orders.order_date Between :dateDebut And :dateFin) as summ from product_detail as d " +
                        "join Product as p on d.product_id =p.product_id \r\n" +
                        "		join category as c on p.category_id=c.category_id\r\n" +
                        "        join order_item as oi on oi.product_id=p.product_id\r\n" +
                        "        join Orders as od on od.id=oi.order_id And od.order_date Between :dateDebut And :dateFin order by od.order_date DESC;\r\n" ,

                resultSetMapping="orderPeriode"),


//
//        @NamedNativeQuery(
//                name="ReportData.gstReport",
//                query="        select oi.order_id as order_number,v.full_name as vendor_name , " +
//                        "p.product_name as product_name, p.price AS product_price, oi.quantity as product_quantity," +
//                        "p.price*oi.quantity*0.25 As company_service_fee, " +
//                        "p.price*oi.quantity*0.75 as vendor_amount,p.price*oi.quantity*0.07 as tax, " +
//                        "(p.price*oi.quantity)+(p.price*oi.quantity*0.07) as total_amount\n" +
//                        "        from product as p\n" +
//                        "        join product_detail as d on p.product_id=d.product_id\n" +
//                        "        join order_item oi on oi.product_product_id =p.product_id\n" +
//                        "        join Vendor v on v.id =d.user_id;\n",
//                resultSetMapping="gstReport"),

        @NamedNativeQuery(
                name="ReportData.gstReport",
                query="select oi.order_id as order_number,v.full_name as vendor_name , " +
                        "p.product_name as product_name, p.price AS product_price, oi.quantity as product_quantity," +
                        "p.price*oi.quantity*0.25 As company_service_fee, " +
                        "p.price*oi.quantity*0.75 as vendor_amount,p.price*oi.quantity*0.07 as tax, " +
                        "(p.price*oi.quantity)+(p.price*oi.quantity*0.07) as total_amount\n" +
                        "        from product as p\n" +
                        "        join order_item oi on oi.product_product_id =p.product_id\n" +
                        "        join Vendor v on v.id =p.user_id;\n",
                resultSetMapping="gstReport"),
//////////////

        @NamedNativeQuery(
                name="ReportData.gstReportByDate",
                query="        select oi.order_id as order_number,v.full_name as vendor_name , " +
                        "p.product_name as product_name, p.price AS product_price, oi.quantity as product_quantity," +
                        "p.price*oi.quantity*0.25 As company_service_fee, " +
                        "p.price*oi.quantity*0.75 as vendor_amount,p.price*oi.quantity*0.07 as tax, " +
                        "(p.price*oi.quantity)+(p.price*oi.quantity*0.07) as total_amount\n" +
                        "        from product as p\n" +
                        "        join product_detail as d on p.product_id=d.product_id\n" +
                        "        join order_item oi on oi.product_id =p.product_id\n" +
                        "        join Vendor v on v.id =p.user_id ;\n",
                resultSetMapping = "gstReportByDate")

})
public class ReportData {
    @Id
    private Integer id;

}
