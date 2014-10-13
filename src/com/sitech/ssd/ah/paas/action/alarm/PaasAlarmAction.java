package com.sitech.ssd.ah.paas.action.alarm;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.ssd.ah.paas.dao.alarm.PaasAlarmDao;
import com.sitech.ssd.ah.paas.domain.alarm.PaasAlarmObj;
import com.sitech.ssd.ah.paas.domain.entity.PaasEntityObj;
import com.sitech.ssd.ah.paas.domain.tree.PaasTreeObj;
import com.sitech.ssd.ah.paas.service.entity.PaasEntityService;
import com.sitech.ssd.ah.paas.service.tree.PaasTreeService;
import com.sitech.ssd.ah.paas.util.PaasConstant;

@Controller("paasAlarmAction")
@Scope("prototype")
public class PaasAlarmAction extends BaseAction {

	@Autowired
	private PaasAlarmDao paasAlarmDao;
	@Autowired
	private PaasEntityService paasEntityService;
	@Autowired
	private PaasTreeService paasTreeService;
	private PaasAlarmObj obj;
	private List resultList;
	private String id;
	private String node_type;

	public PaasAlarmObj getObj() {
		return obj;
	}

	public void setObj(PaasAlarmObj obj) {
		this.obj = obj;
	}

	public List getResultList() {
		return resultList;
	}

	public void setResultList(List resultList) {
		this.resultList = resultList;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNode_type() {
		return node_type;
	}

	public void setNode_type(String node_type) {
		this.node_type = node_type;
	}

	/**
	 * 
	 * @Title: listPaasAlarm
	 * @Description: paas资源池的告警情况
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2014-7-22 上午11:08:48
	 */
	public String listPaasAlarm() throws Exception {
		if (obj == null) {
			obj = new PaasAlarmObj();
		}
		if (resultList == null) {
			resultList = new ArrayList();
		}
		obj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));// 分页
		List<String> entityIdList = queryEntity_id(id, node_type);
		List alarmList = new ArrayList();
		for (String entity_id : entityIdList) {
			obj.setEntity_id(entity_id);
			alarmList = paasAlarmDao.queryForAlarmList(obj);
			resultList.addAll(alarmList);
		}
		return "listPaasAlarm";
	}

	/**
	 * 
	 * @Title: queryEntity_id
	 * @Description: 查询Entity_id用于告警
	 * @param
	 * @return List<String>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-7-22 下午5:07:08
	 */
	private List<String> queryEntity_id(String id, String node_type) throws Exception {
		List<String> entityIdList = new ArrayList<String>();
		List<PaasAlarmObj> alarmList_all = new ArrayList<PaasAlarmObj>();
		if (PaasConstant.DAAS.equals(node_type) || PaasConstant.DAAS_TYPE.equals(node_type)) {
			PaasEntityObj entityTypeObj = new PaasEntityObj();
			entityTypeObj.setEntity_type(PaasConstant.ORACLE_INSTANCE);
			List<PaasEntityObj> instanceList = paasEntityService.queryForEntityList(entityTypeObj);// 查询所有实例集合
			for (PaasEntityObj entObj : instanceList) {
				entityIdList.add(entObj.getEntity_id());
			}
		} else if (PaasConstant.DAAS_BUSI.equals(node_type)) {
			PaasTreeObj treeObj = new PaasTreeObj();
			treeObj.setId(id);
			treeObj.setNode_type(PaasConstant.DAAS_BUSI);
			List<PaasTreeObj> instanceList = paasTreeService.acquireListForType(
					PaasConstant.DAAS_DB_ENTITY, PaasConstant.ORACLE_INSTANCE, treeObj,
					new ArrayList<PaasTreeObj>());// 查询所有实例集合
			for (PaasTreeObj entObj : instanceList) {
				entityIdList.add(entObj.getEntity_id());
			}
		}
		return entityIdList;
	}
}
