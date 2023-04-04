package com.project.market.domain.item.repository;

import com.project.market.domain.item.constant.ItemSellStatus;
import com.project.market.domain.item.entity.Item;
import com.project.market.domain.member.constant.MemberRole;
import com.project.market.domain.member.constant.MemberType;
import com.project.market.domain.member.entity.Member;
import com.project.market.domain.member.repository.MemberRepository;
import com.project.market.global.config.jpa.AuditConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.DataIntegrityViolationException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
@Import(AuditConfig.class)
class ItemRepositoryTest {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private MemberRepository memberRepository;

    final Member member = Member.builder()
            .email("test@email.com")
            .address("����Ư����")
            .memberName("tester")
            .password("password")
            .memberType(MemberType.EMAIL)
            .role(MemberRole.USER)
            .build();

    @BeforeEach
    public void init() {
        memberRepository.save(member);
    }


    @Test
    public void ItemRepository��_NULL��_�ƴ�() throws Exception {
        //given


        //when

        //then
        assertThat(itemRepository).isNotNull();
    }

    @Test
    public void ��ǰ����׽�Ʈ_����() throws Exception {
        //given
        final Item item = Item.builder()
                .itemName("��ǰ��")
                .itemDetail("��ǰ����")
                .stockNumber(2)
                .member(member)
                .build();
        //when

        //then
        assertThrows(DataIntegrityViolationException.class, () -> itemRepository.save(item));
    }

    @Test
    public void ��ǰ����׽�Ʈ_����() throws Exception {
        //given
        final Item item = Item.builder()
                .itemName("��ǰ��")
                .itemDetail("��ǰ����")
                .itemSellStatus(ItemSellStatus.SOLD_OUT)
                .price(300)
                .stockNumber(2)
                .member(member)
                .build();

        //when
        Item result = itemRepository.save(item);

        //then
        assertThat(result.getItemName()).isEqualTo(item.getItemName());
    }
}
