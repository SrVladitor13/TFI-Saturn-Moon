package com.saturnmoon.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class ProductCreateDto {
    @NotBlank(message = "Nombre es requerido")
    private String name;
    
    private String description;
    
    @NotNull(message = "Precio es requerido")
    @Positive(message = "Precio debe ser positivo")
    private BigDecimal price;
    
    @NotNull(message = "Categoría es requerida")
    private Integer categoryId;
    
    @NotNull(message = "Stock es requerido")
    private Integer stock;
    
    private String imageUrl;
}
