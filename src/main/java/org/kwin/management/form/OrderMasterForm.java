package org.kwin.management.form;

import lombok.Data;

import java.util.Date;

@Data
public class OrderMasterForm {
    private String orderId;

    private String buyerAddress;

    private String buyerName;

    private String buyerPhone;

    private Integer installerId;

    private Date installerTime;

    private Integer orderDeposit;
}
