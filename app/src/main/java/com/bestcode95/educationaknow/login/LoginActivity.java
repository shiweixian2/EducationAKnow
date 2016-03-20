package com.bestcode95.educationaknow.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.bestcode95.educationaknow.R;
import com.bestcode95.educationaknow.main.MainActivity;

/**
 * Created by shiweixian on 2016/1/26.
 * 登陆界面
 */
public class LoginActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }

    /**
     * 登陆，进入主界面
     *
     * @param view
     */
    public void Login(View view) {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        this.finish();
    }

//    public void Register(){
//
//    }
}
