package com.sitech.basd.yicloud.util.systemlog;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * <p>
 * Title: MethodLog
 * </p>
 * <p>
 * Description:自定义注解，用来记录系统日志
 * </p>
 * <p>
 * Company: si-tech
 * </p>
 * 
 * @author duangh
 * @date Jun 27, 2013
 */

@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MethodLog {
	String message() default "";

	// 1：增加，2：删除，3：修改，4查询
	int type();

	String remark() default "";

}
