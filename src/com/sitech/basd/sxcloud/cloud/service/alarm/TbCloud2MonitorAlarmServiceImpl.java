package com.sitech.basd.sxcloud.cloud.service.alarm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.sitech.basd.fusioncharts.util.JacksonUtil;
import com.sitech.basd.fusioncharts.vo.Chart;
import com.sitech.basd.fusioncharts.vo.Data;
import com.sitech.basd.fusioncharts.vo.FusionCharts;
import com.sitech.basd.sxcloud.cloud.dao.alarm.TbCloud2MonitorAlarmDao;
import com.sitech.basd.sxcloud.cloud.domain.alarm.TbCloud2MonitorAlarmObj;
import com.sitech.basd.sxcloud.cloud.domain.monitor.MonitorAlarmLocationObj;
import com.sitech.basd.sxcloud.cloud.domain.monitor.MonitorAlarmObj;

/**
 * 
 * <p>
 * Title: TbCloud2MonitorAlarmServiceImpl
 * </p>
 * <p>
 * Description: 接口实现类
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
 * @createtime Mar 29, 2012 2:30:55 PM
 * 
 */
@Service("tbCloud2MonitorAlarmService")
public class TbCloud2MonitorAlarmServiceImpl implements TbCloud2MonitorAlarmService {
	private TbCloud2MonitorAlarmDao tbCloud2MonitorAlarmDao;

	public void setTbCloud2MonitorAlarmDao(TbCloud2MonitorAlarmDao tbCloud2MonitorAlarmDao) {
		this.tbCloud2MonitorAlarmDao = tbCloud2MonitorAlarmDao;
	}

	public void alarmClear(TbCloud2MonitorAlarmObj obj) {
		tbCloud2MonitorAlarmDao.alarmClear(obj);
	}

	public int deleteAlarmByObj(TbCloud2MonitorAlarmObj obj) {
		return tbCloud2MonitorAlarmDao.deleteAlarmByObj(obj);
	}

	public TbCloud2MonitorAlarmObj getDemoAlarmObj() {
		return tbCloud2MonitorAlarmDao.getDemoAlarmObj();
	}

	public int insertAlarmByObj(TbCloud2MonitorAlarmObj obj) {
		return tbCloud2MonitorAlarmDao.insertAlarmByObj(obj);
	}

	public void insertOrUpdateAlarm(TbCloud2MonitorAlarmObj obj) {
		tbCloud2MonitorAlarmDao.insertOrUpdateAlarm(obj);
	}

	public TbCloud2MonitorAlarmObj queryAlarmDataByObj(TbCloud2MonitorAlarmObj obj) {
		return tbCloud2MonitorAlarmDao.queryAlarmDataByObj(obj);
	}

	public List<TbCloud2MonitorAlarmObj> queryOutOfDateData(TbCloud2MonitorAlarmObj obj) {
		return tbCloud2MonitorAlarmDao.queryOutOfDateData(obj);
	}

	public int updateAlarmDataStat(TbCloud2MonitorAlarmObj obj) {
		return tbCloud2MonitorAlarmDao.updateAlarmDataStat(obj);
	}

	public void updateAlarmDataStatService(TbCloud2MonitorAlarmObj obj) {
		tbCloud2MonitorAlarmDao.updateAlarmDataStatService(obj);
	}

	public int updateAlarmDataTime(TbCloud2MonitorAlarmObj obj) {
		return tbCloud2MonitorAlarmDao.updateAlarmDataTime(obj);
	}

	public void insertAlarmWhenConnError() {
		tbCloud2MonitorAlarmDao.insertAlarmWhenConnError();
	}

	@Override
	public Map<String,Long> getAlarms(MonitorAlarmObj obj) {
		return tbCloud2MonitorAlarmDao.getAlarms(obj);
	}
	
	@Override
	public FusionCharts queryMonitorAlarmChart(MonitorAlarmObj obj) {
		FusionCharts fusionCharts = new FusionCharts();
		Chart chart = new Chart();
		Map<String,Long> temp=getAlarms(obj);
//		double[] datas = new double[]{temp.get("0")==null?0:temp.get("0"), temp.get("1")==null?0:temp.get("1"), 
//				temp.get("2")==null?0:temp.get("2"),
//				temp.get("3")==null?0:temp.get("3")};
//		String[] rowKeys = { "严重告警", "主要告警", "次要告警", "不确定告警" };
//		String[] colorKeys = { "FF6347", "CD69C9", "EEC900", "8EE5EE" };
		double[] datas = new double[]{temp.get("0")==null?0:temp.get("0"), temp.get("1")==null?0:temp.get("1"), 
				temp.get("2")==null?0:temp.get("2")};
		String[] rowKeys = { "严重告警", "重要告警", "一般告警" };
		String[] linkList = {"javascript:searchRequest0(theForm)","javascript:searchRequest1(theForm)","javascript:searchRequest2(theForm)"};
		String[] colorKeys = { "FF6347", "CD69C9", "EEC900" };
		List<Data> dataList = new ArrayList<Data>();
		for (int i=0;i<datas.length;i++) {
			double value=datas[i];
			Data data=new Data();
			data.setValue(value+"");
			data.setLabel(rowKeys[i]);
			data.setColor(colorKeys[i]);
			data.setLink(linkList[i]);
			dataList.add(data);
		}
		chart.setYaxisname("单位:个数");
		chart.setCaption("告警TOP展示");
//		chart.setCanvasBgColor("#DDE7FF");
		chart.setBgColor("#DDE7FF");
		chart.setCanvasbgalpha("100");
		chart.setCanvasbordercolor("#DDE7FF");
		fusionCharts.setData(dataList);
		chart.setPlotGradientColor("");
		chart.setThousandseparator(",");
//		chart.setUseroundedges("1");
		chart.setBgcolor("FFFFFF,FFFFFF");
		chart.setShowborder("0");
		chart.setCanvasbordercolor("E6E6E6");
//		chart.setFormatnumberscale("0");
		fusionCharts.setChart(chart);
		return fusionCharts;
	}
	public MonitorAlarmLocationObj queryLocationForHost(MonitorAlarmLocationObj obj){
		return tbCloud2MonitorAlarmDao.queryLocationForHost(obj);
	}
	public MonitorAlarmLocationObj queryLocationForVm(MonitorAlarmLocationObj obj){
		return tbCloud2MonitorAlarmDao.queryLocationForVm(obj);
	}
	public MonitorAlarmLocationObj queryLocationForStore(MonitorAlarmLocationObj obj){
		MonitorAlarmLocationObj malobj = new MonitorAlarmLocationObj();
		malobj = tbCloud2MonitorAlarmDao.queryLocationForStore(obj);
		malobj.setOneLevelName(stringTakeOutRepeat(malobj.getOneLevelName()));	//去重
		malobj.setTwoLevelName(stringTakeOutRepeat(malobj.getTwoLevelName()));	//去重
		malobj.setThreeLevelName(stringTakeOutRepeat(malobj.getThreeLevelName()));//去重
		return malobj;
	}
	
	/**
	 * @Title: stringTakeOutRepeat
	 * @Description: 将字符串转成数组，将数组去重，再拼接成字符串
	 * @param String str
	 * @return String
	 * @author liwq_bj
	 * @version 1.0
	 * @createtime 2014-8-14 下午6:08:24
	 */
	public static String stringTakeOutRepeat(String str){
		StringBuffer sb = new StringBuffer();
		String[] array = str.split(",");
		if(array.length>0){
			HashMap<String, Integer> hm = new HashMap<String, Integer>();
	        for(String strtmp:array){
	            hm.put(strtmp, 1);   //用hashmap除去重复的值
	        }
	        for(String ss:hm.keySet()){
	            sb.append(ss).append(",");
	        }
		}
		
		return sb.toString().substring(0, sb.length()-1);
	}
}
