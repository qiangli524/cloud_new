package com.sitech.basd.yicloud.service.datastore;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sitech.basd.fusioncharts.util.JacksonUtil;
import com.sitech.basd.yicloud.dao.datastore.StoreDeviceDao;
import com.sitech.basd.yicloud.domain.datastore.StoreDeviceObj;
import com.sitech.ssd.ah.fusioncharts.vo.pie.PieChart;
import com.sitech.ssd.ah.fusioncharts.vo.pie.PieData;
import com.sitech.ssd.ah.fusioncharts.vo.pie.PieVO;

public class StoreDeviceServiceImpl implements StoreDeviceService {
	private StoreDeviceDao storeDeviceDao;

	public void setStoreDeviceDao(StoreDeviceDao storeDeviceDao) {
		this.storeDeviceDao = storeDeviceDao;
	}

	/**
	 * 
	 * @Title: queryForListByObj
	 * @Description: 查询存储设备列表
	 * @param
	 * @return List
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-9-11 下午5:55:34
	 */
	public List queryForListByObj(StoreDeviceObj obj) {
		List<StoreDeviceObj> lst = storeDeviceDao.queryForListByObj(obj);
		NumberFormat nuFormat = NumberFormat.getIntegerInstance();
		for (StoreDeviceObj storeDeviceObj : lst) {
			storeDeviceObj.setCapacity(nuFormat.format(Double
					.parseDouble(storeDeviceObj.getCapacity() == null
							? "0"
							: storeDeviceObj.getCapacity())));
			storeDeviceObj.setFreeSpace(nuFormat.format(Double
					.parseDouble(storeDeviceObj.getFreeSpace() == null
							? "0"
							: storeDeviceObj.getFreeSpace())));
		}
		return lst;
	}

	/**
	 * 
	 * @Title: insertStoreDevice
	 * @Description: 插入存储设备
	 * @param
	 * @return int
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-9-11 下午5:57:41
	 */
	public int insertStoreDevice(StoreDeviceObj obj) {
		return storeDeviceDao.insertStoreDevice(obj);
	}

	/**
	 * 
	 * @Title: updateByObj
	 * @Description: 更新存储设备
	 * @param
	 * @return int
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-9-11 下午5:58:01
	 */
	public int updateByObj(StoreDeviceObj obj) {
		return storeDeviceDao.updateByObj(obj);
	}

	/**
	 * 
	 * @Title: deleteByObj
	 * @Description: 删除存储设备
	 * @param
	 * @return int
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-9-11 下午5:58:26
	 */
	public int deleteByObj(StoreDeviceObj obj) {
		return storeDeviceDao.deleteByObj(obj);
	}

	@Override
	public List<StoreDeviceObj> queryForListByUseIn(
			StoreDeviceObj storeDeviceObj) {
		// TODO Auto-generated method stub
		return storeDeviceDao.queryForListByUseIn(storeDeviceObj);
	}
	/**
	 * 
	 * @Title: queryByObj
	 * @Description: 查询一条记录
	 * @param
	 * @return obj
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-9-13
	 */
	public StoreDeviceObj queryByObj(StoreDeviceObj obj) {
		return storeDeviceDao.queryByObj(obj);
	}

	/**
	 * @Title: getStoreMap
	 * @Description: 获取存储类型大小集合
	 * @param
	 * @return Map<String,Double>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-11-19 上午8:45:48
	 */
	public Map<String, Double> getStoreMap(StoreDeviceObj storeDeviceObj) {
		Map<String, Double> map = new HashMap<String, Double>();
		double validSpace = 0.0;// san 有效容量
		double mountSpace = 0.0;// san 接入容量
		double unmountSpace = 0.0;// san 未接入容量
		double alloSpace = 0.0;// san 分配容量
		double unalloSpace = 0.0;// san 未分配容量
		double usedSpace = 0.0;// san 使用容量
		double unusedSpace = 0.0;// san 未使用容量

		StoreDeviceObj deviceObj = new StoreDeviceObj();

		/*--------------------有效容量----------------------*/
		validSpace = storeDeviceDao.queryValidSpace(storeDeviceObj);

		/*--------------------接入容量---------------------*/
		// vmware
		deviceObj = storeDeviceDao.queryVmwareCapacity(storeDeviceObj);
		double allstorevmware = Double
				.parseDouble(deviceObj.getCapacity() == null
						? "0.0"
						: deviceObj.getCapacity());
		double freestorevmware = Double
				.parseDouble(deviceObj.getFreeSpace() == null
						? "0.0"
						: deviceObj.getFreeSpace());
		double purstorevmeare = Double
				.parseDouble(deviceObj.getPurchaseSpace() == null
						? "0.0"
						: deviceObj.getPurchaseSpace());
		double usedstorevmware = allstorevmware - freestorevmware;

		// xen
		deviceObj = storeDeviceDao.queryXenCapacity(storeDeviceObj);
		double allstorexen = Double.parseDouble(deviceObj.getCapacity() == null
				? "0.0"
				: deviceObj.getCapacity());
		double freestorexen = Double
				.parseDouble(deviceObj.getFreeSpace() == null
						? "0.0"
						: deviceObj.getFreeSpace());
		double purstorexen = Double
				.parseDouble(deviceObj.getPurchaseSpace() == null
						? "0.0"
						: deviceObj.getPurchaseSpace());
		double usedstorexen = allstorexen - freestorexen;

		mountSpace = allstorevmware + allstorexen;
		unmountSpace = validSpace - mountSpace;

		alloSpace = purstorevmeare + purstorexen;
		unalloSpace = mountSpace - alloSpace;

		usedSpace = usedstorevmware + usedstorexen;
		unusedSpace = alloSpace - usedSpace;

		NumberFormat nf = NumberFormat.getInstance();
		nf.setMinimumFractionDigits(2);

		map.put("validSpace", Double.parseDouble(nf.format(validSpace)));
		map.put("mountSpace", Double.parseDouble(nf.format(mountSpace)));
		map.put("unmountSpace", unmountSpace);
		map.put("alloSpace", Double.parseDouble(nf.format(alloSpace)));
		map.put("unalloSpace", unalloSpace);
		map.put("usedSpace", usedSpace);
		map.put("unusedSpace", unusedSpace);
		return map;
	}
	/**
	 * @Title: queryStoreType
	 * @Description: 查询存储的类型
	 * @param
	 * @return List<String>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-15 下午12:00:40
	 */
	@Override
	public List<StoreDeviceObj> queryStoreType(StoreDeviceObj storeDeviceObj) {
		return storeDeviceDao.queryStoreType(storeDeviceObj);
	}

	/**
	 * @Title: countByType
	 * @Description: 根据类型统计存储设备的块数
	 * @param
	 * @return int
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-25 下午8:13:35
	 */
	@Override
	public int countByType(StoreDeviceObj storeDeviceObj) {
		return storeDeviceDao.countByType(storeDeviceObj);
	}

	/**
	 * @Title: buildPieXml
	 * @Description: 创建饼图
	 * @param
	 * @return String
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2013-11-26 下午5:01:24
	 */
	@Override
	public String buildPieXml(Map<String, Double> map, String flag,
			String pieRadius) {
		PieVO pieVo = new PieVO();
		PieChart chart = new PieChart();
		chart.setBgcolor("D1EEEE");
		chart.setCanvasbgcolor("EBEBEB");
		chart.setCaptionpadding("25");
		chart.setDecimals("3");
		chart.setThousandseparator(",");
		chart.setShowlegend("1");
		chart.setLegendposition("RIGHT");
		chart.setShowpercetvalues("1");
		chart.setPieRadius(pieRadius);
		chart.setShowborder("0");

		List<PieData> list = new ArrayList<PieData>();

		if ("valid".equals(flag)) {
			chart.setCaption("有效容量");
			chart.setSubcaption("大小： " + map.get("validSpace") + "T");
			PieData data = new PieData();
			data.setLabel("已接入");
			data.setValue(map.get("mountSpace") + "");
			list.add(data);

			PieData data2 = new PieData();
			data2.setLabel("未接入");
			data2.setValue(map.get("unmountSpace") + "");
			list.add(data2);
		} else if ("mount".equals(flag)) {
			chart.setCaption("接入容量");
			chart.setSubcaption("大小： " + map.get("mountSpace") + "T");
			PieData data = new PieData();
			data.setLabel("已分配");
			data.setValue(map.get("alloSpace") + "");
			list.add(data);

			PieData data2 = new PieData();
			data2.setLabel("未分配");
			data2.setValue(map.get("unalloSpace") + "");
			list.add(data2);
		} else if ("allo".equals(flag)) {
			chart.setCaption("分配容量");
			chart.setSubcaption("大小： " + map.get("alloSpace") + "T");
			PieData data = new PieData();
			data.setLabel("已使用");
			data.setValue(map.get("usedSpace") + "");
			list.add(data);

			PieData data2 = new PieData();
			data2.setLabel("未使用");
			data2.setValue(map.get("unusedSpace") + "");
			list.add(data2);
		}

		pieVo.setChart(chart);
		pieVo.setData(list);
		return JacksonUtil.toJson(pieVo);
	}

	/**  
	  * @Title: queryForList  
	  * @Description: 查询存储设备列表
	  * @return List<StoreDeviceObj>   
	  * @throws  
	  * @Date 2014-6-2 上午10:21:36
	  * @author lipp
	  * @param storeDeviceObj
	  * @return
	  */
	@Override
	public List<StoreDeviceObj> queryForList(StoreDeviceObj storeDeviceObj) {
		return storeDeviceDao.queryForList(storeDeviceObj);
	}
}
