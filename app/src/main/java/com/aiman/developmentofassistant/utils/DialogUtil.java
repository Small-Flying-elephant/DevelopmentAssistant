package com.aiman.developmentofassistant.utils;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;


/**
 * Created by linsheng on 2018/7/31.
 */

public class DialogUtil {

    /**
     * 显示"悬浮窗权限"提醒对话框
     *
     * @param
     */
    public static void showOverlayAlertDialog(final Context context) {
        CustomDialog.showInstance(
                (AppCompatActivity) context,
                "需要开启【悬浮窗权限】",
                "取消",
                new CustomDialog.CancleCallback() {
                    @Override
                    public void onCancle() {

                    }
                },
                "去开启",
                new CustomDialog.ConfirmCallback() {
                    @Override
                    public void onConfirm() {
                        ActivityUtil.turnToOverlayPermission(context);
                    }
                });
    }

    /**
     * 显示"辅助服务"提醒对话框
     *
     * @param
     * @param msg      消息内容
     * @param cancle   取消按钮文本
     * @param confirm  确认按钮文本
     */
    public static void showAccessibilityServiceAlertDialog(final Context context, String msg, String cancle, String confirm) {
        CustomDialog.showInstance(
                (AppCompatActivity) context,
                msg,
                cancle,
                new CustomDialog.CancleCallback() {
                    @Override
                    public void onCancle() {

                    }
                },
                confirm,
                new CustomDialog.ConfirmCallback() {
                    @Override
                    public void onConfirm() {
                        ActivityUtil.toAuthAccessibilityService(context);
                    }
                });
    }
}
