package com.technoboost.pet_clinic.app.model;

import com.technoboost.pet_clinic.audit.BaseAudit;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "vets")
@Getter
@Setter
public class Vets extends BaseAudit {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "vet_specialties", joinColumns = @JoinColumn(name = "vet_id"),
            inverseJoinColumns = @JoinColumn(name = "specialties_id"))
    private List<Specialties> specialties;

}
