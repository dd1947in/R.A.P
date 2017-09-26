package com.example.uadnd.cou8901.rap.db;

import com.google.gson.annotations.SerializedName;

/**
 * Created by dd2568 on 9/9/2017.
 */

public class Me {

    //Atrributes as they appear in /api/v1/me json
    @SerializedName("id") String id;
    @SerializedName("name") String name;
    @SerializedName("created") String created ;
    @SerializedName("created_utc") String created_utc;
    @SerializedName("over_18") String over_18;
    @SerializedName("has_verified_email") String has_verified_email;
    @SerializedName("oauth_client_id") String oauth_client_id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getCreated_utc() {
        return created_utc;
    }

    public void setCreated_utc(String created_utc) {
        this.created_utc = created_utc;
    }

    public String getOver_18() {
        return over_18;
    }

    public void setOver_18(String over_18) {
        this.over_18 = over_18;
    }

    public String getHas_verified_email() {
        return has_verified_email;
    }

    public void setHas_verified_email(String has_verified_email) {
        this.has_verified_email = has_verified_email;
    }

    public String getOauth_client_id() {
        return oauth_client_id;
    }

    public void setOauth_client_id(String oauth_client_id) {
        this.oauth_client_id = oauth_client_id;
    }
}
