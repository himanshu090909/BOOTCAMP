package com.ttn.ecommerceApplication.ecommerceApplication.repository;

import com.ttn.ecommerceApplication.ecommerceApplication.entities.Category;
import org.graalvm.compiler.lir.alloc.lsra.LinearScan;
import org.hibernate.annotations.Parent;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category,Long>
{
    @Query(value = "select name from category where parent_id is null",nativeQuery = true)
    List<Object[]> getMainCategory();

    @Query(value = "select name from category where parent_id in(select id from category where name=:name)",nativeQuery = true)
    List<Object[]> getSubCategory(@Param("name") String name);

    @Query(value = "select id from category where name=:name",nativeQuery = true)
    Long getIdOfParentCategory(@Param("name") String name);

    @Modifying
    @Transactional
    @Query(value = "insert into category(name,parent_id) values(?1,?2)",nativeQuery = true)
    void insertNewSubCategory(String name,Long id);

    @Query(value = "select exists(select * from product where category_id=:category_id)",nativeQuery = true)
    int checkIfLeaf(@Param("category_id") Long category_id);
}
