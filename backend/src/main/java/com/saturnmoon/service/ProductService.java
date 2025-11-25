package com.saturnmoon.service;

import com.saturnmoon.dto.ProductCreateDto;
import com.saturnmoon.dto.ProductUpdateDto;
import com.saturnmoon.model.Category;
import com.saturnmoon.model.Product;
import com.saturnmoon.repository.CategoryRepository;
import com.saturnmoon.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    
    public List<Product> getAllActiveProducts() {
        return productRepository.findByIsActiveTrue();
    }
    
    public Product getProductById(Integer id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));
    }
    
    public List<Product> getProductsByCategory(Integer categoryId) {
        return productRepository.findByCategoryIdAndIsActiveTrue(categoryId);
    }
    
    public List<Product> searchProducts(String keyword) {
        return productRepository.findByNameContainingIgnoreCaseAndIsActiveTrue(keyword);
    }
    
    public List<Product> getProductsByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        return productRepository.findByPriceRange(minPrice, maxPrice);
    }
    
    @Transactional
    public Product createProduct(ProductCreateDto dto) {
        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
        
        Product product = new Product();
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setCategory(category);
        product.setStock(dto.getStock());
        product.setImageUrl(dto.getImageUrl());
        product.setIsActive(true);
        
        return productRepository.save(product);
    }
    
    @Transactional
    public Product updateProduct(Integer id, ProductUpdateDto dto) {
        Product product = getProductById(id);
        
        if (dto.getName() != null) {
            product.setName(dto.getName());
        }
        if (dto.getDescription() != null) {
            product.setDescription(dto.getDescription());
        }
        if (dto.getPrice() != null) {
            product.setPrice(dto.getPrice());
        }
        if (dto.getCategoryId() != null) {
            Category category = categoryRepository.findById(dto.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
            product.setCategory(category);
        }
        if (dto.getStock() != null) {
            product.setStock(dto.getStock());
        }
        if (dto.getImageUrl() != null) {
            product.setImageUrl(dto.getImageUrl());
        }
        
        return productRepository.save(product);
    }
    
    @Transactional
    public void deleteProduct(Integer id) {
        Product product = getProductById(id);
        product.setIsActive(false);
        productRepository.save(product);
    }
}
