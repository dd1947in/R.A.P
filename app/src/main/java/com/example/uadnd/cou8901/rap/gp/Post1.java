package com.example.uadnd.cou8901.rap.gp;

import com.example.uadnd.cou8901.rap.db.Post;
import com.google.gson.annotations.SerializedName;

/**
 * Created by dd2568 on 9/10/2017.
 */

public class Post1 {
    @SerializedName("kind") String kind;
    @SerializedName("data") Post data;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public Post getData() {
        return data;
    }

    public void setData(Post data) {
        this.data = data;
    }
}
