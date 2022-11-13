package com.yewin.employeecrudsystem.repository;

import com.yewin.employeecrudsystem.model.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @Author Ye Win.
 * @CreatedDate 05/06/2019.
 * @Description This class is to do database operation.
 */

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {


    @Query(nativeQuery = true, value = "select COUNT(id )from employees")
    int getEmpId();

    /**
     * This is the JPA method query and format will be start with findBy and follow by entity field name with initial capital Letter.
     * So, we don't need to write sql query. We can do with @Query like below.
     */
    List<Employee> findByIsDeletedOrderById(boolean isDelete); // we can use like -> List<Employee> findByIsDeleted(boolean isDelete); // but here we will return results as order by to arrange output as ascending.

    @Query(nativeQuery = true, value = "select * from employees where id=:id and is_deleted=:isDelete")
        // no need to order by in here because it will return only one result.
    Employee findEmployeeById(@Param("id") Long id, @Param("isDelete") boolean del);

    @Query(nativeQuery = true, value = "select * from employees where emp_id=:empId and is_deleted=:isDelete")
        // no need to order by in here because it will return only one result, if not so, we will throw error in service class.
    List<Employee> findEmployeeByEmpId(@Param("empId") String empId, @Param("isDelete") boolean del);

    // here we will find name by 'like' query to get match all name by single text and add order by to arrange output as ascending.
    @Query(nativeQuery = true, value = "select * from employees where emp_name LIKE :empName and is_deleted=:isDelete order by id")
    List<Employee> getEmployeeByName(@Param("empName") String empName, @Param("isDelete") boolean del);

//    @Query(nativeQuery = true, value = "select * from employees where emp_name=:empName and emp_address=:empAddress")
//    List<Employee> getEmployeeByNameAndAddress(@Param("empName") String empName, @Param("empAddress") String empAddress);

    @Modifying // we need modifying anonotation to tell spring JPA to below query is do update operation.
    @Query(nativeQuery = true, value = "update employees set emp_name=:empName, emp_address=:empAddress, emp_pri_phone=:priPhone, emp_sec_phone=:secPhone, emp_email=:email  where id=:id ")
    int updateEmployee(@Param("id") Long id, @Param("empName") String empName, @Param("empAddress") String empAddress, @Param("priPhone") String priPhone, @Param("secPhone") String secPhone, @Param("email") String email);

}
