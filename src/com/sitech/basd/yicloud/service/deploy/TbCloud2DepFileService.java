package com.sitech.basd.yicloud.service.deploy;

import java.util.List;

public interface TbCloud2DepFileService {
	

	/**
	 * @Title:插入部署文件信息
	 * @Copyright: Copyright (c) 201206
	 * @Company: si-tech
	 * @author yangwca
	 * @version 1.0
	 */
	public int insertByObj(List<String> hostlist,int appid,List<Integer> filelist);

}
