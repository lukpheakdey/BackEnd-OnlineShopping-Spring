package com.group.backend.demo.vendor.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Table(name = "category")
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private long categoryId;


    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "description")
    private String description;

    private int status; // 0- inactive,  1- active

    @Column(name = "modified_date")
    private LocalDate modifiedDate;

    public Category( String categoryName, String description, int status, LocalDate modifiedDate) {
        this.categoryName = categoryName;
        this.description = description;
        this.status = status;
        this.modifiedDate = modifiedDate;
    }
}
