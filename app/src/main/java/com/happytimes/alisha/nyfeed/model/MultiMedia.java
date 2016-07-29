package com.happytimes.alisha.nyfeed.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import org.parceler.Parcel;

/**
 * Created by alishaalam on 7/27/16.
 */

@Parcel
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonPropertyOrder({
        "width",
        "url",
        "height",
        "subtype"})
public class MultiMedia {

    @JsonProperty("width")
    private int width;
    @JsonProperty("url")
    private String url;
    @JsonProperty("height")
    private int height;
    @JsonProperty("subtype")
    private String subtype;

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public String getUrl() {
        return String.format("http://www.nytimes.com/%s", url);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }
}
