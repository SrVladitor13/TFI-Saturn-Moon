package com.saturnmoon.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class OrderDto {
    private Integer id;
    private Integer userId;
    private String status;
    private BigDecimal subtotal;
    private BigDecimal tax;
    private BigDecimal shipping;
    private BigDecimal total;
    private String shippingAddress;
    private String notes;
    private LocalDateTime createdAt;
}
