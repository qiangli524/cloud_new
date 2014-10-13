package com.sitech.ssd.sc.os.service;

import java.util.List;

import com.sitech.ssd.sc.os.domain.OsVolGroupModel;

/**
 * 
 * @ClassName: OsVolGroupService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author JamTau
 * @date 2014-9-17 上午12:59:03
 *
 */
public interface OsVolGroupService {
	
	public int copyOsTemplateVolGroup(OsVolGroupModel vg);
	
	public int deleteAllOsVolGroup(OsVolGroupModel vg);
	
	public int deleteOsTemplateVolGroup(OsVolGroupModel vg);
	
	public List<OsVolGroupModel> queryOsVolGroupList(OsVolGroupModel vg);
	
	public List<OsVolGroupModel> unionOsVolGroupList(OsVolGroupModel vg);
}
