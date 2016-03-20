package com.bestcode95.educationaknow.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.bestcode95.educationaknow.R;
import com.bestcode95.educationaknow.utils.view.SlideShowView;

/**
 * Created by shiweixian on 2016/1/28.
 */
public class LeftFragment extends Fragment implements View.OnClickListener {

    private SlideShowView slideShowView;
    private FrameLayout bt1;
    private FrameLayout bt2;
    private FrameLayout bt3;
    private FrameLayout bt4;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_left, null);
        initView(view);
        return view;
    }

    /**
     * 初始化界面組件
     *
     * @param view
     */
    private void initView(View view) {
        slideShowView = (SlideShowView) view.findViewById(R.id.slide_show_view);
        bt1 = (FrameLayout) view.findViewById(R.id.left_button1);
        bt2 = (FrameLayout) view.findViewById(R.id.left_button2);
        bt3 = (FrameLayout) view.findViewById(R.id.left_button3);
        bt4 = (FrameLayout) view.findViewById(R.id.left_button4);
        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
        bt3.setOnClickListener(this);
        bt4.setOnClickListener(this);
    }

    /**
     * 点击事件
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.left_button1:
                break;
            case R.id.left_button2:
                break;
            case R.id.left_button3:
                break;
            case R.id.left_button4:
                startActivity(new Intent(getActivity(), MixedArea.class));
                break;
            default:
                break;
        }
    }
}
