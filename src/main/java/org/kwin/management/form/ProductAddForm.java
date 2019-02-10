package org.kwin.management.form;

import lombok.Data;

@Data
public class ProductAddForm {

    private String productType;

    private String productSize;

    private Integer productDirection;

    private Integer productPrice;

    private Integer productQuantity;
}
