package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.basd.yicloud.domain.datastore.DataStoreObj;
import dao.ConvertDao;
import domain.ConvertObj;

@Service("convertService")
public class ConvertServiceImpl implements ConvertService {
	
	@Autowired
	private ConvertDao convertDao;

	@Override
	public int insertByObj(ConvertObj convertObj){
		convertObj.setState("1");
		return convertDao.insertByObj(convertObj);
	}

	@Override
	public List<ConvertObj> queryForList(ConvertObj convertObj) {
		return convertDao.queryForList(convertObj);
	}
	
	public int deleteByObj(ConvertObj obj){
		return convertDao.deleteByObj(obj);
	}
	
	public int updateByObj(ConvertObj obj) {
		return convertDao.updateByObj(obj);
	}
	
	@Override
	public ConvertObj queryHostStore(DataStoreObj obj) {
		// TODO Auto-generated method stub
		return convertDao.queryHostStore(obj);
	}
}
