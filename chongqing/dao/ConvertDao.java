package dao;

import java.util.List;

import com.sitech.basd.yicloud.domain.datastore.DataStoreObj;

import domain.ConvertObj;


public interface ConvertDao {
	
	public int insertByObj(ConvertObj convertObj);
	
	public List<ConvertObj> queryForList(ConvertObj convertObj);
	
	public int deleteByObj(ConvertObj obj);
	
	public int updateByObj(ConvertObj obj);
	
	/**
	 * 
	 * @Title: queryHostStore
	 * @Description: 查询主机存储总量和空闲量
	 * @param
	 * @return ConvertObj
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-2-19 下午6:45:21
	 */
	public ConvertObj queryHostStore(DataStoreObj obj);
}
