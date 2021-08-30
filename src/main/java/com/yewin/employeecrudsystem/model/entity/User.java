package com.yewin.employeecrudsystem.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author Ye Win.
 * @CreatedDate 05/03/2019.
 * @Description This class is to create entity and bind with database table.
 */

@Entity
@Table(name = "users") // we need to name with users not user because postgresql own table with name user. So we need to avoid conflict table name.
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "password")
    private String password;
    @Column(name="role_id")
    private long roleId;
    @Column(name = "is_deleted")
    private boolean isDeleted;
    @Column(name = "created_date")
    private Date createdDate;
    @Column(name = "updated_date")
    private Date updatedDate;
}
