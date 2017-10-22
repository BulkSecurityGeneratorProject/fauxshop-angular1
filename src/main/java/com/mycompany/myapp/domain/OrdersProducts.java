package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

/**
 * Product information for an order record.
 */
@Entity
@Table(name = "orders_products")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class OrdersProducts extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ordersProductsId;

    @JoinColumn(name = "orderId")
    private Long orderId;

    @JoinColumn(name = "productsId")
    private Long productsId;

    @Size(max = 64)
    @Column(name = "products_name", length = 64)
    private String productsName;

    @Digits(integer = 10, fraction = 2)
    @Column(name = "products_price", length = 64)
    private BigDecimal productsPrice;

    @Digits(integer = 10, fraction = 2)
    @Column(name = "final_price")
    private BigDecimal finalPrice;

    @Digits(integer = 9, fraction = 4)
    @Column(name = "products_tax")
    private BigDecimal productsTax;

    @Min(1)
    @Column(length = 4, nullable = false)
    private Integer productsQuantity;

    public OrdersProducts(Cart cartInvoice) {
        this.productsId = cartInvoice.getProductsId();
        // products name
        // products price
        this.finalPrice = cartInvoice.getCartItemTotalPrice();
        // products tax
        this.productsQuantity = cartInvoice.getCartItemQuantity();
    }

    public Long getOrdersProductsId() {
        return ordersProductsId;
    }

    public void setOrdersProductsId(Long ordersProductsId) {
        this.ordersProductsId = ordersProductsId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getProductsId() {
        return productsId;
    }

    public void setProductsId(Long productsId) {
        this.productsId = productsId;
    }

    public String getProductsName() {
        return productsName;
    }

    public void setProductsName(String productsName) {
        this.productsName = productsName;
    }

    public BigDecimal getProductsPrice() {
        return productsPrice;
    }

    public void setProductsPrice(BigDecimal productsPrice) {
        this.productsPrice = productsPrice;
    }

    public BigDecimal getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(BigDecimal finalPrice) {
        this.finalPrice = finalPrice;
    }

    public BigDecimal getProductsTax() {
        return productsTax;
    }

    public void setProductsTax(BigDecimal productsTax) {
        this.productsTax = productsTax;
    }

    public Integer getProductsQuantity() {
        return productsQuantity;
    }

    public void setProductsQuantity(Integer productsQuantity) {
        this.productsQuantity = productsQuantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrdersProducts that = (OrdersProducts) o;

        return ordersProductsId != null ? ordersProductsId.equals(that.ordersProductsId) : that.ordersProductsId == null;
    }

    @Override
    public int hashCode() {
        return ordersProductsId != null ? ordersProductsId.hashCode() : 0;
    }
}
