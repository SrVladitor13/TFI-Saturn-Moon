package com.saturnmoon.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class OrderStatusUpdateDto {
    @NotBlank(message = "Estado es requerido")
    private String status;
}
