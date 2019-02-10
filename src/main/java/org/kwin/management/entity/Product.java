package org.kwin.management.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.kwin.management.enums.ProductDirectionEnum;
import org.kwin.management.utils.EnumUtil;

import java.util.Date;

@Data
public class Product {
    private String productId;

    private String productType;

    private String productSize;

    private Integer productDirection;

    private Integer productPrice;

    private Integer productStock;

    private Date createTime;

    private Date updateTime;

    @JsonIgnore
    public ProductDirectionEnum getProductDirectionByCode() {
        return EnumUtil.getByCode(productDirection, ProductDirectionEnum.class);
    }
}