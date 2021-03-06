package com.aiman.developmentofassistant.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.aiman.developmentofassistant.R;
import com.aiman.developmentofassistant.bean.CellphoneInfoBean;


import java.util.List;

/**
 * Created by linsheng on 2018/7/29.
 */

public class CellphoneinformationAdapter extends BaseAdapter {

    private Context mContext;
    private List<CellphoneInfoBean> cellphoneInfoBeansList;
    public CellphoneinformationAdapter(Context mContext, List<CellphoneInfoBean> cellphoneInfoBeansList) {
        this.mContext = mContext;
        this.cellphoneInfoBeansList = cellphoneInfoBeansList;
    }

    @Override
    public int getCount() {
        return cellphoneInfoBeansList.size();
    }

    @Override
    public Object getItem(int position) {
        return cellphoneInfoBeansList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder mHolder;
        if (convertView == null) {
            mHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(R.layout.developer_debug_item, null, true);
            mHolder.imageView = (ImageView) convertView.findViewById(R.id.imageView);
            mHolder.textView = (TextView) convertView.findViewById(R.id.textView);
            convertView.setTag(mHolder);
        } else {
            mHolder = (ViewHolder) convertView.getTag();
        }
        mHolder.imageView.setImageResource(cellphoneInfoBeansList.get(position).getImageView());
        mHolder.textView.setText(cellphoneInfoBeansList.get(position).getTextName());
        return convertView;
    }

    class ViewHolder {
        private ImageView imageView;
        private TextView textView;
    }
}
