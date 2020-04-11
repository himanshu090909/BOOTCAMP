package com.ttn.ecommerceApplication.ecommerceApplication.repository;

import com.ttn.ecommerceApplication.ecommerceApplication.entities.Cart;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CartRepository extends CrudRepository<Cart,Long> {
    Cart findByCustomerId(Long id);

    @Modifying
    @Transactional
    @Query(value = "delete from cart where product_variation_id=:product_variation_id",nativeQuery = true)
    void deleteByProductVariationId(@Param("product_variation_id") Long product_variation_id);

    @Modifying
    @Transactional
    @Query(value = "delete from cart where customer_id=:customer_id",nativeQuery = true)
    void emptyUserCart(@Param("customer_id") Long customer_id);

    @Query(value = "select info_attributes,price,product_id,productname from product_variation join " +
            "product on product_variation.product_id=product.id and product_variation.id in" +
            " (select product_variation_id from cart where customer_id=:customer_id)",nativeQuery = true)
    List<Object[]> viewCart(@Param("customer_id") Long customer_id);
}
