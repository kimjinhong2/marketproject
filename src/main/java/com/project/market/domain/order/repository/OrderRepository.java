package com.project.market.domain.order.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.market.domain.member.entity.Member;
import com.project.market.domain.order.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long>, OrderRepositoryCustom{

	List<Order> findByMember(Member member);
}
