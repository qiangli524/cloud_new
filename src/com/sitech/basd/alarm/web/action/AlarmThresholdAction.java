package com.sitech.basd.alarm.web.action;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sitech.basd.alarm.domain.AlarmThreshold;
import com.sitech.basd.alarm.service.AlarmThresholdService;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.basd.sxcloud.rsmu.web.util.exception.BaseException;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.utils.servlet.PrintWriterOut;

@Controller("alarmThresholdAction")
@Scope("prototype")
@SuppressWarnings("all")
public class AlarmThresholdAction extends BaseAction {
	@Autowired
	private AlarmThresholdService alarmThresholdService;
	private AlarmThreshold obj;
	private List resultList;

	public AlarmThreshold getObj() {
		return obj;
	}

	public void setObj(AlarmThreshold obj) {
		this.obj = obj;
	}

	public List getResultList() {
		return resultList;
	}

	public void setResultList(List resultList) {
		this.resultList = resultList;
	}
	/**
	 * @Title:查询出全部信息
	 * @Copyright: Copyright (c) 201308
	 * @Company: si-tech
	 * @author yanggl_bj
	 * @version 1.0
	 */
	public String listAlarmThreshold() throws BaseException {
		if(obj == null){
			obj = new AlarmThreshold();
		}
		obj.setPagination(this.getPaginater().initPagination(request));
		resultList = alarmThresholdService.queryForListByObj(obj);
		return "list";
	}

	/**
	 * @Title:显示修改页面
	 * @Copyright: Copyright (c) 201308
	 * @Company: si-tech
	 * @author yanggl_bj
	 * @version 1.0
	 */
	public String editAlarmThreshold() throws BaseException {
		List<AlarmThreshold> list = alarmThresholdService.queryAlarmThresholdById(obj);
		if (list != null && list.size() > 0) {
			obj = list.get(0);
		}
		return "edit";
	}

	/**
	 * @Title:保存信息
	 * @Copyright: Copyright (c) 201308
	 * @Company: si-tech
	 * @author yanggl_bj
	 * @version 1.0
	 */
	public void saveAlarmThreshold() throws BaseException {
		if (obj.getId() == null || "".equals(obj.getId())) {
			//obj.setAlarm_threshold(Integer.parseInt((obj.getAlarm_threshold()+"").trim()));
			alarmThresholdService.addAlarmThresholdByObj(obj);
		} else {
			alarmThresholdService.updateAlarmThresholdById(obj);
		}
		//return "save";
	}

	/**
	 * @Title: validateForm
	 * @Description: 验证表单关键字是否唯一
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl_bj
	 * @version 1.0
	 * @createtime 20130809
	 */
	public void validateNameEn() {
		String result="";
		if(obj == null){
			obj = new AlarmThreshold();
		}
		if(obj.getAlarm_kpi() != ""){
			Integer a = alarmThresholdService.validateNameByEdit(obj);
			if(a != 0 && a != null){
				result="该指标名已存在！";
			}
		}
		JSONObject jo = new JSONObject();
		jo.put("result", result);
		this.printToScreen(jo.toString());
	}
	/**
	 * @Title: validateNameZh
	 * @Description: 判断指标中文名是否重复
	 * @param
	 * @return void
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-6-23 下午2:31:24
	 */
	public void validateNameZh() {
		String result="";
		if(obj == null){
			obj = new AlarmThreshold();
		}
		if(obj.getAlarm_name() != ""){
			Integer a = alarmThresholdService.validateNameByEdit(obj);
			if(a != 0 && a != null){
				result="该指标名已存在！";
			}
		}
		JSONObject jo = new JSONObject();
		jo.put("result", result);
		this.printToScreen(jo.toString());
	}

	/**
	 * @Title:删除
	 * @Copyright: Copyright (c) 201308
	 * @Company: si-tech
	 * @author yanggl_bj
	 * @version 1.0
	 */
	public void delete() {
		alarmThresholdService.deleteAlarmThreshold(obj);
		//return "delete";
	}
	/**
	 * @Title: printToScreen
	 * @Description: 将内容打回屏幕，即写到响应里
	 * @param
	 * @return void
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-6-20  下午4:50:47
	 */
	private void printToScreen(String value) {
		try {
			response.setCharacterEncoding("UTF-8");
			PrintWriterOut.printWirter(response, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
