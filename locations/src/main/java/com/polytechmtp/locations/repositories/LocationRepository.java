package com.polytechmtp.locations.repositories;


import com.polytechmtp.locations.models.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location,Long> {

}
