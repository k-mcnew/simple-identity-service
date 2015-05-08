package org.example.controllers;

import org.example.models.IdentityResults;
import org.example.models.Person;
import org.example.services.IdentityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * The Identity controller is used to create, modify, and delete Identity related information
 */
@Controller
@RequestMapping(value = "/identity")
public class IdentityController {

    /** Service to perform CRUD operations on identity related information */
    @Autowired
    IdentityService identityService;

    /**
     * Endpoint renders the Person search page
     * @return the name of the ThymeLeaf template to render
     */
    @RequestMapping(method=RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public String getIdentityView() {
        return "search/search";
    }

    /**
     * Endpoint returns a single identity given a valid Id
     * @param id the id of a identity
     * @return a single identity given a valid id
     */
    @RequestMapping(value="/{id}", method= RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Person getIdentityById(@PathVariable String id) {
        return identityService.findOne(Long.valueOf(id));
    }

    /**
     * Endpoint returns a subset of all identity recores
     * @param limit the max number of identities to be returned in a single request, defaulted to 25
     * @param offset where to start returning records from the entire set of results, defaulted to one
     * @return a subset of the entire set of records defined by the offset and limit query parameters
     */
    @RequestMapping(method=RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public IdentityResults getIdentities(@RequestParam(defaultValue="25", required=false) Integer limit, @RequestParam(defaultValue="1", required=false) Integer offset) {

        // do not allow limits less than one
        if (limit < 1) {
            limit = 1;
        }

        // do not allow offsets less than 0 and greater than 50
        if (offset < 0) {
            offset = 25;
        } else if (offset > 50) {
            offset = 50;
        }

        return identityService.findAll(offset, limit);
    }

    /**
     * Endpoint returns the email address for the given person Id
     * @param id the unique identifier for a person record
     * @return the email address for the given person Id
     */
    @RequestMapping(value="/{id}/emailAddress", method=RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String, String> getEmailByPersonId(@PathVariable String id) {
        HashMap<String, String> results = new HashMap<String, String>();
        Person person = identityService.findOne(Long.valueOf(id));
        results.put("email", person.getEmail());

        return results;
    }


    /**
     * Endpoint creates an identity record for the given information
     * @param newIdentity the new identity to persist to the datasource
     * @return the newly created identity
     */
    @RequestMapping(method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Person createIdentity(@RequestBody Person newIdentity) {
        return identityService.createIdentity(newIdentity);
    }

    /**
     * Endpoint updates an identity with the given information
     * @param updateIdentity an existing identity with information to be updated
     * @return the identity with updated information
     */
    @RequestMapping(method=RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Person updateIdentity(@RequestBody Person updateIdentity) {
        return identityService.updateIdentity(updateIdentity);
    }

    /**
     * Endpoint deletes an identity record for the given id
     * @param personId the unique identifier for the identity record to be deleted
     */
    @RequestMapping(method=RequestMethod.DELETE, consumes=MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteIdenity(@RequestParam String personId) {
        identityService.deleteIdentity(personId);
    }
}
