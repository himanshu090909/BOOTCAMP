package com.ttn.ecommerceApplication.ecommerceApplication.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ttn.ecommerceApplication.ecommerceApplication.entities.ProductVariation;

public interface ProductVariationDao
{
    public void makeProductVariationNotAvailable(ProductVariation productVariation);
    public void updateQuantity(Long productVariationId, int quantity);
    public void editProductVariation(ProductVariation productVariation, Long productVariationId);
    public String removeProductVariation(Long productVariationId);
    public void addNewProductVariation(ProductVariation productVariation, String productName) throws JsonProcessingException;
}
