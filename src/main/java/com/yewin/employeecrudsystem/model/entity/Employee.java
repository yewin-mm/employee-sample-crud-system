package com.yewin.employeecrudsystem.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author Ye Win.
 * @CreatedDate 05/06/2019.
 * @Description This class is to create entity and bind with database table.
 */

@Entity
@Table(name = "employees")
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "emp_id")
    private String empId;
    @Column(name = "emp_name")
    private String empName;
    @Column(name = "emp_address")
    private String empAddress;
    @Column(name = "emp_pri_phone")
    private String empPrimaryPhone;
    @Column(name = "emp_sec_phone")
    private String empSecondaryPhone;
    @Column(name = "emp_email")
    private String empEmail;
    @Column(name = "is_deleted")
    private boolean isDeleted;
    @Column(name = "created_date")
    private Date createdDate;
    @Column(name = "updated_date")
    private Date updatedDate;
}
