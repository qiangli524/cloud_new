package com.sitech.basd.resource.service.top;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.basd.resource.dao.global.AlarmGlobalDao;
import com.sitech.basd.resource.dao.global.HostGlobalDao;
import com.sitech.basd.resource.dao.global.VmGlobalDao;

@Service("topTargetService")
public class TopTargetServiceImpl implements TopTargetService {
	@Autowired
	private HostGlobalDao hostGlobalDao;
	@Autowired
	private VmGlobalDao vmGlobalDao;
	@Autowired
	private AlarmGlobalDao alarmGlobalDao;

	/**
	 * 
	 * @Title: queryCpuTop
	 * @Description: 查询y值
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 12, 2013 2:15:38 PM
	 */
	public Double[] queryYData(Map map) {
		String entity_type = (String) map.get("entity_type");
		String type = (String) map.get("type");
		Object uuid = map.get("uuid");
		Double[] y = new Double[0];
		if ("1".equals(entity_type)) {// 主机
			if (uuid == null) {
				if ("cpu".equals(type)) {
					y = (Double[]) hostGlobalDao.queryCpuTop(map).toArray(new Double[0]);
				} else if ("mem".equals(type)) {
					y = (Double[]) hostGlobalDao.queryMemTop(map).toArray(new Double[0]);
				} else {
					y = (Double[]) hostGlobalDao.queryStoreTop(map).toArray(new Double[0]);
				}
			} else {// 查集群下
				if ("cpu".equals(type)) {
					y = (Double[]) hostGlobalDao.queryCpuTopClu(map).toArray(new Double[0]);
				} else if ("mem".equals(type)) {
					y = (Double[]) hostGlobalDao.queryMemTopClu(map).toArray(new Double[0]);
				} else {
					y = (Double[]) hostGlobalDao.queryStoreTopClu(map).toArray(new Double[0]);
				}
			}

		} else if ("2".equals(entity_type)) {// 虚拟机
			if (uuid == null) {
				if ("cpu".equals(type)) {
					y = (Double[]) vmGlobalDao.queryCpuTop(map).toArray(new Double[0]);
				} else if ("mem".equals(type)) {
					y = (Double[]) vmGlobalDao.queryMemTop(map).toArray(new Double[0]);
				} else {
					y = (Double[]) vmGlobalDao.queryStoreTop(map).toArray(new Double[0]);
				}
			} else {
				if ("cpu".equals(type)) {
					y = (Double[]) vmGlobalDao.queryCpuTopClu(map).toArray(new Double[0]);
				} else if ("mem".equals(type)) {
					y = (Double[]) vmGlobalDao.queryMemTopClu(map).toArray(new Double[0]);
				} else {
					y = (Double[]) vmGlobalDao.queryStoreTopClu(map).toArray(new Double[0]);
				}
			}
		}

		return y;
	}

	/**
	 * 
	 * @Title: queryXData
	 * @Description: 查询x值
	 * @param
	 * @return Double[]
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 19, 2013 10:45:05 AM
	 */
	public String[] queryXData(Map map) {
		String entity_type = (String) map.get("entity_type");
		String type = (String) map.get("type");
		Object uuid = map.get("uuid");
		String[] y = new String[0];
		if ("1".equals(entity_type)) {// 主机
			if (uuid == null) {
				if ("cpu".equals(type)) {
					y = (String[]) hostGlobalDao.queryCpuXData(map).toArray(new String[0]);
				} else if ("mem".equals(type)) {
					y = (String[]) hostGlobalDao.queryMemXData(map).toArray(new String[0]);
				} else {
					y = (String[]) hostGlobalDao.queryStoreXData(map).toArray(new String[0]);
				}
			} else {
				if ("cpu".equals(type)) {
					y = (String[]) hostGlobalDao.queryCpuXDataClu(map).toArray(new String[0]);
				} else if ("mem".equals(type)) {
					y = (String[]) hostGlobalDao.queryMemXDataClu(map).toArray(new String[0]);
				} else {
					y = (String[]) hostGlobalDao.queryStoreXDataClu(map).toArray(new String[0]);
				}
			}

		} else if ("2".equals(entity_type)) {// 虚拟机
			if (uuid == null) {
				if ("cpu".equals(type)) {
					y = (String[]) vmGlobalDao.queryCpuXData(map).toArray(new String[0]);
				} else if ("mem".equals(type)) {
					y = (String[]) vmGlobalDao.queryMemXData(map).toArray(new String[0]);
				} else {
					y = (String[]) vmGlobalDao.queryStoreXData(map).toArray(new String[0]);
				}
			} else {
				if ("cpu".equals(type)) {
					y = (String[]) vmGlobalDao.queryCpuXDataClu(map).toArray(new String[0]);
				} else if ("mem".equals(type)) {
					y = (String[]) vmGlobalDao.queryMemXDataClu(map).toArray(new String[0]);
				} else {
					y = (String[]) vmGlobalDao.queryStoreXDataClu(map).toArray(new String[0]);
				}
			}
		}

		return y;
	}

	/**
	 * 查询告警topN对应的y值
	 */
	@Override
	public Double[] queryAlarmYData(Map<String, Integer> map) {
		Double[] y = new Double[0];
		y = (Double[]) alarmGlobalDao.queryTopAlarm(map).toArray(new Double[0]);
		return y;
	}

	/**
	 * 查询告警topN对应的x值
	 */
	@Override
	public String[] queryAlarmXData(Map<String, Integer> map) {
		String[] x = new String[0];
		x = (String[]) alarmGlobalDao.queryAlarmXData(map).toArray(new String[0]);
		return x;
	}

	/**
	 * 
	 * @Title: queryTopList
	 * @Description: 查询topN所对应的列表
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 24, 2013 8:54:54 AM
	 */
	public List queryTopList(Map map) {
		String entity_type = (String) map.get("entity_type");
		String type = (String) map.get("type");
		Object uuid = map.get("uuid");
		List list = new ArrayList();
		if ("1".equals(entity_type)) {// 主机
			if (uuid == null) {
				if ("cpu".equals(type)) {
					list = hostGlobalDao.queryCPUList(map);
				} else if ("mem".equals(type)) {
					list = hostGlobalDao.queryMemList(map);
				} else {
					list = hostGlobalDao.queryStoreList(map);
				}

			} else {
				if ("cpu".equals(type)) {
					list = hostGlobalDao.queryCPUListClu(map);
				} else if ("mem".equals(type)) {
					list = hostGlobalDao.queryMemListClu(map);
				} else {
					list = hostGlobalDao.queryStoreListClu(map);
				}

			}

		} else if ("2".equals(entity_type)) {// 虚拟机
			if (uuid == null) {
				if ("cpu".equals(type)) {
					list = vmGlobalDao.queryCPUList(map);
				} else if ("mem".equals(type)) {
					list = vmGlobalDao.queryMemList(map);
				} else {
					list = vmGlobalDao.queryStoreList(map);
				}
			} else {
				if ("cpu".equals(type)) {
					list = vmGlobalDao.queryCPUListClu(map);
				} else if ("mem".equals(type)) {
					list = vmGlobalDao.queryMemListClu(map);
				} else {
					list = vmGlobalDao.queryStoreListClu(map);
				}
			}
		}

		return list;
	}

}
