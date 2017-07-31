package com.qht.blog2.OtherFragment.send.sendNetMap.UI;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.qht.blog2.BaseFragment.BaseFragment;
import com.qht.blog2.R;
import com.qht.blog2.Util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class FragmentSend_Company extends BaseFragment {

    @BindView(R.id.et_fragment_send_netmap)
    EditText  etFragmentSendNetmap;
    @BindView(R.id.iv_fragment_send_netmaparrow)
    ImageView ivFragmentSendNetmaparrow;
    @BindView(R.id.btn_fragment_send_netmap)
    Button    btnFragmentSendNetmap;
    @BindView(R.id.bmapView)
    MapView   bmapView;
    private Context        mActivity;
    private BaiduMap       mBaiduMap;
    private LocationClient locationClient;
    private OverlayOptions option;//红色mark
    private Marker         marker;
    private List<LatLng> pointstwo = new ArrayList<LatLng>();
    private LatLng p1;//

    @Override
    public int getContentViewId() {
        return R.layout.fragment_send__netmap;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        initView();
        return rootView;
    }

    private void initView() {
initMap();
    }

    /**
     * 初始化地图数据
     * */
    private void initMap() {
        locationClient = new LocationClient(mActivity);
        locationClient.registerLocationListener(new MyLocationListener());
        mBaiduMap = bmapView.getMap();
        // 普通地图
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
        mBaiduMap.setMyLocationEnabled(true);
        LocationClientOption option = new LocationClientOption();
//        option.setCoorType("bd09ll");// 可选，默认gcj02，设置返回的定位结果坐标系
        option.setScanSpan(3000);// 可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于3000ms才是有效的
        option.setOpenGps(false);// 可选，默认false,设置是否使用gps
        option.setLocationNotify(true);// 可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
        option.setIsNeedLocationDescribe(false);// 可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(false);// 可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(false);// 可选，默认false，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认杀死
        option.setEnableSimulateGps(true);// 可选，默认false，设置是否需要过滤gps仿真结果，默认需要
        mBaiduMap.setMapStatus(MapStatusUpdateFactory.zoomTo(14));// 地图缩放级别为14
        locationClient.setLocOption(option);
        locationClient.start();
    }
    /**
     * 定位监听*/
    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            if (location!= null) {
                LatLng point = new LatLng(34.27320460395923,
                         108.79243283534517);
                MyLocationData locData = new MyLocationData.Builder()
                        .accuracy(location.getRadius())
                        .latitude(location.getLatitude())
                        .longitude(location.getLongitude())
                        .build();
                mBaiduMap.setMyLocationData(locData);
                p1 = new LatLng(location.getLatitude(),
                        location.getLongitude());
                mBaiduMap.setMapStatus(MapStatusUpdateFactory.newLatLng(point));
                BitmapDescriptor bitmap = BitmapDescriptorFactory
                        .fromResource(R.mipmap.fragment_send_baidumap_mark);
                option = new MarkerOptions().position(point).icon(bitmap);
                marker = (Marker)mBaiduMap.addOverlay(option);
            } else {
                ToastUtil.showToastShort("定位失败，请检查手机网络或设置！");
            }
        }

    }
    @OnClick({R.id.iv_fragment_send_netmaparrow, R.id.btn_fragment_send_netmap})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_fragment_send_netmaparrow:
                break;
            case R.id.btn_fragment_send_netmap:
                break;
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mActivity = activity;
    }


    @Override
    public void onPause() {
        super.onPause();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        bmapView.onPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bmapView.onDestroy();
    }
}
