package com.bestcode95.educationaknow.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bestcode95.educationaknow.R;

/**
 * Created by weixian on 16-2-25.
 */
public class SchoolOut extends Activity {
    private TextView text5 = null;
    private ImageButton backBt = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_out);
        text5 = (TextView) findViewById(R.id.school_out_text5);
        backBt = (ImageButton) findViewById(R.id.school_out_back_bt);
        text5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SchoolOut.this, AfterClassActivity.class));
            }
        });
        backBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
