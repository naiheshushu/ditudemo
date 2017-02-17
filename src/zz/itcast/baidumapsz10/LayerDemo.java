package zz.itcast.baidumapsz10;

import com.baidu.mapapi.map.BaiduMap;

import android.os.Bundle;
import android.view.KeyEvent;

public class LayerDemo extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		// 普通图层
		case KeyEvent.KEYCODE_1:
			baiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
			break;
		// 卫星图层
		case KeyEvent.KEYCODE_2:
			baiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
			break;
		// 实时路况
		case KeyEvent.KEYCODE_3:
			baiduMap.setTrafficEnabled(true);
			break;

		default:
			break;
		}

		return super.onKeyDown(keyCode, event);
	}

}
