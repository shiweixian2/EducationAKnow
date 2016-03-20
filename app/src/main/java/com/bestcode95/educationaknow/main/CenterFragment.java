package com.bestcode95.educationaknow.main;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.bestcode95.educationaknow.R;
import com.bestcode95.educationaknow.utils.Constants;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by shiweixian on 2016/1/28.
 * 主界面中间的搜索界面
 */
public class CenterFragment extends Fragment {

    private static final String ICON_TEXT = "icon_text";
    private static final String HINT_TEXT = "hint_text";
    private static final int school = 0;
    private static final int time = 1;
    private static final int price = 2;
    private static final int SCHOOL_MSG = 0x123;
    private static final int PRICE_MSG = 0x124;
    private static final String BOTTOM_PRICE_BUNDLE = "bottom_price_bundle";
    private static final String TOP_PRICE_BUNDLE = "top_price_bundle";
    String province = "山东省";
    String city = "济南市";
    String district = "市辖区";
    SimpleAdapter adapter = null;
    String text[] = {"请输入学校名", "2016年10月05号", "请输入价格"};
    Calendar c = Calendar.getInstance();
    int myYear = c.get(Calendar.YEAR);
    int myMonth = c.get(Calendar.MONTH) + 1;
    int myDay = c.get(Calendar.DAY_OF_MONTH);
    private LinearLayout position_bt = null;
    private ListView listView = null;
    private Button sure_bt = null;
    private TextView positionText = null;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case SCHOOL_MSG:
                    String school_name = (String) msg.obj;
                    text[0] = school_name;
                    Log.e("school_name", text[0]);
                    setMyAdapter();
                    break;
                case PRICE_MSG:
                    Bundle bundle = (Bundle) msg.obj;
                    String bottomPrice = bundle.getString(BOTTOM_PRICE_BUNDLE);
                    String topPrice = bundle.getString(TOP_PRICE_BUNDLE);
                    text[2] = bottomPrice + "元 - " + topPrice + "元";
                    setMyAdapter();
                    break;
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_center, null);
        initView(view);
        initEvent();
        return view;
    }

    /**
     * 初始化界面
     * 并为listView 设置数据
     *
     * @param view
     */
    private void initView(View view) {
        position_bt = (LinearLayout) view.findViewById(R.id.position_bt);
        listView = (ListView) view.findViewById(R.id.main_search_listView);
        sure_bt = (Button) view.findViewById(R.id.main_search_sure_bt);
        positionText = (TextView) view.findViewById(R.id.main_search_position_text);
        text[1] = myYear + "年" + myMonth + "月" + myDay + "日";
        setMyAdapter();

    }

    /**
     * 设置listView的适配器
     */
    private void setMyAdapter() {
        adapter = null;
        List<Map<String, String>> data = getData();
        if (data != null) {
            adapter = new SimpleAdapter(getActivity(), getData(), R.layout.layout_for_search_listview,
                    new String[]{ICON_TEXT, HINT_TEXT}, new int[]{R.id.list_item_left, R.id.list_item_center});
            //设置adapter
            listView.setAdapter(adapter);
        }
    }

    /**
     * 处理各种点击事件
     */
    private void initEvent() {
        //选择位置点击事件
        position_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ChoseCity.class);
                startActivityForResult(intent, Constants.POSITION);

            }
        });
        //学校，事件，价格点击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LayoutInflater inflater = getLayoutInflater(null);
                switch (position) {
                    case school:
                        View layout = inflater.inflate(R.layout.chose_school, null);
                        final AlertDialog schoolDialog = new AlertDialog.Builder(getActivity()).create();
                        schoolDialog.setView(layout);
                        final EditText schoolEdit = (EditText) layout.findViewById(R.id.my_school_editText);
                        final Button schoolSureBt = (Button) layout.findViewById(R.id.my_school_name_sure_bt);
                        schoolSureBt.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String mySchool = schoolEdit.getText().toString();
                                Message message = new Message();
//                                Bundle bundle = new Bundle();
//                                bundle.putString(SCHOOL_BUNDLE,mySchool);
//                                message.setData(bundle);
                                message.what = SCHOOL_MSG;
                                message.obj = mySchool;
                                handler.sendMessage(message);
                                schoolDialog.dismiss();
                            }
                        });
                        schoolDialog.show();
                        break;
                    case time:
                        DatePickerDialog dialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                myYear = year;
                                myMonth = monthOfYear + 1;
                                myDay = dayOfMonth;
                                text[1] = myYear + "年" + myMonth + "月" + myDay + "日";
                                setMyAdapter();
                            }
                        }, c.get(Calendar.YEAR), c.get(Calendar.MONTH) + 1, c.get(Calendar.DAY_OF_MONTH));
                        dialog.show();
                        break;
                    case price:
                        View priceLayout = inflater.inflate(R.layout.chose_price, null);
                        final AlertDialog priceDialog = new AlertDialog.Builder(getActivity()).create();
                        priceDialog.setView(priceLayout);
                        final EditText bottomEdit = (EditText) priceLayout.findViewById(R.id.bottom_price_edit);
                        final EditText topEdit = (EditText) priceLayout.findViewById(R.id.top_price_edit);
                        final Button priceSureBt = (Button) priceLayout.findViewById(R.id.my_price_sure_bt);
                        priceSureBt.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String bottomPrice = bottomEdit.getText().toString();
                                String topPrice = topEdit.getText().toString();
                                Message message = new Message();
                                Bundle bundle = new Bundle();
                                bundle.putString(BOTTOM_PRICE_BUNDLE, bottomPrice);
                                bundle.putString(TOP_PRICE_BUNDLE, topPrice);
                                message.setData(bundle);
                                message.what = PRICE_MSG;
                                message.obj = bundle;
                                handler.sendMessage(message);
                                priceDialog.dismiss();
                            }
                        });
                        priceDialog.show();
                        break;
                    default:
                        break;
                }
            }
        });

        //搜索按钮点击事件
        sure_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SearchDetail.class);
                startActivity(intent);
            }
        });
    }

    /**
     * 为listView准备数据
     *
     * @return 数据
     */
    private List<Map<String, String>> getData() {
        String text[] = {"学校", "时间", "价格"};
        List<Map<String, String>> mapList = new ArrayList<>();
        for (int i = 0; i < text.length; i++) {
            Map<String, String> map = new HashMap<>();
            map.put(ICON_TEXT, text[i]);
            map.put(HINT_TEXT, this.text[i]);
            mapList.add(map);
        }
        return mapList;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            //选择地区
            case Constants.POSITION:
                province = data.getStringExtra(Constants.PROVINCE);
                city = data.getStringExtra(Constants.CITY);
                district = data.getStringExtra(Constants.DISTRICT);
                positionText.setText(city + " " + district);
                break;
            default:
                break;
        }
    }
}
