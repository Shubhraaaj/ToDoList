package com.example.shubhraj.jennietasks.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import static com.example.shubhraj.jennietasks.data.JennieContract.CONTENT_AUTHORITY;
import static com.example.shubhraj.jennietasks.data.JennieContract.JennieEntry.CONTENT_ITEM_TYPE;
import static com.example.shubhraj.jennietasks.data.JennieContract.JennieEntry.CONTENT_TYPE;
import static com.example.shubhraj.jennietasks.data.JennieContract.JennieEntry.CONTENT_URI;
import static com.example.shubhraj.jennietasks.data.JennieContract.JennieEntry.TABLE_NAME;
import static com.example.shubhraj.jennietasks.data.JennieContract.PATH_TODO;

/**
 * Created by Shubhraj on 25-10-2017.
 */

public class JennieProvider extends ContentProvider
{
    private static final int JENNIE = 100;
    private static final int JENNIE_ID = 101;

    private static final UriMatcher mUriMatcher = buildUriMatcher();

    private JennieDbHelper dbHelper;

    private static UriMatcher buildUriMatcher() {
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = CONTENT_AUTHORITY;

        matcher.addURI(authority, PATH_TODO, JENNIE);
        matcher.addURI(authority, PATH_TODO + "/#", JENNIE_ID);
        return matcher;
    }

    @Override
    public boolean onCreate() {
        dbHelper = new JennieDbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projections, @Nullable String selections,
                        @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Cursor mCursor;
        switch (mUriMatcher.match(uri))
        {
            case JENNIE:
                mCursor = dbHelper.getReadableDatabase().query(
                        TABLE_NAME,
                        projections,
                        selections,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;

            case JENNIE_ID:
                mCursor = dbHelper.getReadableDatabase().query(
                        TABLE_NAME,
                        projections,
                        JennieContract.JennieEntry._ID + " = '" + ContentUris.parseId(uri) + "'",
                        null,
                        null,
                        null,
                        sortOrder);
                break;
            default:
                throw new UnsupportedOperationException("Unknown URI: " + uri);
        }
        mCursor.setNotificationUri(getContext().getContentResolver(),uri);
        return mCursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        final int match = mUriMatcher.match(uri);
        switch (match) {
            case JENNIE:
                return CONTENT_TYPE;
            case JENNIE_ID:
                return CONTENT_ITEM_TYPE;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        final SQLiteDatabase db = dbHelper.getWritableDatabase();
        final int match = mUriMatcher.match(uri);
        Uri returnUri;
        switch (match)
        {
            case JENNIE:
                long id = db.insert(TABLE_NAME, null, contentValues);
                if (id > 0)
                {
                    returnUri = CONTENT_URI;
                }
                else
                {
                    throw new android.database.SQLException("Failed to insert data into " + uri);
                }
                break;
            default:
                throw new UnsupportedOperationException("Unknown URI:" + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return returnUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selections, @Nullable String[] selectionArgs) {
        final SQLiteDatabase db = dbHelper.getWritableDatabase();
        final int match = mUriMatcher.match(uri);
        int rowsDeleted;
        switch (match)
        {
            case JENNIE:
                rowsDeleted = db.delete(TABLE_NAME, selections, selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException("Unknwon Uri: " + uri);
        }
        if (selections == null||rowsDeleted != 0)
        {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsDeleted;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues,
                      @Nullable String selections, @Nullable String[] selectionArgs) {
        final SQLiteDatabase db = dbHelper.getWritableDatabase();
        final int match = mUriMatcher.match(uri);
        int rowsUpdated;
        switch (match)
        {
            case JENNIE:
                rowsUpdated = db.update(TABLE_NAME, contentValues, selections, selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException("Unknwon Uri: " + uri);
        }
        if (rowsUpdated != 0)
        {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsUpdated;
    }
}
