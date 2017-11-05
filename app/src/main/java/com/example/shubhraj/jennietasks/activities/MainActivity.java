package com.example.shubhraj.jennietasks.activities;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.example.shubhraj.jennietasks.R;
import com.example.shubhraj.jennietasks.adapters.JennieCursorAdapter;
import com.example.shubhraj.jennietasks.data.JennieContract;
import com.example.shubhraj.jennietasks.fragments.AddTaskDialogFragment;

public class MainActivity extends AppCompatActivity implements LoaderCallbacks<Cursor>,AddTaskDialogFragment.AddTaskDialogListener
{
    private RecyclerView recyclerView;
    private JennieCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLinearLayoutManager);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setHasFixedSize(true);
        adapter = new JennieCursorAdapter(this,null);
        recyclerView.setAdapter(adapter);
        FloatingActionButton actionButton = (FloatingActionButton) findViewById(R.id.fab);
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddTaskDialogFragment dialogFragment = new AddTaskDialogFragment();
                dialogFragment.show(getSupportFragmentManager(),"addTask");
            }
        });

        getSupportLoaderManager().initLoader(0, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this, JennieContract.JennieEntry.CONTENT_URI, null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        adapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        adapter.swapCursor(null);
    }

    @Override public void onDialogPositiveClick(String stringValue) {
        if (!TextUtils.isEmpty(stringValue)) {
            getContentResolver().insert(JennieContract.JennieEntry.CONTENT_URI, getTodoListContentValues(stringValue));
        }
    }

    @Override public void onDialogNegativeClick(DialogFragment dialog) {

    }

    public void onTodoItemChange(int todoID, boolean done) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(JennieContract.JennieEntry.COLUMN_DONE, done ? 1 : 0);
        String[] mSelectionArgs = {Integer.toString(todoID)};
        getContentResolver().update(JennieContract.JennieEntry.CONTENT_URI, contentValues, null, mSelectionArgs);
    }

    private ContentValues getTodoListContentValues(String stringValue) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(JennieContract.JennieEntry.COLUMN_DESCRIPTION, stringValue);
        return contentValues;
    }
}
