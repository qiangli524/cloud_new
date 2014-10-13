package com.sitech.basd.sxcloud.rsmu.service.deploy.version;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.domain.deploy.version.TbCloud3AppVersionVO;

public interface VersionResumeService {

	/** 
	 * 
	 * @Title: queryForListByObj
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param
	 * @return List
	 * @throws
	 * @author wangqxa
	 * @version 1.0
	 * @createtime 2013-3-25 下午2:50:01
	 */
	public List queryForListByObj(TbCloud3AppVersionVO obj);
	 /**
     * @Title:查询出具体异常告警信息
     * @Copyright: Copyright (c) 201006
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public TbCloud3AppVersionVO queryVOByObj(TbCloud3AppVersionVO obj);
	 

	 /**
     * @Title:插入异常告警信息
     * @Copyright: Copyright (c) 201006
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public String insertByObj(TbCloud3AppVersionVO obj);


}
