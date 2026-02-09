package com.zosh.payload.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("sku")
    private String sku;

    @JsonProperty("description")
    private String description;

    @JsonProperty("mrp")
    private Double mrp;

    @JsonProperty("sellingPrice")
    private Double sellingPrice;

    @JsonProperty("brand")
    private String brand;

    @JsonProperty("image")
    private String image;

    @JsonProperty("category")
    private CategoryDto category;

    @JsonProperty("categoryId")
    private Long categoryId;

    @JsonProperty("storeId")
    private Long storeId;

    @JsonProperty("createdAt")
    private LocalDateTime createdAt;

    @JsonProperty("updatedAt")
    private LocalDateTime updatedAt;
}