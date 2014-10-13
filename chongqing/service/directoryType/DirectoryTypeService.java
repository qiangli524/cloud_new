package service.directoryType;

import java.sql.SQLException;
import java.util.List;

import domain.directoryType.DirectoryTypeObj;

/**
 * @author chenyu
 *
 */
public interface DirectoryTypeService {

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
