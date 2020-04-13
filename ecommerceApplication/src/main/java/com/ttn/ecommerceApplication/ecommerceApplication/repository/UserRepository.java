package com.ttn.ecommerceApplication.ecommerceApplication.repository;

import com.ttn.ecommerceApplication.ecommerceApplication.entities.User;
import org.graalvm.compiler.lir.LIRInstruction;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface UserRepository extends CrudRepository<User,Long> {

    User findByUsername(String username);

    User findByEmail(String email);


    @Query(value = "select id,username,email from user where id in(select user_id from user_role where role_id in(select id from role where role='ROLE_CUSTOMER'))",nativeQuery = true)
    List<Object[]> findCustomers();

    @Query(value = "select id from user where id in(select user_id from user_role where role_id in(select id from role where role='ROLE_ADMIN'))",nativeQuery = true)
    Long findAdmin();

    @Query(value = "select id,username,email from user where id in(select user_id from user_role where role_id in(select id from role where role='ROLE_SELLER'))",nativeQuery = true)
    List<Object[]> findSellers();

    @Transactional
    @Modifying
    @Query(value = "update User u set u.isDeleted = true, u.isAccountNonExpired = false , u.isEnabled= false where u.id =:id ")
    public void deleteUser(@Param("id") Long  id);





  /*  List<User> findByName(String name);

    List<User> findAll();

    List<User> findByRoles(String role);

    @Query(value = "select username,flat_no,location,state,street_number,vendor_name from vendor join user",nativeQuery = true)
    List<Object[]> a();

    @Query(value = "select name from user where id in (select id from role where role='ROLE_VENDOR')",nativeQuery = true)
    List<Object[]> findAllVendors();

    @Query(value = "select name from user where id in (select id from role where role='ROLE_BUYER')",nativeQuery = true)
    List<Object[]> findAllBuyers();

    @Query(value = "select role_id from user_role where user_id=:user_id",nativeQuery = true)
    List<Object[]>  findRoleIdFromMappingTable(@Param("user_id") int user_id);

    @Modifying
    @Transactional
    @Query(value = "delete from user_role where user_id=:user_id",nativeQuery = true)
    void deleteFromUserRole(@Param("user_id") int user_id);
*/
}