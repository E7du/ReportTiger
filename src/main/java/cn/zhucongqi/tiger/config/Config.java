/**
 * JFinal 的配置信息
 */
package cn.zhucongqi.tiger.config;

import java.util.Properties;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.ext.config.JFinalConfigExt;
import com.jfinal.kit.StrKit;
import com.jfinal.template.Engine;

import cn.zhucongqi.tiger.consts.Consts;
import cn.zhucongqi.tiger.controllers.DashboardController;
import cn.zhucongqi.tiger.data.DataKit;

/**
 * config
 * @author BruceZCQ [zcq@zhucongqi.cn]
 * @version
 */
public class Config extends JFinalConfigExt {

	@Override
	public void configMoreConstants(Constants me) throws RuntimeException {
		if (StrKit.isBlank(Consts.TABLE_NAME_PREFIX)) {
			throw new RuntimeException("Please set the table name prefix first!");
		}
	}

	@Override
	public void configMoreRoutes(Routes me) {
		 //指定/controller
		 me.add("/", DashboardController.class);		
	}

	@Override
	public void configMorePlugins(Plugins me) {
		// do more plugin config
	}

	@Override
	public void configMoreInterceptors(Interceptors me) {
		// do more interceptor config
	}

	@Override
	public void configMoreHandlers(Handlers me) {
		//do more handler config
	}

	@Override
	public void afterJFinalStarted() {
		//初始化数据
		DataKit.init();		
	}

	@Override
	public Properties getLazyProp() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void configEngineMore(Engine e) {
		// TODO Auto-generated method stub
		
	}

}
