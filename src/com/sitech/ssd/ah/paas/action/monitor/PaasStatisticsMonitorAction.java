package com.sitech.ssd.ah.paas.action.monitor;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.ssd.ah.paas.domain.monitor.PaasResourceMonitorObj;
import com.sitech.ssd.ah.paas.service.monitor.PaasStatisticsMonitorService;

@Controller("paasStatisticsMonitorAction")
@Scope("prototype")
public class PaasStatisticsMonitorAction extends BaseAction {

	@Autowired
	private PaasStatisticsMonitorService paasStatisticsMonitorService;
	
	public Map<String,Object> map;
	
	public List<PaasResourceMonitorObj> threadInfoLst;
	public List<PaasResourceMonitorObj> connInfoLst;
	public List<PaasResourceMonitorObj> reqInfoLst;
	public List<PaasResourceMonitorObj> totalByteInfoInfoLst;
	public List<PaasResourceMonitorObj> storeInfoLst;
	public List<PaasResourceMonitorObj> objInfoLst;
	
	public JSONObject threadInfoJo;
	public JSONObject connInfoJo;
	public JSONObject reqInfoJo;
	public JSONObject totalByteInfoJo;
	public JSONObject storeInfoJo;
	public JSONObject objInfoJo;
	
	private String entity_id;// 实体Id

	public String getEntity_id() {
		return entity_id;
	}

	public void setEntity_id(String entity_id) {
		this.entity_id = entity_id;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	@SuppressWarnings("unchecked")
	public String goStatisticsMonitorPage(){
		map = paasStatisticsMonitorService.getAllStatisticsMonitorData(entity_id);
		
		threadInfoLst = (List<PaasResourceMonitorObj>)map.get("threadInfo");
		connInfoLst= (List<PaasResourceMonitorObj>)map.get("connInfo");
		reqInfoLst= (List<PaasResourceMonitorObj>)map.get("reqInfo");
		totalByteInfoInfoLst= (List<PaasResourceMonitorObj>)map.get("totalByteInfo");
		storeInfoLst= (List<PaasResourceMonitorObj>)map.get("storeInfo");
		objInfoLst= (List<PaasResourceMonitorObj>)map.get("objInfo");
		
		threadInfoJo = JSONObject.fromObject(map.get("threadInfoJSON"));
		connInfoJo= JSONObject.fromObject(map.get("connInfoJSON"));
		reqInfoJo= JSONObject.fromObject(map.get("reqInfoJSON"));
		totalByteInfoJo= JSONObject.fromObject(map.get("totalByteInfoJSON"));
		storeInfoJo= JSONObject.fromObject(map.get("storeInfoJSON"));
		objInfoJo= JSONObject.fromObject(map.get("objInfoJSON"));
		
		request.setAttribute("threadInfoLst", JSONArray.fromObject(threadInfoLst));
		request.setAttribute("connInfoLst", JSONArray.fromObject(connInfoLst));
		request.setAttribute("reqInfoLst", JSONArray.fromObject(reqInfoLst));
		request.setAttribute("totalByteInfoInfoLst", JSONArray.fromObject(totalByteInfoInfoLst));
		request.setAttribute("storeInfoLst", JSONArray.fromObject(storeInfoLst));
		request.setAttribute("objInfoLst", JSONArray.fromObject(objInfoLst));
		
		request.setAttribute("threadInfoJo", threadInfoJo);
		request.setAttribute("connInfoJo", connInfoJo);
		request.setAttribute("reqInfoJo", reqInfoJo);
		request.setAttribute("totalByteInfoJo", totalByteInfoJo);
		request.setAttribute("storeInfoJo", storeInfoJo);
		request.setAttribute("objInfoJo", objInfoJo);
			
		return "go";
	}
	
	public String getJson(){
		map = paasStatisticsMonitorService.getAllStatisticsMonitorData(entity_id);
//		threadInfoJo = (String)map.get("threadInfoJSON");
//		JSONUtil.printJSON(JsonthreadInfoJo);
		return null;
	}
	
}
