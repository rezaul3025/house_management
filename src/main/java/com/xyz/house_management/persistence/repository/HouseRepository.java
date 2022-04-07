package com.xyz.house_management.persistence.repository;

import com.xyz.house_management.persistence.entity.House;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface HouseRepository extends JpaRepository<House, UUID> {

}
