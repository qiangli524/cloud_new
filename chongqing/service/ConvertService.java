package service;

import java.util.List;

import com.sitech.basd.yicloud.domain.datastore.DataStoreObj;

import domain.ConvertObj;

public interface ConvertService {
	public int insertByObj(ConvertObj convertObj);
	
	public List<ConvertObj> queryForList(ConvertObj convertObj);
	
	public int deleteByObj(ConvertObj obj);
	
	public int updateByObj(ConvertObj obj);
	
	public ConvertObj queryHostStore(DataStoreObj obj);
}