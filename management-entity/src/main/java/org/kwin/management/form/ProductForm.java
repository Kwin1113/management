package org.kwin.management.form;

import lombok.Data;

@Data
public class ProductForm {

    private String productId;

    private String productType;

    private String productSize;

    private Integer productDirection;

    private Integer productPrice;

    private Integer productStock;
}
