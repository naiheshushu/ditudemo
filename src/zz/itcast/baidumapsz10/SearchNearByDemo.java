package zz.itcast.baidumapsz10;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;
import com.baidu.mapapi.overlayutil.PoiOverlay;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiBoundSearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

/**
 * @author wxj
 *周边搜索： 圆形搜索
 */
public class SearchNearByDemo extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		//
		search();
	}

	private void search() {
		// 1. 获取poisearch对象
		PoiSearch poiSearch = PoiSearch.newInstance();

		PoiNearbySearchOption poiNearBySearchOption = new PoiNearbySearchOption();
		//设置圆心  半径
		poiNearBySearchOption.location(new LatLng(itcastLat, itcastLnt));
		poiNearBySearchOption.radius(3000); //单位  米
		// 设置关键字
		poiNearBySearchOption.keyword("加油站");
		poiSearch.searchNearby(poiNearBySearchOption);
		poiSearch.setOnGetPoiSearchResultListener(new OnGetPoiSearchResultListener() {
			
			//获取 poi搜索结果回调
			@Override
			public void onGetPoiResult(PoiResult result) {
				//创建覆盖物对象
				PoiOverlay poiOverlay = new PoiOverlay(baiduMap);
				//设置数据
				poiOverlay.setData(result);
				
				//  搜索覆盖物的方法
//				baiduMap.addOverlay(poiOverlay);
				poiOverlay.addToMap();
				poiOverlay.zoomToSpan();// 显示的合适的位置
			}
			
			
			//获取poi 详情结果
			@Override
			public void onGetPoiDetailResult(PoiDetailResult arg0) {
				
			}
		});
	}
}
