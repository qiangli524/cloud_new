package com.sitech.ssd.sc.os.dao;

import java.util.List;

import com.sitech.ssd.sc.os.domain.OsPartModel;

/**
 * 
 * @ClassName: OsPartDao
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author JamTau
 * @date 2014-9-17 上午1:03:16
 *
 */
public interface OsPartDao {
	
	public int copyOsTemplatePart(OsPartModel part);
	
	public int deleteOsPart(OsPartModel part);
	
	public List<OsPartModel> selectOsPartList(OsPartModel part);
	
	public List<OsPartModel> unionOsPartList(OsPartModel part);
	
}
