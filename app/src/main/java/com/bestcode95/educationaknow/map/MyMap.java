package com.bestcode95.educationaknow.map;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;

public class MyMap extends AppCompatActivity implements LocationSource, AMapLocationListener {

    //声明变量
    private MapView mapView;
    private AMap aMap;
    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    //声明mLocationOption对象
    private AMapLocationClientOption mLocationOption = null;

    private OnLocationChangedListener mListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        printLog("onCreate");
        setContentView(R.layout.activity_main);
        mapView = (MapView) findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);// 必须要写
        //初始化Map
        initMap();
        //设置定位参数
        setLocationOption();
        //初始化定位客户端
        initLocationClient();
    }

    /**
     * 初始化Map并设置相关属性
     */
    private void initMap() {
        if (mapView == null)
            mapView = (MapView) findViewById(R.id.map);
        aMap = mapView.getMap();
        aMap.setLocationSource(this);// 设置定位监听
        aMap.getUiSettings().setMyLocationButtonEnabled(true);// 设置默认定位按钮是否显示
        aMap.setMyLocationEnabled(true);// 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
        // 设置定位的类型为定位模式，参见类AMap。
        aMap.setMyLocationType(AMap.LOCATION_TYPE_MAP_FOLLOW);
        //设置缩放级别
        aMap.moveCamera(CameraUpdateFactory.zoomTo(20));
        //设置实时显示路况
        aMap.setTrafficEnabled(true);
    }

    /**
     * 设置定位参数
     */
    private void setLocationOption() {
        //初始化定位参数
        mLocationOption = new AMapLocationClientOption();
        //设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //设置是否只定位一次,默认为false
        mLocationOption.setOnceLocation(false);
        //设置是否强制刷新WIFI，默认为强制刷新
        mLocationOption.setWifiActiveScan(true);
        //设置是否允许模拟位置,默认为false，不允许模拟位置
        mLocationOption.setMockEnable(false);
        //设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption.setInterval(200);
        mLocationOption.setGpsFirst(true);
    }

    /**
     * 初始化定位客户端
     */
    private void initLocationClient() {
        //初始化定位
        mLocationClient = new AMapLocationClient(this.getApplicationContext());
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        Log.e("initLocationClient", "启动定位");
        mLocationClient.startLocation();
        aMap.setMyLocationType(AMap.LOCATION_TYPE_MAP_ROTATE);
        //设置定位回调监听
        mLocationClient.setLocationListener(this);
    }

    /**
     * @param aMapLocation
     */
    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation != null) {
            if (aMapLocation.getErrorCode() == 0) {
                Log.e("listener", "定位成功");
                mListener.onLocationChanged(aMapLocation);// 显示系统小蓝点
            } else {
                //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                Log.e("AmapError", "location Error, ErrCode:"
                        + aMapLocation.getErrorCode() + ", errInfo:"
                        + aMapLocation.getErrorInfo());
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        printLog("onResume");
//        if (aMap == null)
//            initMap();
//        if (mLocationClient == null)
//            initLocationClient();
//        if (mLocationOption == null)
//            setLocationOption();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        printLog("onPause");
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        printLog("onSaveInstanceState");
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        printLog("onDestroy");
        deactivate();
        mapView.onDestroy();
        System.exit(0);
    }

    private void printLog(String content) {
        Log.e("MainActivity", content);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_settings:
                Toast.makeText(this, "设置", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_normal_map:
                aMap.setMapType(AMap.MAP_TYPE_NORMAL);// 矢量地图模式
                return true;
            case R.id.action_satellite_map:
                aMap.setMapType(AMap.MAP_TYPE_SATELLITE);// 卫星地图模式
                return true;
            case R.id.action_night_map:
                aMap.setMapType(AMap.MAP_TYPE_NIGHT);//夜景地图模式
                return true;
            case R.id.location_type_locate:
                //标准定位
                aMap.setMyLocationType(AMap.LOCATION_TYPE_LOCATE);
                return true;
            case R.id.location_type_map_follow:
                //追随定位
                aMap.setMyLocationType(AMap.LOCATION_TYPE_MAP_FOLLOW);
                return true;
            case R.id.location_type_map_rotate:
                //旋转定位
                aMap.setMyLocationType(AMap.LOCATION_TYPE_MAP_ROTATE);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {
        mListener = onLocationChangedListener;
        if (mLocationClient == null) {
            //设置定位参数
            setLocationOption();
            //初始化定位客户端
            initLocationClient();
        }
    }

    @Override
    public void deactivate() {
        mListener = null;
        if (mLocationClient != null) {
            mLocationClient.stopLocation();
            mLocationClient.onDestroy();
        }
        mLocationClient = null;
        mLocationOption = null;
    }
}
