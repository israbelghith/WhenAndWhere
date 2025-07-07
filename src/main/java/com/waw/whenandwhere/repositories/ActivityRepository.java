package com.waw.whenandwhere.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.waw.whenandwhere.entities.Activity;


@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long>{

}
