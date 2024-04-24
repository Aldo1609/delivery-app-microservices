package com.aldob.products.mapper;

import com.aldob.products.dto.ProductsDTO;
import com.aldob.products.entity.ProductsEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductsMapper {

    ProductsDTO toDTO(ProductsEntity productsEntity);

    ProductsEntity toEntity(ProductsDTO productsDTO);
}