package com.polytechmtp.kafka.repositories;


import com.polytechmtp.kafka.models.Location;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.Optional;

@Repository
public interface LocationRepository extends JpaRepository<Location,Long> {

}
