package dao.resourceV;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Repository;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;

/**
 * <p>Title: ResourceViewDaoImpl</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: SI-TECH </p>
 * @author chenyu
 * @version 1.0
 * @createtime 2014-8-28 下午4:17:20
 *
 */
@Repository("resourceViewDao")
public class ResourceViewDaoImpl extends BaseDao implements ResourceViewDao {

	@Override
	public ResourceViewObj queryResourceViewObj(String entityId) throws SQLException {
		if (null == entityId) {
			return null;
		}
		ResourceViewObj obj = new ResourceViewObj();
		obj.setEntity_id(entityId);
		try {
			obj = queryResourceViewObj(obj);
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("查询资源错误entityId:"+entityId);
			throw e;
		}
		return obj;
	}

	@Override
	public ResourceViewObj queryResourceViewObj(ResourceViewObj obj) throws SQLException {
		if (null == obj) {
			return null;
		}
		 List<ResourceViewObj> objs = null;
		try {
			objs = queryResourceViewObjs(obj);
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("通过对象查询资源错误"+obj.getEntity_id());
			throw e;
		}
		 ResourceViewObj reObj = null;
		if (CollectionUtils.isNotEmpty(objs)) {
			if (objs.size() == 1) {
				reObj = objs.get(0);
			} else{
				logger.error("通过对象查询资源错误");
				throw new RuntimeException("通过对象查询资源,查处多条数据");
			}
		}
		return reObj;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ResourceViewObj> queryResourceViewObjs(ResourceViewObj obj) throws SQLException {
		if (null == obj) {
			obj = new ResourceViewObj();
		}
		List<ResourceViewObj> objs = null;
		try {
			objs = sqlMapClient.queryForList("ResourceView.queryResourceViewObj", obj);
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("查询资源视图错误.");
			throw e;
		}
		return objs;
	}
	
	

}
