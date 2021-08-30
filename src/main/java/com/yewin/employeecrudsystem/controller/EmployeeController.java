package com.yewin.employeecrudsystem.controller;

import com.yewin.employeecrudsystem.model.entity.Employee;
import com.yewin.employeecrudsystem.model.request.EmployeeRequest;
import com.yewin.employeecrudsystem.service.EmployeeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author Ye Win.
 * @CreatedDate 05/06/2019.
 * @Description This class is to implement api endpoint which can manage data from the Outside or to the Outside as response.
 */

@RestController
public class EmployeeController {


    private static final Logger logger = LogManager.getLogger(EmployeeController.class); // here we used slf4j logger for logging instead of System.out.println(). We can use Log4j and Log4j2 logger too.

    @Autowired
    EmployeeService employeeService;


    @PostMapping("/insertEmployee")
    public ResponseEntity insertEmployee(@RequestParam String empName, @RequestParam String empAddress, @RequestParam String priPhone, @RequestParam String secPhone, @RequestParam String empEmail) {
        logger.info("==================== Start insert employee method!!! ====================");
        logger.info("Request -  employee name: {}, employee address: {}, primary phone: {}, secondary phone: {}, employee email: {}", empName, empAddress, priPhone, secPhone, empEmail);
        ResponseEntity responseEntity;
        try {

            responseEntity = employeeService.insertEmployee(empName, empAddress, priPhone, secPhone, empEmail);

        } catch (Exception e) {// service class error like "can't connect to database, it will catch in here too. So, we need to try, catch in every method because, error can be occur anytime.
            e.printStackTrace();
            logger.error(e.getMessage());
            responseEntity = new ResponseEntity("Something went wrong, Please contact to your administrator!!!!", HttpStatus.INTERNAL_SERVER_ERROR);

        }
        logger.info("Response - body: {}, status code: {}", responseEntity.getBody(), responseEntity.getStatusCode());
        logger.info("==================== End insert employee method!!! ====================");

        return responseEntity;

    }

    @GetMapping("/getAllEmployee")
    public ResponseEntity getAllEmployee() {
        logger.info("==================== Start get all employee method!!! ====================");
        ResponseEntity responseEntity;
        try {

            List<Employee> employeeList = employeeService.getAllEmployee();
            responseEntity = new ResponseEntity(employeeList, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            responseEntity = new ResponseEntity("Something went wrong, Please contact to your administrator!!!!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        logger.info("Response - body: {}, status code: {}", responseEntity.getBody(), responseEntity.getStatusCode());
        logger.info("==================== End get all employee method!!! ====================");

        return responseEntity;

    }

    @GetMapping("/findEmployeeById/{id}")
    public ResponseEntity findEmployeeById(@PathVariable("id") Long id) {
        logger.info("==================== Start find employee by empId method!!! ====================");
        logger.info("Request - Id: {}", id);
        ResponseEntity responseEntity;
        try {

            responseEntity = employeeService.findEmployeeById(id);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            responseEntity = new ResponseEntity("Something went wrong, Please contact to your administrator!!!!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        logger.info("Response - body: {}, status code: {}", responseEntity.getBody(), responseEntity.getStatusCode());
        logger.info("==================== End find employee by empId method!!! ====================");

        return responseEntity;

    }

    @GetMapping("/findEmployeeByEmpId/{empId}")
    public ResponseEntity findEmployeeByEmpId(@PathVariable("empId") String empId) {
        logger.info("==================== Start find employee by empId method!!! ====================");
        logger.info("Request - empId: {}", empId);
        ResponseEntity responseEntity;
        try {

            responseEntity = employeeService.findEmployeeByEmpId(empId);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            responseEntity = new ResponseEntity("Something went wrong, Please contact to your administrator!!!!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        logger.info("Response - body: {}, status code: {}", responseEntity.getBody(), responseEntity.getStatusCode());
        logger.info("==================== End find employee by empId method!!! ====================");

        return responseEntity;

    }

    @GetMapping("/findEmployeeByName/{name}")
    public ResponseEntity findEmployeeByName(@PathVariable("name") String empName) {
        logger.info("==================== Start find all employee by name method!!! ====================");
        logger.info("Request - EmpName: {}", empName);
        ResponseEntity responseEntity;

        try {

            responseEntity = employeeService.getEmployeeByName(empName);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            responseEntity = new ResponseEntity("Something went wrong, Please contact to your administrator!!!!", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        logger.info("Response - body: {}, status code: {}", responseEntity.getBody(), responseEntity.getStatusCode());
        logger.info("==================== End find all employee by name method!!! ====================");

        return responseEntity;

    }

    // you can do with @PutMapping
    @PostMapping("/updateEmployee")
    public ResponseEntity updateEmployee(@RequestBody EmployeeRequest employeeRequest) {
        logger.info("==================== Start update employee method!!! ====================");
        logger.info("Request: {}", employeeRequest);
        ResponseEntity responseEntity;

        try {

            responseEntity = employeeService.updateEmployee(employeeRequest);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            responseEntity = new ResponseEntity("Something went wrong, Please contact to your administrator!!!!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        logger.info("Response - body: {}, status code: {}", responseEntity.getBody(), responseEntity.getStatusCode());
        logger.info("==================== End update employee method!!! ====================");

        return responseEntity;

    }

    // you can do with @DeleteMapping
    @PostMapping("/deleteByEmpId")
    public ResponseEntity deleteByEmpId(@RequestParam String empId) {
        logger.info("==================== Start delete by emp id method!!! ====================");
        logger.info("Request empid: {}", empId);
        ResponseEntity responseEntity;
        try {

            responseEntity = employeeService.deleteByEmpId(empId);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            responseEntity = new ResponseEntity("Something went wrong, Please contact to your administrator!!!!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        //logger.info("Response - body: {}, status code: {}", responseEntity.getBody(), responseEntity.getStatusCode());
        logger.info("==================== End delete by emp id method!!! ====================");

        return responseEntity;

    }

}
