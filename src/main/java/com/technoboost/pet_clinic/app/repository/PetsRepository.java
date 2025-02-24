package com.technoboost.pet_clinic.app.repository;

import com.technoboost.pet_clinic.app.model.Pets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PetsRepository extends JpaRepository<Pets,Long> {
    @Query("select p from Pets p where p.active = true and p.deleted = false")
    List<Pets> findByActiveTrueAndDeletedFalse();

    @Query("select p from Pets p where p.id = ?1 and p.active = true and p.deleted = false")
    Optional<Pets> findByIdAndActiveTrueAndDeletedFalse(Long id);
}
