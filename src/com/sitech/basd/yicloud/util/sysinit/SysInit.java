package com.sitech.basd.yicloud.util.sysinit;

import java.util.List;

import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.sitech.basd.sxcloud.cloud.domain.globalconfig.TbGlobalConfigObj;
import com.sitech.basd.sxcloud.cloud.service.globalconfig.TbGlobalConfigService;
import com.sitech.basd.yicloud.domain.entitytree.EntityTreeObj;
import com.sitech.basd.yicloud.service.entitytree.EntityTreeService;
import com.sitech.basd.yicloud.util.CfgUtil;
import com.sitech.basd.yicloud.util.InvokeCfgUtil;

/**
 * 
 * <p>
 * Title: SysInit
 * </p>
 * <p>
 * Description: 系统启动注册类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author huojla
 * @version 1.0
 * @createtime Sep 18, 2012 9:18:30 AM
 * 
 */
public class SysInit extends HttpServlet {
	private static int COMPATER_TIME = 10;
	private static final Logger logger = Logger.getLogger(SysInit.class);

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 536327111277978937L;
	private ApplicationContext applicationContext;

	public SysInit() {
		super();
	}

	/**
	 * Init初始化加载方法和功能处理
	 */
	public void init() {
		// 获取初始化参数init-param
		@SuppressWarnings("unused")
		String initParmValue = getInitParameter("sys-init-param");
		try {
			COMPATER_TIME = CfgUtil.getInt("vcenter.compare.time");
			if (COMPATER_TIME == 0) {
				COMPATER_TIME = 10;
			}
		} catch (Exception e) {
			logger.error("数据比对时间错误：" + e.getMessage());
		}
		logger.info("COMPATER_TIME-------------------" + COMPATER_TIME);
		// 实例Spring会话
		applicationContext = WebApplicationContextUtils
				.getWebApplicationContext(this.getServletContext());
		// 启动数据比对线程
		startDataCompareThread();
	}

	/**
	 * 
	 * @Title: startDataCompareThread
	 * @Description: 启动数据比对线程
	 * @param
	 * @return void
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Nov 14, 2012 10:40:11 AM
	 */
	private void startDataCompareThread() {
		try {
			Runnable runn = new Runnable() {
				public void run() {
					while (!Thread.interrupted()) {
						logger.info("进入数据比对线程");
						/** 判断数据是否实例，需修改 */
						if (isOpenCompare()) {
							logger.info("数据比对开始！");
							InvokeCfgUtil.invoke("/datacom_get.do");
						}
						try {
							Thread.sleep(COMPATER_TIME * 60 * 1000);
						} catch (InterruptedException e) {
							logger.error("数据比对线程异常---" + e.getMessage());
						}
					}
				}
			};
			new Thread(runn).start();
		} catch (Exception e) {
			logger.error("数据比对线程异常---" + e.getMessage());
		}
	}

	/**
	 * 
	 * @Title: vmwareDataIsInit
	 * @Description: 判断vmware数据是否已经实例
	 * @param
	 * @return boolean
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Nov 14, 2012 11:06:23 AM
	 */
	public boolean vmwareDataIsInit() {
		EntityTreeService entityTreeService = (EntityTreeService) applicationContext
				.getBean("entityTreeService");
		EntityTreeObj obj = new EntityTreeObj();
		List<EntityTreeObj> list = entityTreeService.queryForTree(obj);
		if (list != null && list.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 
	 * @Title: isOpenCompare
	 * @Description: 是否开启数据比对
	 * @param
	 * @return boolean
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Nov 15, 2012 2:13:32 PM
	 */
	public boolean isOpenCompare() {
		TbGlobalConfigService tbGlobalConfigService = (TbGlobalConfigService) applicationContext
				.getBean("tbGlobalConfigService");
		TbGlobalConfigObj obj = new TbGlobalConfigObj();
		obj.setKEY("ISCOMPARE");
		obj = tbGlobalConfigService.queryByObj(obj);
		if (obj != null) {
			String value = obj.getVALUE();
			if ("1".equals(value)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}
