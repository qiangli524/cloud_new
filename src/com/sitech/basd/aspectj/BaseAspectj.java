package com.sitech.basd.aspectj;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.sitech.basd.aspectj.handle.UnitedResourceHandle;
import com.sitech.basd.aspectj.vo.VirtualTypeEnum;
import com.sitech.basd.sxcloud.config.Constant;
import com.sitech.basd.util.session.UserSession;
import com.sitech.utils.date.DateUtil;
import com.sitech.utils.properties.PropertiesUtil;
import com.sitech.utils.xml.XmlProperties;
import com.sitech.vo.log.OperationLogVO;
import com.sitech.vo.util.UnitedConstant;

/**
 * 
 * <p>
 * Title: BaseAspectj
 * </p>
 * <p>
 * Description: 面向切面父类
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
 * @createtime 2013-8-29 上午8:40:17
 * 
 */
public class BaseAspectj {
	private static final Logger LOG = LoggerFactory.getLogger(UserSession.class);
	@Autowired
	public XmlProperties moduleNameMapXml;
	@Autowired
	public UnitedResourceHandle unitedResourceHandle;

	/**
	 * 
	 * @Title: initOperationLogVO
	 * @Description: 实例操作日志对象
	 * @param
	 * @return OperationLogVO
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-8-29 上午9:15:03
	 */
	public OperationLogVO initOperationLogVO(String operObj, String operType, String operSubType,
			String oper, int operResult, String operDesc, String modName) {
		OperationLogVO result = new OperationLogVO();
		// 获取当前系统登录Session
		HttpSession session = UserSession.getHttpSession();
		try {
			Object localHost = session.getAttribute(Constant.LOCAL_HOST);
			Object localPort = session.getAttribute(Constant.LOCAL_PORT);
			Object accout = session.getAttribute(Constant.ACCOUNT);
			Object requestHost = session.getAttribute(Constant.REQUEST_HOST);
			Object requestPort = session.getAttribute(Constant.REQUEST_PORT);
			result.setDestIp((localHost == null ? "" : localHost).toString());
			result.setDestPort((localPort == null ? "" : localPort).toString());
			result.setOperAcct((accout == null ? "" : accout).toString());
			// 暂写为账户名
			result.setOperObj((accout == null ? "" : accout).toString());
			result.setSessionId(session.getId());
			result.setSrcIp((requestHost == null ? "" : requestHost).toString());
			result.setSrcPort((requestPort == null ? "" : requestPort).toString());
		} catch (Exception e) {
			LOG.error("日志切面获取Session数据失败！");
		}
		result.setAppName(PropertiesUtil.getString("properties/cloud_host", "cloud_app_name"));
		result.setOperDesc(operDesc);
		result.setModName(modName);
		result.setOper(oper);
		result.setOperResult(operResult);
		result.setOperSubType(operSubType);
		result.setOperTime(DateUtil.getCurrentDateStr());
		result.setOperType(operType);
		return result;
	}

	/**
	 * 
	 * @Title: getVirtualType
	 * @Description: 根据vtype获取虚拟化类型
	 * @param
	 * @return VirtualTypeEnum
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-8-29 上午10:00:47
	 */
	public VirtualTypeEnum getVirtualType(String vtype) {
		if (UnitedConstant.VMWARE.equals(vtype)) {
			return VirtualTypeEnum.VMWARE;
		} else if (UnitedConstant.XEN.equals(vtype)) {
			return VirtualTypeEnum.XEN;
		}
		return VirtualTypeEnum.ERROR;
	}

	/**
	 * 
	 * @Title: getModuleName
	 * @Description: 获取模块名称
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-8-29 下午2:50:23
	 */
	public String getModuleName(String key) {
		Map<String, String> map = moduleNameMapXml.getMap();
		if (map != null) {
			return moduleNameMapXml.getMap().get(key);
		}
		return "获取菜单错误，请联系管理员！";
	}

}
