package com.example.uadnd.cou8901.rap.cp;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by dd2568 on 9/1/2017.
 */

public class RAPDBHelper  extends SQLiteOpenHelper {
    //private static final String DATABASE_NAME = "rap.db";
    public static final int VERSION = 1;

    public RAPDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public RAPDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }
    public  RAPDBHelper(Context context){
        super(context, RAPContract.DATABASE_NAME, null, VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SUBREDDITS_SQL = " CREATE TABLE " +
                RAPContract.Subreddits.TABLE_NAME + " (" +
                RAPContract.Subreddits._ID + " INTEGER PRIMARY KEY, " +   // PK from base columns
                //RAPContract.Subreddits.COLUMN_DESCRIPTION + " TEXT, " +   // Field is commented out
                RAPContract.Subreddits.COLUMN_DISPLAY_NAME + " TEXT, " +
                RAPContract.Subreddits.COLUMN_DISPLAY_NAME_PREFIXED + " TEXT, " +
                RAPContract.Subreddits.COLUMN_HEADER_IMG + " TEXT, " +
                RAPContract.Subreddits.COLUMN_ID + " TEXT, " +
                RAPContract.Subreddits.COLUMN_LANG + " TEXT, " +
                RAPContract.Subreddits.COLUMN_NAME + " TEXT, " +
                RAPContract.Subreddits.COLUMN_TITLE + " TEXT ) " ;
        final String POSTS_SQL = " CREATE TABLE " +
                RAPContract.Posts.TABLE_NAME + " (" +
                RAPContract.Posts._ID + " INTEGER PRIMARY KEY, " +   // PK from base columns
                RAPContract.Posts.COLUMN_CREATED + " TEXT, " +
                RAPContract.Posts.COLUMN_DOWNS + " TEXT, " +
                RAPContract.Posts.COLUMN_ID + " TEXT, " +
                RAPContract.Posts.COLUMN_IS_VIDEO + " TEXT, " +
                RAPContract.Posts.COLUMN_PERMALINK + " TEXT, " +
                RAPContract.Posts.COLUMN_SUBREDDIT + " TEXT, " +
                RAPContract.Posts.COLUMN_SUBREDDIT_TYPE + " TEXT, " +
                RAPContract.Posts.COLUMN_THUMBNAIL + " TEXT, " +
                RAPContract.Posts.COLUMN_TITLE + " TEXT, " +
                RAPContract.Posts.COLUMN_UPS + " TEXT, " +
                RAPContract.Posts.COLUMN_URL + " TEXT ) " ;


        sqLiteDatabase.execSQL(SUBREDDITS_SQL);
        sqLiteDatabase.execSQL(POSTS_SQL);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        final String SUBREDDITS_DROP_SQL = " DROP TABLE IF EXISTS " + RAPContract.Subreddits.TABLE_NAME ;
        final String POSTS_DROP_SQL = " DROP TABLE IF EXISTS " + RAPContract.Posts.TABLE_NAME ;

        sqLiteDatabase.execSQL(SUBREDDITS_DROP_SQL);
        sqLiteDatabase.execSQL(POSTS_DROP_SQL);
        onCreate(sqLiteDatabase);
    }
    public void onRefreshDB(SQLiteDatabase sqLiteDatabase){
        onUpgrade(sqLiteDatabase, VERSION, VERSION);
    }
}
