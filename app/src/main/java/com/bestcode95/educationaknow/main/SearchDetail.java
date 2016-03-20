package com.bestcode95.educationaknow.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import com.bestcode95.educationaknow.R;
import com.bestcode95.educationaknow.utils.Constants;
import com.bestcode95.educationaknow.utils.adapter.MyListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by weixian on 16-2-23.
 * 搜索的详情页
 */
public class SearchDetail extends Activity {

    String names[] = {"育英场所", "Child托管所", "天聪小饭桌", "悦童小饭桌", "艾尔小饭桌", "儿童天堂", "慧儿小饭桌"};
    private ImageButton backBt = null;
    private Button accordPriceBt = null;
    private Button accordPopularityBt = null;
    private ListView listView = null;
    private MyListAdapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_detail);
        initView();
        initEvent();
    }

    /**
     * 初始化界面组件
     */
    private void initView() {
        backBt = (ImageButton) findViewById(R.id.back_bt);
        accordPriceBt = (Button) findViewById(R.id.accord_price_bt);
        accordPopularityBt = (Button) findViewById(R.id.accord_popularity_bt);
        listView = (ListView) findViewById(R.id.search_detail_list);
        accordPriceBt.setBackground(getResources().getDrawable(R.drawable.search_detail_price_selected));
        accordPopularityBt.setBackground(getResources().getDrawable(R.drawable.search_detail_popularity_normal));
        accordPriceBt.setTextColor(0xfff7b332);
        accordPopularityBt.setTextColor(0xffffffff);
        adapter = new MyListAdapter(this, getData());
        listView.setAdapter(adapter);
    }

    /**
     * 处理点击事件
     */
    private void initEvent() {
        backBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchDetail.this.finish();
            }
        });
        accordPriceBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                accordPriceBt.setBackground(getResources().getDrawable(R.drawable.search_detail_price_selected));
                accordPopularityBt.setBackground(getResources().getDrawable(R.drawable.search_detail_popularity_normal));
                accordPriceBt.setTextColor(0xfff7b332);
                accordPopularityBt.setTextColor(0xffffffff);
                if (adapter != null)
                    adapter = null;
                adapter = new MyListAdapter(SearchDetail.this, getData());
                adapter.notifyDataSetChanged();
                listView.invalidateViews();
                listView.setAdapter(adapter);
            }
        });
        accordPopularityBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                accordPriceBt.setBackground(getResources().getDrawable(R.drawable.search_detail_price_normal));
                accordPopularityBt.setBackground(getResources().getDrawable(R.drawable.search_detail_popularity_selected));
                accordPriceBt.setTextColor(getResources().getColor(R.color.white));
                accordPopularityBt.setTextColor(getResources().getColor(R.color.search_detail_bg));
                if (adapter != null) {
                    adapter = new MyListAdapter(SearchDetail.this, getReData());
                    adapter.notifyDataSetChanged();
                    listView.invalidateViews();
                    listView.setAdapter(adapter);
                }
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(SearchDetail.this, SearchDetailSecond.class);
                intent.putExtra(Constants.SHOP_NAME, names[position]);
                Log.e("SearchDetail", position + "被点击");
                startActivity(intent);
            }
        });
    }


    /**
     * 按价格排序
     *
     * @return
     */
    private List<Map<String, Object>> getData() {
        int[] item_bg_colors = new int[]{0xff68b83b, 0xff5584af, 0xff8e58a1, 0xff73c5bf, 0xff3194d2
                , 0xffeae725, 0xff905aa2};
        int[] item_right_bg_colors = new int[]{0xff78b83b, 0xff6584af, 0xff9e58a1, 0xff83c5bf, 0xff4194d2
                , 0xfffae725, 0xffa05aa2};
        List<Map<String, Object>> data = new ArrayList<>();
        for (int i = 0; i < names.length; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put(Constants.ITEM_NAME, names[i]);
            map.put(Constants.LAYOUT_COLOR, item_bg_colors[i]);
            map.put(Constants.RIGHT_LAYOUT_COLOR, item_right_bg_colors[i]);
            data.add(map);
        }
        return data;
    }

    /**
     * 按价格排序
     *
     * @return
     */
    private List<Map<String, Object>> getReData() {
        int[] item_bg_colors = new int[]{0xff68b83b, 0xff5584af, 0xff8e58a1, 0xff73c5bf, 0xff3194d2
                , 0xffeae725, 0xff905aa2};
        String names[] = {"育英场所", "Child托管所", "天聪小饭桌", "悦童小饭桌", "艾尔小饭桌", "儿童天堂", "慧儿小饭桌"};
        int[] item_right_bg_colors = new int[]{0xff78b83b, 0xff6584af, 0xff9e58a1, 0xff83c5bf, 0xff4194d2
                , 0xfffae725, 0xffa05aa2};
        List<Map<String, Object>> data = new ArrayList<>();
        for (int i = names.length - 1; i >= 0; i--) {
            Map<String, Object> map = new HashMap<>();
            map.put(Constants.ITEM_NAME, names[i]);
            map.put(Constants.LAYOUT_COLOR, item_bg_colors[i]);
            map.put(Constants.RIGHT_LAYOUT_COLOR, item_right_bg_colors[i]);
            data.add(map);
        }
        return data;
    }


}
