package com.aldob.ordenes.service.impl;

import com.aldob.ordenes.dto.OrdersDTO;
import com.aldob.ordenes.exception.OrderNotFoundException;
import com.aldob.ordenes.exception.ProductOutOfStockException;
import com.aldob.ordenes.mapper.OrdersMapper;
import com.aldob.ordenes.repository.OrdersRepository;
import com.aldob.ordenes.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrdersService{

    @Autowired
    private OrdersRepository ordersRepository;
    @Autowired
    private OrdersMapper ordersMapper;
    @Autowired
    private ProductService productService;

    @Override
    public List<OrdersDTO> getAllOrders() {
        return ordersRepository.findAll().stream()
                .map(ordersMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public OrdersDTO getOrderById(Integer id) {
        if(ordersRepository.existsById(id)) {
            return ordersRepository.findById(id)
                    .map(ordersMapper::toDTO)
                    .orElse(null);
        }else{
            throw new OrderNotFoundException("Orden no encontrada con la id: " + id);
        }
    }

    @Override
    public OrdersDTO saveOrder(OrdersDTO ordersDTO) {
        return productService.getProduct(ordersDTO.getId_comida())
                .flatMap(productDTO -> {
                    if (productDTO != null && productDTO.getStock() > 0) {
                        // Crear la orden
                        OrdersDTO createdOrder = ordersMapper.toDTO(ordersRepository.save(ordersMapper.toEntity(ordersDTO)));

                        // Descontar el stock
                        productDTO.setStock(productDTO.getStock() - 1);
                        return productService.updateProduct(productDTO)
                                .thenReturn(createdOrder);
                    } else {
                        throw new ProductOutOfStockException("El producto con id " + ordersDTO.getId_comida() + " está agotado.");
                    }
                })
                .block();
    }

    @Override
    public OrdersDTO updateOrder(Integer id, OrdersDTO ordersDTO) {
        if(ordersRepository.existsById(id)) {
            return ordersRepository.findById(id)
                    .map(orders -> {
                        orders.setId_comida(ordersDTO.getId_comida());
                        orders.setCliente(ordersDTO.getCliente());
                        orders.setDelivery(ordersDTO.getDelivery());
                        return ordersMapper.toDTO(ordersRepository.save(orders));
                    })
                    .orElse(null);
        }else{
            throw new OrderNotFoundException("Orden no encontrada con la id: " + id);
        }
    }

    @Override
    public void deleteOrder(Integer id) {
            if (ordersRepository.existsById(id)) {
                ordersRepository.deleteById(id);
            } else {
                throw new OrderNotFoundException("Orden no encontrada con la id: " + id);
            }
    }

    @Override
    public OrdersDTO updateDelivery(Integer id, OrdersDTO ordersDTO) {
        if(ordersRepository.existsById(id)) {
            return ordersRepository.findById(id)
                    .map(orders -> {
                        orders.setDelivery(ordersDTO.getDelivery());
                        return ordersMapper.toDTO(ordersRepository.save(orders));
                    })
                    .orElse(null);
        }else{
            throw new OrderNotFoundException("Orden no encontrada con la id: " + id);
        }
    }

}



