package com.yewin.employeecrudsystem.repository;

import com.yewin.employeecrudsystem.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author Ye Win.
 * @CreatedDate 05/06/2019.
 * @Description This class is to do database operation.
 */

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
