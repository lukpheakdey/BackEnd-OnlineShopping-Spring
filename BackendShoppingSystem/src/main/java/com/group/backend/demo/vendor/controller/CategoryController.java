package com.group.backend.demo.vendor.controller;

import com.group.backend.demo.vendor.domain.Category;
import com.group.backend.demo.vendor.domain.Product;
import com.group.backend.demo.vendor.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping(value ="/categories")
    public List<Category> getAllCategories(){
        return (List<Category>) categoryService.findAll();
    }

    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping(value = "/category/{id}")
    public Category getCategoryById(@PathVariable("id") Long categoryId) {
        return categoryService.findById(categoryId);
    }

    @CrossOrigin(origins = "http://localhost:4200/")
    @PostMapping(value = "/update_category", produces = "application/json")
    public Category updateCategory(@RequestBody Category category) {
        categoryService.save(category);
        return category;
    }

    @CrossOrigin(origins = "http://localhost:4200/")
    @PostMapping(value = "/category", produces = "application/json")
    public Category save(@RequestBody Category category) {
        System.out.println(category);
        categoryService.save(category);
        return category;
    }

    @CrossOrigin(origins = "http://localhost:4200/")
    @DeleteMapping(value = "/category/{id}")
    public Category delete(@PathVariable("id") Long categoryId) {
        Category category = categoryService.findById(categoryId);
        categoryService.delete(categoryId);
        return category;
    }

    @GetMapping("/categories/search")
    public List<Category> search(@RequestParam("categoryNumber") String categoryNumber, @RequestParam("categoryName") String categoryName,
                                 @RequestParam("status") Integer status) {
        boolean byCategoryNumber = categoryNumber != null && !categoryNumber.isEmpty();
        boolean byCategoryName = categoryName != null && !categoryName.isEmpty();
        boolean byStatus = status == 1;

        if (byCategoryName) {
            return categoryService.findByCategoryName(categoryName, status);
        } else if(byStatus) {
            return categoryService.findByStatus(status);
        }
        else
            return categoryService.findAll();

    }

}