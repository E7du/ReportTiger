package cn.zhucongqi.tiger.controllers;

import com.jfinal.aop.Before;
import com.jfinal.ext.interceptor.ExceptionInterceptor;
import com.jfinal.ext.core.ControllerExt;

import cn.zhucongqi.tiger.services.AppSecretKeysService;
import cn.zhucongqi.tiger.services.CodeDataService;
import cn.zhucongqi.tiger.validators.AppSecretKeysValidator;

/**
 * 
 * @author BruceZCQ [zcq@zhucongqi.cn]
 * @version
 */
@Before({ExceptionInterceptor.class})
public class TigerMobileController extends ControllerExt {
	
	private AppSecretKeysService appSecretKeysService;
	private CodeDataService codeDataService;
	
	/**
	 * 授权
	 */
	@Before({AppSecretKeysValidator.class})
	public void authDevice() {
		String uuid = this.getPara("uuid");
		String appkey = this.getPara("appkey");
		String bundle = this.getPara("bundle");
		appSecretKeysService.authDevice(uuid, appkey, bundle);
	}
	
	/**
	 * 记录数据
	 */
	 public void logData(){
		 String code = this.getPara("code");
		 String token = this.getPara("token");
		 String jsondata = this.getPara("data");
		 codeDataService.logData(token, code, jsondata);
	 }

	@Override
	public void onExceptionError(Exception e) {
		
	}
}
