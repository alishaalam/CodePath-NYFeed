
package com.happytimes.alisha.nyfeed.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import org.parceler.Parcel;

@Parcel
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonPropertyOrder({
    "response",
    "status",
    "copyright"
})
public class ArticleCollection {

    @JsonProperty("response")
    public Response response;
    @JsonProperty("status")
    public String status;
    @JsonProperty("copyright")
    public String copyright;

    /**
     * 
     * @return
     *     The response
     */
    @JsonProperty("response")
    public Response getResponse() {
        return response;
    }

    /**
     * 
     * @param response
     *     The response
     */
    @JsonProperty("response")
    public void setResponse(Response response) {
        this.response = response;
    }

    /**
     * 
     * @return
     *     The status
     */
    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    /**
     * 
     * @param status
     *     The status
     */
    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 
     * @return
     *     The copyright
     */
    @JsonProperty("copyright")
    public String getCopyright() {
        return copyright;
    }

    /**
     * 
     * @param copyright
     *     The copyright
     */
    @JsonProperty("copyright")
    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

}
