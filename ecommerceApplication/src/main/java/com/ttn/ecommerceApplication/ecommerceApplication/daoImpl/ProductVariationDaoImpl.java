package com.ttn.ecommerceApplication.ecommerceApplication.daoImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ttn.ecommerceApplication.ecommerceApplication.dao.ProductVariationDao;
import com.ttn.ecommerceApplication.ecommerceApplication.entities.CategoryMetadataField;
import com.ttn.ecommerceApplication.ecommerceApplication.entities.Product;
import com.ttn.ecommerceApplication.ecommerceApplication.entities.ProductVariation;
import com.ttn.ecommerceApplication.ecommerceApplication.entities.Seller;
import com.ttn.ecommerceApplication.ecommerceApplication.exceptionHandling.NotFoundException;
import com.ttn.ecommerceApplication.ecommerceApplication.exceptionHandling.NullException;
import com.ttn.ecommerceApplication.ecommerceApplication.repository.*;
import com.ttn.ecommerceApplication.ecommerceApplication.utilities.GetCurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductVariationDaoImpl implements ProductVariationDao
{

    @Autowired
    ProductVariationRepository productVariationRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    GetCurrentUser getCurrentUser;

    @Autowired
    SellerRepository sellerRepository;

    @Autowired
    CategoryMetadataFieldValuesRepository categoryMetadataFieldValuesRepository;

    @Autowired
    CategoryMetadataFieldRepository categoryMetadataFieldRepository;

    @Override
    public void makeProductVariationNotAvailable(ProductVariation productVariation)
    {
        if(productVariation.getQuantity_available()==0)
        {
            productVariation.setActive(false);
        }
    }

    @Override
    public void updateQuantity(Long productVariationId, int quantity)
    {
        Optional<ProductVariation> productVariationOptional=  productVariationRepository.findById(productVariationId);
        ProductVariation productVariation =productVariationOptional.get();
        int quantity_Available = productVariation.getQuantity_available();
        int quantityAvailable = quantity_Available - quantity;
        if (quantityAvailable<0)
        {
            throw new NullPointerException("only "+quantity_Available+" are in stock please select in this range");
        }
        productVariation.setQuantity_available(quantityAvailable);
        productVariationRepository.save(productVariation);
    }


    @Override
    public String removeProductVariation(Long productVariationId) {
        String username = getCurrentUser.getUser();
        Seller seller = sellerRepository.findByUsername(username);
        Long id = productVariationRepository.getProductId(productVariationId);
        Optional<Product> productOptional = productRepository.findById(id);
        Product product = productOptional.get();
        if ((product.getSeller().getUsername()).equals(seller.getUsername())) {
            productVariationRepository.deleteProductVariation(productVariationId);
            return "variation has been successfully removed";
        } else {
            System.out.println("8");
            throw new NullException("you can't delete this product");

        }
    }

    @Override
    public void editProductVariation(ProductVariation productVariation, Long productVariationId) {
        String username = getCurrentUser.getUser();
        Seller seller = sellerRepository.findByUsername(username);
        Long id = productVariationRepository.getProductId(productVariationId);
        Optional<Product> productOptional = productRepository.findById(id);
        Product product = productOptional.get();
        if ((product.getSeller().getUsername()).equals(seller.getUsername())) {
            Optional<ProductVariation> productVariation1 = productVariationRepository.findById(productVariationId);
            ProductVariation productVariation2 = productVariation1.get();
            productVariation2.setQuantity_available(productVariation.getQuantity_available());
            productVariation2.setPrice(productVariation.getPrice());
            productVariation2.setActive(productVariation.getisActive());
            productVariationRepository.save(productVariation2);
        } else {
            throw new NullPointerException("You do not have this product to sell");
        }
    }

    public void addNewProductVariation(ProductVariation productVariation, String productName) throws JsonProcessingException {
        Product product1 = productRepository.findById(productRepository.findProduct(productName)).get();
        if (product1.equals(null))
        {
            throw new NotFoundException("The product name provided is wrong as this product is not available");
        }
        else {
            Map<String, String> stringMap = new HashMap<>();
            String seller = getCurrentUser.getUser();
            Seller seller1 = sellerRepository.findByUsername(seller);
            Map<String, Object> map = productVariation.getInfoAttributes();

            if ((product1.getSeller().getUsername()).equals(seller1.getUsername())) {
                productVariation.setProduct(product1);
                Long categoryId = productRepository.getCategoryId(product1.getID());
                List<Long> metadataIds = categoryMetadataFieldValuesRepository.getMetadataId(categoryId);

                for (long l : metadataIds) {

                    String metadata = categoryMetadataFieldRepository.getNameOfMetadata(l);
                    String metadataValues = categoryMetadataFieldValuesRepository.getFieldValuesForCompositeKey(categoryId, l);
                    stringMap.put(metadata, metadataValues);

                }

                int count=0;

                for (String key : map.keySet()) {
                    if (stringMap.containsKey(key)) {
                        String str = stringMap.get(key);
                        Object obj = map.get(key);
                        String[] strings = str.split(",");
                        List<String> list = Arrays.asList(strings);
                        if (list.contains(obj)) {
                            count++;
                        }

                    }
                }

                if (count == map.size())
                {
                    String info = objectMapper.writeValueAsString(productVariation.getInfoAttributes());
                    productVariation.setInfoJson(info);
                    productVariationRepository.save(productVariation);
                }

                else{
                    throw new NotFoundException("Field values are not provided correctly");
                }

            }


            else {
                throw  new NullException("you can't add any product variation to this product");
            }


        }

    }
}
