package com.ttn.ecommerceApplication.ecommerceApplication.repository;

import com.ttn.ecommerceApplication.ecommerceApplication.entities.CategoryMetadataField;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryMetadataFieldRepository extends PagingAndSortingRepository<CategoryMetadataField,Long>
{

    @Query(value = "select name from category_metadata_field where id = :id",nativeQuery = true)
    List<Object[]> getMetadataField(@Param("id") Long id);
}
