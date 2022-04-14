package jpabook.jpashop.Stream;

import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderItem;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class StreamTest {

    @Test
    public void 스트림_테스트() throws Exception {
        List<OrderItem> orderItemList = new ArrayList<OrderItem>();

        //given
        OrderItem orderItem = new OrderItem();
        Item item1 = new Book();
        item1.setId(1L);
        item1.setName("책1");
        orderItem.setId(1L);
        orderItem.setItem(item1);
        orderItem.setCount(5);
        orderItem.setOrderPrice(1000);

        OrderItem orderItem2 = new OrderItem();
        Item item2 = new Book();
        item2.setId(2L);
        item2.setName("책2");
        orderItem2.setId(1L);
        orderItem2.setItem(item1);
        orderItem2.setCount(5);
        orderItem2.setOrderPrice(1000);

        orderItemList.add(orderItem);
        orderItemList.add(orderItem2);

        //when
        orderItemList
                .stream()
                .map(i -> i.getItem())
                //.map(OrderItem::getItem)  위에 람다랑 이거랑 같은로직
                .map(Item::getName)
                .forEach(System.out::println);


        //then
        //Assertions.assertThat(sum).isEqualTo(10000);

    }
}
