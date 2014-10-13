package com.sitech.basd.yicloud.web.scheduler.form;

import java.util.List;

import com.sitech.basd.sxcloud.cloud.web.templet.form.TempletForm;

public class StrategyForm {
	private String strategy_id;// 调度策略编号
	private String strategy_name;// 策略名称
	private String strategy_desc;// 策略描述
	private String enable;// 调度策略是否生效，0是无效，1是有效
	private String type;// 调度策略类型 1 通用策略 2 专用策略
	private String entity_id;// 调度策略对应实体
	private String entity_name;// 调度策略对应实体的名称
	private String strateay_level;
	private String effect_date;// 调度策略有效时间
	private String excute;// 调度策略执行动作
	private String excute_type;// 调度策略 执行类型 人工 或者 自动
	private String status;// 调度策略 执行状态
	private String temp_id;// 调度策略 执行模板
	private String nums;// 调度策略 执行次数
	private String ins_date;// 调度策略 创建时间
	private String creater;// 调度策略 创建人
	private String netWork_id;//网络域ID
	private String netWork_name;//网络域名称

	private String kpi_id;// 指标;
	private String operator;// 调度策略规则 计算公式 > >= < <= != in not in
	private String threshold;// 触发条件 临界值

	private String trigger_ids;// 触发器id，多个用,号分隔
	private List resultList;

	private String trigger_id;// 触发条件id
	private int flag;
	private String name;

	private String SHU;

	private TempletForm tempForm;

	private List hostList;
	private List templateList;
	private String migtype;// 是否指定主机 1 是 2 否
	private int cpu;// CPU个数
	private int mem;// 内存大小
	private double storage;// 存储大小
	private int vh_num; // 虚拟机个数
	private String host_id; // 目标主机ID
	private String TASK_ID;// --任务编号，自动生成
	private String STRATEGY_ID;// --所属策略ID
	private String TYPE;// --任务类型 1 修改虚拟机 2 删除虚拟机 3 迁移虚拟机
	private String VH_ID;// --虚拟机ID，采用uuid
	private String STATUS;// --任务状态 0未处理 1成功 2 失败
	private String TEMP_ID;// --模板ID 预留，暂时不用
	private String INS_DATE;// --任务生成时间
	private String END_DATE;// --任务完成时间
	private String connect_id; // 资源池ID
	
	private String monitor_id;//监控对象id
	private String monitor_name;//监控对象名字

	public String getNetWork_name() {
		return netWork_name;
	}

	public void setNetWork_name(String netWork_name) {
		this.netWork_name = netWork_name;
	}

	public String getMonitor_name() {
		return monitor_name;
	}

	public void setMonitor_name(String monitor_name) {
		this.monitor_name = monitor_name;
	}

	public String getMonitor_id() {
		return monitor_id;
	}

	public void setMonitor_id(String monitor_id) {
		this.monitor_id = monitor_id;
	}

	public String getTASK_ID() {
		return TASK_ID;
	}

	public void setTASK_ID(String task_id) {
		TASK_ID = task_id;
	}

	public String getSTRATEGY_ID() {
		return STRATEGY_ID;
	}

	public void setSTRATEGY_ID(String strategy_id) {
		STRATEGY_ID = strategy_id;
	}

	public String getTYPE() {
		return TYPE;
	}

	public void setTYPE(String type) {
		TYPE = type;
	}

	public String getVH_ID() {
		return VH_ID;
	}

	public void setVH_ID(String vh_id) {
		VH_ID = vh_id;
	}

	public String getSTATUS() {
		return STATUS;
	}

	public void setSTATUS(String status) {
		STATUS = status;
	}

	public String getTEMP_ID() {
		return TEMP_ID;
	}

	public void setTEMP_ID(String temp_id) {
		TEMP_ID = temp_id;
	}

	public String getINS_DATE() {
		return INS_DATE;
	}

	public void setINS_DATE(String ins_date) {
		INS_DATE = ins_date;
	}

	public String getEND_DATE() {
		return END_DATE;
	}

	public void setEND_DATE(String end_date) {
		END_DATE = end_date;
	}

	public void setHostList(List hostList) {
		this.hostList = hostList;
	}

	public String getSHU() {
		return SHU;
	}

	public void setSHU(String shu) {
		SHU = shu;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public String getTrigger_id() {
		return trigger_id;
	}

	public void setTrigger_id(String trigger_id) {
		this.trigger_id = trigger_id;
	}

	public List getResultList() {
		return resultList;
	}

	public void setResultList(List resultList) {
		this.resultList = resultList;
	}

	public String getStrategy_id() {
		return strategy_id;
	}

	public void setStrategy_id(String strategy_id) {
		this.strategy_id = strategy_id;
	}

	public String getStrategy_name() {
		return strategy_name;
	}

	public void setStrategy_name(String strategy_name) {
		this.strategy_name = strategy_name;
	}

	public String getStrategy_desc() {
		return strategy_desc;
	}

	public void setStrategy_desc(String strategy_desc) {
		this.strategy_desc = strategy_desc;
	}

	public String getEnable() {
		return enable;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getEntity_id() {
		return entity_id;
	}

	public void setEntity_id(String entity_id) {
		this.entity_id = entity_id;
	}

	public String getEffect_date() {
		return effect_date;
	}

	public void setEffect_date(String effect_date) {
		this.effect_date = effect_date;
	}

	public String getExcute() {
		return excute;
	}

	public void setExcute(String excute) {
		this.excute = excute;
	}

	public String getExcute_type() {
		return excute_type;
	}

	public void setExcute_type(String excute_type) {
		this.excute_type = excute_type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTemp_id() {
		return temp_id;
	}

	public void setTemp_id(String temp_id) {
		this.temp_id = temp_id;
	}

	public String getNums() {
		return nums;
	}

	public void setNums(String nums) {
		this.nums = nums;
	}

	public String getIns_date() {
		return ins_date;
	}

	public void setIns_date(String ins_date) {
		this.ins_date = ins_date;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public String getKpi_id() {
		return kpi_id;
	}

	public void setKpi_id(String kpi_id) {
		this.kpi_id = kpi_id;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getThreshold() {
		return threshold;
	}

	public void setThreshold(String threshold) {
		this.threshold = threshold;
	}

	public String getStrateay_level() {
		return strateay_level;
	}

	public void setStrateay_level(String strateay_level) {
		this.strateay_level = strateay_level;
	}

	public String getTrigger_ids() {
		return trigger_ids;
	}

	public void setTrigger_ids(String trigger_ids) {
		this.trigger_ids = trigger_ids;
	}

	public TempletForm getTempForm() {
		return tempForm;
	}

	public void setTempForm(TempletForm tempForm) {
		this.tempForm = tempForm;
	}

	public String getEntity_name() {
		return entity_name;
	}

	public void setEntity_name(String entity_name) {
		this.entity_name = entity_name;
	}

	public List getTemplateList() {
		return templateList;
	}

	public void setTemplateList(List templateList) {
		this.templateList = templateList;
	}

	public int getCpu() {
		return cpu;
	}

	public void setCpu(int cpu) {
		this.cpu = cpu;
	}

	public int getMem() {
		return mem;
	}

	public void setMem(int mem) {
		this.mem = mem;
	}

	public double getStorage() {
		return storage;
	}

	public void setStorage(double storage) {
		this.storage = storage;
	}

	public int getVh_num() {
		return vh_num;
	}

	public void setVh_num(int vh_num) {
		this.vh_num = vh_num;
	}

	public List getHostList() {
		return hostList;
	}

	public String getHost_id() {
		return host_id;
	}

	public void setHost_id(String host_id) {
		this.host_id = host_id;
	}

	public String getMigtype() {
		return migtype;
	}

	public void setMigtype(String migtype) {
		this.migtype = migtype;
	}

	public String getConnect_id() {
		return connect_id;
	}

	public void setConnect_id(String connect_id) {
		this.connect_id = connect_id;
	}

	public String getNetWork_id() {
		return netWork_id;
	}

	public void setNetWork_id(String netWork_id) {
		this.netWork_id = netWork_id;
	}
	
}
