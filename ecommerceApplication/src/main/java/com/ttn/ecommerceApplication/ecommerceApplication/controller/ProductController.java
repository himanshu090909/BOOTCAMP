package com.ttn.ecommerceApplication.ecommerceApplication.controller;
import com.ttn.ecommerceApplication.ecommerceApplication.dao.CategoryDao;
import com.ttn.ecommerceApplication.ecommerceApplication.dao.ProductDao;
import com.ttn.ecommerceApplication.ecommerceApplication.dto.ProductDTO;
import com.ttn.ecommerceApplication.ecommerceApplication.entities.Product;
import com.ttn.ecommerceApplication.ecommerceApplication.repository.CategoryRepository;
import com.ttn.ecommerceApplication.ecommerceApplication.repository.ProductRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @ApiOperation("This URI is for Seller to add a new product to a category")
    @PostMapping("/addProduct/{category}")
    public void addNewProduct(@Valid @RequestBody ProductDTO product, @PathVariable(name = "category") Long category) {
        productDao.addNewProduct(product,category);

    }

    @GetMapping("/getProducts")
    public List<Object[]> getProductDetails()
    {
        List<Object[]> objectsList = productDao.getProductDetails();
        return objectsList;
    }

    @DeleteMapping("/deleteProduct/{productsId}")
    public void deleteProduct(@PathVariable Long productId)
    {
        System.out.println("1");
        productDao.deleteProduct(productId);
    }

    @DeleteMapping("dp/{id}")
    public void dp(@PathVariable Long id)
    {
        productDao.deleteProduct(id);
    }



    @PutMapping("/editProduct/{id}")
    public void editProduct(@RequestBody ProductDTO product, @PathVariable Long id) throws IllegalAccessException {
        productDao.editProduct(product,id);
    }

    @PostMapping("/setActiveStatus/{productName}")
    public String  setStatus(@PathVariable String productName)
    {
       productDao.setStatus(productName);
       return "success";
    }



    @ApiOperation("This URI is for seller to view a single product he owns ")
    @GetMapping("/viewSingleProduct/{productId}")
    public List<Object[]> viewSingleProduct(@PathVariable Long productId)
    {
        return productDao.viewSingleProduct(productId);
    }
}



