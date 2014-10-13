package com.sitech.basd.yicloud.service.deploy;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.sitech.basd.yicloud.dao.deploy.TbCloud2DepFileDao;
import com.sitech.basd.yicloud.domain.deploy.TbCloud2DepFileObj;

public class TbCloud2DepFileServiceImpl implements TbCloud2DepFileService {

	/**
	 * @Title:插入部署文件信息
	 * @Copyright: Copyright (c) 201206
	 * @Company: si-tech
	 * @author yangwca
	 * @version 1.0
	 */
	public int insertByObj(List<String> hostlist,int appid,List<Integer> filelist) {
		TbCloud2DepFileObj depFileObj=new TbCloud2DepFileObj();
		int retStr=1;
		Date d = new Date();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		for(String hostip : hostlist){
			//通过序列得到批次号
			try{
				//int bathid=tbCloud2DepFileDao.queryBatchID();
				for(Integer file : filelist){
					depFileObj.setID(11);
					depFileObj.setHOSTIP(hostip);
					depFileObj.setAPP_ID(appid);
					depFileObj.setFILE_ID(file);
					for(int i=0;i<20000;i++){
						tbCloud2DepFileDao.insertByObj(depFileObj);
					}
				}
			}catch(Exception e){
				e.printStackTrace();
				retStr=0;
			}
		}
		Date d1 = new Date();
		DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		return retStr;
	}

	private TbCloud2DepFileDao tbCloud2DepFileDao;

	public TbCloud2DepFileDao getTbCloud2DepFileDao() {
		return tbCloud2DepFileDao;
	}

	public void setTbCloud2DepFileDao(TbCloud2DepFileDao tbCloud2DepFileDao) {
		this.tbCloud2DepFileDao = tbCloud2DepFileDao;
	}
	
}
