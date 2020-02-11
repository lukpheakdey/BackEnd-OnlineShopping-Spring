package com.group.backend.demo.vendor.service;

import com.group.backend.demo.vendor.domain.Category;

import java.util.List;

public interface ICategoryService {

    public List<Category> findAll();

    public Category save(Category category);

    public Category findById(Long categoryId);

    public void delete(Long categoryId);

    public List<Category> findByCategoryName(String categoryName, Integer status);

    public List<Category> findByStatus(Integer status);


}