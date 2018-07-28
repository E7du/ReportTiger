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
