package com.example.uadnd.cou8901.rap.ui;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uadnd.cou8901.rap.R;
import com.example.uadnd.cou8901.rap.db.Post;
import com.example.uadnd.cou8901.rap.dummy.DummyContent;
import com.example.uadnd.cou8901.rap.mh.MediaUrlHandler;
import com.example.uadnd.cou8901.rap.net.RedditRESTAPIHandler;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.squareup.picasso.Picasso;

import java.util.Arrays;
import java.util.HashSet;

import timber.log.Timber;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

/**
 * A fragment representing a single RedditPost detail screen.
 * This fragment is either contained in a {@link RedditPostListActivity}
 * in two-pane mode (on tablets) or a {@link RedditPostDetailActivity}
 * on handsets.
 */
public class RedditPostDetailFragment extends Fragment   implements View.OnClickListener, ExoPlayer.EventListener{
    private static final String TAG =   "PostDetailFragment";
    private Tracker mTracker;
    private Context mContext;

    private ImageView mImageView;
    private ImageView mImageThumbsUpView;
    private ImageView mImageThumbsDownView;
    private ImageView mImageShareView;
    private TextView mTitleView;
    private TextView mViewComments;

    private SimpleExoPlayer mExoPlayer;
    private SimpleExoPlayerView mPlayerView;
    private  MediaSessionCompat mMediaSession;
    private PlaybackStateCompat.Builder mStateBuilder;
    private NotificationManager mNotificationManager;



    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    //private DummyContent.DummyItem mItem;
    private Post post;
    Bundle bundle  = null;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public RedditPostDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();


        // [START shared_tracker]
        RAPApplication application = (RAPApplication) getActivity().getApplication();
        mTracker = application.getDefaultTracker();
        // [END shared_tracker]

        if(getArguments() != null) {
                  //.getBundle("POST_BUNDLE") ;
                //post = new Post() ;post.setTitle( bundle.getString("TITLE"));
            bundle = getArguments();
            post = new Post() ;
            post.setTitle( bundle.getString("TITLE"));
            post.setUrl(bundle.getString("URL"));
            post.setPermalink(bundle.getString("PERMALINK"));
            //Timber.d(post.getUrl());

        }
        mTracker.setScreenName(TAG);
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.redditpost_detail, container, false);
        mPlayerView = (SimpleExoPlayerView) rootView.findViewById(R.id.sepv_post_video);
        mImageView = (ImageView) rootView.findViewById(R.id.iv_post_image);
        mTitleView = (TextView)rootView.findViewById(R.id.tv_title);
        mViewComments = (TextView)rootView.findViewById(R.id.tv_view_comments);

        mImageThumbsUpView = (ImageView) rootView.findViewById(R.id.iv_thumbs_up);
        mImageThumbsDownView = (ImageView) rootView.findViewById(R.id.iv_thumbs_down);
        mImageShareView = (ImageView) rootView.findViewById(R.id.iv_share);

        // Show the dummy content as text in a TextView.
        if (post != null) {
            //System.out.println(post.getTitle());
            mTitleView.setText(post.getTitle());

            //Handle Vote Up
            mImageThumbsUpView.setOnClickListener(new View.OnClickListener() {

                                                      @Override
                                                      public void onClick(View view) {
                                                          Toast.makeText(mContext, "Voted Up", Toast.LENGTH_SHORT).show();
                                                          new AsyncVoteReddit().execute(post.getId(), "1"); // Vote Up
                                                      }
                                                  }

            );
            mImageThumbsDownView.setOnClickListener(new View.OnClickListener() {

                                                      @Override
                                                      public void onClick(View view) {
                                                          Toast.makeText(mContext, "Voted Down", Toast.LENGTH_SHORT).show();
                                                          new AsyncVoteReddit().execute(post.getId(), "-1"); // Vote Down
                                                      }
                                                  }

            );
            mImageShareView.setOnClickListener(new View.OnClickListener() {
                                                   @Override
                                                   public void onClick(View view) {
                                                       String redditUrl = "https://www.reddit.com" + post.getPermalink(); //post.getUrl() ; //
                                                       //Toast.makeText(mContext, redditUrl, Toast.LENGTH_LONG).show();
                                                       Intent redditIntent = new Intent(Intent.ACTION_SEND);
                                                       redditIntent.setType("text/plain");
                                                       redditIntent.putExtra(Intent.EXTRA_TEXT, redditUrl);
                                                       startActivity(Intent.createChooser(redditIntent, "Share a Reddit URL"));
                                                   }
                                               }
            );
            mViewComments.setOnClickListener(new View.OnClickListener() {
                    //Toast.makeText(getContext(), "hello", Toast.LENGTH_LONG).show()
                    @Override
                    public void onClick(View v) {
                        // do something when the buttonLogin is clicked
                        System.out.println("https://www.reddit.com"+post.getPermalink());
                        String permaLink = post.getPermalink();
                        if(permaLink == null || permaLink.equals("null")) {
                            Toast.makeText(getActivity(), "No Comments Link for this Post", Toast.LENGTH_SHORT).show();
                        } else {
                            Uri webpage = Uri.parse("https://www.reddit.com"+post.getPermalink());
                            Intent viewCommentsIntent = new Intent(Intent.ACTION_VIEW, webpage);
                            if (viewCommentsIntent.resolveActivity(mContext.getPackageManager()) != null) {
                                startActivity(viewCommentsIntent);
                            }

                        }
                    }

            });
        }
        int mediaUrlHandler = bundle.getInt("MEDIA_HANDLER");
        switch (mediaUrlHandler) {
            case MediaUrlHandler.EXOPAYER_MEDIA :
                mImageView.setVisibility(View.INVISIBLE);
                mPlayerView.setVisibility(View.VISIBLE);
                initializeMediaSession();
                initializePlayer(Uri.parse(post.getUrl()));


                break;
            case MediaUrlHandler.IMAGEVIEW_MEDIA :
                mPlayerView.setVisibility(View.INVISIBLE);
                mImageView.setVisibility(View.VISIBLE);
                Picasso.with(mContext).load(post.getUrl()).fit().into(mImageView);

                break;
            case MediaUrlHandler.BROWSER_MEDIA :
            default:
                mPlayerView.setVisibility(View.INVISIBLE);
                mImageView.setVisibility(View.INVISIBLE);
                Uri webpage = Uri.parse(post.getUrl());
                Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                if (intent.resolveActivity(mContext.getPackageManager()) != null) {
                    startActivity(intent);
                }
                break;


        }


/*
        if(isVideoUrl(post.getUrl())) {   // Direct video URLs , use Simple Exo Player
            mImageView.setVisibility(View.INVISIBLE);
            mPlayerView.setVisibility(View.VISIBLE);
            initializeMediaSession();
            initializePlayer(Uri.parse(post.getUrl()));

        }else if(isImageUrl(post.getUrl()))  {   // Direct image URL, load them into image view with Picasso
            mPlayerView.setVisibility(View.INVISIBLE);
            mImageView.setVisibility(View.VISIBLE);
            Picasso.with(mContext).load(post.getUrl()).fit().into(mImageView);

         } else {   // Everything must be launched into a browser.
            //Launch a webview intent
            mPlayerView.setVisibility(View.INVISIBLE);
            mImageView.setVisibility(View.INVISIBLE);
            Uri webpage = Uri.parse(post.getUrl());
            Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
            if (intent.resolveActivity(mContext.getPackageManager()) != null) {
                startActivity(intent);
            }

        }
        */

        mTracker.setScreenName(TAG);
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());
        return rootView;
    }
    /*
    private boolean isVideoUrl(String url) {
        if(url.endsWith(".mp4") ) {
            return true;
        } else {
            return false;
        }

    }
    private boolean isImageUrl(String url) {
        if(url.endsWith(".jpg") || url.endsWith(".gif") || url.endsWith(".png") || url.endsWith(".svg")) {
            return true;
        } else {
            return false;
        }
    }
*/
    private void initializeMediaSession() {

        // Create a MediaSessionCompat.
        mMediaSession = new MediaSessionCompat(mContext, "TAG");


        // Enable callbacks from MediaButtons and TransportControls.
        mMediaSession.setFlags(
                MediaSessionCompat.FLAG_HANDLES_MEDIA_BUTTONS |
                        MediaSessionCompat.FLAG_HANDLES_TRANSPORT_CONTROLS);

        // Do not let MediaButtons restart the player when the app is not visible.
        mMediaSession.setMediaButtonReceiver(null);

        // Set an initial PlaybackState with ACTION_PLAY, so media buttons can start the player.
        mStateBuilder = new PlaybackStateCompat.Builder()
                .setActions(
                        PlaybackStateCompat.ACTION_PLAY |
                                PlaybackStateCompat.ACTION_PAUSE |
                                PlaybackStateCompat.ACTION_SKIP_TO_PREVIOUS |
                                PlaybackStateCompat.ACTION_PLAY_PAUSE);

        mMediaSession.setPlaybackState(mStateBuilder.build());


        mMediaSession.setCallback(new RedditPostDetailFragment.MySessionCallback());

        // Start the Media Session since the activity is active.
        mMediaSession.setActive(true);

    }
    private void initializePlayer(Uri mediaUri) {
        if (mExoPlayer == null) {
            // Create an instance of the ExoPlayer.
            TrackSelector trackSelector = new DefaultTrackSelector();
            LoadControl loadControl = new DefaultLoadControl();
            mExoPlayer = ExoPlayerFactory.newSimpleInstance(mContext, trackSelector, loadControl);
            mPlayerView.setPlayer(mExoPlayer);

            // Set the ExoPlayer.EventListener to this activity.
            mExoPlayer.addListener(this);

            // Prepare the MediaSource.
            String userAgent = Util.getUserAgent(mContext, "BakersResourceApp");
            MediaSource mediaSource = new ExtractorMediaSource(mediaUri, new DefaultDataSourceFactory(
                    mContext, userAgent), new DefaultExtractorsFactory(), null, null);
            mExoPlayer.prepare(mediaSource);
            mExoPlayer.setPlayWhenReady(true);
        }
    }
    private void releasePlayer() {
        //mNotificationManager.cancelAll();
        if(mExoPlayer != null) {
            mExoPlayer.stop();
            mExoPlayer.release();
            mExoPlayer = null;
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        releasePlayer();
        if(mMediaSession != null) {
            mMediaSession.setActive(false);
        }

    }

    @Override
    public void onPause() {
        super.onPause();
        if(mExoPlayer != null) {
            mExoPlayer.stop();
            mExoPlayer.release();
            mExoPlayer = null;
        }

    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onTimelineChanged(Timeline timeline, Object manifest) {

    }

    @Override
    public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

    }

    @Override
    public void onLoadingChanged(boolean isLoading) {

    }

    @Override
    public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {

    }

    @Override
    public void onPlayerError(ExoPlaybackException error) {

    }

    @Override
    public void onPositionDiscontinuity() {

    }
    private class MySessionCallback extends MediaSessionCompat.Callback {
        @Override
        public void onPlay() {
            mExoPlayer.setPlayWhenReady(true);
        }

        @Override
        public void onPause() {
            mExoPlayer.setPlayWhenReady(false);
        }

        @Override
        public void onSkipToPrevious() {
            mExoPlayer.seekTo(0);
        }
    }

    public class AsyncVoteReddit extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            SharedPreferences sharedPref = getDefaultSharedPreferences(mContext);
            String redditAccessToken = sharedPref.getString(getString(R.string.reddit_access_token_key), getString(R.string.reddit_access_token_missing));


            //Timber.d("==========================================================================");
            //Timber.d(redditAccessToken);
            //Timber.d("==========================================================================");


            Timber.d("access_token=" + redditAccessToken);
            RedditRESTAPIHandler redditRESTAPIHandler = new RedditRESTAPIHandler(getString(R.string.reddit_rap_user_agent_value), redditAccessToken);
            String id =   strings[0];
            String dir = strings[1];
            redditRESTAPIHandler.apiVoteOnRedditPost(id, dir);
            return "OK";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }

}
