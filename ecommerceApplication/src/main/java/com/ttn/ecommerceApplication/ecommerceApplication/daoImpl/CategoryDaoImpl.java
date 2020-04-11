package com.ttn.ecommerceApplication.ecommerceApplication.daoImpl;
import com.ttn.ecommerceApplication.ecommerceApplication.dao.CategoryDao;
import com.ttn.ecommerceApplication.ecommerceApplication.entities.Category;
import com.ttn.ecommerceApplication.ecommerceApplication.exceptionHandling.NullException;
import com.ttn.ecommerceApplication.ecommerceApplication.repository.CategoryRepository;
import com.ttn.ecommerceApplication.ecommerceApplication.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryDaoImpl implements CategoryDao
{

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    SellerRepository sellerRepository;

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
       Optional<Category> category1=categoryRepository.findById(id);
       category.setCategory(category1.get());
       categoryRepository.save(category);
    }

    @Override
    public List<Object[]> getSubcategory() {
            List<Object[]> objects = sellerRepository.getSubcategory();
            return objects;
        }
    }


