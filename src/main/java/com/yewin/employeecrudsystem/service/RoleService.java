package com.yewin.employeecrudsystem.service;

import com.yewin.employeecrudsystem.model.entity.Role;
import com.yewin.employeecrudsystem.repository.RoleRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Ye Win.
 * @CreatedDate 05/06/2019.
 * @Description This class is to implement business logic.
 */

@Service
public class RoleService {

    private static final Logger logger = LogManager.getLogger(RoleService.class);

    @Autowired
    RoleRepository roleRepository;

    public List<Role> getAllRole() {
        List<Role> roleList = roleRepository.findAll();
        logger.info("Database response size: {}", roleList.size());
        return roleList;
    }
}
