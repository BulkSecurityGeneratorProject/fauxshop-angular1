package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mycompany.myapp.config.Constants;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;

/**
 * A user.
 */
@Entity
@Table(name = "products")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Products extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productsId;

    @Min(1)
    @Column(length = 4, nullable = false)
    private Integer productsQuantity;

    @Size(max = 12)
    @Column(name = "products_model", length = 12)
    private String productsModel;

    @Size(max = 64)
    @Column(name = "products_image", length = 64)
    private String productsImage;

    @Digits(integer = 10, fraction = 2)
    @Column(name = "products_price", length = 64)
    private BigDecimal productsPrice;

    @Column(name = "products_date_added")
    private Instant productsDateAdded = null;

    @Column(name = "products_last_modified")
    private Instant productsLastModified = null;

    @Column(name = "products_date_available")
    private Instant productsDateAvailable = null;

    @Digits(integer = 5, fraction = 2)
    @Column(name = "products_weight", length = 64)
    private BigDecimal productsWeight;

    @NotNull
    @Column(name = "products_status", nullable = false)
    private boolean productsStatus = false;

    @NotNull
    @Min(1)
    @Column(name = "products_tax_class_id", length = 4, nullable = false)
    private Integer productsTaxClassId;

    @NotNull
    @Min(1)
    @Column(name = "manufacturers_id", length = 4, nullable = false)
    private Integer manufacturersId;

    public Long getProductsId() {
        return productsId;
    }

    public void setProductsId(Long productsId) {
        this.productsId = productsId;
    }

    public Integer getProductsQuantity() {
        return productsQuantity;
    }

    public void setProductsQuantity(Integer productsQuantity) {
        this.productsQuantity = productsQuantity;
    }

    public String getProductsModel() {
        return productsModel;
    }

    public String getProductsImage() {
        return productsImage;
    }

    public BigDecimal getProductsPrice() {
        return productsPrice;
    }

    public void setProductsPrice(BigDecimal productsPrice) { this.productsPrice = productsPrice; }

    public Instant getProductsDateAdded() {
        return productsDateAdded;
    }

    public Instant getProductsLastModified() {
        return productsLastModified;
    }

    public Instant getProductsDateAvailable() {
        return productsDateAvailable;
    }

    public BigDecimal getProductsWeight() {
        return productsWeight;
    }

    public boolean isProductsStatus() {
        return productsStatus;
    }

    public Integer getProductsTaxClassId() {
        return productsTaxClassId;
    }

    public Integer getManufacturersId() {
        return manufacturersId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Products user = (Products) o;
        return !(user.getProductsId() == null || getProductsId() == null) && Objects.equals(getProductsId(), user.getProductsId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getProductsId());
    }
}
