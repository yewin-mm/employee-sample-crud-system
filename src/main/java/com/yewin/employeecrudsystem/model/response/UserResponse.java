package com.yewin.employeecrudsystem.model.response;

import lombok.Data;

import java.util.Date;

@Data
public class UserResponse {

    private Long id;
    private String userName;
    private String password;
    private long roleId;
    private Date createdDate;
    private Date updatedDate;
    private String roleName;
}
