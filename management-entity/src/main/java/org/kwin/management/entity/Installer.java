package org.kwin.management.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Installer {
    private Integer installerId;

    private String installerName;

    private String installerPhone;

    private Date createTime;

    private Date updateTime;
}