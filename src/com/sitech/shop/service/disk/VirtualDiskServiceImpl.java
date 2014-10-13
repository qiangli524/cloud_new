package com.sitech.shop.service.disk;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import publiccloud.VirtualDiskStatus;

import com.sitech.basd.resource.dao.united.UnitedTreeDao;
import com.sitech.basd.resource.domain.united.UnitedTreeObj;
import com.sitech.basd.resource.service.united.UnitedTreeService;
import com.sitech.basd.resource.util.VirtualClient;
import com.sitech.basd.sxcloud.cloud.dao.vmhost.VMHostDao;
import com.sitech.basd.sxcloud.cloud.domain.vmhost.VMHostObj;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.basd.yicloud.dao.datastore.DataStoreDao;
import com.sitech.basd.yicloud.domain.datastore.DataStoreObj;
import com.sitech.shop.dao.disk.VirtualDiskDao;
import com.sitech.shop.dao.disk.VmDiskRelationDao;
import com.sitech.shop.domain.disk.VirtualDiskObj;
import com.sitech.shop.domain.disk.VmDiskRelationObj;
import com.sitech.utils.exception.HttpClientException;
import com.sitech.utils.jackson.JacksonUtil;
import com.sitech.utils.randomid.RandomUUID;
import com.sitech.vo.united.VirtualDiskUnitedVO;
import com.sitech.vo.util.UnitedConstant;
import com.sitech.vo.util.VirtualConstant;

@Service("virtualDiskService")
public class VirtualDiskServiceImpl implements VirtualDiskService {

	@Autowired
	private VirtualDiskDao virtualDiskDao;

	@Autowired
	private VmDiskRelationDao vmDiskRelationDao;

	@Autowired
	private VMHostDao vmHostDao;

	// @Autowired
	// private HostInfoDao hostInfoDao;

	@Autowired
	private UnitedTreeDao unitedTreeDao;

	@Autowired
	private DataStoreDao dataStoreDao;

	@Autowired
	private UnitedTreeService unitedTreeService;

	/**
	 * @Title: queryForListByObj
	 * @Description: 查询列表
	 * @param
	 * @return List<VirtualDiskObj>
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2014-4-17 下午3:41:59
	 */
	public List<VirtualDiskObj> queryForListByObj(VirtualDiskObj virtualDiskObj) {
		return virtualDiskDao.queryForListByObj(virtualDiskObj);
	};

	/**
	 * 
	 * @Title: insertByObj
	 * @Description: 插入一条信息
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2014-4-10 下午7:31:34
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void insertByObj(VirtualDiskObj obj) throws Exception {
		virtualDiskDao.insertByObj(obj);
	}

	/**
	 * 
	 * @Title: deleteByObj
	 * @Description: 删除一条记录
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-4-10 下午7:33:23
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public int deleteByObj(VirtualDiskObj obj) throws Exception {
		return virtualDiskDao.deleteByObj(obj);
	}

	/**
	 * @Title: queryForListDetail
	 * @Description: 查询详细列表
	 * @param
	 * @return List<VirtualDiskObj>
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2014-4-18 上午9:19:20
	 */
	@Override
	public List<VirtualDiskObj> queryForListDetail(VirtualDiskObj virtualDiskObj) {
		return virtualDiskDao.queryForListDetail(virtualDiskObj);
	}

	/**
	 * @Title: createDisk
	 * @Description: 创建虚拟磁盘
	 * @param
	 * @return VirtualDiskUnitedVO
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @throws HttpClientException
	 * @createtime 2014-4-18 上午11:51:27
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public VirtualDiskUnitedVO createDisk(VirtualDiskUnitedVO virtualDiskUnitedVO) throws Exception {
		VirtualDiskUnitedVO result = new VirtualDiskUnitedVO();
		// 商城传递过来的信息：虚拟磁盘大小、所属虚拟机、虚拟磁盘名称
		virtualDiskUnitedVO.setConnectCode("VCENTER.m0");
		virtualDiskUnitedVO.setVmCode("vm-126");
		virtualDiskUnitedVO.setCapacityInMB((long) 1024);
		virtualDiskUnitedVO.setLabelName("testlipp3");

		String connectCode = virtualDiskUnitedVO.getConnectCode();
		String vmCode = virtualDiskUnitedVO.getVmCode();
		String labelName = virtualDiskUnitedVO.getLabelName();

		try {
			VMHostObj vmHostObj = getVmHostObj(connectCode, vmCode);

			String entityId = vmHostObj.getHostCode();
			String vmName = vmHostObj.getVH_NAME();

			// 第一步：找到数据中心
			UnitedTreeObj treeObj = new UnitedTreeObj();
			treeObj.setUuid(entityId);
			treeObj.setConnect_id(connectCode);
			List<UnitedTreeObj> treeList = unitedTreeDao.queryForListByObj(treeObj);
			treeObj = treeList.get(0);
			treeObj = unitedTreeService.getUnitedTreeObj(treeObj.getParent_id(),
					UnitedConstant.DATACENTER, 0);
			String datacenterCode = treeObj.getUuid();

			// 第二步：找到数据存储
			List<DataStoreObj> storeList = getDataStoreList(entityId, connectCode, datacenterCode);
			DataStoreObj storeObj = randomStore(storeList);

			// 第三步：构造接口所需的名称参数
			String directory = "[" + storeObj.getNAME() + "] " + vmName + "/";
			// String directory = "[datastore1 (1)] Nexenta_huojla/";
			virtualDiskUnitedVO.setConnectCode(connectCode);
			virtualDiskUnitedVO.setDatacenterCode(datacenterCode);
			virtualDiskUnitedVO.setVmdkPath(directory + labelName + ".vmdk");
			virtualDiskUnitedVO.setSourceVmdkPath(directory);

			virtualDiskUnitedVO.setDatastoreCode(storeObj.getStore_uuid());

			String url = "virtualhardware/" + VirtualConstant.VT_VMWARE + "/disk/create";
			result = VirtualClient.post(url, virtualDiskUnitedVO,
					new JacksonUtil.TypeReference<VirtualDiskUnitedVO>() {
					});
			if (result.getIsSuccess()) {
				String log = result.getLog();
				if ("".equals(log)) {
					saveToDB(virtualDiskUnitedVO, result.getVmdkLabel(), null, "1");
				} else {
					saveToDB(virtualDiskUnitedVO, result.getVmdkLabel(), "", "1");
				}
			} else {
				LogHelper.error("创建虚拟磁盘接口调用失败 ");
			}

		} catch (Exception e) {
			e.printStackTrace();
			LogHelper.error("创建虚拟磁盘出错，错误信息： " + e);
			throw new Exception(e);
		}
		return result;
	}

	/**
	 * @Title: randomStore
	 * @Description: 随机一块数据存储
	 * @return DataStoreObj
	 * @throws
	 * @Date 2014-4-23 下午1:51:39
	 * @author lipp
	 * @param storeList
	 * @return
	 */
	private DataStoreObj randomStore(List<DataStoreObj> storeList) {
		DataStoreObj storeObj = new DataStoreObj();
		if (storeList.size() > 0) {
			int max = storeList.size();
			int index = new Random().nextInt(max);
			storeObj = storeList.get(index);
		}
		return storeObj;
	}

	/**
	 * @Title: getDataStoreList
	 * @Description: 获取数据存储集合
	 * @return List<DataStoreObj>
	 * @throws
	 * @Date 2014-4-23 下午1:46:45
	 * @author lipp
	 * @param entityId
	 * @param connectCode
	 * @param datacenterCode
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<DataStoreObj> getDataStoreList(String hostCode, String connectCode,
			String datacenterCode) {
		DataStoreObj dataStoreObj = new DataStoreObj();
		dataStoreObj.setConnectId(connectCode);
		dataStoreObj.setDATACENTER_ID(datacenterCode);
		dataStoreObj.setHOST_ID(hostCode);
		List<DataStoreObj> storeList = dataStoreDao.queryForListByObj(dataStoreObj);
		return storeList;
	}

	/**
	 * @Title: getUnitedTreeObj
	 * @Description: 获取统一树对象
	 * @return UnitedTreeObj
	 * @throws
	 * @Date 2014-4-23 下午1:22:23
	 * @author lipp
	 * @param id
	 * @param datacenter
	 * @return
	 */
	// private UnitedTreeObj getUnitedTreeObj(String id, String entityType, int
	// count) {
	// count++;
	// if (count > 5) {// 避免进入死循环
	// return null;
	// } else {
	// UnitedTreeObj treeObj = new UnitedTreeObj();
	// treeObj.setId(id);
	// List<UnitedTreeObj> treeList = unitedTreeDao.queryForListByObj(treeObj);
	// treeObj = treeList.get(0);
	// if (entityType.equals(treeObj.getType())) {
	// return treeObj;
	// } else {
	// treeObj = getUnitedTreeObj(treeObj.getParent_id(), entityType, count);
	// }
	// return treeObj;
	// }
	// }

	/**
	 * @Title: saveToDB
	 * @Description: 数据入库
	 * @return void
	 * @Date 2014-4-23 下午12:34:34
	 * @author lipp
	 * @param virtualDiskUnitedVO
	 * @throws Exception
	 */
	private void saveToDB(VirtualDiskUnitedVO virtualDiskUnitedVO, String label, String flag,
			String userId) throws Exception {
		try {
			// 虚拟磁盘入库
			VirtualDiskObj virtualDiskObj = new VirtualDiskObj();
			String id = RandomUUID.getUuid();
			virtualDiskObj.setId(id);
			virtualDiskObj.setName(virtualDiskUnitedVO.getLabelName());
			virtualDiskObj.setConnectCode(virtualDiskUnitedVO.getConnectCode());
			virtualDiskObj.setDatacenterCode(virtualDiskUnitedVO.getDatacenterCode());
			virtualDiskObj.setDatastoreCode(virtualDiskUnitedVO.getDatastoreCode());
			virtualDiskObj.setCapacityInMB(virtualDiskUnitedVO.getCapacityInMB() * 1.0);
			virtualDiskObj.setUser_id(userId);
			virtualDiskObj.setPath(virtualDiskUnitedVO.getVmdkPath());
			virtualDiskObj.setLabel(label);
			virtualDiskObj.setDisk_type("2");
			virtualDiskDao.insertByObj(virtualDiskObj);

			if (flag == null) {// 挂载成功
				// 磁盘与虚拟机关系入库
				VmDiskRelationObj relationObj = new VmDiskRelationObj();
				relationObj.setVirdisk_id(id);
				relationObj.setConnect_id(virtualDiskUnitedVO.getConnectCode());
				relationObj.setVm_uuid(virtualDiskUnitedVO.getVmCode());
				relationObj.setId(RandomUUID.getUuid());
				vmDiskRelationDao.insertByObj(relationObj);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// 将异常信息发工单给运维人员，待开发

			throw new Exception(e);
		}

	}

	/**
	 * @Title: getVmHostObj
	 * @Description: 获取虚拟机
	 * @return VMHostObj
	 * @throws
	 * @Date 2014-4-23 上午11:05:31
	 * @author lipp
	 * @param connectCode
	 * @param vmCode
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private VMHostObj getVmHostObj(String connectCode, String vmCode) {
		VMHostObj vmHostObj = new VMHostObj();
		vmHostObj.setConnectId(connectCode);
		vmHostObj.setVH_UUID(vmCode);
		List<VMHostObj> vhList = vmHostDao.queryForListByObj(vmHostObj);
		vmHostObj = vhList.get(0);
		return vmHostObj;
	}

	/**
	 * @Title: destoryDisk
	 * @Description: 删除虚拟磁盘
	 * @return int
	 * @throws
	 * @Date 2014-4-23 下午3:03:55
	 * @author lipp
	 * @param obj
	 * @return
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public int destoryDisk(VirtualDiskObj obj) {
		VirtualDiskUnitedVO result = new VirtualDiskUnitedVO();
		int ret = 0;
		try {
			// 请求接口删除虚拟磁盘
			// String url = "virtualhardware/disk/destory?vType=" +
			// VirtualConstant.VT_VMWARE +
			// "&connectCode="+obj.getConnectCode() + "&datacenterCode=" +
			// obj.getDatacenterCode()
			// + "&vmdkPath="+obj.getPath();
			String url = "virtualhardware/disk/remove/true?vType=" + VirtualConstant.VT_VMWARE
					+ "&connectCode=" + obj.getConnectCode() + "&datacenterCode="
					+ obj.getDatacenterCode() + "&vmCode=" + obj.getVm_uuid() + "&vmdkLabel="
					+ obj.getLabel();
			result = VirtualClient.delete(url,
					new JacksonUtil.TypeReference<VirtualDiskUnitedVO>() {
					});
		} catch (Exception e) {
			e.printStackTrace();
			ret = -1;
		}

		if (result.getIsSuccess()) {
			// 信息同步给商城后台，待开发，此处如果失败，不进行下面的操作，通知运维处理

			// 删除数据库信息
			virtualDiskDao.deleteByObj(obj);
			VmDiskRelationObj relationObj = new VmDiskRelationObj();
			relationObj.setVirdisk_id(obj.getId());
			vmDiskRelationDao.deleteByObj(relationObj);

			List<VirtualDiskUnitedVO> virList = result.getSelfList();
			for (VirtualDiskUnitedVO vo : virList) {
				VirtualDiskObj virtualDiskObj = new VirtualDiskObj();
				virtualDiskObj.setPath(vo.getVmdkPath());
				virtualDiskObj.setConnectCode(vo.getConnectCode());
				virtualDiskObj.setDatacenterCode(vo.getDatacenterCode());
				virtualDiskObj.setLabel(vo.getVmdkLabel());
				try {
					virtualDiskDao.updateByPath(virtualDiskObj);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} else {
			ret = -1;
		}
		return ret;
	}

	/**
	 * @Title: unInstallDisk
	 * @Description: 卸载虚拟磁盘
	 * @return int
	 * @throws
	 * @Date 2014-4-23 下午3:10:26
	 * @author lipp
	 * @param obj
	 * @return
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public int unInstallDisk(VirtualDiskObj obj) {
		VirtualDiskUnitedVO result = new VirtualDiskUnitedVO();
		int ret = 0;
		try {
			// 请求接口删除虚拟磁盘
			String url = "virtualhardware/disk/remove/false?vType=" + VirtualConstant.VT_VMWARE
					+ "&connectCode=" + obj.getConnectCode() + "&datacenterCode="
					+ obj.getDatacenterCode() + "&vmCode=" + obj.getVm_uuid() + "&vmdkLabel="
					+ obj.getLabel();
			result = VirtualClient.delete(url,
					new JacksonUtil.TypeReference<VirtualDiskUnitedVO>() {
					});
		} catch (Exception e) {
			e.printStackTrace();
			ret = -1;
		}

		if (result.getIsSuccess()) {
			// 删除数据库信息
			VmDiskRelationObj relationObj = new VmDiskRelationObj();
			relationObj.setConnect_id(obj.getConnectCode());
			relationObj.setVm_uuid(obj.getVm_uuid());
			relationObj.setVirdisk_id(obj.getId());
			vmDiskRelationDao.deleteByObj(relationObj);

			// 更新磁盘状态
			obj.setState(VirtualDiskStatus.unmount);
			virtualDiskDao.updateByPath(obj);

			// 更新label
			List<VirtualDiskUnitedVO> virList = result.getSelfList();
			for (VirtualDiskUnitedVO vo : virList) {
				VirtualDiskObj virtualDiskObj = new VirtualDiskObj();
				virtualDiskObj.setPath(vo.getVmdkPath());
				virtualDiskObj.setConnectCode(vo.getConnectCode());
				virtualDiskObj.setDatacenterCode(vo.getDatacenterCode());
				virtualDiskObj.setLabel(vo.getVmdkLabel());
				try {
					virtualDiskDao.updateByPath(virtualDiskObj);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} else {
			ret = -1;
		}
		return ret;
	}

	/**
	 * @Title: mountDisk
	 * @Description: 挂载虚拟磁盘
	 * @return int
	 * @throws
	 * @Date 2014-4-23 下午3:14:21
	 * @author lipp
	 * @param obj
	 * @return
	 */
	@Override
	public int mountDisk(VirtualDiskObj obj) {

		VirtualDiskUnitedVO vo = new VirtualDiskUnitedVO();
		vo.setConnectCode(obj.getConnectCode());
		vo.setVmCode(obj.getVm_uuid());
		vo.setVmdkPath(obj.getPath());
		vo.setDatacenterCode(obj.getDatacenterCode());

		VirtualDiskUnitedVO result = new VirtualDiskUnitedVO();
		int ret = 0;
		try {
			String url = "virtualhardware/disk/mount/" + VirtualConstant.VT_VMWARE;
			result = VirtualClient.put(url, vo,
					new JacksonUtil.TypeReference<VirtualDiskUnitedVO>() {
					});
		} catch (Exception e) {
			e.printStackTrace();
			ret = -1;
		}

		if (result.getIsSuccess()) {
			// 更新label
			List<VirtualDiskUnitedVO> virList = result.getSelfList();
			for (VirtualDiskUnitedVO vr : virList) {
				VirtualDiskObj virtualDiskObj = new VirtualDiskObj();
				virtualDiskObj.setPath(vr.getVmdkPath());
				virtualDiskObj.setConnectCode(vr.getConnectCode());
				virtualDiskObj.setDatacenterCode(vr.getDatacenterCode());
				virtualDiskObj.setLabel(vr.getVmdkLabel());
				virtualDiskObj.setState(VirtualDiskStatus.mount);
				try {
					virtualDiskDao.updateByPath(virtualDiskObj);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			VmDiskRelationObj relationObj = new VmDiskRelationObj();
			relationObj.setVirdisk_id(obj.getId());
			relationObj.setVm_uuid(obj.getVm_uuid());
			relationObj.setConnect_id(obj.getConnectCode());
			relationObj.setId(RandomUUID.getUuid());
			try {
				vmDiskRelationDao.insertByObj(relationObj);
			} catch (Exception e) {
				// 入库失败，发送工单给运维人员，待开发

				e.printStackTrace();
			}
		} else {
			ret = -1;
		}
		return ret;
	}

	/**
	 * (非 Javadoc)
	 * <p>
	 * Title: queryForCount
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @author wanglei_bj@si-tech.com.cn
	 * @param obj
	 * @return
	 * @see com.sitech.shop.service.disk.com.sitech.shop.service.disk.VirtualDiskService#queryForCount(com.sitech.basd.resource.domain.united.disk.VirtualDiskObj)
	 */
	@Override
	public Integer queryForCount(VirtualDiskObj obj) {
		int ret = 0;
		try {
			ret = virtualDiskDao.queryForCount(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;

	}

	/**
	 * (非 Javadoc)
	 * <p>
	 * Title: updateDisk
	 * </p>
	 * <p>
	 * Description: 更新一条记录
	 * </p>
	 * 
	 * @author wanglei_bj@si-tech.com.cn
	 * @param obj
	 * @return
	 * @see com.sitech.shop.service.disk.VirtualDiskService#updateDisk(domain.disk.VirtualDiskObj)
	 */
	@Override
	public int updateDisk(VirtualDiskObj obj) {
		try {
			return virtualDiskDao.updateByObj(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	/**
	 * @Title: getHostObj
	 * @Description: 获取主机
	 * @return TbCloud2HostInfoObj
	 * @throws
	 * @Date 2014-4-23 上午11:08:16
	 * @author lipp
	 * @param hostCode
	 * @param connectCode
	 * @return
	 */
	/*
	 * private TbCloud2HostInfoObj getHostObj(String hostCode, String
	 * connectCode) { TbCloud2HostInfoObj hostInfoObj = new
	 * TbCloud2HostInfoObj(); hostInfoObj.setH_uuid(hostCode);
	 * hostInfoObj.setConnectId(connectCode); List<TbCloud2HostInfoObj> hostList
	 * = hostInfoDao.queryForListByObj(hostInfoObj); hostInfoObj =
	 * hostList.get(0); return hostInfoObj; }
	 */

}
