package com.ttn.ecommerceApplication.ecommerceApplication.controller;

import com.ttn.ecommerceApplication.ecommerceApplication.dao.CategoryDao;
import com.ttn.ecommerceApplication.ecommerceApplication.dao.CategoryMetadataFieldDao;
import com.ttn.ecommerceApplication.ecommerceApplication.dao.ProductDao;
import com.ttn.ecommerceApplication.ecommerceApplication.dto.ViewCategoriesDTO;
import com.ttn.ecommerceApplication.ecommerceApplication.entities.Category;
import com.ttn.ecommerceApplication.ecommerceApplication.entities.CategoryMetadataField;
import com.ttn.ecommerceApplication.ecommerceApplication.entities.CategoryMetadataFieldValues;
import com.ttn.ecommerceApplication.ecommerceApplication.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class CategoryController {
    @Autowired
    CategoryDao categoryDao;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CategoryMetadataFieldRepository categoryMetadataFieldRepository;

    @Autowired
    CategoryMetadataFieldValuesRepository categoryMetadataFieldValuesRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryMetadataFieldDao categoryMetadataFieldDao;

    @Autowired
    ProductDao productDao;

    @Autowired
    ProductVariationRepository productVariationRepository;

































    @GetMapping("/getCategory")
    public List<Object[]> getCategories()
    {
        List<Object[]> list = new ArrayList<>();
        Iterable<Category> categories = categoryRepository.findAll();
        for (Category category : categories)
        {
            if (categoryRepository.checkIfLeaf(category.getId())==0)
            {
                list.add(categoryRepository.getNameOfCategory(category.getId()));
                List<Long> longList = categoryMetadataFieldValuesRepository.getMetadataId(category.getId());
                for (Long l : longList)
                {
                    List<Object[]> list1 = categoryMetadataFieldRepository.getMetadataField(l);
                    list.addAll(list1);
                }
                List<Object[]> list1 = categoryMetadataFieldValuesRepository.getValues(category.getId());
                list.addAll(list1);
            }
            else
            {
                list.add(0,categoryRepository.getNameOfCategory(category.getId()));

            }
        }
        return list;
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

    @PostMapping("/addNewCategory/{parent_category_id}")
    public String addNewSubCategory(@Valid @PathVariable(name = "parent_category_id") Long parent_category_id, @RequestBody Category category) {
        categoryDao.addNewSubCategory(parent_category_id, category);
        return "subcategory added successfully";
    }

    @PostMapping("/addNewCategory")
    public ResponseEntity addMainCategory(@Valid @RequestBody Category category)
    {
        return categoryDao.addMainCategory(category);
    }

    @GetMapping("/getSubcategories")
    public List<Object[]> getSubcategories() {
        List<Object[]> objects = categoryDao.getSubcategory();
        return objects;
    }

    @GetMapping("/filtering")
    public List<Object[]> a()
    {
       return categoryDao.getFilteringDetails(6L);
    }

    @GetMapping("/viewACategory/{id}")
    public List<Object[]> viewCategory(@PathVariable Long id)
    {
        return categoryDao.viewACategory(id);
    }

    @GetMapping("/viewCategoriesForSeller")
    public List<ViewCategoriesDTO> viewCategoriesDTOS()
    {
        return categoryDao.viewAllCategoriesForSeller();
    }






    @PostMapping("/addCategoryMetadataField")
    public String addCategoryMetadataField(@Valid @RequestBody CategoryMetadataField categoryMetadataField) {
        categoryMetadataFieldRepository.save(categoryMetadataField);
        return "CategoryMetadataField is successfully created";
    }

    @GetMapping("/viewCategoryMetadataField")
    public ResponseEntity<List<CategoryMetadataField>> viewCategoryMetadataField(@RequestParam(name = "pageNo", required = true, defaultValue = "0") Integer pageNo,
                                                                                 @RequestParam(name = "pageSize", required = true, defaultValue = "10") Integer pageSize,
                                                                                 @RequestParam(name = "sortBy", defaultValue = "id") String sortBy) {
        List<CategoryMetadataField> list = categoryMetadataFieldDao.viewCategoryMetadataField(pageNo, pageSize, sortBy);
        return new ResponseEntity<List<CategoryMetadataField>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/deleteCategoryMetadataField/{id}")
    public String deleteCategoryMetadataField(@PathVariable(value = "id") Long id) {
        categoryMetadataFieldDao.deleteCategoryMetadataField(id);
        return "CategoryMetadataField is successfully deleted";
    }

    @PutMapping("/updateCategory/{categoryId}")
    public String updateCategory(@Valid @RequestBody Category category, @PathVariable(name = "categoryId") Long categoryId) {
        categoryDao.updateCategory(category, categoryId);
        return "Category successfully updated";
    }


    @PostMapping("/addMetadataValues/{categoryId}/{metadataId}")
    public void addMetadataValues(@Valid @RequestBody CategoryMetadataFieldValues categoryMetadataFieldValues,
                                  @PathVariable(value = "categoryId") Long categoryId,
                                  @PathVariable(value = "metadataId") Long metadataId) {
        categoryDao.addMetadataValues(categoryMetadataFieldValues, categoryId, metadataId);

    }

    @PutMapping("/updateMetadataValues/{categoryId}/{metadataId}")
    public void updateMetadataValues(@Valid @RequestBody CategoryMetadataFieldValues categoryMetadataFieldValues,
                                     @PathVariable(value = "categoryId") Long categoryId,
                                     @PathVariable(value = "metadataId") Long metadataId) {

        categoryDao.updateMetadataValues(categoryMetadataFieldValues, categoryId, metadataId);

    }



}
