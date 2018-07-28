package cn.zhucongqi.tiger.services;

import java.math.BigInteger;

import com.jfinal.ext.core.Service;
import com.jfinal.ext.kit.DateTimeKit;
import com.jfinal.ext.kit.RandomKit;
import com.jfinal.kit.HashKit;

import cn.zhucongqi.tiger.consts.Consts;
import cn.zhucongqi.tiger.model.ReportTigerAppSecretKeys;
import cn.zhucongqi.tiger.model.ReportTigerDeviceToken;

/**
 * 处理移动客户端请求
 * @author BruceZCQ [zcq@zhucongqi.cn]
 * @version
 */
public class AppSecretKeysService extends Service {

	/**
	 * 授权
	 * @param uuid
	 * @param appkey
	 * @param bundle
	 * @return
	 */
	public void authDevice(String uuid, String appkey, String bundle){
		ReportTigerAppSecretKeys fetch = new ReportTigerAppSecretKeys();
		fetch.setAppkey(appkey);
		fetch.setBundle(bundle);
		ReportTigerAppSecretKeys appSecretKeys = fetch.findOne();
		
		ReportTigerDeviceToken deviceToken= new ReportTigerDeviceToken();
		String token = null;
		Long timeout = null;
		
		if (null != appSecretKeys) {
			token = RandomKit.randomMD5Str();
			timeout = DateTimeKit.getUnixTimeAfterDay(Consts.TOKEN_TIMEOUT_DAYS);
			deviceToken.setUuid(uuid);
			deviceToken.setToken(token);
			deviceToken.setTimeout(new BigInteger(String.valueOf(timeout)));
			if (deviceToken.save()) {
				this.controller.setAttr(Consts.TOKEN, token);
				this.controller.setAttr(Consts.TIMEOUT, timeout);
				this.controller.renderJson();
			}
		}else {
			this.controller.renderError(401);
		}
 	}
		
	/**
	 * 首次注册app
	 * @param packagevalue
	 */
	public void addApp(String packagevalue) {
		String packageval = this.wrapPackagename(packagevalue);
		String appKey = HashKit.md5(RandomKit.randomStr());
		String secretkeysalt = HashKit.md5(RandomKit.randomStr());
		String bundle = HashKit.md5(packageval);
		String secretkey = HashKit.md5(secretkeysalt + bundle + appKey
				+ secretkeysalt + appKey + bundle + secretkeysalt);
		ReportTigerAppSecretKeys appSecretKeys = new ReportTigerAppSecretKeys();
		appSecretKeys.setId(new BigInteger("0"));
		appSecretKeys.setBundle(bundle);
		appSecretKeys.setAppkey(appKey);
		appSecretKeys.setSecretkey(secretkey);
		appSecretKeys.setPackageName(packageval);
		appSecretKeys.setSecretkeysalt(secretkeysalt);
		if (appSecretKeys.save()) {
			this.controller.renderText("包名称:<br/>" + packageval
					+ "<br/>AppKey:<br/>" + appKey + "<br/>请把此key拷贝到客户端使用");
		} else {
			this.controller.renderText("ec:500");
		}
	}
	
	/**
	 * 校验包名称是否已被注册。
	 * @param packagevalue
	 */
	public void validatePackage(String packageName) {
		packageName = this.wrapPackagename(packageName);
		ReportTigerAppSecretKeys appSecretKey = new ReportTigerAppSecretKeys();
		appSecretKey.setPackageName(packageName);
		ReportTigerAppSecretKeys appSecretKeys = appSecretKey.findOne();
		if (appSecretKeys != null) {
			this.controller.renderText("包名称:<br/>" + packageName
					+ "已经存在,请重新输入！");
		} else {
			this.controller.renderText("包名称:<br/>" + packageName + "可以使用！");
		}
	}
	
	/**
	 * packagename warp
	 * @param packageValue
	 * @return
	 */
	private String wrapPackagename(String packageValue){
		String packagename = Consts.DEFAULT_APP_PACKAGE_NAME;
		if (packageValue.startsWith(packagename)) {
			return packageValue;
		}
		return packagename + packageValue;
	}
}
