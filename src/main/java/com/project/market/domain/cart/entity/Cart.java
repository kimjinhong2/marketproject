package com.project.market.domain.cart.entity;

import java.util.List;

import com.project.market.domain.base.BaseEntity;
import com.project.market.domain.member.entity.Member;
import com.project.market.domain.order.entity.OrderItem;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table
@NoArgsConstructor
public class Cart extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne(fetch = FetchType.LAZY)//LAZY는 필요한 시점에 연관된 데이터만 불러오는 것, EAGER는 데이터 조회시 연관된 데이터 한 번에 불러오는 것
	@JoinColumn(name = "member_id",nullable = false)
	private Member member;
	//연관관계의 주인은 1:N인 경우 N쪽으로
	@OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, fetch = FetchType.LAZY)//mappedby는 자신이 연관관계의 주인이 아님을 표시
	private List<OrderItem> orderItemList;
	
	@Builder
	public Cart(Member member, List<OrderItem> orderItemList) {
		this.member = member;
		this.orderItemList=orderItemList;
	}
	
	public void addOrderItem(OrderItem orderItem) {
        this.orderItemList.add(orderItem);
        orderItem.setCart(this);
    }
}
