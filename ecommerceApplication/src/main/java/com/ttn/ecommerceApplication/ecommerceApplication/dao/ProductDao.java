package com.ttn.ecommerceApplication.ecommerceApplication.dao;

import com.ttn.ecommerceApplication.ecommerceApplication.entities.Category;
import com.ttn.ecommerceApplication.ecommerceApplication.entities.Product;

import java.util.List;

public interface ProductDao
{

     Long getid(String productName);

     void save(Product product);

     public void addProduct(Product product,String category);
     public List<Object[]> getProductDetails();
     public void deleteProduct( String productName);
     public void editProduct(Product product, String productName);
     public void setStatus(String ProductName);

    List<Object[]> viewProduct(Long product_id);
}
