package com.yewin.employeecrudsystem.service;

import com.yewin.employeecrudsystem.model.entity.Employee;
import com.yewin.employeecrudsystem.model.entity.User;
import com.yewin.employeecrudsystem.model.request.EmployeeRequest;
import com.yewin.employeecrudsystem.repository.EmployeeRepository;
import com.yewin.employeecrudsystem.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @Author Ye Win.
 * @CreatedDate 05/06/2019.
 * @Description This class is to implement business logic.
 */

@Service
public class EmployeeService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    EmployeeRepository employeeRepository;


    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class); // here we used slf4j logger for logging instead of System.out.println(). We can use Log4j and Log4j2 logger too.

    @Transactional // transactional for roll back. eg. we saved into employee table and when we save into user table, at that time, when error was thrown, the employee save data will be roll back.
    public ResponseEntity insertEmployee(String empName, String empAddress, String priPhone, String secPhone, String empEmail) {

        /**
         * validation for input fields, except secondary phone, cause it can be blank and it will be optional field in UI.
         * we should do validation by creating new method eg. employeeValidation(String empName, empAddress, ...){return Fail, Error} like that;
         */

        if(empName == null || empName.trim().equals("")){
            return new ResponseEntity("Employee Name is Empty", HttpStatus.BAD_REQUEST);
        }

        if(empAddress == null || empAddress.trim().equals("")){
            return new ResponseEntity("Employee Address is Empty", HttpStatus.BAD_REQUEST);
        }

        if(priPhone == null || priPhone.trim().equals("")){
            return new ResponseEntity("Primary Phone is Empty", HttpStatus.BAD_REQUEST);
        }

        if(empEmail == null || empEmail.trim().equals("")){
            return new ResponseEntity("Employee Email is Empty", HttpStatus.BAD_REQUEST);
        }

        /**
         * We can do extra validation in here eg. validation for priPhone length and prefix number, etc.
         * and we can do validation for email format with regular expression too. (please reference in google for regular expression format for email).
         * Currently, I will skip that part.
         */

        Employee employee = new Employee();
        employee.setEmpName(empName);
        employee.setEmpAddress(empAddress);
        employee.setEmpPrimaryPhone(priPhone);
        employee.setEmpSecondaryPhone(secPhone);
        employee.setEmpEmail(empEmail);
        // here we use isDeleted to determine if this row is delete or not. Because we don't want to delete row permanently. Default is false and it's mean it's not delete.
        employee.setDeleted(false);
        employee.setCreatedDate(new Date());
        employee.setUpdatedDate(new Date());


        // we will count existing data in employee and to generate unique empId by increasing 1 to existing id.
        int count = employeeRepository.getEmpId();

        // generate emp id and it will be unique because it's increment by total row of table by retrieving count in above.
        int i = count + 10001;
        String empId = "EMP_" + i;
        employee.setEmpId(empId);

        logger.info("Before saving into employee: {}",employee);
        employeeRepository.save(employee);

        logger.info("Successfully save into employee table: {}", employee);

        // This is for user and employee will become in system too. We will control by empId.
        User user = new User();
        user.setUserName(empId);


        /**
         * we can save into db as not plain text password for security purpose,
         * we can encrypt the password with MD5, SHA256 (fast, but not recommend) or SimplePBKDF2Hasher (PBKDF2) (recommended) algorithm,
         * but here, I just add as plain text because this is simple project.
          */
        user.setPassword("employee"); // default password and it can be change by calling changePassword API later.
        user.setRoleId(2); // here I set as default role and it's "Normal User" role. It can be change (we can change to admin role) by calling changeRole API later.
        user.setDeleted(false);
        user.setCreatedDate(new Date());
        user.setUpdatedDate(new Date());

        logger.info("Before saving into user table: {} ",user);
        userRepository.save(user);

        logger.info("Successfully save into user table: {}", user);

        return new ResponseEntity(employee, HttpStatus.OK);
    }



    public List<Employee> getAllEmployee() {
        // here we will return employee who is not deleted.
        List<Employee> employeeList = employeeRepository.findByIsDeletedOrderById(false);
        logger.info("Database response size: {}",employeeList.size());
        return employeeList;
    }


    public ResponseEntity findEmployeeById(Long id) {

        if(id == null){
            return new ResponseEntity("Input id is null, Please type valid id", HttpStatus.BAD_REQUEST);
        }

        // here, we will find by id and is delete is false. It's mean we will show not deleted employee only.
        // findbyEmployee id should return single object (not List object), cause it will unique and not duplicate as it's primary key.
        Employee employee = employeeRepository.findEmployeeById(id, false);

        // check for response is empty or not.
        if(employee == null ){
            String errMsg = "Couldn't find employee by input Id: " +id;
            return new ResponseEntity(errMsg, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(employee, HttpStatus.OK);
    }

    public ResponseEntity findEmployeeByEmpId(String empId) {

        /**
         * we can create below string checking 'if condition' as one static method in new ValidationUtil class. And we can call that method from there.
         * Because it's need in other method too (it's duplicate). So, it's better combine as one static method for validation String check.
         * eg. public static boolean isValidString(String input){
         *  if(input == null || input.trim().equals("")
         *  {return false;}
         *  return true;
         * }
         *
         * boolean b = ValidationUtil.isValidString(empId);
         */

        if(empId == null || empId.trim().equals("")){
            return new ResponseEntity("Input employee Id is null or empty, Please type valid employee id", HttpStatus.BAD_REQUEST);
        }

        // here, we will find by emp_id and is delete is false. It's mean we will show not deleted employee only.
        // even emp id is unique as generate, we need to define return type as list because it's not primary key and it cant be duplicate.
        List<Employee> employeeList = employeeRepository.findEmployeeByEmpId(empId, false);

        logger.info("Database response size: {}",employeeList.size());

        // check for response list is empty or not.
        if(employeeList.isEmpty()){
            String errMsg = "Couldn't find employee by input Employee Id: " +empId;
            return new ResponseEntity(errMsg, HttpStatus.BAD_REQUEST);
        }
        // check for employee id is duplicate. It shouldn't be duplicate and so, we will return error.
        if(employeeList.size() > 1){
            return new ResponseEntity("Something went wrong. Please contact to your administrator!!!!", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity(employeeList, HttpStatus.OK);
    }


    public ResponseEntity getEmployeeByName(String empName) {

        /**
         * we can create below string checking 'if condition' as one static method in new ValidationUtil class. And we can call that method from there.
         * Because it's need in other method too (it's duplicate). So, it's better combine as one static method for validation String check.
         * eg. public static boolean isValidString(String input){
         *  if(input == null || input.trim().equals("")
         *  {return false;}
         *  return true;
         * }
         *
         * boolean b = ValidationUtil.isValidString(empName);
         */

        if(empName == null || empName.trim().equals("")){
            return new ResponseEntity("Input employee Name is null or empty, Please type valid name", HttpStatus.BAD_REQUEST);
        }

        String name = empName + "%"; // prepare for like query. We will find with Like query to get match name.
        List<Employee> employeeList = employeeRepository.getEmployeeByName(name, false); // we will find only employee is not deleted.

        logger.info("Database response size: {}",employeeList.size());

        if (employeeList.isEmpty()) {
            String errMsg = "Couldn't find employee by input employee Name: "+empName;
            return new ResponseEntity(errMsg, HttpStatus.OK);
        }

        return new ResponseEntity(employeeList, HttpStatus.NOT_FOUND);
    }


    /**
     * Here we can add other logic like get employee by email and get employee by phoneNo, etc...
     */



    public ResponseEntity updateEmployee(EmployeeRequest requestEmployee) {

        if (requestEmployee != null) {

            // check for request employee id null or empty. and this field is important to get existing value from db as per empId
            if (requestEmployee.getEmpId() == null || requestEmployee.getEmpId().trim().equals("")) {
                return new ResponseEntity("input employee id is null or empty", HttpStatus.NOT_FOUND);
            }

            // find by request emp id which to validate and to compare with database value and request employee value.
            List<Employee> dbResponseEmployeeList = employeeRepository.findEmployeeByEmpId(requestEmployee.getEmpId(), false);

            if (!dbResponseEmployeeList.isEmpty()) {

                // check for employee id is duplicate. It shouldn't be duplicate and so, we will return error.
                if(dbResponseEmployeeList.size() > 1){
                    return new ResponseEntity("Something went wrong. Please contact to your administrator!!!!", HttpStatus.INTERNAL_SERVER_ERROR);
                }

                // now, list can only have one value and we can get by get(0).
                Employee dbResponseEmployee = dbResponseEmployeeList.get(0);

                int count = 0; // for counting at least one record is change or not. here, we can do with boolean datatype too.

                /**
                 * We will do validate for input request employee name, address etc is null or empty.
                 * if it's null or empty, we will not update for that field (eg. name) and so we will check with ! (not) operator and if not equal null, or etc. we will do process.
                 * we will also do validate for input values is equal with database response.
                 * if same with database response value, we don't need to update that.
                 * So, we will do with ! (not) operator when checking with db value. If not same(equal) or not empty, we will do update. if same, we will not update for that field (eg. name).
                 */

                if (requestEmployee.getEmpName() != null && !requestEmployee.getEmpName().trim().equals("") &&
                        !dbResponseEmployee.getEmpName().equals(requestEmployee.getEmpName())) {

                    dbResponseEmployee.setEmpName(requestEmployee.getEmpName()); // override and set input emp name value to database response object if input name is not null or not same with db response name.
                    logger.info("Update name: {}",dbResponseEmployee.getEmpName());

                    count = 1; // note that changes was occur.
                }

                if (requestEmployee.getEmpAddress() != null && !requestEmployee.getEmpAddress().trim().equals("") &&
                        !dbResponseEmployee.getEmpAddress().equals(requestEmployee.getEmpAddress())) {

                    // override and set input address value to database response object if input address is not null or not same with db response address.
                    dbResponseEmployee.setEmpAddress(requestEmployee.getEmpAddress());
                    logger.info("Update address: {}",dbResponseEmployee.getEmpAddress());
                    count = 1; // note that changes was occur.
                }

                if (requestEmployee.getPriPhone() != null && !requestEmployee.getPriPhone().trim().equals("") &&
                        !dbResponseEmployee.getEmpPrimaryPhone().equals(requestEmployee.getPriPhone())) {

                    dbResponseEmployee.setEmpPrimaryPhone(requestEmployee.getPriPhone());
                    logger.info("Update primary phone: {}",dbResponseEmployee.getEmpPrimaryPhone());
                    count = 1;
                }

                if (requestEmployee.getSecPhone() != null && !requestEmployee.getSecPhone().trim().equals("") &&
                        !dbResponseEmployee.getEmpSecondaryPhone().equals(requestEmployee.getSecPhone())) {

                    dbResponseEmployee.setEmpSecondaryPhone(requestEmployee.getSecPhone());
                    logger.info("Update secondary phone: {}",dbResponseEmployee.getEmpSecondaryPhone());
                    count = 1;
                }

                if (requestEmployee.getEmail() != null && !requestEmployee.getEmail().trim().equals("") &&
                        !dbResponseEmployee.getEmpEmail().equals(requestEmployee.getEmail())) {

                    dbResponseEmployee.setEmpEmail(requestEmployee.getEmail());
                    logger.info("Update email: {}",dbResponseEmployee.getEmpEmail());
                    count = 1;
                }

                // validate at least one data was changed or not,
                // if count = 0, we can assume that there is no data was change and so, no need to be update (save).
                // because count value will be 1 if only one field was changed as per above if conditions.
                if(count == 1) {
                    dbResponseEmployee.setUpdatedDate(new Date()); // update updatedDate
                    // update dbResponseEmployee object and update field value will be in that object and rest value is same with existing.
                    dbResponseEmployee = employeeRepository.save(dbResponseEmployee);

                    logger.info("Successfully updated into db: {}", dbResponseEmployee);

                    /**
                     * we can call below method instead of above save method and below method is with @Query annotation.
                     * if you use below code instead of above code, please comment above save method line and remove /* and / in below.
                     */

                    /*
                    int a = employeeRepository.updateEmployee(dbResponseEmployee.getId(), dbResponseEmployee.getEmpName(),
                            dbResponseEmployee.getEmpAddress(), dbResponseEmployee.getEmpPrimaryPhone(),
                            dbResponseEmployee.getEmpSecondaryPhone(), dbResponseEmployee.getEmpEmail());
                    */

                    return new ResponseEntity("Successfully updated employee information. Please try to check by calling all employee information or employee by name or employee id", HttpStatus.OK);

                } else {
                    return new ResponseEntity("There is no changes with existing data as per user input.", HttpStatus.BAD_REQUEST);
                }

            } else {
                String errMsg = "There is no employee record from database as per input employee id "+ requestEmployee.getEmpId();
                return new ResponseEntity(errMsg, HttpStatus.NOT_FOUND);
            }

        } else {
            return new ResponseEntity("employee input object is null", HttpStatus.NOT_FOUND);

        }
    }


    @Transactional // for roll back.
    public ResponseEntity deleteByEmpId(String empId) {

        if(empId == null || empId.trim().equals("")){
            return new ResponseEntity("Input employee Id is null or empty, Please type valid id", HttpStatus.BAD_REQUEST);
        }

        List<Employee> employeeList = employeeRepository.findEmployeeByEmpId(empId, false); // find by delete is false

        /**
         * we can create these two below duplicate checking list 'if conditions' as one static method in new ValidationUtil class.
         * And we can call that method from there.
         * eg. public static <T> boolean isValidList(List<T> list) {
         *         return list.size() != 1 ? false : true;
         *     }
         *
         * boolean b = ValidationUtil.isValidList(employeeList);
         */

        // check for response list is empty or not.
        if(employeeList.isEmpty()){
            String errMsg = "Couldn't find employee by input Employee Id: " +empId;
            return new ResponseEntity(errMsg, HttpStatus.BAD_REQUEST);
        }
        // check for employee id is duplicate. It shouldn't be duplicate and so, we will return error.
        if(employeeList.size() > 1){
            return new ResponseEntity("Something went wrong. Please contact to your administrator!!!!", HttpStatus.INTERNAL_SERVER_ERROR);
        }


        /**
         * We need to set delete to true in user table too.
         */
        List<User> userList = userRepository.findByEmpId(empId, false);

        // check for response list is empty or not.
        if(userList.isEmpty()){
            String errMsg = "Couldn't find User with by input Employee Id: " +empId;
            return new ResponseEntity(errMsg, HttpStatus.BAD_REQUEST);
        }
        // check for employee id is duplicate. It shouldn't be duplicate and so, we will return error.
        if(userList.size() > 1){
            return new ResponseEntity("Something went wrong. Please contact to your administrator!!!!", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // now user List has only one value and we will get it by get(0)
        User user = userList.get(0);

        // now employee List has only one value and we will get it by get(0)
        Employee employee = employeeList.get(0);

        /**
         * Here we don't delete row permanently. We will just set deleted to true and for find APIs, we will filter by delete is false.
         */
        employee.setDeleted(true); // update existing employee is_delete to true which employee object will save into db.
        employee.setUpdatedDate(new Date()); // update updatedDate
        employeeRepository.save(employee); // update by calling save method.

        logger.info("Successfully update isDeleted into employee: {}",employee);



        user.setDeleted(true); // update existing user is_delete to true. which user object will save into db.
        user.setUpdatedDate(new Date()); // update updatedDate
        userRepository.save(user); // We need to set delete to true in user table too.

        logger.info("Successfully update isDeleted into user: {}",employee);

        return new ResponseEntity("Successfully Deleted.", HttpStatus.OK);

    }
}
