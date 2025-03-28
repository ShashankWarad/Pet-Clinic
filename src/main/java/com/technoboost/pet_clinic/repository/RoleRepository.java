package com.technoboost.pet_clinic.repository;

import com.technoboost.pet_clinic.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {

   @Query("select r from Role r where r.name = ?1")
   Optional<Role> findByName(String name);
}
