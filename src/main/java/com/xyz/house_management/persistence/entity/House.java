package com.xyz.house_management.persistence.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Data
@Entity
public class House {

    @Id
    @GeneratedValue
    private UUID id;

    private String title;

    private String description;

    private String location;

    private Integer constructionYear;

    private Integer numberOfFloor;

    private Double totalSizeInSqm;
}
