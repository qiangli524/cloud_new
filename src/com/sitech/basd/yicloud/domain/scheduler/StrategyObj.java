package com.sitech.basd.yicloud.domain.scheduler;

import com.sitech.basd.common.domain.BasePrivilegeObj;
import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

public class StrategyObj extends BasePrivilegeObj {
	private String strategy_id;// 调度策略编号
	private String strategy_name;// 策略名称
	private String strategy_desc;// 策略描述
	private String enable;// 调度策略是否生效，0是无效，1是有效
	private String type;// 调度策略类型 1 通用策略 2 专用策略
	private String strateay_level;// 级别VC 0,DC 1,CLUSTER 2,HOST 3,VMHOST 4'
	private String entity_id;// 调度策略对应实体UUID
	private String entity_name;// 调度策略对应的实体名称
	private String effect_date;// 调度策略有效时间
	private String excute;// 调度策略执行动作
	private String excute_type;// 调度策略 执行类型 人工 或者 自动
	private String status;// 调度策略 执行状态
	private String temp_id;// 调度策略 执行模板
	private String nums;// 调度策略 执行次数
	private String ins_date;// 调度策略 创建时间
	private String creater;// 调度策略 创建人
	private String migtype;// 是否指定主机 1 是 2 否
	private String netWork_id;//网络域ID
	private String netWork_name;//网络域名称

	private String kpi_id;// 指标;
	private String operator;// 调度策略规则 计算公式 > >= < <= != in not in
	private String threshold;// 触发条件 临界值
	private String trigger_id;
	private String tigger_n;
	private String trigger_m;
	private String min;
	private String max;
	private String interval;
	private String tigger_level;
	private String content;
	private String kpi_desc;

	private int cpu;// CPU个数
	private int mem;// 内存大小
	private double storage;// 存储大小
	private int vh_num; // 虚拟机个数
	private String host_id; // 目标主机ID
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

	public String getKpi_desc() {
		return kpi_desc;
	}

	public void setKpi_desc(String kpi_desc) {
		this.kpi_desc = kpi_desc;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public String getStrateay_level() {
		return strateay_level;
	}

	public void setStrateay_level(String strateay_level) {
		this.strateay_level = strateay_level;
	}

	public String getTrigger_id() {
		return trigger_id;
	}

	public void setTrigger_id(String trigger_id) {
		this.trigger_id = trigger_id;
	}

	public String getTigger_n() {
		return tigger_n;
	}

	public void setTigger_n(String tigger_n) {
		this.tigger_n = tigger_n;
	}

	public String getInterval() {
		return interval;
	}

	public void setInterval(String interval) {
		this.interval = interval;
	}

	public String getTigger_level() {
		return tigger_level;
	}

	public void setTigger_level(String tigger_level) {
		this.tigger_level = tigger_level;
	}

	public String getEntity_name() {
		return entity_name;
	}

	public void setEntity_name(String entity_name) {
		this.entity_name = entity_name;
	}

	public String getTrigger_m() {
		return trigger_m;
	}

	public void setTrigger_m(String trigger_m) {
		this.trigger_m = trigger_m;
	}

	public String getMin() {
		return min;
	}

	public void setMin(String min) {
		this.min = min;
	}

	public String getMax() {
		return max;
	}

	public void setMax(String max) {
		this.max = max;
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

	public String getHost_id() {
		return host_id;
	}

	public void setHost_id(String host_id) {
		this.host_id = host_id;
	}

	public int getVh_num() {
		return vh_num;
	}

	public void setVh_num(int vh_num) {
		this.vh_num = vh_num;
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
