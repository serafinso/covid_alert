package com.polytechmtp.contacts.repositories;

import com.polytechmtp.contacts.models.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact,Long> {

}
