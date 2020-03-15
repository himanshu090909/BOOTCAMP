package com.jpa2.jpa2project.repositories;

import com.jpa2.jpa2project.entities.Employee;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

public interface EmployeeRepository extends CrudRepository<Employee,Integer>
{
    @Query("select firstName,lastName from Employee where salary>(select AVG(salary) from Employee)")
    List<Object[]> findPartialData(Pageable pageable);

    @Query("select AVG(salary) from Employee")
    int findaverage();

    @Transactional
    @Query(value = "update Employee e set e.salary=:salary where e.salary<:average")
    @Modifying
    void updatingSalaryOfEmployeesHavingSalaryLessThanAverageSalary(@Param("average") int average,@Param("salary") int salary);

    @Query("select min(salary) from Employee")
    int findminsal();

    @Transactional
    @Query(value = "delete from Employee where salary=:salary")
    @Modifying
    void deletingEmployeesHavingMinimumSalary(@Param("salary") int salary);

    @Query(value = "select emp_id,emp_age,emp_first_name from employee where emp_last_name like '%singh'",nativeQuery = true)
    List<Object[]> findEmployeesWhoseLastNameEndsWithSingh();

    @Modifying
    @Transactional
    @Query(value = "delete from employee where emp_age >:emp_age",nativeQuery = true)
    void deletingSalaryHavingAgeGreaterThanPassedAge(@Param("emp_age") int emp_age);

}
