package com.saturnmoon.controller;

import com.saturnmoon.dto.OrderCreateDto;
import com.saturnmoon.dto.OrderDto;
import com.saturnmoon.dto.OrderStatusUpdateDto;
import com.saturnmoon.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class OrderController {
    
    private final OrderService orderService;
    
    @PostMapping
    public ResponseEntity<OrderDto> createOrder(
            @AuthenticationPrincipal UserDetails userDetails,
            @Valid @RequestBody OrderCreateDto dto) {
        Integer userId = getUserId(userDetails);
        return ResponseEntity.ok(orderService.createOrder(userId, dto));
    }
    
    @GetMapping
    public ResponseEntity<List<OrderDto>> getUserOrders(@AuthenticationPrincipal UserDetails userDetails) {
        Integer userId = getUserId(userDetails);
        return ResponseEntity.ok(orderService.getUserOrders(userId));
    }
    
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable Integer orderId) {
        return ResponseEntity.ok(orderService.getOrderById(orderId));
    }
    
    @GetMapping("/admin/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }
    
    @PutMapping("/{orderId}/status")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<OrderDto> updateOrderStatus(
            @PathVariable Integer orderId,
            @Valid @RequestBody OrderStatusUpdateDto dto) {
        return ResponseEntity.ok(orderService.updateOrderStatus(orderId, dto.getStatus()));
    }
    
    private Integer getUserId(UserDetails userDetails) {
        return 1; // Temporal, implementar extracción del JWT
    }
}
