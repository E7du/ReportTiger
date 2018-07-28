package cn.zhucongqi.tiger.kit;

import javax.servlet.http.HttpServletRequest;

import com.jfinal.kit.StrKit;

import cn.zhucongqi.tiger.models.Platform;
  
/** 
 * 获取请求对应的 platformname
 * @author BruceZCQ [zcq@zhucongqi.cn]
 * @version
 */
public class UserAgentKit {  

	private UserAgentKit() {
		
	}
	/**
	 * 获取 UA
	 * @param userAgent
	 * @return
	 */
    public static String platformName(HttpServletRequest request){  
    	if (null == request) {
			return "";
		}
    	
    	String userAgent = request.getHeader("User-Agent");
    	if (StrKit.isBlank(userAgent)) {
			return "";
		}
    	
    	userAgent = userAgent.toLowerCase();
    	
		if (userAgent.contains("iphone") || userAgent.contains("ipad")) {
			return Platform.IOS;
		} else if (userAgent.contains("android")) {
			return Platform.ANDROID;
		} 
		return "";
    } 
}  