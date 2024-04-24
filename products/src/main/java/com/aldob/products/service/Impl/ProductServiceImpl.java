package com.aldob.products.service.Impl;

import com.aldob.products.dto.ProductsDTO;
import com.aldob.products.exception.ProductNotFoundException;
import com.aldob.products.mapper.ProductsMapper;
import com.aldob.products.repository.ProductsRepository;
import com.aldob.products.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductsRepository productsRepositoryRepository;

    @Autowired
    private ProductsMapper productsMapper;

    @Override
    public List<ProductsDTO> getAllProducts() {
        return productsRepositoryRepository.findAll().stream()
                .map(productsMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProductsDTO getProductById(Integer id) {
        if(productsRepositoryRepository.existsById(id)) {
            return productsRepositoryRepository.findById(id)
                    .map(productsMapper::toDTO)
                    .orElse(null);
        }else{
            throw new ProductNotFoundException("Producto no encontrada con la id: " + id);
        }
    }

    @Override
    public ProductsDTO createProduct(ProductsDTO productsDTO) {
        return productsMapper.toDTO(productsRepositoryRepository.save(productsMapper.toEntity(productsDTO)));
    }

    @Override
    public ProductsDTO updateProduct(Integer id, ProductsDTO productsDTO) {
        if(productsRepositoryRepository.existsById(id)) {
            return productsRepositoryRepository.findById(id)
                    .map(products -> {
                        products.setProducto(productsDTO.getProducto());
                        products.setId_producto(productsDTO.getId_producto());
                        products.setStock(productsDTO.getStock());
                        return productsMapper.toDTO(productsRepositoryRepository.save(products));
                    })
                    .orElse(null);
        }else{
            throw new ProductNotFoundException("Producto no encontrada con la id: " + id);
        }
    }

    @Override
    public void deleteProduct(Integer id) {
        if (productsRepositoryRepository.existsById(id)) {
            productsRepositoryRepository.deleteById(id);
        } else {
            throw new ProductNotFoundException("Producto no encontrada con la id: " + id);
        }
    }
}
