package com.technoboost.pet_clinic.app.model;

import com.technoboost.pet_clinic.audit.BaseAudit;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "visits")
public class Visits extends BaseAudit {
    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name = "pets_id",nullable = false)
    private Pets pets;

    @Column(name = "visit_date",nullable = false)
    private LocalDate visitDate;

    @Column(name = "description")
    private String description;
}
