package com.aldob.products.service;

import com.aldob.products.dto.ProductsDTO;

import java.util.List;

public interface ProductService {

    List<ProductsDTO> getAllProducts();
    ProductsDTO getProductById(Integer id);
    ProductsDTO createProduct(ProductsDTO ordersDTO);
    ProductsDTO updateProduct(Integer id, ProductsDTO ordersDTO);
    void deleteProduct(Integer id);

}