package com.sitech.basd.sxcloud.rsmu.service.softmanage;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.domain.softmanage.TbBusiSoftwareInfoHisObj;

public interface SoftwareInfohisService {

	/**
     * @Title:根据软件历史部分信息查询匹配的所有应用信息
     * @Copyright: Copyright (c) 201006
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public List queryForListByObj(TbBusiSoftwareInfoHisObj obj);
	 /**
     * @Title:查询出具体软件历史信息
     * @Copyright: Copyright (c) 201006
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public TbBusiSoftwareInfoHisObj queryByObj(TbBusiSoftwareInfoHisObj obj);
	 /**
     * @Title:更新软件历史信息
     * @Copyright: Copyright (c) 201006
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public int updateByObj(TbBusiSoftwareInfoHisObj obj);
	 /**
     * @Title:删除软件历史信息
     * @Copyright: Copyright (c) 201006
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public int deleteByObj(TbBusiSoftwareInfoHisObj obj);
	 /**
     * @Title:插入软件历史信息
     * @Copyright: Copyright (c) 201006
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public int insertByObj(TbBusiSoftwareInfoHisObj obj);

}
