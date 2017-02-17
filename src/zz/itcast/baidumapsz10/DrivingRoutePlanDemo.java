package zz.itcast.baidumapsz10;

import java.util.List;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.overlayutil.DrivingRouteOverlay;
import com.baidu.mapapi.search.route.DrivingRouteLine;
import com.baidu.mapapi.search.route.DrivingRoutePlanOption;
import com.baidu.mapapi.search.route.DrivingRoutePlanOption.DrivingPolicy;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRouteResult;

import android.os.Bundle;

/**
 * @author wxj 驾车路线搜索
 */
public class DrivingRoutePlanDemo extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		driving();
	}

	private void driving() {

		// 获取 RoutePlanSearch 对象
		RoutePlanSearch planSearch = RoutePlanSearch.newInstance();
		DrivingRoutePlanOption drivingRoutePlanOption = new DrivingRoutePlanOption();
		// 设置 起点 终点 驾车策略
		drivingRoutePlanOption.from(PlanNode.withLocation(slPostion));// 升龙
		drivingRoutePlanOption.to(PlanNode.withLocation(new LatLng(itcastLat,
				itcastLnt)));// 传智
		drivingRoutePlanOption.policy(DrivingPolicy.ECAR_TIME_FIRST);//时间最短
		// 发起搜索
		planSearch.drivingSearch(drivingRoutePlanOption);

		// 获取展示结果
		planSearch
				.setOnGetRoutePlanResultListener(new OnGetRoutePlanResultListener() {

					@Override
					public void onGetWalkingRouteResult(WalkingRouteResult walkingRouteResult) {
						//步行搜索结果
					}

					@Override
					public void onGetTransitRouteResult(TransitRouteResult transitRouteResult) {
						//换成搜索结果
					}

					@Override
					public void onGetDrivingRouteResult(DrivingRouteResult drivingRouteResult) {
						//驾车搜索结果
						DrivingRouteOverlay drivingRouteOverlay = new DrivingRouteOverlay(baiduMap);
						List<DrivingRouteLine> routeLines = drivingRouteResult.getRouteLines();
						drivingRouteOverlay.setData(routeLines.get(0));
						//进行展示
						drivingRouteOverlay.addToMap();
						//显示到合适的位置
						drivingRouteOverlay.zoomToSpan();
					}
				});
	}
}
