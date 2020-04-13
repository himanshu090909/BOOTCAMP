package com.ttn.ecommerceApplication.ecommerceApplication.daoImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ttn.ecommerceApplication.ecommerceApplication.dao.CategoryDao;
import com.ttn.ecommerceApplication.ecommerceApplication.entities.Category;
import com.ttn.ecommerceApplication.ecommerceApplication.entities.CategoryMetadataField;
import com.ttn.ecommerceApplication.ecommerceApplication.exceptionHandling.NullException;
import com.ttn.ecommerceApplication.ecommerceApplication.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryDaoImpl implements CategoryDao
{

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    SellerRepository sellerRepository;

    @Autowired
    CategoryMetadataFieldRepository categoryMetadataFieldRepository;

    @Autowired
    CategoryMetadataFieldValuesRepository categoryMetadataFieldValuesRepository;

    @Autowired
    ProductRepository productRepository;


    @Override
    public List<Object[]> getAllCategory()
    {

        List<Object[]> list = categoryRepository.getMainCategory();
        if (list.isEmpty())
        {
            throw new NullException("no categories available");
        }
        return list;
    }

    @Override
    public List<Object[]> getAllSubCategory(String mainCategory) {
       return categoryRepository.getSubCategory(mainCategory);
    }

    @Override
    public void save(Category category) {

    }

    @Override
    public void addNewSubCategory(String parentCategory,Category category)
    {
        Long id = categoryRepository.getIdOfParentCategory(parentCategory);
        int result = categoryRepository.checkIfLeaf(id);
        if (result==0)
        {
            Optional<Category> category1 = categoryRepository.findById(id);
            category.setCategory(category1.get());
            categoryRepository.save(category);
        }
        else
        {
            throw new NullPointerException("parent category you selected is already a leaf node");
        }
    }

    @Override
    public ResponseEntity addMainCategory(String CategoryName)
    {
        Category category = new Category();
        category.setName(CategoryName);
        categoryRepository.save(category);
        return ResponseEntity.ok().body("category added");
    }

    @Override
    public List<Object[]> getSubcategory() {
            List<Object[]> objects = sellerRepository.getSubcategory();
            return objects;
        }






    public List<Object[]> getFilteringDetails(Long category_id)
    {
        List<Long> longList = categoryMetadataFieldValuesRepository.getMetadataId(category_id);
        List<Object[]> list = new ArrayList<>();
        Object[] o = new Object[1];
        o[0] = "metadata Field";
        list.add(o);
        for (Long l : longList)
        {
            List<Object[]> list1 = categoryMetadataFieldRepository.getMetadataField(l);
            list.addAll(list1);
        }
        o[0]="metadata field values";
        list.add(o);
        List<Object[]> list1 = categoryMetadataFieldValuesRepository.getValues(category_id);
        List<Object[]> list2 = productRepository.getBrands(category_id);
        list.addAll(list1);
        list.addAll(list2);








        return list;
    }
    }


