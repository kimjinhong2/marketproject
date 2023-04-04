package com.project.market.domain.item.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.project.market.domain.item.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Long>,ItemRepositoryCustom {

	@Override
	Optional<Item> findById(Long id);
}
