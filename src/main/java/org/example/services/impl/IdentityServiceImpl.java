package org.example.services.impl;

import org.example.models.IdentityResults;
import org.example.models.Person;
import org.example.repositories.AddressRepository;
import org.example.repositories.PersonRepository;
import org.example.services.IdentityService;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.inject.Inject;

/**
 * @See IdentityService interface for documentation
 */
@Service
@Transactional
public class IdentityServiceImpl implements IdentityService {

    @Inject
    private PersonRepository personRepository;
    @Inject
    private AddressRepository addressRepository;

    public IdentityResults findAll(int offset, int limit) {

        IdentityResults results = new IdentityResults();
        results.setPeople(personRepository.findAll(new PageRequest(offset - 1, limit, Sort.Direction.ASC, "lastName")));
        results.setCurrentPage(results.getPeople().getTotalPages() + 1);
        //Pagination variables
        results.setBeginPage(Math.max(1, results.getCurrentPage() - 5));
        results.setEndPage(Math.min(results.getBeginPage() + 10, results.getPeople().getTotalPages()));

        return results;
    }

    public Person findOne(Long personId) {
        return personRepository.findOne(personId);
    }

    public Person createIdentity(Person person) {
        person.setAddress(addressRepository.saveAndFlush(person.getAddress()));
        return personRepository.saveAndFlush(person);
    }

    public Person updateIdentity(Person person) {

        person.setAddress(addressRepository.saveAndFlush(person.getAddress()));
        Person updatedPerson = personRepository.saveAndFlush(person);

        return updatedPerson;
    }

    public void deleteIdentity(String personId) {
        Person deleted = personRepository.getOne(Long.valueOf(personId));
        addressRepository.delete(deleted.getAddress());
        deleted.setAddress(null);
        personRepository.delete(deleted);
    }



}
