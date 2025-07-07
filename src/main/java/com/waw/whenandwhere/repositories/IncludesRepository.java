package com.waw.whenandwhere.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.waw.whenandwhere.entities.Includes;

@Repository
public interface IncludesRepository extends JpaRepository<Includes, Long> {

}
