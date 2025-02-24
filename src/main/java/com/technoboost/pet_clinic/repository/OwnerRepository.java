package com.technoboost.pet_clinic.repository;

import com.technoboost.pet_clinic.app.Entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OwnerRepository extends JpaRepository<Owner, Long> {

    Optional<Owner> findByEmail(String email);
}
