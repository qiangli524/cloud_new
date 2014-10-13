package dao.tree;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.utils.randomid.RandomUUID;

import domain.service.ServiceObj;
import domain.tree.HadoopTreeObj;

@SuppressWarnings("all")
@Repository("hadoopTreeDao")
public class HadoopTreeDaoImpl extends BaseDao implements HadoopTreeDao {
	/**
	 * 
	 * @Title: queryForListByObj
	 * @Description: 查询列表
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-1-4 下午8:53:20
	 */
	public List queryForListByObj(HadoopTreeObj obj) {
		List list = new ArrayList();
		try {
			list = getSqlMap().queryForList("HadoopTree.queryForListByObj", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("HadoopTree.queryForListByObj:" + sqlexception.getMessage()
					+ getClass().getName());
		}
		return list;
	}

	/**
	 * @Title: insertByObj
	 * @Description: 插入一条记录
	 * @param
	 * @return int
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-5 下午2:07:36
	 */
	@Override
	public int insertByObj(HadoopTreeObj hadoopTreeObj) {
		int ret = 0;
		hadoopTreeObj.setId(RandomUUID.getUuid());
		try {
			Object obj = getSqlMap().insert("HadoopTree.insertByObj", hadoopTreeObj);
			if (obj != null) {
				ret = (Integer) obj;
			}
		} catch (Exception e) {
			LogHelper.error("HadoopTree.insertByObj: " + e.getMessage() + e.getClass().getName());
			ret = -1;
		}
		return ret;
	}

	/**
	 * @Title: updateByObj
	 * @Description: 更新一条记录
	 * @param
	 * @return int
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-5 下午2:07:59
	 */
	@Override
	public int updateByObj(HadoopTreeObj hadoopTreeObj) {
		int ret = 0;
		try {
			Object obj = getSqlMap().update("HadoopTree.updateByObj", hadoopTreeObj);
			if (obj != null) {
				ret = (Integer) obj;
			}
		} catch (Exception e) {
			LogHelper.error("HadoopTree.updateByObj: " + e.getMessage() + e.getClass().getName());
			ret = -1;
		}
		return ret;
	}

	/**
	 * @Title: deleteByObj
	 * @Description: 删除一条记录
	 * @param
	 * @return int
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-5 下午2:08:14
	 */
	@Override
	public int deleteByObj(HadoopTreeObj hadoopTreeObj) {
		int ret = 0;
		try {
			Object obj = getSqlMap().delete("HadoopTree.deleteByObj", hadoopTreeObj);
			if (obj != null) {
				ret = (Integer) obj;
			}
		} catch (Exception e) {
			LogHelper.error("HadoopTree.deleteByObj: " + e.getMessage() + e.getClass().getName());
			ret = -1;
		}
		return ret;
	}

	/**
	 * 
	 * @Title: querybyObj
	 * @Description: 查询一条父节点记录
	 * @param
	 * @return HadoopTreeObj
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-1-7 上午10:49:48
	 */
	@Override
	public HadoopTreeObj queryByObj(HadoopTreeObj hadoopTreeObj) {
		HadoopTreeObj obj = new HadoopTreeObj();
		try {
			obj = (HadoopTreeObj) getSqlMap().queryForObject(
					"HadoopTree.queryByObj", hadoopTreeObj);
		} catch (Exception e) {
			LogHelper.error("HadoopTree.queryByObj: " + e.getMessage()
					+ e.getClass().getName());
		}
		return obj;
	}

	/**
	 * @Title: queryForServiceList
	 * @Description: 查询服务集合
	 * @param
	 * @return List<ServiceNodeObj>
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2014-2-20 下午4:08:59
	 */
	@Override
	public List<ServiceObj> queryForServiceList(HadoopTreeObj treeObj) {
		List<ServiceObj> list = new ArrayList<ServiceObj>();
		try {
			list = getSqlMap().queryForList("HadoopTree.queryForServiceList", treeObj);
		} catch (Exception e) {
			LogHelper.error("HadoopTree.queryForServiceList: " + e.getMessage() + e.getClass().getName());
		}
		return list;
	}
}
