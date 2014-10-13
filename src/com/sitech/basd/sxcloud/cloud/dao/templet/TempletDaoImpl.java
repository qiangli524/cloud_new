package com.sitech.basd.sxcloud.cloud.dao.templet;

import java.util.List;

import com.sitech.basd.sxcloud.cloud.domain.templet.TempletObj;
import com.sitech.basd.sxcloud.cloud.domain.templet.TempletTypeObj;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.basd.yicloud.domain.vmman.VMHostObj;


public class TempletDaoImpl extends BaseDao implements TempletDao {
	/**
	 * @Title:查询已有模板列表
	 * @Copyright: Copyright (c) 2012-01-13
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public List queryForListByObj(TempletObj obj) {
		List lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"Templet.queryByObjForCount", obj)).intValue());
			}
			lst = getSqlMap().queryForList("Templet.queryForListByObj", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("Templet.queryForListByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
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
		List lst = null;
		try {
			lst = getSqlMap().queryForList("Templet.queryForListByType");
		} catch (Exception sqlexception) {
			LogHelper.error("Templet.queryForListByType:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;

	}
	@Override
	public List queryForListValue(TempletObj obj) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("Templet.queryForListValue",obj);
		} catch (Exception sqlexception) {
			LogHelper.error("Templet.queryForListValue:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}
	/**
	 * @Title:查询资源类别
	 * @Copyright: Copyright (c) 2012-01-13
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public List queryResourceType() {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("Templet.queryResourceType");
		} catch (Exception sqlexception) {
			LogHelper.error("Templet.queryResourceType:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;

	}

	/**
	 * @Title:查询所选资源类别对应模板信息
	 * @Copyright: Copyright (c) 2012-01-13
	 * @Company: si-tech
	 * @author taoxue
	 * @version 1.0
	 */
	public List queryResourceTypeTemplet(String TYPE) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("Templet.queryResourceTypeTemplet",
					TYPE);
		} catch (Exception sqlexception) {
			LogHelper.error("Templet.queryResourceTypeTemplet:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;

	}

	/**
	 * @Title:查询已发布的应用部署模板列表
	 * @Copyright: Copyright (c) 2012- 03-10
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public List queryAppForListByObj(TempletObj obj) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("Templet.queryAppForListByObj");
		} catch (Exception sqlexception) {
			LogHelper.error("Templet.queryAppForListByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;

	}

	/**
	 * @Title:创建模板
	 * @Copyright: Copyright (c) 2012-01-13
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public int insertByObj(TempletObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().insert("Templet.insertByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("Templet.insertByObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title:删除已有模板
	 * @Copyright: Copyright (c) 2012-01-13
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public int deleteByObj(TempletObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().delete("Templet.deleteByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("Templet.deleteByObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title:查询并返回一个模板对象
	 * @Copyright: Copyright (c) 2012-01-13
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public TempletObj queryByObj(TempletObj obj) {
		List lst = null;
		TempletObj tempObj = null;
		lst = queryForListByObj(obj);
		if (lst != null && lst.size() > 0) {
			tempObj = (TempletObj) lst.get(0);
		}
		return tempObj;
	}

	/**
	 * @Title:更新模板信息
	 * @Copyright: Copyright (c) 2012-01-13
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public int updateByObj(TempletObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("Templet.updateByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("Templet.updateByObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
	}

	/**
	 * @Title:发布模板信息
	 * @Copyright: Copyright (c) 2012-01-13
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public int releaseByObj(TempletObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().update("Templet.releaseByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("Templet.releaseByObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
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
		List lst = null;
		try {
			lst = getSqlMap().queryForList("Templet.queryConByObjBase", obj);

		} catch (Exception sqlexception) {
			LogHelper.error("Templet.queryConByObjBase:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * @Title:查询模板类型配置
	 * @Copyright: Copyright (c) 2012-02-20
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public List queryTypeListByObj(TempletObj obj) {
		List lst = null;
		try {
			lst = getSqlMap().queryForList("Templet.queryTypeListByObj", obj);

		} catch (Exception sqlexception) {
			LogHelper.error("Templet.queryTypeListByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
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
		List lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"Templet.queryHisByObjForCount", obj))
								.intValue());
			}
			lst = getSqlMap().queryForList("Templet.queryHisForListByObj", obj);

		} catch (Exception sqlexception) {
			LogHelper.error("Templet.queryHisForListByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * @Title:插入操作模板日志
	 * @Copyright: Copyright (c) 2012-02-22
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */

	public int insertHisByObj(TempletObj obj) {
		int ret = 0;
		try {
			Object o = getSqlMap().insert("Templet.insertHisByObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("Templet.insertHisByObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
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
		List lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"TempletType.queryTempletTypeForCount", obj))
								.intValue());
			}
			lst = getSqlMap().queryForList("TempletType.queryTempletTypeList", obj);

		} catch (Exception sqlexception) {
			LogHelper.error("TempletType.queryTempletTypeList:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
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
		int ret = 0;
		try {
			Object o = getSqlMap().insert("TempletType.insertByTempletTypeObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("TempletType.insertByTempletTypeObj:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return ret;
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
		int ret = 0;
		try {
			Object o = getSqlMap().update("TempletType.updateByTempletTypeObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("TempletType.updateByTempletTypeObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
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
		int ret = 0;
		try {
			Object o = getSqlMap().delete("TempletType.deleteByTempletTypeObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("TempletType.deleteByTempletTypeObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
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
		List lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"TempletType.queryForCountByImageType", obj))
								.intValue());
			}
			lst = getSqlMap().queryForList("TempletType.queryForListByImageType", obj);

		} catch (Exception sqlexception) {
			LogHelper.error("TempletType.queryForListByImageType:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
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
		List lst = null;
		try {
			lst = getSqlMap().queryForList("TempletType.queryTempletTypeListByType", obj);

		} catch (Exception sqlexception) {
			LogHelper.error("TempletType.queryTempletTypeListByType:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
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
		List lst = null;
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"TempletType.queryForCountByNOEqualType", obj))
								.intValue());
			}
			lst = getSqlMap().queryForList("TempletType.queryForListByNOEqualType", obj);

		} catch (Exception sqlexception) {
			LogHelper.error("TempletType.queryForListByNOEqualType:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
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
		int ret = 0;
		try {
			Object o = getSqlMap().update("TempletType.updateByVMHostObj", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("TempletType.updateByVMHostObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
	}
	/**
	 * @Title:修改模板的KV值
	 * @Copyright: Copyright (c) 2013-03-07
	 * @Company: si-tech
	 * @author xumq
	 * @version 1.0
	 */
	public int updateStrategy(TempletObj obj) {
		int ret =0;
		try {
			Object o = getSqlMap().update("Templet.updateStrategy", obj);
			if (o != null) {
				ret = Integer.parseInt(o.toString());
			}
		} catch (Exception sqlexception) {
			ret = -1;
			LogHelper.error("Templet.updateStrategy:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return ret;
	}
	
}
