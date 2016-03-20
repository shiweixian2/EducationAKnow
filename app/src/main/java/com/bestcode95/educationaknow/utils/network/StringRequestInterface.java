package com.bestcode95.educationaknow.utils.network;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;

/**
 * Created by shiweixian on 2016/1/25.
 */
public abstract class StringRequestInterface {
    public static Response.Listener<String> mListener;
    public static Response.ErrorListener mErrorListener;
    public Context mContext;

    public StringRequestInterface(Context context, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        this.mContext = context;
        this.mListener = listener;
        this.mErrorListener = errorListener;
    }

    public Response.Listener<String> loadingListener() {
        mListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                onMySuccess(s);
            }
        };
        return mListener;
    }

    public Response.ErrorListener errorListener() {
        mErrorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                onMyError(volleyError);
            }
        };
        return mErrorListener;
    }

    public abstract void onMySuccess(String result);

    public abstract void onMyError(VolleyError error);
}
