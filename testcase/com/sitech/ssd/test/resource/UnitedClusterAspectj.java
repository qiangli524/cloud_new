package com.sitech.ssd.test.resource;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;

import com.sitech.basd.resource.domain.united.UnitedTreeObj;
import com.sitech.vo.united.ClusterUnitedVO;

/**
 * 
 * <p>
 * Title: UnitedClusterAspectj
 * </p>
 * <p>
 * Description: 统一树集群切面
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author huojla
 * @version 1.0
 * @createtime 2013-8-27 下午1:58:22
 * 
 */
// @Aspect
public class UnitedClusterAspectj {
	/**
	 * 
	 * @Title: anyMethod
	 * @Description: 声明切入点
	 * @param
	 * @return void
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-8-27 下午7:15:28
	 */
	// @Pointcut("execution (* com.sitech.basd.aspectj.resource.TestAspectj.*(..))")
	@Pointcut("execution (* com.sitech.basd.aspectj.resource.TestAspectj.println(..))")
	private void anyMethod() {
	}

	/**
	 * 
	 * @Title: createCluster
	 * @Description: 创建集群切入点
	 * @param
	 * @return void
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-8-28 上午9:17:08
	 */
	// @Before("anyMethod() && args(obj,vo)")
	public void createCluster(UnitedTreeObj obj, ClusterUnitedVO vo) {

	}

	// @AfterReturning(pointcut = "anyMethod()", returning = "result")
	public void deleteCluster(String result) {
	}

	@Around("anyMethod()")
	public void around(ProceedingJoinPoint pjp) {
		Object[] objs = pjp.getArgs();
		try {
			Object result = pjp.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}
