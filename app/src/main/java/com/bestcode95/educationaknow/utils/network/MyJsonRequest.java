package com.bestcode95.educationaknow.utils.network;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

/**
 * Created by shiweixian on 2016/1/26.
 */
public class MyJsonRequest {
    public static JsonObjectRequest mObjectRequest;
    public static Context mContext;

    public static void RequestGet(Context context, String url, String tag, JsonRequestInterface jif) {
        MyApplication.getHttpQueues().cancelAll(tag);
        mObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, jif.loadingListener(), jif.errorListener());
        mObjectRequest.setTag(tag);
        MyApplication.getHttpQueues().add(mObjectRequest);
        MyApplication.getHttpQueues().start();
    }

    public static void RequestPost(Context context, String url, String tag, JSONObject object, JsonRequestInterface jif) {
        MyApplication.getHttpQueues().cancelAll(tag);
        mObjectRequest = new JsonObjectRequest(Request.Method.POST, url, object, jif.loadingListener(), jif.errorListener());
        mObjectRequest.setTag(tag);
        MyApplication.getHttpQueues().add(mObjectRequest);
        MyApplication.getHttpQueues().start();
    }
}
