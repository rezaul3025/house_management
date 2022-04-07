package com.xyz.house_management.api;

import com.xyz.house_management.controller.HouseController;
import com.xyz.house_management.persistence.entity.House;
import lombok.NonNull;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class HouseResponseAssembler implements RepresentationModelAssembler<House, HouseResponse> {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public HouseResponse toModel(@NonNull House entity) {
        val houseResponse = new HouseResponse();
        modelMapper.map(entity, houseResponse);
        Link linkToHouseResource = linkTo(methodOn(HouseController.class).findHouseById(houseResponse.getId())).withSelfRel();
        houseResponse.add(linkToHouseResource);
        return houseResponse;
    }
}
