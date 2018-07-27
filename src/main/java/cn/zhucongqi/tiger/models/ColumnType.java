/**
 * 
 */
package cn.zhucongqi.tiger.models;

/**
 * 
 * @author BruceZCQ [zcq@zhucongqi.cn]
 * @version
 */
public class ColumnType {

	public static final int INT_ID_1 = 1;
	public static final int VARCHAR_ID_2 = 2;
	public static final int TEXT_ID_3 = 3;
	public static final int BIGINT_ID_4 = 4;
	public static final int TINYINT_ID_5 = 5;
	
	private int id;
	
	private String name;
	
	public ColumnType(int id, String name){
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
