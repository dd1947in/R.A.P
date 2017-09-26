package com.example.uadnd.cou8901.rap.db;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;

import com.example.uadnd.cou8901.rap.cp.RAPContract;

import java.util.ArrayList;

/**
 * Created by dd2568 on 9/9/2017.
 */

public class SQLiteRedditObjects {
    public static void persistSubreddit(Context context, Subreddit subreddit) {
        Uri uri;
        ContentValues contentValues = new ContentValues();
        //contentValues.put(RAPContract.Subreddits.COLUMN_DESCRIPTION, subreddit.getDescription());  // Field is commented out
        contentValues.put(RAPContract.Subreddits.COLUMN_DISPLAY_NAME, subreddit.getDisplay_name());
        contentValues.put(RAPContract.Subreddits.COLUMN_DISPLAY_NAME_PREFIXED, subreddit.getDisplay_name_prefixed());
        contentValues.put(RAPContract.Subreddits.COLUMN_HEADER_IMG, subreddit.getHeader_img());
        contentValues.put(RAPContract.Subreddits.COLUMN_ID, subreddit.getId());
        contentValues.put(RAPContract.Subreddits.COLUMN_LANG, subreddit.getLang());
        contentValues.put(RAPContract.Subreddits.COLUMN_NAME, subreddit.getName());
        contentValues.put(RAPContract.Subreddits.COLUMN_TITLE, subreddit.getTitle());

        ContentResolver contentResolver = context.getContentResolver();
        uri = contentResolver.insert(RAPContract.Subreddits.SUBREDDITS_URI, contentValues);
     }
     public  static  void persistPost(Context context, Post post) {
         Uri uri;
         ContentValues contentValues = new ContentValues();
         contentValues.put(RAPContract.Posts.COLUMN_CREATED, post.getCreated());
         contentValues.put(RAPContract.Posts.COLUMN_DOWNS, post.getDowns());
         contentValues.put(RAPContract.Posts.COLUMN_ID, post.getId());
         contentValues.put(RAPContract.Posts.COLUMN_IS_VIDEO, post.getIs_video());
         contentValues.put(RAPContract.Posts.COLUMN_PERMALINK, post.getPermalink());
         contentValues.put(RAPContract.Posts.COLUMN_SUBREDDIT, post.getSubreddit());
         contentValues.put(RAPContract.Posts.COLUMN_SUBREDDIT_TYPE, post.getSubreddit_type());
         contentValues.put(RAPContract.Posts.COLUMN_THUMBNAIL, post.getThumbnail());
         contentValues.put(RAPContract.Posts.COLUMN_TITLE, post.getTitle());
         contentValues.put(RAPContract.Posts.COLUMN_UPS, post.getUps());
         contentValues.put(RAPContract.Posts.COLUMN_URL, post.getUrl());

         ContentResolver contentResolver = context.getContentResolver();
         uri = contentResolver.insert(RAPContract.Posts.POSTS_URI, contentValues);
     }

     public static void persistMasterPostList(Context context, ArrayList<Post> alp) {
         if(alp != null & alp.size() > 0) {
             int alpSize = alp.size();
             for(int i = 0; i < alpSize; i++) {
                 Post post = alp.get(i);
                 //ensure post is populated.
                 if(post != null && post.getTitle() != null ) {
                     persistPost(context, post);
                 }
             }
         }

     }


}
