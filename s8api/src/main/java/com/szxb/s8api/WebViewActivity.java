package com.szxb.s8api;

import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import butterknife.BindView;
import butterknife.ButterKnife;


public class WebViewActivity extends AppCompatActivity {

    @BindView(R.id.mtoolbar)
    Toolbar mtoolbar;
    @BindView(R.id.webview)
    WebView webview;
    @BindView(R.id.activity_web_view)
    LinearLayout activityWebView;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;


    String url = "http://www.baidu.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        mtoolbar.setTitle("");
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        webview.getSettings().setJavaScriptEnabled(true);
        WebSettings settings = webview.getSettings();
        if (Build.VERSION.SDK_INT == 17) {
            settings.setDisplayZoomControls(false);
        }
        settings.setLoadWithOverviewMode(true);

        //分辨适应
        DisplayMetrics metrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int mDensity = metrics.densityDpi;
        switch (mDensity) {
            case 120:
                settings.setDefaultZoom(WebSettings.ZoomDensity.CLOSE);
                break;
            case 160:
                settings.setDefaultZoom(WebSettings.ZoomDensity.MEDIUM);
                break;
            case 240:
                settings.setDefaultZoom(WebSettings.ZoomDensity.FAR);
                break;
            default:
                break;
        }
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        webview.setWebChromeClient(new myWebChromeClien());
        webview.setWebViewClient(new MyWebViewClient());
        webview.loadUrl(url);
    }


    class MyWebViewClient extends WebViewClient {
        public MyWebViewClient() {

        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            super.onReceivedError(view, request, error);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            view.loadUrl(url);
            return true;
        }
    }

    class myWebChromeClien extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            progressBar.setProgress(newProgress);
            if (newProgress == 100)
                progressBar.setVisibility(View.GONE);

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}
