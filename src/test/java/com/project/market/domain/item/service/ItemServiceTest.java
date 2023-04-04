package com.project.market.domain.item.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

import com.project.market.domain.item.constant.ItemSellStatus;
import com.project.market.domain.item.entity.Item;
import com.project.market.domain.item.repository.ItemRepository;
import com.project.market.domain.member.constant.MemberRole;
import com.project.market.domain.member.constant.MemberType;
import com.project.market.domain.member.entity.Member;

@ExtendWith(MockitoExtension.class)
public class ItemServiceTest {

	@InjectMocks
	private ItemService target;
	
	@Mock
	private ItemRepository itemRepository;
	
	final Member member= Member.builder()
			.email("test@gmail.com")
			.address("����")
			.memberName("ȫ�浿")
			.password("password")
			.memberType(MemberType.EMAIL)
			.role(MemberRole.USER)
			.build();
	
	@Test
	public void ��ǰ����׽�Ʈ_����() throws Exception{
		final Item item = Item.builder()
                .itemName("��ǰ��")
                .itemDetail("��ǰ����")
                .stockNumber(2)
                .member(member)
                .build();
		
		Item result=target.saveItem(item);
		
		assertThat(result).isNull();
	}
	
	@Test
	public void ��ǰ����׽�Ʈ_����() throws Exception{
		final Item item = Item.builder()
                .itemName("��ǰ��")
                .itemDetail("��ǰ����")
                .itemSellStatus(ItemSellStatus.SOLD_OUT)
                .price(300)
                .stockNumber(2)
                .member(member)
                .build();
        doReturn(item).when(itemRepository).save(any(Item.class));

        //when
        Item result = target.saveItem(item);

        //then
        assertThat(result).isEqualTo(item);
	}
}
