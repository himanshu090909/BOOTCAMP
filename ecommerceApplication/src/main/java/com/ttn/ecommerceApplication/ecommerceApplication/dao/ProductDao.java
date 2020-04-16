package com.ttn.ecommerceApplication.ecommerceApplication.dao;

import com.ttn.ecommerceApplication.ecommerceApplication.dto.ProductDTO;
import com.ttn.ecommerceApplication.ecommerceApplication.entities.Category;
import com.ttn.ecommerceApplication.ecommerceApplication.entities.Product;

import java.util.List;

public interface ProductDao
{

     Long getid(String productName);

     void save(Product product);

     public List<Object[]> viewSingleProduct(Long productId);

     public List<Object[]> getProductDetails();
     public void deleteProduct( Long productId);
     public void editProduct(ProductDTO product, Long id) throws IllegalAccessException;
     public void addNewProduct(ProductDTO product, Long category);

     public void addProduct(Product product, Long categoryid);

     public List<Object[]> viewSingleProductForAdmin(Long productId);
     public void deactivate(Long productId);

     public void activateProduct(Long productId);


     List<Object[]> viewProduct(Long product_id);
}
