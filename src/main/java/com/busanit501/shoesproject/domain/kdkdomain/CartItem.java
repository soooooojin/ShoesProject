package com.busanit501.shoesproject.domain.kdkdomain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@Table(name="cartItem")
public class CartItem extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "cartItemId")
    private Long cartItemId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="cartId")
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "itemId")
    private Item item;

    private int count;

    public static CartItem createCartItem(Cart cart, Item item, int count) {
        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setItem(item);
        cartItem.setCount(count);
        return cartItem;
    }

    public void addCount(int count){
        this.count += count;
    }

    public void updateCount(int count){
        this.count = count;
    }

}