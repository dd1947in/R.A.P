package com.example.uadnd.cou8901.rap.ui;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.support.annotation.NonNull;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.uadnd.cou8901.rap.R;
import com.example.uadnd.cou8901.rap.cp.RAPContract;
import com.example.uadnd.cou8901.rap.mh.MediaUrlHandler;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

//import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.squareup.picasso.Picasso;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import timber.log.Timber;

/**
 * An activity representing a list of RedditPosts. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link RedditPostDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class RedditPostListActivity extends AppCompatActivity  implements
        LoaderManager.LoaderCallbacks<Cursor>{
    private static final String TAG =   "PostListActivity";
    private static final int REDDIT_POST_LOADER_ID = 0;
    Context mContext;
    private RedditPostCursorAdapterMD mAdapter;

    //Member variables for the adapter and Recyclerview


    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    @BindView(R.id.adView)  AdView mAdView  ;
    //View recyclerView = findViewById(R.id.redditpost_list);
    @BindView(R.id.redditpost_list) View recyclerView;
    private Unbinder unbinder;

    private boolean mTwoPane;
    private Tracker mTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redditpost_list);

        unbinder = ButterKnife.bind(this);
        mContext = this;
        Timber.d(TAG + ":onCreate");

        //Timber.plant(new Timber.DebugTree()); Initialized in MainActivity

        // [START shared_tracker]
        RAPApplication application = (RAPApplication) getApplication();
        mTracker = application.getDefaultTracker();
        // [END shared_tracker]


        //mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);

          // [START shared_tracker]
        // Obtain the shared Tracker instance.
        //AnalyticsApplication application = (AnalyticsApplication) getApplication();
        //mTracker = application.getDefaultTracker();
       // [END shared_tracker]
        //View recyclerView = findViewById(R.id.redditpost_list);

        assert recyclerView != null;
        mAdapter = new RedditPostCursorAdapterMD(this);
        setupRecyclerView((RecyclerView) recyclerView);



        if (findViewById(R.id.redditpost_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }
        mTracker.setScreenName(TAG);
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());
        //GsonParser.testGsonParser(this);
        getSupportLoaderManager().initLoader(REDDIT_POST_LOADER_ID, null, RedditPostListActivity.this);
        //SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        /*
        SharedPreferences sharedPref = getDefaultSharedPreferences(this);
        Timber.d("==========================================================================");
        Timber.d(sharedPref.getString(getString(R.string.reddit_access_token_key), "NO_TOKEN"));
        Timber.d("==========================================================================");
        */
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Timber.d(TAG +":onSaveInstanceState");
        int scrollX = recyclerView.getScrollX();
        int scrollY = recyclerView.getScrollY();
        outState.putInt(getString(R.string.SCROLLX), scrollX);
        outState.putInt(getString(R.string.SCROLLY), scrollY);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        Timber.d(TAG +":onRestoreInstanceState");
        super.onRestoreInstanceState(savedInstanceState);
        int scrollX = savedInstanceState.getInt(getString(R.string.SCROLLX));
        int scrollY = savedInstanceState.getInt(getString(R.string.SCROLLY));

        recyclerView.scrollTo(scrollX, scrollY);

    }


    @Override
    protected void onResume() {
        super.onResume();
        Timber.d(TAG +":onResume");

        mTracker.setScreenName(TAG);
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        Timber.d(TAG +":onCreateOptionsMenu");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Timber.d(TAG +":onOptionsItemSelected");
        if (id == R.id.action_settings) {
            //Toast.makeText(this, "Action Settings", Toast.LENGTH_LONG).show();
            return true;
        } else if(id == R.id.refresh_button)  {
            //Toast.makeText(this, "Download Button", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(mContext, DownloadActivity.class);
            startActivity(intent);

            return true;

        }else if(id == R.id.star_button)  {
            //Toast.makeText(this, "Star Button", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(mContext, StarActivity.class);
            startActivity(intent);

            return true;

        }else if(id == R.id.user_button)  {
            //Toast.makeText(this, "User Button", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(mContext, UserActivity.class);
            startActivity(intent);

            return true;

        }

        return super.onOptionsItemSelected(item);
    }
    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        //recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(DummyContent.ITEMS));
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new AsyncTaskLoader<Cursor>(this) {
            Cursor mPostData = null;

            @Override
            protected void onStartLoading() {
                Timber.d("onStartLoading");
                if(mPostData != null) {
                    deliverResult(mPostData);
                } else {
                    //Force a new load
                    forceLoad();
                }
            }

            @Override
            public Cursor loadInBackground() {
                Timber.d("loadInBackground");

                try {
                    return getContentResolver().query(RAPContract.Posts.POSTS_URI,
                            null, //projection
                            null, // selection
                            null, // selection args
                            RAPContract.Posts._ID);  // order posts by pk


                }catch (Exception e) {
                    Timber.d(e);
                    return null;
                }
            }

            @Override
            public void deliverResult(Cursor data) {
                Timber.d("deliverResult=" + data.getCount());
                mPostData = data;
                super.deliverResult(data);

                data.moveToFirst();

            }
        };
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        Timber.d(TAG+":onLoadFinished");
        if(data == null) {
            Timber.d("onLoadFinished data is null");
        }
        mAdapter.swapCursor(data);

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        Timber.d(TAG+"onLoaderReset");
    }



    public class RedditPostCursorAdapterMD  extends RecyclerView.Adapter<RedditPostCursorAdapterMD.RedditPostViewHolder>{
        private Cursor mCursor;
        private Context mContext;
        public RedditPostCursorAdapterMD(Context mContext) {
            this.mContext = mContext;
        }

        @Override
        public RedditPostCursorAdapterMD.RedditPostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mContext)
                    .inflate(R.layout.redditpost_list_content, parent, false);
            return new RedditPostCursorAdapterMD.RedditPostViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final RedditPostCursorAdapterMD.RedditPostViewHolder holder, int position) {
            mCursor.moveToPosition(position);
            final String postId = mCursor.getString(mCursor.getColumnIndex(RAPContract.Posts.COLUMN_ID));
            final String postTitle = mCursor.getString(mCursor.getColumnIndex(RAPContract.Posts.COLUMN_TITLE));
            final String postThumbnail = mCursor.getString(mCursor.getColumnIndex(RAPContract.Posts.COLUMN_THUMBNAIL));
            final String postSubreddit = mCursor.getString(mCursor.getColumnIndex(RAPContract.Posts.COLUMN_SUBREDDIT));
            final String postSubredditType = mCursor.getString(mCursor.getColumnIndex(RAPContract.Posts.COLUMN_SUBREDDIT_TYPE));
            final String postCreated = mCursor.getString(mCursor.getColumnIndex(RAPContract.Posts.COLUMN_CREATED));
            final String postUps = mCursor.getString(mCursor.getColumnIndex(RAPContract.Posts.COLUMN_UPS));

            final String postUrl = mCursor.getString(mCursor.getColumnIndex(RAPContract.Posts.COLUMN_URL));
            //For testing media player, set all urls to .mp4 urls. Comment the above line and uncomment the below line.
            //final String postUrl = "https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffd974_-intro-creampie/-intro-creampie.mp4";

            final String postDowns = mCursor.getString(mCursor.getColumnIndex(RAPContract.Posts.COLUMN_DOWNS));
            final String postIsVideo = mCursor.getString(mCursor.getColumnIndex(RAPContract.Posts.COLUMN_IS_VIDEO));
            final String postPermalink = mCursor.getString(mCursor.getColumnIndex(RAPContract.Posts.COLUMN_PERMALINK));
            final String idPk = mCursor.getString(mCursor.getColumnIndex(RAPContract.Posts._ID));

            final Bundle bundle = new Bundle();
            bundle.putString("_ID", idPk);
            bundle.putString("ID", postId);
            bundle.putString("TITLE", postTitle);
            bundle.putString("THUMBNAIL", postThumbnail);
            bundle.putString("SUBREDDIT", postSubreddit);
            bundle.putString("SUBREDDIT_TYPE", postSubredditType);
            bundle.putString("CREATED", postCreated);
            bundle.putString("UPS", postUps);
            bundle.putString("URL", postUrl);
            bundle.putString("DOWNS", postDowns);
            bundle.putString("IS_VIDEO", postIsVideo);
            bundle.putString("PERMALINK", postPermalink);



            holder.mTitleTextView.setText(postTitle);
            holder.mSubredditTextView.setText("r/"+postSubreddit);
            if(postThumbnail == null || postThumbnail.equalsIgnoreCase("null") || postThumbnail.equalsIgnoreCase("self") || postThumbnail.equalsIgnoreCase("")) {
                holder.mThumbnailImageView.setImageResource(R.drawable.icons8notapplicable48);

            } else {
                Picasso.with(mContext).load(postThumbnail).fit().into(holder.mThumbnailImageView);

            }
            int mediaUrlHandler = MediaUrlHandler.getMediaPlayer(postUrl);
            bundle.putInt("MEDIA_HANDLER", mediaUrlHandler);
            switch (mediaUrlHandler) {
                case MediaUrlHandler.EXOPAYER_MEDIA :
                    holder.mPostTypeTextView.setText(getString(R.string.text_tv_post_type_video));
                    break;
                case MediaUrlHandler.IMAGEVIEW_MEDIA :
                    holder.mPostTypeTextView.setText(getString(R.string.text_tv_post_type_image));
                    break;
                case MediaUrlHandler.BROWSER_MEDIA :
                    default:
                        holder.mPostTypeTextView.setText(getString(R.string.text_tv_post_type_link));
                        break;


            }
            /*
            if(isVideoUrl(postUrl)) {
                holder.mPostTypeTextView.setText(getString(R.string.text_tv_post_type_video));

            } else if(isImageUrl(postUrl)) {
                holder.mPostTypeTextView.setText(getString(R.string.text_tv_post_type_image));
            } else {
                holder.mPostTypeTextView.setText(getString(R.string.text_tv_post_type_link));
            }*/

            //OnClickListeners
            holder.mThumbnailImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (mTwoPane) {

                        RedditPostDetailFragment fragment = new RedditPostDetailFragment();
                        fragment.setArguments(bundle);

                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.redditpost_detail_container, fragment)
                                .commit();
                    } else {

                        Intent postDetailIntent = new Intent(mContext, RedditPostDetailActivity.class);
                        postDetailIntent.putExtra("POST_BUNDLE", bundle);
                        mContext.startActivity(postDetailIntent, bundle);
                    }
                }
            });

            holder.mTitleTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mTwoPane) {
                        RedditPostDetailFragment fragment = new RedditPostDetailFragment();
                        fragment.setArguments(bundle);

                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.redditpost_detail_container, fragment)
                                .commit();
                    } else {

                        Intent postDetailIntent = new Intent(mContext, RedditPostDetailActivity.class);

                        postDetailIntent.putExtra("POST_BUNDLE", bundle);
                        mContext.startActivity(postDetailIntent, bundle);
                    }
                }
            });
        }
        /*
        private boolean isVideoUrl(String url) {

            if(url.endsWith(".mp1") || url.endsWith(".mp2") || url.endsWith(".mp3") || url.endsWith(".mp4")) {
                return true;
            } else {
                return false;
            }

        }
        private boolean isImageUrl(String url) {
            if(url.endsWith(".jpeg") || url.endsWith(".jpg") || url.endsWith(".gif") || url.endsWith(".png") || url.endsWith(".svg")) {
                return true;
            } else {
                return false;
            }
        }
*/
        @Override
        public int getItemCount() {
            if(mCursor == null) {
                return 0;
            } else {
                return mCursor.getCount();
            }
        }
        public Cursor swapCursor(Cursor cursor) {
            if(mCursor == cursor) {
                return null;
            }
            Cursor tmpCursor = mCursor;
            this.mCursor = cursor;
            if(cursor != null) {
                this.notifyDataSetChanged();
            }
            return tmpCursor;
        }

        class RedditPostViewHolder extends RecyclerView.ViewHolder {

            @BindView(R.id.iv_thumbnail) ImageView mThumbnailImageView;  //thumbnail
            @BindView(R.id.tv_title) TextView mTitleTextView; //title
            @BindView(R.id.tv_sr_displayname) TextView mSubredditTextView; //title
            @BindView(R.id.tv_post_type) TextView mPostTypeTextView; //title
            public RedditPostViewHolder(View view) {
                super(view);
                //Timber.d("RecipeViewHolder");
                ButterKnife.bind(this, view);
            }
        }


    }
}
