/*
 * Copyright 2018 Jobsz
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
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
