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
package cn.zhucongqi.tiger.data;

import java.util.ArrayList;
import java.util.List;
import cn.zhucongqi.tiger.models.ColumnType;
import cn.zhucongqi.tiger.models.Platform;
import com.jfinal.ext.kit.DDLKit;

/**
 * 
 * @author BruceZCQ [zcq@zhucongqi.cn]
 * @version
 */
public class DataKit {
	
	// Platforms
	private static List<Platform> platforms = null;
	// table column types
	private static List<ColumnType> columnsTypes = null;
	
	private DataKit() {
		
	}
	
	/**
	 * 初始化全局数据
	 */
	public static void init(){
		
	}
	
	/**
	 * 获取所有 Platform
	 * @return
	 */
	public static List<Platform> getPlatforms(){
		if (null == platforms) {
			platforms = new ArrayList<Platform>();
			Platform ios = new Platform(Platform.IOS_10, Platform.IOS);
			platforms.add(ios);
			Platform android = new Platform(Platform.ANDROID_20, Platform.ANDROID);
			platforms.add(android);
		}
		return platforms;
	}
	
	/**
	 * 获取 Platform
	 * @param id
	 * @return
	 */
	private static Platform getPlatform(int id){
		if (null == platforms) {
			DataKit.getPlatforms();
		}
		for (Platform platform : platforms) {
			if (platform.getId() == id) {
				return platform;
			}
		}
		return platforms.get(0);
	}
	
	/**
	 * 获取 platform 的 name
	 * @param id
	 * @return
	 */
	public static String getPlatformName(int id){
		if (null == platforms) {
			DataKit.getPlatforms();
		}
		return DataKit.getPlatform(id).getName();
	}
	
	/**
	 * 获取所有的 column 类型
	 * @return
	 */
	public static List<ColumnType> getColumnTypes(){
		if (null == columnsTypes) {
			columnsTypes = new ArrayList<ColumnType>();
			ColumnType intType = new ColumnType(ColumnType.INT_ID_1,DDLKit.INT);
			columnsTypes.add(intType);
			ColumnType varcharType = new ColumnType(ColumnType.VARCHAR_ID_2, DDLKit.VARCHAR);
			columnsTypes.add(varcharType);
			ColumnType textType = new ColumnType(ColumnType.TEXT_ID_3, DDLKit.TEXT);
			columnsTypes.add(textType);
			ColumnType bigintType = new ColumnType(ColumnType.BIGINT_ID_4, DDLKit.BIGINT);
			columnsTypes.add(bigintType);
			ColumnType tinyintType = new ColumnType(ColumnType.TINYINT_ID_5, DDLKit.TINYINT);
			columnsTypes.add(tinyintType);
		}
		return columnsTypes;
	}
	
	private static ColumnType getType(int typeId){
		if (null == columnsTypes) {
			DataKit.getColumnTypes();
		}
		
		for (ColumnType columnType : columnsTypes) {
			if (columnType.getId() == typeId) {
				return columnType;
			}
		}
		return columnsTypes.get(0);
	}
	
	/**
	 * 获取类型名称
	 * @param typeId
	 * @return
	 */
	public static String getTypeName(int typeId){
		return getType(typeId).getName();
	}
}
