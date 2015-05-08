package org.example.services;

import org.example.models.IdentityResults;
import org.example.models.Person;

/**
 * Service performs CRUD operations for all Idenity related information
 */
public interface IdentityService {

    /**
     * Finds and returns a subset of the entire set of records defined by the offset and limit query parameters
     * @param offset where to start returning records from the entire set of results
     * @param limit the max number of identities to be returned in a single request
     * @return
     */
    public IdentityResults findAll(int offset, int limit);

    /**
     * Finds a single Identity record for the given unique identifier
     * @param personId the unique identifier for an Identity record
     * @return a single Identity for the given unique idenitifer
     */
    public Person findOne(Long personId);

    /**
     * Persists the given information to the datasource as an Identity record.
     * @param person the information that represents an Identity record
     * @return the newly created Idenity record
     */
    public Person createIdentity(Person person);

    /**
     * Deletes an Identity record from the datasource given a valid unique identifier
     * @param personId the unique identifier for the Identity record to be deleted
     */
    public void deleteIdentity(String personId);

    /**
     * Updates an Identity record with the given information.
     * @param person an Identity model with the updated information to persist to the datasource
     * @return the updated Identity record
     */
    public Person updateIdentity(Person person);
}
