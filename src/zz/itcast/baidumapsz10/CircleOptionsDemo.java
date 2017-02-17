package zz.itcast.baidumapsz10;

import com.baidu.mapapi.map.CircleOptions;
import com.baidu.mapapi.map.Stroke;
import com.baidu.mapapi.model.LatLng;

import android.os.Bundle;

public class CircleOptionsDemo extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		draw();
	}

	/**
	 * 绘制圆形覆盖物
	 */
	private void draw() {
		// 创建覆盖物对象
		CircleOptions circleOptions = new CircleOptions();
		//设置参数
		circleOptions.radius(100);  //  米
		//圆心
		circleOptions.center(new LatLng(itcastLat, itcastLnt));//传智
		circleOptions.stroke(new Stroke(20, 0x60ff0000)) ;  //像素     argb
		//填充色
		circleOptions.fillColor(0x6000ff00);
		
		//添加覆盖物
		baiduMap.addOverlay(circleOptions);
	}
	
}
