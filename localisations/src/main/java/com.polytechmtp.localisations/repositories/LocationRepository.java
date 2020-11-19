package com.polytechmtp.localisations.repositories;


import com.polytechmtp.localisations.models.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location,Long> {

}
