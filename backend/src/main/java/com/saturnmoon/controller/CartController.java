package com.saturnmoon.controller;

import com.saturnmoon.dto.AddToCartRequest;
import com.saturnmoon.dto.CartDto;
import com.saturnmoon.service.CartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CartController {

    private final CartService cartService;

    @GetMapping
    public ResponseEntity<CartDto> getCart(@AuthenticationPrincipal UserDetails userDetails) {
        Integer userId = getUserId(userDetails);
        return ResponseEntity.ok(cartService.getCart(userId));
    }

    @PostMapping("/add")
    public ResponseEntity<CartDto> addToCart(
            @AuthenticationPrincipal UserDetails userDetails,
            @Valid @RequestBody AddToCartRequest request) {
        Integer userId = getUserId(userDetails);
        return ResponseEntity.ok(cartService.addToCart(userId, request.getProductId(), request.getQuantity()));
    }

    @PutMapping("/item/{itemId}")
    public ResponseEntity<CartDto> updateCartItem(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Integer itemId,
            @RequestParam Integer quantity) {
        Integer userId = getUserId(userDetails);
        return ResponseEntity.ok(cartService.updateCartItem(userId, itemId, quantity));
    }

    @DeleteMapping("/item/{itemId}")
    public ResponseEntity<CartDto> removeFromCart(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Integer itemId) {
        Integer userId = getUserId(userDetails);
        return ResponseEntity.ok(cartService.removeFromCart(userId, itemId));
    }

    @DeleteMapping("/clear")
    public ResponseEntity<?> clearCart(@AuthenticationPrincipal UserDetails userDetails) {
        Integer userId = getUserId(userDetails);
        cartService.clearCart(userId);
        return ResponseEntity.ok().body("Carrito vaciado exitosamente");
    }

    private Integer getUserId(UserDetails userDetails) {
        return 1; // Temporal, implementar extracción del JWT
    }
}
