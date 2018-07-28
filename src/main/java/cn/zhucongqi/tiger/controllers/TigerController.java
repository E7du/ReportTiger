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
package cn.zhucongqi.tiger.controllers;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.ext.interceptor.ExceptionInterceptor;
import com.jfinal.ext.core.ControllerExt;
import com.jfinal.ext.kit.DDLKit.Column;
import com.jfinal.ext.kit.PageViewKit;

import cn.zhucongqi.tiger.consts.Consts;
import cn.zhucongqi.tiger.consts.ViewPaths;
import cn.zhucongqi.tiger.data.DataKit;
import cn.zhucongqi.tiger.models.Platform;
import cn.zhucongqi.tiger.models.TableMaker;
import cn.zhucongqi.tiger.models.TableMaker.TableType;
import cn.zhucongqi.tiger.services.AppSecretKeysService;
import cn.zhucongqi.tiger.services.TableMakerService;
import cn.zhucongqi.tiger.validators.TigerTableMakerValidator;

/**
 * 
 * @author BruceZCQ [zcq@zhucongqi.cn]
 * @version
 */
@Before({ExceptionInterceptor.class})
public class TigerController extends ControllerExt {

	private AppSecretKeysService appSecretKeysService;
	private TableMakerService tableMakerService;

	@Clear
	public void index(){
		this.appV();
	}

	/**
	 * 添加 Appview
	 */
	private void appV(){
		this.setAttr("packname", Consts.DEFAULT_APP_PACKAGE_NAME);
		this.render(PageViewKit.getJSPPageViewFromWebInf(ViewPaths.APP_VIEW_PATH, "index"));
	}
	
	/**
	 * 预览 DDL
	 */
	@Before({TigerTableMakerValidator.class})
	public void preview(){
		TableMaker tableMaker = this.getTableMaker();
		if (null == tableMaker) {
			this.renderText("参数错误");
		}else{
			this.renderText(tableMaker.ddl(TableType.DATA, Platform.IOS_10));	
		}
	}
	
	/**
	 * 获取 maker model
	 * @return
	 */
	private TableMaker getTableMaker(){
		
		String appId = this.getPara("app");
		String code = this.getPara("code").trim();
		String codeComment = this.getPara("codecomment").trim();

		JSONArray fields = JSON.parseArray(this.getPara("fields"));
		if (null == fields) {
			return null;
		}
		
		List<Column> columns = new ArrayList<Column>();
		for (int i = 0; i < fields.size(); i++) {
			String[] field = fields.getString(i).split(":");
			if (field.length != 7) {
				return null;
			}
			String colName = field[0];
			int colType = Integer.valueOf(field[1]);
			int colSize = Integer.valueOf(field[2]);
			String colInitVal = field[3];
			String colComment = field[4];
			boolean pk = Integer.valueOf(field[5]) == 0 ? false : true;
			boolean uk = Integer.valueOf(field[6]) == 0 ? false : true;
			Column column = new Column(colName, DataKit.getTypeName(colType), colComment, colSize, colInitVal, pk, uk);
			columns.add(column);
		}
		
		TableMaker tableMaker = new TableMaker();
		tableMaker.setAppId(appId);
		tableMaker.setCode(code);
		tableMaker.setIntro(codeComment);
		tableMaker.setColumns(columns);
		return tableMaker;
	}
	
	/**
	 * 添加一个App
	 */
	public void addApp(){
		String packagevalue = this.getPara("packagename");
		appSecretKeysService.addApp(packagevalue);
	}

	/**
	 * 添加 code
	 */
	@Clear
	public void regCodeV(){
		this.apps();
		this.render(PageViewKit.getJSPPageViewFromWebInf(ViewPaths.CODE_VIEW_PATH,"index"));
	}
	
	private void apps(){
		this.setAttr("apps", tableMakerService.getAllApps());
	}
	
	/**
	 * 注册code  创建相应表
	 */
	@Before({TigerTableMakerValidator.class})
	public void regCode() {
		TableMaker tableMaker = this.getTableMaker();
		if (null == tableMaker) {
			this.renderText("参数错误");
		}else{
			tableMakerService.regCode(tableMaker);
		}
	}
	
	/**
	 * 校验包名是否可用
	 */
	public void validatePackage() {
		String packagevalue = this.getPara("packagename");
		appSecretKeysService.validatePackage(packagevalue);
	}

	@Override
	public void onExceptionError(Exception e) {
		
	}
}
