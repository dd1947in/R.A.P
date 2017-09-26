package com.example.uadnd.cou8901.rap.cp;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by dd2568 on 9/1/2017.
 */

public class RAPContract {
    //Database
    public static final String DATABASE_NAME = "rap.db";
    //Authority
    public static final String AUTHORITY = "com.example.uadnd.cou8901.rap";

    //Base Content URI
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    //paths for subreddits, posts
    public static final String PATH_SUBREDDITS = "subreddits" ;
    public static final String PATH_SUBREDDITS_WITH_ID = "subreddits/#" ; // with CP PK

    public static final String PATH_POSTS = "posts";
    public static final String PATH_POSTS_WITH_ID = "posts/#"; // with CP PK

    /*subreddits table*/
    public static final class Subreddits implements BaseColumns {
        // Subreddits URI
        public static final Uri SUBREDDITS_URI = BASE_CONTENT_URI.buildUpon()
                .appendPath(PATH_SUBREDDITS)
                .build();
        public static final Uri SUBREDDITS_WITH_ID_URI = BASE_CONTENT_URI.buildUpon()
                .appendPath(PATH_SUBREDDITS_WITH_ID)
                .build();

        //Subreddits Table Name
        public static final String TABLE_NAME = "subreddits" ;

        //"_ID" column will be PK
        // following column names as found in json
        public static final String COLUMN_ID = "id" ;
        public static final String COLUMN_TITLE = "title" ;
        public static final String COLUMN_DISPLAY_NAME = "display_name" ;
        public static final String COLUMN_DISPLAY_NAME_PREFIXED = "display_name_prefixed" ;
        public static final String COLUMN_HEADER_IMG = "header_img" ;
        // public static final String COLUMN_DESCRIPTION = "description" ;  // This field is deliberately commented  out
        public static final String COLUMN_LANG = "lang" ;
        public static final String COLUMN_NAME = "name" ; //kind + _ + id
    }

    public static final class Posts implements BaseColumns {
        //Posts URI
        public static final Uri POSTS_URI = BASE_CONTENT_URI.buildUpon()
                .appendPath(PATH_POSTS)
                .build();
        public static final Uri POSTS_WITH_ID_URI = BASE_CONTENT_URI.buildUpon()
                .appendPath(PATH_POSTS_WITH_ID)
                .build();

        //Posts Table Name
        public static final String TABLE_NAME = "posts" ;

        //"_ID" column will be PK
        // following column names as found in json
        public static final String COLUMN_ID = "id" ;
        public static final String COLUMN_TITLE = "title" ;
        public static final String COLUMN_SUBREDDIT = "subreddit" ;
        public static final String COLUMN_SUBREDDIT_TYPE = "subreddit_type" ;
        public static final String COLUMN_PERMALINK = "permalink" ;
        public static final String COLUMN_URL = "url" ;
        public static final String COLUMN_THUMBNAIL = "thumbnail" ;
        public static final String COLUMN_UPS = "ups" ;
        public static final String COLUMN_DOWNS = "downs" ;
        public static final String COLUMN_IS_VIDEO = "is_video" ;
        public static final String COLUMN_CREATED = "created" ;

    }
}
