package com.technoboost.pet_clinic.app.model;

import com.technoboost.pet_clinic.audit.BaseAudit;
import com.technoboost.pet_clinic.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "visits")
public class Visits extends BaseAudit {
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "pets_id", nullable = false)
    private Pets pets;

    @Column(name = "visit_date", nullable = false)
    private LocalDateTime visitDate;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "vet_id")
    private Vets vet;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;
}
