package com.filipkesteli.drinkingroad4;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

/**
 * Created by Filip on 31.5.2016..
 */
public class PROBAdialog extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final MapsActivity mapsActivity = (MapsActivity) getActivity();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Choose your drink strategy");
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View v = layoutInflater.inflate(R.layout.insert_text_dialog, null);
        builder.setView(v);

        builder.setNeutralButton("See strategy map and drink again", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(getActivity(), MapsActivity.class);
                dismiss();
            }
        });
        handleIntents();
        return builder.create();
    }

    private void handleIntents() {
        Intent intent = getActivity().getIntent();
        if (intent.hasExtra(MapsActivity.INTENT_TO_DIALOG_FRAGMENT)) {

        }
    }

}
