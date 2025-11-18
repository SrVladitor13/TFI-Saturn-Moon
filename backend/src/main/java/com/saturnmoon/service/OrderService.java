package com.saturnmoon.service;

import com.saturnmoon.dto.OrderCreateDto;
import com.saturnmoon.dto.OrderDto;
import com.saturnmoon.model.*;
import com.saturnmoon.repository.CartRepository;
import com.saturnmoon.repository.OrderRepository;
import com.saturnmoon.repository.ProductRepository;
import com.saturnmoon.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    
    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    
    @Transactional
    public OrderDto createOrder(Integer userId, OrderCreateDto dto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Carrito vacío"));
        
        if (cart.getItems().isEmpty()) {
            throw new RuntimeException("El carrito está vacío");
        }
        
        Order order = new Order();
        order.setUser(user);
        order.setStatus(Order.OrderStatus.PENDING);
        order.setShippingAddress(dto.getShippingAddress());
        order.setNotes(dto.getNotes());
        
        BigDecimal subtotal = BigDecimal.ZERO;
        for (CartItem cartItem : cart.getItems()) {
            Product product = cartItem.getProduct();
            
            if (product.getStock() < cartItem.getQuantity()) {
                throw new RuntimeException("Stock insuficiente para: " + product.getName());
            }
            
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(product);
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setUnitPrice(product.getPrice());
            orderItem.setSubtotal(product.getPrice().multiply(new BigDecimal(cartItem.getQuantity())));
            
            order.getItems().add(orderItem);
            subtotal = subtotal.add(orderItem.getSubtotal());
            
            product.setStock(product.getStock() - cartItem.getQuantity());
            productRepository.save(product);
        }
        
        order.setSubtotal(subtotal);
        order.setTax(subtotal.multiply(new BigDecimal("0.21")));
        order.setShipping(new BigDecimal("5.99"));
        order.setTotal(order.getSubtotal().add(order.getTax()).add(order.getShipping()));
        
        Order savedOrder = orderRepository.save(order);
        
        cart.getItems().clear();
        cartRepository.save(cart);
        
        return convertToDto(savedOrder);
    }
    
    public List<OrderDto> getUserOrders(Integer userId) {
        return orderRepository.findByUserIdOrderByCreatedAtDesc(userId)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    
    public OrderDto getOrderById(Integer orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
        return convertToDto(order);
    }
    
    public List<OrderDto> getAllOrders() {
        return orderRepository.findAllByOrderByCreatedAtDesc()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    
    @Transactional
    public OrderDto updateOrderStatus(Integer orderId, String status) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
        
        order.setStatus(Order.OrderStatus.valueOf(status.toUpperCase()));
        Order savedOrder = orderRepository.save(order);
        
        return convertToDto(savedOrder);
    }
    
    private OrderDto convertToDto(Order order) {
        OrderDto dto = new OrderDto();
        dto.setId(order.getId());
        dto.setUserId(order.getUser().getId());
        dto.setStatus(order.getStatus().toString());
        dto.setSubtotal(order.getSubtotal());
        dto.setTax(order.getTax());
        dto.setShipping(order.getShipping());
        dto.setTotal(order.getTotal());
        dto.setShippingAddress(order.getShippingAddress());
        dto.setNotes(order.getNotes());
        dto.setCreatedAt(order.getCreatedAt());
        return dto;
    }
}
