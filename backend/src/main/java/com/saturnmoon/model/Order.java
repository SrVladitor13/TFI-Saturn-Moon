package com.saturnmoon.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status = OrderStatus.PENDING;
    
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal subtotal;
    
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal tax;
    
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal shipping;
    
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal total;
    
    @Column(name = "coupon_code")
    private String couponCode;
    
    @Column(name = "coupon_discount", precision = 10, scale = 2)
    private BigDecimal couponDiscount = BigDecimal.ZERO;
    
    @Column(name = "shipping_address")
    private String shippingAddress;
    
    @Column(columnDefinition = "TEXT")
    private String notes;
    
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items = new ArrayList<>();
    
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
    
    public enum OrderStatus {
        PENDING, SHIPPED, DELIVERED, CANCELLED
    }
}
