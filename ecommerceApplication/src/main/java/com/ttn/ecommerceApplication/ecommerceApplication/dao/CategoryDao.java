package com.ttn.ecommerceApplication.ecommerceApplication.dao;

import com.ttn.ecommerceApplication.ecommerceApplication.entities.Category;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CategoryDao
{
    List<Object[]> getAllCategory();

    List<Object[]> getAllSubCategory(String mainCategory);

    void save(Category category);

    void addNewSubCategory(String parentCategory,Category category);

    public ResponseEntity addMainCategory(String Categoryname);

    public List<Object[]> getSubcategory();


    public List<Object[]> getFilteringDetails(Long category_id);





}
