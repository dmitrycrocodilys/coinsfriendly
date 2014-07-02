package com.coinsfriendly.mobile.graphs;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import com.coinsfriendly.mobile.R;

/**
 * Created by dmitry on 7/1/14.
 */
public class GraphsMenuActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graphsmenu);
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
