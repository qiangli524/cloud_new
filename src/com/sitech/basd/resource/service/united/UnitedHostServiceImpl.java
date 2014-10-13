package com.sitech.basd.resource.service.united;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.basd.resource.dao.united.UnitedTreeDao;
import com.sitech.basd.resource.domain.united.UnitedTreeObj;
import com.sitech.basd.resource.util.RevertEntity;
import com.sitech.basd.resource.util.VirtualClient;
import com.sitech.basd.sxcloud.cloud.dao.resource.HostInfoDao;
import com.sitech.basd.sxcloud.cloud.domain.resource.TbCloud2HostInfoObj;
import com.sitech.basd.yicloud.dao.datastore.DataStoreDao;
import com.sitech.basd.yicloud.domain.datastore.DataStoreObj;
import com.sitech.basd.yicloud.domain.vmauthority.VmAuthorityObj;
import com.sitech.basd.yicloud.service.vmauthority.VmAuthorityService;
import com.sitech.utils.exception.HttpClientException;
import com.sitech.utils.formater.DoubleFormater;
import com.sitech.utils.jackson.JacksonUtil;
import com.sitech.vo.united.DatastoreUnitedVO;
import com.sitech.vo.united.HostUnitedVO;
import com.sitech.vo.united.PortGroupUnitedVO;
import com.sitech.vo.util.UnitedConstant;
import com.sitech.vo.util.VirtualConstant;

@Service("unitedHostService")
public class UnitedHostServiceImpl implements UnitedHostService {
	private static final Logger LOG = Logger.getLogger(UnitedHostService.class);
	@Autowired
	private UnitedTreeDao unitedTreeDao;
	@Autowired
	private VmAuthorityService vmAuthorityService;
	@Autowired
	private HostInfoDao hostInfoDao;
	@Autowired
	private DataStoreDao dataStoreDao;

	/**
	 * 
	 * @Title: createHost
	 * @Description: 创建主机
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @throws HttpClientException
	 * @throws SQLException
	 * @createtime Jul 23, 2013 11:54:09 AM
	 */
	public String createHost(UnitedTreeObj obj, HostUnitedVO vo) throws HttpClientException,
			SQLException {
		String vtype = obj.getVtype();
		int userid = obj.getUser_id();
		String result = UnitedConstant.FAIL;
		String url = "";
		if (UnitedConstant.VMWARE.equals(vtype)) {
			url = "hostsystem/" + VirtualConstant.VT_VMWARE + "/create";
			vo = RevertEntity.toRestHost(obj, vo);
			HostUnitedVO vi = VirtualClient.post(url, vo,
					new JacksonUtil.TypeReference<HostUnitedVO>() {
					});
			if (vi.getIsSuccess()) {
				result = UnitedConstant.SUCCESS;
				UnitedTreeObj data = new UnitedTreeObj();
				data.setName(obj.getName());
				data.setVtype(UnitedConstant.UNALLOCATED);
				data = unitedTreeDao.queryByObj(data);
				// 将数据插入树表
				obj.setUuid(vi.getHostCode());
				obj.setType(UnitedConstant.HOST);

				if (data != null) {// 说明当前主机是作为未分配资源存在的
					obj.setId(data.getId());

					unitedTreeDao.updateObjById(obj);
				} else {
					unitedTreeDao.insertByObj(obj);
				}

				// 更新其在主机表中的分配状态
				TbCloud2HostInfoObj host = new TbCloud2HostInfoObj();
				host.setEq_ip(vo.getHostIp());
				/*
				 * 移除查询时未分配状态条件 host.setAllocated(0);
				 */
				host = hostInfoDao.queryByObj(host);
				host.setAllocated(1);
				host.setH_uuid(vi.getHostCode());
				host.setConnectId(obj.getConnect_id());
				host.setConnectStatus("connected");
				/*
				 * 暂时去除宿主机状态信息 host.setSTATUS("3");// 维护模式
				 */
				hostInfoDao.updateByObj(host);
				// 非admin用户，创建主机时需插入到entityuser表
				if (userid != 1) {
					VmAuthorityObj authorObj = new VmAuthorityObj();
					authorObj.setUSERID(userid);
					authorObj.setENTITY_NAME(obj.getName());
					authorObj.setENTITY_TYPE("3");// 主机的资源类型为3
					authorObj.setENTITY_ID(obj.getUuid());
					authorObj.setOPERAUTHORITY("0");// 给与创建主机人所有的操作权限
					authorObj.setTYPE(vtype);
					authorObj.setCONNECT_ID(obj.getConnect_id());
					vmAuthorityService.insertByObj(authorObj);
				}
			} else {
				result = result + vi.getLog();
			}
		} else if (UnitedConstant.XEN.equals(vtype)) {

		}
		return result;
	}

	/**
	 * 
	 * @Title: deleteHost
	 * @Description: 删除主机
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @throws SQLException
	 * @createtime Jul 27, 2013 10:50:17 AM
	 */
	public String deleteHost(UnitedTreeObj obj) throws HttpClientException, SQLException {
		String vtype = obj.getVtype();
		String result = UnitedConstant.FAIL;
		String url = "";
		HostUnitedVO vo = new HostUnitedVO();
		if (UnitedConstant.VMWARE.equals(vtype)) {
			url = "hostsystem/" + VirtualConstant.VT_VMWARE + "/delete/" + obj.getConnect_id()
					+ "/" + obj.getUuid();
			vo = VirtualClient.delete(url, new JacksonUtil.TypeReference<HostUnitedVO>() {
			});
			if (vo.getIsSuccess()) {
				result = UnitedConstant.SUCCESS;
				updateRelatedData(obj);
			} else {
				result = vo.getLog();
			}
		} else if (UnitedConstant.XEN.equals(vtype)) {

		}
		return result;
	}

	/**
	 * 
	 * @Title: getHostPortGroup
	 * @Description: 查看主机的网络信息
	 * @param
	 * @return
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-08-21
	 */
	public PortGroupUnitedVO getHostPortGroup(UnitedTreeObj obj) throws HttpClientException {
		PortGroupUnitedVO portGroupUnitedVO = new PortGroupUnitedVO();
		String vtype = "";
		if (obj.getVtype().equals(UnitedConstant.POWERVM)) {
			vtype = VirtualConstant.VT_IBM;
		} else if (obj.getVtype().equals(UnitedConstant.XEN)) {
			vtype = VirtualConstant.VT_XEN;
		} else if (obj.getVtype().equals(UnitedConstant.KVM)) {
			vtype = VirtualConstant.VT_KVM;
		} else {
			vtype = VirtualConstant.VT_VMWARE;
		}
		String url = "network/" + vtype + "/portgroup/get/" + obj.getConnect_id() + "/"
				+ obj.getUuid();
		portGroupUnitedVO = VirtualClient.get(url,
				new JacksonUtil.TypeReference<PortGroupUnitedVO>() {
				});
		return portGroupUnitedVO;
	}

	public VmAuthorityService getVmAuthorityService() {
		return vmAuthorityService;
	}

	public void setVmAuthorityService(VmAuthorityService vmAuthorityService) {
		this.vmAuthorityService = vmAuthorityService;
	}

	/**
	 * 
	 * @Title:
	 * @Description: 查询未分配资源的集群（暂时是选取第一个，后续根据规划需要改造）
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @throws SQLException
	 * @createtime Jul 27, 2013 10:50:17 AM
	 */
	public void updateRelatedData(UnitedTreeObj obj) {
		UnitedTreeObj cluster = new UnitedTreeObj();
		cluster.setVtype(UnitedConstant.UNALLOCATED);
		cluster.setType(UnitedConstant.CLUSTER);
		List<UnitedTreeObj> clusterList;
		try {
			LOG.info("查询未分配资源池的一个集群-----------");
			clusterList = unitedTreeDao.queryForUnitedTree(cluster);
			if (clusterList != null && clusterList.size() > 0) {
				cluster = clusterList.get(0);
			}
			obj.setParent_id(cluster.getId());
			obj.setVtype(UnitedConstant.UNALLOCATED);
			LOG.debug("更新树表的相应数据-----------");
			unitedTreeDao.updateObjById(obj);
			// 将主机的分配状态更新为未分配
			TbCloud2HostInfoObj host = new TbCloud2HostInfoObj();
			host.setH_uuid(obj.getUuid());
			host.setConnectId(obj.getConnect_id());
			host.setEq_name(obj.getName());
			host = hostInfoDao.queryByObj(host);
			if (host != null) {
				LOG.info("更新主机表的分配状态-----------");
				host.setAllocated(0);
				hostInfoDao.updateByObj(host);
			}

		} catch (SQLException e) {
			String log = "";
			LOG.error(log + e.getMessage(), e);
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * @Title: getHostDataStoreForXen
	 * @Description: xen查看主机的存储列表
	 * @param
	 * @return
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime 2013-08-21
	 */
	public List<DataStoreObj> getHostDataStoreForXen(DataStoreObj obj) throws Exception {
		List<DataStoreObj> list = new ArrayList<DataStoreObj>();
		String url = "datastore/" + VirtualConstant.VT_XEN + "/search/byhost/" + obj.getConnectId()
				+ "/" + obj.getHOST_ID();
		DatastoreUnitedVO result = VirtualClient.get(url,
				new JacksonUtil.TypeReference<DatastoreUnitedVO>() {
				});
		if (!result.isSuccess) {
			list = null;
			throw new RuntimeException(obj.getHOST_ID() + "@" + obj.getConnectId() + "获取存储错误！"
					+ result.log);
		} else {
			String types = "iso" + "," + "udev";// 过滤掉xen中的dvd和iso类型的存储
			if (result.getDatastoreList() != null && result.getDatastoreList().size() > 0) {
				for (DatastoreUnitedVO vo : result.getDatastoreList()) {
					if (types.contains(vo.getDatastoreType())) {
						continue;
					} else {
						DataStoreObj dObj = new DataStoreObj();
						dObj.setHOST_ID(vo.getHostCode());
						dObj.setConnectId(vo.getConnectCode());
						dObj.setNAME(vo.getDatastoreName());
						dObj.setTYPE(vo.getDatastoreType());
						dObj.setCAPACITY(String.valueOf(DoubleFormater.saven(
								vo.getCapacityInMb() / 1024, 2)));
						dObj.setFREE_SPACE(String.valueOf(DoubleFormater.saven(
								vo.getFreeSpaceInMb() / 1024, 2)));
						list.add(dObj);
					}
				}
			}
		}
		return list;
	}

	/**
	 * @Title: disConnectHost
	 * @Description: 断开主机连接
	 * @return String
	 * @throws
	 * @Date 2014-4-28 下午3:08:15
	 * @author lipp
	 * @param vtype
	 * @param hostUnitedVO
	 * @return
	 */
	@Override
	public String disConnectHost(String vtype, HostUnitedVO hostUnitedVO) {
		HostUnitedVO retVO = new HostUnitedVO();
		String result = "";
		String url = "";
		try {
			if (UnitedConstant.VMWARE.equals(vtype)) {
				url = "hostsystem/" + VirtualConstant.VT_VMWARE + "/disconnect";
			} else if (UnitedConstant.XEN.equals(vtype)) {
				url = "hostsystem/" + VirtualConstant.VT_XEN + "/disconnect";
			}
			retVO = VirtualClient.post(url, hostUnitedVO,
					new JacksonUtil.TypeReference<HostUnitedVO>() {
					});
			if (retVO.getIsSuccess()) {
				result = "success";
			} else {
				result = "Failed :" + retVO.getLog();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * @Title: changeHostPowerState
	 * @Description: 更改主机电源状态
	 * @throws
	 * @Date 2014-4-28 下午3:30:31
	 * @author lipp
	 * @param vtype
	 * @param hostUnitedVO
	 * @return
	 */
	@Override
	public String changeHostPowerState(String vtype, HostUnitedVO hostUnitedVO) {
		HostUnitedVO retVO = new HostUnitedVO();
		String result = "";
		String url = "";
		try {
			if (UnitedConstant.VMWARE.equals(vtype)) {
				url = "hostsystem/" + VirtualConstant.VT_VMWARE + "/change/powerstate";
			} else if (UnitedConstant.XEN.equals(vtype)) {
				url = "hostsystem/" + VirtualConstant.VT_XEN + "/change/powerstate";
			}
			retVO = VirtualClient.put(url, hostUnitedVO,
					new JacksonUtil.TypeReference<HostUnitedVO>() {
					});
			if (retVO.getIsSuccess()) {
				result = "success";
			} else {
				result = "Failed : " + retVO.getLog();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * @Title: enterAwaitModeHost
	 * @Description: 主机进入待机模式
	 * @throws
	 * @Date 2014-4-28 下午3:53:15
	 * @author lipp
	 * @param vtype
	 * @param hostUnitedVO
	 * @return
	 */
	@Override
	public String enterAwaitModeHost(String vtype, HostUnitedVO hostUnitedVO) {
		HostUnitedVO retVO = new HostUnitedVO();
		String result = "";
		String url = "";
		try {
			if (UnitedConstant.VMWARE.equals(vtype)) {
				url = "hostsystem/" + VirtualConstant.VT_VMWARE + "/toStandBy";
			} else if (UnitedConstant.XEN.equals(vtype)) {
				url = "hostsystem/" + VirtualConstant.VT_XEN + "/toStandBy";
			}
			retVO = VirtualClient.put(url, hostUnitedVO,
					new JacksonUtil.TypeReference<HostUnitedVO>() {
					});
			if (retVO.getIsSuccess()) {
				result = "success";
			} else {
				result = "Failed :" + retVO.getLog();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * @Title: exitAwaitModeHost
	 * @Description: 主机退出待机模式
	 * @throws
	 * @Date 2014-4-29 上午9:06:29
	 * @author lipp
	 * @param vtype
	 * @param hostUnitedVO
	 * @return
	 */
	@Override
	public String exitAwaitModeHost(String vtype, HostUnitedVO hostUnitedVO) {
		HostUnitedVO retVO = new HostUnitedVO();
		String result = "";
		String url = "";
		try {
			if (UnitedConstant.VMWARE.equals(vtype)) {
				url = "hostsystem/" + VirtualConstant.VT_VMWARE + "/fromStandBy";
			} else if (UnitedConstant.XEN.equals(vtype)) {
				url = "hostsystem/" + VirtualConstant.VT_XEN + "/fromStandBy";
			}
			retVO = VirtualClient.put(url, hostUnitedVO,
					new JacksonUtil.TypeReference<HostUnitedVO>() {
					});
			if (retVO.getIsSuccess()) {
				result = "success";
			} else {
				result = "Failed :" + retVO.getLog();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * @Title: saveHostDataStore
	 * @Description: 主机挂载存储
	 * @throws
	 * @Date 2014-6-1 下午14:55:42
	 * @author liwq
	 * @param vtype
	 * @param datastoreUnitedVO
	 * @return
	 */
	@Override
	public String saveHostDataStore(String vtype, DatastoreUnitedVO datastoreUnitedVO) {
		HostUnitedVO retVO = new HostUnitedVO();
		HostUnitedVO vo = new HostUnitedVO();
		vo.setDatastore(datastoreUnitedVO);
		String result = "";
		String url = "";
		try {
			if (UnitedConstant.VMWARE.equals(vtype)) {
				url = "hostsystem/" + VirtualConstant.VT_VMWARE + "/saveStore";
			} else if (UnitedConstant.XEN.equals(vtype)) {

			}
			retVO = VirtualClient.put(url, vo, new JacksonUtil.TypeReference<HostUnitedVO>() {
			});
			if (retVO.getIsSuccess()) {
				result = "success";
				// 入库操作
				DataStoreObj insertVo = new DataStoreObj();
				insertVo.setNAME(retVO.getDatastore().getDatastoreName());
				insertVo.setHOST_ID(datastoreUnitedVO.getHostCode());
				insertVo.setConnectId(datastoreUnitedVO.getConnectCode());
				insertVo.setREMOTE_HOST(datastoreUnitedVO.getRemoteHost());
				insertVo.setREMOTE_PATH(datastoreUnitedVO.getRemotePath());
				insertVo.setTYPE(datastoreUnitedVO.getDatastoreType());
				insertVo.setCAPACITY(String.valueOf(retVO.getDatastore().getCapacityInMb()));
				insertVo.setFREE_SPACE(String.valueOf(retVO.getDatastore().getFreeSpaceInMb()));
				insertVo.setSTORAGE_URL(retVO.getDatastore().getDatastoreUrl());
				dataStoreDao.insertDatastore(insertVo);
			} else {
				result = "Failed :" + retVO.getLog();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
