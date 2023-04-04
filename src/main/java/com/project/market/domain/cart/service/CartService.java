package com.project.market.domain.cart.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.market.domain.cart.entity.Cart;
import com.project.market.domain.cart.repository.CartRepository;
import com.project.market.domain.member.entity.Member;
import com.project.market.domain.order.entity.OrderItem;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartService {

	private final CartRepository cartRepository;
	
	@Transactional(readOnly = true)
	public Cart getCartByMember(Member member) {
		Optional<Cart> cart = cartRepository.findFirstByMember(member);
		if(cart.isEmpty()) {
			return createNewCart(member);
		}
		
		return cart.get();
	}
	
	@Transactional
	public void addOrderItem(OrderItem orderItem,Member member) {
		Cart cart = getCartByMember(member);
		cart.addOrderItem(orderItem);
	}
	
	private Cart createNewCart(Member member) {
		return cartRepository.save(
					Cart.builder()
							.member(member)
							.orderItemList(new ArrayList<>())
							.build()
				);
	}
}
