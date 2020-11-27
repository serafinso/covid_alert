package com.polytechmtp.kafka.repositories;

import com.polytechmtp.kafka.models.KeycloakUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public interface UserRepository extends JpaRepository<KeycloakUser,String> {

    @Modifying
    @Query(value = "UPDATE user_entity SET state = :state WHERE id = :id", nativeQuery=true)
    void updateState(@Param("state") String state, @Param("id") String id);

}
