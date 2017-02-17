package zz.itcast.baidumapsz10;

import java.util.List;

import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.overlayutil.PoiOverlay;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiDetailSearchOption;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;

/**
 * @author wxj 城市搜索
 */
public class SearchInCity extends BaseActivity {

	private int pageNum = 0; // 默认显示第一页
	private PoiSearch poiSearch;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 1. 获取poisearch对象

		poiSearch = PoiSearch.newInstance();
		//
		search();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_1: // 显示下一页
			pageNum++;
			search();
			break;

		default:
			break;
		}

		return super.onKeyDown(keyCode, event);
	}

	class MyPoiOverlay extends PoiOverlay {

		public MyPoiOverlay(BaiduMap arg0) {
			super(arg0);
		}

		// 当mark覆盖物 被点击的时候调用
		@Override
		public boolean onPoiClick(int i) {
			//
			// 获取 poiResult
			PoiResult poiResult = getPoiResult();
			List<PoiInfo> allPoi = poiResult.getAllPoi();
			PoiInfo poiInfo = allPoi.get(i);
			String message = poiInfo.address + "::" + poiInfo.name + "::"
					+ poiInfo.phoneNum;
			Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT)
					.show();

			// 发起详情搜索
			PoiDetailSearchOption poiDetailSearchOption = new PoiDetailSearchOption();
			// 设置uid
			poiDetailSearchOption.poiUid(poiInfo.uid);
			poiSearch.searchPoiDetail(poiDetailSearchOption);
			return super.onPoiClick(i);
		}

	}

	private void search() {
		PoiCitySearchOption poiCItySearchOption = new PoiCitySearchOption();
		poiCItySearchOption.city("郑州");
		poiCItySearchOption.pageNum(pageNum);// 模式显示第一页
		poiCItySearchOption.keyword("加油站");
		poiSearch.searchInCity(poiCItySearchOption);
		poiSearch
				.setOnGetPoiSearchResultListener(new OnGetPoiSearchResultListener() {

					// 获取 poi搜索结果回调
					@Override
					public void onGetPoiResult(PoiResult result) {
						// 清空覆盖物
						baiduMap.clear();
						// 创建覆盖物对象
						PoiOverlay poiOverlay = new MyPoiOverlay(baiduMap);
						// 设置数据
						poiOverlay.setData(result);

						// 搜索覆盖物的方法
						// baiduMap.addOverlay(poiOverlay);
						poiOverlay.addToMap();
						// poiOverlay.removeFromMap();
						poiOverlay.zoomToSpan();// 显示的合适的位置

						// 处理覆盖物的点击
						baiduMap.setOnMarkerClickListener(poiOverlay);
					}

					// 获取poi 详情结果
					@Override
					public void onGetPoiDetailResult(
							PoiDetailResult detailResult) {
						String message = detailResult.getCommentNum() + "::"
								+ detailResult.getEnvironmentRating() + "::"
								+ detailResult.getPrice();
						Toast.makeText(getApplicationContext(), message,
								Toast.LENGTH_SHORT).show();

					}
				});
	}
}
