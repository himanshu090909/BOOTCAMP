package com.ttn.ecommerceApplication.ecommerceApplication.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ttn.ecommerceApplication.ecommerceApplication.entities.ProductVariation;

import java.util.List;

public interface ProductVariationDao
{
    public void makeProductVariationNotAvailable(ProductVariation productVariation);
    public void updateQuantity(Long productVariationId, int quantity);
    public void editProductVariation(ProductVariation productVariation, Long productVariationId) throws JsonProcessingException;
    public String removeProductVariation(Long productVariationId);
    public void addNewProductVariation(ProductVariation productVariation, Long productId) throws JsonProcessingException;
    public List<Object[]> getSingleProductVariation(Long productVariationId);
    public List<Object[]> getAllProductVariations(Long productId);


}
