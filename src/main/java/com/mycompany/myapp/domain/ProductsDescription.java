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
 * A user.
 */
@Entity
@Table(name = "products_description")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ProductsDescription extends AbstractAuditingEntity implements Serializable {

    @Id
    @JoinColumn(name = "productsId")
    private Long productsId;

    @Size(max = 64)
    @Column(name = "products_name", length = 64)
    private String productsName;

    @Column(name = "products_description")
    private String productsDescription;

    @Size(max = 255)
    @Column(name = "products_url", length = 255)
    private String productsURL;

    @Size(min = 1, max = 5)
    @Column(name = "products_viewed", length = 4, nullable = false)
    private Integer productsViewed;

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

    public String getProductsDescription() {
        return productsDescription;
    }

    public void setProductsDescription(String productsDescription) {
        this.productsDescription = productsDescription;
    }

    public String getProductsURL() {
        return productsURL;
    }

    public void setProductsURL(String productsURL) {
        this.productsURL = productsURL;
    }

    public Integer getProductsViewed() {
        return productsViewed;
    }

    public void setProductsViewed(Integer productsViewed) {
        this.productsViewed = productsViewed;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProductsDescription user = (ProductsDescription) o;
        return !(user.getProductsId() == null || getProductsId() == null) && Objects.equals(getProductsId(), user.getProductsId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getProductsId());
    }
}
