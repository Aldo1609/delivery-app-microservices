package com.aldob.products.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
@Table(name = "products")
public class ProductsEntity {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private int product_id;
    private int id_producto;
    private String producto;
    private int stock;
}