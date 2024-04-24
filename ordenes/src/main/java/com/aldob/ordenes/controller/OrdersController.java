package com.aldob.ordenes.controller;

import com.aldob.ordenes.dto.OrdersDTO;
import com.aldob.ordenes.service.OrdersService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    @GetMapping(value = "/orders")
    public List<OrdersDTO> getOrders()
    {
        return ordersService.getAllOrders();
    }

    @GetMapping(value = "/orders/{id}")
    public OrdersDTO getOrderById(@PathVariable Integer id)
    {
        return ordersService.getOrderById(id);
    }

    @PostMapping(value = "/orders")
    public OrdersDTO createOrder(@Valid @RequestBody OrdersDTO ordersDTO)
    {
        return ordersService.saveOrder(ordersDTO);
    }

    @PutMapping(value = "/orders/delivery/{id}")
    public OrdersDTO updateDelivery(@PathVariable Integer id, @RequestBody OrdersDTO ordersDTO)
    {
        return ordersService.updateDelivery(id, ordersDTO);
    }

    @PutMapping(value = "/orders/{id}")
    public OrdersDTO updateOrder(@PathVariable Integer id, @Valid @RequestBody OrdersDTO ordersDTO)
    {
        return ordersService.updateOrder(id, ordersDTO);
    }

    @DeleteMapping(value = "/orders/{id}")
    public void deleteOrder(@PathVariable Integer id)
    {
        ordersService.deleteOrder(id);
    }

}
