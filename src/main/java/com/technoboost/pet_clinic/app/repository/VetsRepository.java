package com.technoboost.pet_clinic.app.repository;

import com.technoboost.pet_clinic.app.model.Vets;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VetsRepository extends JpaRepository<Vets ,Long> {
}
