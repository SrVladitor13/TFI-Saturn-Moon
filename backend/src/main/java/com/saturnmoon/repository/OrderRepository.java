package com.saturnmoon.repository;

import com.saturnmoon.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByUserId(Integer userId);
    List<Order> findByUserIdOrderByCreatedAtDesc(Integer userId);
    List<Order> findAllByOrderByCreatedAtDesc();
}
