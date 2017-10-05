package com.mycompany.myapp.service.dto;

import com.mycompany.myapp.domain.Cart;
import com.mycompany.myapp.domain.Products;
import com.mycompany.myapp.domain.ProductsDescription;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.Instant;

/**
 * A DTO representing a cart record.
 */
public class CartDTO {

    private Long cartId;

    private Long id;

    @Size(min = 1, max = 4)
    private Integer cartItemQuantity;

    @Digits(integer = 10, fraction = 2)
    private BigDecimal cartItemTotalPrice;

    private Long productsId;

    @Size(min = 1, max = 4)
    private Integer productsQuantity;

    @Size(max = 12)
    private String productsModel;

    @Size(max = 64)
    private String productsImage;

    @Digits(integer = 10, fraction = 2)
    private BigDecimal productsPrice;

    private Instant productsDateAdded = null;

    private Instant productsLastModified = null;

    private Instant productsDateAvailable = null;

    @Digits(integer = 5, fraction = 2)
    private BigDecimal productsWeight;

    @NotNull
    private boolean productsStatus = false;

    @NotNull
    @Size(min = 1, max = 5)
    private Integer productsTaxClassId;

    @NotNull
    @Size(min = 1, max = 5)
    private Integer manufacturersId;

    private String productsDescription;

    private String productsName;

    private String productsURL;

    private Integer productsViewed;

    public CartDTO(Cart cart, Products products, ProductsDescription prodDescription){
        this(cart.getCartId(), cart.getId(), cart.getCartItemQuantity(), cart.getCartItemTotalPrice(), products.getProductsId(), products.getProductsQuantity(), products.getProductsModel(), products.getProductsImage(), products.getProductsPrice(),
            products.getProductsDateAdded(), products.getProductsLastModified(), products.getProductsDateAvailable(), products.getProductsWeight(),
            products.isProductsStatus(), products.getProductsTaxClassId(), products.getManufacturersId(), prodDescription.getProductsDescription(), prodDescription.getProductsName(), prodDescription.getProductsURL(),
            prodDescription.getProductsViewed());
    }

    public CartDTO(Long cartId, Long id, Integer cartItemQuantity, BigDecimal cartItemTotalPrice, Long productsId, Integer productsQuantity, String productsModel, String productsImage, BigDecimal productsPrice, Instant productsDateAdded, Instant productsLastModified, Instant productsDateAvailable, BigDecimal productsWeight, boolean productsStatus, Integer productsTaxClassId, Integer manufacturersId, String productsDescription, String productsName, String productsURL, Integer productsViewed) {
        this.cartId = cartId;
        this.id = id;
        this.cartItemQuantity = cartItemQuantity;
        this.cartItemTotalPrice = cartItemTotalPrice;
        this.productsId = productsId;
        this.productsQuantity = productsQuantity;
        this.productsModel = productsModel;
        this.productsImage = productsImage;
        this.productsPrice = productsPrice;
        this.productsDateAdded = productsDateAdded;
        this.productsLastModified = productsLastModified;
        this.productsDateAvailable = productsDateAvailable;
        this.productsWeight = productsWeight;
        this.productsStatus = productsStatus;
        this.productsTaxClassId = productsTaxClassId;
        this.manufacturersId = manufacturersId;
        this.productsDescription = productsDescription;
        this.productsName = productsName;
        this.productsURL = productsURL;
        this.productsViewed = productsViewed;
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCartItemQuantity() {
        return cartItemQuantity;
    }

    public void setCartItemQuantity(Integer cartItemQuantity) {
        this.cartItemQuantity = cartItemQuantity;
    }

    public BigDecimal getCartItemTotalPrice() {
        return cartItemTotalPrice;
    }

    public void setCartItemTotalPrice(BigDecimal cartItemTotalPrice) {
        this.cartItemTotalPrice = cartItemTotalPrice;
    }

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

    public void setProductsModel(String productsModel) {
        this.productsModel = productsModel;
    }

    public String getProductsImage() {
        return productsImage;
    }

    public void setProductsImage(String productsImage) {
        this.productsImage = productsImage;
    }

    public BigDecimal getProductsPrice() {
        return productsPrice;
    }

    public void setProductsPrice(BigDecimal productsPrice) {
        this.productsPrice = productsPrice;
    }

    public Instant getProductsDateAdded() {
        return productsDateAdded;
    }

    public void setProductsDateAdded(Instant productsDateAdded) {
        this.productsDateAdded = productsDateAdded;
    }

    public Instant getProductsLastModified() {
        return productsLastModified;
    }

    public void setProductsLastModified(Instant productsLastModified) {
        this.productsLastModified = productsLastModified;
    }

    public Instant getProductsDateAvailable() {
        return productsDateAvailable;
    }

    public void setProductsDateAvailable(Instant productsDateAvailable) {
        this.productsDateAvailable = productsDateAvailable;
    }

    public BigDecimal getProductsWeight() {
        return productsWeight;
    }

    public void setProductsWeight(BigDecimal productsWeight) {
        this.productsWeight = productsWeight;
    }

    public boolean isProductsStatus() {
        return productsStatus;
    }

    public void setProductsStatus(boolean productsStatus) {
        this.productsStatus = productsStatus;
    }

    public Integer getProductsTaxClassId() {
        return productsTaxClassId;
    }

    public void setProductsTaxClassId(Integer productsTaxClassId) {
        this.productsTaxClassId = productsTaxClassId;
    }

    public Integer getManufacturersId() {
        return manufacturersId;
    }

    public void setManufacturersId(Integer manufacturersId) {
        this.manufacturersId = manufacturersId;
    }


    public String getProductsDescription() {
        return productsDescription;
    }

    public void setProductsDescription(String productsDescription) {
        this.productsDescription = productsDescription;
    }

    public String getProductsName() {
        return productsName;
    }

    public void setProductsName(String productsName) {
        this.productsName = productsName;
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
    public String toString() {
        return "ProductsDTO{" +
            "productsId=" + productsId +
            ", productsQuantity=" + productsQuantity +
            ", productsModel='" + productsModel + '\'' +
            ", productsImage='" + productsImage + '\'' +
            ", productsPrice=" + productsPrice +
            ", productsDateAdded=" + productsDateAdded +
            ", productsLastModified=" + productsLastModified +
            ", productsDateAvailable=" + productsDateAvailable +
            ", productsWeight=" + productsWeight +
            ", productsStatus=" + productsStatus +
            ", productsTaxClassId=" + productsTaxClassId +
            ", manufacturersId=" + manufacturersId +
            ", productsDescription='" + productsDescription + '\'' +
            ", productsName='" + productsName + '\'' +
            ", productsURL='" + productsURL + '\'' +
            ", productsViewed=" + productsViewed +
            '}';
    }

}
