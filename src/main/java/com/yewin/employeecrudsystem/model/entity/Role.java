package com.yewin.employeecrudsystem.model.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author Ye Win.
 * @CreatedDate 05/06/2019.
 * @Description This class is to create entity and bind with database table.
 */

@Entity
@Table(name = "role")
@Data
public class Role {
    @Id
    private Long id;
    @Column(name = "role_name")
    private String roleName;
}
