package com.bestcode95.educationaknow.utils.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bestcode95.educationaknow.utils.view.MyListItem;

import java.util.List;

/**
 * 选择位置的适配器
 *
 * @author shiweixian
 */
public class MyAdapter extends BaseAdapter {

    private Context context;
    private List<MyListItem> myList;

    public MyAdapter(Context context, List<MyListItem> myList) {
        this.context = context;
        this.myList = myList;
    }

    public int getCount() {
        return myList.size();
    }

    public Object getItem(int position) {
        return myList.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        MyListItem myListItem = myList.get(position);
        return new MyAdapterView(this.context, myListItem);
    }

    class MyAdapterView extends LinearLayout {
        public static final String LOG_TAG = "MyAdapterView";

        public MyAdapterView(Context context, MyListItem myListItem) {
            super(context);
            this.setOrientation(HORIZONTAL);

            LayoutParams params = new LayoutParams(250, 70);
            params.setMargins(1, 1, 1, 1);

            TextView name = new TextView(context);
            name.setTextColor(Color.BLACK);
            name.setText(myListItem.getName());
            name.setTextSize(20);
            addView(name, params);

            LayoutParams params2 = new LayoutParams(200, LayoutParams.WRAP_CONTENT);
            params2.setMargins(1, 1, 1, 1);

            TextView pcode = new TextView(context);
            pcode.setText(myListItem.getPcode());
            addView(pcode, params2);
            pcode.setVisibility(GONE);

        }

    }

}