package com.xyz.house_management.api;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.util.UUID;

@Data
@Relation(collectionRelation = "houses", itemRelation = "house")
public class HouseResponse extends RepresentationModel<HouseResponse> {
    private UUID id;

    private String title;

    private String description;

    private String location;

    private Integer constructionYear;

    private Integer numberOfFloor;

    private Double totalSizeInSqm;
}
