package com.saturnmoon.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class OrderCreateDto {
    @NotBlank(message = "Dirección de envío es requerida")
    private String shippingAddress;
    
    private String notes;
}
