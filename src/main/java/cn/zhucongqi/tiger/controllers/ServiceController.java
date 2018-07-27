/**
 * 用于一些公共的服务管理，比如验证码等
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
