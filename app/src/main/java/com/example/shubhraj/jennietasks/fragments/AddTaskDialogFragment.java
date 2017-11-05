package com.example.shubhraj.jennietasks.fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.example.shubhraj.jennietasks.R;
import com.example.shubhraj.jennietasks.services.JennieService;

import static com.example.shubhraj.jennietasks.services.JennieService.EXTRA_TASK_DESCRIPTION;

/**
 * Created by Shubhraj on 25-10-2017.
 */

public class AddTaskDialogFragment extends DialogFragment
{

    AddTaskDialogListener mListener;

    @Override public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (AddTaskDialogListener) context;
        }
        catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement AddTaskDialogListener");
        }
    }

    public static AddTaskDialogFragment getInstance(String isTask)
    {
        Bundle bundle = new Bundle();
        bundle.putString("ID_TASK",isTask);
        AddTaskDialogFragment fragment = new AddTaskDialogFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(R.string.add_new_task_label);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.task_create_layout, null, false);
        final EditText textView = (EditText) view.findViewById(R.id.edit_text);
        builder.setView(view);
        builder.setPositiveButton(getString(R.string.add_label), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(getContext(), JennieService.class);
                intent.putExtra(EXTRA_TASK_DESCRIPTION, textView.getText().toString());
                getActivity().startService(intent);
            }
        }).setNegativeButton(getString(R.string.cancel_label), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //TODO: Add negative button operation
            }
        });
        return builder.create();
    }

    public interface AddTaskDialogListener {
        public void onDialogPositiveClick(String inputValue);

        public void onDialogNegativeClick(DialogFragment dialog);
    }
}
