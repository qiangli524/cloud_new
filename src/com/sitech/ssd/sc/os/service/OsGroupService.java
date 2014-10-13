package com.sitech.ssd.sc.os.service;

import java.util.List;

import com.sitech.ssd.sc.os.domain.OsGroupModel;

/**
  * @Title:
  * @Description:
  * 
  * @Copyight: Copyright (c) 2014
  * @Company: SI-Tech
  * @Author: JamTau
  * @Date 2014-7-10 下午04:15:58
 */
public interface OsGroupService {
	
	
	
	public int addOsGroup(OsGroupModel model);	
	
	public int copyOsTemplateGroup(OsGroupModel model);	
	
	public int batchAddOsGroup(List<OsGroupModel> list);
	
	public int modifyOsGroup(OsGroupModel model);
	
	public int deleteOsGroup(OsGroupModel model);
	
	public int deleteOsGroupList(OsGroupModel model);
	
	/**
	 * @Title: queryOsGroup 
	 * @Description: 
	 * 	只实现通过os_host_id,filesys_name查询文件系统
	 * @Author: JamTau
	 * @Date 2014-7-15 上午09:18:20
	 * @param model
	 * @return  OsGroupModel
	 */
	public OsGroupModel queryOsGroup(OsGroupModel model);
	
	public List<OsGroupModel> queryOsGroupList(OsGroupModel model);

	public List<OsGroupModel> unionOsGroupList(OsGroupModel model);
}
