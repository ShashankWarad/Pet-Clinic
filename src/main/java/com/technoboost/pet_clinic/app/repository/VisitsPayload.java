package com.technoboost.pet_clinic.app.repository;

import com.technoboost.pet_clinic.app.model.Visits;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitsPayload extends JpaRepository<Visits,Long> {
}
