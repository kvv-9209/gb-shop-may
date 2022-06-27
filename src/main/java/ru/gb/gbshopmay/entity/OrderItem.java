package ru.gb.gbshopmay.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.gb.gbshopmay.entity.common.BaseEntity;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @author Artem Kropotov
 * created at 22.06.2022
 **/
@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "order_item")
public class OrderItem extends BaseEntity {

    @Column(name = "quantity")
    private Short quantity;

    @Column(name = "item_price")
    private BigDecimal itemPrice;

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Builder
    public OrderItem(Long id, Short quantity, BigDecimal itemPrice, BigDecimal totalPrice, Product product, Order order) {
        super(id);
        this.quantity = quantity;
        this.itemPrice = itemPrice;
        this.totalPrice = totalPrice;
        this.product = product;
        this.order = order;
    }
}
