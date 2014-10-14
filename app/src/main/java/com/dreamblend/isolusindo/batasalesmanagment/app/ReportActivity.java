package com.dreamblend.isolusindo.batasalesmanagment.app;

import android.app.ActionBar;
import android.content.Intent;
import android.net.http.SslError;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class ReportActivity extends ActionBarActivity {

    String id;
    private WebView webViewReport;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        Intent receive = getIntent();
        id = receive.getExtras().getString("username-id");
        webViewReport = (WebView) findViewById(R.id.webViewReport);
        webViewReport.setWebViewClient(new webViewClient());
        webViewReport.getSettings().setJavaScriptEnabled(true);

        webViewReport.loadUrl("http://wearedreamblend.com/bata/dsm_report.php?bataID="+id);

    }

    class webViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            super.onReceivedSslError(view, handler, error);
            handler.proceed();
        }
    }

}
