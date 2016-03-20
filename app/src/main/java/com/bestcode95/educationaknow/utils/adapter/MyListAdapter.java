package com.bestcode95.educationaknow.utils.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bestcode95.educationaknow.R;
import com.bestcode95.educationaknow.utils.Constants;

import java.util.List;
import java.util.Map;

/**
 * Created by weixian on 16-2-24.
 */
public class MyListAdapter extends BaseAdapter {

    List<Map<String, Object>> mData = null;
    private Context mContext = null;

//    private int[] item_bg_colors = new int[]{0xff68b83b, 0xff5584af, 0xff8e58a1, 0xff73c5bf, 0xff3194d2
//            , 0xffeae725, 0xff905aa2};
//    private String names[] = {"育英场所", "Child托管所", "天聪小饭桌", "悦童小饭桌", "艾尔小饭桌", "儿童天堂", "慧儿小饭桌"};
//    private int[] item_right_bg_colors = new int[]{0xff78b83b, 0xff6584af, 0xff9e58a1, 0xff83c5bf, 0xff4194d2
//            , 0xfffae725, 0xffa05aa2};

    public MyListAdapter(Context context, List<Map<String, Object>> data) {
        this.mContext = context;
        mData = data;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.search_detail_listview_layout, null);
            holder.nameTextView = (TextView) convertView.findViewById(R.id.search_detail_list_name);
            holder.rightLayout = (LinearLayout) convertView.findViewById(R.id.right_layout);
            //将holder绑定到convertView
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        //像ViewHolder填入数据
        holder.nameTextView.setText(mData.get(position).get(Constants.ITEM_NAME).toString());
        holder.rightLayout.setBackgroundColor((int) mData.get(position).get(Constants.LAYOUT_COLOR));
        convertView.setBackgroundColor((int) mData.get(position).get(Constants.RIGHT_LAYOUT_COLOR));
        return convertView;
    }

    /**
     * ViewHolder类，用于储存item中控件的引用
     */
    class ViewHolder {
        TextView nameTextView;
        LinearLayout rightLayout;
    }
}
