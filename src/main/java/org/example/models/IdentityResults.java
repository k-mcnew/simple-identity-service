package org.example.models;

import org.springframework.data.domain.Page;
import java.io.Serializable;

/**
 * Model holds metadata related to pagination of Identity information, and the current result set
 */
public class IdentityResults implements Serializable {

    /** The current page of the result set */
    private Integer currentPage;

    /** The beginning page of the result set */
    private Integer beginPage;

    /** The last page of the result set */
    private Integer endPage;

    /** A subset of the Pageable result set */
    private Page<Person> people;

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getBeginPage() {
        return beginPage;
    }

    public void setBeginPage(Integer beginPage) {
        this.beginPage = beginPage;
    }

    public Integer getEndPage() {
        return endPage;
    }

    public void setEndPage(Integer endPage) {
        this.endPage = endPage;
    }

    public Page<Person> getPeople() {
        return people;
    }

    public void setPeople(Page<Person> people) {
        this.people = people;
    }
}
