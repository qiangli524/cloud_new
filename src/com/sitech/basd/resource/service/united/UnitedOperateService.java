package com.sitech.basd.resource.service.united;

import java.util.List;

import com.sitech.basd.resource.domain.united.UnitedTreeObj;
import com.sitech.basd.yicloud.domain.datastore.DataStoreObj;
import com.sitech.basd.yicloud.domain.switches.VirtualSwitch;
import com.sitech.utils.exception.HttpClientException;
import com.sitech.vo.united.PortGroupUnitedVO;
import com.sitech.vo.united.VirtualDiskUnitedVO;
import com.sitech.vo.united.VirtualEthernetCardUnitedVO;
import com.sitech.vo.united.VirtualMachineUnitedVO;

/**
 * 
 * <p>
 * Title: UnitedOperateService
 * </p>
 * <p>
 * Description:联通测试用例中添加
 * </p>
 * <p>
 * Company: si-tech
 * </p>
 * 
 * @author duangh
 * @date 2013 八月 30
 */
public interface UnitedOperateService {
	/**
	 * 
	 * @Title: createOperate
	 * @Description:创建裸机
	 * @author duangh
	 * @date 2013 八月 30 18:20:08
	 * @return
	 */
	public String createOperate(UnitedTreeObj obj, VirtualMachineUnitedVO vo)
			throws HttpClientException;

	/**
	 * 
	 * @Title: hostVswitch
	 * @Description:查询主机的交换机信息
	 * @author duangh
	 * @date 2013 九月 2 09:54:50
	 * @return
	 */
	public List<VirtualSwitch> hostVswitch(VirtualSwitch vsObj);

	/**
	 * 
	 * @Title: hostPortGroup
	 * @Description:查询主机的端口组信息，联通测试用例添加
	 * @author duangh
	 * @date 2013 九月 2 13:54:46
	 * @param vsObj
	 * @return
	 */
	public List<PortGroupUnitedVO> hostPortGroup(UnitedTreeObj obj);

	/**
	 * 
	 * @Title: mountISO
	 * @Description:挂载ISO
	 * @author duangh
	 * @date 2013 九月 3 10:33:32
	 * @return
	 */
	public String mountISO(VirtualMachineUnitedVO vo);

	/**
	 * 
	 * @Title: rebootTOBIOS
	 * @Description:调用接口，重启后进入BIOS
	 * @author duangh
	 * @date 2013 九月 3 11:37:50
	 * @return
	 */
	public String rebootToBIOS(VirtualMachineUnitedVO vo);

	/**
	 * 
	 * @Title: isoPathList
	 * @Description:查询ISO路径，暂时从全局配置表里配置，以后看是否能从接口取到
	 * @author duangh
	 * @date 2013 九月 4 18:09:04
	 * @return
	 */
	public List<String> isoPathList();

	/**
	 * 
	 * @Title: addVnic
	 * @Description:调用接口虚拟机添加网卡
	 * @author duangh
	 * @date 2013 九月 6 09:35:54
	 * @param vo
	 * @return
	 */
	public String addVnic(VirtualEthernetCardUnitedVO vo);

	/**
	 * 
	 * @Title: getVnicInfo
	 * @Description:得到虚拟机的所有网卡信息
	 * @author duangh
	 * @date 2013 九月 6 11:09:26
	 * @param vo
	 * @return
	 */
	public List<VirtualEthernetCardUnitedVO> getVnicInfo(VirtualEthernetCardUnitedVO vo);

	/**
	 * 
	 * @Title: deleteVnic
	 * @Description:删除虚拟机的网卡
	 * @author duangh
	 * @date 2013 九月 6 13:24:57
	 * @param vo
	 * @return
	 */
	public String deleteVnic(VirtualEthernetCardUnitedVO vo);

	/**
	 * 
	 * @Title: reconfigVnic
	 * @Description:重新配置虚拟机网卡
	 * @author duangh
	 * @date 2013 九月 6 15:32:04
	 * @param vo
	 * @return
	 */
	public String reconfigVnic(VirtualEthernetCardUnitedVO vo);

	/**
	 * 
	 * @Title: createDisk
	 * @Description:虚拟机创建磁盘或新挂载磁盘
	 * @author duangh
	 * @date 2013 九月 6 17:03:41
	 * @param vo
	 * @return
	 */
	public String createDisk(VirtualDiskUnitedVO vo);

	/**
	 * 
	 * @Title: getDiskInfo
	 * @Description:查询虚拟机磁盘信息
	 * @author duangh
	 * @date 2013 九月 6 21:21:45
	 * @param vo
	 * @return
	 */
	public List<VirtualDiskUnitedVO> getDiskInfo(VirtualDiskUnitedVO vo);

	/**
	 * 
	 * @Title: deleteDisk
	 * @Description:调用接口删除虚拟机磁盘
	 * @author duangh
	 * @date 2013 九月 6 23:18:28
	 * @param vo
	 * @return
	 */
	public String deleteDisk(VirtualDiskUnitedVO vo, String destroy);

	/**
	 * 
	 * @Title: getDataStoreList
	 * @Description: 获取存储列表
	 * @param
	 * @return List<DataStoreObj>
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-7-21 下午2:04:43
	 */
	public List<DataStoreObj> getDataStoreList(String hostCode, String connectCode,
			String datacenterCode);
}
