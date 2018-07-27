/**
 * 所有系统中用到的常量
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
