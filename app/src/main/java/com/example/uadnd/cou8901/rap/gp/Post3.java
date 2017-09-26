package com.example.uadnd.cou8901.rap.gp;

import com.google.gson.annotations.SerializedName;

/**
 * Created by dd2568 on 9/10/2017.
 */

public class Post3 {
    @SerializedName("kind") String kind;
    @SerializedName("data") Post2 data;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public Post2 getData() {
        return data;
    }

    public void setData(Post2 data) {
        this.data = data;
    }
}
