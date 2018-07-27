/**
 * 
 */
package cn.zhucongqi.tiger.validators;

import com.jfinal.core.Controller;
import com.jfinal.ext.validate.ValidatorExt;

/**
 * 校验pc端表结构创建
 * @author BruceZCQ [zcq@zhucongqi.cn]
 * @version
 */
public class TigerTableMakerValidator extends ValidatorExt {

	protected void validate(Controller c) {
		this.validateRequiredString("app", "apperr", "先选择 app");
		this.validateRequiredString("code", "codeerr", "请输入数据助记码");
		this.validateRequiredString("codecomment", "codecommenterr", "请输入数据助记码描述");
		this.validateRequiredString("fields", "fieldserr", "请输入数据字段描述");
	}
}
