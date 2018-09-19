package com.aiman.developmentofassistant.framelayout;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.aiman.developmentofassistant.Adapter.CellphoneinformationAdapter;
import com.aiman.developmentofassistant.R;
import com.aiman.developmentofassistant.bean.CellphoneInfoBean;
import com.aiman.developmentofassistant.developmentofassistant.MainActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static com.aiman.developmentofassistant.developmentofassistant.MainActivity.book;
import static com.aiman.developmentofassistant.developmentofassistant.MainActivity.display;
import static com.aiman.developmentofassistant.developmentofassistant.MainActivity.phone;
import static com.aiman.developmentofassistant.developmentofassistant.MainActivity.wifi;

/**
 * Created by linsheng on 2018/7/29.
 */

public class CellphoneinformationFrameLayout extends BaseInsertHolder implements AdapterView.OnItemClickListener {

    private Context mContext;
    private List<CellphoneInfoBean> cellphoneInfoBeansList;

    public CellphoneinformationFrameLayout(Context applicationContext, ViewGroup parent) {
        super(parent);
        this.mContext = applicationContext;
    }

    @Override
    public View getInsertView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_cellphone_information, parent, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        GridView gridView = (GridView) view.findViewById(R.id.developer_debug_gridview);
        initData();
        CellphoneinformationAdapter cellphoneinformationAdapter = new CellphoneinformationAdapter(mContext, cellphoneInfoBeansList);
        gridView.setAdapter(cellphoneinformationAdapter);
        gridView.setOnItemClickListener(this);


    }

    private void initData() {
        int[] imageView = {R.drawable.info_cpu, R.drawable.info_hardware, R.drawable.info_id,
                R.drawable.info_network, R.drawable.info_system, R.drawable.ui_screen_info,
                R.drawable.info_my_apps};
        int[] textView = {R.string.app_framelayout_ccpu, R.string.app_framelayout_hardware, R.string.app_framelayout_locationID,
                R.string.app_framelayout_newwork, R.string.app_framelayout_system, R.string.app_framelayout_screen, R.string.app_framelayout_location_app};
        cellphoneInfoBeansList = new ArrayList<CellphoneInfoBean>();
        for (int i = 0; i < imageView.length; i++) {
            CellphoneInfoBean cellphoneInfoBean = new CellphoneInfoBean();
            cellphoneInfoBean.setTextName(textView[i]);
            cellphoneInfoBean.setImageView(imageView[i]);
            cellphoneInfoBeansList.add(cellphoneInfoBean);
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
        switch (position) {
            case 0:
                MainActivity.startGame();
                MainActivity.showInterstitial();
                showCPUDialog();
                break;
            case 1:
                MainActivity.startGame();
                MainActivity.showInterstitial();
                showDeviceInfo();
                break;
            case 2:
                MainActivity.startGame();
                MainActivity.showInterstitial();
                showDeviceId();
                break;
            case 3:
                MainActivity.startGame();
                MainActivity.showInterstitial();
                showTelephoneNet();
                break;
            case 4:
                MainActivity.startGame();
                MainActivity.showInterstitial();
                showSystem();
                break;
            case 5:
                MainActivity.startGame();
                MainActivity.showInterstitial();
                showScreenInfo();
                break;
            case 6:
                MainActivity.startGame();
                MainActivity.showInterstitial();
                IntentShow();
                break;
            case 7:
                MainActivity.startGame();
                MainActivity.showInterstitial();
                Toast.makeText(mContext, "Is developing", Toast.LENGTH_LONG).show();
                break;

        }
    }

    private void IntentShow() {
        Intent intentLaunger = new Intent(Settings.ACTION_MANAGE_APPLICATIONS_SETTINGS);
        intentLaunger.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intentLaunger);
    }

    private void showSystem() {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.system_devices_title);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        builder.setMessage(context.getResources().getString(R.string.telep_devices_info_release) + " : " + Build.VERSION.RELEASE + "\r\n" +
                context.getResources().getString(R.string.telep_devices_info_sdk) + " : " + Build.VERSION.SDK + "\r\n" +
                context.getResources().getString(R.string.telep_devices_info_xinghao) + " ： " +  Build.MODEL + "\r\n"
        );
        builder.show();

    }

    private void showScreenInfo() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.screen_devices_info);
        builder.setMessage(context.getResources().getString(R.string.screen_devices_density) + " : " + book.density+"" + "\r\n" +
                context.getResources().getString(R.string.screen_devices_densitydpi) + " : " + book.densityDpi + "\r\n" +
                context.getResources().getString(R.string.screen_devices_getWidth) + " : " + display.getWidth()+"" + "\r\n" +
                context.getResources().getString(R.string.screen_devices_getHeight) + " : " + display.getHeight() + "\r\n" +
                context.getResources().getString(R.string.screen_devices_xdpi) + " : " + book.xdpi + "\r\n" +
                context.getResources().getString(R.string.screen_devices_ydpi) + " : " + book.ydpi + "\r\n"

        );
        builder.show();
    }

    private void showTelephoneNet() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.telep_net_info);
        builder.setMessage(context.getResources().getString(R.string.telep_net_info_Operator) + " : " + phone.getSimOperatorName() + "\r\n" +
                context.getResources().getString(R.string.telep_net_info_iso) + " : " + phone.getNetworkCountryIso() + "\r\n" +
                context.getResources().getString(R.string.telep_net_info_type) + " : " + phone.getNetworkOperatorName() + "\r\n" +
                context.getResources().getString(R.string.telep_net_info_mac) + " : " + wifi.getConnectionInfo().getMacAddress() + "\r\n" +
                context.getResources().getString(R.string.telep_net_info_wifi)  + " : " +  wifi.getConnectionInfo().getSSID() + "\r\n" +
                context.getResources().getString(R.string.telep_net_info_getBSSID) + " : " + wifi.getConnectionInfo().getBSSID() + "\r\n" +
                context.getResources().getString(R.string.telep_net_info_ip) + " : " + wifi.getConnectionInfo().getIpAddress()

        );
        builder.show();
    }

    private void showDeviceInfo() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.telep_devices_info);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        builder.setMessage(
                context.getResources().getString(R.string.string_id_dialog_brand) + " : " + Build.BRAND + "\r\n" +
                context.getResources().getString(R.string.string_id_dialog_devices) + " : " + Build.DEVICE + "\r\n" +
                context.getResources().getString(R.string.string_id_dialog_hardware) + " : " + Build.HARDWARE + "\r\n"
        );
        builder.show();
    }

    private void showDeviceId() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.string_id_dialog_idinfo);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        builder.setMessage(context.getResources().getString(R.string.string_id_dialog_imei) + " : " + phone.getDeviceId() + "\r\n" +
                context.getResources().getString(R.string.string_id_dialog_phone) + " : " + phone.getLine1Number()
        );
        builder.show();
    }


    private void showCPUDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.string_cpu_dialog_settitle);
        builder.setMessage(context.getResources().getString(R.string.string_cpu_dialog_cpu_abis) + " : " + Build.CPU_ABI + "\r\n"+
                context.getResources().getString(R.string.string_cpu_dialog_cpu_abis2) + " : " + Build.CPU_ABI2 + "\r\n"+
                context.getResources().getString(R.string.string_cpu_dialog_cpu_number) + " : " + getCPUNumber() + "\r\n"+
                context.getResources().getString(R.string.string_cpu_dialog_cpu_model) + " : " +getCPUname()
                );
        builder.show();
    }

    private String getCPUname() {

            String str1 = "/proc/cpuinfo";
            String str2 = "";

            try {
                FileReader fr = new FileReader(str1);
                BufferedReader localBufferedReader = new BufferedReader(fr);
                while ((str2 = localBufferedReader.readLine()) != null) {
                    if (str2.contains("Hardware")) {
                        return str2.split(":")[1];
                    }
                }
                localBufferedReader.close();
            } catch (IOException e) {
            }
            return null;

        }

    private int getCPUNumber(){
        class CpuFilter implements FileFilter {
            @Override
            public boolean accept(File pathname) {
                //Check if filename is "cpu", followed by a single digit number
                if(Pattern.matches("cpu[0-9]", pathname.getName())) {
                    return true;
                }
                return false;
            }
        }

        try {
            //Get directory containing CPU info
            File dir = new File("/sys/devices/system/cpu/");
            //Filter to only list the devices we care about
            File[] files = dir.listFiles(new CpuFilter());

            //Return the number of cores (virtual CPU devices)
            return files.length;
        } catch(Exception e) {
            //Print exception
            e.printStackTrace();
            //Default to return 1 core
            return 1;
        }
        }


}
