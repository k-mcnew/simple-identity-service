package org.example.repositories;

import org.example.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;

/**
 * Persists and retrieves information that represents an address.
 */
@Repository
@Table(name="address")
public interface AddressRepository extends JpaRepository<Address, Long> { }


