package com.sitech.basd.busimanager.service.busitree;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.basd.busimanager.dao.busitree.BusiManagerTreeDao;
import com.sitech.basd.busimanager.domain.busitree.BusiManagerTree;
import com.sitech.basd.fusioncharts.vo.Categories;
import com.sitech.basd.fusioncharts.vo.Category;
import com.sitech.basd.fusioncharts.vo.Chart;
import com.sitech.basd.fusioncharts.vo.Data;
import com.sitech.basd.fusioncharts.vo.Dataset;
import com.sitech.basd.fusioncharts.vo.FusionCharts;
import com.sitech.basd.resource.domain.top.TopTargetObj;
import com.sitech.basd.sxcloud.cloud.domain.vmhost.VMHostObj;
import com.sitech.basd.util.PropertyUtil;
 
/**
 * 
 * <p>
 * Title: busiManagerTreeDao
 * </p>
 * <p>
 * Description: 业务系统树业务处理类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author huojla
 * @version 1.0
 * @createtime 2013-5-20 下午1:44:34
 * 
 */
@Service("busiManagerTreeService")
public class BusiManagerTreeServiceImpl implements BusiManagerTreeService {
	@Autowired
	private BusiManagerTreeDao busiManagerTreeDao;
	@Autowired
	private PropertyUtil bsTreeIconProp;

	@Autowired
	private PropertyUtil unitedTreeIconProp;

	/**
	 * 
	 * @Title: queryForTree
	 * @Description: 查询业务中心，业务系统，应用等生成树
	 * @param
	 * @return List<BusiManagerTree>
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-5-20 下午1:48:23
	 */
	public List<BusiManagerTree> queryForTree(BusiManagerTree obj) {
		return busiManagerTreeDao.queryForTree(obj);
	}

	/**
	 * 
	 * @Title: queryForLimitTree
	 * @Description: 查询业务中心，业务系统，应用等生成权限树
	 * @param
	 * @return List<BusiManagerTree>
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-5-20 下午1:48:23
	 */
	public List<BusiManagerTree> queryForLimitTree(BusiManagerTree obj) {
		return busiManagerTreeDao.queryForLimitTree(obj);
	}

	/**
	 * 
	 * @Title: insertBusiManagerTree
	 * @Description: 向业务中心的树中插入数据
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-5-20 下午1:48:30
	 */
	public void insertBusiManagerTree(BusiManagerTree obj) throws Exception {
		busiManagerTreeDao.insertBusiManagerTree(obj);
	}

	/**
	 * 
	 * @Title: initBusiSysTreelist
	 * @Description: 实例树业务系统树权限数据List
	 * @param
	 * @return List<BusiSysTree>
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-5-21 上午11:24:41
	 */
	public List<BusiManagerTree> initBusiSysTreelist(String id, String username,
			List<BusiManagerTree> resultList) {
		List<BusiManagerTree> list = new ArrayList<BusiManagerTree>();
		BusiManagerTree tempObj = new BusiManagerTree();
		for (BusiManagerTree obj : resultList) {
			BusiManagerTree tObj = new BusiManagerTree();
			tObj.setId(obj.getId());
			tObj.setName(obj.getName());
			tObj.setType(obj.getType());
			tObj.setEntity_id(obj.getEntity_id());
			tObj.setConnect_id(obj.getConnect_id());
			tempObj.setParent_id(obj.getId());
			/*
			 * if (!"1".equals(id)) { if (1 == (obj.getType())) {
			 * tempObj.setUser_id(id); } }
			 */
			// 判断是不是父节点
			List<BusiManagerTree> lst = queryForTree(tempObj);
			if (lst == null || lst.size() == 0) {
				tObj.setIsParent(false);
			} else {
				tObj.setIsParent(true);
			}
			// 设置图标
			if (obj.getType() == 0) {// 业务中心
				tObj.setIcon(bsTreeIconProp.getString("busi.sys.center.png"));
				tObj.setNocheck(true);
			} else if (obj.getType() == 1) { // 一级业务系统
				tObj.setIcon(bsTreeIconProp.getString("busi.sys.png"));
				tObj.setNocheck(true);
			} else if (obj.getType() == 2) { // 二级业务系统
				tObj.setIcon(bsTreeIconProp.getString("sys.app.png"));
				tObj.setNocheck(true);
			} else if (obj.getType() == 3) { // 业务资源
				tObj.setIcon(bsTreeIconProp.getString("app.deploy.png"));
				if ("0".equals(obj.getState())) {// 从数据库中查询是否是停止状态
					tObj.setIcon(unitedTreeIconProp.getString("vm_stop"));
					tObj.setState("1");// 关机状态
				} else if ("1".equals(obj.getState())) {
					tObj.setIcon(unitedTreeIconProp.getString("vm_run"));
					tObj.setState("2");// 运行状态
				} else if ("3".equals(obj.getState())) {
					tObj.setIcon(unitedTreeIconProp.getString("vm_supend"));
					tObj.setState("3");// 挂起状态
				} else {
					tObj.setIcon(unitedTreeIconProp.getString("vm_noexist"));
					tObj.setState("4");// 虚拟机不存在
				}
				tObj.setNocheck(true);
			} else if(obj.getType() == 5){
				//此处可以考虑根据主机的状态，显示不同的图片
				tObj.setIcon(bsTreeIconProp.getString("host.png"));
				tObj.setNocheck(true);
			}
			list.add(tObj);
		}
		return list;
	}

	/**
	 * 
	 * @Title: deleteBusiManagerTreeById
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param
	 * @return int
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-5-22 下午2:30:11
	 */
	public int deleteBusiManagerTreeById(BusiManagerTree obj) {
		return busiManagerTreeDao.deleteBusiManagerTreeById(obj);
	}

	/**
	 * 
	 * @Title: updateTreeNode
	 * @Description: 更新树节点信息
	 * @param
	 * @return int
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime May 30, 2013 6:54:45 PM
	 */
	public int updateBusiManagerTreeByObj(BusiManagerTree obj) {
		return busiManagerTreeDao.updateBusiManagerTreeByObj(obj);
	}

	/**
	 * 
	 * @Title: queryBusiCenterSonNodesNum
	 * @Description: 查询业务中心下各类型子节点的个数
	 * @param
	 * @return BusiManagerTree
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Jun 22, 2013 11:39:18 AM
	 */
	public BusiManagerTree queryBusiCenterSonNodesNum(BusiManagerTree obj) {
		return busiManagerTreeDao.queryBusiCenterSonNodesNum(obj);
	}

	/**
	 * @Title: countByObj
	 * @Description: 统计业务系统下各子节点个数
	 * @param
	 * @return int
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-14 下午5:41:08
	 */
	@Override
	public int countByObj(BusiManagerTree BusiManagerTree) {
		// TODO Auto-generated method stub
		return busiManagerTreeDao.countByObj(BusiManagerTree);
	}

	/**
	 * @Title: queryForListByParentIdList
	 * @Description: 根据节点类型和父节点的id集合查询节点集合
	 * @param
	 * @return List<BusiManagerTree>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-19 下午2:48:39
	 */
	@Override
	public List<BusiManagerTree> queryForListByParentIdList(BusiManagerTree BusiManagerTree) {
		return busiManagerTreeDao.queryForListByParentIdList(BusiManagerTree);
	}

	/**
	 * 
	 * @Title: validateDelete
	 * @Description: 验证能否被删除
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-8-23 下午7:30:51
	 */
	public Boolean validateDelete(String id, String type) {
		boolean result = false;
		BusiManagerTree sysTreeObj = new BusiManagerTree();
		sysTreeObj.setParent_id(id);
		if (type.equals("0")) {
			sysTreeObj.setType(1);
		} else if (type.equals("1")) {
			sysTreeObj.setType(2);
		} else if (type.equals("2")) {
			sysTreeObj.setType(3);
		}
		List lst = busiManagerTreeDao.queryForTree(sysTreeObj);
		if (lst == null || lst.size() < 1) {
			result = true;
		}
		return result;
	}

	@Override
	public Map<Long, Long> getTypeCounts(BusiManagerTree obj) {
		return busiManagerTreeDao.getTypeCounts(obj);
	}

	/**
	 * @Title: queryForVmList
	 * @Description: 获取对应的虚拟机列表（去掉已存在的）
	 * @param
	 * @return List
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime 2013-9-13 下午2:25:16
	 */
	public List<VMHostObj> queryForVmList(VMHostObj obj) {
		return busiManagerTreeDao.queryForVmList(obj);
	}

	/**
	 * 
	 * @Title: queryVmTopN
	 * @Description: 查询虚拟机TopN
	 * @param
	 * @return FusionCharts
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2013-9-14 上午9:30:25
	 */
	public FusionCharts queryVmTopN(Map<String, Object> map) throws Exception {
		FusionCharts fusionCharts = new FusionCharts();
		// 设置报表基本参数
		Chart chart = new Chart();
		List<Data> dataList = new ArrayList<Data>();
		List<Data> dataList1 = new ArrayList<Data>();
		String type = (String) map.get("type");
		Integer top_num = (Integer) map.get("top_num");
		if ("cpu".equals(type)) {
			dataList = busiManagerTreeDao.queryVmCpuTopN(map);
			dataList1 = busiManagerTreeDao.queryVmCpuTopN2(map);
			if (dataList1 != null) {
				dataList.addAll(dataList1);
			}
			Collections.sort(dataList);
			Collections.reverse(dataList);
			chart.setCaption("虚拟机Cpu Top N 报表");
			chart.setXaxisname("虚拟机名称");
			chart.setYaxisname("Cpu使用量");
			chart.setNumberSuffix("%");
		} else if ("mem".equals(type)) {
			dataList = busiManagerTreeDao.queryVmMemTopN(map);
			dataList1 = busiManagerTreeDao.queryVmMemTopN2(map);
			if (dataList1 != null) {
				dataList.addAll(dataList1);
			}
			Collections.sort(dataList);
			Collections.reverse(dataList);
			chart.setCaption("虚拟机内存 Top N 报表");
			chart.setXaxisname("虚拟机名称");
			chart.setYaxisname("内存使用量");
			chart.setNumberSuffix("%");
		} else {
			dataList = busiManagerTreeDao.queryVmStoreTopN(map);
			chart.setCaption("虚拟机存储 Top N 报表");
			chart.setXaxisname("虚拟机名称");
			chart.setYaxisname("存储使用量");
			chart.setNumberSuffix("%");
		}
		if (dataList.size() > top_num) {
			dataList = dataList.subList(0, top_num);
		}
		fusionCharts.setChart(chart);
		fusionCharts.setData(dataList);
		return fusionCharts;
	}

	/**
	 * 
	 * @Title: queryTopList
	 * @Description: 获取TopN实体列表
	 * @param
	 * @return List
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-9-15 下午3:20:24
	 */
	public List queryTopList(Map<String, Object> map) throws Exception {
		String type = (String) map.get("type");
		Integer top_num = (Integer) map.get("top_num");
		List<TopTargetObj> list = new ArrayList<TopTargetObj>();
		List<TopTargetObj> list2 = new ArrayList<TopTargetObj>();
		if ("cpu".equals(type)) {
			list = busiManagerTreeDao.queryVmCpuList(map);
			list2 = busiManagerTreeDao.queryVmCpuList2(map);
			if (list != null) {
				list.addAll(list2);
			}
			Collections.sort(list, new Comparator<TopTargetObj>() {
				@Override
				public int compare(TopTargetObj o1, TopTargetObj o2) {
					return String.valueOf(o1.getCpu_usage()).compareTo(
							String.valueOf(o2.getCpu_usage()));
				}
			});
			Collections.reverse(list);
		} else if ("mem".equals(type)) {
			list = busiManagerTreeDao.queryVmMemList(map);
			list2 = busiManagerTreeDao.queryVmMemList2(map);
			if (list != null) {
				list.addAll(list2);
			}
			Collections.sort(list, new Comparator<TopTargetObj>() {
				@Override
				public int compare(TopTargetObj o1, TopTargetObj o2) {
					return String.valueOf(o1.getMem_usage()).compareTo(
							String.valueOf(o2.getMem_usage()));
				}
			});
			Collections.reverse(list);
		} else {
			// 待开发 存储使用率
			// list = busiManagerTreeDao.queryStoreList(map);
		}
		if (list.size() > top_num) {
			return list.subList(0, top_num);
		}
		return list;
	}

	/**
	 * 
	 * @Title: querySysVmTopN
	 * @Description: 查询虚拟机TopN
	 * @param
	 * @return FusionCharts
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2013-9-14 上午9:30:25
	 */
	public FusionCharts querySysVmTopN(Map<String, Object> map) throws Exception {
		FusionCharts fusionCharts = new FusionCharts();
		// 设置报表基本参数
		Chart chart = new Chart();
		List<Data> dataList = new ArrayList<Data>();
		List<Data> dataList1 = new ArrayList<Data>();
		String type = (String) map.get("type");
		Integer top_num = (Integer) map.get("top_num");
		if ("cpu".equals(type)) {
			dataList = busiManagerTreeDao.querySysVmCpuTopN(map);
			dataList1 = busiManagerTreeDao.queryVmCpuTopN1(map);
			if (dataList1 != null) {
				dataList.addAll(dataList1);
			}
			Collections.sort(dataList);
			Collections.reverse(dataList);
			chart.setCaption("虚拟机Cpu Top N 报表");
			chart.setXaxisname("虚拟机名称");
			chart.setYaxisname("Cpu使用量");
			chart.setNumberSuffix("%");
		} else if ("mem".equals(type)) {
			dataList = busiManagerTreeDao.querySysVmMemTopN(map);
			dataList1 = busiManagerTreeDao.queryVmMemTopN1(map);
			if (dataList1 != null) {
				dataList.addAll(dataList1);
			}
			Collections.sort(dataList);
			Collections.reverse(dataList);
			chart.setCaption("虚拟机内存 Top N 报表");
			chart.setXaxisname("虚拟机名称");
			chart.setYaxisname("内存使用量");
			chart.setNumberSuffix("%");
		} else {// 暂时无
			/*
			 * dataList = busiManagerTreeDao.queryVmStoreTopN(map);
			 * chart.setCaption("虚拟机存储 Top N 报表"); chart.setXaxisname("虚拟机名称");
			 * chart.setYaxisname("存储使用量"); chart.setNumberSuffix("%");
			 */
		}
		if (dataList.size() > top_num) {
			dataList = dataList.subList(0, top_num);
		}
		fusionCharts.setChart(chart);
		fusionCharts.setData(dataList);
		return fusionCharts;
	}

	/**
	 * 
	 * @Title: querySysTopList
	 * @Description: 获取TopN实体列表
	 * @param
	 * @return List
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime 2013-9-15 下午3:20:24
	 */
	public List querySysTopList(Map<String, Object> map) throws Exception {
		String type = (String) map.get("type");
		Integer top_num = (Integer) map.get("top_num");
		List<TopTargetObj> list = new ArrayList<TopTargetObj>();
		List<TopTargetObj> list2 = new ArrayList<TopTargetObj>();
		if ("cpu".equals(type)) {
			list = busiManagerTreeDao.querySysVmCpuList(map);
			list2 = busiManagerTreeDao.queryVmCpuList1(map);
			if (list != null && list.size() > 0) {
				list.addAll(list2);
			}
			Collections.sort(list, new Comparator<TopTargetObj>() {
				@Override
				public int compare(TopTargetObj o1, TopTargetObj o2) {
					return String.valueOf(o1.getCpu_usage()).compareTo(
							String.valueOf(o2.getCpu_usage()));
				}
			});
			Collections.reverse(list);
		} else if ("mem".equals(type)) {
			list = busiManagerTreeDao.querySysVmMemList(map);
			list2 = busiManagerTreeDao.queryVmMemList1(map);
			if (list != null && list.size() > 0) {
				list.addAll(list2);
			}
			Collections.sort(list, new Comparator<TopTargetObj>() {
				@Override
				public int compare(TopTargetObj o1, TopTargetObj o2) {
					return String.valueOf(o1.getMem_usage()).compareTo(
							String.valueOf(o2.getMem_usage()));
				}
			});
			Collections.reverse(list);
		} else {
			// 待开发 存储使用率
			// list = busiManagerTreeDao.queryStoreList(map);
		}
		if (list.size() > top_num) {
			return list.subList(0, top_num);
		}
		return list;
	}

	@Override
	public Map<String, Long> getTypeCountsByPoint(BusiManagerTree obj) {
		List<BusiManagerTree> objs = busiManagerTreeDao.queryAllStatisticsCount(obj);
		Map<String, Long> maps = new HashMap<String, Long>();
		Set<String> set = new HashSet<String>();
		Set<String> set1 = new HashSet<String>();
		Set<String> set2 = new HashSet<String>();
		Set<String> set3 = new HashSet<String>();
		Set<String> set4 = new HashSet<String>();
		for (int i = 0; i < objs.size(); i++) {
			BusiManagerTree temp = objs.get(i);
			if (temp.getId() != null) {
				set.add(temp.getId());
			}
			if (temp.getId1() != null) {
				set1.add(temp.getId1());
			}
			if (temp.getId2() != null) {
				set2.add(temp.getId2());
			}
			if (temp.getId3() != null && !"4".equals(temp.getType3())) {// 若为存在承载业务，则跳过
				set3.add(temp.getId3());
			}
			if (temp.getId4() != null) {
				set4.add(temp.getId4());
			}
		}
		maps.put("0", set.size() + 0L);
		maps.put("1", set1.size() + 0L);
		maps.put("2", set2.size() + 0L);
		maps.put("3", set3.size() + 0L);
		maps.put("4", set4.size() + 0L);
		return maps;
	}

	/**
	 * 
	 * @Title: queryVmBySubSystem
	 * @Description: 通过业务子系统查询其对应的虚拟机列表
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2013-10-11 下午3:34:06
	 */
	public List queryVmBySubSystem(BusiManagerTree busi) {
		List vmList = new ArrayList();
		BusiManagerTree bu = null;
		List<BusiManagerTree> sub = busiManagerTreeDao.queryForTree(busi);
		if (sub != null && sub.size() > 0) {
			bu = sub.get(0);// 暂时默认业务子系统的名字是不可以重复的
		}
		if (bu != null) {
			BusiManagerTree parent = new BusiManagerTree();
			parent.setParent_id(bu.getId());
			vmList = busiManagerTreeDao.queryForTree(parent);// 返回虚拟机列表
		}
		return vmList;
	}

	/**
	 * 
	 * @Title: getExpandNodes
	 * @Description: 获取展开节点集合
	 * @param
	 * @return String
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime 2013-9-13 下午5:37:04
	 */
	public List getExpandNodes(BusiManagerTree obj) {
		List list = new ArrayList<BusiManagerTree>();
		try {
			list = busiManagerTreeDao.queryExpandNodes(obj);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 
	 * @Title: getExpandNodes
	 * @Description: 获取展开节点集合
	 * @param
	 * @return String
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime 2013-9-13 下午5:37:04
	 */
	public List getExpandNodes1(BusiManagerTree obj) {
		List list = new ArrayList<BusiManagerTree>();
		try {
			list = busiManagerTreeDao.queryExpandNodes1(obj);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 
	 * @Title: queryExpandNodesForBusi
	 * @Description: 查询展开树所需结点（子业务）
	 * @param
	 * @return List
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2013-9-14 上午9:30:25
	 */
	public List queryExpandNodesForBusi(BusiManagerTree busi) {
		List list = new ArrayList<BusiManagerTree>();
		try {
			list = busiManagerTreeDao.queryExpandNodesForBusi(busi);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public FusionCharts queryChartDayData(List<BusiManagerTree> objs, String caption) {
		FusionCharts charts = new FusionCharts();
		Chart chart = new Chart();
		chart.setCaption(caption);
		chart.setPalette("2");
		chart.setShowvalues("0");
		chart.setDecimals("0");
		chart.setShowborder("1");
		chart.setPlotGradientColor("");
		chart.setCanvasPadding("0");
		chart.setBgcolor("#F4F4F4");
		charts.setChart(chart);
		// 横坐标
		List<Categories> categoriesLst = new ArrayList<Categories>();
		Categories categories = new Categories();
		List<Category> lst = new ArrayList<Category>();
		String[] strs = new String[] { "子系统数量", "虚拟机数量" };
		for (int i = 0; i < 2; i++) {
			Category category = new Category();
			category.setLabel(strs[i]);
			lst.add(category);
		}

		categories.setCategory(lst);
		categoriesLst.add(categories);
		charts.setCategories(categoriesLst);
		List<Dataset> datasets = new ArrayList<Dataset>();

		for (BusiManagerTree o : objs) {
			Dataset dataset = new Dataset();
			String name = o.getName();
			dataset.setSeriesname(name);
			BusiManagerTree obj1 = new BusiManagerTree();
			obj1.setId1(o.getId());
			Map<String, Long> maps = getTypeCountsByPoint(obj1);
			long sCount = maps.get("2") == null ? 0 : maps.get("2");
			long rCount = maps.get("3") == null ? 0 : maps.get("3");
			List<Data> datas = new ArrayList<Data>();
			Data d = new Data();
			d.setValue(sCount + "");
			Data d1 = new Data();
			d1.setValue(rCount + "");
			datas.add(d);
			datas.add(d1);
			dataset.setData(datas);
			datasets.add(dataset);
		}
		charts.setDataset(datasets);
		return charts;
	}

	/**
	 * 
	 * @see com.sitech.basd.busimanager.service.busitree.BusiManagerTreeService#getVmhostListByBusiId(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public List getVmhostListByBusiId(String id, String type) {
		if (type == null || id == null) {
			return null;
		}
		List list = new ArrayList();
		// 子业务
		this.getBusiTreeList(id, type, list);
		return list;
	}

	private void getBusiTreeList(String id, String type, List<VMHostObj> list) {
		List<BusiManagerTree> busiList = busiManagerTreeDao.getBusiTreeList(id, type);
		for (BusiManagerTree bt : busiList) {
			if (bt.getType() == 2) {
				busiManagerTreeDao.getVmhostListByBusiId(bt, list);
			} else {
				getBusiTreeList(bt.getId(), String.valueOf(bt.getType()), list);
			}
		}
	}

	/**
	 * 
	 * @see com.sitech.basd.busimanager.service.busitree.BusiManagerTreeService#getOutPutStreamByVmList(java.util.List,
	 *      java.io.ByteArrayOutputStream)
	 */
	@Override
	public void getOutPutStreamByVmList(List<Map<String, Object>> list, ByteArrayOutputStream os) {
		/* Create workbook */
		HSSFWorkbook workbook = new HSSFWorkbook();
		try {
			/* Create Title Font */
			Font fontTitle = workbook.createFont();
			fontTitle.setFontHeightInPoints((short) 10);
			fontTitle.setBoldweight(Font.BOLDWEIGHT_BOLD);

			/* Create Data Font */
			Font fontData = workbook.createFont();
			fontData.setFontHeightInPoints((short) 9);
			fontData.setBoldweight(Font.BOLDWEIGHT_NORMAL);

			/* Create Title Style */
			CellStyle cellStyleTitle = workbook.createCellStyle();
			cellStyleTitle.setBorderLeft(CellStyle.BORDER_THIN);
			cellStyleTitle.setBorderRight(CellStyle.BORDER_THIN);
			cellStyleTitle.setBorderTop(CellStyle.BORDER_THIN);
			cellStyleTitle.setBorderBottom(CellStyle.BORDER_THIN);
			cellStyleTitle.setAlignment(CellStyle.ALIGN_CENTER);
			cellStyleTitle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
			cellStyleTitle.setWrapText(true);
			cellStyleTitle.setFont(fontTitle);

			/* Create Data Style */
			CellStyle cellStyleData = workbook.createCellStyle();
			cellStyleData.setBorderLeft(CellStyle.BORDER_THIN);
			cellStyleData.setBorderRight(CellStyle.BORDER_THIN);
			cellStyleData.setBorderTop(CellStyle.BORDER_THIN);
			cellStyleData.setBorderBottom(CellStyle.BORDER_THIN);
			cellStyleData.setAlignment(CellStyle.ALIGN_RIGHT);
			cellStyleData.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
			cellStyleData.setFont(fontData);

			Sheet st = workbook.createSheet("Sheet1");
			// 设置头部
			Map map = new HashMap();
			map.put("businame", "子系统");
			map.put("VH_NAME", "虚拟机名称");
			map.put("VH_IP", "虚拟机IP");
			map.put("VH_TYPE", "虚拟机类型");
			map.put("VH_SYSTEM", "操作系统");
			map.put("VH_CPU", "CPU(核)");
			map.put("VH_MEM", "内存(G)");
			map.put("VH_STAT", "虚拟机状态");
			String[] keys = new String[] { "businame", "VH_NAME", "VH_IP", "VH_TYPE",
					"VH_SYSTEM", "VH_CPU", "VH_MEM", "VH_STAT" };
			// data head
			if(true){				
				Row row = st.createRow(0);
				row.setHeightInPoints(30);
				for(int i=0;i<keys.length;i++){
					String key = keys[i];
					String val = "";
					if(map.get(key)!=null){
						val = String.valueOf(map.get(key));
					}
					Cell cell = row.createCell(i);
					// cell.setEncoding(HSSFCell.ENCODING_UTF_16);
					cell.setCellStyle(cellStyleTitle);
					cell.setCellValue(val);
				}
			}
			// data left & data
			for(int i=0;i<list.size();i++){
				Row row = st.createRow(i+1);
				row.setHeightInPoints(15);
				Map<String, Object> datamap = list.get(i);
				for(int j=0;j<keys.length;j++){
					String key = keys[j];
					String val = "";
					if(datamap.get(key)!=null){
						val = String.valueOf(datamap.get(key));
					}
					Cell cell = row.createCell(j);
					// cell.setEncoding(HSSFCell.ENCODING_UTF_16);
					cell.setCellStyle(cellStyleData);
					cell.setCellValue(val);
				}
			}
			st.autoSizeColumn((short)0); //调整第一列宽度
			st.autoSizeColumn((short)1); //调整第二列宽度
			st.autoSizeColumn((short)2); //调整第三列宽度
			st.autoSizeColumn((short)3); //调整第四列宽度
			st.autoSizeColumn((short)4); //调整第四列宽度
			st.autoSizeColumn((short)5); //调整第四列宽度
			st.autoSizeColumn((short)6); //调整第四列宽度
			st.autoSizeColumn((short)7); //调整第四列宽度
			workbook.write(os);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {

		}		
	}

	/**
	 *
	 * @see com.sitech.basd.busimanager.service.busitree.BusiManagerTreeService#queryForList(com.sitech.basd.busimanager.domain.busitree.BusiManagerTree)
	 */
	@Override
	public List<BusiManagerTree> queryForList(BusiManagerTree obj) {
		return busiManagerTreeDao.queryForList(obj);
	}
	
}
