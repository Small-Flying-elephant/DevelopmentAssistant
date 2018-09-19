package com.aiman.developmentofassistant.framelayout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.aiman.developmentofassistant.Adapter.DeveloperDebugAdapter;
import com.aiman.developmentofassistant.R;
import com.aiman.developmentofassistant.bean.DeveloperDebugBean;
import com.aiman.developmentofassistant.developmentofassistant.MainActivity;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by linsheng on 2018/7/27.
 */

@SuppressLint("ValidFragment")
public class DeveloperdebugFrameLayout extends BaseInsertHolder implements AdapterView.OnItemClickListener {

    private Handler mHandler = new Handler(Looper.getMainLooper());
    private Context mContext;
    private List<DeveloperDebugBean> developerDebugBeanslist;
    public DeveloperdebugFrameLayout(Context applicationContext, ViewGroup parent) {
        super(parent);
        mContext = applicationContext;
    }

    @Override
    public View getInsertView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_developer_debug, parent, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        GridView gridView = (GridView)view.findViewById(R.id.developer_debug_gridview);
        initData();
        DeveloperDebugAdapter mDeveloperDebugAdapter = new DeveloperDebugAdapter(mContext,developerDebugBeanslist);
        gridView.setAdapter(mDeveloperDebugAdapter);
        gridView.setOnItemClickListener(this);
    }

    private void initData() {
        int[] imageView = {R.drawable.debug_layout_bounds,R.drawable.debug_overdraw,R.drawable.debug_surface_update,
                R.drawable.debug_strict_mode, R.drawable.debug_pointer_location,R.drawable.debug_gpu_rending,R.drawable.debug_gpu_view_update,
                R.drawable.debug_track_gpu_frame,R.drawable.debug_dev_options,R.drawable.debug_settings,
                R.drawable.debug_locale_settings,R.drawable.debug_dont_keep_activities,R.drawable.debug_stay_awake,R.drawable.debug_usb_debugging,
                R.drawable.debug_running_services};
        int[] textView = {R.string.app_framelayout_devoleper_debug_one,R.string.app_framelayout_devoleper_debug_two,R.string.app_framelayout_devoleper_debug_three,R.string.app_framelayout_devoleper_debug_four,R.string.app_framelayout_devoleper_debug_five,
        R.string.app_framelayout_devoleper_debug_six,R.string.app_framelayout_devoleper_debug_seven,R.string.app_framelayout_devoleper_debug_eight,R.string.app_framelayout_devoleper_debug_nine,R.string.app_framelayout_devoleper_debug_ten,R.string.app_framelayout_devoleper_debug_eleven,R.string.app_framelayout_devoleper_debug_twelve,R.string.app_framelayout_devoleper_debug_thirteen,R.string.app_framelayout_devoleper_debug_fourteen,R.string.app_framelayout_devoleper_debug_fifteen};

    developerDebugBeanslist = new ArrayList<DeveloperDebugBean>();

    for (int i = 0 ;i < imageView.length ; i ++){

        DeveloperDebugBean debugBean = new DeveloperDebugBean();

        debugBean.setImageView(imageView[i]);
        debugBean.setTextName(textView[i]);
        developerDebugBeanslist.add(debugBean);
    }


    }

    @Override
    void onStart() {
    }

    @Override
    void onStop() {

    }

    @Override
    int getDefaultBackgroundDrawable() {
        return 0;
    }

    @Override
    String getBackgroundPicUrl() {
        return null;
    }

    @Override
    void onResume() {

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


        switch (position){
            case 0:
                MainActivity.startGame();
                MainActivity.showInterstitial();
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DEVELOPMENT_SETTINGS);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
                break;
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 11:
            case 12:
            case 13:
                MainActivity.startGame();
                MainActivity.showInterstitial();
                Intent intents = new Intent(Settings.ACTION_APPLICATION_DEVELOPMENT_SETTINGS);
                intents.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intents);
                break;
            case 14:
                MainActivity.startGame();
                MainActivity.showInterstitial();
                Toast.makeText(mContext,"Is developing",Toast.LENGTH_LONG).show();
                break;
            case 10:
                MainActivity.startGame();
                MainActivity.showInterstitial();
                Intent intentLaunger = new Intent(Settings.ACTION_LOCALE_SETTINGS);
                intentLaunger.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intentLaunger);
                break;
            case 9:
                MainActivity.startGame();
                MainActivity.showInterstitial();
                Intent intentSystemSetting =  new Intent(Settings.ACTION_SETTINGS);
                intentSystemSetting.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intentSystemSetting);
        }
    }

}
