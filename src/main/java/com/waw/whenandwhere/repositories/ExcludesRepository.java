package com.waw.whenandwhere.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.waw.whenandwhere.entities.Excludes;
@Repository
public interface ExcludesRepository extends JpaRepository<Excludes, Long>{

}
