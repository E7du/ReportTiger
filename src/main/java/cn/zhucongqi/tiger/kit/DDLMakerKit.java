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

import java.math.BigInteger;
import java.util.List;

import com.jfinal.ext.kit.DDLKit;
import com.jfinal.ext.kit.DateTimeKit;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Db;

import cn.zhucongqi.tiger.consts.Consts;
import cn.zhucongqi.tiger.data.DataKit;
import cn.zhucongqi.tiger.model.ReportTigerMappingInfo;
import cn.zhucongqi.tiger.models.Platform;
import cn.zhucongqi.tiger.models.TableMaker;
import cn.zhucongqi.tiger.models.TableMaker.TableType;

/**
 * code注册工具
 * @author BruceZCQ [zcq@zhucongqi.cn]
 * @version
 */
public class DDLMakerKit {

	private static final String INFO_TABLE_NAME_FOOT = "_info";

	private static final String DATA_TABLE_NAME_FOOT = "_data";
	
	private static DDLMakerKit ddlUtil = null;
	

	private DDLMakerKit() {
		
	}
	
	public static DDLMakerKit getInstance() {
		if (null == ddlUtil) {
			ddlUtil = new DDLMakerKit();
		}
		return ddlUtil;
	}
	
	/**
	 * Code Data Table Name 
	 * @param index
	 * @param code
	 * @param platformCode: ios / android
	 * @return tiger_code0_ios_data,... tiger_code9_ios_data
	 */
	public static String codeDataTableName(BigInteger index, String code, String platformCode){
		if (StrKit.isBlank(code) || StrKit.isBlank(platformCode)) {
			return "";
		}
		StringBuilder ddlBuilder = new StringBuilder();
		ddlBuilder.append(Consts.TABLE_NAME_PREFIX).append(code);
		if (null != index) {
			Long hashId = index.longValue() % Consts.TABLE_HASH_SIZE;
			ddlBuilder.append("_").append(hashId);
		}
		ddlBuilder.append(platformCode).append(DATA_TABLE_NAME_FOOT);
		return ddlBuilder.toString();
	}
	
	/**
	 * Code Info TableName
	 * @param code
	 * @param platformCode
	 * @return tiger_code_ios_info/tiger_code_android_info
	 */
	public static String codeInfoTableName(String code, String platformCode){
		if (StrKit.isBlank(code) || StrKit.isBlank(platformCode)) {
			return "";
		}
		
		StringBuilder tableName = new StringBuilder();
		tableName.append(Consts.TABLE_NAME_PREFIX).append(code).append(platformCode).append(INFO_TABLE_NAME_FOOT);
		return tableName.toString();
	}

	/**
	 * 创建表结构
	 * @param tableMaker 创建表所需参数
	 * @return
	 */
	public Boolean create(TableMaker tableMaker) {

		if (null == tableMaker.getAppId() ||
				null == tableMaker.getIntro()
				|| this.codeExistCheck(tableMaker.getCode())) {
			return false;
		}

		if (this.codeMapping(tableMaker) && this.createCodeTables(tableMaker)) {
			return true;
		}

		return false;
	}

	/**
	 * 判断code值是否已经存在
	 * 
	 * @param code
	 * @return
	 */
	private Boolean codeExistCheck(String code) {
		if (StrKit.isBlank(code)) {
			return false;
		}
		ReportTigerMappingInfo mappinginfo = new ReportTigerMappingInfo();
		mappinginfo.setCode(code);
		ReportTigerMappingInfo mappingInfos = mappinginfo.findOne();
		if (null != mappingInfos) {
			return true;
		}
		return false;
	}

	/**
	 * insert mapping
	 * 
	 * @param code
	 * @param desc
	 * @return
	 */
	private Boolean codeMapping(TableMaker tableMaker) {
		ReportTigerMappingInfo mapping = new ReportTigerMappingInfo();
		mapping.setCode(tableMaker.getCode());
		mapping.setIntro(tableMaker.getIntro());
		mapping.setAppid(new BigInteger(tableMaker.getAppId()));
		mapping.setCdate(new BigInteger(String.valueOf(DateTimeKit.getCurrentUnixTime())));
		mapping.setId(new BigInteger("0"));
		return mapping.save();
	}

	/**
	 * 创建info表结构
	 * @param tableMaker
	 * @return
	 */
	private Boolean createCodeInfoTable(TableMaker tableMaker) {
		
		boolean ret = true;
		
		List<Platform> platforms = DataKit.getPlatforms();
		
		for (Platform platform : platforms) {
			String tableName = DDLMakerKit.codeInfoTableName(tableMaker.getCode(), platform.getName());
			String infoTableDDL = DDLKit.createTable(tableName, tableMaker.getTableComment(TableType.INFO)).ddl();
			ret = this.excuteSql(infoTableDDL);
		}
		
		return ret;
	}

	/**
	 * 创建数据tables
	 * @param tableMaker
	 * @return
	 */
	private Boolean createCodeTables(TableMaker tableMaker) {
		
		Boolean ret = false;
		
		// create info table
		if (!this.createCodeInfoTable(tableMaker)) {
			return ret;
		}

		// create data tables
		List<Platform> platforms = DataKit.getPlatforms();
		for (int i = 0; i < Consts.TABLE_HASH_SIZE; i++) {
			for (Platform platform : platforms) {
				String tableName = DDLMakerKit.codeDataTableName(new BigInteger(i+""), tableMaker.getCode(), platform.getName());
				String dataTableDDL = DDLKit.createTable(tableName, tableMaker.getTableComment(TableType.DATA)).addColumns(tableMaker.getColumns()).ddl();
				ret = this.excuteSql(dataTableDDL);
			}
		}
		return ret;
	}

	/**
	 * excute sql
	 * 
	 * @param sql
	 * @return
	 */
	private Boolean excuteSql(String sql) {
		boolean result = false;
		if (null != sql && Db.update(sql) == 0) {
			result = true;
		}
		return result;
	}

}
