package com.sitech.ssd.ah.busiMon.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.basd.sxcloud.rsmu.web.util.exception.BaseException;
import com.sitech.ssd.ah.busiMon.domain.TbBossBusiMonAlarmCfg;
import com.sitech.ssd.ah.busiMon.service.BossBusiMonAlarmCfgService;
import com.sitech.ssd.ah.busiMonitor.domain.MonitorCfgObj;
import com.sitech.utils.monitor.bossBusiMon.BossBusiMonConstant;
import com.sitech.utils.monitor.bossBusiMon.ExpressionUtil;

/**
 * <p>Title:BossBusiMonAlarmCfgAction</p>
 * <p>Description:boss业务监控报警配置action</p>
 * <p>Copyright:Copyright (c) 2014</p>
 * <p>Company:SI-TECH </p>
 * @author wangjl_cmi_jl
 * @version 1.0
 * @createtime Sep 12, 2014 10:11:00 AM
 */
@Controller("bossBusiMonAlarmCfgAction")
@Scope("prototype")
public class BossBusiMonAlarmCfgAction extends BaseAction {
	/**
	 * 日志记录器
	 */
	private static Logger logger = Logger.getLogger(BossBusiMonAlarmCfgAction.class);
	
	/**
	 * boss业务监控报警配置服务类
	 */
	@Autowired
	private BossBusiMonAlarmCfgService bossBusiMonAlarmCfgService;
	
	/**
	 * boss业务监控报警配置实体
	 */
	private TbBossBusiMonAlarmCfg tbBossBusiMonAlarmCfg;
	
	/**
	 * 路径list
	 */
	private List<MonitorCfgObj> pathList;
	
	/**
	 * 主机IP
	 */
	private String hostIp;
	
	/**
	 * 结果List
	 */
	private List resultList;

	public List getResultList() {
		return resultList;
	}

	public void setResultList(List resultList) {
		this.resultList = resultList;
	}
	
	public TbBossBusiMonAlarmCfg getTbBossBusiMonAlarmCfg() {
		return tbBossBusiMonAlarmCfg;
	}

	public void setTbBossBusiMonAlarmCfg(TbBossBusiMonAlarmCfg tbBossBusiMonAlarmCfg) {
		this.tbBossBusiMonAlarmCfg = tbBossBusiMonAlarmCfg;
	}

	public List<MonitorCfgObj> getPathList() {
		return pathList;
	}

	public void setPathList(List<MonitorCfgObj> pathList) {
		this.pathList = pathList;
	}

	public String getHostIp() {
		return hostIp;
	}

	public void setHostIp(String hostIp) {
		this.hostIp = hostIp;
	}

	/**
	 * @Title:listBossBusiMonAlarmCfg
	 * @Description:查询boss业务监控报警配置list的方法
	 * @return String
	 * @throws BaseException
	 * @author wangjl_cmi_jl
	 * @version 1.0
	 * @createtime Sep 12, 2014 10:12:29 AM
	 */
	public String listBossBusiMonAlarmCfg() throws BaseException {
		if(tbBossBusiMonAlarmCfg == null){
			tbBossBusiMonAlarmCfg = new TbBossBusiMonAlarmCfg();
		}
		tbBossBusiMonAlarmCfg.setPagination(this.getPaginater().initPagination(request));
		resultList = bossBusiMonAlarmCfgService.queryForListByObj(tbBossBusiMonAlarmCfg);
		return "list";
	}

	/**
	 * @Title:editBossBusiMonAlarmCfg
	 * @Description:编辑boss业务监控报警配置的方法
	 * @return String
	 * @throws BaseException
	 * @author wangjl_cmi_jl
	 * @version 1.0
	 * @createtime Sep 12, 2014 10:13:11 AM
	 */
	public String editBossBusiMonAlarmCfg() throws BaseException {
		List<TbBossBusiMonAlarmCfg> list = bossBusiMonAlarmCfgService.queryBossBusiMonAlarmCfgById(tbBossBusiMonAlarmCfg);
		if (list != null && list.size() > 0) {
			tbBossBusiMonAlarmCfg = list.get(0);
		}
		return "edit";
	}
	
	/**
	 * @Title:saveBossBusiMonAlarmCfg
	 * @Description:保存或更新boss业务监控报警配置的方法
	 * @throws BaseException
	 * @author wangjl_cmi_jl
	 * @version 1.0
	 * @createtime Sep 12, 2014 10:13:46 AM
	 */
	public void saveBossBusiMonAlarmCfg() throws BaseException {
		
		saveBeforeHandle();
		
		if (tbBossBusiMonAlarmCfg.getID() == null || "".equals(tbBossBusiMonAlarmCfg.getID())) {
			bossBusiMonAlarmCfgService.addBossBusiMonAlarmCfg(tbBossBusiMonAlarmCfg);
		} else {
			bossBusiMonAlarmCfgService.updateBossBusiMonAlarmCfgById(tbBossBusiMonAlarmCfg);
		}
	}

	/**
	 * @Title:saveBeforeHandle
	 * @Description:保存前处理的方法
	 * @author wangjl_cmi_jl
	 * @version 1.0
	 * @createtime Sep 13, 2014 11:29:17 AM
	 */
	private void saveBeforeHandle() {
		String serious_alarm_expression = tbBossBusiMonAlarmCfg.getSERIOUS_ALARM_EXPRESSION();
		String serious_alarm_info_expression = tbBossBusiMonAlarmCfg.getSERIOUS_ALARM_INFO_EXPRESSION();
		String common_alarm_expression = tbBossBusiMonAlarmCfg.getCOMMON_ALARM_EXPRESSION();
		String common_alarm_info_expression = tbBossBusiMonAlarmCfg.getCOMMON_ALARM_INFO_EXPRESSION();
		serious_alarm_expression = expressionHandle(serious_alarm_expression);
		serious_alarm_info_expression = expressionHandle(serious_alarm_info_expression);
		common_alarm_expression = expressionHandle(common_alarm_expression);
		common_alarm_info_expression = expressionHandle(common_alarm_info_expression);
		
		tbBossBusiMonAlarmCfg.setSERIOUS_ALARM_EXPRESSION(serious_alarm_expression);
		tbBossBusiMonAlarmCfg.setSERIOUS_ALARM_INFO_EXPRESSION(serious_alarm_info_expression);
		tbBossBusiMonAlarmCfg.setCOMMON_ALARM_EXPRESSION(common_alarm_expression);
		tbBossBusiMonAlarmCfg.setCOMMON_ALARM_INFO_EXPRESSION(common_alarm_info_expression);
		
		String flag = tbBossBusiMonAlarmCfg.getFLAG();
		if(StringUtils.isEmpty(flag)){
			flag = "1";
			tbBossBusiMonAlarmCfg.setFLAG(flag);
			tbBossBusiMonAlarmCfg.setSUB_ENTITY("");
		}
		
		if(BossBusiMonConstant.DIE_KPI_ID.equals(tbBossBusiMonAlarmCfg.getKPI_ID())){
			if("0".equals(flag)){
				String host_ip = tbBossBusiMonAlarmCfg.getHOST_IP();
				String sub_entity = tbBossBusiMonAlarmCfg.getSUB_ENTITY();
				tbBossBusiMonAlarmCfg.setSUB_ENTITY(host_ip + BossBusiMonConstant.EQ_ID_SPLIT_CAHR +  sub_entity);
			}
		}
	}

	/**
	 * @Title:expressionHandle
	 * @Description:表达式处理的方法
	 * @param serious_alarm_expression
	 * @return String
	 * @author wangjl_cmi_jl
	 * @version 1.0
	 * @createtime Sep 12, 2014 10:14:33 AM
	 */
	private String expressionHandle(String serious_alarm_expression) {
		serious_alarm_expression = serious_alarm_expression.replace("＞", ">");
		serious_alarm_expression = serious_alarm_expression.replace("＜", "<");
		serious_alarm_expression = serious_alarm_expression.replace("＆＆", "&&");
		return serious_alarm_expression;
	}
	
	/**
	 * @Title:delete
	 * @Description:boss业务监控报警配置删除的方法
	 * @author wangjl_cmi_jl
	 * @version 1.0
	 * @createtime Sep 12, 2014 10:14:51 AM
	 */
	public void delete() {
		bossBusiMonAlarmCfgService.deleteBossBusiMonAlarmCfg(tbBossBusiMonAlarmCfg);
	}
	
	/**
	 * @Title:openOrCloseBossBusiMon
	 * @Description:开启或关闭boss业务监控报警配置的方法
	 * @author wangjl_cmi_jl
	 * @version 1.0
	 * @createtime Sep 12, 2014 10:15:11 AM
	 */
	public void openOrCloseBossBusiMon() {
		bossBusiMonAlarmCfgService.openOrCloseBossBusiMon(tbBossBusiMonAlarmCfg);
	}
	
	/**
	 * @Title:checkAlarmCfgIsExist
	 * @Description:检查boss业务监控报警配置是否存在的方法
	 * @author wangjl_cmi_jl
	 * @version 1.0
	 * @createtime Sep 13, 2014 11:43:29 AM
	 */
	public void checkAlarmCfgIsExist() {
		logger.info("查询boss业务监控报警是否存在的方法开始执行");
		try {
			saveBeforeHandle();
			HttpServletResponse response = ServletActionContext.getResponse();
			String saveFlag="true";
			List<TbBossBusiMonAlarmCfg> tbBossBusiMonAlarmCfgs = bossBusiMonAlarmCfgService.checkAlarmCfgIsExist(tbBossBusiMonAlarmCfg);
			for(TbBossBusiMonAlarmCfg tbBossBusiMonAlarmCfg : tbBossBusiMonAlarmCfgs){
				logger.info("查询到已经存在的boss业务监控配置信息为：KPI_ID:" + tbBossBusiMonAlarmCfg.getKPI_ID() + "FLAG:" + tbBossBusiMonAlarmCfg.getFLAG() + "SUB_ENTITY:" + tbBossBusiMonAlarmCfg.getSUB_ENTITY());
			}
			if(tbBossBusiMonAlarmCfgs !=null && tbBossBusiMonAlarmCfgs.size() > 0){
				saveFlag = "false";
			}
			PrintWriter out = response.getWriter();
			out.print(saveFlag); 
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("boss业务监控报警是否存在的方法异常");
		}
		logger.info("查询boss业务监控报警是否存在的方法执行结束");
	}
	
	/**
	 * @Title:expressionSeriousCheck
	 * @Description:严重报警表达式校验的方法
	 * @author wangjl_cmi_jl
	 * @version 1.0
	 * @createtime Sep 16, 2014 8:39:05 AM
	 */
	public void expressionSeriousCheck() {
		String serious_alarm_expression = tbBossBusiMonAlarmCfg.getSERIOUS_ALARM_EXPRESSION();
		expressionCheck(serious_alarm_expression);
	}
	
	/**
	 * @Title:expressionCommonCheck
	 * @Description:一般报警表达式校验的方法
	 * @author wangjl_cmi_jl
	 * @version 1.0
	 * @createtime Sep 16, 2014 8:39:31 AM
	 */
	public void expressionCommonCheck() {
		String common_alarm_expression = tbBossBusiMonAlarmCfg.getCOMMON_ALARM_EXPRESSION();
		expressionCheck(common_alarm_expression);
	}

	/**
	 * @Title:expressionCheck
	 * @Description:表达式校验的方法
	 * @param serious_alarm_expression
	 * @author wangjl_cmi_jl
	 * @version 1.0
	 * @createtime Sep 16, 2014 8:39:56 AM
	 */
	private void expressionCheck(String serious_alarm_expression) {
		HttpServletResponse response = ServletActionContext.getResponse();
		String expressionHandle = expressionHandle(serious_alarm_expression);
		String replaceHandle = replaceHandle(expressionHandle);
		Integer parseBooleanExpression = ExpressionUtil.parseBooleanExpression(replaceHandle);
		PrintWriter out = null;
		try {
			out = response.getWriter();
			if(parseBooleanExpression == 0){
				out.print("0"); 
				logger.info("表达式[" + replaceHandle + "]为0-假");
			}else if(parseBooleanExpression == 1){
				out.print("1"); 
				logger.info("表达式[" + replaceHandle + "]为1-真");
			}else if(parseBooleanExpression == 2){
				out.print("2"); 
				logger.info("表达式[" + replaceHandle + "]不合法");
			}else if(parseBooleanExpression == 3){
				out.print("3"); 
				logger.info("表达式[" + replaceHandle + "]为3-空");
			}else {
				out.print("NO"); 
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(out != null){
				out.close();
			}
		}
	}
	
	/**
	 * @Title:replaceHandle
	 * @Description:表达式处理的方法
	 * @param expression
	 * @return String
	 * @author wangjl_cmi_jl
	 * @version 1.0
	 * @createtime Sep 16, 2014 8:40:15 AM
	 */
	private String replaceHandle(String expression) {
		if(StringUtils.isEmpty(expression)){
			return expression;
		}
		expression = expression.replace("{in_count}","1");
		expression = expression.replace("{out_count}","1");
		expression = expression.replace("{no_master_count}","1");
		expression = expression.replace("{error_count}","1");
		expression = expression.replace("{before_in_count}","1");
		expression = expression.replace("{before_out_count}","1");
		expression = expression.replace("{before_no_master_count}","1");
		expression = expression.replace("{before_error_count}","1");
		return expression;
	}
	
	/**
	 * @Title:queryPathByIp
	 * @Description:根据主机IP查询路径的方法
	 * @return String
	 * @author wangjl_cmi_jl
	 * @version 1.0
	 * @createtime Sep 17, 2014 1:46:54 PM
	 */
	public String queryPathByIp(){
		pathList = new ArrayList<MonitorCfgObj>();
		try {
			pathList = bossBusiMonAlarmCfgService.queryPathByIpList(hostIp,BossBusiMonConstant.DIE_KPI_ID);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "pathList";
	}
}
