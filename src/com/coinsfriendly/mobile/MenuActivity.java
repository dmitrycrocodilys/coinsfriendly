package com.coinsfriendly.mobile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    public void item1Clicked(View v)
    {
        startActivity(new Intent(this, PointOfSaleActivity.class));
    }

    public void item2Clicked(View v)
    {
        startActivity(new Intent(this, TxHistoryActivity.class));
    }

    public void item3Clicked(View v)
    {
        startActivity(new Intent(this, InviteActivity.class));
    }
}
