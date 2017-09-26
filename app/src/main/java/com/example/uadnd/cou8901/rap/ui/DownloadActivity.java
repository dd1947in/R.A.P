package com.example.uadnd.cou8901.rap.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.uadnd.cou8901.rap.R;
import com.example.uadnd.cou8901.rap.cp.RAPContract;
import com.example.uadnd.cou8901.rap.db.Me;
import com.example.uadnd.cou8901.rap.db.Post;
import com.example.uadnd.cou8901.rap.db.RedditPostEqualOpportunityEnforcer;
import com.example.uadnd.cou8901.rap.db.Subreddit;
import com.example.uadnd.cou8901.rap.gp.GsonParser;
import com.example.uadnd.cou8901.rap.net.RedditRESTAPIHandler;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import java.util.ArrayList;

import timber.log.Timber;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;
import static com.example.uadnd.cou8901.rap.db.SQLiteRedditObjects.persistMasterPostList;
import static com.example.uadnd.cou8901.rap.db.SQLiteRedditObjects.persistSubreddit;

public class DownloadActivity extends AppCompatActivity {
    private static final String TAG =   "DownloadActivity";

    private Tracker mTracker;
    private Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        mContext = this;

        // [START shared_tracker]
        RAPApplication application = (RAPApplication) getApplication();
        mTracker = application.getDefaultTracker();
        // [END shared_tracker]

        mTracker.setScreenName(TAG);
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());

        /*
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(Intent.EXTRA_TEXT, "REDDIT_REFRESH");
        startActivity(intent);
        */
        new AsyncRedditRefresh().execute();

    }


    public class AsyncRedditRefresh extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            //if(strings[0] != null ) return "OK";
            SharedPreferences sharedPref = getDefaultSharedPreferences(mContext);
            String strSubreddits = sharedPref.getString( getString(R.string.key_user_subreddits) , "");
            String accessToken = sharedPref.getString( getString(R.string.reddit_access_token_key) , "");

            RedditRESTAPIHandler redditAPIHandler = new RedditRESTAPIHandler( accessToken );  //User Agent and access token

            //Delete old and start a new DB
            //mContext.deleteDatabase(RAPContract.DATABASE_NAME);


            //Get the policy enforcer .
            RedditPostEqualOpportunityEnforcer policyEnforcer = new RedditPostEqualOpportunityEnforcer();

            String [] arrSubreddits = strSubreddits.split("\n\n");

            for(int i = 0 ; i < arrSubreddits.length ; i++) {
                 String postsJson = redditAPIHandler.getJsonResponseWithAccessTokenHeader(getString(R.string.oauth_reddit_com_url)+"/"+arrSubreddits[i]+".json");
                ArrayList<Post> alp = GsonParser.getPostsFromJsonUsingGsonParser(postsJson);
                policyEnforcer.addPostList(alp);
            }

            // Policy enforced subreddit posts
            ArrayList<Post> masterPostList = policyEnforcer.getMasterPostList();
            //mContext.deleteDatabase(RAPContract.DATABASE_NAME);

            persistMasterPostList(mContext, masterPostList);
            return "OK";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Timber.d(s);

            Intent intent = new Intent(mContext, RedditPostListActivity.class);
            startActivity(intent);

        }
    }


}
