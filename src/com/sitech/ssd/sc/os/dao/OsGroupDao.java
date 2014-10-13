package com.sitech.ssd.sc.os.dao;

import java.util.List;

import com.sitech.ssd.sc.os.domain.OsGroupModel;

/**
  * @Title:操作系统用户组DAO
  * @Description:
  * 
  * @Copyight: Copyright (c) 2014
  * @Company: SI-Tech
  * @Author: JamTau
  * @Date 2014-7-9 下午07:27:33
 */
public interface OsGroupDao {
	
	public int insertOsGroup(OsGroupModel model);
	public int copyOsTemplateGroup(OsGroupModel model);	
	/**
	 * @Title: deleteOsGroup 
	 * @Description: 
	 * 	输入os_host_id，删除主机对应的所有用户组
	 *  输入id，删除一行数据
	 * @Author: JamTau
	 * @Date 2014-7-15 上午11:02:30
	 * @param model
	 * @return  int
	 */
	public int deleteOsGroup(OsGroupModel model);
	
	public int updateOsGroup(OsGroupModel model);
	
	public OsGroupModel selectOsGroup(OsGroupModel model);
	
	public List<OsGroupModel> selectOsGroupList(OsGroupModel model);

	public List<OsGroupModel> unionOsGroupList(OsGroupModel model);
}
