package com.prathamesh.kartmalldata.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
@Getter
@Setter
@ToString
@Table(name = "product_info")
public class ProductInfo implements Serializable {
    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @JsonProperty("title")
    @Column(name = "product_title")
    private String productTitle;

    @Column(name = "description")
    private String description;

    @Column(name = "discount_percentage")
    private Double discountPercentage;


    @Column(name = "original_price")
    private Double originalPrice;

    @Column(name = "price", nullable = false)
    private Double price;

    @JsonProperty("brand")
    @Column(name = "product_brand")
    private String productBrand;

    @Column(name = "seller_id")
    private Long sellerId;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<ProductImages> productImages = new ArrayList<>();

    @Column(name = "category")
    private String category;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ProductInfo that = (ProductInfo) o;
        return productId != null && Objects.equals(productId, that.productId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
