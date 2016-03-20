package com.bestcode95.educationaknow.utils.network;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by shiweixian on 2016/1/24.
 */
public class MyApplication extends Application {

    public static RequestQueue queues;

    /**
     * 返回volley请求队列
     *
     * @return volley请求队列
     */
    public static RequestQueue getHttpQueues() {
        return queues;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        queues = Volley.newRequestQueue(getApplicationContext());
    }
}
