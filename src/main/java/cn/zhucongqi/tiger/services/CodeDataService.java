/**
 * 记录数据Service
 */
package cn.zhucongqi.tiger.services;

import java.math.BigInteger;
import java.util.List;

import com.jfinal.ext.core.Service;
import com.jfinal.ext.kit.DateTimeKit;
import com.jfinal.ext.kit.FastJsonKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

import cn.zhucongqi.tiger.kit.DDLMakerKit;
import cn.zhucongqi.tiger.kit.UserAgentKit;
import cn.zhucongqi.tiger.model.TigerDeviceToken;

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
		
		String platformName = UserAgentKit.platformName(this.controller.getRequest().getHeader("User-Agent"));
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
	 * @return
	 * TODO 3小时内才算过期
	 */
	public boolean isTimeOut(String token){
	
		List<TigerDeviceToken> deviceTokens = TigerDeviceToken.dao.find("SELECT * FROM "
				+ TigerDeviceToken.table + " WHERE token = ?  LIMIT 1 ", token);
		if (null != deviceTokens && deviceTokens.size() == 1) {
			BigInteger timeout = deviceTokens.get(0).get("timeout");
			return DateTimeKit.getCurrentUnixTime() < Long.valueOf(timeout.toString()) ;
		}
		return false;
	}
	
}
