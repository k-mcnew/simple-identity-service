package org.example.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Holds the criteria provided by the front end used to query the Identity service with.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchCriteria {

    /** the search value */
    private String searchInput;

    public String getSearchInput() {
        return searchInput;
    }

    public void setSearchInput(String searchInput) {
        this.searchInput = searchInput;
    }
}
