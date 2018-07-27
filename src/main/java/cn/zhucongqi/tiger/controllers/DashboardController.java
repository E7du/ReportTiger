/**
 * 
 */
package cn.zhucongqi.tiger.controllers;

import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.ext.core.ControllerExt;
import com.jfinal.ext.interceptor.ExceptionInterceptor;
import com.jfinal.ext.kit.PageViewKit;

import cn.zhucongqi.tiger.consts.ViewPaths;

/**
 * 
 * @author BruceZCQ [zcq@zhucongqi.cn]
 * @version
 */
@Before({ExceptionInterceptor.class})
public class DashboardController extends ControllerExt {

	@Clear
	public void index(){
		this.indexV();
	}
	
	private void indexV(){
		this.render(PageViewKit.getJSPPageViewFromWebInf(ViewPaths.DASHBOARD_VIEW_PATH, "index"));
	}

	@Override
	public void onExceptionError(Exception e) {
		
	}
	
}
