package com.example.shubhraj.jennietasks.adapters;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.shubhraj.jennietasks.R;
import com.example.shubhraj.jennietasks.data.JennieContract;

/**
 * Created by Shubhraj on 26-10-2017.
 */

public class JennieCursorAdapter extends RecyclerView.Adapter<JennieCursorAdapter.MyViewHolder>
{
    private Cursor cursor;

    public JennieCursorAdapter(Context context, Cursor cursor) {
        this.cursor = cursor;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        cursor.moveToPosition(position);
        holder.bindView(cursor);

    }

    @Override
    public int getItemCount() {
        return cursor != null ? cursor.getCount() : 0;
    }

    public void swapCursor(Cursor cursor) {
        this.cursor = cursor;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        private TextView mTextView;
        private CheckBox mCheckBox;
        private int task_id = -1;
        public MyViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.todo_item_description);
            mCheckBox = (CheckBox) itemView.findViewById(R.id.todo_item_checkbox);
        }

        public void bindView(Cursor cursor)
        {
            task_id = cursor.getInt(cursor.getColumnIndex(JennieContract.JennieEntry._ID));
            mTextView.setText(cursor.getString(cursor.getColumnIndex(JennieContract.JennieEntry.COLUMN_DESCRIPTION)));
            boolean isTaskDone = cursor.getInt(cursor.getColumnIndex(JennieContract.JennieEntry.COLUMN_DONE)) == 1;
            mCheckBox.setChecked(isTaskDone);
        }
    }

}