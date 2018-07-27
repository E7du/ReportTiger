/**
 * 注册code封装属性 code,intro,appid
 */
package cn.zhucongqi.tiger.models;

import java.util.List;
import cn.zhucongqi.tiger.data.DataKit;
import cn.zhucongqi.tiger.kit.DDLMakerKit;
import com.jfinal.ext.kit.DDLKit;
import com.jfinal.ext.kit.DDLKit.Column;
import com.jfinal.ext.kit.DDLKit.Table;

/**
 * 
 * @author BruceZCQ [zcq@zhucongqi.cn]
 * @version
 */
public class TableMaker {
	
	public enum TableType{
		INFO,
		DATA
	}
	
	//注册code
	private String code = null;
	//描述
	private String intro = null;
	//stats_app_secret_keys的id
	private String appId = null;	
	// columns
	private List<Column> columns = null;
	
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @return the intro
	 */
	public String getIntro() {
		return intro;
	}
	/**
	 * @param intro the intro to set
	 */
	public void setIntro(String intro) {
		this.intro = intro;
	}
	/**
	 * @return the appid
	 */
	public String getAppId() {
		return appId;
	}
	/**
	 * @param appid the appid to set
	 */
	public void setAppId(String appId) {
		this.appId = appId;
	}
	/**
	 * @return the columns
	 */
	public List<Column> getColumns() {
		return columns;
	}
	/**
	 * @param columns the columns to set
	 */
	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}
	/**
	 * table comment
	 * code+intro+info/data
	 * @return
	 */
	public String getTableComment(TableType type){
		StringBuilder comment = new StringBuilder();
		String postfix = (type == TableType.INFO) ? "info":"data";
		comment.append(this.code).append(this.intro).append(postfix);
		return comment.toString();
	}
	/**
	 * 获取表名
	 * @param type
	 * @param id
	 * @return
	 */
	private String getTableName(TableType type, int id){
		String platformName = DataKit.getPlatformName(id);
		if (type == TableType.INFO) {
			return DDLMakerKit.codeInfoTableName(this.getCode(), platformName);
		}
		return DDLMakerKit.codeDataTableName(null, this.code, platformName);
	}
	/**
	 * 获取表的 ddl
	 * @param type
	 * @param id
	 * @return
	 */
	public String ddl(TableType type, int id){
		String tableName = this.getTableName(type, id);
		Table table = DDLKit.createTable(tableName, this.getTableComment(type));
		if (type == TableType.INFO) {
			return table.ddl();
		}
		return table.addColumns(this.getColumns()).ddl();
	}
}
