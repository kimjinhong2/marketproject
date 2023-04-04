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
	
	@OneToOne(fetch = FetchType.LAZY)//LAZY�� �ʿ��� ������ ������ �����͸� �ҷ����� ��, EAGER�� ������ ��ȸ�� ������ ������ �� ���� �ҷ����� ��
	@JoinColumn(name = "member_id",nullable = false)
	private Member member;
	//���������� ������ 1:N�� ��� N������
	@OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, fetch = FetchType.LAZY)//mappedby�� �ڽ��� ���������� ������ �ƴ��� ǥ��
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
