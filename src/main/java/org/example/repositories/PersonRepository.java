package org.example.repositories;

import org.example.models.Person;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;

/**
 * Persists and retrieves information that represents a person
 */
@Repository
@Table(name="people")
public interface PersonRepository extends JpaRepository<Person, Long> { }
