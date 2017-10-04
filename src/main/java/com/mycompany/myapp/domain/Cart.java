package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

/**
 * A cart record.
 */
@Entity
@Table(name = "cart")
public class Cart extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;

    @JoinColumn(name = "id")
    private Long id;

    @JoinColumn(name = "productsId")
    private Long productsId;

    @Size(min = 1, max = 4)
    @Column(name = "cart_item_quantity", length = 4, nullable = false)
    private Integer cartItemQuantity;

    @Digits(integer = 10, fraction = 2)
    @Column(name = "cart_item_total_price", length = 64)
    private BigDecimal cartItemTotalPrice;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getCartId() {
        return cartId;
    }

    public Long getId() {
        return id;
    }

    public Long getProductsId() {
        return productsId;
    }

    public Integer getCartItemQuantity() {
        return cartItemQuantity;
    }

    public BigDecimal getCartItemTotalPrice() {
        return cartItemTotalPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cart cart = (Cart) o;

        return cartId != null ? cartId.equals(cart.cartId) : cart.cartId == null;
    }

    @Override
    public int hashCode() {
        return cartId != null ? cartId.hashCode() : 0;
    }
}
