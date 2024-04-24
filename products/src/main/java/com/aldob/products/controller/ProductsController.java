package com.aldob.products.controller;

import com.aldob.products.dto.ProductsDTO;
import com.aldob.products.service.Impl.ProductServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ProductsController {

    @Autowired
    private ProductServiceImpl orderService;

    @GetMapping(value = "/products")
    public List<ProductsDTO> getOrders() {
        return orderService.getAllProducts();
    }

    @GetMapping(value = "/products/{id}")
    public ProductsDTO getOrderById(@PathVariable Integer id) {
        return orderService.getProductById(id);
    }

    @PostMapping(value = "/products")
    public ProductsDTO createOrder(@Valid @RequestBody ProductsDTO productsDTODTO) {
        return orderService.createProduct(productsDTODTO);
    }

    @PutMapping(value = "/products/{id}")
    public ProductsDTO updateOrder(@PathVariable Integer id, @Valid @RequestBody ProductsDTO productsDTODTO) {
        return orderService.updateProduct(id, productsDTODTO);
    }

    @DeleteMapping(value = "/products/{id}")
    public void deleteOrder(@PathVariable Integer id) {
        orderService.deleteProduct(id);
    }

}
