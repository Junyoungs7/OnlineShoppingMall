package com.SesinMall.repository;

import com.SesinMall.constant.ItemCategory;
import com.SesinMall.constant.SellStatus;
import com.SesinMall.dto.ItemFormDto;
import com.SesinMall.entity.Item;
import com.SesinMall.entity.QItem;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class ItemRepositoryTest {

    @PersistenceContext
    EntityManager em;

    @Autowired
    ItemRepository itemRepository;

    @Test
    @DisplayName("상품 저장 테스트")
    public void saveItem(){
        Item item = new Item();
        item.setItemNm("테스트 상품");
        item.setPrice(10000);
        item.setItemCategory(ItemCategory.ONE);
        item.setItemDetail("테스트 상품 상세 설명");
        item.setSellStatus(SellStatus.SELL);
        item.setStockNumber(100);

        Item savedItem = itemRepository.save(item);
        assertEquals(savedItem.getItemNm(), item.getItemNm());
    }

    public void createItemList(){
        for(int i = 1; i <= 10; i++){
            Item item = new Item();
            item.setItemNm("테스트 상품"+i);
            item.setPrice(10000+i);
            item.setItemCategory(ItemCategory.ONE);
            item.setItemDetail("테스트 상품 상세 설명"+i);
            item.setSellStatus(SellStatus.SELL);
            item.setStockNumber(100+i);
            itemRepository.save(item);
        }
    }

    @Test
    @DisplayName("상품 조회 테스트")
    public void findByItemNmTest(){
        this.createItemList();
        List<Item> itemList = itemRepository.findByItemNm("테스트 상품3");

        for(Item item : itemList){
            System.out.println(item.toString());
        }
    }

    @Test
    @DisplayName("Querydsl 테스트")
    public void findByItemDetail(){
        this.createItemList();
        List<Item> itemList = itemRepository.findByItemDetail("테스트 상품 상세 설명");
        for(Item item : itemList){
            System.out.println(item.toString());
        }
    }

    @Test
    @DisplayName("Querydsl 조회 테스트1")
    public void queryDslTest(){
        this.createItemList();
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QItem qItem = QItem.item;
        JPAQuery<Item> query = queryFactory.selectFrom(qItem)
                .where(qItem.sellStatus.eq(SellStatus.SELL))
                .where(qItem.itemDetail.like("%"+"테스트 상품 상세 설명"+"%"))
                .orderBy(qItem.price.desc());

        List<Item> itemList = query.fetch();
        for(Item item : itemList)
            System.out.println(item.toString());
    }


}

