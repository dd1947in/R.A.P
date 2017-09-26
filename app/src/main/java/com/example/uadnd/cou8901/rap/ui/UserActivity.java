package com.example.uadnd.cou8901.rap.ui;

import android.content.SharedPreferences;
import android.icu.text.DateFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.uadnd.cou8901.rap.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import java.text.SimpleDateFormat;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;
import static java.util.Locale.*;

public class UserActivity extends AppCompatActivity {

    private static final String TAG =   "UserActivity";

    @BindView(R.id.tv_user_property_label) TextView tvUpLabel;
    @BindView(R.id.tv_user_property_value) TextView tvUpValue;

    @BindView(R.id.tv_id_label) TextView tvIdLabel;
    @BindView(R.id.tv_id_value) TextView tvIdValue;

    @BindView(R.id.tv_name_label) TextView tvNameLabel;
    @BindView(R.id.tv_name_value) TextView tvNameValue;

    @BindView(R.id.tv_created_label) TextView tvCreatedLabel;
    @BindView(R.id.tv_created_value) TextView tvCreatedValue;

    @BindView(R.id.tv_created_utc_label) TextView tvCreatedUtcLabel;
    @BindView(R.id.tv_created_utc_value) TextView tvCreatedUtcValue;


    @BindView(R.id.tv_over_18_label) TextView tvOver18Label;
    @BindView(R.id.tv_over_18_value) TextView tvOver18Value;

    @BindView(R.id.tv_has_verified_email_label) TextView tvHasVerifiedEmailLabel;
    @BindView(R.id.tv_has_verified_email_value) TextView tvHasVerifiedEmailValue;

    @BindView(R.id.tv_oauth_client_id_label) TextView tvOauthClientIdLabel;
    @BindView(R.id.tv_oauth_client_id_value) TextView tvOauthClientIdValue;

    @BindView(R.id.tv_access_token_label) TextView tvAccessTokenIdLabel;
    @BindView(R.id.tv_access_token_value) TextView tvAccessTokenIdValue;

    @BindView(R.id.tv_token_issue_time_label) TextView tvTokenIssueTimeLabel;
    @BindView(R.id.tv_token_issue_time_value) TextView tvTokenIssueTimeValue;

    @BindView(R.id.tv_token_expire_time_label) TextView tvTokenExpireTimeLabel;
    @BindView(R.id.tv_token_expire_time_value) TextView tvTokenExpireTimeValue;

    @BindView(R.id.adView) AdView mAdView  ;
    private Tracker mTracker;
    private Unbinder unbinder;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        unbinder = ButterKnife.bind(this);

        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);

        // [START shared_tracker]
        RAPApplication application = (RAPApplication) getApplication();
        mTracker = application.getDefaultTracker();
        // [END shared_tracker]


        SharedPreferences sharedPref = getDefaultSharedPreferences(this);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

        tvUpLabel.setText(getString(R.string.rup_header_label));
        tvUpValue.setText(getString(R.string.rup_header_value));

        tvIdLabel.setText(getString(R.string.rup_id)); //id
        tvIdValue.setText(sharedPref.getString(getString(R.string.key_apiv1me_id), ""));


        tvNameLabel.setText(getString(R.string.rup_name));//name
        tvNameValue.setText(sharedPref.getString(getString(R.string.key_apiv1me_name), ""));

        tvCreatedLabel.setText(getString(R.string.rup_created));//created
        tvCreatedValue.setText(df.format(sharedPref.getLong(getString(R.string.key_apiv1me_created), 0L)));

        tvCreatedUtcLabel.setText(getString(R.string.rup_created_utc));//created_utc
        tvCreatedUtcValue.setText(df.format(sharedPref.getLong(getString(R.string.key_apiv1me_created_utc), 0L)));

        tvOver18Label.setText(getString(R.string.rup_over_18));//over_18
        tvOver18Value.setText(sharedPref.getString(getString(R.string.key_apiv1me_over_18), ""));

        tvHasVerifiedEmailLabel.setText(getString(R.string.rup_has_verified_email));
        tvHasVerifiedEmailValue.setText(sharedPref.getString(getString(R.string.key_apiv1me_has_verified_email), ""));

        tvOauthClientIdLabel.setText(getString(R.string.rup_oauth_client_id));
        tvOauthClientIdValue.setText(sharedPref.getString(getString(R.string.key_apiv1me_oauth_client_id), ""));


        tvAccessTokenIdLabel.setText(getString(R.string.rup_access_token_id));
        tvAccessTokenIdValue.setText(sharedPref.getString(getString(R.string.reddit_access_token_key), ""));


        tvTokenIssueTimeLabel.setText(getString(R.string.rup_token_issue_time));
        tvTokenIssueTimeValue.setText(df.format(sharedPref.getLong(getString(R.string.reddit_access_token_issued_timestamp_key), 0L)));

        tvTokenExpireTimeLabel.setText(getString(R.string.rup_token_expire_time));
        tvTokenExpireTimeValue.setText(df.format(sharedPref.getLong(getString(R.string.reddit_access_token_expire_timestamp_key), 0L)));

        //tvTokenExpireTimeValue.setText(String.valueOf(sharedPref.getLong(getString(R.string.reddit_access_token_expire_timestamp_key), 0L)));

        mTracker.setScreenName(TAG);
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());

    }
}
