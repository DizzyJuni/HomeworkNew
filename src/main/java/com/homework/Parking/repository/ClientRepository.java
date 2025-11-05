package com.homework.Parking.repository;

import com.homework.Parking.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findByPhoneNumber(String phoneNumber);
    Optional<Client> findByCarsId(Long Id);
    boolean existsByPhoneNumber(String phoneNumber);
    boolean existsByEmail(String email);

}