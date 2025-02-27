package com.technoboost.pet_clinic.security;

import com.technoboost.pet_clinic.exception.PetClinicException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.*;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.Date;

@Service
@Slf4j
public class JwtProviderImpl implements JwtProvider {

    private KeyStore keyStore;

    @PostConstruct
    public void init() {
        try {
            keyStore = KeyStore.getInstance("JKS");
            InputStream resourceAsStream = getClass().getResourceAsStream("/wf.jks");
            keyStore.load(resourceAsStream, "4v>fsXvs8DFCQWF".toCharArray());
        } catch (KeyStoreException | CertificateException | NoSuchAlgorithmException | IOException e) {
            throw new PetClinicException("Exception occurred while loading keystore");
        }

    }

    @Override
    public String generateToken(Authentication authentication) {
        Date now = new Date();
        long jwtExpirationInMs = 1000L * 60 * 60 * 24;
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);
        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(principal.getUsername())
                .signWith(SignatureAlgorithm.RS256, getPrivateKey())
                .setIssuedAt(new Date())
                .setIssuer("wf")
                .setExpiration(expiryDate)
                .compact();
    }

    private PrivateKey getPrivateKey() {
        try {
            return (PrivateKey) keyStore.getKey("wf", "4v>fsXvs8DFCQWF".toCharArray());
        } catch (KeyStoreException | NoSuchAlgorithmException | UnrecoverableKeyException e) {
            throw new PetClinicException("Exception occurred while retrieving public key from keystore");
        }
    }

    @Override
    public boolean validateToken(String jwt) {
        try {
            getAllClaimsFromToken(jwt);
            return true;
        } catch (MalformedJwtException | SignatureException ex) {
            log.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty.");
        }
        return false;
    }

    private PublicKey getPublicKey() {
        try {
            return keyStore.getCertificate("wf").getPublicKey();
        } catch (KeyStoreException e) {
            throw new PetClinicException("Exception occurred while retrieving public key from keystore");
        }
    }

    @Override
    public String getUsernameFromJWT(String token) {
        return getAllClaimsFromToken(token).getSubject();
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .verifyWith(getPublicKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
