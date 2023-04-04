package com.project.market.web.cartList.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class CartOrderDto {

    private Long Id;

    private List<CartOrderDto> cartOrderDtoList;

}