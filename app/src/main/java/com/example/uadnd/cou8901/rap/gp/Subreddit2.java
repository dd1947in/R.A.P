package com.example.uadnd.cou8901.rap.gp;

import com.google.gson.annotations.SerializedName;

/**
 * Created by dd2568 on 9/10/2017.
 */

public class Subreddit2 {
    @SerializedName("modhash") String modhash;
    @SerializedName("children") Subreddit1 [] children ;
    @SerializedName("after") String after;
    @SerializedName("before") String before;

    public String getModhash() {
        return modhash;
    }

    public void setModhash(String modhash) {
        this.modhash = modhash;
    }

    public Subreddit1[] getChildren() {
        return children;
    }

    public void setChildren(Subreddit1[] children) {
        this.children = children;
    }

    public String getAfter() {
        return after;
    }

    public void setAfter(String after) {
        this.after = after;
    }

    public String getBefore() {
        return before;
    }

    public void setBefore(String before) {
        this.before = before;
    }
}
