package zz.itcast.baidumapsz10;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

import com.baidu.mapapi.map.BaiduMap.OnMarkerClickListener;
import com.baidu.mapapi.map.MapViewLayoutParams.ELayoutMode;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapViewLayoutParams;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.model.LatLng;

public class MarkerOptionsDemo extends BaseActivity {

	private View popView;
	private TextView tvTitle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		draw();
		initPop();

	}

	/**
	 * 初始化 pop
	 */
	private void initPop() {
		// xml --- view
		
		popView = View.inflate(this, R.layout.pop, null);
		tvTitle = (TextView) popView.findViewById(R.id.title);

		MapViewLayoutParams mapViewLayoutParams  = new MapViewLayoutParams.Builder()
		//设置 宽  高
		.width(MapViewLayoutParams.WRAP_CONTENT)
		.height(MapViewLayoutParams.WRAP_CONTENT)
		//显示模式  相对 屏幕还是相对 mapview
		.layoutMode(ELayoutMode.mapMode)  //  相对 经纬度
		//设置  坐标
		.position(new LatLng(itcastLat, itcastLnt))
		.build();
		mapView.addView(popView, mapViewLayoutParams  );
		// 初始化 先隐藏掉
		popView.setVisibility(View.INVISIBLE);
	}

	private void draw() {
		// 绘制marker覆盖物
		MarkerOptions markerOptions = new MarkerOptions();

		// 设置参数
		// 设置图标
		ArrayList<BitmapDescriptor> defaultBitmapDescriptors = new ArrayList<BitmapDescriptor>();

		// 创建BitmapDescriptor
		BitmapDescriptor defaultBitmapDescriptor = BitmapDescriptorFactory
				.fromResource(R.drawable.eat_icon);
		BitmapDescriptor eatBitmapDescriptor = BitmapDescriptorFactory
				.fromResource(R.drawable.icon_geo);
		defaultBitmapDescriptors.add(eatBitmapDescriptor);
		defaultBitmapDescriptors.add(defaultBitmapDescriptor);

		markerOptions.icons(defaultBitmapDescriptors);
		markerOptions.period(5);// 刷新频率 值越小 刷新越快
		markerOptions.title("郑州传智播客-喵喵");
		// 设置位置
		markerOptions.position(new LatLng(itcastLat, itcastLnt));

		// 显示marker覆盖物
		baiduMap.addOverlay(markerOptions);
		// 添加其他三个marker覆盖物
		markerOptions = new MarkerOptions().title("向北")
				.position(new LatLng(itcastLat + 0.001, itcastLnt))
				.icon(defaultBitmapDescriptor);

		baiduMap.addOverlay(markerOptions);

		markerOptions = new MarkerOptions().title("向东")
				.position(new LatLng(itcastLat, itcastLnt + 0.001))

				.icon(defaultBitmapDescriptor);

		baiduMap.addOverlay(markerOptions);

		markerOptions = new MarkerOptions().title("向西南")
				.position(new LatLng(itcastLat - 0.001, itcastLnt - 0.001))

				.icon(defaultBitmapDescriptor);

		baiduMap.addOverlay(markerOptions);

		// 监听 marker覆盖物 的点击
		baiduMap.setOnMarkerClickListener(new OnMarkerClickListener() {

			// 点击marker覆盖物会调用
			// clickedMarker 点击的marker覆盖物
			@Override
			public boolean onMarkerClick(Marker clickedMarker) {
				MapViewLayoutParams mapViewLayoutParams  = new MapViewLayoutParams.Builder()
				//设置 宽  高
				.width(MapViewLayoutParams.WRAP_CONTENT)
				.height(MapViewLayoutParams.WRAP_CONTENT)
				//显示模式  相对 屏幕还是相对 mapview
				.layoutMode(ELayoutMode.mapMode)  //  相对 经纬度
				//设置  坐标
				.position(clickedMarker.getPosition())
				.build();
				
				//点击后进行更新
				mapView.updateViewLayout(popView, mapViewLayoutParams);
				
				//先进行显示
				popView.setVisibility(View.VISIBLE);
				tvTitle.setText(clickedMarker.getTitle());
				return false;
			}
		});

	}

}
