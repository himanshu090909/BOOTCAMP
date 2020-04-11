package com.ttn.ecommerceApplication.ecommerceApplication.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ttn.ecommerceApplication.ecommerceApplication.dao.ProductDao;
import com.ttn.ecommerceApplication.ecommerceApplication.dao.ProductVariationDao;
import com.ttn.ecommerceApplication.ecommerceApplication.entities.ProductVariation;
import com.ttn.ecommerceApplication.ecommerceApplication.repository.ProductRepository;
import com.ttn.ecommerceApplication.ecommerceApplication.repository.ProductVariationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ProjectVariationController
{
    @Autowired
    ProductVariationRepository productVariationRepository;

    @Autowired
    ProductDao productDao;

    @Autowired
    ProductVariationDao productVariationDao;

    @Autowired
    ProductRepository productRepository;;


    @GetMapping("/getProductVariation/{u}")
    public List<Object[]> getProductVariation(@PathVariable(name = "u") String u)
    {
        Long id = productDao.getid(u);
        return productVariationRepository.getProductVariation(id);
    }

    @GetMapping("/getAllProductVariations/{productName}")
    public List<Object[]> getAllProductVariations(@PathVariable String productName) {
        Long id = productRepository.findProduct(productName);
        List<Object[]> productVariations = productVariationRepository.getProductVariations(id);
        return productVariations;

    }

    @PostMapping("/addProductVariations/{productName}")
    public void addNewProductVariation(@Valid @RequestBody ProductVariation productVariation, @PathVariable String productName) throws JsonProcessingException {

        productVariationDao.addNewProductVariation(productVariation, productName);

    }

    @PostMapping("/editProductVariations/{productVariationId}")
    public void updateProductVariation(@RequestBody ProductVariation productVariation, @PathVariable Long productVariationId) {

        productVariationDao.editProductVariation(productVariation,productVariationId);
    }

    @DeleteMapping("/deleteProductVariation/{productVariationId}")
    public String  deleteProductVariation(@PathVariable Long productVariationId) {
        return productVariationDao.removeProductVariation(productVariationId);
    }

}
