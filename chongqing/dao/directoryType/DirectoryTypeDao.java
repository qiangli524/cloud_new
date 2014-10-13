/**
 * @author chenyu:下午4:05:55
 */
package dao.directoryType;

import java.sql.SQLException;
import java.util.List;

import domain.directoryType.DirectoryTypeObj;


/**
 * @author chenyu
 * 数据字典类型维护Dao
 */
public interface DirectoryTypeDao {
	
	
	/**
	 * 增
	 * @author chenyu:下午4:12:58
	 * @param directoryTypeObj
	 * @return
	 * @throws SQLException 
	 */
	public int insertByObj(DirectoryTypeObj directoryTypeObj) throws SQLException;

	/**
	 * 查
	 * @author chenyu:下午4:12:58
	 * @param directoryTypeObj
	 * @return
	 * @throws SQLException 
	 */
	public List<DirectoryTypeObj> queryForList(DirectoryTypeObj directoryTypeObj) throws SQLException;

	/**
	 * 删
	 * @author chenyu:下午4:12:58
	 * @param directoryTypeObj
	 * @return
	 * @throws SQLException 
	 */
	public int deleteByObj(DirectoryTypeObj directoryTypeObj) throws SQLException;

	/**
	 * 改
	 * @author chenyu:下午4:12:58
	 * @param directoryTypeObj
	 * @return
	 * @throws SQLException 
	 */
	public int updateByObj(DirectoryTypeObj directoryTypeObj) throws SQLException;
}
