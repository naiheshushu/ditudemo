package zz.itcast.baidumapsz10;

import com.baidu.mapapi.map.CircleOptions;
import com.baidu.mapapi.map.Stroke;
import com.baidu.mapapi.map.TextOptions;
import com.baidu.mapapi.model.LatLng;

import android.os.Bundle;

public class TextOptionsDemo extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		draw();
	}

	/**
	 * 绘制文字覆盖物
	 */
	private void draw() {
		// 创建覆盖物对象
		TextOptions textOptions = new TextOptions();
		//设置参数
		textOptions.fontColor(0x66000000);
		textOptions.fontSize(30);//像素
		textOptions.position(new LatLng(itcastLat, itcastLnt));
		textOptions.rotate(45);
		textOptions.text("郑州传智播客-z10");
		//添加覆盖物
		baiduMap.addOverlay(textOptions);
	}

}
