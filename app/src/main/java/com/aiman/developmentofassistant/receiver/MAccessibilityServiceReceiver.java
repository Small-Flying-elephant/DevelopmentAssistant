package com.aiman.developmentofassistant.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import com.aiman.developmentofassistant.service.MAccessibilityService;
import com.aiman.developmentofassistant.window.WindowViewContainer;


/**
 * Created by linsheng on 2018/7/31.
 */

public class MAccessibilityServiceReceiver extends BroadcastReceiver {

    /**
     * 显示/隐藏悬浮窗Action名称
     */
    public static final String SWITCH_ACTION = "android.intent.action.SWITCH";

    /**
     * 关闭悬浮窗Action名称
     */
    public static final String CLOSE_ACTION = "android.intent.action.CLOSE";

    @Override
    public void onReceive(Context context, Intent intent) {
        // 只处理"辅助服务"Intent消息
        if (!(context instanceof MAccessibilityService)) {
            return;
        }
        if (intent.getAction() == null) {
            return;
        }
        switch (intent.getAction()) {
            case SWITCH_ACTION: // 显示/隐藏悬浮窗
                WindowViewContainer.getInstance(context).switchWindowView();
                break;
            case CLOSE_ACTION: // 关闭悬浮窗
                MAccessibilityService service = (MAccessibilityService) context;
                // Android 7.0以上可直接调用API方法关闭辅助服务
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    service.disableSelf();
                }
                break;
            default:
                break;
        }
    }
}
