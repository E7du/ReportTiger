package cn.zhucongqi.tiger.services;

import java.util.List;

import com.jfinal.ext.core.Service;

import cn.zhucongqi.tiger.kit.DDLMakerKit;
import cn.zhucongqi.tiger.model.TigerAppSecretKeys;
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
	public List<TigerAppSecretKeys> getAllApps(){
		return TigerAppSecretKeys.dao.find("SELECT id, package_name FROM "+TigerAppSecretKeys.table);
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