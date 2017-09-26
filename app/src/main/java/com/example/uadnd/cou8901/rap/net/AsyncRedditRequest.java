package com.example.uadnd.cou8901.rap.net;

import android.os.AsyncTask;

/**
 * Created by dd2568 on 9/25/2017.
 */

public class AsyncRedditRequest  extends AsyncTask<String, Void, String> {
    //URL, accessToken
    @Override
    protected String doInBackground(String... strings) {
        String url = strings[0];
        String accessToken = null;
        RedditRESTAPIHandler redditAPIHandler = null;
        String jsonResponse = null;

        if(strings.length == 2) {
            accessToken = strings[1];
            redditAPIHandler = new RedditRESTAPIHandler( accessToken );  //access token
        }else {
            redditAPIHandler = new RedditRESTAPIHandler();
        }



        if(url.startsWith("https://oauth."))  {
            //requests that need authorization header with access token
            jsonResponse = redditAPIHandler.getJsonResponseWithAccessTokenHeader(url);

        } else {
            //requests without authorizatino header
            jsonResponse = redditAPIHandler.getJsonResponseWithoutAccessTokenHeader(url);
        }
        return jsonResponse;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }
}
