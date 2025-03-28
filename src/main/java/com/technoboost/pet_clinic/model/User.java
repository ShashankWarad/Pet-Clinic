package com.technoboost.pet_clinic.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", unique = true, updatable = false, nullable = false)
    private Long id;

    @Column(nullable = false, name = "name")
    private String name;

    @Column(name = "username", nullable = false, unique = true, updatable = false)
    private String username;

    @Column(name = "mobile_number", nullable = false, unique = true, updatable = false)
    private String mobileNumber;

    @Column(nullable = false, name = "password")
    private String password;

    @Column(nullable = false, name = "account_non_expired")
    private boolean accountNonExpired = true;

    @Column(nullable = false, name = "address")
    private String address;

    @Column(nullable = false, name = "city")
    private String city;

    @Column(nullable = false, name = "account_non_locked")
    private boolean accountNonLocked = true;

    @Column(nullable = false, name = "credentials_non_expired")
    private boolean credentialsNonExpired = true;

    @Column(nullable = false, name = "enabled")
    private boolean enabled = true;

    @Column(name = "created_date",nullable = false,updatable = false)
    @CreatedDate
    private LocalDateTime createdDate;

    @Column(name = "last_modified_date",nullable = false,updatable = false)
    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    @ToString.Exclude
    private Set<Role> roles = new HashSet<>();

    public User(Long id) {
        this.id = id;
    }
}
