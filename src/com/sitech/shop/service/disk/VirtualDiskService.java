package com.sitech.shop.service.disk;

import java.util.List;

import com.sitech.shop.domain.disk.VirtualDiskObj;
import com.sitech.shop.domain.disk.VmDiskRelationObj;
import com.sitech.utils.exception.HttpClientException;
import com.sitech.vo.united.ResultSet;
import com.sitech.vo.united.VirtualDiskUnitedVO;


public interface VirtualDiskService {
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
	public List<VirtualDiskObj> queryForListByObj(VirtualDiskObj virtualDiskObj);

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
	public void insertByObj(VirtualDiskObj obj) throws Exception;

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
	public int deleteByObj(VirtualDiskObj obj) throws Exception;

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
	public List<VirtualDiskObj> queryForListDetail(VirtualDiskObj virtualDiskObj);

	/**
	 * @Title: createDisk
	 * @Description: 创建虚拟磁盘
	 * @param
	 * @return VirtualDiskUnitedVO
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @throws HttpClientException
	 * @throws Exception
	 * @createtime 2014-4-18 上午11:51:27
	 */
	public ResultSet createDisk(VirtualDiskUnitedVO virtualDiskUnitedVO)
			throws HttpClientException, Exception;

	/**
	 * 
	 * @Title: updateDisk
	 * @Description: TODO(更新磁盘)
	 * @param @param VirtualDiskObj
	 * @return int 返回类型
	 * @author wanglei_bj@si-tech.com.cn
	 * @throws
	 * @createtime 2014-6-20
	 */
	public int updateDisk(VirtualDiskObj obj);

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
	public int destoryDisk(VirtualDiskObj obj);

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
	public int unInstallDisk(VirtualDiskObj obj);

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
	public int mountDisk(VirtualDiskObj obj);

	/**
	 * 
	 * @Title: queryForCount
	 * @Description: TODO(查询虚拟磁盘数量)
	 * @param @param obj
	 * @param @return 设定文件
	 * @return Integer 返回类型
	 * @author wanglei_bj@si-tech.com.cn
	 * @throws
	 */
	public Integer queryForCount(VirtualDiskObj obj);

}
