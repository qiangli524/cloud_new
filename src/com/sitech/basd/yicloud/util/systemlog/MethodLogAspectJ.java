package com.sitech.basd.yicloud.util.systemlog;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

import javax.annotation.Resource;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.sitech.basd.sxcloud.config.Constant;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysOperationLogObj;
import com.sitech.basd.sxcloud.rsmu.service.system.OperationService;

/**
 * 
 * <p>
 * Title: MethodLogAspectJ
 * </p>
 * <p>
 * Description:利用Spring AOP记录系统日志,暂时不能获取成功失败的信息
 * </p>
 * <p>
 * Company: si-tech
 * </p>
 * 
 * @author duangh
 * @date Jun 27, 2013
 */
@Component
@Aspect
public class MethodLogAspectJ {
	private static final Logger LOG = LoggerFactory.getLogger(MethodLogAspectJ.class);

	@Resource
	private OperationService operationService;

	@Pointcut("@annotation(com.sitech.basd.yicloud.util.systemlog.MethodLog)")
	public void methodPointcut() {
	}

	@Around("methodPointcut()")
	public Object methodCacheHold(ProceedingJoinPoint joinPoint) {
		Object result = null;
		try {
			TbSysOperationLogObj logObj = getLogObj(joinPoint);
			if (logObj != null) {
				operationService.insertByObj(logObj);
			}
		} catch (Exception e) {
			LOG.error("云平台记录操作日志失败！" + e.getMessage() + e, e);
		}
		// 保证调用的方法调用
		try {
			result = joinPoint.proceed();
		} catch (Throwable e) {
			LOG.error("记录日志后吗，调用方法异常！" + e.getMessage() + e, e);
		}
		return result;
	}

	// 获取方法的中文备注--用于记录用户的操作日志描述
	public static TbSysOperationLogObj getLogObj(ProceedingJoinPoint joinPoint) throws Exception {
		Object target = joinPoint.getTarget();
		Class targetClass = target.getClass();
		Field field = targetClass.getField("session");// 获取session,因为Action继承了BaseAction,BaseAction实现了Struts2的接口
		TbSysOperationLogObj operObj = new TbSysOperationLogObj();
		Object property = field.get(target);
		if (property instanceof Map) {
			Map session = (Map) property;
			int funcid = 0;
			// /** 增加新框架后无法获取funcsid，待修改 */
			Integer funcsid = null;
			try {
				funcsid = (Integer) session.get(Constant.FUNCID);
			} catch (Exception e) {
				LOG.error("记录操作日志异常！" + e.getMessage() + e, e);
				return null;
			}
			if (funcsid != null && !funcsid.equals("null") && !funcsid.equals("")) {
				funcid = funcsid;
			}
			String userName = (String) session.get("name");
			Assert.notNull(userName, "用户不能为空");
			Object loginId = session.get(Constant.LOGID_SESSION_KEY);
			operObj.setLOGINID(Integer.parseInt(loginId.toString()));
			operObj.setUSERID((Integer) session.get("id"));
			operObj.setRESULT(1);// 默认成功
			operObj.setFUNCID(funcid);
		}
		String methodName = joinPoint.getSignature().getName();
		Method[] method = targetClass.getMethods();

		for (Method m : method) {
			if (m.getName().equals(methodName)) {
				MethodLog methodCache = m.getAnnotation(MethodLog.class);// 获取注解
				operObj.setMESSAGE(methodCache.message());
				operObj.setREMARK(methodCache.remark());
				operObj.setOPERTYPE(methodCache.type());
				break;
			}
		}
		return operObj;
	}
}
