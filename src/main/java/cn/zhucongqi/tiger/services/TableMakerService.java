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

import java.util.List;

import com.jfinal.ext.core.Service;

import cn.zhucongqi.tiger.kit.DDLMakerKit;
import cn.zhucongqi.tiger.model.ReportTigerAppSecretKeys;
import cn.zhucongqi.tiger.models.TableMaker;

/**
 * 数据统计表生成
 * @author BruceZCQ [zcq@zhucongqi.cn]
 * @version
 */
public class TableMakerService extends Service {
	
	/**
	 * 获取所有的 apps
	 * @return
	 */
	public List<ReportTigerAppSecretKeys> getAllApps(){
		return (new ReportTigerAppSecretKeys().find());
	}
	
	/**
	 * 创建表结构
	 * @param tableMaker
	 * TODO 自定义 error code
	 */
	public void regCode(TableMaker tableMaker){
		
		boolean ret = DDLMakerKit.getInstance().create(tableMaker);
		StringBuilder retSbr = new StringBuilder();
		if (ret) {
			retSbr.append("Code创建成功");
		}else{
			retSbr.append("Code创建失败");
		}
		this.controller.renderText(retSbr.toString());
	}
	
}
