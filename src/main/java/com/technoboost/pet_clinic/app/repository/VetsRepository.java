package com.technoboost.pet_clinic.app.repository;

import com.technoboost.pet_clinic.app.model.Vets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VetsRepository extends JpaRepository<Vets ,Long> {
    @Query("select v from Vets v where v.active = true and v.deleted = false")
    List<Vets> findAllVetsAndActiveTrueAndDeletedFalse();
}
