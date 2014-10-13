package com.sitech.shop.dao.disk;

import java.util.List;

import com.sitech.shop.domain.disk.VmDiskRelationObj;

/**
 * <p>
 * Title: VmDiskRelationDao
 * </p>
 * <p>
 * Description: 虚拟机与虚拟磁盘关系接口
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author lipp
 * @version 1.0
 * @createtime 2014-4-18 下午3:27:20
 * 
 */
public interface VmDiskRelationDao {

	/**
	 * @Title: insertByObj
	 * @Description: 插入记录
	 * @param
	 * @return int
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2014-4-18 下午3:26:10
	 */
	public void insertByObj(VmDiskRelationObj relationObj);

	/**
	 * @Title: deleteByObj
	 * @Description: 删除记录
	 * @param
	 * @return int
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2014-4-18 下午3:26:06
	 */
	public int deleteByObj(VmDiskRelationObj relationObj);

	/**
	 * 
	 * @Title: insertForBatch
	 * @Description: 批量插入数据
	 * @param
	 * @return void
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-6-18 下午2:46:32
	 */
	public void insertForBatch(List<VmDiskRelationObj> list);
}
