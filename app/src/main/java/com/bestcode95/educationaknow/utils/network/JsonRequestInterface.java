package com.bestcode95.educationaknow.utils.network;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONObject;

/**
 * Created by shiweixian on 2016/1/25.
 */
public abstract class JsonRequestInterface {
    public static Response.Listener<JSONObject> mListener;
    public static Response.ErrorListener mErrorListener;
    public Context mContext;

    public JsonRequestInterface(Context context, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        this.mContext = context;
        this.mListener = listener;
        this.mErrorListener = errorListener;
    }

    public Response.Listener<JSONObject> loadingListener() {
        mListener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject object) {
                onMySuccess(object);
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

    public abstract void onMySuccess(JSONObject object);

    public abstract void onMyError(VolleyError error);
}
