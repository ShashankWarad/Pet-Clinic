package com.technoboost.pet_clinic.app.repository;

import com.technoboost.pet_clinic.app.dto.TypesDto;
import com.technoboost.pet_clinic.app.model.Types;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TypesRepository extends JpaRepository<Types,Long> {
    @Query("SELECT COUNT(t) > 0 FROM Types t WHERE t.name = :name AND t.active = true AND t.deleted = false")
    boolean existsNameInActiveTrueAndDeletedFalse(@Param("name") String name);

    @Query("select t from Types t where t.id = ?1 and t.active = true and t.deleted = false")
    Optional<Types> findByIdAndActiveTrueAndDeletedFalse(Long id);

    @Query("SELECT com.technoboost.pet_clinic.app.dto.TypesDto(t.id, t.name) FROM Types t WHERE t.active = true AND t.deleted = false")
    List<TypesDto> findAllAndActiveTrueAndDeletedFalse();
}
