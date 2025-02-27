package com.technoboost.pet_clinic.repository;

import com.technoboost.pet_clinic.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT COUNT(u) > 0 FROM User u WHERE u.username = :username AND u.enabled = TRUE")
    boolean existsByUsernameAndEnabledTrue(@Param("username") String username);


    @Query("SELECT COUNT(u) > 0 FROM User u WHERE u.mobileNumber = :mobileNumber AND u.enabled = TRUE")
    boolean existsByMobileNumberAndEnabledTrue(@Param("mobileNumber") String mobileNumber);

    @Query("select u from User u where u.username = ?1")
    Optional<User> findByUsername(String username);
}
