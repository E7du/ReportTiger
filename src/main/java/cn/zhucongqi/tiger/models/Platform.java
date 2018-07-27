/**
 * 
 */
package cn.zhucongqi.tiger.models;

/**
 * 
 * @author BruceZCQ [zcq@zhucongqi.cn]
 * @version
 */
public class Platform {
	
	public static final int IOS_10 = 10;
	
	public static final String IOS = "_ios";
	
	public static final int ANDROID_20 = 20;
	
	public static final String ANDROID = "_android";
	
	/// 设备类型
	private int id;
	
	/// 设备名称
	private String name = null;
	
	public Platform(int id, String name){
		this.id = id;
		this.name = name;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
}
