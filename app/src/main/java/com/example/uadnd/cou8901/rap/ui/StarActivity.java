package com.example.uadnd.cou8901.rap.ui;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.uadnd.cou8901.rap.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

public class StarActivity extends AppCompatActivity {
    private static final String TAG =   "StarActivity";

    @BindView(R.id.tv_user_subreddits) TextView tvUserSubreddits;
    @BindView(R.id.adView) AdView mAdView  ;
    private Tracker mTracker;
    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_star);
        unbinder = ButterKnife.bind(this);

        // [START shared_tracker]
        RAPApplication application = (RAPApplication) getApplication();
        mTracker = application.getDefaultTracker();
        // [END shared_tracker]

        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);


        SharedPreferences sharedPref = getDefaultSharedPreferences(this);
        tvUserSubreddits.setText(sharedPref.getString(getString(R.string.key_user_subreddits), ""));

        mTracker.setScreenName(TAG);
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());

    }
}
