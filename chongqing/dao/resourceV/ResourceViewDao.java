package dao.resourceV;

import java.sql.SQLException;
import java.util.List;

/**
 * <p>Title: ResourceView</p>
 * <p>Description: 资源视图
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: SI-TECH </p>
 * @author chenyu
 * @version 1.0
 * @createtime 2014-8-28 下午4:06:54
 *
 */
public interface ResourceViewDao {

	/**
	 * @Title: queryResourceViewObj
	 * @Description: 通过key查询资源信息
	 * @param entityId
	 * @return
	 * @return ResourceViewObj
	 * @throws
	 * @author chenyu
	 * @version 1.0
	 * @throws SQLException 
	 * @createtime 2014-8-28 下午4:14:49
	 */
	public ResourceViewObj queryResourceViewObj(String entityId) throws SQLException;
	
	
	/**
	 * @Title: queryResourceViewObj
	 * @Description: 通过key查询资源信息
	 * @param entityId
	 * @return
	 * @return ResourceViewObj
	 * @throws
	 * @author chenyu
	 * @version 1.0
	 * @throws SQLException 
	 * @createtime 2014-8-28 下午4:14:49
	 */
	public ResourceViewObj queryResourceViewObj(ResourceViewObj obj) throws SQLException;
	
	/**
	 * @Title: queryResourceViewObj
	 * @Description: 通过key查询资源信息集合
	 * @param entityId
	 * @return
	 * @return ResourceViewObj
	 * @throws
	 * @author chenyu
	 * @version 1.0
	 * @throws SQLException 
	 * @createtime 2014-8-28 下午4:14:49
	 */
	public List<ResourceViewObj> queryResourceViewObjs(ResourceViewObj obj) throws SQLException;

}
