package com.itaxi.server.advertisement.domain.repository;

import com.itaxi.server.advertisement.domain.Advertisement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdvertisementRepository extends JpaRepository<Advertisement, Long> {
    Optional<Advertisement> findByName(String name);
}
