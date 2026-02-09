package com.zosh.payload.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zosh.domain.storeStatus;
import com.zosh.model.StoreContact;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StoreDto {


    private Long id;


    private String brand;


    private UserDto storeAdmin;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private String description;

    private String storeType;

    private storeStatus status;

    @JsonProperty("contact")
    private StoreContact contact;

}
