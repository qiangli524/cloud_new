package com.sitech.basd.sxcloud.rsmu.dao.softmanage;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.domain.softmanage.TbBusiAppPortObj;

public interface TbBusiAppPortDao {

	/**
     * @Title:根据端口部分信息查询匹配的所有端口信息
     * @Copyright: Copyright (c) 201007
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public List queryForListByObj(TbBusiAppPortObj obj);
	 /**
     * @Title:查询出具体端口信息
     * @Copyright: Copyright (c) 201007
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public TbBusiAppPortObj queryByObj(TbBusiAppPortObj obj);
	 /**
     * @Title:更新端口信息
     * @Copyright: Copyright (c) 201007
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public int updateByObj(TbBusiAppPortObj obj);
	 /**
     * @Title:删除端口信息
     * @Copyright: Copyright (c) 201007
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public int deleteByObj(TbBusiAppPortObj obj);
	
	 /**
     * @Title:通过应用ID删除应用下所有端口信息
     * @Copyright: Copyright (c) 201007
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public int deleteAppidByObj(TbBusiAppPortObj obj);
	
	
	 /**
     * @Title:插入端口信息
     * @Copyright: Copyright (c) 201007
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public int insertByObj(TbBusiAppPortObj obj);

}
