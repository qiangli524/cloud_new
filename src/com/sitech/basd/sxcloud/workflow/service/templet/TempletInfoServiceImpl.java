package com.sitech.basd.sxcloud.workflow.service.templet;

import java.util.HashMap;
import java.util.List;

import com.sitech.basd.sxcloud.workflow.dao.templet.TempletInfoDao;
import com.sitech.basd.sxcloud.workflow.domain.templet.TempletInfoObj;

public class TempletInfoServiceImpl implements TempletInfoService {
	/**
	 * @Title:保存修改后的模板信息
	 * @Copyright: Copyright (c) 201107
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 * @return
	 */
	public int saveTempletInfo(TempletInfoObj obj) {
		return templetInfoDao.saveTempletInfo(obj);
	}

	/**
	 * @Title:删除模板信息
	 * @Copyright: Copyright (c) 201107
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 * @return
	 */
	public int deleteTempletInfo(String requestNo) {
		return templetInfoDao.deleteTempletInfo(requestNo);
	}

	/**
	 * @Title:查询模板列表
	 * @Copyright: Copyright (c) 201107
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 * @return
	 */
	public List<TempletInfoObj> queryTempletList(TempletInfoObj obj) {
		return templetInfoDao.queryTempletList(obj);
	}

	/**
	 * @Title:查询并返回一个模板对象
	 * @Copyright: Copyright (c) 2012-01-13
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */

	public TempletInfoObj queryByObj(TempletInfoObj obj) {
		return templetInfoDao.queryByObj(obj);
	}

	/**
	 * @Title:根据资源类别编号查询资源信息
	 * @Copyright: Copyright (c) 2012-3
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 * @return
	 */
	public TempletInfoObj queryResourceListInfo(String num) {
		return templetInfoDao.queryResourceListInfo(num);
	}

	/**
	 * @Title:得到多个KV值
	 * @Copyright: Copyright (c) 2012-3
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 * @return
	 */

	public HashMap getKvMap(TempletInfoObj obj) {
		HashMap kvmap = new HashMap();
		if (obj.getKV1() != null) {
			kvmap.put(obj.getKV1().split(":")[0], obj.getKV1().split(":")[1]);
		}
		if (obj.getKV2() != null) {
			kvmap.put(obj.getKV2().split(":")[0], obj.getKV2().split(":")[1]);
		}
		if (obj.getKV3() != null) {
			kvmap.put(obj.getKV3().split(":")[0], obj.getKV3().split(":")[1]);
		}
		if (obj.getKV4() != null) {
			kvmap.put(obj.getKV4().split(":")[0], obj.getKV4().split(":")[1]);
		}
		if (obj.getKV5() != null) {
			kvmap.put(obj.getKV5().split(":")[0], obj.getKV5().split(":")[1]);
		}
		if (obj.getKV6() != null) {
			kvmap.put(obj.getKV6().split(":")[0], obj.getKV6().split(":")[1]);
		}
		if (obj.getKV7() != null) {
			kvmap.put(obj.getKV7().split(":")[0], obj.getKV7().split(":")[1]);
		}
		if (obj.getKV8() != null) {
			kvmap.put(obj.getKV8().split(":")[0], obj.getKV8().split(":")[1]);
		}
		if (obj.getKV9() != null) {
			kvmap.put(obj.getKV9().split(":")[0], obj.getKV9().split(":")[1]);
		}
		if (obj.getKV10() != null) {
			kvmap.put(obj.getKV10().split(":")[0], obj.getKV10().split(":")[1]);
		}
		if (obj.getKV11() != null) {
			kvmap.put(obj.getKV11().split(":")[0], obj.getKV11().split(":")[1]);
		}
		return kvmap;
	}

	TempletInfoDao templetInfoDao;

	public void setTempletInfoDao(TempletInfoDao templetInfoDao) {
		this.templetInfoDao = templetInfoDao;
	}

}
