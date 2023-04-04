package com.project.market.web.adminItem.dto;

import com.project.market.domain.item.constant.ItemSellStatus;
import com.project.market.domain.item.entity.Item;
import com.project.market.domain.member.entity.Member;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class RegisterAdminItemDto {

    @NotBlank(message = "��ǰ���� �ʼ� �Է� ���Դϴ�.")
    private String itemName;

    @NotNull(message = "������ �ʼ� �Է� ���Դϴ�.")
    private Integer price;

    @NotEmpty(message = "��ǰ �󼼴� �ʼ� �Է� ���Դϴ�.")
    private String itemDetail;

    @NotNull(message = "���� �ʼ� �Է� ���Դϴ�.")
    private Integer stockNumber;

    private ItemSellStatus itemSellStatus;

    private List<MultipartFile> itemImageFiles;

    @Builder
    public RegisterAdminItemDto(String itemName, Integer price, String itemDetail, Integer stockNumber, ItemSellStatus itemSellStatus, List<MultipartFile> itemImageFiles) {
        this.itemName = itemName;
        this.price = price;
        this.itemDetail = itemDetail;
        this.stockNumber = stockNumber;
        this.itemSellStatus = itemSellStatus;
        this.itemImageFiles = itemImageFiles;
    }

    public Item toItemEntity(Member member) {
        return Item.builder()
                .itemName(itemName)
                .price(price)
                .itemDetail(itemDetail)
                .stockNumber(stockNumber)
                .itemSellStatus(itemSellStatus)
                .member(member)
                .build();
    }
}