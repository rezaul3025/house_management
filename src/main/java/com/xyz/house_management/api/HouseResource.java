package com.xyz.house_management.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RequestMapping(value = "/api/v1")
public interface HouseResource {
    @Operation(summary = "Create house")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Create house",
                    content = @Content)})
    @PostMapping(value = "/houses", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> createHouse(@Valid @RequestBody HouseRequest request);

    @Operation(summary = "Get a house by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the house",
                    content = {@Content(mediaType = MediaTypes.HAL_JSON_VALUE,
                            schema = @Schema(implementation = HouseResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "House not found",
                    content = @Content)})
    @GetMapping(value = "/houses/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    ResponseEntity<HouseResponse> findHouseById(@PathVariable UUID id);

    @Operation(summary = "Get all houses")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the houses",
                    content = {@Content(mediaType = MediaTypes.HAL_JSON_VALUE,
                            schema = @Schema(implementation = HouseResponseCollection.class))}),
            @ApiResponse(responseCode = "404", description = "House not found",
                    content = @Content)})
    @GetMapping(value = "/houses", produces = MediaTypes.HAL_JSON_VALUE)
    ResponseEntity<?> findAllHouses(@RequestParam(required = false) Integer page,
                                    @RequestParam(required = false) Integer size);

    @Operation(summary = "Delete house")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Delete house",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "House not found",
                    content = @Content)})
    @DeleteMapping(value = "/houses/{id}")
    ResponseEntity<?> deleteHouse(@PathVariable UUID id);
}
