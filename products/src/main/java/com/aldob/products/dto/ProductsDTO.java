package com.aldob.products.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductsDTO {

    private int product_id;
    private int id_producto;

    @NotNull(message = "El producto no puede ser nulo")
    @NotBlank(message = "El producto no puede estar vacío")
    private String producto;

    @NotNull(message = "El stock no puede ser nulo")
    @NotBlank(message = "El stock no puede estar vacío")
    @Min(value = 0, message = "El stock no puede ser negativo")
    private int stock;
}