package com.project.market.web.adminItem.dto;

import com.project.market.domain.item.constant.ItemSellStatus;
import com.project.market.domain.item.entity.Item;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UpdateAdminItemDto {
    private Long itemId;

    @NotBlank(message = "��ǰ���� �ʼ� �Է� ���Դϴ�.")
    private String itemName;

    @NotNull(message = "������ �ʼ� �Է� ���Դϴ�.")
    private Integer price;

    @NotBlank(message = "��ǰ �󼼴� �ʼ� �Է� ���Դϴ�.")
    private String itemDetail;

    @NotNull(message = "���� �ʼ� �Է� ���Դϴ�.")
    private Integer stockNumber;

    private ItemSellStatus itemSellStatus;

    private List<MultipartFile> itemImageFiles;

    private List<ItemImageDto> itemImageDtos;

    private List<String> originalImageNames;

    @Builder
    @Getter @Setter
    public static class ItemImageDto {
        private Long itemImageId;
        private String originalImageName;
    }

    @Builder
    public UpdateAdminItemDto(Long itemId, String itemName, Integer price, String itemDetail, Integer stockNumber, ItemSellStatus itemSellStatus, List<MultipartFile> itemImageFiles, List<ItemImageDto> itemImageDtos, List<String> originalImageNames) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.price = price;
        this.itemDetail = itemDetail;
        this.stockNumber = stockNumber;
        this.itemSellStatus = itemSellStatus;
        this.itemImageFiles = itemImageFiles;
        this.itemImageDtos = itemImageDtos;
        this.originalImageNames = originalImageNames;
    }

    public static UpdateAdminItemDto of(Item item, List<ItemImageDto> itemImageDtos) {
        return UpdateAdminItemDto.builder()
                .itemId(item.getId())
                .itemName(item.getItemName())
                .price(item.getPrice())
                .itemDetail(item.getItemDetail())
                .stockNumber(item.getStockNumber())
                .itemSellStatus(item.getItemSellStatus())
                .itemImageDtos(itemImageDtos)
                .build();
    }

    public Item toItemEntity() {
        return Item.builder()
                .itemName(itemName)
                .price(price)
                .itemDetail(itemDetail)
                .stockNumber(stockNumber)
                .itemSellStatus(itemSellStatus)
                .build();
    }
}