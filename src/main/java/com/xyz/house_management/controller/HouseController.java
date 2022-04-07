package com.xyz.house_management.controller;

import com.xyz.house_management.api.HouseRequest;
import com.xyz.house_management.api.HouseResource;
import com.xyz.house_management.api.HouseResponse;
import com.xyz.house_management.api.HouseResponseAssembler;
import com.xyz.house_management.persistence.entity.House;
import com.xyz.house_management.persistence.repository.HouseRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequiredArgsConstructor
public class HouseController implements HouseResource {

    private final HouseRepository houseRepository;

    private final HouseResponseAssembler houseResponseAssembler;

    private final ModelMapper modelMapper;

    private final PagedResourcesAssembler<House> pagedResourcesAssembler;

    @Override
    public ResponseEntity<HouseResponse> createHouse(HouseRequest request) {
        House house = new House();
        modelMapper.map(request, house);
        house = houseRepository.saveAndFlush(house);
        Link linkToNewResource = linkTo(methodOn(HouseController.class).findHouseById(house.getId())).withSelfRel();
        return ResponseEntity.created(linkToNewResource.toUri()).build();
    }

    @Override
    public ResponseEntity<HouseResponse> findHouseById(@NonNull UUID id) {
        Optional<House> houseOptional = houseRepository.findById(id);
        return houseOptional.map(house -> ResponseEntity.ok(houseResponseAssembler.toModel(house)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<?> findAllHouses(Integer page, Integer size) {
        if (page == null || size == null) {
            return getHouseResponseEntity();
        }
        return getHouseResponseEntityWithPagination(page, size);
    }

    @Override
    public ResponseEntity<?> deleteHouse(@NonNull UUID id) {
        Optional<House> houseOptional = houseRepository.findById(id);
        if (houseOptional.isPresent()) {
            houseRepository.delete(houseOptional.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    private ResponseEntity<?> getHouseResponseEntity() {
        List<House> houseList = houseRepository.findAll();
        if (CollectionUtils.isEmpty(houseList)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(houseResponseAssembler.toCollectionModel(houseRepository.findAll()));
    }

    private ResponseEntity<?> getHouseResponseEntityWithPagination(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<House> houseListPage = houseRepository.findAll(pageable);
        if (CollectionUtils.isEmpty(houseListPage.getContent())) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pagedResourcesAssembler.toModel(houseListPage, houseResponseAssembler));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
