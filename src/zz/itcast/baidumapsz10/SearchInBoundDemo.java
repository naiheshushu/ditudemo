package zz.itcast.baidumapsz10;

import android.os.Bundle;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;
import com.baidu.mapapi.overlayutil.PoiOverlay;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiBoundSearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;

/**
 * @author wxj 范围搜索：矩形搜索
 */
public class SearchInBoundDemo extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		//
		search();
	}

	private void search() {
		// 1. 获取poisearch对象
		PoiSearch poiSearch = PoiSearch.newInstance();

		PoiBoundSearchOption poiBoudnSearchOption = new PoiBoundSearchOption();
		// 设置参数
		// 设置矩形 东北点 西南点
		LatLngBounds latlngBounds = new LatLngBounds.Builder()
		// 设置 对角点
				.include(new LatLng(34.790645, 113.558995)) // 东北点
				.include(new LatLng(34.789233, 113.550675))// 西南点
				.build();

		poiBoudnSearchOption.bound(latlngBounds);
		// 设置关键字
		poiBoudnSearchOption.keyword("加油站");
		poiSearch.searchInBound(poiBoudnSearchOption);
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
