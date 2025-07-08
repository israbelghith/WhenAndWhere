package com.waw.whenandwhere.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.waw.whenandwhere.entities.Images;

@Repository
public interface ImagesRepository extends JpaRepository<Images, Long> {

    List<Images> findByActivityId(Long activityId);

}
