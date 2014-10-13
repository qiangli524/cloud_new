package com.sitech.basd.report.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.basd.report.dao.ReportDao;

/**
 * <p>Title: ReportServiceImpl</p>
 * <p>Description: 报表数据处理serviceImpl
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: SI-TECH </p>
 * @author chenyu
 * @version 1.0
 * @createtime 2014-6-14 上午9:47:51
 *
 */
@Service("resourceReportService")
public class ReportServiceImpl implements ReportService {
	
	@Autowired
	private ReportDao reportDao;

	@Override
	public List<Map<String, String>> getResourceOccupancyRate(String userDomain) throws SQLException {
		List<Map<String, String>> reList = new ArrayList<Map<String,String>>();
		try {
			Map<String, String> cpuMap = new HashMap<String, String>();
			cpuMap.put("type", "cpu个数");
			Map<String, String> memMap = new HashMap<String, String>();
			memMap.put("type", "内存大小/G");
			Map<String, String> mhzMap = new HashMap<String, String>();
			mhzMap.put("type", "cpu频率/GHZ");
			Map<String, String> storageMap = new HashMap<String, String>();
			storageMap.put("type", "存储大小/G");
			Map<String, String> ipMap = new HashMap<String, String>();
			ipMap.put("type", "ip数量");
			List<Map<String, Long>> result = reportDao.getResourceOccupancyRate(userDomain);
			for (Map<String, Long> map : result) {
				if("vcpu_all_count".equals(map.get("key"))){
					cpuMap.put("total", String.valueOf(map.get("value")));
					continue;
				}
				if("vcpu_used_count".equals(map.get("key"))){
					cpuMap.put("value",String.valueOf(map.get("value")));
					continue;
				}
				if("mem_all_mb".equals(map.get("key"))){
					memMap.put("total", divide(map.get("value"), 1000).toString());
					continue;
				}
				if("mem_used_mb".equals(map.get("key"))){
					memMap.put("value", divide(map.get("value"), 1000).toString());
					continue;
				}
				if("cpu_all_mhz".equals(map.get("key"))){
					mhzMap.put("total", divide(map.get("value"), 1000).toString());
					continue;
				}
				if("cpu_used_mhz".equals(map.get("key"))){
					mhzMap.put("value", divide(map.get("value"), 1000).toString());
					continue;
				}
				if("storage_mount_mb".equals(map.get("key"))){
					storageMap.put("total", divide(map.get("value"), 1000).toString());
					continue;
				}
				if("storage_used_mb".equals(map.get("key"))){
					storageMap.put("value", divide(map.get("value"), 1000).toString());
					continue;
				}
				if("ip_all_count".equals(map.get("key"))){
					ipMap.put("total", String.valueOf(map.get("value")));
					continue;
				}
				if("ip_used_count".equals(map.get("key"))){
					ipMap.put("value",String.valueOf(map.get("value")));
					continue;
				}
			}
			countPercent(cpuMap, "vcpu_all_count","vcpu_used_count" );
			countPercent(memMap, "mem_all_mb", "mem_used_mb");
			countPercent(mhzMap, "cpu_all_mhz", "cpu_used_mhz");
			countPercent(storageMap, "storage_mount_mb", "storage_used_mb");
			countPercent(ipMap, "ip_all_count" , "ip_used_count");
			reList.add(cpuMap);
			reList.add(memMap);
			reList.add(mhzMap);
			reList.add(storageMap);
			reList.add(ipMap);
		} catch (SQLException e) {
			throw e;
		}
		return reList;
	}
	
	@Override
	public List<Map<String, String>> getResourceTrend(String userDomain,String type,String startDateStr,String endDateStr)
			throws SQLException, ParseException {
		// 查询条件
		if ("cpu".equals(type)) {
			type = "cpu_used_mhz";
		} else if ("memory".equals(type)) {
			type = "mem_used_mb";
		} else if ("store".equals(type)) {
			type = "storage_used_mb";
		} else if ("ip".equals(type)) {
			type = "ip_used_count";
		} else {
			type = null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Date now = new Date();
		if(StringUtils.isEmpty(startDateStr)&&StringUtils.isEmpty(endDateStr)){
			endDateStr = sdf.format(now);
			startDateStr = sdf.format(DateUtils.addYears(now, -1));
		} else if(StringUtils.isEmpty(startDateStr)){
			startDateStr = sdf.format(DateUtils.addYears(DateUtils.parseDate(endDateStr, new String[]{"yyyy-MM"}), -1));
		} else if(StringUtils.isEmpty(endDateStr)){
			endDateStr = sdf.format(DateUtils.addYears(DateUtils.parseDate(startDateStr, new String[]{"yyyy-MM"}), 1));
		}
		
		// 查询
		List<Map<String, String>> reList = new ArrayList<Map<String,String>>();
		List<Map<String, Long>> result = reportDao.getResourceTrend(userDomain,type,startDateStr,endDateStr);
		
		// 处理数据
		for (Map<String, Long> map : result) {
			Map<String, String> reMap = new HashMap<String, String>();
			for (Entry<String, Long> entry : map.entrySet()) {
				reMap.put(entry.getKey(), String.valueOf(entry.getValue()));
			}
			if (!MapUtils.isEmpty(reMap)) {
				reList.add(reMap);
			}
		}
		return reList;
	}

	
	@Override
	public Map<String, String> getResourceAlarmCount() throws SQLException {
		// 查询
		Map<String, String> reMap = new HashMap<String,String>();
		List<Map<String, Long>> result = reportDao.getResourceAlarmCount();
		if(!CollectionUtils.isEmpty(result)){
			for (Map<String, Long> map : result) {
				for (Entry<String, Long> entry : map.entrySet()) {
					if ("1".equals(entry.getKey())) {
						reMap.put("critical", String.valueOf(entry.getValue()));
					}
					if ("2".equals(entry.getKey())) {
						reMap.put("major", String.valueOf(entry.getValue()));
					}
					if ("3".equals(entry.getKey())) {
						reMap.put("normal", String.valueOf(entry.getValue()));
					}
				}
			}
		}
		return reMap;
	}

	/**
	 * 
	 * @Title: countPercent
	 * @Description: 计算百分比
	 * @param
	 * @return void
	 * @throws
	 * @author chenyu
	 * @version 1.0
	 * @createtime 2014-6-14 上午10:12:59
	 */
	private void countPercent(Map<String, String> map, String total, String used) {
		// 只有当总量和已使用量均存在的情况下,才计算百分比
		if(!StringUtils.isEmpty(map.get(total))&&!StringUtils.isEmpty(map.get(used))&&Integer.parseInt(used)>0){
			map.put("percent", divide(Long.parseLong(map.get(used)),Integer.parseInt(map.get(total)), 2).toString());
		}
	}

	/**
	 * 
	 * @Title: divide
	 * @Description: 将srcNum除以dvNum,结果四舍五入,保留0位小数
	 * @param
	 * @return BigDecimal
	 * @throws
	 * @author chenyu
	 * @version 1.0
	 * @createtime 2014-6-13 下午5:16:32
	 */
	private BigDecimal divide(long srcNum,int dvNum){
		return divide(srcNum, dvNum, 0);
	}

	/**
	 * 
	 * @Title: divide
	 * @Description: 将srcNum除以dvNum,结果四舍五入,保留指定位数小数
	 * @param
	 * @return BigDecimal
	 * @throws
	 * @author chenyu
	 * @version 1.0
	 * @createtime 2014-6-13 下午5:16:32
	 */
	private BigDecimal divide(long srcNum,int dvNum,int scale){
		BigDecimal src = new BigDecimal(srcNum);
		BigDecimal dv = new BigDecimal(dvNum);
		return src.divide(dv,scale, RoundingMode.HALF_UP);
		
	}
	
}
