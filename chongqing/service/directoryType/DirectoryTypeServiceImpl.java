package service.directoryType;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.directoryType.DirectoryTypeDao;
import domain.directoryType.DirectoryTypeObj;

/**
 * @author chenyu
 *
 */
@Service("directoryTypeService")
public class DirectoryTypeServiceImpl implements DirectoryTypeService {

	@Autowired
	private DirectoryTypeDao directoryTypeDao;
	
	@Override
	public int insertByObj(DirectoryTypeObj directoryTypeObj) throws SQLException {
		return directoryTypeDao.insertByObj(directoryTypeObj);
	}

	@Override
	public List<DirectoryTypeObj> queryForList(DirectoryTypeObj directoryTypeObj) throws SQLException {
		return directoryTypeDao.queryForList(directoryTypeObj);
	}

	@Override
	public int deleteByObj(DirectoryTypeObj directoryTypeObj) throws SQLException {
		return directoryTypeDao.deleteByObj(directoryTypeObj);
	}

	@Override
	public int updateByObj(DirectoryTypeObj directoryTypeObj) throws SQLException {
		return directoryTypeDao.updateByObj(directoryTypeObj);
	}

}
