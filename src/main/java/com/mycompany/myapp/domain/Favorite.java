package com.mycompany.myapp.domain;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A favorite record.
 */
@Entity
@Table(name = "favorite")
public class Favorite extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long favoriteId;

    @JoinColumn(name = "id")
    private Long id;

    @JoinColumn(name = "productsId")
    private Long productsId;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getFavoriteId() {
        return favoriteId;
    }

    public Long getId() {
        return id;
    }

    public Long getProductsId() {
        return productsId;
    }

    public void setFavoriteId(Long favoriteId) { this.favoriteId = favoriteId; }

    public void setId(Long id) {
        this.id = id;
    }

    public void setProductsId(Long productsId) {
        this.productsId = productsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Favorite favorite = (Favorite) o;

        if (id != null ? !id.equals(favorite.id) : favorite.id != null) return false;
        return productsId != null ? productsId.equals(favorite.productsId) : favorite.productsId == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (productsId != null ? productsId.hashCode() : 0);
        return result;
    }

}
