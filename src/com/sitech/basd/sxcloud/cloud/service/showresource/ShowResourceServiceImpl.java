package com.sitech.basd.sxcloud.cloud.service.showresource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;

import com.sitech.basd.cloud3.domain.charts.DataPoint;
import com.sitech.basd.sxcloud.cloud.dao.resource.HostInfoDao;
import com.sitech.basd.sxcloud.cloud.dao.showresource.ShowResourceDao;
import com.sitech.basd.sxcloud.cloud.domain.showresource.ShowResourceObj;
import com.sitech.basd.sxcloud.cloud.web.showresource.form.AlarmHostStatistics;
import com.sitech.basd.sxcloud.cloud.web.showresource.form.OtherHostStatistics;
import com.sitech.basd.sxcloud.cloud.web.showresource.form.PowerHostStatistics;
import com.sitech.basd.sxcloud.cloud.web.showresource.form.VmwareHostStatistics;
import com.sitech.basd.sxcloud.cloud.web.showresource.form.XenHostStatistics;
import com.sitech.basd.yicloud.dao.datastore.DataStoreDao;
import com.sitech.utils.number.NumberFormatUtil;

public class ShowResourceServiceImpl implements ShowResourceService {
	private ShowResourceDao showResourceDao;

	@Autowired
	private HostInfoDao hostInfoDao;

	public void setShowResourceDao(ShowResourceDao showResourceDao) {
		this.showResourceDao = showResourceDao;
	}

	private DataStoreDao dataStoreDao;

	public void setDataStoreDao(DataStoreDao dataStoreDao) {
		this.dataStoreDao = dataStoreDao;
	}

	/**
	 * 
	 * @Title: insertbyObj
	 * @Description: 插入一条记录
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Feb 23, 2013 4:37:05 PM
	 */
	public int insertbyObj(ShowResourceObj obj) {
		return showResourceDao.insertbyObj(obj);
	}

	/**
	 * 
	 * @Title: getHostList
	 * @Description: 获取主机的统计数据
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Feb 25, 2013 4:06:20 PM
	 */
	public List<HashMap<String, Integer>> getHostList() {
		return showResourceDao.getHostList();
	}

	/**
	 * 
	 * @Title: getHostList
	 * @Description: 获取虚拟机的统计数据
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Feb 25, 2013 4:06:20 PM
	 */

	public List getVmList() {
		return showResourceDao.getVmList();
	}

	/**
	 * 
	 * @Title: getSwitchList
	 * @Description: 获取交换机的统计数据
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Feb 25, 2013 4:07:51 PM
	 */
	public List getSwitchList() {
		return showResourceDao.getSwitchList();
	}

	/**
	 * 
	 * @Title: getResourceList
	 * @Description: 获取资源监控数据,替换为getResourceData duangh
	 * @param params
	 *            :KPI_ID,rownum
	 * @return List
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime Feb 26, 2013 3:42:22 PM
	 */
	@Deprecated
	public List getResourceList(Map map) {
		return showResourceDao.getResourceList(map);
	}

	@Override
	public Map<String, Double> getResourePool(String type) {
		return showResourceDao.getResourePool(type);
	}

	/**
	 * 
	 * @Title: otherHostStatictics
	 * @Description:统计KVM等其他主机cpu,内存等信息
	 * @author duangh
	 * @date Jun 7, 2013 9:23:32 PM
	 * @return
	 */
	@Override
	public OtherHostStatistics otherHostStatictics() {
		OtherHostStatistics otherStstistics = new OtherHostStatistics();
		return otherStstistics;
	}

	/**
	 * 
	 * @Title: powerHostStatictics
	 * @Description:统计IBM power主机cpu，内存，存储等信息
	 * @author duangh
	 * @date Jun 7, 2013 9:22:35 PM
	 * @return
	 */
	@Override
	public PowerHostStatistics powerHostStatictics() {
		PowerHostStatistics powerStatistics = new PowerHostStatistics();
		return powerStatistics;
	}

	/**
	 * 
	 * @Title: vmwareHostStatistics
	 * @Description:统计vmware主机cpu,内存等信息
	 * @author duangh
	 * @date Jun 7, 2013 9:18:10 PM
	 * @return
	 */
	@Override
	public VmwareHostStatistics vmwareHostStatistics() {
		VmwareHostStatistics vmwareStatistics = new VmwareHostStatistics();
		Map<String, Double> map = this.getResourePool("4");
		vmwareStatistics.setAllCPU(map.get("allCPU").intValue());
		vmwareStatistics.setAllMem(map.get("allMem"));
		vmwareStatistics.setUsedCPU(map.get("usedCPU").intValue());
		vmwareStatistics.setUsedMem(map.get("usedMem"));
		vmwareStatistics.setFreeCPU(map.get("freeCPU").intValue());
		vmwareStatistics.setFreeMem(map.get("freeMem"));
		vmwareStatistics.setAllVcpu(map.get("allVcpu").intValue());
		vmwareStatistics.setFreeVcpu(map.get("freeVcpu").intValue());
		vmwareStatistics.setUsedVcpu(map.get("usedVcpu").intValue());

		Map tempMap = new HashMap();
		Map<String, Double> storeMap = dataStoreDao
				.queryVmwareDataStoreAllAndFree(tempMap);
		// TbCloud2HostInfoObj hostInfoObj = new TbCloud2HostInfoObj();
		// hostInfoObj.setAllocated(0);
		// hostInfoObj.setHasvertual("4");

		// double allStor = hostInfoDao.countStoreForUnAllo(hostInfoObj);
		double allStor = 0;
		double freeStor = 0;
		double usedStor = 0;
		if (storeMap != null) {
			if (storeMap.get("CAPACITY") != null
					&& storeMap.get("FREESPACE") != null) {
				allStor = storeMap.get("CAPACITY");
				freeStor = storeMap.get("FREESPACE");
				usedStor = allStor - freeStor;
				allStor = Double.parseDouble(NumberFormatUtil.numFormat(
						allStor, "0.00"));
				freeStor = Double.parseDouble(NumberFormatUtil.numFormat(
						freeStor, "0.00"));
				usedStor = Double.parseDouble(NumberFormatUtil.numFormat(
						usedStor, "0.00"));
			} else {
				allStor = 0;
				freeStor = 0;
				usedStor = 0;
			}
		}
		vmwareStatistics.setAllStor(allStor);
		vmwareStatistics.setFreeStor(freeStor);
		vmwareStatistics.setUsedStor(usedStor);
		return vmwareStatistics;
	}

	/**
	 * 
	 * @Title: xenHostStatistics
	 * @Description:统计xen主机cpu,内存等信息
	 * @author duangh
	 * @date Jun 7, 2013 8:12:16 PM
	 * @return
	 */
	@Override
	public XenHostStatistics xenHostStatistics() {
		XenHostStatistics xenStatistics = new XenHostStatistics();
		Map<String, Double> map = this.getResourePool("3");
		xenStatistics.setAllCPU(map.get("allCPU").intValue());
		xenStatistics.setAllMem(map.get("allMem"));
		xenStatistics.setUsedCPU(map.get("usedCPU").intValue());
		xenStatistics.setUsedMem(map.get("usedMem"));
		xenStatistics.setFreeCPU(map.get("freeCPU").intValue());
		xenStatistics.setFreeMem(map.get("freeMem"));
		xenStatistics.setAllVcpu(map.get("allVcpu").intValue());
		xenStatistics.setFreeVcpu(map.get("freeVcpu").intValue());
		xenStatistics.setUsedVcpu(map.get("usedVcpu").intValue());

		Map tempMap = new HashMap();
		Map<String, Double> storeMap = dataStoreDao
				.queryXenDataStoreAllAndFree(tempMap);
		// TbCloud2HostInfoObj hostInfoObj = new TbCloud2HostInfoObj();
		// hostInfoObj.setAllocated(0);
		// hostInfoObj.setHasvertual("3");
		// double allStor = hostInfoDao.countStoreForUnAllo(hostInfoObj);
		double allStor = 0.0;
		double freeStor = 0;
		double usedStor = 0;
		if (storeMap != null) {
			if (storeMap.get("CAPACITY") != null
					&& storeMap.get("FREESPACE") != null) {
				allStor = storeMap.get("CAPACITY");
				freeStor = storeMap.get("FREESPACE");
				usedStor = allStor - freeStor;
				allStor = Double.parseDouble(NumberFormatUtil.numFormat(
						allStor, "0.00"));
				freeStor = Double.parseDouble(NumberFormatUtil.numFormat(
						freeStor, "0.00"));
				usedStor = Double.parseDouble(NumberFormatUtil.numFormat(
						usedStor, "0.00"));
			} else {
				allStor = 0;
				freeStor = 0;
				usedStor = 0;
			}
		}
		xenStatistics.setAllStor(allStor);
		xenStatistics.setFreeStor(freeStor);
		xenStatistics.setUsedStor(usedStor);
		return xenStatistics;
	}

	/**
	 * 
	 * @Title: getResourceData
	 * @Description:首页展示资源监控情况
	 * @author duangh
	 * @date Jul 9, 2013 4:40:38 PM
	 * @return
	 */
	@Override
	public String getResourceData(String type, String rownum) {
		String highData = null;
		Map params = new HashMap();
		JSONObject json = new JSONObject();
		if (type.equals("all")) {
			// CPU
			params.put("KPI_ID", "PM-R-01-010-09");
			params.put("ROWNUM", Integer.parseInt(rownum));
			List<DataPoint> resultList = showResourceDao
					.getResourceData(params);
			json.accumulate("cpu", JSONArray.fromObject(resultList).toString());
			// 内存
			params.put("KPI_ID", "PM-R-01-010-010");
			params.put("ROWNUM", Integer.parseInt(rownum));
			resultList = showResourceDao.getResourceData(params);
			json.accumulate("mem", JSONArray.fromObject(resultList).toString());
			// 存储
			params.put("KPI_ID", "PM-R-01-010-011");
			params.put("ROWNUM", Integer.parseInt(rownum));
			resultList = showResourceDao.getResourceData(params);
			json.accumulate("storage", JSONArray.fromObject(resultList)
					.toString());

		} else if (type.equals("vmware")) {
			// CPU
			params.put("KPI_ID", "PM-R-01-010-06");
			params.put("ROWNUM", Integer.parseInt(rownum));
			List<DataPoint> resultList = showResourceDao
					.getResourceData(params);
			json.accumulate("cpu", JSONArray.fromObject(resultList).toString());
			// 内存
			params.put("KPI_ID", "PM-R-01-010-07");
			params.put("ROWNUM", Integer.parseInt(rownum));
			resultList = showResourceDao.getResourceData(params);
			json.accumulate("mem", JSONArray.fromObject(resultList).toString());
			// 存储
			params.put("KPI_ID", "PM-R-01-010-08");
			params.put("ROWNUM", Integer.parseInt(rownum));
			resultList = showResourceDao.getResourceData(params);
			json.accumulate("storage", JSONArray.fromObject(resultList)
					.toString());
		} else if (type.equals("xen")) {
			// CPU
			params.put("KPI_ID", "PM-R-01-010-03");
			params.put("ROWNUM", rownum);
			List<DataPoint> resultList = showResourceDao
					.getResourceData(params);
			json.accumulate("cpu", JSONArray.fromObject(resultList).toString());
			// 内存
			params.put("KPI_ID", "PM-R-01-010-04");
			params.put("ROWNUM", rownum);
			resultList = showResourceDao.getResourceData(params);
			json.accumulate("mem", JSONArray.fromObject(resultList).toString());
			// 存储
			params.put("KPI_ID", "PM-R-01-010-05");
			params.put("ROWNUM", rownum);
			resultList = showResourceDao.getResourceData(params);
			json.accumulate("storage", JSONArray.fromObject(resultList)
					.toString());
		}
		highData = json.toString();
		return highData;
	}
	/**
	 * 
	 * @Title: alarmHostStatistics
	 * @Description:数据中心展示告警信息
	 * @author
	 * @date 2013-8-20
	 * @return
	 */
	public List<AlarmHostStatistics> alarmHostStatistics() {

		return showResourceDao.getAlarmAll();
	}
	/**
	 * 
	 * @Title: queryIpData
	 * @Description:数据中心展示IP信息
	 * @author
	 * @date 2013-8-20
	 * @return
	 */
	public List<AlarmHostStatistics> queryIpData() {

		return showResourceDao.queryIpData();
	}
	/**
	 * 
	 * @Title: queryAllCPU
	 * @Description: 查询CPU总数
	 * @param
	 * @return obj
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-09-09
	 */
	public AlarmHostStatistics queryAllCPU() {
		return showResourceDao.queryAllCPU();
	}
	/**
	 * 
	 * @Title: queryUsedCPU
	 * @Description: 查询已用CPU
	 * @param
	 * @return obj
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-09-09
	 */
	public AlarmHostStatistics queryUsedCPU() {
		return showResourceDao.queryUsedCPU();
	}
	/**
	 * 
	 * @Title: queryWOCount
	 * @Description: 查询工单总数
	 * @param
	 * @return obj
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-09-14
	 */
	public AlarmHostStatistics queryWOCount() {
		return showResourceDao.queryWOCount();
	}
}
