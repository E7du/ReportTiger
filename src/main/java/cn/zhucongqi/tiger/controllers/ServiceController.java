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
import com.jfinal.ext.interceptor.ExceptionInterceptor;
import com.jfinal.ext.core.ControllerExt;

/**
 * 
 * @author BruceZCQ [zcq@zhucongqi.cn]
 * @version
 */
@Before({ExceptionInterceptor.class})
public class ServiceController extends ControllerExt {

	/**
	 * 生成图像验证码
	 */
	@Clear
	public void captcha(){
		this.renderCaptcha();
	}

	@Override
	public void onExceptionError(Exception e) {
		// TODO Auto-generated method stub
		
	}

}
