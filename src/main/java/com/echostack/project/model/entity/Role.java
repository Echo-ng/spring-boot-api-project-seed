package com.echostack.project.model.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @Author: Echo
 * @Date: 2019/2/22 15:29
 * @Description:
 */
@Entity
@Data
public class Role {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name;
}
