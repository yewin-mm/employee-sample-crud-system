package com.yewin.employeecrudsystem.service;

import com.yewin.employeecrudsystem.model.entity.Role;
import com.yewin.employeecrudsystem.model.entity.User;
import com.yewin.employeecrudsystem.model.response.UserResponse;
import com.yewin.employeecrudsystem.repository.RoleRepository;
import com.yewin.employeecrudsystem.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @Author Ye Win.
 * @CreatedDate 05/06/2019.
 * @Description This class is to implement business logic.
 */

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserService.class); // here we used slf4j logger for logging instead of System.out.println(). We can use Log4j and Log4j2 logger too.

    public ResponseEntity logInUserNameAndPassword(String userName, String password) {

        if (userName == null || userName.trim().equals("")) {
            return new ResponseEntity("Input User Name is null or empty, Please type valid User Name", HttpStatus.BAD_REQUEST);
        }

        if (password == null || password.trim().equals("")) {
            return new ResponseEntity("Input password is null or empty, Please type valid password", HttpStatus.BAD_REQUEST);
        }

        List<User> userList = userRepository.findByUserNameAndPassword(userName, password);
        logger.info("Database response size: {}", userList.size());

        /**
         * we can create these two below duplicate checking list 'if conditions' as one static method in new ValidationUtil class.
         * And we can call that method from there.
         * eg. public static <T> boolean isValidList(List<T> list) {
         *         return list.size() != 1 ? false : true;
         *     }
         *
         *     boolean b = ValidationUtil.isValidList(userList);
         */

        // check db response is empty and if empty, we assume there is no matching username and password. So, we return error message.
        if (userList.isEmpty()) {
            return new ResponseEntity("Username or Password is wrong", HttpStatus.NOT_FOUND);
        }

        // there shouldn't be 2 user and which both name and password are same in database. So, we will return error message.
        if (userList.size() > 1) {
            return new ResponseEntity("Something went wrong. Please contact to your administrator!!!", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // if above "if conditions" were not meet, program will do this line and here, we can assume list include only one data.
        User user = userList.get(0); // get 1st data from list (now, list include only one data, if not so, above if condition will be worked)
        long roleId = user.getRoleId();

        // to get role name, we need to retrieve from role table as per role Id.
        Optional<Role> optionalRole = roleRepository.findById(roleId);
        if (!optionalRole.isPresent()) { // check !(not) optional is existed or not.
            logger.warn("Role is not present, Please insert some role. Please read `instruction` section in ReadMe file.");
            return new ResponseEntity("Something went wrong. Please contact to your administrator!!!", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        Role role = optionalRole.get(); // get role value from optional.

        // We can response user model as response, but here, we want to add role name too in response.
        // So, we need to create new Model POJO class call EmployeeResponse.
        // That class can have all require field which related with User and Role Table.
        // With that way, we can response both User and Role table data in one Object.
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setUserName(user.getUserName());
        response.setPassword(user.getPassword());
        response.setCreatedDate(user.getCreatedDate());
        response.setUpdatedDate(user.getUpdatedDate());
        response.setRoleId(user.getRoleId());

        response.setRoleName(role.getRoleName()); // which is retrieve from role table.

        return new ResponseEntity(response, HttpStatus.OK);
    }

    public ResponseEntity changePassword(String userName, String oldPassword, String newPassword, String confirmNewPassword) {

        // check user name is null or empty.
        if (userName == null || userName.trim().equals("")) {
            return new ResponseEntity("Input User Name is null or empty, Please type valid User Name.", HttpStatus.BAD_REQUEST);
        }

        // check old password is null or empty
        if (oldPassword == null || oldPassword.trim().equals("")) {
            return new ResponseEntity("Old Password is null or empty", HttpStatus.BAD_REQUEST);
        }

        // check old password is equal with new password or not
        if (oldPassword.equals(newPassword)) {
            return new ResponseEntity("Old Password can't be same with New Password", HttpStatus.BAD_REQUEST);
        }

        // check new password is null or empty
        if (newPassword == null || newPassword.trim().equals("")) {
            return new ResponseEntity("New Password is null or empty", HttpStatus.BAD_REQUEST);
        }

        // check for new password is not equal with confirm password case
        if (!newPassword.equals(confirmNewPassword)) {
            return new ResponseEntity("Confirm Password is not same with New Password", HttpStatus.BAD_REQUEST);
        }


        List<User> userList = userRepository.findByEmpId(userName, false);

        /**
         * we can create these two below duplicate checking list 'if conditions' as one static method in new ValidationUtil class.
         * And we can call that method from there.
         * eg. public static <T> boolean isValidList(List<T> list) {
         *         return list.size() != 1 ? false : true;
         *     }
         *
         *  boolean b = ValidationUtil.isValidList(userList);
         */

        // check for response list is empty or not.
        if (userList.isEmpty()) {
            String errMsg = "Couldn't find User with by input UserName: " + userName;
            return new ResponseEntity(errMsg, HttpStatus.BAD_REQUEST);
        }
        // check for employee id is duplicate. It shouldn't be duplicate and so, we will return error.
        if (userList.size() > 1) {
            return new ResponseEntity("Something went wrong. Please contact to your administrator!!!!", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // now user List has only one value and we will get it by get(0)
        User user = userList.get(0);

        String existingPassword = user.getPassword(); // pull existing password from database.

        /**
         * here if you insert password with Encryption in insert api (not insert as plain text in that api),
         * you need to decrypt back the database existingPassword before checking with input old password.
         * Because user will type plain text as input.
         */
        if (!oldPassword.equals(existingPassword)) {
            return new ResponseEntity("Input old password is wrong!!!", HttpStatus.BAD_REQUEST);
        }

        // here if you use encrypt while inserting into db in insert api, you need to use that encrypt method before adding password into user object.
        user.setPassword(newPassword); // update password field only into user object which object will save into db.
        user.setUpdatedDate(new Date()); // update updatedDate
        userRepository.save(user);
        logger.info("Successfully updated new password: {}", newPassword);

        /**
         * Here we can call below method instead of above save method.
         */
//        int a = userRepository.changePassword(user.getPassword(), userName);

        return new ResponseEntity("Successfully updated new password. Please try to check by calling login again!!!", HttpStatus.OK);
    }


    public ResponseEntity changeRole(int roleId, String userName) {

        // check user name is null or empty.
        if (userName == null || userName.trim().equals("")) {
            return new ResponseEntity("Input User Name is null or empty, Please type valid User Name.", HttpStatus.BAD_REQUEST);
        }

        List<User> userList = userRepository.findByEmpId(userName, false);

        /**
         * we can create these two below duplicate checking list 'if conditions' as one static method in new ValidationUtil class.
         * And we can call that method from there.
         * eg. public static <T> boolean isValidList(List<T> list) {
         *         return list.size() != 1 ? false : true;
         *     }
         *
         *  boolean b = ValidationUtil.isValidList(userList);
         */

        // check for response list is empty or not.
        if (userList.isEmpty()) {
            String errMsg = "Couldn't find User with by input User Name: " + userName;
            return new ResponseEntity(errMsg, HttpStatus.BAD_REQUEST);
        }
        // check for employee id is duplicate. It shouldn't be duplicate and so, we will return error.
        if (userList.size() > 1) {
            return new ResponseEntity("Something went wrong. Please contact to your administrator!!!!", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // now user List has only one value and we will get it by get(0)
        User user = userList.get(0);

        if (roleId == user.getRoleId()) {
            return new ResponseEntity("Input role Id is same with existing database role Id.", HttpStatus.BAD_REQUEST);
        }


        user.setRoleId(roleId); // update role id into user object which object will save into db.
        user.setUpdatedDate(new Date()); // update updatedDate
        user = userRepository.save(user);
        logger.info("Successfully updated user role: {}", user);

        /**
         * Here we can call below method instead of above save method.
         */
//        int a = userRepository.changeRole(user.getRoleId(), empId);
        return new ResponseEntity("Successfully updated user role. Please try to check by calling login again", HttpStatus.OK);
    }


}
