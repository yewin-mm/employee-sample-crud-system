package com.yewin.employeecrudsystem.controller;

import com.yewin.employeecrudsystem.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Ye Win.
 * @CreatedDate 05/06/2019.
 * @Description This class is to implement api endpoint which can manage data from the Outside or to the Outside as response.
 */

@RestController
public class UserController {
    @Autowired
    UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class); // here we used slf4j logger for logging instead of System.out.println(). We can use Log4j and Log4j2 logger too.

    @PostMapping("/logIn")
    public ResponseEntity loginUserNameAndPassword(@RequestParam String userName, @RequestParam String password) {
        logger.info("==================== Start Login method!!! ====================");
        logger.info("Request - UserName: {}, Password: {}", userName, password);
        ResponseEntity responseEntity;
        try {
            responseEntity = userService.logInUserNameAndPassword(userName, password);

        } catch (Exception e) { // service class error like "can't connect to database, it will catch in here too. So, we need to try, catch in every method because, error can be occur anytime.
            e.printStackTrace();
            logger.error(e.getMessage());
            responseEntity = new ResponseEntity("Something went wrong. Please contact to your administrator!!!!", HttpStatus.INTERNAL_SERVER_ERROR);

        }
        logger.info("Response - body: {}, status code: {}", responseEntity.getBody(), responseEntity.getStatusCode());
        logger.info("==================== End Login method!!! ====================");

        return responseEntity;
    }

    @PostMapping("/changePassword")
    public ResponseEntity changePassword(@RequestParam String userName, @RequestParam String oldPassword, @RequestParam String newPassword, @RequestParam String confirmNewPassword) {
        logger.info("==================== Start change password method!!! ====================");
        logger.info("Request - UserName: {}, oldPassword: {}, newPassword: {}", userName, oldPassword, newPassword);
        ResponseEntity responseEntity;
        try {
            responseEntity = userService.changePassword(userName, oldPassword, newPassword, confirmNewPassword);

        } catch (Exception e) { // service class error like "can't connect to database, it will catch in here too. So, we need to try, catch in every method because, error can be occur anytime.
            e.printStackTrace();
            logger.error(e.getMessage());
            responseEntity = new ResponseEntity("Something went wrong. Please contact to your administrator!!!!", HttpStatus.INTERNAL_SERVER_ERROR);

        }
        logger.info("Response - body: {}, status code: {}", responseEntity.getBody(), responseEntity.getStatusCode());
        logger.info("==================== End change password method!!! ====================");

        return responseEntity;
    }


    @PostMapping("/changeRole")
    public ResponseEntity changeRole(@RequestParam int roleId, @RequestParam String userName) {
        logger.info("==================== Start change role method!!! ====================");
        logger.info("Request - roleId: {}, userName: {}", roleId, userName);
        ResponseEntity responseEntity;
        try {

            responseEntity = userService.changeRole(roleId, userName);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            responseEntity = new ResponseEntity("Something went wrong, Please contact to your administrator!!!!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        logger.info("Response - body: {}, status code: {}", responseEntity.getBody(), responseEntity.getStatusCode());
        logger.info("==================== End change role method!!! ====================");

        return responseEntity;

    }
}
