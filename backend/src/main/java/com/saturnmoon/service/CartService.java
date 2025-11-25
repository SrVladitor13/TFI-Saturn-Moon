package com.saturnmoon.service;

import com.saturnmoon.dto.CartDto;
import com.saturnmoon.dto.CartItemDto;
import com.saturnmoon.model.Cart;
import com.saturnmoon.model.CartItem;
import com.saturnmoon.model.Product;
import com.saturnmoon.model.User;
import com.saturnmoon.repository.CartItemRepository;
import com.saturnmoon.repository.CartRepository;
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
public class CartService {
    
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    
    @Transactional
    public CartDto getCart(Integer userId) {
        Cart cart = getOrCreateCart(userId);
        return convertToDto(cart);
    }
    
    @Transactional
    public CartDto addToCart(Integer userId, Integer productId, Integer quantity) {
        Cart cart = getOrCreateCart(userId);
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        
        if (product.getStock() < quantity) {
            throw new RuntimeException("Stock insuficiente");
        }
        
        CartItem existingItem = cartItemRepository.findByCartIdAndProductId(cart.getId(), productId)
                .orElse(null);
        
        if (existingItem != null) {
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
            cartItemRepository.save(existingItem);
        } else {
            CartItem newItem = new CartItem();
            newItem.setCart(cart);
            newItem.setProduct(product);
            newItem.setQuantity(quantity);
            cartItemRepository.save(newItem);
        }
        
        return convertToDto(cart);
    }
    
    @Transactional
    public CartDto updateCartItem(Integer userId, Integer itemId, Integer quantity) {
        Cart cart = getOrCreateCart(userId);
        CartItem item = cartItemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item no encontrado"));
        
        if (!item.getCart().getId().equals(cart.getId())) {
            throw new RuntimeException("Item no pertenece al carrito del usuario");
        }
        
        if (quantity <= 0) {
            cartItemRepository.delete(item);
        } else {
            item.setQuantity(quantity);
            cartItemRepository.save(item);
        }
        
        return convertToDto(cart);
    }
    
    @Transactional
    public CartDto removeFromCart(Integer userId, Integer itemId) {
        Cart cart = getOrCreateCart(userId);
        CartItem item = cartItemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item no encontrado"));
        
        if (!item.getCart().getId().equals(cart.getId())) {
            throw new RuntimeException("Item no pertenece al carrito del usuario");
        }
        
        cartItemRepository.delete(item);
        return convertToDto(cart);
    }
    
    @Transactional
    public void clearCart(Integer userId) {
        Cart cart = getOrCreateCart(userId);
        cart.getItems().clear();
        cartRepository.save(cart);
    }
    
    private Cart getOrCreateCart(Integer userId) {
        return cartRepository.findByUserId(userId)
                .orElseGet(() -> {
                    User user = userRepository.findById(userId)
                            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
                    Cart newCart = new Cart();
                    newCart.setUser(user);
                    return cartRepository.save(newCart);
                });
    }
    
    private CartDto convertToDto(Cart cart) {
        List<CartItemDto> items = cart.getItems().stream()
                .map(this::convertItemToDto)
                .collect(Collectors.toList());
        
        BigDecimal total = items.stream()
                .map(CartItemDto::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        
        return new CartDto(cart.getId(), items, total);
    }
    
    private CartItemDto convertItemToDto(CartItem item) {
        Product product = item.getProduct();
        BigDecimal subtotal = product.getPrice().multiply(new BigDecimal(item.getQuantity()));
        
        return new CartItemDto(
                item.getId(),
                product.getId(),
                product.getName(),
                product.getPrice(),
                item.getQuantity(),
                subtotal,
                product.getImageUrl()
        );
    }
}
