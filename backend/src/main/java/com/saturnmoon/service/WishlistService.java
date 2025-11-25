package com.saturnmoon.service;

import com.saturnmoon.model.Product;
import com.saturnmoon.model.User;
import com.saturnmoon.model.Wishlist;
import com.saturnmoon.repository.ProductRepository;
import com.saturnmoon.repository.UserRepository;
import com.saturnmoon.repository.WishlistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WishlistService {
    
    private final WishlistRepository wishlistRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    
    public List<Product> getWishlist(Integer userId) {
        return wishlistRepository.findByUserId(userId)
                .stream()
                .map(Wishlist::getProduct)
                .collect(Collectors.toList());
    }
    
    @Transactional
    public void addToWishlist(Integer userId, Integer productId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        
        if (wishlistRepository.findByUserIdAndProductId(userId, productId).isPresent()) {
            throw new RuntimeException("El producto ya está en la lista de deseos");
        }
        
        Wishlist wishlist = new Wishlist();
        wishlist.setUser(user);
        wishlist.setProduct(product);
        wishlistRepository.save(wishlist);
    }
    
    @Transactional
    public void removeFromWishlist(Integer userId, Integer productId) {
        wishlistRepository.deleteByUserIdAndProductId(userId, productId);
    }
}
