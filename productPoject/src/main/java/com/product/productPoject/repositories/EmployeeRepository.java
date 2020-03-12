package com.product.productPoject.repositories;
import com.product.productPoject.Entities.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface EmployeeRepository extends PagingAndSortingRepository<Employee,Integer>
{
             List<Employee> findByName(String name);
             List<Employee> findByNameLike(String pattern);
             List<Employee> findByAgeBetween(int age1,int age2);
}
