package org.kwin.management.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.kwin.management.enums.ProductDirectionEnum;
import org.kwin.management.utils.EnumUtil;

import java.util.Date;

@Data
public class OrderDetail {
    private String detailId;

    private String orderId;

    private String productId;

    private String productType;

    private String productSize;

    private Integer productDirection;

    private Integer productPrice;

    private Integer productQuantity;

    private Date createTime;

    private Date updateTime;

    @JsonIgnore
    public ProductDirectionEnum getProductDirectionByCode() {
        return EnumUtil.getByCode(productDirection, ProductDirectionEnum.class);
    }
}