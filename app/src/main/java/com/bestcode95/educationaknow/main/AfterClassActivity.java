package com.bestcode95.educationaknow.main;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.bestcode95.educationaknow.R;

/**
 * Created by weixian on 16-2-25.
 * 课外活动
 */
public class AfterClassActivity extends Activity {

    private ImageButton backBt = null;
    private Button add_to_collections_bt = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.after_class_activity);
        backBt = (ImageButton) findViewById(R.id.after_class_back_bt);
        backBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        add_to_collections_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AfterClassActivity.this, "收藏成功", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
