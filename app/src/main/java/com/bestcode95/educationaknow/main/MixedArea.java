package com.bestcode95.educationaknow.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import com.bestcode95.educationaknow.R;

/**
 * Created by weixian on 16-2-25.
 * 综合区
 */
public class MixedArea extends Activity implements View.OnClickListener {

    private FrameLayout schoolOutBt = null;
    private FrameLayout eduBt = null;
    private FrameLayout discountBt = null;
    private ImageButton backBt = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mixed_area);
        initView();
    }

    private void initView() {
        backBt = (ImageButton) findViewById(R.id.mixed_area_back_bt);
        schoolOutBt = (FrameLayout) findViewById(R.id.mixed_area_school_out_bt);
        eduBt = (FrameLayout) findViewById(R.id.mixed_area_edu_bt);
        discountBt = (FrameLayout) findViewById(R.id.mixed_area_discount_bt);
        backBt.setOnClickListener(this);
        schoolOutBt.setOnClickListener(this);
        eduBt.setOnClickListener(this);
        discountBt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.mixed_area_back_bt:
                finish();
                break;
            case R.id.mixed_area_school_out_bt:
                intent = new Intent(MixedArea.this, SchoolOut.class);
                startActivity(intent);
                break;
            case R.id.mixed_area_edu_bt:
                intent = new Intent(MixedArea.this, Edu.class);
                startActivity(intent);
                break;
            case R.id.mixed_area_discount_bt:
                intent = new Intent(MixedArea.this, Discount.class);
                startActivity(intent);
                break;
        }
    }
}
