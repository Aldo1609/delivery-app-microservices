package com.aldob.ordenes.service;

import com.aldob.ordenes.dto.OrdersDTO;

import java.util.List;

public interface OrdersService {

    List<OrdersDTO> getAllOrders();

    OrdersDTO getOrderById(Integer id);

    OrdersDTO saveOrder(OrdersDTO ordersDTO);

    OrdersDTO updateOrder(Integer id, OrdersDTO ordersDTO);

    OrdersDTO updateDelivery(Integer id, OrdersDTO ordersDTO);

    void deleteOrder(Integer id);

}
