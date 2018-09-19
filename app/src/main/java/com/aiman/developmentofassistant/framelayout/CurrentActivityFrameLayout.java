package com.aiman.developmentofassistant.framelayout;

import android.content.Context;
import android.os.Build;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


import com.aiman.developmentofassistant.R;
import com.aiman.developmentofassistant.developmentofassistant.MainActivity;
import com.aiman.developmentofassistant.service.MAccessibilityService;
import com.aiman.developmentofassistant.utils.ActivityUtil;
import com.aiman.developmentofassistant.utils.DialogUtil;
import com.aiman.developmentofassistant.utils.PermissionUtil;
import com.aiman.developmentofassistant.window.WindowViewContainer;



/**
 * Created by linsheng on 2018/7/31.
 */

public class CurrentActivityFrameLayout extends BaseInsertHolder{

    private Context mContext;
    private LinearLayout mSwitchLL; // 打开/关闭悬浮窗（根布局）
    private static TextView mSwitchTV; // 打开/关闭悬浮窗（提示文本）

    private static View mHintTV; // 权限提示

    private static LinearLayout mOverlayLL; // 悬浮窗权限提示（根布局）
    private static TextView mOverlayTV; // 悬浮窗权限（提示文本）
    private static Switch mOverlaySC; // 悬浮窗权限（开关按钮）

    private static LinearLayout mNotifyLL; // 通知栏权限提示（根布局）
    private static TextView mNotifyTV; // 通知栏权限（提示文本）
    private static Switch mNotifySC; // 通知栏权限（开关按钮）

    private static View mCloseV; // 关闭辅助服务按钮
    public CurrentActivityFrameLayout(Context applicationContext, ViewGroup parent) {
        super(parent);
        this.mContext = applicationContext;
    }

    @Override
    public View getInsertView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_current_activity, parent, false);
        initView(view);
        initViewListener();
        return view;
    }

    private void initView(View view) {
        mSwitchLL = view.findViewById(R.id.ll_switch);
        mSwitchTV = view.findViewById(R.id.tv_switch);
        mHintTV = view.findViewById(R.id.tv_hint);
        mOverlayLL = view.findViewById(R.id.ll_overlay);
        mOverlayTV = view.findViewById(R.id.tv_overlay);
        mOverlaySC = view.findViewById(R.id.sc_overlay);
        mNotifyLL = view.findViewById(R.id.ll_notify);
        mNotifyTV = view.findViewById(R.id.tv_notify);
        mNotifySC = view.findViewById(R.id.sc_notify);
        mCloseV = view.findViewById(R.id.tv_close);

        switchWindow();

    }

    /**
     * 初始化视图监听器
     */
    private void initViewListener() {
        // "显示/关闭悬浮窗"点击监听
        mSwitchLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.startGame();
                MainActivity.showInterstitial();
                switchWindow();
            }
        });
        // "悬浮窗权限"点击监听
        mOverlaySC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtil.turnToOverlayPermission(context);
            }
        });
        // "通知栏权限"点击监听
        mNotifySC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtil.turnToNotifyPermission(context);
            }
        });
        // "关闭辅助服务"点击监听
        mCloseV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtil.toAuthAccessibilityService(context);
            }
        });
    }

    /**
     * 显示/隐藏窗口视图
     */
    private static void switchWindow() {
        // 切换视图显示状态
        if (!PermissionUtil.hasOverlayPermission(context)) {
            DialogUtil.showOverlayAlertDialog(context);
            return;
        }
        // 检查用户是否已授权开启"辅助功能"
        if (!PermissionUtil.getServiceState(context, MAccessibilityService.SERVCE_NAME)) {
            DialogUtil.showAccessibilityServiceAlertDialog(context, context.getResources().getString(R.string.string_open_Floating_window_permission), "cancel", "ok");
            return;
        }
        WindowViewContainer.getInstance(context).switchWindowView();
        updateUI();
    }

    public static void updateUI() {
        // 设置"打开/关闭悬浮窗"提示
        boolean isShow = WindowViewContainer.getInstance(context).getWinodwViewShowState();
            mSwitchTV.setText(isShow ? R.string.string_service_close : R.string.string_service_start);
            // 设置"悬浮窗权限"显示样式（6.0以下默认不需要请求该权限）
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                mOverlayLL.setVisibility(View.GONE);
            } else {
                boolean overlay = PermissionUtil.hasOverlayPermission(context);
                mOverlayTV.setText(Html.fromHtml(context.getResources().getString(overlay ? R.string.string_service_overlay_tip2 : R.string.string_service_overlay_tip1)));
                mOverlaySC.setChecked(overlay);
                mOverlayLL.setVisibility(View.VISIBLE);
            }
            // 设置"通知权限"显示样式（4.4以下默认不需要请求该权限）
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
                mNotifyLL.setVisibility(View.GONE);
                mHintTV.setVisibility(View.GONE);
            } else {
                boolean notify = PermissionUtil.hasNotifyPermission(context);

                mNotifyTV.setText(Html.fromHtml(context.getResources().getString(notify ? R.string.string_service_notify_tip2 : R.string.string_service_notify_tip1)));
                mNotifySC.setChecked(notify);
                mNotifyLL.setVisibility(View.VISIBLE);
            }
            // 设置"关闭辅助服务按钮"的显示和隐藏
            if (PermissionUtil.getServiceState(context, MAccessibilityService.SERVCE_NAME)) {
                mCloseV.setVisibility(View.VISIBLE);
            } else {
                mCloseV.setVisibility(View.GONE);
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
//        updateUI();
    }
}
