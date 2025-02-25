package com.technoboost.pet_clinic.app.repository;

import com.technoboost.pet_clinic.app.model.Visits;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface VisitsRepository extends JpaRepository<Visits,Long> {


    @Query("select v from Visits v where v.id = :id AND v.owner.id = :ownerId AND v.active = true AND v.deleted = false")
    Optional<Visits> findByIdActiveTrueAndDeletedFalse(Long ownerId,Long id);

    @Query("select v from Visits v where v.owner.id = ?1 AND v.active = true AND v.deleted = false ORDER By v.canceled , v.visitDate desc")
    List<Visits> findAllByUserIdActiveTrueAndDeletedFalse(Long ownerId);
}
