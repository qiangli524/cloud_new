package com.sitech.shop.dao.broadband;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.shop.domain.broadband.AclNumberObj;

/**
 * 用户购买宽带时所需要的ACL号dao实现类
 * @author duangh
 *
 */
@Repository("aclNumberDao")
public class AclNumberDaoImpl extends BaseDao implements AclNumberDao{

	@Override
	public List<AclNumberObj> queryAclNumList(AclNumberObj obj) {
		List<AclNumberObj>  resultList = getSqlMapClientTemplate().queryForList("AclNumber.queryForAclNumberList", obj);
		return resultList;
	}

	@Override
	public int updateAclNum(AclNumberObj obj) {
		int row = getSqlMapClientTemplate().update("AclNumber.updateByObj", obj);
		return row;
	}

	@Override
	public int delAclNum(AclNumberObj obj) {
		int row = getSqlMapClientTemplate().delete("AclNumber.deleteByObj", obj);
		return row;
	}

	@Override
	public int insertAclNum(AclNumberObj obj) {
		int row = (Integer) getSqlMapClientTemplate().insert("AclNumber.insertByObj", obj);
		return row;
	}
	/** (非 Javadoc) 
	* <p>Title: getAclNumberByObj</p> 
	* <p>Description: </p> 
	* @author wanglei_bj@si-tech.com.cn 
	* @param obj
	* @return 
	* @see com.sitech.basd.resource.dao.united.vlan.AclNumberDao#getAclNumberByObj(com.sitech.basd.resource.domain.united.AclNumberObj) 
	*/
	@Override
	public AclNumberObj getAclNumberByObj(AclNumberObj obj) {
		
		AclNumberObj result = null;
		List<AclNumberObj> list = queryAclNumList(obj);
		if (list != null && list.size() > 0) {
			result = list.get(0);
		}
		return result;
	}

	/** (非 Javadoc) 
	* <p>Title: queryForCount</p> 
	* <p>Description: </p> 
	* @author wanglei_bj@si-tech.com.cn 
	* @param obj
	* @return 
	 * @throws SQLException 
	* @see dao.AclNumber.AclNumberDao#queryForCount(domain.AclNumber.AclNumberObj) 
	*/
	@Override
	public Integer queryForCount(AclNumberObj obj) throws SQLException {
		return (Integer) (getSqlMap().queryForObject("AclNumber.queryByObjForCount", obj));
	}
}
