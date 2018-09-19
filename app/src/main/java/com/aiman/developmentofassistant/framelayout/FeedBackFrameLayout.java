package com.aiman.developmentofassistant.framelayout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aiman.developmentofassistant.R;


/**
 * Created by linsheng on 2018/7/27.
 */

@SuppressLint("ValidFragment")
public class FeedBackFrameLayout extends BaseInsertHolder {

    private Context mContext;
    public FeedBackFrameLayout(Context applicationContext, ViewGroup parent) {
        super(parent);
        this.mContext = applicationContext;

    }

    @Override
    public View getInsertView() {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.layout_feedback_debug, parent, false);
        return inflate;
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
}
