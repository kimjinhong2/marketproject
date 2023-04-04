package com.project.market.domain.itemImage.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.market.domain.item.entity.Item;
import com.project.market.domain.itemImage.entity.ItemImage;

public interface ItemImageRepository extends JpaRepository<ItemImage, Long>{

	List<ItemImage> findByItemOrderById(Item item);
}
