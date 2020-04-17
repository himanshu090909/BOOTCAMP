package com.ttn.ecommerceApplication.ecommerceApplication.daoImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ttn.ecommerceApplication.ecommerceApplication.dao.ProductVariationDao;
import com.ttn.ecommerceApplication.ecommerceApplication.dto.ProductVariationDTO;
import com.ttn.ecommerceApplication.ecommerceApplication.entities.CategoryMetadataField;
import com.ttn.ecommerceApplication.ecommerceApplication.entities.Product;
import com.ttn.ecommerceApplication.ecommerceApplication.entities.ProductVariation;
import com.ttn.ecommerceApplication.ecommerceApplication.entities.Seller;
import com.ttn.ecommerceApplication.ecommerceApplication.exceptionHandling.NotFoundException;
import com.ttn.ecommerceApplication.ecommerceApplication.exceptionHandling.NullException;
import com.ttn.ecommerceApplication.ecommerceApplication.repository.*;
import com.ttn.ecommerceApplication.ecommerceApplication.utilities.GetCurrentUser;
import com.ttn.ecommerceApplication.ecommerceApplication.utilities.HashMapConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductVariationDaoImpl implements ProductVariationDao {

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
    public void makeProductVariationNotAvailable(ProductVariation productVariation) {
        if (productVariation.getQuantity_available() == 0) {
            productVariation.setActive(false);
        }
    }

    @Override
    public void updateQuantity(Long productVariationId, int quantity) {
        Optional<ProductVariation> productVariationOptional = productVariationRepository.findById(productVariationId);
        ProductVariation productVariation = productVariationOptional.get();
        int quantity_Available = productVariation.getQuantity_available();
        int quantityAvailable = quantity_Available - quantity;
        if (quantityAvailable < 0) {
            throw new NullPointerException("only " + quantity_Available + " are in stock please select in this range");
        }
        productVariation.setQuantity_available(quantityAvailable);
        productVariationRepository.save(productVariation);
    }

    public void editProductVariation(ProductVariation productVariation, Long productVariationId) throws JsonProcessingException {
        String username = getCurrentUser.getUser();
        Seller seller = sellerRepository.findByUsername(username);
        long id = productVariationRepository.getProductId(productVariationId);
        Optional<Product> productOptional = productRepository.findById(id);
        Optional<ProductVariation> productVariation3= productVariationRepository.findById(productVariationId);
        if(productVariation3.isPresent()&&productOptional.isPresent())
        {
            Product product = productOptional.get();
            if (product.getisActive()== true)
            {
                if ((product.getSeller().getUsername()).equals(seller.getUsername())) {
                    Map<String, String> stringMap = new HashMap<>();
                    Map<String, Object> map = productVariation.getInfoAttributes();
                    Long categoryId = productRepository.getCategoryId(product.getID());
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
                        Optional<ProductVariation> productVariation1 = productVariationRepository.findById(productVariationId);
                        ProductVariation productVariation2 = productVariation1.get();
                        productVariation2.setActive(productVariation.getisActive());
                        productVariation2.setQuantity_available(productVariation.getQuantity_available());
                        productVariation2.setPrice(productVariation.getPrice());
                        String info = objectMapper.writeValueAsString(productVariation.getInfoAttributes());
                        productVariation2.setInfoJson(info);
                        productVariationRepository.save(productVariation2);
                    }

                    else{
                        throw new NotFoundException("Field values are not provided correctly");
                    }

                } else {
                    throw  new  NullException("You don't have this product to sell");
                }
            }
            else {
                throw new NullException("This product is not active");
            }
        }

        else
        {
            throw  new NullException("This product variation ID is wrong");
        }

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


    public void addNewProductVariation(ProductVariation productVariation, Long productId) throws JsonProcessingException {
        Optional<Product> product = productRepository.findById(productId);
        if (product.isPresent()) {
            Product product1 = product.get();
            if (product1.isActive()) {
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

                    int count = 0;

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

                    if (count == map.size()) {
                        String info = objectMapper.writeValueAsString(productVariation.getInfoAttributes());
                        productVariation.setInfoJson(info);
                        productVariation.setActive(true);
                        productVariationRepository.save(productVariation);
                    } else {
                        throw new NotFoundException("Field values are not provided correctly");
                    }

                } else {
                    throw new NullException("you can't add any product variation to this product");
                }


            } else {
                     throw new NullException("product is not active you cannot add new variations");
            }
        }
            else {
            throw new NotFoundException("The product name provided is wrong as this product is not available");

        }
            }



    public ProductVariationDTO getSingleProductVariation(Long productVariationId) throws JsonProcessingException {
        String username = getCurrentUser.getUser();
        Seller seller = sellerRepository.findByUsername(username);
        Optional<ProductVariation> productVariation = productVariationRepository.findById(productVariationId);
        if (productVariation.isPresent())
        {
            Product product = productVariation.get().getProduct();
            if (product.getSeller().getUsername().equals(seller.getUsername())&&product!=null)
            {
                ProductVariationDTO productVariationDTO = new ProductVariationDTO();
                productVariationDTO.setProductname(product.getProductname());
                productVariationDTO.setBrand(product.getBrand());
                productVariationDTO.setCancellable(product.getisCancellable());
                productVariationDTO.setActive(product.getisActive());
                productVariationDTO.setDescription(product.getDescription());
                productVariationDTO.setReturnable(product.getisReturnable());
                Map<String ,Object> map = objectMapper.readValue(productVariation.get().getInfoJson(), HashMap.class);
                List<String > field = new ArrayList<>();
                List<String > values = new ArrayList<>();
                for (Map.Entry m : map.entrySet())
                {
                    field.add(m.getKey().toString());
                    values.add(m.getValue().toString());
                }
                System.out.println(field);
                System.out.println(values);
                productVariationDTO.setFields(field);
                productVariationDTO.setValues(values);
                productVariationDTO.setPrice(productVariation.get().getPrice());
                productVariationDTO.setCurrentActiveStatus(productVariation.get().getisActive());
                productVariationDTO.setQuantityAvailable(productVariation.get().getQuantity_available());
                return productVariationDTO;

            }
            else
            {
                throw  new NotFoundException("You cannot view this product variation or product is not present");
            }
        }
        else {
            throw new NullException("This product variation do not exist");
        }


    }



    public List<ProductVariationDTO> getAllProductVariations(Long productId) throws JsonProcessingException {

        String seller= getCurrentUser.getUser();
        Seller seller1= sellerRepository.findByUsername(seller);
        Optional<Product> productOptional= productRepository.findById(productId);
        List<ProductVariationDTO> list = new ArrayList<>();
        if (productOptional.isPresent())
        { Product product = productOptional.get();
            if ((product.getSeller().getUsername()).equals(seller1.getUsername()))
            {
                Set<ProductVariation> productVariations = product.getProductVariations();
                for (ProductVariation productVariation : productVariations)
                {
                    list.add(getSingleProductVariation(productVariation.getId()));
                }
                return list;
            }
            else {
                throw new NotFoundException("You cannot view the product variation of this product ");
            }
        }

        else {
            throw new NotFoundException("This product ID is wrong");
        }

    }



    }


