package org.kwin.management.form;

import lombok.Data;

@Data
public class OrderDetailForm {

    private String detailId;

    private String orderId;

    private Integer productQuantity;
}
