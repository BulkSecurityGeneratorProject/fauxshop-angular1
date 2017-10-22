package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mycompany.myapp.service.dto.OrderDTO;
import org.hibernate.annotations.*;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.hibernate.annotations.Cache;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

/**
 * An order record
 */
@Entity
@Table(name = "orders")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Orders extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @JoinColumn(name = "id")
    private Long id;

    @Size(max = 64)
    @Column(name = "customer_name", length = 64)
    private String customerName;

    @Size(max = 64)
    @Column(name = "customer_address_1", length = 64)
    private String customerAddress1;

    @Size(max = 64)
    @Column(name = "customer_address_2", length = 64)
    private String customerAddress2;

    @Size(max = 32)
    @Column(name = "customer_suburb", length = 32)
    private String customerSuburb;

    @Size(max = 32)
    @Column(name = "customer_city", length = 32)
    private String customerCity;

    @Size(max = 10)
    @Column(name = "customer_postcode", length = 10)
    private String customerPostcode;

    @Size(max = 32)
    @Column(name = "customer_state", length = 32)
    private String customerState;

    @Size(max = 32)
    @Column(name = "customer_country", length = 32)
    private String customerCountry;

    @Size(max = 32)
    @Column(name = "customer_telephone", length = 32)
    private String customerTelephone;

    @Size(max = 96)
    @Column(name = "customer_email", length = 96)
    private String customerEmail;

    @Size(max = 64)
    @Column(name = "delivery_name", length = 64)
    private String deliveryName;

    @Size(max = 64)
    @Column(name = "delivery_address_1", length = 64)
    private String deliveryAddress1;

    @Size(max = 64)
    @Column(name = "delivery_address_2", length = 64)
    private String deliveryAddress2;

    @Size(max = 32)
    @Column(name = "delivery_city", length = 32)
    private String deliveryCity;

    @Size(max = 10)
    @Column(name = "delivery_postcode", length = 10)
    private String deliveryPostcode;

    @Size(max = 32)
    @Column(name = "delivery_state", length = 32)
    private String deliveryState;

    @Size(max = 32)
    @Column(name = "delivery_country", length = 32)
    private String deliveryCountry;

    @Size(max = 12)
    @Column(name = "payment_method", length = 12)
    private String paymentMethod;

    @Size(max = 64)
    @Column(name = "stripe_charge_id", length = 64)
    private String stripeChargeId;

    @LastModifiedDate
    @Column(name = "date_purchased")
    @JsonIgnore
    private Instant datePurchased;

    @Digits(integer = 10, fraction = 2)
    @Column(name = "shipping_cost", length = 64)
    private BigDecimal shippingCost;

    @Size(max = 32)
    @Column(name = "shipping_method", length = 32)
    private String shippingMethod;

    @Size(max = 32)
    @Column(name = "order_status", length = 32)
    private String orderStatus;

    @LastModifiedDate
    @Column(name = "order_date_finished")
    @JsonIgnore
    private Instant orderDateFinished;

    @Size(max = 5000)
    @Column(name = "comments", length = 5000)
    private String comments;

    public Orders() {
        // empty constructor
    }

    public Orders(OrderDTO orderDTO) {
        this.deliveryAddress1 = orderDTO.getDeliveryAddress1();
        this.deliveryAddress2 = orderDTO.getDeliveryAddress2();
        this.deliveryCity = orderDTO.getDeliveryCity();
        this.deliveryCountry = orderDTO.getDeliveryCountry();
        this.deliveryName = orderDTO.getDeliveryName();
        this.deliveryPostcode = orderDTO.getDeliveryPostcode();
        this.deliveryState = orderDTO.getDeliveryState();
        this.id = orderDTO.getId();
        this.orderId = orderDTO.getOrderId();
        this.shippingCost = orderDTO.getShippingCost();
        this.stripeChargeId = orderDTO.getStripeChargeId();
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress1() {
        return customerAddress1;
    }

    public void setCustomerAddress1(String customerAddress1) {
        this.customerAddress1 = customerAddress1;
    }

    public String getCustomerAddress2() {
        return customerAddress2;
    }

    public void setCustomerAddress2(String customerAddress2) {
        this.customerAddress2 = customerAddress2;
    }

    public String getCustomerSuburb() {
        return customerSuburb;
    }

    public void setCustomerSuburb(String customerSuburb) {
        this.customerSuburb = customerSuburb;
    }

    public String getCustomerCity() {
        return customerCity;
    }

    public void setCustomerCity(String customerCity) {
        this.customerCity = customerCity;
    }

    public String getCustomerPostcode() {
        return customerPostcode;
    }

    public void setCustomerPostcode(String customerPostcode) {
        this.customerPostcode = customerPostcode;
    }

    public String getCustomerState() {
        return customerState;
    }

    public void setCustomerState(String customerState) {
        this.customerState = customerState;
    }

    public String getCustomerCountry() {
        return customerCountry;
    }

    public void setCustomerCountry(String customerCountry) {
        this.customerCountry = customerCountry;
    }

    public String getCustomerTelephone() {
        return customerTelephone;
    }

    public void setCustomerTelephone(String customerTelephone) {
        this.customerTelephone = customerTelephone;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getDeliveryName() {
        return deliveryName;
    }

    public void setDeliveryName(String deliveryName) {
        this.deliveryName = deliveryName;
    }

    public String getDeliveryAddress1() {
        return deliveryAddress1;
    }

    public void setDeliveryAddress1(String deliveryAddress1) {
        this.deliveryAddress1 = deliveryAddress1;
    }

    public String getDeliveryAddress2() {
        return deliveryAddress2;
    }

    public void setDeliveryAddress2(String deliveryAddress2) {
        this.deliveryAddress2 = deliveryAddress2;
    }

    public String getDeliveryCity() {
        return deliveryCity;
    }

    public void setDeliveryCity(String deliveryCity) {
        this.deliveryCity = deliveryCity;
    }

    public String getDeliveryPostcode() {
        return deliveryPostcode;
    }

    public void setDeliveryPostcode(String deliveryPostcode) {
        this.deliveryPostcode = deliveryPostcode;
    }

    public String getDeliveryState() {
        return deliveryState;
    }

    public void setDeliveryState(String deliveryState) {
        this.deliveryState = deliveryState;
    }

    public String getDeliveryCountry() {
        return deliveryCountry;
    }

    public void setDeliveryCountry(String deliveryCountry) {
        this.deliveryCountry = deliveryCountry;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getStripeChargeId() {
        return stripeChargeId;
    }

    public void setStripeChargeId(String ccType) {
        this.stripeChargeId = stripeChargeId;
    }

    public Instant getDatePurchased() {
        return datePurchased;
    }

    public void setDatePurchased(Instant datePurchased) {
        this.datePurchased = datePurchased;
    }

    public BigDecimal getShippingCost() {
        return shippingCost;
    }

    public void setShippingCost(BigDecimal shippingCost) {
        this.shippingCost = shippingCost;
    }

    public String getShippingMethod() {
        return shippingMethod;
    }

    public void setShippingMethod(String shippingMethod) {
        this.shippingMethod = shippingMethod;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Instant getOrderDateFinished() {
        return orderDateFinished;
    }

    public void setOrderDateFinished(Instant orderDateFinished) {
        this.orderDateFinished = orderDateFinished;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Orders order = (Orders) o;

        return orderId != null ? orderId.equals(order.orderId) : order.orderId == null;
    }

    @Override
    public int hashCode() {
        return orderId != null ? orderId.hashCode() : 0;
    }

}
