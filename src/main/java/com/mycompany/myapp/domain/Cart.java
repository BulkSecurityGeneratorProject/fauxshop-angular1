package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
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

    @Min(1)
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setProductsId(Long productsId) {
        this.productsId = productsId;
    }

    public void setCartItemQuantity(Integer cartItemQuantity) {
        this.cartItemQuantity = cartItemQuantity;
    }

    public void setCartItemTotalPrice(BigDecimal cartItemTotalPrice) {
        this.cartItemTotalPrice = cartItemTotalPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cart cart = (Cart) o;

        if (id != null ? !id.equals(cart.id) : cart.id != null) return false;
        return productsId != null ? productsId.equals(cart.productsId) : cart.productsId == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (productsId != null ? productsId.hashCode() : 0);
        return result;
    }

}
