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
package cn.zhucongqi.tiger.services;

import java.math.BigInteger;

import com.jfinal.ext.core.Service;
import com.jfinal.ext.kit.DateTimeKit;
import com.jfinal.ext.kit.FastJsonKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

import cn.zhucongqi.tiger.kit.DDLMakerKit;
import cn.zhucongqi.tiger.kit.UserAgentKit;
import cn.zhucongqi.tiger.model.ReportTigerDeviceToken;

/**
 * 数据记录 Service
 * @author BruceZCQ [zcq@zhucongqi.cn]
 * @version
 */
public class CodeDataService extends Service {

	/**
	 *  插入数据
	 * @param code
	 * @param clList
	 */
	public void logData(String token, String code, String jsondata){
		if (!isTimeOut(token)) {
			this.controller.renderNull();
		}
		String platformName = UserAgentKit.platformName(this.controller.getRequest());
		// update info id
		String infoTableName = DDLMakerKit.codeInfoTableName(code, platformName);
		Record info = new Record();
		info.set("id", "0");
		Db.save(infoTableName, info);
		// save data to data table ref hashid
		String dataTableName = DDLMakerKit.codeDataTableName(info.getBigInteger("id"), code, platformName);
		Record data = (Record)FastJsonKit.jsonToRecord(jsondata);
		data.set("id", "0");
		Db.save(dataTableName, data);
	}
	
	/**
	 * token 是否过期
	 * @param tocken
	 * TODO 3小时内才算过期
	 */
	public boolean isTimeOut(String token){
		ReportTigerDeviceToken deviceToken = new ReportTigerDeviceToken();
		deviceToken.setToken(token);
		ReportTigerDeviceToken deviceTokens = deviceToken.findOne();
		if (null != deviceTokens) {
			BigInteger timeout = deviceTokens.getTimeout();
			return DateTimeKit.getCurrentUnixTime() < Long.valueOf(timeout.toString()) ;
		}
		return false;
	}
	
}
