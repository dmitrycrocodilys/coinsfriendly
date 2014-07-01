package com.coinsfriendly.mobile.graphs;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import com.coinsfriendly.mobile.R;

/**
 * Created by dmitry on 7/1/14.
 */
public class GraphsActivity extends Activity {

    private WebView graphsWebView;
    private LinearLayout graphsLayout;

    private String GRAPHS_FULL_URL = "http://analytic.gur.engine.it/app/login";
    private String GRAPHS_FREE_URL = "http://analytic.gur.engine.it/app/free_version";

    private class MyWebViewClient extends WebViewClient
    {
        @Override
        public void onPageFinished(WebView view, String url)
        {
            Log.d("WebView", "onPageFinished()");
            super.onPageFinished(view, url);
            WindowManager manager = (WindowManager) GraphsActivity.this.getSystemService(Context.WINDOW_SERVICE);
            DisplayMetrics metrics = new DisplayMetrics();
            manager.getDefaultDisplay().getMetrics(metrics);
            metrics.widthPixels /= metrics.density;
            graphsWebView.loadUrl("javascript:var scale = " + metrics.widthPixels + " / document.body.scrollWidth; document.body.style.zoom = scale;");
        }

        @Override
        public boolean shouldOverrideUrlLoading (WebView view, String url)
        {
            view.loadUrl(url);
            return false;
        }
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadWebView();
    }

    private void loadWebView()
    {
        //graphsLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.graphs, null);
        setContentView(R.layout.graphs);
        graphsWebView = (WebView) findViewById(R.id.graphsWebView);
        graphsWebView.getSettings().setJavaScriptEnabled(true);
        graphsWebView.getSettings().setSupportZoom(true);
        graphsWebView.setWebViewClient(new MyWebViewClient());
        Bundle bundle = getIntent().getExtras();
        if (bundle.getString("version").equalsIgnoreCase("full")) {
            graphsWebView.loadUrl(GRAPHS_FULL_URL);
        } else
        {
            graphsWebView.loadUrl(GRAPHS_FREE_URL);
        }
    }

}
