package com.aiman.developmentofassistant.framelayout;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by linsheng on 2018/7/27.
 */

public abstract class BaseInsertHolder {
    protected ViewGroup parent;
    protected static Context context;


    public BaseInsertHolder(ViewGroup parent) {
        this.parent = parent;
        this.context = parent.getContext();
    }

    abstract View getInsertView();

    abstract void onStart();

    abstract void onStop();

    abstract int getDefaultBackgroundDrawable();

    abstract String getBackgroundPicUrl();

    abstract  void onResume();

}
