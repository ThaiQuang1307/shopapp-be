package com.project.shopapp.repositories;

import com.project.shopapp.models.OrderDetailModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetailModel, Long> {
    List<OrderDetailModel> findAllByOrderModel_id(Long orderId);
}
