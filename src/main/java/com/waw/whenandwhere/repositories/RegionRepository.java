package com.waw.whenandwhere.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.waw.whenandwhere.entities.Region;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long>{

}
