package com.example.uadnd.cou8901.rap.gp;

import com.example.uadnd.cou8901.rap.db.Subreddit;
import com.google.gson.annotations.SerializedName;

/**
 * Created by dd2568 on 9/10/2017.
 */

public class Subreddit1 {
    @SerializedName("kind") String kind;
    @SerializedName("data") Subreddit data;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public Subreddit getData() {
        return data;
    }

    public void setData(Subreddit data) {
        this.data = data;
    }
}
