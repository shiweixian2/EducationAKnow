package com.bestcode95.educationaknow.main;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.bestcode95.educationaknow.R;

/**
 * Created by weixian on 16-2-25.
 */
public class Discount extends Activity {
    private ImageButton backBt = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discount);
        backBt = (ImageButton) findViewById(R.id.discount_back_bt);
        backBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
