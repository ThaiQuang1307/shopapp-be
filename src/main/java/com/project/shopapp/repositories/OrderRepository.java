package com.project.shopapp.repositories;

import com.project.shopapp.models.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderModel, Long> {
    // tìm các đơn hàng của 1 user
    List<OrderModel> findAllByUserModel_id(Long userId);
}
