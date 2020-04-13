package com.ttn.ecommerceApplication.ecommerceApplication.controller;
import com.ttn.ecommerceApplication.ecommerceApplication.dao.CategoryDao;
import com.ttn.ecommerceApplication.ecommerceApplication.dao.ProductDao;
import com.ttn.ecommerceApplication.ecommerceApplication.entities.Product;
import com.ttn.ecommerceApplication.ecommerceApplication.repository.CategoryRepository;
import com.ttn.ecommerceApplication.ecommerceApplication.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductController
{
    @Autowired
    ProductDao productDao;

    @Autowired
    CategoryDao categoryDao;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @PostMapping("/addProduct/{category}")
    public void addNewProduct(@RequestBody Product product, @PathVariable(name = "category") String category) {
        List<Object[]> sub = categoryDao.getSubcategory();
        for (Object[] objects : sub) {
            if (objects[0].toString().equals(category))
            {
                productDao.addProduct(product,category);
                break;
            }
        }
    }

    @GetMapping("/getProducts")
    public List<Object[]> getProductDetails()
    {
        List<Object[]> objectsList = productDao.getProductDetails();
        return objectsList;
    }

    @DeleteMapping("/deleteProduct/{productName}")
    public void deleteProduct(@PathVariable String productName) {
        productDao.deleteProduct(productName);
    }
    @PostMapping("/editProduct/{productName}")
    public void editProduct(@RequestBody Product product, @PathVariable String productName) {
        productDao.editProduct(product,productName);
    }

    @PostMapping("/setActiveStatus/{productName}")
    public String  setStatus(@PathVariable String productName)
    {
       productDao.setStatus(productName);
       return "success";
    }

    @GetMapping("/viewSingleProduct/{id}")
    public List<Object[]> viewSingleProduct(@PathVariable Long id)
    {
        return productRepository.getSingleProduct(id);
    }
}



