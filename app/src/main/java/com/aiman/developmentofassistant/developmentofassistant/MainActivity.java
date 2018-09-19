package com.aiman.developmentofassistant.developmentofassistant;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import android.telephony.TelephonyManager;
import android.text.Html;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.aiman.developmentofassistant.R;
import com.aiman.developmentofassistant.framelayout.CellphoneinformationFrameLayout;
import com.aiman.developmentofassistant.framelayout.CurrentActivityFrameLayout;
import com.aiman.developmentofassistant.framelayout.DeveloperdebugFrameLayout;
import com.aiman.developmentofassistant.framelayout.FeedBackFrameLayout;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

/**
 * Created by linsheng on 2018/7/31.
 */

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    public static MainActivity mActivity;
    private FrameLayout frameLayout;
    private DeveloperdebugFrameLayout developerdebugFrameLayout;
    private CellphoneinformationFrameLayout cellphoneinformationFrameLayout;
    private CurrentActivityFrameLayout currentActivityFrameLayout;
    public static TelephonyManager phone;
    public static WifiManager wifi;
    public static Display display;
    private static DisplayMetrics metrics;
    public static DisplayMetrics book;
    public static InterstitialAd mInterstitialAd;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MobileAds.initialize(this, "ca-app-pub-4610846548122626~9522787916");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        frameLayout = (FrameLayout)findViewById(R.id.container);
        developerdebugFrameLayout  =  new DeveloperdebugFrameLayout(getApplicationContext(),frameLayout);
        frameLayout.addView(developerdebugFrameLayout.getInsertView());
        //这是带Home旋转开关按钮
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        //这是不带Home旋转开关按钮
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer,  R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();//该方法会自动和Toolbar关联, 将开关的图片显示在了Toolbar上

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new DrawerLayoutListener());

        phone = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        wifi = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        display = getWindowManager().getDefaultDisplay();

        metrics = getResources().getDisplayMetrics();
        book=new DisplayMetrics();
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-4610846548122626/2382664492");
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                startGame();
            }
        });
    }

    public static void startGame() {

        // Request a new ad if one isn't already loaded, hide the button, and kick off the timer.
        if (!mInterstitialAd.isLoading() && !mInterstitialAd.isLoaded()) {
            AdRequest adRequest = new AdRequest.Builder().build();
            mInterstitialAd.loadAd(adRequest);
        }
    }

    public static void showInterstitial() {
        // Show the ad if it's ready. Otherwise toast and restart the game.
        if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            startGame();
        }
    }

    @Override
    public void onBackPressed()
    {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private class DrawerLayoutListener implements NavigationView.OnNavigationItemSelectedListener {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            }
            switch (item.getItemId()){
                case R.id.nav_camera:
                    startGame();
                    showInterstitial();
                    developerdebugFrameLayout  =  new DeveloperdebugFrameLayout(getApplicationContext(),frameLayout);
                    if (null != frameLayout){
                        frameLayout.removeAllViews();
                    }
                    frameLayout.addView(developerdebugFrameLayout.getInsertView());
                    break;
                case R.id.nav_gallery:
                    startGame();
                    showInterstitial();
                    cellphoneinformationFrameLayout  =  new CellphoneinformationFrameLayout(getApplicationContext(),frameLayout);
                    if (null != frameLayout){
                        frameLayout.removeAllViews();
                    }
                    frameLayout.addView(cellphoneinformationFrameLayout.getInsertView());
                    break;
                case R.id.nav_slideshow:
                    startGame();
                    showInterstitial();
                    currentActivityFrameLayout = new CurrentActivityFrameLayout(getApplicationContext(),frameLayout);
                    if (null != frameLayout){
                        frameLayout.removeAllViews();
                    }
                    frameLayout.addView(currentActivityFrameLayout.getInsertView());
                    break;
                case R.id.nav_manage:
                    startGame();
                    showInterstitial();
                    Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=com.aiman.joke.jokedaquan");
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                    break;
                case R.id.nav_share:
                    startGame();
                    showInterstitial();
                    Uri uritool = Uri.parse("https://play.google.com/store/apps/details?id=com.aiman.wallapperbackground.hdwallpaper");
                    Intent intenttool = new Intent(Intent.ACTION_VIEW, uritool);
                    startActivity(intenttool);
                    break;
                case R.id.nav_send:
                    startGame();
                    showInterstitial();
                    showABout();
                    break;
            }


            return true;
        }
    }

    private void showABout() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.abuout);
        builder.setMessage(this.getResources().getString(R.string.email) + "\r\n" +
                "\r\n"+
                this.getResources().getString(R.string.GitHub) + "\r\n"+
                "\r\n"+
                this.getResources().getString(R.string.CSDN)
        );
        builder.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (currentActivityFrameLayout != null  ){
            CurrentActivityFrameLayout.updateUI();
        }
    }

    @Override
    protected void onDestroy() {
        mActivity = null;
        super.onDestroy();
    }
}
