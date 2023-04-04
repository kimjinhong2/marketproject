package com.project.market.domain.cart.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.market.domain.cart.entity.Cart;
import com.project.market.domain.member.entity.Member;

public interface CartRepository extends JpaRepository<Cart, Long>{

	Optional<Cart> findFirstByMember(Member member);
	
	void deleteAllByMember(Member member);
}
