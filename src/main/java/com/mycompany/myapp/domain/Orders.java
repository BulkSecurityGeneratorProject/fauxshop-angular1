package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Column(name = "customer_street_address", length = 64)
    private String customerStreetAddress;

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
    @Column(name = "delivery_street_address", length = 64)
    private String deliveryStreetAddress;

    @Size(max = 32)
    @Column(name = "delivery_suburb", length = 32)
    private String deliverySuburb;

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

    @Size(max = 20)
    @Column(name = "cc_type", length = 20)
    private String ccType;

    @Size(max = 64)
    @Column(name = "cc_owner", length = 64)
    private String ccOwner;

    @Size(max = 32)
    @Column(name = "cc_number", length = 32)
    private String ccNumber;

    @Size(max = 4)
    @Column(name = "cc_expires", length = 4)
    private String ccExpires;

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
    private Instant order_date_finished;

    @Size(max = 5000)
    @Column(name = "comments", length = 5000)
    private String comments;

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

    public String getCustomerStreetAddress() {
        return customerStreetAddress;
    }

    public void setCustomerStreetAddress(String customerStreetAddress) {
        this.customerStreetAddress = customerStreetAddress;
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

    public String getDeliveryStreetAddress() {
        return deliveryStreetAddress;
    }

    public void setDeliveryStreetAddress(String deliveryStreetAddress) {
        this.deliveryStreetAddress = deliveryStreetAddress;
    }

    public String getDeliverySuburb() {
        return deliverySuburb;
    }

    public void setDeliverySuburb(String deliverySuburb) {
        this.deliverySuburb = deliverySuburb;
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

    public String getCcType() {
        return ccType;
    }

    public void setCcType(String ccType) {
        this.ccType = ccType;
    }

    public String getCcOwner() {
        return ccOwner;
    }

    public void setCcOwner(String ccOwner) {
        this.ccOwner = ccOwner;
    }

    public String getCcNumber() {
        return ccNumber;
    }

    public void setCcNumber(String ccNumber) {
        this.ccNumber = ccNumber;
    }

    public String getCcExpires() {
        return ccExpires;
    }

    public void setCcExpires(String ccExpires) {
        this.ccExpires = ccExpires;
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

    public Instant getOrder_date_finished() {
        return order_date_finished;
    }

    public void setOrder_date_finished(Instant order_date_finished) {
        this.order_date_finished = order_date_finished;
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
