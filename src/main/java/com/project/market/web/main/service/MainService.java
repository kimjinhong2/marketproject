package com.project.market.web.main.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.market.domain.item.service.ItemService;
import com.project.market.web.main.dto.ItemSearchDto;
import com.project.market.web.main.dto.MainItemDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MainService {

	private final ItemService itemService;
	
	@Transactional(readOnly = true)
	public Page<MainItemDto> getMainItem(ItemSearchDto itemSearchDto, Pageable pageable) {
		String searchQuery = itemSearchDto.getSearchQuery();//�˻� ���� Ȯ��
		
		return itemService.getSearchMainItem(searchQuery, pageable);//��ϵ� ��ǰ �� �˻� ���뿡 �´� ��ǰ Ž��
	}
}
