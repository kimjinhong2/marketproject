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
		String searchQuery = itemSearchDto.getSearchQuery();//검색 내용 확인
		
		return itemService.getSearchMainItem(searchQuery, pageable);//등록된 상품 중 검색 내용에 맞는 상품 탐색
	}
}
