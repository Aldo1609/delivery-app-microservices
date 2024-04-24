package com.aldob.ordenes.dto;

import lombok.Data;

@Data
public class OrdersDTO {

    private int id;
    private int id_comida;
    private String cliente;
    private int delivery;

}
