
package com.happytimes.alisha.nyfeed.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonPropertyOrder({
    "person",
    "original",
    "organization"
})
public class Byline {

    @JsonProperty("person")
    public List<Person> person = new ArrayList<>();
    @JsonProperty("original")
    public String original;
    @JsonProperty("organization")
    public String organization;

    /**
     * 
     * @return
     *     The person
     */
    @JsonProperty("person")
    public List<Person> getPerson() {
        return person;
    }

    /**
     * 
     * @param person
     *     The person
     */
    @JsonProperty("person")
    public void setPerson(List<Person> person) {
        this.person = person;
    }

    /**
     * 
     * @return
     *     The original
     */
    @JsonProperty("original")
    public String getOriginal() {
        return original;
    }

    /**
     * 
     * @param original
     *     The original
     */
    @JsonProperty("original")
    public void setOriginal(String original) {
        this.original = original;
    }

    /**
     * 
     * @return
     *     The organization
     */
    @JsonProperty("organization")
    public String getOrganization() {
        return organization;
    }

    /**
     * 
     * @param organization
     *     The organization
     */
    @JsonProperty("organization")
    public void setOrganization(String organization) {
        this.organization = organization;
    }


}
