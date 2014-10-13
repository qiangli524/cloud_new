package dao.directoryType;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.utils.randomid.RandomUUID;

import domain.directoryType.DirectoryTypeObj;

/**
 * @author chenyu
 *
 */
@Service("DirectionTypeDao")
public class DirectionTypeDaoImpl extends BaseDao implements DirectoryTypeDao {

	@Override
	public int insertByObj(DirectoryTypeObj directoryTypeObj) throws SQLException {
		directoryTypeObj.setId(RandomUUID.getUuid());
		int result = 0;
		try {
			Object result_obj = getSqlMap().insert("DirectionType.insertByObj", directoryTypeObj);
			if (null != result_obj) {
				result = Integer.parseInt(result_obj.toString());
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("DirectionType.insertByObj:" + e.getMessage()
					+ e.getClass().getName());
			throw e;
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DirectoryTypeObj> queryForList(DirectoryTypeObj directoryTypeObj) throws SQLException {
		List<DirectoryTypeObj> list;
		try {
			 list = getSqlMapClient().queryForList("DirectoryType.queryForList", directoryTypeObj);
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("DirectoryType.queryForList:" + e.getMessage()
					+ e.getClass().getName());
			throw e;
		}
		return list;
	}

	@Override
	public int deleteByObj(DirectoryTypeObj directoryTypeObj) throws SQLException {
		int result = 0;
		try {
			Object result_obj = getSqlMap().delete("DirectionType.deleteByObj", directoryTypeObj);
			if (null != result_obj) {
				result = Integer.parseInt(result_obj.toString());
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("DirectionType.deleteByObj:" + e.getMessage()
					+ e.getClass().getName());
			throw e;
		}
		return result;
	}

	@Override
	public int updateByObj(DirectoryTypeObj directoryTypeObj) throws SQLException {
		if (null == directoryTypeObj) {
			throw new RuntimeException("更新的字典对象为空，不能保存");
		}
		if (StringUtils.isEmpty(directoryTypeObj.getId())) {
			throw new RuntimeException("更新的字典对象id为空，不能保存");
		}
		int result = 0;
		try {
			result = getSqlMapClient().update("DirectionType.updateByObj", directoryTypeObj);
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("DirectionType.updateByObj:" + e.getMessage()
					+ e.getClass().getName());
			throw e;
		}
		return result;
	}

}
