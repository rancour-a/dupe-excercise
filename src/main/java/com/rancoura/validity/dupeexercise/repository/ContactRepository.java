package com.rancoura.validity.dupeexercise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.rancoura.validity.dupeexercise.domain.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {

}
