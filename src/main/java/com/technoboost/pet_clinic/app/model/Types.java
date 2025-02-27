package com.technoboost.pet_clinic.app.model;

import com.technoboost.pet_clinic.audit.BaseAudit;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "_types")
public class Types extends BaseAudit {
    @Column(name = "name",nullable = false)
    private String name;
}
