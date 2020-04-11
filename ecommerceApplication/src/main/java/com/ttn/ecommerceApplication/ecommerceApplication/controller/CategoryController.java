package com.ttn.ecommerceApplication.ecommerceApplication.controller;

import com.ttn.ecommerceApplication.ecommerceApplication.dao.CategoryDao;
import com.ttn.ecommerceApplication.ecommerceApplication.dao.ProductDao;
import com.ttn.ecommerceApplication.ecommerceApplication.entities.Category;
import com.ttn.ecommerceApplication.ecommerceApplication.repository.CategoryRepository;
import com.ttn.ecommerceApplication.ecommerceApplication.repository.ProductRepository;
import com.ttn.ecommerceApplication.ecommerceApplication.repository.ProductVariationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CategoryController {
    @Autowired
    CategoryDao categoryDao;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductDao productDao;

    @Autowired
    ProductVariationRepository productVariationRepository;

    @GetMapping("/getCategory")
    public List<Object[]> getCategories()
    {
        return categoryDao.getAllCategory();
    }

    @GetMapping("/getCategory/{category_name}")
    public List<Object[]> getSubCategory(@PathVariable(name = "category_name") String categoryName) {
        List<Object[]> list = categoryDao.getAllSubCategory(categoryName);
        if (list.isEmpty()) {
            list = productRepository.getProducts(categoryName);
            if (list.isEmpty()) {
                list = productRepository.findByProductname(categoryName);
            }
        }
        return list;
    }

    @PostMapping("/addNewCategory/{parent_category}")
    public String addNewSubCategory(@PathVariable(name = "parent_category") String parent_category, @RequestBody Category category) {
        categoryDao.addNewSubCategory(parent_category, category);
        return "subcategory added successfully";
    }

    @GetMapping("/getSubcategories")
    public List<Object[]> getSubcategories() {
        List<Object[]> objects = categoryDao.getSubcategory();
        return objects;
    }
}
