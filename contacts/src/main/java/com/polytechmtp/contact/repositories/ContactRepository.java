package com.polytechmtp.contact.repositories;

import com.polytechmtp.contact.models.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact,Long> {

}
