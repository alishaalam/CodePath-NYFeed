
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
    "meta",
    "docs"
})
public class Response {

    @JsonProperty("meta")
    private Meta meta;
    @JsonProperty("docs")
    private List<Doc> docs = new ArrayList<Doc>();

    /**
     * @return The meta
     */
    @JsonProperty("meta")
    public Meta getMeta() {
        return meta;
    }

    /**
     * @param meta The meta
     */
    @JsonProperty("meta")
    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    /**
     * @return The docs
     */
    @JsonProperty("docs")
    public List<Doc> getDocs() {
        return docs;
    }

    /**
     * @param docs The docs
     */
    @JsonProperty("docs")
    public void setDocs(List<Doc> docs) {
        this.docs = docs;
    }

}
