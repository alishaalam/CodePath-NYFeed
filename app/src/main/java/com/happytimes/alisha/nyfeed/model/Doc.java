
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
    "web_url",
    "snippet",
    "lead_paragraph",
    "abstract",
    "print_page",
    "blog",
    "source",
    "multimedia",
    "headline",
    "keywords",
    "pub_date",
    "document_type",
    "news_desk",
    "section_name",
    "subsection_name",
    "byline",
    "type_of_material",
    "_id",
    "word_count",
    "slideshow_credits"
})
public class Doc {

    @JsonProperty("web_url")
    public String webUrl;
    @JsonProperty("snippet")
    public String snippet;
    @JsonProperty("lead_paragraph")
    public String leadParagraph;
    @JsonProperty("source")
    public String source;
    @JsonProperty("multimedia")
    public List<MultiMedia> multimedia = new ArrayList<>();
    @JsonProperty("headline")
    public Headline headline;
    @JsonProperty("pub_date")
    public String pubDate;
    @JsonProperty("document_type")
    public String documentType;
    @JsonProperty("news_desk")
    public String newsDesk;
    @JsonProperty("section_name")
    public String sectionName;
    @JsonProperty("subsection_name")
    public String subsectionName;
    @JsonProperty("type_of_material")
    public String typeOfMaterial;
    @JsonProperty("_id")
    public String id;
    @JsonProperty("word_count")
    public String wordCount;


    // Empty constructor to be able to use Parceler library
    public Doc() {
    }

    /**
     * 
     * @return
     *     The webUrl
     */
    @JsonProperty("web_url")
    public String getWebUrl() {
        return webUrl;
    }

    /**
     * 
     * @param webUrl
     *     The web_url
     */
    @JsonProperty("web_url")
    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    /**
     * 
     * @return
     *     The snippet
     */
    @JsonProperty("snippet")
    public String getSnippet() {
        return snippet;
    }

    /**
     * 
     * @param snippet
     *     The snippet
     */
    @JsonProperty("snippet")
    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    /**
     * 
     * @return
     *     The leadParagraph
     */
    @JsonProperty("lead_paragraph")
    public String getLeadParagraph() {
        return leadParagraph;
    }

    /**
     * 
     * @param leadParagraph
     *     The lead_paragraph
     */
    @JsonProperty("lead_paragraph")
    public void setLeadParagraph(String leadParagraph) {
        this.leadParagraph = leadParagraph;
    }

    /**
     * 
     * @return
     *     The source
     */
    @JsonProperty("source")
    public String getSource() {
        return source;
    }

    /**
     * 
     * @param source
     *     The source
     */
    @JsonProperty("source")
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * 
     * @return
     *     The multimedia
     */
    @JsonProperty("multimedia")
    public List<MultiMedia> getMultimedia() {
        return multimedia;
    }

    /**
     * 
     * @param multimedia
     *     The multimedia
     */
    @JsonProperty("multimedia")
    public void setMultimedia(List<MultiMedia> multimedia) {
        this.multimedia = multimedia;
    }

    /**
     * 
     * @return
     *     The headline
     */
    @JsonProperty("headline")
    public Headline getHeadline() {
        return headline;
    }

    /**
     * 
     * @param headline
     *     The headline
     */
    @JsonProperty("headline")
    public void setHeadline(Headline headline) {
        this.headline = headline;
    }


    /**
     * 
     * @return
     *     The pubDate
     */
    @JsonProperty("pub_date")
    public String getPubDate() {
        return pubDate;
    }

    /**
     * 
     * @param pubDate
     *     The pub_date
     */
    @JsonProperty("pub_date")
    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    /**
     * 
     * @return
     *     The documentType
     */
    @JsonProperty("document_type")
    public String getDocumentType() {
        return documentType;
    }

    /**
     * 
     * @param documentType
     *     The document_type
     */
    @JsonProperty("document_type")
    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    /**
     * 
     * @return
     *     The newsDesk
     */
    @JsonProperty("news_desk")
    public String getNewsDesk() {
        return newsDesk;
    }

    /**
     * 
     * @param newsDesk
     *     The news_desk
     */
    @JsonProperty("news_desk")
    public void setNewsDesk(String newsDesk) {
        this.newsDesk = newsDesk;
    }

    /**
     * 
     * @return
     *     The sectionName
     */
    @JsonProperty("section_name")
    public String getSectionName() {
        return sectionName;
    }

    /**
     * 
     * @param sectionName
     *     The section_name
     */
    @JsonProperty("section_name")
    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    /**
     * 
     * @return
     *     The subsectionName
     */
    @JsonProperty("subsection_name")
    public String getSubsectionName() {
        return subsectionName;
    }

    /**
     * 
     * @param subsectionName
     *     The subsection_name
     */
    @JsonProperty("subsection_name")
    public void setSubsectionName(String subsectionName) {
        this.subsectionName = subsectionName;
    }


    /**
     * 
     * @return
     *     The typeOfMaterial
     */
    @JsonProperty("type_of_material")
    public String getTypeOfMaterial() {
        return typeOfMaterial;
    }

    /**
     * 
     * @param typeOfMaterial
     *     The type_of_material
     */
    @JsonProperty("type_of_material")
    public void setTypeOfMaterial(String typeOfMaterial) {
        this.typeOfMaterial = typeOfMaterial;
    }

    /**
     * 
     * @return
     *     The id
     */
    @JsonProperty("_id")
    public String getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The _id
     */
    @JsonProperty("_id")
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The wordCount
     */
    @JsonProperty("word_count")
    public String getWordCount() {
        return wordCount;
    }

    /**
     * 
     * @param wordCount
     *     The word_count
     */
    @JsonProperty("word_count")
    public void setWordCount(String wordCount) {
        this.wordCount = wordCount;
    }
}
