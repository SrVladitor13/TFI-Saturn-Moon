package com.saturnmoon.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddToCartRequest {
    @NotNull(message = "Product ID es requerido")
    private Integer productId;
    
    @Positive(message = "Cantidad debe ser positiva")
    private Integer quantity = 1;
}
