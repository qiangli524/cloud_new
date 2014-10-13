package com.sitech.basd.resource.service.united;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.basd.resource.dao.united.UnitedTreeDao;
import com.sitech.basd.resource.domain.united.UnitedTreeObj;
import com.sitech.basd.resource.util.VirtualClient;
import com.sitech.utils.exception.HttpClientException;
import com.sitech.utils.jackson.JacksonUtil;
import com.sitech.vo.united.HostPnicVO;
import com.sitech.vo.united.HostVSwitchVO;
import com.sitech.vo.united.PortGroupUnitedVO;
import com.sitech.vo.united.ResultSet;
import com.sitech.vo.util.UnitedConstant;

@Service("unitedNetworkService")
public class UnitedNetworkServiceImpl implements UnitedNetworkService {
	private static final Logger LOG = Logger.getLogger(UnitedNetworkService.class);
	@Autowired
	private UnitedTreeDao unitedTreeDao;

	/**
	 * 
	 * @Title:添加网络域
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @throws SQLException
	 * @createtime 2013-9-11 上午11:01:20
	 */
	public String addDomain(UnitedTreeObj obj) throws SQLException {
		// obj.setType(UnitedConstant.DOMAIN);
		String ret = unitedTreeDao.insertByObj(obj);
		return ret;
	}

	/**
	 * 
	 * @Title:删除
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-9-12
	 */
	public int delOperate(UnitedTreeObj obj) throws SQLException {
		return unitedTreeDao.deleteByObj(obj);

	}

	/**
	 * 
	 * @Title:修改
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-9-13
	 */
	public int updOperate(UnitedTreeObj obj) throws SQLException {
		return unitedTreeDao.updateOperateByObj(obj);
	}

	/**
	 * 
	 * @Title: queryNetDomain
	 * @Description: 查询网络域
	 * @param
	 * @return list
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @throws Exception
	 * @createtime 2013-09-14
	 */
	public List queryNetDomain() {
		return unitedTreeDao.queryNetDomain();
	}

	/**
	 * 
	 * @Title: querySubNet
	 * @Description: 查询子网络域
	 * @param
	 * @return list
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2013-09-14
	 */
	public List querySubNet(UnitedTreeObj obj) {
		return unitedTreeDao.querySubNet(obj);
	}

	@Override
	public List<HostPnicVO> getpnicInfo(String connect_id, String hostId, String vtype) {
		List<HostPnicVO> pnicList = null;
		if (UnitedConstant.VMWARE.equals(vtype)) {
			String url = "network/vmware/pnic/" + connect_id + "/" + hostId;
			try {
				pnicList = VirtualClient.get(url, new JacksonUtil.TypeReference<List<HostPnicVO>>() {
				});
			} catch (HttpClientException e) {
				LOG.error("获取主机物理网卡信息失败！");
			}
		}
		return pnicList;
	}

	@Override
	public List<HostVSwitchVO> getVswitchInfo(String connect_id, String hostId) {
		String url = "network/vmware/pnic/vswitch/" + connect_id + "/" + hostId;
		List<HostVSwitchVO> vsList = null;
		try {
			vsList = VirtualClient.get(url, new JacksonUtil.TypeReference<List<HostVSwitchVO>>() {
			});
			for (HostVSwitchVO vs : vsList) {
				List<PortGroupUnitedVO> pgList = vs.getPgList();
				for (PortGroupUnitedVO pg : pgList) {
					String getURI = "network/vmware/pnic/pg/" + pg.getName() + "/VCENTER.m0/"+hostId;
					List<String> vmList = VirtualClient.get(getURI, new JacksonUtil.TypeReference<List<String>>() {
					});
					pg.setVmList(vmList);
				}
			}
		} catch (HttpClientException e) {
			LOG.error("获取主机物理网卡下标准交换机信息失败！");
		}
		return vsList;
	}

	@Override
	public List<HostVSwitchVO> getStandandVswitchPG(String connect_id,String hostId, String vsName) {
		String url = "network/vmware/pnic/vswitch/" + connect_id + "/" + hostId;
		List<HostVSwitchVO> vsList = null;
		try {
			vsList = VirtualClient.get(url, new JacksonUtil.TypeReference<List<HostVSwitchVO>>() {
			});
		} catch (HttpClientException e) {
			LOG.error("获取主机物理网卡下标准交换机信息失败！");
		}
		return vsList;
	}

	@Override
	public String setVlan(String connect_id, String hostId, String pgName,
			String vlanId) {
		PortGroupUnitedVO vo = new PortGroupUnitedVO();
		vo.setVlanId(Integer.valueOf(vlanId));
		vo.setName(pgName);
		vo.setHostCode(hostId);
		vo.setConnectCode(connect_id);
		String url = "network/vmware/pnic/pg/" + pgName + "/"+connect_id+"/"+hostId;
		String result = null;
		try {
			ResultSet resultSet = VirtualClient.put(url,vo,new JacksonUtil.TypeReference<ResultSet>() {
			});
			result = resultSet.getLog();
		} catch (HttpClientException e) {
			LOG.error("设置VLAN异常！");
		}
		return result;
	}
}
