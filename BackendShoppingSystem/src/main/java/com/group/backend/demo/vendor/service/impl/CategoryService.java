package com.group.backend.demo.vendor.service.impl;


import com.group.backend.demo.vendor.domain.Category;
import com.group.backend.demo.vendor.repository.ICategoryRepository;
import com.group.backend.demo.vendor.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements ICategoryService {
    private ICategoryRepository categoryRepository;

    @Autowired
    public CategoryService(ICategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll(Sort.by("categoryName"));
    }


    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category findById(Long categoryId) {
        return categoryRepository.findById(categoryId).orElse(null);
    }

    @Override
    public void delete(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }

    @Override
    public List<Category> findByCategoryName(String categoryName, Integer status) {
        return categoryRepository.findByCategoryName(categoryName, status);
    }

    @Override
    public List<Category> findByStatus(Integer status) {
        return categoryRepository.findByStatus(status);
    }
}
