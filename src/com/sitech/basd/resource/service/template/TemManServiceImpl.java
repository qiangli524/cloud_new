package com.sitech.basd.resource.service.template;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.basd.resource.dao.template.TemManDao;
import com.sitech.basd.resource.domain.template.TemManObj;
import com.sitech.basd.resource.util.VirtualClient;
import com.sitech.basd.sxcloud.cloud.dao.vmhost.VMHostDao;
import com.sitech.basd.yicloud.dao.datastore.DataStoreDao;
import com.sitech.basd.yicloud.domain.datastore.DataStoreObj;
import com.sitech.utils.exception.HttpClientException;
import com.sitech.utils.jackson.JacksonUtil;
import com.sitech.vo.enumtype.Types;
import com.sitech.vo.united.VirtualMachineDiskOper;
import com.sitech.vo.united.VirtualMachineUnitedVO;
import com.sitech.vo.util.UnitedConstant;
import com.sitech.vo.util.VirtualConstant;

@Service("temManService")
public class TemManServiceImpl implements TemManService {
	@Autowired
	private TemManDao temManDao;
	@Autowired
	private VMHostDao vmHostDao;
	@Autowired
	private DataStoreDao dataStoreDao;

	/**
	 * 
	 * @Title: queryForList
	 * @Description: 查询模板列表
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime May 9, 2013 5:00:06 PM
	 */
	public List queryForList(TemManObj obj) {
		return temManDao.queryForList(obj);
	}

	public int updateByObj(TemManObj obj) {
		return temManDao.updateByObj(obj);
	}

	public int deleteByObj(TemManObj obj) {
		return temManDao.deleteByObj(obj);
	}

	public int insertByObj(TemManObj obj) {
		return temManDao.insertByObj(obj);
	}

	/**
	 * 
	 * @Title: createTem
	 * @Description: 创建模板
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @throws HttpClientException
	 * @createtime May 15, 2013 8:54:57 AM
	 */
	public String createTem(TemManObj obj) throws HttpClientException {
		String result = UnitedConstant.FAIL;
		obj.setStore(obj.getStore() * 1024);
		VirtualMachineUnitedVO vo = new VirtualMachineUnitedVO();
		vo.setConnectCode(obj.getConnectId());
		vo.setHostCode(obj.getHostCode());
		vo.setNewVmName(obj.getName());
		vo.setNumCPUs(obj.getCpu());
		vo.setMemoryMB(obj.getMem());
		vo.setStorageSizeInMb(obj.getStore() * 1024);
		vo.setVmCode(obj.getTemplateCode());
		vo.setIsTemplate(true);
		String type = obj.getType();
		String url = "";
		vo.setCreateType(Types.createType.VM_TO_TEMPLATE);
		if (UnitedConstant.VMWARE.equals(type)) {
			DataStoreObj store = new DataStoreObj();
			store.setHOST_ID(obj.getHostCode());
			List<DataStoreObj> list = dataStoreDao.queryForListByObj(store);
			if (list != null && list.size() > 0) {
				store = list.get(0);
			}
			vo.setDatastoreCode(store.getStore_uuid());
			// 对磁盘的操作
			vo.setVmDiskOper(VirtualMachineDiskOper.EDIT);
			url = "virtualmachine/" + VirtualConstant.VT_VMWARE + "/clone";
			vo = VirtualClient.post(url, vo,
					new JacksonUtil.TypeReference<VirtualMachineUnitedVO>() {
					});
			if (vo.getIsSuccess()) {
				result = UnitedConstant.SUCCESS;
				obj.setTemplateCode(vo.getVmCode());
				// 虚拟网卡数量
				obj.setNic_count(vo.getEthernetCardList() == null ? 0 : vo.getEthernetCardList()
						.size());
				temManDao.insertByObj(obj);
			} else {
				result = vo.getLog();
			}
		} else if ("3".equals(type)) {
			url = "virtualmachine/" + VirtualConstant.VT_XEN + "/clone";
			vo.setIsTemplate(false);
			vo = VirtualClient.post(url, vo,
					new JacksonUtil.TypeReference<VirtualMachineUnitedVO>() {
					});
			if (vo.getIsSuccess()) {
				result = UnitedConstant.SUCCESS;
				obj.setTemplateCode(vo.getVmCode());
				obj.setType(UnitedConstant.XEN);
				obj.setNic_count(vo.getEthernetCardList() == null ? 0 : vo.getEthernetCardList()
						.size());
				temManDao.insertByObj(obj);
			} else {
				result = vo.getLog();
			}
		} else {
			obj.setType("3");// OVF,暂时写成这样
			temManDao.insertByObj(obj);
		}
		return result;
	}

	/**
	 * 
	 * @Title: delTem
	 * @Description: 删除模板
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @throws UnsupportedEncodingException
	 * @createtime May 21, 2013 9:37:06 AM
	 */
	public String delTem(TemManObj obj) throws HttpClientException {

		String result = "success";
		obj = temManDao.queryOneTemManObj(obj);
		VirtualMachineUnitedVO vo = new VirtualMachineUnitedVO();
		// vo.setConnectCode(obj.getConnectId());
		// vo.setHostCode(obj.getHostCode());
		// vo.setVmCode(obj.getTemplateCode());
		String type = obj.getType();
		String url = "";
		// vo.setCreateType(Types.createType.VM_TO_TEMPLATE);
		if (UnitedConstant.VMWARE.equals(type)) {
			url = "virtualmachine/" + VirtualConstant.VT_VMWARE + "/destory/" + obj.getConnectId()
					+ "/" + obj.getTemplateCode();
			vo = VirtualClient.delete(url, new JacksonUtil.TypeReference<VirtualMachineUnitedVO>() {
			});
			if (vo.getIsSuccess()) {
				temManDao.deleteByObj(obj);
			} else {
				result = vo.getLog();
			}
		} else if (UnitedConstant.XEN.equals(type)) {
			url = "virtualmachine/" + VirtualConstant.VT_XEN + "/destory/" + obj.getConnectId()
					+ "/" + obj.getTemplateCode();
			vo = VirtualClient.delete(url, new JacksonUtil.TypeReference<VirtualMachineUnitedVO>() {
			});
			if (vo.getIsSuccess()) {
				temManDao.deleteByObj(obj);
			} else {
				result = vo.getLog();
			}
		} else {
			temManDao.deleteByObj(obj);
		}
		return result;
	}

	@Override
	public List<TemManObj> queryTemListById(TemManObj obj) {
		// TODO Auto-generated method stub
		return temManDao.queryTemListById(obj);
	}
}
