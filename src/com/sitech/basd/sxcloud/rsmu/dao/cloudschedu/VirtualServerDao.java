package com.sitech.basd.sxcloud.rsmu.dao.cloudschedu;

import java.util.List;

import com.sitech.basd.sxcloud.rsmu.domain.cloudschedu.VirtualServerObj;

public interface VirtualServerDao {

	/**
     * @Title:根据虚拟服务器部分信息查询匹配的所有虚拟服务器信息
     * @Copyright: Copyright (c) 201007
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public List queryForListByObj(VirtualServerObj obj);
	 /**
     * @Title:查询出具体虚拟服务器信息
     * @Copyright: Copyright (c) 201007
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public VirtualServerObj queryByObj(VirtualServerObj obj);
	 /**
     * @Title:更新虚拟服务器信息
     * @Copyright: Copyright (c) 201007
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public int updateByObj(VirtualServerObj obj);
	 /**
     * @Title:删除虚拟服务器信息
     * @Copyright: Copyright (c) 201007
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public int deleteByObj(VirtualServerObj obj);
	 /**
     * @Title:插入虚拟服务器信息
     * @Copyright: Copyright (c) 201007
     * @Company: si-tech
     * @author yangwenchao
     * @version 1.0
    */
	public int insertByObj(VirtualServerObj obj);

}
