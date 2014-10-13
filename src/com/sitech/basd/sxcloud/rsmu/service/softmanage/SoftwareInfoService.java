package com.sitech.basd.sxcloud.rsmu.service.softmanage;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.domain.softmanage.TbBusiSoftwareInfoObj;

public interface SoftwareInfoService {

	/**
     * @Title:根据软件部分信息查询匹配的所有应用信息
     * @Copyright: Copyright (c) 201006
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public List queryForListByObj(TbBusiSoftwareInfoObj obj);
	 /**
     * @Title:查询出具体软件信息
     * @Copyright: Copyright (c) 201006
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public TbBusiSoftwareInfoObj queryByObj(TbBusiSoftwareInfoObj obj);
	 /**
     * @Title:更新软件信息
     * @Copyright: Copyright (c) 201006
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public int updateByObj(TbBusiSoftwareInfoObj obj);
	 /**
     * @Title:删除软件信息
     * @Copyright: Copyright (c) 201006
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public int deleteByObj(TbBusiSoftwareInfoObj obj);
	 /**
     * @Title:插入软件信息
     * @Copyright: Copyright (c) 201006
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public int insertByObj(TbBusiSoftwareInfoObj obj);
	
	/**
	 * @Title: queryForListByObjNew
	 * @Description:根据对象查询所有
	 * @param
	 * @return List<TbBusiSoftwareInfoObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-17 下午4:37:30
	 */
	public List<TbBusiSoftwareInfoObj> queryForListByObjNew(
			TbBusiSoftwareInfoObj tbBusiSoftwareInfoObj);
	
	/**
	 * @Title: queryForListByAppidUseIn
	 * @Description: 使用in语句根据应用id集合查询符合条件的软件信息
	 * @param
	 * @return List<TbBusiSoftwareInfoObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-17 下午5:18:49
	 */
	public List<TbBusiSoftwareInfoObj> queryForListByAppidUseIn(
			TbBusiSoftwareInfoObj tbBusiSoftwareInfoObj);

}
