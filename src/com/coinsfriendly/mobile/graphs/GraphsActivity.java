package com.coinsfriendly.mobile.graphs;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import com.coinsfriendly.mobile.R;

/**
 * Created by dmitry on 7/1/14.
 */
public class GraphsActivity extends Activity {

    private WebView graphsWebView;
    private LinearLayout graphsLayout;
    private ProgressBar progressBar;

    private String GRAPHS_FULL_URL = "http://analytic.coinsfriendly.com/app/login";
    private String GRAPHS_FREE_URL = "http://analytic.coinsfriendly.com/app/free_version";

    private class MyWebViewClient extends WebViewClient
    {
        @Override
        public void onPageFinished(WebView view, String url)
        {
            Log.d("WebView", "onPageFinished()");
            WindowManager manager = (WindowManager) GraphsActivity.this.getSystemService(Context.WINDOW_SERVICE);
            DisplayMetrics metrics = new DisplayMetrics();
            manager.getDefaultDisplay().getMetrics(metrics);
            metrics.widthPixels /= metrics.density;
            graphsWebView.loadUrl("javascript:var scale = " + metrics.widthPixels + " / document.body.scrollWidth; document.body.style.zoom = scale;");
            Thread viewThread = new Thread() {
                public void run() {
                    runOnUiThread(new Runnable() {
                        public void run() {
                            progressBar.setVisibility(View.GONE);
                            graphsWebView.setVisibility(View.VISIBLE);
                        }
                    });
                }
            };
            viewThread.start();
        }

        @Override
        public boolean shouldOverrideUrlLoading (WebView view, String url)
        {
            Log.d("WEBVIEW", "shouldOverrideUrlLoading (WebView view, String url)");
            Thread viewThread = new Thread() {
                public void run() {
                    runOnUiThread(new Runnable() {
                        public void run() {
                            progressBar.setVisibility(View.VISIBLE);
                            graphsWebView.setVisibility(View.INVISIBLE);
                        }
                    });
                }
            };
            viewThread.start();
            view.loadUrl(url);
            return false;
        }
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graphs);
        loadWebView();
    }

    private void loadWebView()
    {
        graphsWebView = (WebView) findViewById(R.id.graphsWebView);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        graphsWebView.getSettings().setJavaScriptEnabled(true);
        graphsWebView.getSettings().setSupportZoom(true);
        graphsWebView.setWebViewClient(new MyWebViewClient());
        Bundle bundle = getIntent().getExtras();
        if (bundle.getString("version").equalsIgnoreCase("full"))
        {
            graphsWebView.loadUrl(GRAPHS_FULL_URL);
        }
        else
        {
            graphsWebView.loadUrl(GRAPHS_FREE_URL);
        }
    }

    public void logoClicked(View v)
    {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.coins_friendly_link)));
        startActivity(browserIntent);
    }

}
