package com.polytechmtp.kafka.repositories;

import com.polytechmtp.kafka.models.UserLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface UserLocationRepository extends JpaRepository<UserLocation,Long> {
}
