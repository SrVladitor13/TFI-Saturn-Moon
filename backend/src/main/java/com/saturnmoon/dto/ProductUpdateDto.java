package com.saturnmoon.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class ProductUpdateDto {
    private String name;
    private String description;
    private BigDecimal price;
    private Integer categoryId;
    private Integer stock;
    private String imageUrl;
}
