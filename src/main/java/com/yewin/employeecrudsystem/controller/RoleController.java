package com.yewin.employeecrudsystem.controller;

import com.yewin.employeecrudsystem.model.entity.Role;
import com.yewin.employeecrudsystem.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author Ye Win.
 * @CreatedDate 05/06/2019.
 * @Description This class is to implement api endpoint which can manage data from the Outside or to the Outside as response.
 */

@RestController
public class RoleController {
    @Autowired
    RoleService roleService;

    private static final Logger logger = LoggerFactory.getLogger(RoleController.class); // here we used slf4j logger for logging instead of System.out.println(). We can use Log4j and Log4j2 logger too.

    @GetMapping("/findAllRole")
    public ResponseEntity getAllUserRole() {
        logger.info("==================== Start findAllRole  method!!! ====================");
        ResponseEntity responseEntity;
        try {
            List<Role> roleList = roleService.getAllRole();
            responseEntity = new ResponseEntity(roleList, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            responseEntity = new ResponseEntity("Something went wrong, Please contact to your administrator!!!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        logger.info("Response - body: {}, status code: {}", responseEntity.getBody(), responseEntity.getStatusCode());
        logger.info("==================== End findAllRole  method!!! ====================");
        return responseEntity;

    }

}
