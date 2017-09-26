package com.example.uadnd.cou8901.rap.db;

import com.google.gson.annotations.SerializedName;

/**
 * Created by dd2568 on 9/9/2017.
 */

public class Subreddit {
    transient long _id; // PK
    // All the following are properties as they appear in json
    @SerializedName("id") String id ;
    @SerializedName("title") String title ;
    @SerializedName("display_name") String display_name;//to help Gson recognize property
    @SerializedName("display_name_prefixed") String display_name_prefixed; //to help Gson recognize property
    @SerializedName("header_img") String header_img; //to help Gson recognize property
    //String description ;  // field is commented out
    @SerializedName("lang") String lang ;
    @SerializedName("name") String name ; //kind + _ + id

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public String getDisplay_name_prefixed() {
        return display_name_prefixed;
    }

    public void setDisplay_name_prefixed(String display_name_prefixed) {
        this.display_name_prefixed = display_name_prefixed;
    }

    public String getHeader_img() {
        return header_img;
    }

    public void setHeader_img(String header_img) {
        this.header_img = header_img;
    }

//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
