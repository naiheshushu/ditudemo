package zz.itcast.baidumapsz10;

import java.util.List;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.overlayutil.DrivingRouteOverlay;
import com.baidu.mapapi.overlayutil.WalkingRouteOverlay;
import com.baidu.mapapi.search.route.DrivingRouteLine;
import com.baidu.mapapi.search.route.DrivingRoutePlanOption;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRouteLine;
import com.baidu.mapapi.search.route.WalkingRoutePlanOption;
import com.baidu.mapapi.search.route.WalkingRouteResult;
import com.baidu.mapapi.search.route.DrivingRoutePlanOption.DrivingPolicy;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class WalkRoutePlanDemo extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		walking();
	}

	private void walking() {

		// 获取 RoutePlanSearch 对象
		RoutePlanSearch planSearch = RoutePlanSearch.newInstance();
		
		
		WalkingRoutePlanOption walkingRoutePlanOption =  new WalkingRoutePlanOption();
		//设置起点  重点  
		walkingRoutePlanOption.from(PlanNode.withLocation(slPostion));//升龙
		walkingRoutePlanOption.to(PlanNode.withLocation(new LatLng(itcastLat, itcastLnt)));//传智
		
		planSearch.walkingSearch(walkingRoutePlanOption );

		// 获取展示结果
		planSearch
				.setOnGetRoutePlanResultListener(new OnGetRoutePlanResultListener() {

					@Override
					public void onGetWalkingRouteResult(WalkingRouteResult walkingRouteResult) {
						//步行搜索结果
						WalkingRouteOverlay walkingRouteOverlay = new WalkingRouteOverlay(baiduMap);
						  List<WalkingRouteLine> routeLines = walkingRouteResult.getRouteLines();
						walkingRouteOverlay.setData(routeLines.get(0));
						//进行展示
						walkingRouteOverlay.addToMap();
						//显示到合适的位置
						walkingRouteOverlay.zoomToSpan();
					}

					@Override
					public void onGetTransitRouteResult(TransitRouteResult transitRouteResult) {
						//换成搜索结果
					}

					@Override
					public void onGetDrivingRouteResult(DrivingRouteResult drivingRouteResult) {
						//驾车搜索结果
					
					}
				});
	}
}
