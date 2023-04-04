package com.project.market.web.orderhist.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.project.market.domain.order.constant.OrderStatus;

import lombok.Getter;

@Getter
public class OrderHistDto {

	private Long orderId;
    private LocalDateTime orderTime;
    private OrderStatus orderStatus;
    private int totalPrice;

    private List<OrderItemHistDto> orderItemHistDtos;

    public OrderHistDto(Long orderId, LocalDateTime orderTime, OrderStatus orderStatus) {
        this.orderId = orderId;
        this.orderTime = orderTime;
        this.orderStatus = orderStatus;
    }

    public void setOrderItemHistDtos(List<OrderItemHistDto> orderItemHistDtos) {
        this.orderItemHistDtos = orderItemHistDtos;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Getter
    public static class OrderItemHistDto {
        private String itemName;
        private int count;
        private int orderPrice;
        private String imageUrl;

        public OrderItemHistDto(String itemName, int count, int orderPrice, String imageUrl) {
            this.itemName = itemName;
            this.count = count;
            this.orderPrice = orderPrice;
            this.imageUrl = imageUrl;
        }
    }

    public OrderHistDto(Long orderId, LocalDateTime orderTime, OrderStatus orderStatus, int totalPrice, List<OrderItemHistDto> orderItemHistDtos) {
        this.orderId = orderId;
        this.orderTime = orderTime;
        this.orderStatus = orderStatus;
        this.totalPrice = totalPrice;
        this.orderItemHistDtos = orderItemHistDtos;
    }
}
