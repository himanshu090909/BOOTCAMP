package com.ttn.ecommerceApplication.ecommerceApplication.repository;

import com.ttn.ecommerceApplication.ecommerceApplication.entities.CategoryMetadataFieldValues;
import com.ttn.ecommerceApplication.ecommerceApplication.entities.CategoryMetadataFieldValuesId;
import org.graalvm.compiler.lir.alloc.lsra.LinearScan;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryMetadataFieldValuesRepository extends PagingAndSortingRepository<CategoryMetadataFieldValues, CategoryMetadataFieldValuesId>
{
    @Query(value = "select category_metadata_id from category_metadata_field_values where category_id=:category_id",nativeQuery = true)
    List<Long> getMetadataId(@Param("category_id") Long category_id);

    @Query(value = "select field_values from category_metadata_field_values where category_id=:category_id",nativeQuery = true)
    List<Object[]> getValues(@Param("category_id") Long category_id);

}
