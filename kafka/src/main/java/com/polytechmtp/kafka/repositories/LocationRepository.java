package com.polytechmtp.kafka.repositories;


import com.polytechmtp.kafka.models.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface LocationRepository extends JpaRepository<Location,Long> {

    @Modifying
    @Query(value = "SELECT * FROM locations WHERE user_id = :id", nativeQuery=true)
    List<Location> get(@Param("id") String id);

}
