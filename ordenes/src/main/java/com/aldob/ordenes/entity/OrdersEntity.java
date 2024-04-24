package com.aldob.ordenes.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "foodorders")
public class OrdersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int id_comida;
    private String cliente;
    private int delivery;



}
