package com.waw.whenandwhere.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.waw.whenandwhere.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{

}
