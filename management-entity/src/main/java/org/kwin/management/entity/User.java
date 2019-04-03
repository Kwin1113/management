package org.kwin.management.entity;

import lombok.Data;

import java.util.Date;

@Data
public class User {

    private Integer userId;

    private String userName;

    private String password;

    private Date createTime;

    private Date updateTime;
}
