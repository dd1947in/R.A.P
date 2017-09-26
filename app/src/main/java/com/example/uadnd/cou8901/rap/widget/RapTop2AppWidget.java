package com.example.uadnd.cou8901.rap.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.example.uadnd.cou8901.rap.R;
import com.example.uadnd.cou8901.rap.db.Post;
import com.example.uadnd.cou8901.rap.gp.GsonParser;
import com.example.uadnd.cou8901.rap.net.RedditRESTAPIHandler;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import static java.lang.Thread.sleep;

/**
 * Implementation of App Widget functionality.
 */
public class RapTop2AppWidget extends AppWidgetProvider {
    private static final String USER_AGENT = "R.A.P/0.1 by cou8901" ;
    private static final String TOP_URL = "https://www.reddit.com/top.json";
    private static final String ACCESS_TOKEN = "DUMMY_TOKEN";
    public static ArrayList<Post> top2Posts = null;
    private static final String onClickTop1 = "ON_CLICK_TOP_1";
    private static final String onClickTop2 = "ON_CLICK_TOP_2";


    public void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {



        CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.rap_top2_app_widget);

        views.setTextViewText(R.id.appwidget_text_heading, widgetText); //widgetText

        //top2Posts = getTop2RedditPosts();
        if(top2Posts != null) {
            //

            String tvField1 = top2Posts.get(0).getSubreddit() + ":" + top2Posts.get(0).getTitle() ;
            String tvField2 = top2Posts.get(1).getSubreddit() + ":" + top2Posts.get(1).getTitle() ;


            views.setTextViewText(R.id.appwidget_text_1, tvField1);
            views.setTextViewText(R.id.appwidget_text_2, tvField2);
            //views.setImageViewIcon(R.id.iv_appwidget_1, R.drawable.icons8internetbrowser50);


            views.setOnClickPendingIntent(R.id.appwidget_text_1, getPendingIntent(context, onClickTop1));
            views.setOnClickPendingIntent(R.id.iv_appwidget_1, getPendingIntent(context, onClickTop1));

            views.setOnClickPendingIntent(R.id.appwidget_text_2, getPendingIntent(context, onClickTop2));
            views.setOnClickPendingIntent(R.id.iv_appwidget_2, getPendingIntent(context, onClickTop2));


        }
        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);

        if(onClickTop1.equals(intent.getAction())) {

            Uri webpage = Uri.parse(top2Posts.get(0).getUrl());
            Intent intent1 = new Intent(Intent.ACTION_VIEW).setData(webpage) ;  //new Intent(Intent.ACTION_VIEW, webpage);
            if (intent1.resolveActivity(context.getPackageManager()) != null) {
                context.startActivity(intent1);
            }

        } else if(onClickTop2.equals(intent.getAction())) {
             Uri webpage = Uri.parse(top2Posts.get(1).getUrl());
            Intent intent1 = new Intent(Intent.ACTION_VIEW).setData(webpage) ;  //new Intent(Intent.ACTION_VIEW, webpage);
            if (intent1.resolveActivity(context.getPackageManager()) != null) {
                context.startActivity(intent1);
            }

        }

    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        try {
            new AsyncGetTop2Posts().execute("").get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
        new AsyncGetTop2Posts().execute("");
    }


    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
    private PendingIntent getPendingIntent(Context context, String action) {
        Intent intent = new Intent(context, getClass());
        intent.setAction(action);
        return PendingIntent.getBroadcast(context, 0, intent, 0);
    }
    //https://www.reddit.com/top.json
    public class AsyncGetTop2Posts extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            RedditRESTAPIHandler restapiHandler = new RedditRESTAPIHandler(USER_AGENT, ACCESS_TOKEN); //No need to login or access token
            String jsonTop = restapiHandler.getJsonResponseWithoutAccessTokenHeader(TOP_URL);
            ArrayList<Post> alp = GsonParser.getPostsFromJsonUsingGsonParser(jsonTop);
            if(alp != null && alp.size() >= 2) {
                top2Posts = new ArrayList<Post>();
                top2Posts.add(alp.get(0));
                top2Posts.add(alp.get(1));
            }

            return jsonTop;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }
}

