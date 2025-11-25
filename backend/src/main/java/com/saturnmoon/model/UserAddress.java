package com.saturnmoon.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

@Entity
@Table(name = "user_addresses")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAddress {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    @Column(nullable = false)
    private String street;
    
    @Column(nullable = false)
    private String city;
    
    @Column(name = "postal_code", nullable = false)
    private String postalCode;
    
    @Column(nullable = false)
    private String country;
    
    @Column(name = "is_default", nullable = false)
    private Boolean isDefault = false;
}
