package jpabook.jpashop.domain.item;

import jpabook.jpashop.domain.Category;
import jpabook.jpashop.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn( name= "dtype" )
@Getter @Setter
public abstract class Item {

    @Id
    @GeneratedValue
    @Column(name="item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    //==== 비즈니스 로직 ====//
    /**
     *
     * 비즈니스 서비스 쪽에서 Setter를 통해 Entity를 조작하기보단,
     * Entity쪽에서 해당 변수를 객체 내에서 비즈니스 로직을 통해 조작하는것이
     * 더욱 객체지향적으로 설계하기에 이렇게 비즈니스 로직을 Entity에 넣어놓는것     * */
    /**
     *
     * stock 증가
     * **/
    public void addStock(int quantity){
        this.stockQuantity += quantity;
    }

    /**
     *
     * stock 감소
     */
    public void removeStock(int quantity){
        int restStock = this.stockQuantity - quantity;
        if ( restStock < 0){
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity = restStock;

    }

}
