package com.yewin.employeecrudsystem.model.request;

import lombok.Data;

@Data
public class EmployeeRequest {
    String empId;
    String empName;
    String empAddress;
    String priPhone;
    String secPhone;
    String email;
}
