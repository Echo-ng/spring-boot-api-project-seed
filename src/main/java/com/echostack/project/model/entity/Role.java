package com.echostack.project.model.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @Author: Echo
 * @Date: 2019/2/22 15:29
 * @Description:
 */
@Entity
@Data
public class Role {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;
}
