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
package cn.zhucongqi.tiger.consts;

/**
 * 
 * @author BruceZCQ [zcq@zhucongqi.cn]
 * @version
 */
public class Consts {

	/**
	 * token 过期时间[天数]
	 */
	public static final int TOKEN_TIMEOUT_DAYS = 7;
	
	/**
	 * table hash size , init 10
	 */
	public static final int TABLE_HASH_SIZE = 10;
	
	/**
	 * token
	 */
	public static final String TOKEN = "token";
	
	/**
	 * token timeout
	 */
	public static final String TIMEOUT = "timeout";
	
	/**
	 * 默认的app包名
	 */
	public static final String DEFAULT_APP_PACKAGE_NAME = "cn.zhucongqi.";
	
	/**
	 * 表名的前缀
	 */
	public static final String TABLE_NAME_PREFIX = "report_tiger_";
	
	private Consts() {
		
	}
	
}
