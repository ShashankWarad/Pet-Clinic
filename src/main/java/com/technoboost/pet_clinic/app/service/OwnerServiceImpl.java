package com.technoboost.pet_clinic.app.service;

import com.technoboost.pet_clinic.app.Entity.Owner;
import com.technoboost.pet_clinic.app.response.OwnerResponse;
import com.technoboost.pet_clinic.repository.OwnerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository ownerRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void registerNewOwner(OwnerResponse ownerDTO) {
        if (ownerRepository.findByEmail(ownerDTO.getEmail()).isPresent()) {
            throw new IllegalArgumentException("An account already exists for this email.");
        }

        Owner owner = new Owner();
        owner.setFirstName(ownerDTO.getFirstName());
        owner.setLastName(ownerDTO.getLastName());
        owner.setEmail(ownerDTO.getEmail());
        owner.setPassword(passwordEncoder.encode(ownerDTO.getPassword())); // Encrypt password
        owner.setPhoneNumber(ownerDTO.getPhoneNumber());
        owner.setAddress(ownerDTO.getAddress());

        ownerRepository.save(owner);
    }
}
