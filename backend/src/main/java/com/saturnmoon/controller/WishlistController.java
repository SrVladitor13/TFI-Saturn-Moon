package com.saturnmoon.controller;

import com.saturnmoon.model.Product;
import com.saturnmoon.service.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/wishlist")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class WishlistController {
    
    private final WishlistService wishlistService;
    
    @GetMapping
    public ResponseEntity<List<Product>> getWishlist(@AuthenticationPrincipal UserDetails userDetails) {
        Integer userId = getUserId(userDetails);
        return ResponseEntity.ok(wishlistService.getWishlist(userId));
    }
    
    @PostMapping("/{productId}")
    public ResponseEntity<?> addToWishlist(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Integer productId) {
        Integer userId = getUserId(userDetails);
        wishlistService.addToWishlist(userId, productId);
        return ResponseEntity.ok().body("Producto añadido a la lista de deseos");
    }
    
    @DeleteMapping("/{productId}")
    public ResponseEntity<?> removeFromWishlist(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Integer productId) {
        Integer userId = getUserId(userDetails);
        wishlistService.removeFromWishlist(userId, productId);
        return ResponseEntity.ok().body("Producto eliminado de la lista de deseos");
    }
    
    private Integer getUserId(UserDetails userDetails) {
        return 1; // Temporal, implementar extracción del JWT
    }
}
