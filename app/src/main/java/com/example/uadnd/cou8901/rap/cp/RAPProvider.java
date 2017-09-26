package com.example.uadnd.cou8901.rap.cp;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by dd2568 on 9/1/2017.
 */

public class RAPProvider extends ContentProvider {

    public static final int SUBREDDITS = 100 ; // select * from subreddits
    private static final int SUBREDDITS_WITH_ID = 101 ; //  ... where _ID = n

    public static final int POSTS = 200 ; // select * from subreddits
    public static final int POSTS_WITH_ID = 201 ; //  ... where _ID = n



    private static final UriMatcher uriMatcher = buildUriMatcher();

    private RAPDBHelper rapdbHelper;

    public static UriMatcher buildUriMatcher() {
        // Init matcher
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        //Subreddits
        uriMatcher.addURI(RAPContract.AUTHORITY, RAPContract.PATH_SUBREDDITS, SUBREDDITS);
        uriMatcher.addURI(RAPContract.AUTHORITY, RAPContract.PATH_SUBREDDITS_WITH_ID, SUBREDDITS_WITH_ID);

        //Posts
        uriMatcher.addURI(RAPContract.AUTHORITY, RAPContract.PATH_POSTS, POSTS);
        uriMatcher.addURI(RAPContract.AUTHORITY, RAPContract.PATH_POSTS_WITH_ID, POSTS_WITH_ID);

        return uriMatcher;

    }

    @Override
    public boolean onCreate() {
        Context context = getContext();
        rapdbHelper = new RAPDBHelper(context);

        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        final SQLiteDatabase database = rapdbHelper.getReadableDatabase();
        int match = uriMatcher.match(uri);
        Cursor cursor = null;

        switch (match) {
            case SUBREDDITS :
                cursor = database.query(RAPContract.Subreddits.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
            case SUBREDDITS_WITH_ID :
                cursor = database.query(RAPContract.Subreddits.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
            case POSTS :
                cursor = database.query(RAPContract.Posts.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
            case POSTS_WITH_ID :
                cursor = database.query(RAPContract.Posts.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;

        }

        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        throw new UnsupportedOperationException("Not yet implemented");
        //return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        //final SQLiteDatabase database = rapdbHelper.getReadableDatabase();
        final SQLiteDatabase database = rapdbHelper.getWritableDatabase();
        int match = uriMatcher.match(uri);
        long id;
        Uri returnUri = null;

        switch (match) {
            case SUBREDDITS :
            case SUBREDDITS_WITH_ID :
                id = database.insertWithOnConflict(RAPContract.Subreddits.TABLE_NAME, null, contentValues, SQLiteDatabase.CONFLICT_REPLACE);
                if(id > 0) {
                    returnUri = ContentUris.withAppendedId(RAPContract.Subreddits.SUBREDDITS_URI, id);
                }
                break;
            case POSTS :
            case POSTS_WITH_ID :
                id = database.insertWithOnConflict(RAPContract.Posts.TABLE_NAME, null, contentValues, SQLiteDatabase.CONFLICT_REPLACE);
                if(id > 0) {
                    returnUri = ContentUris.withAppendedId(RAPContract.Posts.POSTS_URI, id);
                }
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri:" + uri);

        }

        return returnUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }
}
