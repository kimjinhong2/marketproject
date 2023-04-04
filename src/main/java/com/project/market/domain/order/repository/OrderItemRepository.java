package com.project.market.domain.order.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.market.domain.order.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{

	Optional<OrderItem> findById(Long id);
	
	void deleteById(Long id);
}
