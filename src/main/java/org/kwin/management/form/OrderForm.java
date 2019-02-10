package org.kwin.management.form;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class OrderForm {
    private String buyerAddress;

    private String buyerName;

    private String buyerPhone;

    private Integer installerId;

    private Date installerTime;

    private Integer orderDeposit;

    private Integer orderStatus = 0;

    private List<ProductAddForm> productAddFormList;
}
