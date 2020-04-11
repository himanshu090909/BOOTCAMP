package com.ttn.ecommerceApplication.ecommerceApplication.dao;

import com.ttn.ecommerceApplication.ecommerceApplication.entities.Category;

import java.util.List;

public interface CategoryDao
{
    List<Object[]> getAllCategory();

    List<Object[]> getAllSubCategory(String maninCategory);

    void save(Category category);

    void addNewSubCategory(String parentCategory,Category category);

    public List<Object[]> getSubcategory();




}
