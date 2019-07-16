package com.example.alcchallenge1khmaieshassen;

import android.Manifest;
import android.net.http.SslError;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import pub.devrel.easypermissions.EasyPermissions;

public class AboutActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle(R.string.about_toolbar_title);

        String[] perms = { Manifest.permission.INTERNET };
        if (EasyPermissions.hasPermissions(this, perms)) {
            // Have permissions, do the thing!
            WebView mWebview = (WebView)findViewById(R.id.webView);
            mWebview.getSettings().setJavaScriptEnabled(true);
            mWebview.getSettings().setDomStorageEnabled(true);
            mWebview.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
            mWebview.setWebViewClient(new WebViewClient(){
                @Override
                public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                    // DO NOT CALL SUPER METHOD
                    handler.proceed(); // Ignore SSL certificate errors
                }
            });
            mWebview.loadUrl("http://andela.com/alc/");
        } else {
            // Ask for both permissions
            EasyPermissions.requestPermissions(this, getString(R.string.rationale_internet),
                    123, perms);
        }


    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }


}
