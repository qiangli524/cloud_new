/**
 * SyncDataImpl.java
 * com.sitech.ssd.gx.sync
 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2013 十一月 21 		duangh
 *
 * Copyright (c) 2013, TNT All Rights Reserved.
 */

package com.sitech.ssd.gx.service.sync;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sitech.ssd.gx.constant.HuaweiRestURI;
import com.sitech.ssd.gx.dao.entitytree.HuaweiEntityTreeDao;
import com.sitech.ssd.gx.dao.huaweihost.HuaweiHostDao;
import com.sitech.ssd.gx.dao.huaweivm.HuaweiVMHostDao;
import com.sitech.ssd.gx.domain.huaweientitytree.HuaweiEntityTreeObj;
import com.sitech.ssd.gx.domain.huaweihost.HuaweiHostObj;
import com.sitech.ssd.gx.domain.huaweivm.HuaweiVmObj;
import com.sitech.ssd.gx.utils.HwEntityTypeConstant;
import com.sitech.utils.exception.HttpClientException;
import com.sitech.utils.http.HttpClientCustomUtil;
import com.sitech.utils.jackson.JacksonUtil;
import com.sitech.utils.randomid.RandomUUID;
import com.sitech.vo.huawei.Basic;
import com.sitech.vo.huawei.ClusterVO;
import com.sitech.vo.huawei.Clusters;
import com.sitech.vo.huawei.HostVO;
import com.sitech.vo.huawei.Hosts;
import com.sitech.vo.huawei.SiteVO;
import com.sitech.vo.huawei.Sites;
import com.sitech.vo.huawei.VMDetailVO;
import com.sitech.vo.huawei.VMS;

/**
 * ClassName:SyncDataImpl Function:调用云管理平台接口同步查询华为数据接口实现类
 * 
 * @author duangh
 * @version
 * @since Ver 1.0
 * @Date 2013 十一月 21 16:11:10
 * @see
 */
public class SyncHuaweiDataImpl implements SyncHuaweiData {
	private HuaweiEntityTreeDao huaweiEntityTreeDao;
	private HuaweiHostDao huaweiHostDao;
	private HuaweiVMHostDao huaweiVMHostDao;
	
	public void setHuaweiEntityTreeDao(HuaweiEntityTreeDao huaweiEntityTreeDao) {
		this.huaweiEntityTreeDao = huaweiEntityTreeDao;
	}

	public void setHuaweiHostDao(HuaweiHostDao huaweiHostDao) {
		this.huaweiHostDao = huaweiHostDao;
	}

	public void setHuaweiVMHostDao(HuaweiVMHostDao huaweiVMHostDao) {
		this.huaweiVMHostDao = huaweiVMHostDao;
	}

	/**
	 * 
	 * @Title: initRestEntities
	 * @Description: 实例化华为实体
	 * @param
	 * @return void
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-12-3 下午10:39:40
	 */
	public void initRestEntities() throws HttpClientException {
		List<SiteVO> voList = queryAllSite();
		for (SiteVO basicVO : voList) {
			String dcTreeId = RandomUUID.getUuid();
			//插入实体树数据中心数据
			HuaweiEntityTreeObj dataCenterTreeObj = new HuaweiEntityTreeObj();
			dataCenterTreeObj.setId(dcTreeId);
			dataCenterTreeObj.setName(basicVO.getName());
			dataCenterTreeObj.setCode(basicVO.getUrn());
			dataCenterTreeObj.setType(HwEntityTypeConstant.HUAWEI_DATACENTER);
			dataCenterTreeObj.setParent_id("0");
			huaweiEntityTreeDao.insertTreeNode(dataCenterTreeObj);
			
			String[] siteUrns = basicVO.getUrn().split(":");
			String siteId = siteUrns[siteUrns.length-1];
			
			/** 查询所有集群信息 */
			List<ClusterVO> clusterList = queryAllClusters(siteId);
			for (ClusterVO clusterVO : clusterList) {
				String clusterTreeId = RandomUUID.getUuid();
				//插入实体树集群数据
				HuaweiEntityTreeObj clusterTreeObj = new HuaweiEntityTreeObj();
				clusterTreeObj.setId(clusterTreeId);
				clusterTreeObj.setName(clusterVO.getName());
				clusterTreeObj.setCode(clusterVO.getUrn());
				clusterTreeObj.setDc_code(basicVO.getUrn());
				clusterTreeObj.setType(HwEntityTypeConstant.HUAWEI_CLUSTER);
				clusterTreeObj.setParent_id(dcTreeId);
				huaweiEntityTreeDao.insertTreeNode(clusterTreeObj);
			}
			/** 查询所有主机信息 */
			List<HostVO> hostList = queryAllHosts(siteId);
			for (HostVO hostVO : hostList) {
				String hostTreeId = RandomUUID.getUuid();
				//查询父节点
				HuaweiEntityTreeObj cluTreeObj = new HuaweiEntityTreeObj();
				cluTreeObj.setCode(hostVO.getClusterUrn());
				cluTreeObj.setDc_code(basicVO.getUrn());
				cluTreeObj = huaweiEntityTreeDao.queryTreeNode(cluTreeObj);
				//插入主机数据
				String hostId = RandomUUID.getUuid();
				HuaweiHostObj hostObj = new HuaweiHostObj(hostVO);
				hostObj.setId(hostId);
				huaweiHostDao.insertByObj(hostObj);
				//插入实体树主机数据
				HuaweiEntityTreeObj hostTreeObj = new HuaweiEntityTreeObj();
				hostTreeObj.setId(hostTreeId);
				hostTreeObj.setName(hostVO.getName());
				hostTreeObj.setCode(hostVO.getUrn());
				hostTreeObj.setDc_code(basicVO.getUrn());
				hostTreeObj.setCluser_code(cluTreeObj.getCode());
				hostTreeObj.setType(HwEntityTypeConstant.HUAWEI_HOST);
				hostTreeObj.setParent_id(cluTreeObj.getId());
				hostTreeObj.setEntity_id(hostId);
				huaweiEntityTreeDao.insertTreeNode(hostTreeObj);
			}
			/** 查询所有虚拟机信息 */
			List<VMDetailVO> vmList = queryAllVMs(siteId);
			for (VMDetailVO vmVO : vmList) {
				//查询父节点
				HuaweiEntityTreeObj cluTreeObj = new HuaweiEntityTreeObj();
				cluTreeObj.setCode(vmVO.getClusterUrn());
				cluTreeObj.setDc_code(basicVO.getUrn());
				cluTreeObj = huaweiEntityTreeDao.queryTreeNode(cluTreeObj);
				HuaweiEntityTreeObj hostTreeObj = new HuaweiEntityTreeObj();
				hostTreeObj.setCode(vmVO.getHostUrn());
				hostTreeObj.setDc_code(basicVO.getUrn());
				hostTreeObj = huaweiEntityTreeDao.queryTreeNode(hostTreeObj);
				//判断父节点
				String parentType = vmVO.getLocation().split(":")[3];
				String parantId = "";
				if(parentType.equals("hosts")){
					parantId = hostTreeObj.getId();
				}else if(parentType.equals("clusters")){
					parantId = cluTreeObj.getId();
				}
				String vmId = RandomUUID.getUuid();
				//插入虚拟机数据
				String vmTreeId = RandomUUID.getUuid();
				HuaweiVmObj vmObj = new HuaweiVmObj(vmVO);
				vmObj.setId(vmId);
				huaweiVMHostDao.insertByObj(vmObj);
				//插入实体树主机数据
				HuaweiEntityTreeObj vmTreeObj = new HuaweiEntityTreeObj();
				vmTreeObj.setId(vmTreeId);
				vmTreeObj.setName(vmVO.getName());
				vmTreeObj.setCode(vmVO.getUrn());
				vmTreeObj.setDc_code(basicVO.getUrn());
				vmTreeObj.setCluser_code(cluTreeObj.getCode());
				vmTreeObj.setHost_code(vmVO.getHostUrn());
				vmTreeObj.setEntity_id(vmId);
				if(vmVO.getIsTemplate()){
					vmTreeObj.setType(HwEntityTypeConstant.HUAWEI_IMAGE);
				}else{
					vmTreeObj.setType(HwEntityTypeConstant.HUAWEI_VM);
				}
				vmTreeObj.setParent_id(parantId);
				huaweiEntityTreeDao.insertTreeNode(vmTreeObj);
			}
		}
	}

	// 调用云平台rest接口查询华为站点信息，对应为数据中心
	public List<SiteVO> queryAllSite() throws HttpClientException {
		String siteRestURI = HuaweiRestURI.BASIC_URI + HuaweiRestURI.SITE_URI + HuaweiRestURI.SEPARATOR;
		Sites sites = HttpClientCustomUtil.get(siteRestURI, new JacksonUtil.TypeReference<Sites>() {
		});
		List<SiteVO> dcList = sites.getSites();
		return dcList;
	}

	// 调用云管理平台rest接口，同步查询集群信息
	public List<ClusterVO> queryAllClusters(String siteId) throws HttpClientException {
		String clustersURI = HuaweiRestURI.BASIC_URI + HuaweiRestURI.SITE_URI + HuaweiRestURI.SEPARATOR  + siteId + HuaweiRestURI.CLUSTER_URI + HuaweiRestURI.SEPARATOR;
		Clusters clusters = HttpClientCustomUtil.get(clustersURI,
				new JacksonUtil.TypeReference<Clusters>() {
				});
		List<ClusterVO> clusterList = clusters.getClusters();
		return clusterList;
	}

	// 调用云管理平台rest接口，同步查询下所有主机信息，主机信息中含有所属集群信息
	public List<HostVO> queryAllHosts(String siteId) throws HttpClientException {
		String hostURI = HuaweiRestURI.BASIC_URI + HuaweiRestURI.SITE_URI + HuaweiRestURI.SEPARATOR  + siteId + HuaweiRestURI.HOST_URI + HuaweiRestURI.SEPARATOR;
		Hosts hosts = HttpClientCustomUtil.get(hostURI, new JacksonUtil.TypeReference<Hosts>() {
		});
		List<HostVO> hostList = hosts.getHosts();
		return hostList;
	}

	// 调用云平台REST接口，同步查询所有虚拟机信息，虚拟机信息中包含所属主机信息
	public List<VMDetailVO> queryAllVMs(String siteId) throws HttpClientException {
		String vmURI = HuaweiRestURI.BASIC_URI + HuaweiRestURI.SITE_URI + HuaweiRestURI.SEPARATOR + siteId + HuaweiRestURI.VM_URI + HuaweiRestURI.SEPARATOR;
		VMS vms = HttpClientCustomUtil.get(vmURI, new JacksonUtil.TypeReference<VMS>() {
		});
		List<VMDetailVO> vmList = vms.getVms();
		return vmList;
	}
}
