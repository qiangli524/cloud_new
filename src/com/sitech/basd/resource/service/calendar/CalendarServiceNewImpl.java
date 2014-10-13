package com.sitech.basd.resource.service.calendar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.basd.fusioncharts.vo.Chart;
import com.sitech.basd.fusioncharts.vo.Data;
import com.sitech.basd.fusioncharts.vo.FusionCharts;
import com.sitech.basd.resource.dao.calendar.CalendarDao;
import com.sitech.basd.resource.domain.calendar.CalendarObj;

/**
 * 
 * <p>Title: CalendarServiceImpl</p>
 * <p>Description: 资源使用情况统计</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p> Company: SI-TECH</p>
 * 
 * @author qism
 * @version 1.0
 * @createtime 2014-05-14 下午13:47:40
 * 
 */
@Service("calendarServiceNew")
public class CalendarServiceNewImpl implements CalendarServiceNew {
	@Autowired
	public CalendarDao calendarDao;

	/**
	 * @Title:查询CPU资源使用情况
	 * @Company: si-tech
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-05-14 下午13:47:40
	 */
	@Override
	public FusionCharts queryCpuList(CalendarObj calendarObj) {
		List<Data> dataList = new ArrayList<Data>();
		Data data = new Data();
		Data data1 = new Data();
		Data data2 = new Data();
		FusionCharts fusionCharts = new FusionCharts();
		//获取CPU数据
		List<Map<String,String>> list=calendarDao.getResource(calendarObj);
		Map<String,String> maps=new HashMap<String, String>();
		for(Map<String, String> map : list){
			String key="";
			String value="";
			Set<Entry<String,String>> set = map.entrySet();  
		    for (Entry<String,String>   entry  :   set) {  
		        if(entry.getValue() == null || isIntOrFloat(entry.getValue())){
		        	value = entry.getValue();
		        }else{
		        	key = entry.getValue();
		        }
		    }   
	        maps.put(key, value);
		}
		data.setLabel("分配量");
		data.setValue(maps.get("allot_cpu_amount"));
		dataList.add(data);
		data1.setLabel("预分配量");
		data1.setValue(maps.get("bo_cpu_amount"));
		dataList.add(data1);
		data2.setLabel("剩余量");
		data2.setValue(maps.get("surplus_cpu_amount"));
		dataList.add(data2);
		// 设置报表基本参数
		Chart chart = new Chart();
		//chart.setCaption("CPU资源");// 报表主标题
		chart.setSubcaption("单位:核");// 报表子标题
		chart.setBaseFontSize("10");// 图表字体大小
		chart.setOutCnvBaseFont("10");// 图表画布以外的字体样式
		chart.setPieRadius("50");//圆半径
		chart.setBgcolor("FFFFFF,FFFFFF");//画板背景色
		chart.setShowborder("0");//画板边框
		fusionCharts.setChart(chart);
		fusionCharts.setData(dataList);
		return fusionCharts;
	}
	/**
	 * @Title:查询存储资源使用情况
	 * @Company: si-tech
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-05-14 下午13:47:40
	 */
	@Override
	public FusionCharts queryStorageList(CalendarObj calendarObj) {
		List<Data> dataList = new ArrayList<Data>();
		Data data = new Data();
		Data data1 = new Data();
		Data data2 = new Data();
		//获取存储数据
		List<Map<String,String>> list=calendarDao.getResource(calendarObj);
		Map<String,String> maps=new HashMap<String, String>();
		for(Map<String, String> map : list){
			String key="";
			String value="";
			Set<Entry<String,String>> set = map.entrySet();  
		    for (Entry<String,String>   entry  :   set) {  
		        if(entry.getValue() == null || isIntOrFloat(entry.getValue())){
		        	value = entry.getValue();
		        }else{
		        	key = entry.getValue();
		        }
		    }   
	        maps.put(key, value);
		}
		FusionCharts fusionCharts = new FusionCharts();
		data.setLabel("预分配量");
		data.setValue(maps.get("bo_store_amount"));
		dataList.add(data);	
		data1.setLabel("分配量");
		data1.setValue(maps.get("allot_store_amount"));
		dataList.add(data1);
		data2.setLabel("剩余量");
		data2.setValue(maps.get("surplus_store_amount"));
		dataList.add(data2);
		// 设置报表基本参数
		Chart chart = new Chart();
		//chart.setCaption("存储资源");
		chart.setSubcaption("单位:TB");
		chart.setBaseFontSize("10");
		chart.setOutCnvBaseFont("10");
		chart.setPieRadius("50");
		chart.setBgcolor("FFFFFF,FFFFFF");
		chart.setShowborder("0");
		fusionCharts.setChart(chart);
		fusionCharts.setData(dataList);
		return fusionCharts;
	}

	/**
	 * @Title:查询内存资源使用情况
	 * @Company: si-tech
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-05-14 下午13:47:40
	 */
	@Override
	public FusionCharts queryMemList(CalendarObj calendarObj) {
		List<Data> dataList = new ArrayList<Data>();
		Data data  = new Data();
		Data data1 = new Data();
		Data data2 = new Data();
		FusionCharts fusionCharts = new FusionCharts();
		//获取内存数据
		List<Map<String,String>> list=calendarDao.getResource(calendarObj);
		Map<String,String> maps=new HashMap<String, String>();
		for(Map<String, String> map : list){
			String key="";
			String value="";
			Set<Entry<String,String>> set = map.entrySet();  
		    for (Entry<String,String>   entry  :   set) {  
		        if(entry.getValue() == null || isIntOrFloat(entry.getValue())){
		        	value = entry.getValue();
		        }else{
		        	key = entry.getValue();
		        }
		    }   
	        maps.put(key, value);
		}
		data.setLabel("预分配量");
		data.setValue(maps.get("bo_mem_amount"));
		dataList.add(data);
		data1.setLabel("分配量");
		data1.setValue(maps.get("allot_mem_amount"));
		dataList.add(data1);
		data2.setLabel("剩余量");
		data2.setValue(maps.get("surplus_mem_amount"));
		dataList.add(data2);
		// 设置报表基本参数
		Chart chart = new Chart();
		//chart.setCaption("内存资源");
		chart.setSubcaption("单位:G");
		chart.setBaseFontSize("10");
		chart.setOutCnvBaseFont("10");
		chart.setPieRadius("50");
		chart.setBgcolor("FFFFFF,FFFFFF");
		chart.setShowborder("0");
		fusionCharts.setChart(chart);
		fusionCharts.setData(dataList);
		return fusionCharts;
	}

	/**
	 * @Title:查询IP资源使用情况
	 * @Company: si-tech
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-05-14 下午16:15:26
	 */
	@Override
	public FusionCharts queryNetList(CalendarObj calendarObj) {
		List<Data> dataList = new ArrayList<Data>();
		Data data1 = new Data();
		Data data2 = new Data();
		//获取存储数据
		List<Map<String,String>> list=calendarDao.getResource(calendarObj);
		Map<String,String> maps=new HashMap<String, String>();
		for(Map<String, String> map : list){
			String key="";
			String value="";
			Set<Entry<String,String>> set = map.entrySet();  
		    for (Entry<String,String>   entry  :   set) {  
		        if(entry.getValue() == null || isIntOrFloat(entry.getValue())){
		        	value = entry.getValue();
		        }else{
		        	key = entry.getValue();
		        }
		    }   
	        maps.put(key, value);
		}
		data1.setLabel("使用量");
		data1.setValue(maps.get("used_ip_amount"));
		dataList.add(data1);
		data2.setLabel("剩余量");
		data2.setValue(maps.get("surplus_ip_amount"));
		dataList.add(data2);
		FusionCharts fusionCharts = new FusionCharts();
		// 设置报表基本参数
		Chart chart = new Chart();
		chart.setSubcaption("单位:个");
		chart.setBaseFontSize("10");
		chart.setOutCnvBaseFont("10");
		chart.setPieRadius("50");
		chart.setBgcolor("FFFFFF,FFFFFF");
		chart.setShowborder("0");
		fusionCharts.setChart(chart);
		fusionCharts.setData(dataList);
		return fusionCharts;
	}
	
	/**
	 * 判断变量是否为数字
	 * @Title: isIntOrFloat
	 * @Description: 是数字返回true
	 * @param
	 * @return boolean
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-5-19 上午9:08:40
	 */
	public static boolean isIntOrFloat(String str) {
		Boolean boo1 =str.matches("\\d+"); //匹配正整数
		Boolean boo2 =str.matches("^[1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*$");//匹配正浮点数
		Boolean boo3 = boo1 || boo2 == true ? true:false; 
		return boo3;			  
    }
}
