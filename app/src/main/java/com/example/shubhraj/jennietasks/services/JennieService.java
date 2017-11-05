package com.example.shubhraj.jennietasks.services;

import android.app.IntentService;
import android.content.ContentValues;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import static com.example.shubhraj.jennietasks.data.JennieContract.JennieEntry.COLUMN_DESCRIPTION;
import static com.example.shubhraj.jennietasks.data.JennieContract.JennieEntry.CONTENT_URI;

/**
 * Created by Shubhraj on 25-10-2017.
 */

public class JennieService extends IntentService
{
    public static final String EXTRA_TASK_DESCRIPTION = "extra_task";
    private static final String TAG = "DebuggingTag";

    public JennieService() {
        super("JennieService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String taskDescription = intent.getStringExtra(EXTRA_TASK_DESCRIPTION);
        Log.d(TAG,"ADDED:"+taskDescription);
        ContentValues values = new ContentValues();
        values.put(COLUMN_DESCRIPTION, taskDescription);
        getContentResolver().insert(CONTENT_URI,values);
    }
}
