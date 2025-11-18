package com.saturnmoon.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class AddToCartRequest {
    @NotNull(message = "Product ID es requerido")
    private Integer productId;

    @Positive(message = "Cantidad debe ser positiva")
    private Integer quantity = 1;
}
