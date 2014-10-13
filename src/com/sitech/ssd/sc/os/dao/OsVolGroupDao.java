package com.sitech.ssd.sc.os.dao;

import java.util.List;

import com.sitech.ssd.sc.os.domain.OsVolGroupModel;

/**
 * 
 * @ClassName: OsVolGroupDao
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author JamTau
 * @date 2014-9-17 上午1:02:35
 *
 */
public interface OsVolGroupDao {
	
	public int copyOsTemplateVolGroup(OsVolGroupModel vg);
	
	public int deleteOsVolGroup(OsVolGroupModel vg);
	
	public List<OsVolGroupModel> selectOsVolGroupList(OsVolGroupModel vg);
	
	public List<OsVolGroupModel> unionOsVolGroupList(OsVolGroupModel vg);
}
