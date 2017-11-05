package com.example.shubhraj.jennietasks.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Shubhraj on 25-10-2017.
 */

public class JennieContract
{
    public static final String CONTENT_AUTHORITY = "com.example.shubhraj.jennietasks";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_TODO = "jennie";

    public static final class JennieEntry implements BaseColumns
    {
        public static final String TABLE_NAME = "jennie";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_DONE = "done";

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_TODO).build();
        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE +
                CONTENT_AUTHORITY + "/" + PATH_TODO;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE +
                CONTENT_AUTHORITY + "/" + PATH_TODO;
    }
}
