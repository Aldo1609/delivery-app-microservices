package com.aldob.ordenes.mapper;

import com.aldob.ordenes.dto.OrdersDTO;
import com.aldob.ordenes.entity.OrdersEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrdersMapper {

    OrdersDTO toDTO(OrdersEntity ordersEntity);

    OrdersEntity toEntity(OrdersDTO ordersDTO);

}
