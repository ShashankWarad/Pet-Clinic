package com.technoboost.pet_clinic.app.repository;

import com.technoboost.pet_clinic.app.model.Pets;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetsRepository extends JpaRepository<Pets,Long> {
}
