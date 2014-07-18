package com.coinsfriendly.mobile.graphs;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import com.coinsfriendly.mobile.R;

/**
 * Created by dmitry on 7/1/14.
 */
public class GraphsMenuActivity extends Activity {

    private static final String PREFS_NAME = "GRAPHS_PREFS";
    private SharedPreferences settings;

    private void aboutAlert(final SharedPreferences.Editor editor)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(GraphsMenuActivity.this);
        final AlertDialog alert = builder.create();
        alert.setTitle("DISCLAIMER");
        alert.setMessage(getString(R.string.about));
        alert.setButton(DialogInterface.BUTTON_POSITIVE, "Accept", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                editor.putBoolean("firstTimeLoad", true);
                editor.commit();
            }
        });
        alert.setOnDismissListener(new DialogInterface.OnDismissListener()
        {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                if (!settings.getBoolean("firstTimeLoad", false))
                {
                    System.exit(0);
                }
            }
        });
        alert.show();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graphsmenu);
        settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        if (!settings.getBoolean("firstTimeLoad", false))
        {
            aboutAlert(editor);
        }
    }

    public void item1Clicked(View v)
    {
        Intent intent = new Intent(this, GraphsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("version", "full");
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void item2Clicked(View v)
    {
        Intent intent = new Intent(this, GraphsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("version", "free");
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void logoClicked(View v)
    {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.coins_friendly_link)));
        startActivity(browserIntent);
    }

}
