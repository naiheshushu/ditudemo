package zz.itcast.baidumapsz10;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Point;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.model.LatLng;

public class HelloWorld extends Activity {

	private MapView mapView;
	private BaiduMap baiduMap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initSDK();
		setContentView(R.layout.activity_hello_world);
		init();
	}

	private void initSDK() {
		// 通过广播获取 ak的校验结果
		MyBroadCastReceiver receiver = new MyBroadCastReceiver();
		IntentFilter filter = new IntentFilter();
		filter.addAction(SDKInitializer.SDK_BROADCAST_ACTION_STRING_NETWORK_ERROR);// 网络错误
		filter.addAction(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR);// 权限校验错误
		registerReceiver(receiver, filter);
		// 初始化百度地图引擎
		SDKInitializer.initialize(getApplicationContext()); // 上下文：需要一个全局的上下文

	}

	/**
	 * 初始化控件
	 */
	private void init() {
		mapView = (MapView) findViewById(R.id.mapview);
		// mapview --- baidumap 一一对应

		baiduMap = mapView.getMap();

		// 重新设置中心点
		LatLng centerLaulng = new LatLng(34.798413, 113.551107);// 传智博客
		MapStatusUpdate centerMapStatusUpdate = MapStatusUpdateFactory
				.newLatLng(centerLaulng);
		baiduMap.setMapStatus(centerMapStatusUpdate);

		// 默认是12 ---- 15
		MapStatusUpdate zoomMapStatusUpdate = MapStatusUpdateFactory.zoomTo(19);
		baiduMap.setMapStatus(zoomMapStatusUpdate);
		
		
		// 隐藏 标尺
//		mapView.showScaleControl(false);  //  其实是 removeView
		//隐藏缩放按钮
//		mapView.showZoomControls(false);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		// 一级级放大
		case KeyEvent.KEYCODE_1:
			MapStatusUpdate zoomInMapStatusUpdate = MapStatusUpdateFactory
					.zoomIn();
			baiduMap.setMapStatus(zoomInMapStatusUpdate);
			break;
		// 一级级缩小
		case KeyEvent.KEYCODE_2:
			MapStatusUpdate zoomOutMapStatusUpdate = MapStatusUpdateFactory
					.zoomOut();
			baiduMap.setMapStatus(zoomOutMapStatusUpdate);
			break;
		// 平角旋转
		case KeyEvent.KEYCODE_3:
			float oldRoate = baiduMap.getMapStatus().rotate;// 获取之前的 平角角度
			float newRotate = oldRoate + 30;
			MapStatus rotateMapStatus = new MapStatus.Builder()
			// 设置角度
					.rotate(newRotate) // 0 --- 360
					.build();
			MapStatusUpdate rotateMapStatusUpdate = MapStatusUpdateFactory
					.newMapStatus(rotateMapStatus);
			baiduMap.setMapStatus(rotateMapStatusUpdate);
			break;
		// 俯角旋转
		case KeyEvent.KEYCODE_4:
			float oldoverlook = baiduMap.getMapStatus().overlook;// 获取之前的 俯角角度
			float newoverlook = oldoverlook - 5;
			MapStatus overlookMapStatus = new MapStatus.Builder()
			// 设置角度
					.overlook(newoverlook) // 0- - 45
					.build();
			MapStatusUpdate overlookMapStatusUpdate = MapStatusUpdateFactory
					.newMapStatus(overlookMapStatus);
			baiduMap.setMapStatus(overlookMapStatusUpdate);
			break;
		case KeyEvent.KEYCODE_5: // 有动画的平移
			MapStatusUpdate animateMapStatusUpdate = MapStatusUpdateFactory
					.newLatLng(new LatLng(34.813292, 113.552154)); //升龙   
			baiduMap.animateMapStatus(animateMapStatusUpdate);   //动画方式 平移
			break;
		case KeyEvent.KEYCODE_6:  //  罗盘显示
			//  跟标尺 缩放按钮不太一样   
			//获取UISetting
			baiduMap.getUiSettings().setCompassEnabled(true);
			baiduMap.getUiSettings().setCompassPosition(new Point(100, 100));
			break;
		case KeyEvent.KEYCODE_7:  //  罗盘隐藏
			//  跟标尺 缩放按钮不太一样   
			//获取UISetting
			baiduMap.getUiSettings().setCompassEnabled(false);
			break;

		default:
			break;
		}

		return super.onKeyDown(keyCode, event); // 不能返回true 不然返回键 无响应
	}

	class MyBroadCastReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			// 接收 校验 ak 结果
			if (SDKInitializer.SDK_BROADCAST_ACTION_STRING_NETWORK_ERROR
					.equals(intent.getAction())) {
				Toast.makeText(getApplicationContext(), "网络错误",
						Toast.LENGTH_SHORT).show();
			} else if (SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR
					.equals(intent.getAction())) {
				Toast.makeText(getApplicationContext(), "权限校验失败",
						Toast.LENGTH_SHORT).show();
			}
		}

	}

}
