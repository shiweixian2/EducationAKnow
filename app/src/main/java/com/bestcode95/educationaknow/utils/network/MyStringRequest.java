package com.bestcode95.educationaknow.utils.network;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;

import java.util.Map;

/**
 * Created by shiweixian on 2016/1/25.
 */
public class MyStringRequest {
    public static StringRequest stringRequest;
    public static Context mContext;

    public static void RequestGet(Context context, String url, String tag, StringRequestInterface vif) {
        MyApplication.getHttpQueues().cancelAll(tag);
        stringRequest = new StringRequest(Request.Method.GET, url, vif.loadingListener(), vif.errorListener());
        stringRequest.setTag(tag);
        MyApplication.getHttpQueues().add(stringRequest);
        MyApplication.getHttpQueues().start();
    }

    public static void RequestPost(Context context, String url, String tag, final Map<String, String> params, StringRequestInterface vif) {
        MyApplication.getHttpQueues().cancelAll(tag);
        stringRequest = new StringRequest(Request.Method.POST, url, vif.loadingListener(), vif.errorListener()) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }
        };
        stringRequest.setTag(tag);
        MyApplication.getHttpQueues().add(stringRequest);
        MyApplication.getHttpQueues().start();
    }
}
