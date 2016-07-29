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
    "hits",
    "time",
    "offset"
})
public class Meta {

    @JsonProperty("hits")
    public Integer hits;
    @JsonProperty("time")
    public Integer time;
    @JsonProperty("offset")
    public Integer offset;


    /**
     * 
     * @return
     *     The hits
     */
    @JsonProperty("hits")
    public Integer getHits() {
        return hits;
    }

    /**
     * 
     * @param hits
     *     The hits
     */
    @JsonProperty("hits")
    public void setHits(Integer hits) {
        this.hits = hits;
    }

    /**
     * 
     * @return
     *     The time
     */
    @JsonProperty("time")
    public Integer getTime() {
        return time;
    }

    /**
     * 
     * @param time
     *     The time
     */
    @JsonProperty("time")
    public void setTime(Integer time) {
        this.time = time;
    }

    /**
     * 
     * @return
     *     The offset
     */
    @JsonProperty("offset")
    public Integer getOffset() {
        return offset;
    }

    /**
     * 
     * @param offset
     *     The offset
     */
    @JsonProperty("offset")
    public void setOffset(Integer offset) {
        this.offset = offset;
    }

}
