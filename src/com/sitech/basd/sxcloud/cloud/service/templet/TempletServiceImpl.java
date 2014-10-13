package com.sitech.basd.sxcloud.cloud.service.templet;

import java.util.HashMap;
import java.util.List;

import com.sitech.basd.sxcloud.cloud.dao.templet.TempletDao;
import com.sitech.basd.sxcloud.cloud.domain.image.TbCloud2ImageInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.templet.TempletObj;
import com.sitech.basd.sxcloud.cloud.domain.templet.TempletTypeObj;
import com.sitech.basd.sxcloud.rsmu.service.BaseService;
import com.sitech.basd.yicloud.domain.vmman.VMHostObj;


public class TempletServiceImpl extends BaseService implements TempletService {

	/**
	 * @Title:删除已有模板
	 * @Copyright: Copyright (c) 2012-01-13
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public int deleteByObj(TempletObj obj) {
		/*
		 * boolean flag = false; { //默认从接口获取数据 //获取数据成功，则flag设为true } if(!flag){
		 * return templetDao.deleteByObj(obj); }
		 */
		return templetDao.deleteByObj(obj);
	}

	/**
	 * @Title:创建模板
	 * @Copyright: Copyright (c) 2012-01-13
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public int insertByObj(TempletObj obj) {
		/*
		 * boolean flag = false; { //默认从接口获取数据 //获取数据成功，则flag设为true } if(!flag){
		 * return templetDao.insertByObj(obj); }
		 */
		return templetDao.insertByObj(obj);
	}

	/**
	 * @Title:查询并返回一个模板对象
	 * @Copyright: Copyright (c) 2012-01-13
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public TempletObj queryByObj(TempletObj obj) {
		/*
		 * boolean flag = false; { //默认从接口获取数据 //获取数据成功，则flag设为true } if(!flag){
		 * return templetDao.queryByObj(obj); }
		 */
		return templetDao.queryByObj(obj);
	}

	/**
	 * @Title:查询已有模板列表
	 * @Copyright: Copyright (c) 2012-01-13
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public List queryForListByObj(TempletObj obj) {
		/*
		 * boolean flag = false; { //默认从接口获取数据 //获取数据成功，则flag设为true } if(!flag){
		 * return tbVirtualDao.queryForListByObj(obj); }
		 */
		return templetDao.queryForListByObj(obj);
	}
	/**
	 * @Title:查询需要的模板
	 * @Copyright: Copyright (c) 2013-03-05
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	@Override
	public List queryForListByType(TempletObj obj) {
		// TODO Auto-generated method stub
		return templetDao.queryForListByType(obj);
	}

	/**
	 * @Title:查询资源类别
	 * @Copyright: Copyright (c) 2012-01-13
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public List queryResourceType() {
		/*
		 * boolean flag = false; { //默认从接口获取数据 //获取数据成功，则flag设为true } if(!flag){
		 * return tbVirtualDao.queryForListByObj(obj); }
		 */
		return templetDao.queryResourceType();
	}

	/**
	 * @Title:查询所选资源类别对应模板信息
	 * @Copyright: Copyright (c) 2012-01-13
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public List queryResourceTypeTemplet(String TYPE) {
		return templetDao.queryResourceTypeTemplet(TYPE);
	}

	/**
	 * @Title:查询已发布的应用部署模板列表
	 * @Copyright: Copyright (c) 2012-03-10
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public List queryAppForListByObj(TempletObj obj) {
		return templetDao.queryAppForListByObj(obj);
	}

	/**
	 * @Title:更新模板信息
	 * @Copyright: Copyright (c) 2012-01-13
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public int updateByObj(TempletObj obj) {
		/*
		 * boolean flag = false; { //默认从接口获取数据 //获取数据成功，则flag设为true } if(!flag){
		 * return tbVirtualDao.deleteByObj(obj); }
		 */
		return templetDao.updateByObj(obj);

	}

	/**
	 * @Title:发布模板信息
	 * @Copyright: Copyright (c) 2012-02-13
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public int releaseByObj(TempletObj obj) {
		/*
		 * boolean flag = false; { //默认从接口获取数据 //获取数据成功，则flag设为true } if(!flag){
		 * return tbVirtualDao.deleteByObj(obj); }
		 */
		return templetDao.releaseByObj(obj);

	}

	/**
	 * @Title:查询已有模板配置
	 * @Copyright: Copyright (c) 2012-01-13
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public List queryConByObjBase(TempletObj obj) {
		/*
		 * boolean flag = false; { //默认从接口获取数据 //获取数据成功，则flag设为true } if(!flag){
		 * return tbVirtualDao.queryForListByObj(obj); }
		 */
		return templetDao.queryConByObjBase(obj);
	}

	/**
	 * @Title:查询模板类型配置
	 * @Copyright: Copyright (c) 2012-01-13
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public List queryTypeListByObj(TempletObj obj) {

		return templetDao.queryTypeListByObj(obj);
	}

	/**
	 * @Title:查询模板配置并将其中的key和value存放在一个MAP 中
	 * @Copyright: Copyright (c) 2012-03-14
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public HashMap queryType(TempletObj obj) {
		HashMap map = new HashMap();
		List<TempletObj> list = this.queryConByObjBase(obj);
		if (list != null && list.size() > 0) {
			for (TempletObj tempObj : list) {
				map.put(tempObj.getKEY(), tempObj.getVALUE());
			}
		}
		return map;
	}

	/**
	 * @Title:将查询的模板中的KV1..KVn分开放在MAP中
	 * @Copyright: Copyright (c) 2012-03-14
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public HashMap getKvMap(TempletObj obj) {
		HashMap kvmap = new HashMap();
		TempletObj tempObj = this.queryByObj(obj);
		if (tempObj.getKV1() != null) {
			kvmap.put(tempObj.getKV1().split(":")[0], tempObj.getKV1().split(
					":")[1]);
		}
		if (tempObj.getKV2() != null) {
			kvmap.put(tempObj.getKV2().split(":")[0], tempObj.getKV2().split(
					":")[1]);
		}
		if (tempObj.getKV3() != null) {
			kvmap.put(tempObj.getKV3().split(":")[0], tempObj.getKV3().split(
					":")[1]);
		}
		if (tempObj.getKV4() != null) {
			kvmap.put(tempObj.getKV4().split(":")[0], tempObj.getKV4().split(
					":")[1]);
		}
		if (tempObj.getKV5() != null) {
			kvmap.put(tempObj.getKV5().split(":")[0], tempObj.getKV5().split(
					":")[1]);
		}
		if (tempObj.getKV6() != null) {
			kvmap.put(tempObj.getKV6().split(":")[0], tempObj.getKV6().split(
					":")[1]);
		}
		if (tempObj.getKV7() != null) {
			kvmap.put(tempObj.getKV7().split(":")[0], tempObj.getKV7().split(
					":")[1]);
		}
		if (tempObj.getKV8() != null) {
			kvmap.put(tempObj.getKV8().split(":")[0], tempObj.getKV8().split(
					":")[1]);
		}
		if (tempObj.getKV9() != null) {
			kvmap.put(tempObj.getKV9().split(":")[0], tempObj.getKV9().split(
					":")[1]);
		}
		if (tempObj.getKV10() != null) {
			kvmap.put(tempObj.getKV10().split(":")[0], tempObj.getKV10().split(
					":")[1]);
		}
		if (tempObj.getKV11() != null) {
			kvmap.put(tempObj.getKV11().split(":")[0], tempObj.getKV11().split(
					":")[1]);
		}
		if (tempObj.getKV12() != null) {
			kvmap.put(tempObj.getKV12().split(":")[0], tempObj.getKV12().split(
					":")[1]);
		}
		if (tempObj.getKV13() != null) {
			kvmap.put(tempObj.getKV13().split(":")[0], tempObj.getKV13().split(
					":")[1]);
		}
		if (tempObj.getKV14() != null) {
			kvmap.put(tempObj.getKV14().split(":")[0], tempObj.getKV14().split(
					":")[1]);
		}
		if (tempObj.getKV15() != null) {
			kvmap.put(tempObj.getKV15().split(":")[0], tempObj.getKV15().split(
					":")[1]);
		}
		if (tempObj.getKV16() != null) {
			kvmap.put(tempObj.getKV16().split(":")[0], tempObj.getKV16().split(
					":")[1]);
		}
		if (tempObj.getKV17() != null) {
			kvmap.put(tempObj.getKV17().split(":")[0], tempObj.getKV17().split(
					":")[1]);
		}
		if (tempObj.getKV18() != null) {
			kvmap.put(tempObj.getKV18().split(":")[0], tempObj.getKV18().split(
					":")[1]);
		}
		if (tempObj.getKV19() != null) {
			kvmap.put(tempObj.getKV19().split(":")[0], tempObj.getKV19().split(
					":")[1]);
		}
		if (tempObj.getKV20() != null) {
			kvmap.put(tempObj.getKV20().split(":")[0], tempObj.getKV20().split(
					":")[1]);
		}

		return kvmap;
	}

	/**
	 * @Title:查询模板操作历史
	 * @Copyright: Copyright (c) 2012-02-22
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */

	@SuppressWarnings("unchecked")
	public List queryHisForListByObj(TempletObj obj) {
		return templetDao.queryHisForListByObj(obj);
	}

	/**
	 * @Title:插入操作模板日志
	 * @Copyright: Copyright (c) 2012-02-22
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */

	public int insertHisByObj(TempletObj obj) {
		/*
		 * boolean flag = false; { //默认从接口获取数据 //获取数据成功，则flag设为true } if(!flag){
		 * return templetDao.insertByObj(obj); }
		 */
		return templetDao.insertHisByObj(obj);
	}
	
	/**
	 * 
	 * @Title: 查看镜像类型
	 * @Copyright: Copyright (c) 2012-9-12
	 * @Company: si-tech
	 * @author hehui
	 * @version 1.0
	 */
	public List queryTempletTypeList(TempletTypeObj obj){
		return templetDao.queryTempletTypeList(obj);
	}
	/**
	 * 
	 * @Title: 增加镜像类型
	 * @Copyright: Copyright (c) 2012-9-12
	 * @Company: si-tech
	 * @author hehui
	 * @version 1.0
	 */
	public int insertByTempletTypeObj(TempletTypeObj obj){
		return templetDao.insertByTempletTypeObj(obj);
	}
	
	/**
	 * 
	 * @Title: 修改镜像类型
	 * @Copyright: Copyright (c) 2012-9-12
	 * @Company: si-tech
	 * @author hehui
	 * @version 1.0
	 */
	public int updateByTempletTypeObj(TempletTypeObj obj){
		return templetDao.updateByTempletTypeObj(obj);
	}
	
	/**
	 * 
	 * @Title: 删除镜像类型
	 * @Copyright: Copyright (c) 2012-9-12
	 * @Company: si-tech
	 * @author hehui
	 * @version 1.0
	 */
	public int deleteByTempletTypeObj(TempletTypeObj obj){
		return templetDao.deleteByTempletTypeObj(obj);
	}
	
	/**
	 * 
	 * @Title: 显示某个类型对应的所有镜像
	 * @Copyright: Copyright (c) 2012-9-12
	 * @Company: si-tech
	 * @author hehui
	 * @version 1.0
	 */
	public List queryForListByImageType(VMHostObj obj){
		return templetDao.queryForListByImageType(obj);
	}
	
	/**
	 * 
	 * @Title: 根据镜像类型查询镜像列表
	 * @Copyright: Copyright (c) 2012-9-14
	 * @Company: si-tech
	 * @author hehui
	 * @version 1.0
	 */
	public List queryTempletTypeListByType(TempletTypeObj obj){
		return templetDao.queryTempletTypeListByType(obj);
	}
	
	/**
	 * 
	 * @Title: 显示除某个类型外的所有镜像
	 * @Copyright: Copyright (c) 2012-9-20
	 * @Company: si-tech
	 * @author hehui
	 * @version 1.0
	 */
	public List queryForListByNOEqualType(VMHostObj obj){
		return templetDao.queryForListByNOEqualType(obj);
	}
	
	/**
	 * 
	 * @Title: 绑定镜像
	 * @Copyright: Copyright (c) 2012-9-20
	 * @Company: si-tech
	 * @author hehui
	 * @version 1.0
	 */
	public int bindingImage(VMHostObj obj){
		return templetDao.bindingImage(obj);
	}
	@Override
	public List queryForListValue(TempletObj obj) {
		// TODO Auto-generated method stub
		return templetDao.queryForListValue(obj);
	}
	/**
	 * @Title:修改模板的KV
	 * @Copyright: Copyright (c) 2013-03-07
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	
	@Override
	public int updateStrategy(TempletObj obj) {
		// TODO Auto-generated method stub
		return templetDao.updateStrategy(obj);
	}

	TempletDao templetDao;

	public void setTempletDao(TempletDao templetDao) {
		this.templetDao = templetDao;
	}

	public TempletDao getTempletDao() {
		return templetDao;
	}
	



}
