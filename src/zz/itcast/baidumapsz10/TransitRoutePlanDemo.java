package zz.itcast.baidumapsz10;

import java.util.List;

import android.os.Bundle;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.overlayutil.TransitRouteOverlay;
import com.baidu.mapapi.overlayutil.WalkingRouteOverlay;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.TransitRouteLine;
import com.baidu.mapapi.search.route.TransitRoutePlanOption;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRouteLine;
import com.baidu.mapapi.search.route.WalkingRoutePlanOption;
import com.baidu.mapapi.search.route.WalkingRouteResult;
import com.baidu.platform.comapi.map.t;

public class TransitRoutePlanDemo extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		transit();
	}

	private void transit() {

		// 获取 RoutePlanSearch 对象
		RoutePlanSearch planSearch = RoutePlanSearch.newInstance();

		TransitRoutePlanOption transitRouteplanOption = new TransitRoutePlanOption();
		transitRouteplanOption.city("郑州");
		transitRouteplanOption.from(PlanNode.withLocation(slPostion));// 升龙
		transitRouteplanOption.to(PlanNode.withLocation(new LatLng(itcastLat,
				itcastLnt)));// 传智
		planSearch.transitSearch(transitRouteplanOption);
		// 获取展示结果
		planSearch
				.setOnGetRoutePlanResultListener(new OnGetRoutePlanResultListener() {

					@Override
					public void onGetWalkingRouteResult(
							WalkingRouteResult walkingRouteResult) {
						// 步行搜索结果

					}

					@Override
					public void onGetTransitRouteResult(
							TransitRouteResult transitRouteResult) {
						// 换成搜索结果
						TransitRouteOverlay transitRouteOverlay = new TransitRouteOverlay(
								baiduMap);
						List<TransitRouteLine> routeLines = transitRouteResult
								.getRouteLines();
						transitRouteOverlay.setData(routeLines.get(0));
						// 进行展示
						transitRouteOverlay.addToMap();
						// 显示到合适的位置
						transitRouteOverlay.zoomToSpan();

						baiduMap.setOnMarkerClickListener(transitRouteOverlay);
					}

					@Override
					public void onGetDrivingRouteResult(
							DrivingRouteResult drivingRouteResult) {
						// 驾车搜索结果

					}
				});
	}
}
