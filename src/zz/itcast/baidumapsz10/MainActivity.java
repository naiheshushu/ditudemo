package zz.itcast.baidumapsz10;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.SDKInitializer;

public class MainActivity extends Activity {

	private static String[] objects = new String[] { "hello world", "图层",
			"圆形覆盖物", "展示文字", "marker覆盖物", "矩形范围内搜索", "圆形区域", "全城搜索", "驾车路线",
			"步行路线", "公交换乘", "我的位置" };
	private static Class[] clazzs = new Class[] { HelloWorld.class,
			LayerDemo.class, CircleOptionsDemo.class, TextOptionsDemo.class,
			MarkerOptionsDemo.class, SearchInBoundDemo.class,
			SearchNearByDemo.class, SearchInCity.class,
			DrivingRoutePlanDemo.class, WalkRoutePlanDemo.class,
			TransitRoutePlanDemo.class,LocationDemo.class };

	private ListView list;
	private ArrayAdapter<String> adapter;
	private MyBroadCast broadCast;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		list = (ListView) findViewById(R.id.listview);

		adapter = new ArrayAdapter<String>(getApplicationContext(),
				android.R.layout.simple_list_item_1, objects) {
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				TextView tv = (TextView) super.getView(position, convertView,
						parent);
				tv.setTextColor(Color.BLACK);
				return tv;
			}
		};

		list.setAdapter(adapter);
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(getApplicationContext(),
						clazzs[position]);
				startActivity(intent);
			}
		});

		// SDKInitializer.initialize(getApplicationContext()); //
		// 不能传递Activity，必须是全局Context
		initSDK();
	}

	private void initSDK() {
		SDKInitializer.initialize(getApplicationContext()); // 不能传递Activity，必须是全局Context
		broadCast = new MyBroadCast();
		IntentFilter filter = new IntentFilter();
		filter.addAction(SDKInitializer.SDK_BROADCAST_ACTION_STRING_NETWORK_ERROR);
		filter.addAction(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR);
		registerReceiver(broadCast, filter);
	}

	private class MyBroadCast extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			if (intent.getAction().equals(
					SDKInitializer.SDK_BROADCAST_ACTION_STRING_NETWORK_ERROR)) {
				Toast.makeText(getApplicationContext(), "网络错误",
						Toast.LENGTH_SHORT).show();
			} else if (intent
					.getAction()
					.equals(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR)) {
				Toast.makeText(getApplicationContext(), "key校验失败",
						Toast.LENGTH_SHORT).show();
			}
		}

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (broadCast != null) {
			unregisterReceiver(broadCast);
		}
	}

}
