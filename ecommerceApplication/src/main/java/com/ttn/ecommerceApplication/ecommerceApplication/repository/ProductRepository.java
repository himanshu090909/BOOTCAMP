package com.ttn.ecommerceApplication.ecommerceApplication.repository;

import com.ttn.ecommerceApplication.ecommerceApplication.entities.Product;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface ProductRepository extends CrudRepository<Product,Long>
{
    @Query(value = "select brand,description,productname from product where category_id in (select id from category where name=:name)",nativeQuery = true)
    List<Object[]> getProducts(@Param("name") String name );

    @Query(value =  "select productname,description,brand" +
            " from product  where seller_user_id=:seller_user_id",nativeQuery = true)
    List<Object[]> getProductss(@Param(value = "seller_user_id") Long id);

    List<Object[]> findByProductname(String productName);

    @Query(value = "select brand,description,is_active,is_cancellable,productname from product",nativeQuery = true)
    public List<Object[]> getAllProducts();

    @Query("select id from Product where productname =:productname")
    public Long findProduct(@Param(value = "productname") String productname);

    @Transactional
    @Modifying
    @Query(value = "delete from product  where id=:id and productname=:productname",nativeQuery = true)
    public void deleteProduct(@Param(value = "id") Long id, @Param(value = "productname") String productname);

    @Transactional
    @Modifying
    @Query(value = "delete product_variation from product_variation inner join" +
            " product on product.id= product_variation.product_id where product_id =:id  ",nativeQuery = true)
    public void deleteProductVariation(@Param(value = "id") Long id);

    @Query(value = "select id from product where productname=:productname",nativeQuery = true)
    Long getProductVariationid(@Param("productname") String productname);

    @Query(value = "select price from product_variation where id =:id",nativeQuery = true)
    public double getPrice(@Param(value = "id") Long id);

    @Transactional
    @Modifying
    @Query(value = "update Product set isActive= false where productname =:productname")
    public void setActiveStatusOfProduct(@Param(value = "productname") String productname);

    @Transactional
    @Modifying
    @Query(value = "update product_variation set is_active = false where product_id =:product_id",nativeQuery = true)
    public void setActiveStatusOfProductAndProductVariation(@Param(value = "product_id") Long product_id);



}
