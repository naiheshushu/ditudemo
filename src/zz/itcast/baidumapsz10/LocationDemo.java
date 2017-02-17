package zz.itcast.baidumapsz10;

import android.os.Bundle;
import android.view.KeyEvent;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;

public class LocationDemo extends BaseActivity {
	public LocationClient mLocationClient = null;
	public BDLocationListener myListener = new MyLocationListener();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mLocationClient = new LocationClient(getApplicationContext()); // 声明LocationClient类
		mLocationClient.registerLocationListener(myListener); // 注册监听函数
		initLocation();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		MyLocationConfiguration config;
		switch (keyCode) {
		case KeyEvent.KEYCODE_1: // 普通模式
			config = new MyLocationConfiguration(
					MyLocationConfiguration.LocationMode.NORMAL, true,
					BitmapDescriptorFactory.fromResource(R.drawable.icon_geo));
			// 位置的配置
			baiduMap.setMyLocationConfigeration(config);
			break;
		case KeyEvent.KEYCODE_2: // 跟随模式
			config = new MyLocationConfiguration(
					MyLocationConfiguration.LocationMode.FOLLOWING, true,
					BitmapDescriptorFactory.fromResource(R.drawable.icon_geo));
			// 位置的配置
			baiduMap.setMyLocationConfigeration(config);
			break;
		case KeyEvent.KEYCODE_3: // 罗盘模式
			config = new MyLocationConfiguration(
					MyLocationConfiguration.LocationMode.COMPASS, true,
					BitmapDescriptorFactory.fromResource(R.drawable.icon_geo));
			// 位置的配置
			baiduMap.setMyLocationConfigeration(config);
			break;
		}

		return super.onKeyDown(keyCode, event);
	}

	private void initLocation() {
		LocationClientOption option = new LocationClientOption();
		option.setLocationMode(LocationMode.Hight_Accuracy);// 可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
		option.setCoorType("bd09ll");// 可选，默认gcj02，设置返回的定位结果坐标系
		int span = 1000;
		option.setScanSpan(span);// 可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
		option.setIsNeedAddress(true);// 可选，设置是否需要地址信息，默认不需要
		option.setOpenGps(true);// 可选，默认false,设置是否使用gps
		option.setLocationNotify(true);// 可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
		option.setIgnoreKillProcess(false);// 可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
		option.SetIgnoreCacheException(false);// 可选，默认false，设置是否收集CRASH信息，默认收集
		mLocationClient.setLocOption(option);
		// 参数1 显示模式
		// 参数2 是否允许显示方向信息
		MyLocationConfiguration config = new MyLocationConfiguration(
				MyLocationConfiguration.LocationMode.FOLLOWING, true,
				BitmapDescriptorFactory.fromResource(R.drawable.icon_geo));
		// 位置的配置
		baiduMap.setMyLocationConfigeration(config);
		// 打开 定位图层
		baiduMap.setMyLocationEnabled(true);

	}

	@Override
	protected void onStart() {
		super.onStart();
		mLocationClient.start();
	}

	@Override
	protected void onStop() {
		super.onStop();
		mLocationClient.stop();
	}

	// 根据 位置改变显示 地理位置

	class MyLocationListener implements BDLocationListener {

		@Override
		public void onReceiveLocation(BDLocation bdLocation) {

			// 定位信息
			MyLocationData myLocationData = new MyLocationData.Builder()
			// 设置参数
					.latitude(bdLocation.getLatitude()) // 纬度
					.longitude(bdLocation.getLongitude()) // 精度
					.build();
			baiduMap.setMyLocationData(myLocationData);
		}
	}
}
