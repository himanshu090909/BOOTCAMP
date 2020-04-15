package com.ttn.ecommerceApplication.ecommerceApplication.dao;

import com.ttn.ecommerceApplication.ecommerceApplication.dto.ViewCategoriesDTO;
import com.ttn.ecommerceApplication.ecommerceApplication.entities.Category;
import com.ttn.ecommerceApplication.ecommerceApplication.entities.CategoryMetadataFieldValues;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface CategoryDao
{
    List<Object[]> getAllCategory();

    List<Object[]> getAllSubCategory(String mainCategory);

    void save(Category category);

    void addNewSubCategory(Long parentCategory_id,Category category);

    public ResponseEntity addMainCategory(Category category);

    public List<Object[]> viewACategory(Long category_id);


    public List<Object[]> getSubcategory();

    public void updateCategory(Category category, Long categoryId);


    public List<Object[]> getFilteringDetails(Long category_id);

    public void addMetadataValues(CategoryMetadataFieldValues categoryMetadataFieldValues, Long categoryId, Long metadataId);

    public void updateMetadataValues(CategoryMetadataFieldValues categoryMetadataFieldValues, Long categoryId, Long metadataId);

    public List<ViewCategoriesDTO> viewAllCategoriesForSeller();






}
