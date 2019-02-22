package org.kwin.management.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class OrderMasterForm {
    private String orderId;

    private String buyerAddress;

    private String buyerName;

    private String buyerPhone;

    private Integer installerId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date installerTime;

    private Integer orderDeposit;
}
