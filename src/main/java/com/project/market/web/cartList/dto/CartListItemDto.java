package com.project.market.web.cartList.dto;


import com.project.market.domain.item.entity.Item;
import com.project.market.domain.order.entity.OrderItem;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CartListItemDto {

    private Long id; //��ٱ��� ��ǰ ���̵�

    private String itemName; //��ǰ��

    private String itemDetail;

    private int price; //��ǰ �ݾ�

    private int count; //����

    private String imgUrl; //��ǰ �̹��� ���

    public static CartListItemDto of(OrderItem orderItem) {
        Item item = orderItem.getItem();
        return CartListItemDto.builder()
                .id(orderItem.getId())
                .itemName(item.getItemName())
                .itemDetail(item.getItemDetail())
                .price(item.getPrice())
                .count(orderItem.getCount())
                .imgUrl(item.getImageList().get(0).getImageUrl())
                .build();
    }
}