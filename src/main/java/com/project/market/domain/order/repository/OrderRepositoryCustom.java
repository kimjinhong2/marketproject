package com.project.market.domain.order.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.project.market.domain.member.entity.Member;
import com.project.market.web.orderhist.dto.OrderHistDto;

public interface OrderRepositoryCustom {

	Page<OrderHistDto> getOrderHistByMember(Member member,Pageable pageable);
}
