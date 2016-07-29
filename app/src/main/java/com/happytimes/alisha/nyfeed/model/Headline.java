
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
    "main",
    "print_headline"
})
public class Headline {

    @JsonProperty("main")
    public String main;
    @JsonProperty("print_headline")
    public String printHeadline;

    /**
     * 
     * @return
     *     The main
     */
    @JsonProperty("main")
    public String getMain() {
        return main;
    }

    /**
     * 
     * @param main
     *     The main
     */
    @JsonProperty("main")
    public void setMain(String main) {
        this.main = main;
    }

    /**
     * 
     * @return
     *     The printHeadline
     */
    @JsonProperty("print_headline")
    public String getPrintHeadline() {
        return printHeadline;
    }

    /**
     * 
     * @param printHeadline
     *     The print_headline
     */
    @JsonProperty("print_headline")
    public void setPrintHeadline(String printHeadline) {
        this.printHeadline = printHeadline;
    }


}
