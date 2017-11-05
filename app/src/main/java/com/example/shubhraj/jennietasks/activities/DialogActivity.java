package com.example.shubhraj.jennietasks.activities;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shubhraj.jennietasks.R;

public class DialogActivity extends AppCompatActivity {
    private Button mButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        mButton = (Button) findViewById(R.id.button_load);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater inflater = LayoutInflater.from(DialogActivity.this);
                View dialog = inflater.inflate(R.layout.task_create_layout,null);
                AlertDialog.Builder builder = new AlertDialog.Builder(DialogActivity.this);
                builder.setView(dialog);
                final EditText editText = (EditText) view.findViewById(R.id.edit_text);
                builder.setCancelable(false)
                        .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(DialogActivity.this,"Added",Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setTitle("Add a new Task")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(DialogActivity.this,"Cancelled",Toast.LENGTH_SHORT).show();
                    }
                }).show();
            }
        });
    }
}
