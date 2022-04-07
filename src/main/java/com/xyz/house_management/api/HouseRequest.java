package com.xyz.house_management.api;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class HouseRequest {
    @NotBlank(message = "Title cannot be empty or null")
    private String title;

    @NotBlank(message = "Description cannot be empty or null")
    private String description;

    @NotBlank(message = "Location cannot be empty or null")
    private String location;

    @NotNull(message = "Construction year cannot be null")
    private Integer constructionYear;

    @NotNull(message = "Number of  floor cannot be null")
    private Integer numberOfFloor;

    @NotNull(message = "Total size in squire cannot be null")
    private Double totalSizeInSqm;
}
