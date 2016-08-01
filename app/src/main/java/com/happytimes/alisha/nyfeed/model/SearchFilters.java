package com.happytimes.alisha.nyfeed.model;

import org.parceler.Parcel;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by alishaalam on 7/31/16.
 */
@Parcel
public class SearchFilters {

    public String beginDate;
    public String sort_order;
    public String news_desk_values;

    public String getBeginDate() {
        return beginDate;
    }

    public SearchFilters() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        this.beginDate = formatter.format(Calendar.getInstance().getTime());
        this.sort_order = "oldest";

    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getSort_order() {
        return sort_order;
    }

    public void setSort_order(String sort_order) {
        this.sort_order = sort_order;
    }

    public String getNews_desk_values() {
        return news_desk_values;
    }

    public void setNews_desk_values(String news_desk_values) {
        this.news_desk_values = news_desk_values;
    }
}
