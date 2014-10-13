/**   
o * Copyright: Copyright (c) 2014
 * Company: SI-TECH
 *
 * @Title: PhysicalVlanAction.java 
 * @Package action.vlan 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author wanglei_bj@si-tech.com.cn 
 * @date 2014-4-23 下午3:38:23 
 * @version V1.0   
 */
package com.sitech.shop.action.vlan;

import java.sql.Timestamp;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.basd.syslog.SysLogConstant;
import com.sitech.basd.syslog.service.TbUserOperationLogService;
import com.sitech.shop.domain.vlan.PhysicalVlanObj;
import com.sitech.shop.service.PhysicalVlanService;
import com.sitech.utils.exception.HttpClientException;
import com.sitech.utils.http.HttpClientUtil;
import com.sitech.utils.properties.PropertiesUtil;
import com.sitech.utils.randomid.RandomUUID;
import com.sitech.utils.servlet.PrintWriterOut;
import com.sitech.vo.util.UnitedConstant;

/**
 * @ClassName: PhysicalVlanAction
 * @Description: TODO(物理Vlan Action)
 * @author wanglei_bj@si-tech.com.cn
 * @date 2014-4-23 下午3:38:23
 * @version 1.0
 */
@Component("physicalVlanAction")
@Scope("prototype")
public class PhysicalVlanAction extends BaseAction {

	private static final long serialVersionUID = 5581132994372989514L;
	private static final Logger log = LoggerFactory.getLogger(PhysicalVlanAction.class);
	@Autowired
	private TbUserOperationLogService logger;
	@Autowired
	private PhysicalVlanService physicalVlanService;

	private PhysicalVlanObj obj = new PhysicalVlanObj();
	private List<?> resultList;

	private String areaId;
	private String result = "error";
	private String type;// 用户菜单的动态切换样式

	// 用户日志相关
	private Integer operRet = SysLogConstant.OPERATION_SUCCEED;// 操作结果
	private String oper = "";// 操作类型
	private String operationObject = "";// 操作对象
	private Timestamp startTime = null;// 操作起始时间

	/**
	 * 
	 * @Title: listPhysicalVlan
	 * @Description: TODO(运维门户-物理Vlan列表)
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author wanglei_bj@si-tech.com.cn
	 * @throws
	 */
	public String listPhysicalVlan() {
		// obj.setArea_id(session.get("areaid").toString());
		// obj.setUser_id(session.get("id").toString());
		obj.setPagination(this.getPaginater().initPagination(request));
		try {
			setResultList(physicalVlanService.getPhysicalVlanList(obj));
		} catch (Exception e) {
			log.error("获取物理Vlan列表失败");
			e.printStackTrace();
		}
		return "list";
	}

	/**
	 * 
	 * @Title: addPhysicalVlanPage
	 * @Description: TODO(添加Vlan页面)
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author wanglei_bj@si-tech.com.cn
	 * @throws
	 */
	public String addPhysicalVlanPage() {
		obj = new PhysicalVlanObj();
		return "add";

	}

	/**
	 * 
	 * @Title: editPhysicalVlan
	 * @Description: TODO(修改Vlan页面)
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author wanglei_bj@si-tech.com.cn
	 * @throws
	 */
	public String editPhysicalVlan() {
		obj = physicalVlanService.getPhysicalVlan(obj);
		return "edit";
	}

	/**
	 * 
	 * @Title: renamePhysicalVlan
	 * @Description: TODO(控制台：用户重命名Vlan)
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author wanglei_bj@si-tech.com.cn
	 * @throws
	 */
	public String renamePhysicalVlan() {
		obj = physicalVlanService.getPhysicalVlan(obj);
		return "rename";
	}

	/**
	 * 
	 * @Title: savePhysicalVlan
	 * @Description: TODO(保存Vlan)
	 * @param 设定文件
	 * @return void 返回类型
	 * @author wanglei_bj@si-tech.com.cn
	 * @throws
	 */
	public void savePhysicalVlan() {
		JSONObject json = new JSONObject();
		try {
			obj.setId(RandomUUID.getUuid());
			obj.setIsused("0");
			result = physicalVlanService.createPhysicalVlan(obj);
			if (result.equals(UnitedConstant.SUCCESS)) {
				json.put("result", result);
			} else {
				json.put("result", "error");
			}
		} catch (Exception e) {
			log.error("创建物理vlan失败");
			json.put("result", "error");
		}
		returnJson(json.toString());
	}

	/**
	 * 
	 * @Title: updatePhysicalVlan
	 * @Description: TODO(更新Vlan)
	 * @param 设定文件
	 * @return void 返回类型
	 * @author wanglei_bj@si-tech.com.cn
	 * @throws
	 */
	public void updatePhysicalVlan() {
		// 记录执行开始时间
		startTime = new Timestamp(System.currentTimeMillis());
		oper = SysLogConstant.MODIFY_VLAN;

		JSONObject json = new JSONObject();
		try {
			if (obj.getId() != null && !"".equals(obj.getId())) {
				result = physicalVlanService.updatePhysicalVlan(obj);
				operationObject = obj.getId();
			}
			if (result.equals(UnitedConstant.SUCCESS)) {
				json.put("result", result);
			} else {
				json.put("result", "error");
				operRet = SysLogConstant.OPERATION_ERROR;
			}
			logger.insertUserOperationLog(oper, operRet, operationObject, oper, startTime, session,
					PhysicalVlanAction.class);
		} catch (Exception e) {
			log.error("更新物理vlan失败");
			json.put("result", "error");
			operRet = SysLogConstant.OPERATION_ERROR;
			logger.insertUserOperationLog(oper, operRet, operationObject, oper, startTime, session,
					PhysicalVlanAction.class);
		}
		returnJson(json.toString());
	}

	/**
	 * 
	 * @Title: deletePhysicalVlan
	 * @Description: TODO(删除物理vlan)
	 * @param 设定文件
	 * @return void 返回类型
	 * @author wanglei_bj@si-tech.com.cn
	 * @throws
	 */
	public void deletePhysicalVlan() {
		// 记录执行开始时间
		startTime = new Timestamp(System.currentTimeMillis());
		oper = SysLogConstant.DELETE_VLAN;

		JSONObject json = new JSONObject();
		try {
			if (obj.getId() != null && !"".equals(obj.getId())) {
				operationObject = physicalVlanService.getPhysicalVlan(obj).getName();
				result = physicalVlanService.deletePhysicalVlan(obj);
			}
			if (result.equals(UnitedConstant.SUCCESS)) {
				json.put("result", result);
			} else {
				json.put("result", "error");
				operRet = SysLogConstant.OPERATION_ERROR;
			}
			logger.insertUserOperationLog(oper, operRet, operationObject, oper, startTime, session,
					PhysicalVlanAction.class);

		} catch (Exception e) {
			log.error("删除物理vlan失败");
			json.put("result", "error");
			operRet = SysLogConstant.OPERATION_ERROR;
			logger.insertUserOperationLog(oper, operRet, operationObject, oper, startTime, session,
					PhysicalVlanAction.class);

		}

		returnJson(json.toString());
	}

	/**
	 * 
	 * @Title: collectPhysicalVlan
	 * @Description: TODO(回收物理Vlan)
	 * @param 设定文件
	 * @return void 返回类型
	 * @author wanglei_bj@si-tech.com.cn
	 * @throws
	 */
	public void collectPhysicalVlan() {
		JSONObject json = new JSONObject();
		try {
			if (obj.getId() != null && !"".equals(obj.getId())) {
				result = physicalVlanService.collectPhysicalVlan(obj);
			}
			json.put("result", result);
		} catch (Exception e) {
			log.error("回收物理vlan失败");
			json.put("result", "error");
		}

		returnJson(json.toString());
	}

	/**
	 * 
	 * @Title: applyPhysicalVlanPage
	 * @Description: TODO(用户申请Vlan页面)
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author wanglei_bj@si-tech.com.cn
	 * @throws
	 */
	public String applyPhysicalVlanPage() {
		return "apply";
	}

	/**
	 * 
	 * @Title: applyPhysicalVlan
	 * @Description: TODO(用户申请Vlan，更新用户ID)
	 * @param 设定文件
	 * @return void 返回类型
	 * @author wanglei_bj@si-tech.com.cn
	 * @throws
	 */
	public void applyPhysicalVlan() {
		// 记录执行开始时间
		Timestamp startTime = new Timestamp(System.currentTimeMillis());
		operationObject = obj.getName();

		JSONObject json = new JSONObject();
		obj.setUser_id(session.get("id").toString());
		obj.setArea_id(session.get("areaid").toString());

		try {
			result = physicalVlanService.updatePhysicalVlan(obj);
			if (result.equals(UnitedConstant.SUCCESS)) {
				json.put("result", result);
			} else {
				json.put("result", "error");
				operRet = SysLogConstant.OPERATION_ERROR;
			}
			logger.insertUserOperationLog(oper, operRet, operationObject, oper, startTime, session,
					PhysicalVlanAction.class);
		} catch (Exception e) {
			log.error("申请vlan失败");
			json.put("result", "error");
			operRet = SysLogConstant.OPERATION_ERROR;
			logger.insertUserOperationLog(oper, operRet, operationObject, oper, startTime, session,
					PhysicalVlanAction.class);
		}
		returnJson(json.toString());
	}

	/**
	 * 
	 * @Title: getARandomVlan
	 * @Description: TODO(获得一个随机的未使用的Vlan,要提供 提供地域ID)
	 * @param 设定文件
	 * @return void 返回类型
	 * @author wanglei_bj@si-tech.com.cn
	 * @throws
	 */
	public void getARandomVlan() {
		JSONObject json = new JSONObject();
		obj = new PhysicalVlanObj();
		try {
			// 地区 和 单点登录以后 修改下面内容
			obj.setUser_id(session.get("id").toString());
			obj.setArea_id(session.get("areaid").toString());
			obj.setIsused("0");
			obj = physicalVlanService.getARandomVlan(obj);
			if (null != obj) {
				json.put("result", "success");
				json.put("vlan", obj);
			} else {
				json.put("result", "error");
			}
		} catch (Exception e) {
			log.error("获得随机vlan失败");
			json.put("result", "error");
		}
		returnJson(json.toString());
	}

	/**
	 * 
	 * @Title: listPhysicalVlanForCustomer
	 * @Description: TODO(控制台Vlan列表)
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author wanglei_bj@si-tech.com.cn
	 * @throws
	 */
	public String listPhysicalVlanForCustomer() {
		obj.setPagination(this.getPaginater().initPagination(request));

		try {
			obj.setUser_id(session.get("id").toString());
			obj.setArea_id(session.get("areaid").toString());
			setResultList(physicalVlanService.getPhysicalVlanList(obj));
		} catch (Exception e) {
			log.error("获取物理Vlan列表失败");
			e.printStackTrace();
		}
		return "list_for_customer";
	}

	/**
	 * 
	 * @Title: ajaxGetVlanListForCustomer
	 * @Description: TODO(异步查询vlan列表)
	 * @param 设定文件
	 * @return void 返回类型
	 * @author wanglei_bj@si-tech.com.cn
	 * @throws
	 */
	public void ajaxGetVlanListForCustomer() {
		JSONArray jsonArray = new JSONArray();
		obj.setUser_id(session.get("id").toString());
		obj.setArea_id(session.get("areaid").toString());
		resultList = physicalVlanService.getPhysicalVlanList(obj);
		jsonArray = JSONArray.fromObject(resultList);
		returnJson(jsonArray.toString());
	}


	/**
	 * 
	 * @Title: expandBandwidthPage
	 * @Description: 进入带宽升级页面（带宽大小）
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @throws HttpClientException
	 * @createtime 2014-7-1 下午3:12:09
	 */
	public String expandBandwidthPage() throws HttpClientException {
		String url = "";
		obj = physicalVlanService.getPhysicalVlanObj(obj);
		url = PropertiesUtil.getString("properties/public_cloud", "bandwidth_expand");
		String maintenance_platform_url = PropertiesUtil.getString("properties/public_cloud",
				"maintenance_platform_url");
		// 调用黎明的方法查询带宽对应的订购关系ID
		String relationsStr = HttpClientUtil.get(maintenance_platform_url + "getVlanId/"
				+ obj.getVlan_id());
		url = url + relationsStr;
		PrintWriterOut.printWirter(response, url);
		return null;
	}

	/**
	 * 
	 * @Title: delayBandwidthPage
	 * @Description: 进入带宽续订页面（时间）
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @throws HttpClientException
	 * @createtime 2014-7-1 下午3:17:03
	 */
	public String delayBandwidthPage() throws HttpClientException {
		String url = "";
		obj = physicalVlanService.getPhysicalVlanObj(obj);
		url = PropertiesUtil.getString("properties/public_cloud", "bandwidth_delay");
		String maintenance_platform_url = PropertiesUtil.getString("properties/public_cloud",
				"maintenance_platform_url");
		// 调用黎明的方法查询带宽对应的订购关系ID
		String relationsStr = HttpClientUtil.get(maintenance_platform_url + "getVlanId/"
				+ obj.getVlan_id());
		url = url + relationsStr;
		PrintWriterOut.printWirter(response, url);
		return null;
	}

	public PhysicalVlanObj getObj() {
		return obj;
	}

	public void setObj(PhysicalVlanObj obj) {
		this.obj = obj;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	/**
	 * @return resultList
	 */
	public List<?> getResultList() {
		return resultList;
	}

	/**
	 * @param resultList
	 *            the resultList to set
	 */
	public void setResultList(List<?> resultList) {
		this.resultList = resultList;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
