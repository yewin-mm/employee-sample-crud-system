package com.yewin.employeecrudsystem.repository;

import com.yewin.employeecrudsystem.model.entity.User;
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
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(nativeQuery = true, value = "select * from users where user_name=:userName and password=:password")
    List<User> findByUserNameAndPassword(@Param("userName") String userName, @Param("password") String password);

    @Query(nativeQuery = true, value = "select * from users where user_name=:empId and is_deleted=:isDelete")
    List<User> findByEmpId(@Param("empId") String empId, @Param("isDelete") boolean del);

    @Modifying // we need modifying anonotation to tell spring JPA to below query is do update operation.
    @Query(nativeQuery = true, value = "update users set role_id=:roleId where user_name=:usrName")
    int changeRole(@Param("roleId") int roleId, @Param("usrName") String name);

    @Modifying // we need modifying anonotation to tell spring JPA to below query is do update operation.
    @Query(nativeQuery = true, value = "update users set password=:pwd where user_name=:userName")
    int changePassword(@Param("pwd") String password, @Param("userName") String userName);

}
