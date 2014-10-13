package com.sitech.ssd.sc.os.service;

import java.util.List;

import com.sitech.ssd.sc.os.domain.OsPartModel;

/**
 * 
 * @ClassName: OsPartService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author JamTau
 * @date 2014-9-17 上午12:58:31
 *
 */
public interface OsPartService {
	
	public int copyOsTemplatePart(OsPartModel part);
	
	public int deleteAllOsPart(OsPartModel part);
	
	public int deleteOsTemplatePart(OsPartModel part);
	
	public List<OsPartModel> queryOsPartList(OsPartModel part);
	
	public List<OsPartModel> unionOsPartList(OsPartModel part);
}
