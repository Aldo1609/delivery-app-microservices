package com.aldob.ordenes.service.impl;

import com.aldob.ordenes.dto.ProductDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {

    private final WebClient webClient;

    public ProductService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8080/api/v1").build();
    }

    public Flux<ProductDTO> getAllProducts() {
        return webClient.get()
                .uri("/products")
                .retrieve()
                .bodyToFlux(ProductDTO.class);
    }

    public Mono<ProductDTO> getProduct(int orderId) {
        return webClient.get()
                .uri("/products/{id}", orderId)
                .retrieve()
                .bodyToMono(ProductDTO.class);
    }

    public Mono<ProductDTO> updateProduct(ProductDTO productDTO) {
        return webClient.put()
                .uri("/products/{id}", productDTO.getProduct_id())
                .body(BodyInserters.fromValue(productDTO))
                .retrieve()
                .bodyToMono(ProductDTO.class);
    }
}