package com.prathamesh.kartmalldata.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.prathamesh.kartmalldata.model.ProductImages;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    @JsonProperty("id")
    private Long productId;

    @JsonProperty("title")
    private String productTitle;

    @JsonProperty("description")
    private String description;

    @JsonProperty("discount_percentage")
    private Double discountPercentage;


    @JsonProperty("original_price")
    private Double originalPrice;

    @JsonProperty("price")
    private Double price;

    @JsonProperty("brand")
    private String productBrand;

    @JsonProperty("seller_id")
    private Long sellerId;

    @JsonProperty("product_images")
    private List<String> productImages = new ArrayList<>();

    @JsonProperty("category")
    private String category;
}

