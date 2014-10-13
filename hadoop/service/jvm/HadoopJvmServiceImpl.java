package service.jvm;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import service.tree.HadoopTreeService;
import util.HadoopConstant;

import com.sitech.basd.fusioncharts.vo.Categories;
import com.sitech.basd.fusioncharts.vo.Category;
import com.sitech.basd.fusioncharts.vo.Chart;
import com.sitech.basd.fusioncharts.vo.Data;
import com.sitech.basd.fusioncharts.vo.Dataset;
import com.sitech.basd.fusioncharts.vo.FusionCharts;

import dao.host.HadoopHostInfoDao;
import dao.jvm.HadoopJvmDao;
import dao.kpi.HadoopKpiDao;
import domain.host.HadoopHostInfoObj;
import domain.jvm.HadoopJvmObj;
import domain.kpi.HadoopKpiObj;
import domain.tree.HadoopTreeObj;

/**
 * 
 * <p>
 * Title: HadoopJvmDao
 * </p>
 * <p>
 * Description: jvm 相关操作
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author yanggl
 * @version 1.0
 * @createtime 2014-1-18 下午5:21:02
 * 
 */
@Service("hadoopJvmService")
public class HadoopJvmServiceImpl implements HadoopJvmService{
	@Autowired
	private HadoopJvmDao hadoopJvmDao;
	@Autowired
	private HadoopKpiDao hadoopKpiDao;
	@Autowired
	private HadoopTreeService hadoopTreeService;
	@Autowired
	private HadoopHostInfoDao hadoopHostInfoDao;

	/**
	 * 
	 * @Title: queryHadoopJvm
	 * @Description: jvm 图标
	 * @param
	 * @return FusionCharts
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-1-17 上午9:54:34
	 */
	@Override
	public FusionCharts queryHadoopJvm(HadoopJvmObj obj) {
		HadoopKpiObj kpiObj = new HadoopKpiObj();
		kpiObj.setKpi_id(obj.getKpi_id());
		kpiObj = hadoopKpiDao.queryByObj(kpiObj);
		FusionCharts fusionCharts = new FusionCharts();
		Chart chart = new Chart();
		chart.setCaption(kpiObj.getDescription());
		chart.setXaxisname("时间");
		String unit = null;
		if (null == kpiObj.getUnit() || "".equals(kpiObj.getUnit())) {
			unit = "次数";
		} else {
			unit = kpiObj.getUnit();
		}
		chart.setYaxisname("单位: " + unit);
		chart.setNumvisibleplot("1000");// 显示多少个点
		chart.setLabelstep("1000");// x轴坐标值的步长，即可以设置隔几个柱子显示一个值
		chart.setLabelDisplay("WRAP");// x轴坐标值的具体展现形式
		chart.setShowvalues("0");// 是否在图形上显示每根柱子具体的值
		chart.setCanvasPadding("10");// 图两边的空的距离
		chart.setFormatNumberScale("0");// 不格式化数字
		fusionCharts.setChart(chart);
		// 横坐标
		List<Categories> categoriesLst = new ArrayList<Categories>();
		List<Category> cList = new ArrayList<Category>();
		Categories categories = new Categories();
		List<Dataset> datasetList = new ArrayList<Dataset>();
		List<Data> datas = new ArrayList<Data>();
		Dataset dataset = new Dataset();
		dataset.setRenderas("Line");
		dataset.setAnchorRadius("4");
		dataset.setLinethickness("2");
		dataset.setColor("#75cc28");
		datas = hadoopJvmDao.queryHadoopJvm(obj);// 数据
		for(Data data:datas){
			Category c=new Category();
			c.setLabel(data.getLabel());
			cList.add(c);
		}
		categories.setCategory(cList);
		categoriesLst.add(categories);
		fusionCharts.setCategories(categoriesLst);
		dataset.setData(datas);
		datasetList.add(dataset);
		fusionCharts.setDataset(datasetList);
		return fusionCharts;
	}
	
	/**
	 * 
	 * @Title: queryLogHostCount
	 * @Description: Log中输出ERROR,FATAL的主机个数
	 * @param
	 * @return HadoopJvmObj
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @throws Exception 
	 * @createtime 2014-1-18 上午11:46:51
	 */
	@Override
	public int queryLogHostCount(HadoopJvmObj obj) throws Exception {
		List<HadoopJvmObj> logList = queryLogList(obj);
		int logCount = logList.size();
		return logCount;
	}
	
	/**
	 * 
	 * @Title: showHostList
	 * @Description: 展示主机列表
	 * @param
	 * @return List<HadoopHostInfoObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @throws Exception 
	 * @createtime 2014-1-18 下午4:03:12
	 */
	@Override
	public List<HadoopHostInfoObj> showHostList(HadoopJvmObj obj) throws Exception {
		List<HadoopJvmObj> logList = queryLogList(obj);
		List hostNameList = new ArrayList();
		List<HadoopHostInfoObj> hostList = new ArrayList<HadoopHostInfoObj>();
		if(logList!=null && logList.size()>0){
			HadoopHostInfoObj hostInfoObj = new HadoopHostInfoObj();
			for (HadoopJvmObj jvmObj : logList) {
				hostNameList.add(jvmObj.getHost_name());
			}
			hostInfoObj.setCluster_id(logList.get(0).getCluster_name());
			hostInfoObj.setHostNameList(hostNameList);
			hostInfoObj.setPagination(obj.getPagination());// 分页
			hostList = hadoopHostInfoDao.queryLogHostInfoList(hostInfoObj);
		}
		return hostList;
	}
	
	/**
	 * 
	 * @Title: queryLogList
	 * @Description: Log中输出ERROR,FATAL的主机集合
	 * @param
	 * @return List<HadoopJvmObj>
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @throws Exception 
	 * @createtime 2014-1-18 下午2:36:20
	 */
	@Override
	public List<HadoopJvmObj> queryLogList(HadoopJvmObj obj) throws Exception{
		HadoopTreeObj hadoopTreeObj = new HadoopTreeObj();
		hadoopTreeObj.setId(obj.getId());
		hadoopTreeObj.setParent_id(obj.getParent_id());
		List uuidList = new ArrayList();
		String cluster_uuid = this.acquireClusterName(hadoopTreeObj, 0);
		List<HadoopTreeObj> hostList = this.acquireChildNode(HadoopConstant.hostNode, null, hadoopTreeObj, new ArrayList<HadoopTreeObj>());//主机节点
		for(HadoopTreeObj treeObj:hostList){
			uuidList.add(treeObj.getUuid());
		}
		obj.setCluster_name(cluster_uuid);//集群表中的ID
		obj.setUuidList(uuidList);
		List<HadoopJvmObj> logList = hadoopJvmDao.queryLogList(obj);
		return logList;
	}
	
	
	/**
	 * @Title: countChildNode
	 * @Description: 递归计算子级节点个数，不单单是子节点
	 * @param
	 * @return int
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @throws Exception 
	 * @createtime 2014-1-9 下午7:22:04
	 */
	private List<HadoopTreeObj> acquireChildNode(String childNodeType, String parentNodeServiceType, HadoopTreeObj hadoopTreeObj,List<HadoopTreeObj> retList) throws Exception{
		try {
			HadoopTreeObj treeObj = new HadoopTreeObj();
			treeObj.setParent_id(hadoopTreeObj.getId());
			List<HadoopTreeObj> treeList = hadoopTreeService.queryForListByObj(treeObj);
			for (HadoopTreeObj htObj : treeList) {
				if (childNodeType.equals(htObj.getNode_type())) {
					if (parentNodeServiceType != null && !"".equals(parentNodeServiceType)) {
//						if (parentNodeServiceType.equals(hadoopTreeObj.getService_type())) {//主机节点实际上也录入了服务类型
						if (parentNodeServiceType.equals(htObj.getService_type())) {
							retList.add(htObj);
						}
					} else {
						retList.add(htObj);
					}
				} else {
					acquireChildNode(childNodeType, parentNodeServiceType, htObj, retList);
				}
			}
			return retList;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("递归查询子级节点错误，原因： " , e);
		}
	}
	/**
	 * @Title: acquireClusterName
	 * @Description: 递归获取集群的编号
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-14 下午7:22:44
	 */
	private String acquireClusterName(HadoopTreeObj hadoopTreeObj, int count) throws Exception {
		HadoopTreeObj treeObj = new HadoopTreeObj();
		count = count + 1;
		try {
			treeObj.setId(hadoopTreeObj.getParent_id());
			treeObj = hadoopTreeService.queryForListByObj(treeObj).get(0);
			if (HadoopConstant.hadoop_dc.equals(treeObj.getNode_type())) {
				return treeObj.getUuid();
			} else {
				if (count >= 6) {// 不能进入死循环，一定次数后强制终止
					return null;
				}
				return acquireClusterName(treeObj, count);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("递归获取集群名称出错，错误原因：", e);
		}
	}
}
