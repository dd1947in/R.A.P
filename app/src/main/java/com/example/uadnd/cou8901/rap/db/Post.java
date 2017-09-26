package com.example.uadnd.cou8901.rap.db;

import com.google.gson.annotations.SerializedName;

/**
 * Created by dd2568 on 9/9/2017.
 */

public class Post {
    transient long _id; // PK
    // All the following are properties as they appear in json
    @SerializedName("id") String id;
    @SerializedName("title") String title;
    @SerializedName("subreddit") String subreddit;
    @SerializedName("subreddit_type") String subreddit_type;   //to help Gson recognize property
    @SerializedName("permalink") String permalink; //permalink
    @SerializedName("url") String url;
    @SerializedName("thumbnail") String thumbnail;
    @SerializedName("ups") String ups;
    @SerializedName("downs") String downs;
    @SerializedName("is_video") String is_video; //to help Gson recognize property
    @SerializedName("created") String created;

    public Post(){}
    public Post(long _id, String id, String title, String subreddit, String subreddit_type, String permalink, String url, String thumbnail, String ups, String downs, String is_video, String created) {
        this._id = _id;
        this.id = id;
        this.title = title;
        this.subreddit = subreddit;
        this.subreddit_type = subreddit_type;
        this.permalink = permalink;
        this.url = url;
        this.thumbnail = thumbnail;
        this.ups = ups;
        this.downs = downs;
        this.is_video = is_video;
        this.created = created;
    }

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

    public String getSubreddit() {
        return subreddit;
    }

    public void setSubreddit(String subreddit) {
        this.subreddit = subreddit;
    }

    public String getSubreddit_type() {
        return subreddit_type;
    }

    public void setSubreddit_type(String subreddit_type) {
        this.subreddit_type = subreddit_type;
    }

    public String getPermalink() {
        return permalink;
    }

    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getUps() {
        return ups;
    }

    public void setUps(String ups) {
        this.ups = ups;
    }

    public String getDowns() {
        return downs;
    }

    public void setDowns(String downs) {
        this.downs = downs;
    }

    public String getIs_video() {
        return is_video;
    }

    public void setIs_video(String is_video) {
        this.is_video = is_video;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }
}
