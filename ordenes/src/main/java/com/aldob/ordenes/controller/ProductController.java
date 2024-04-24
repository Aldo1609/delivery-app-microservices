package com.aldob.ordenes.controller;

import com.aldob.ordenes.dto.ProductDTO;
import com.aldob.ordenes.service.impl.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/products")
    public Flux<ProductDTO> getProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/products/{id}")
    public Mono<ProductDTO> getProducts(@PathVariable Integer id) {
        return productService.getProduct(id);
    }

}
