/**
 * 
 */
package cn.zhucongqi.tiger.validators;

import com.jfinal.core.Controller;
import com.jfinal.ext.validate.ValidatorExt;

/**
 * 校验移动客户端数据
 * @author BruceZCQ [zcq@zhucongqi.cn]
 * @version
 */
public class AppSecretKeysValidator extends ValidatorExt {

	protected void validate(Controller c) {
		this.validateRequiredString("uuid", "", "");
		this.validateRequiredString("appkey", "", "");
		this.validateRequiredString("bundle", "", "");
	}
}
