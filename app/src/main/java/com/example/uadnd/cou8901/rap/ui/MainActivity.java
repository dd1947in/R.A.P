package com.example.uadnd.cou8901.rap.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.uadnd.cou8901.rap.R;
import com.example.uadnd.cou8901.rap.db.Me;
import com.example.uadnd.cou8901.rap.db.Post;
import com.example.uadnd.cou8901.rap.db.RedditPostEqualOpportunityEnforcer;
import com.example.uadnd.cou8901.rap.db.Subreddit;
import com.example.uadnd.cou8901.rap.gp.GsonParser;

import com.example.uadnd.cou8901.rap.net.RedditRESTAPIHandler;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import timber.log.Timber;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;
import static com.example.uadnd.cou8901.rap.db.SQLiteRedditObjects.persistMasterPostList;
import static com.example.uadnd.cou8901.rap.db.SQLiteRedditObjects.persistSubreddit;

public class MainActivity extends AppCompatActivity {
    private static final String TAG =   "MainActivity";
    private  String userAgent = null ; //getString(R.string.reddit_rap_user_agent_value);
    private String accessToken = null;

    //Use Butterknife for view binding
    @BindView(R.id.tv_ma_app_intro_1)  TextView tv1;
    @BindView(R.id.tv_ma_app_intro_2)  TextView tv2;
    @BindView(R.id.tv_ma_app_intro_3)  TextView tv3;
    @BindView(R.id.tv_ma_app_intro_4)   TextView tv4;
    @BindView(R.id.tv_ma_app_intro_5)   TextView tv5;
    @BindView(R.id.button_login)  Button buttonLogin;
    @BindView(R.id.adView) AdView mAdView  ;
    @BindView(R.id.progressBar2)  ProgressBar progressBar;
    private Unbinder unbinder;
    private static int REDDIT_DATA_LOADED = 0;

    Context mContext;
    private Tracker mTracker;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        //Plant Timber
        Timber.plant(new Timber.DebugTree());

        setContentView(R.layout.activity_main);
        mContext = this;
        Timber.d("onCreate");



        unbinder = ButterKnife.bind(this);

        // [START shared_tracker]
        RAPApplication application = (RAPApplication) getApplication();
        mTracker = application.getDefaultTracker();
        // [END shared_tracker]

        //mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);
        userAgent = getString(R.string.reddit_rap_user_agent_value);


        //=========
        Intent intent = getIntent();
        String intentAction = intent.getAction();

        String intentExtraText = intent.getStringExtra(Intent.EXTRA_TEXT);


        //if(getIntent()!=null && getIntent().getAction() != null && getIntent().getAction().equals(Intent.ACTION_VIEW)) {
        if (intentAction != null && intentAction.equals(Intent.ACTION_VIEW)) {  // Case of Return from Reddit after login
            Uri uri = intent.getData();
            String redditError = uri.getQueryParameter("error");
            if (redditError != null) {
                Timber.d("Reddit Login Error : " + redditError);
            } else {
                Timber.d("Handling login return from Reddit!");
                //Extract access_token after login @ reddit.com
                Timber.d(uri.toString());
                HashMap<String, String> hashMap = parseRedditReturnUriForAccessToken(uri);
                if (hashMap.containsKey("access_token")) {
                    Date date = new Date();
                    long dateNow = date.getTime();
                    long dateTokenExpires = dateNow + Long.parseLong(hashMap.get("expires_in")) * 1000;

                    SharedPreferences sharedPref = getDefaultSharedPreferences(this);
                    SharedPreferences.Editor editor = sharedPref.edit();

                    editor.putString(getString(R.string.reddit_access_token_key), hashMap.get("access_token"));
                    editor.putLong(getString(R.string.reddit_access_token_issued_timestamp_key), dateNow);
                    editor.putLong(getString(R.string.reddit_access_token_expire_timestamp_key), dateTokenExpires);
                    editor.commit();

                    tv1.setVisibility(View.INVISIBLE);
                    tv2.setVisibility(View.INVISIBLE);
                    tv3.setVisibility(View.INVISIBLE);
                    tv4.setVisibility(View.INVISIBLE);
                    buttonLogin.setVisibility(View.INVISIBLE);

                    progressBar.setVisibility(View.VISIBLE);
                    tv5.setVisibility(View.VISIBLE);



                    refreshReddit();  //Does just one Async call and does everythign inside one call

                }
            }
        }
        //=========
        mTracker.setScreenName(TAG);
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());
    }
    /**************************************************************************************************************/
    public void refreshReddit() {
        // this is a loading cycle ans login at reddit is completed

        SharedPreferences sharedPref = getDefaultSharedPreferences(this);
        String accessToken  = sharedPref.getString(getString(R.string.reddit_access_token_key), "");
        if(!accessToken.equals("")) { // We can do more robust checking for expiration later.
            new AsyncRedditRefresh().execute(accessToken);
        } else {
            Timber.d("No Access token for refresh!");
        }

        //After implementing additional functionality, uncomment above  line and comment below three lines
        /*
        REDDIT_DATA_LOADED = 1;
        Intent intent = new Intent(mContext, RedditPostListActivity.class);
        startActivity(intent);
*/
    }
     public void signInToReddit(View view) {
        //Sign in to reddit.com using a browser
        String url = getString(R.string.reddit_oauth2_url_fullly_formed);
        //Timber.d(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
        //Timber.d("signInToReddit");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Timber.d("onResume");

        if(REDDIT_DATA_LOADED == 1) {
            Intent intent = new Intent(mContext, RedditPostListActivity.class);
            startActivity(intent);
        }

    }

    private HashMap<String, String> parseRedditReturnUriForAccessToken(Uri uri) {
        HashMap<String, String> hashMap = new HashMap<String, String>();
        String pars[] = uri.getFragment().toString().split("&");  // Get Fragment and split by '&'
        for(int i = 0 ; i < pars.length; i++) {
            String keyValuePair[] = pars[i].split("=");
            if(keyValuePair.length == 2) {
                hashMap.put(keyValuePair[0], keyValuePair[1]);
                Timber.d(keyValuePair[0]+ "==>" + keyValuePair[1]);
            }
        }
        return hashMap;
    }
/**************************************************************************************/
    public class AsyncRedditRefresh extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            //if(strings[0] != null ) return "OK";

            RedditRESTAPIHandler redditAPIHandler = new RedditRESTAPIHandler(getString(R.string.reddit_rap_user_agent_value), strings[0] );  //User Agent and access token

            //Handle : https://oauth.reddit.com/api/v1/me
            String apiV1MeJson = redditAPIHandler.getJsonResponseWithAccessTokenHeader(getString(R.string.url_api_v1_me));
            //Delete old and start a new DB
            //mContext.deleteDatabase(RAPContract.DATABASE_NAME);

            //Get the policy enforcer .
            RedditPostEqualOpportunityEnforcer policyEnforcer = new RedditPostEqualOpportunityEnforcer();

            //Get my subreddits
            String mySubredditsJson = redditAPIHandler.getJsonResponseWithAccessTokenHeader(getString(R.string.url_subreddits_mine_subscriber_json));
             StringBuffer sb = new StringBuffer();
            //Timber.d(str2);
            ArrayList<Subreddit> subreddits = GsonParser.getSubredditsFromJsonUsingGsonParser(mySubredditsJson);
            for(int i = 0 ; i < subreddits.size(); i++) {
                //Loop through subreddits
                //First persist subreddit i SQLite DB
                persistSubreddit(mContext, subreddits.get(i));
                String sb1 = "\n\n" + subreddits.get(i).getDisplay_name_prefixed()  ; //+ "( " + subreddits.get(i).getName() + " )\n";
                sb.append(sb1);
                String postsJson = redditAPIHandler.getJsonResponseWithAccessTokenHeader(getString(R.string.oauth_reddit_com_url)+"/"+subreddits.get(i).getDisplay_name_prefixed()+".json");
                //Timber.d(postsJson);
                ArrayList<Post> alp = GsonParser.getPostsFromJsonUsingGsonParser(postsJson);
                policyEnforcer.addPostList(alp);
            }

            //Timber.d(apiV1MeJson);
            Me me = GsonParser.getMeFromJsonUsingGsonParser(apiV1MeJson);
            SharedPreferences sharedPref = getDefaultSharedPreferences(mContext);
            SharedPreferences.Editor editor = sharedPref.edit();

            editor.putString(getString(R.string.key_apiv1me_id), me.getId());
            editor.putString(getString(R.string.key_apiv1me_name), me.getName());
            editor.putLong(getString(R.string.key_apiv1me_created), Long.valueOf(me.getCreated().substring(0,10) ) * 1000L );
            editor.putLong(getString(R.string.key_apiv1me_created_utc), Long.parseLong(me.getCreated_utc().substring(0,10)) * 1000L);
            editor.putString(getString(R.string.key_apiv1me_over_18), me.getOver_18());
            editor.putString(getString(R.string.key_apiv1me_has_verified_email), me.getHas_verified_email());
            editor.putString(getString(R.string.key_apiv1me_oauth_client_id), me.getOauth_client_id());



            editor.putString(getString(R.string.key_user_subreddits), sb.toString());
            //Timber.d(sb.toString());
            editor.commit();



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

            REDDIT_DATA_LOADED = 1;
            Intent intent = new Intent(mContext, RedditPostListActivity.class);
            startActivity(intent);

        }
    }


}
