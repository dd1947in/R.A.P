package com.example.uadnd.cou8901.rap.net;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import timber.log.Timber;

/**
 * Created by dd2568 on 9/16/2017.
 */

public class RedditRESTAPIHandler {
    String userAgent = "R.A.P/0.1 by cou8901";
    String accessToken = null;
    String responseJson = null;
    String REDDIT_VOTE_URL = "https://oauth.reddit.com/api/vote";

    public RedditRESTAPIHandler() {

    }
    public RedditRESTAPIHandler(String accessToken) {
        this.accessToken = accessToken;
    }
    public RedditRESTAPIHandler(String userAgent, String accessToken) {
        this.userAgent = userAgent;
        this.accessToken = accessToken;
    }
    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

     public String getJsonResponseWithAccessTokenHeader(String url) {
        return callRedditRESTAPI(url, true);
    }
    public String getJsonResponseWithoutAccessTokenHeader(String url) {
        return callRedditRESTAPI(url, false);
    }

    private String callRedditRESTAPI(String url, boolean authorizationHeader ) {
        OkHttpClient client;
        Request.Builder requestBuilder;
        Request request;

        client = new OkHttpClient();
        requestBuilder = new Request.Builder();

        if(authorizationHeader) {
            request = requestBuilder
                    .addHeader("User-Agent", userAgent)
                    .addHeader("Authorization", "bearer " + accessToken)
                    .url(url).build();
        } else {
            request = requestBuilder
                    .addHeader("User-Agent", userAgent)
                    //.addHeader("Authorization", "bearer " + accessToken)
                    .url(url).build();
        }
        try {
            Response response = client.newCall(request).execute();
            responseJson = response.body().string();
        }catch (IOException ioe) {
            Timber.d(ioe);
        }
        return responseJson;
    }
    public void apiVoteOnRedditPost(String id, String dir) {
        /*
        dir	=> vote direction. one of (1, 0, -1)
        id	=> fullname of a thing
        rank =>	 an integer greater than 1
        uh / X-Modhash header	=> a modhash
         */

        String name = "t3_" + id;  // id param is not name, we have to prefix "t3_"
        //dir => 1, 0, -1

        OkHttpClient client;
        Request.Builder requestBuilder;
        Request request;

        client = new OkHttpClient();
        requestBuilder = new Request.Builder();

        RequestBody formBody = new FormBody.Builder()
                .add("id", name)
                .add("dir", dir)      // vote up or down or cancel
                .build();

        request = requestBuilder //new Request.Builder()
                .addHeader("User-Agent", userAgent)
                .addHeader("Authorization", "bearer " + accessToken)
                .url(REDDIT_VOTE_URL)
                .post(formBody)
                .build();
        try {
            Response response = client.newCall(request).execute();
            responseJson = response.body().string();
        }catch (IOException ioe) {
            Timber.d(ioe);
        }
    }
}
