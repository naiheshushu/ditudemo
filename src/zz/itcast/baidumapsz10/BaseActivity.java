package zz.itcast.baidumapsz10;

import android.app.Activity;
import android.os.Bundle;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.model.LatLng;

/**
 * 基类
 * 初始化地图
 * 初始化一些经纬度
 *
 */
public class BaseActivity extends Activity {
    public MapView mapView;
    public BaiduMap baiduMap;
    public double itcastLat = 34.798413;//传智的纬度
    public double itcastLnt = 113.551107;//传智的经度
    public LatLng slPostion = new LatLng(34.813292 ,113.552154);//升龙坐标
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_world);
        mapView = (MapView) findViewById(R.id.mapview);
        
        initMap();
    }
    
    // 初始化地图
    private void initMap() {
        // 获取baiduMap
        baiduMap = mapView.getMap();
        // 设置默认缩放级别
        MapStatusUpdate zoomStatusUpdate = MapStatusUpdateFactory.zoomTo(15);
        baiduMap.setMapStatus(zoomStatusUpdate);
        // 设置中心点
        LatLng centerLatlng = new LatLng(34.798413, 113.551107);// 纬度、经度
        MapStatusUpdate centerStatusUpdate = MapStatusUpdateFactory.newLatLng(centerLatlng);
        baiduMap.setMapStatus(centerStatusUpdate);
        // 隐藏标尺和按钮
        // mapView.showScaleControl(false);
        // mapView.showZoomControls(false);
        
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();//mapview的生命周期方法
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }
    
    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }
}
