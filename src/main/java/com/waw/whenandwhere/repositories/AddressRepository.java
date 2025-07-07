package com.waw.whenandwhere.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.waw.whenandwhere.entities.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long>{

}
