package com.sitech.basd.resource.service.united;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.basd.resource.dao.united.UnitedTreeDao;
import com.sitech.basd.resource.domain.united.UnitedTreeObj;
import com.sitech.basd.resource.util.RevertEntity;
import com.sitech.basd.resource.util.VirtualClient;
import com.sitech.utils.exception.HttpClientException;
import com.sitech.utils.jackson.JacksonUtil;
import com.sitech.utils.randomid.RandomUUID;
import com.sitech.vo.united.DataCenterUnitedVO;
import com.sitech.vo.util.UnitedConstant;
import com.sitech.vo.util.VirtualConstant;

@Service("unitedDataCenterService")
public class UnitedDataCenterServiceImpl implements UnitedDataCenterService {
	@Autowired
	private UnitedTreeDao unitedTreeDao;

	/**
	 * 
	 * @Title: createDataCenter
	 * @Description: 创建数据中心
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @throws Exception
	 * @createtime Jul 22, 2013 4:20:11 PM
	 */
	public String createDataCenter(UnitedTreeObj obj) throws HttpClientException, SQLException {
		String vtype = obj.getVtype();
		obj.setType(UnitedConstant.DATACENTER);
		String result = UnitedConstant.FAIL;
		String url = "datacenter/" + VirtualConstant.VT_VMWARE + "/create";
		if (UnitedConstant.VMWARE.equals(vtype)) {// vmware虚拟化
			DataCenterUnitedVO vo = RevertEntity.toRestDataCenter(obj);
			vo = VirtualClient.post(url, vo, new JacksonUtil.TypeReference<DataCenterUnitedVO>() {
			});
			if (vo.getIsSuccess()) {
				result = UnitedConstant.SUCCESS;
				obj.setUuid(vo.getDataCenterCode());
				unitedTreeDao.insertByObj(obj);
			} else {
				result = vo.getLog();
			}
			// 插入数据
		} else {// Xen虚拟化以及其他类型的数据中心，在此处不做类型判断是因为创建xen数据中心和其他类型一致，只需要插入数据库
			obj.setUuid(RandomUUID.getUuid());
			obj.setConnect_id("");
			unitedTreeDao.insertByObj(obj);
			result = UnitedConstant.SUCCESS;
		}
		return result;
	}

	/**
	 * 
	 * @Title: delDataCenter
	 * @Description:删除数据中心
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @throws SQLException
	 * @throws Exception
	 * @createtime Jul 22, 2013 5:45:53 PM
	 */
	public String delDataCenter(UnitedTreeObj obj) throws HttpClientException, SQLException {
		String result = UnitedConstant.FAIL;
		String vtype = obj.getVtype();
		String url = "";
		if (UnitedConstant.VMWARE.equals(vtype)) {
			DataCenterUnitedVO vo = RevertEntity.toRestDataCenter(obj);
			url = "datacenter/" + VirtualConstant.VT_VMWARE + "/delete/" + obj.getConnect_id()
					+ "/" + obj.getUuid();
			vo = VirtualClient.delete(url, new JacksonUtil.TypeReference<DataCenterUnitedVO>() {
			});
			if (vo.getIsSuccess()) {
				result = UnitedConstant.SUCCESS;
				unitedTreeDao.deleteByObj(obj);
			} else {
				result = vo.getLog();
			}

			// 插入数据
		} else if (UnitedConstant.XEN.equals(vtype)) {
			unitedTreeDao.deleteByObj(obj);
			result = UnitedConstant.SUCCESS;
		}

		return result;
	}
}
