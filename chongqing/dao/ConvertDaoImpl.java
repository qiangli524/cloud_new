package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.yicloud.domain.datastore.DataStoreObj;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.utils.randomid.RandomUUID;

import domain.ConvertObj;
@Repository("convertDao")
public class ConvertDaoImpl extends BaseDao implements ConvertDao {
	
	@Override
	public int insertByObj(ConvertObj convertObj) {
		convertObj.setId(RandomUUID.getUuid());
		int ret = 0;
		try {
			Object ob = getSqlMap().insert("Convert.insertByObj", convertObj);
			if (ob != null) {
				ret = Integer.parseInt(ob.toString());
			}
		} catch (SQLException e) {
			ret = -1;
			logger.error("Convert.insertByObj:" + e.getMessage()
					+ e.getClass().getName());
		}
		return ret;
	}

	@Override
	public List<ConvertObj> queryForList(ConvertObj convertObj) {
		List<ConvertObj> list=new ArrayList<ConvertObj>();
		try {
			list = getSqlMap().queryForList("Convert.queryForList", convertObj);
			for (ConvertObj convertObj2 : list) {
				int time = Integer.parseInt(convertObj2.getUsedTime());
		        int N = time/3600;
		        time = time%3600;
		        int K = time/60;
		        time = time%60;
		        int M = time;
		        convertObj2.setUsedTime(N+"小时"+K+"分"+M+"秒");
			}
		} catch (SQLException e) {
			logger.error("Convert.queryForList:" + e.getMessage()
					+ e.getClass().getName());
		}
		return list;
	}
	
	public int deleteByObj(ConvertObj obj) {
		int ret = 0;
		try {
			Object ob = getSqlMap().delete("Convert.deleteByObj",
					obj);
			if (ob != null) {
				ret = Integer.parseInt(ob.toString());
			}
		} catch (Exception e) {
			ret = -1;
			LogHelper.error("Convert.deleteByObj: "
					+ e.getMessage() + e.getClass().getName());
		}
		return ret;
	}
	
	public int updateByObj(ConvertObj obj) {
		int ret = 0;
		try {
			Object ob = getSqlMap().update("Convert.updateByObj", obj);
			if (ob != null) {
				ret = Integer.parseInt(ob.toString());
			}
		} catch (Exception e) {
			ret = -1;
			LogHelper.error("Convert.updateByObj: " + e.getMessage()
					+ e.getClass().getName());
		}
		return ret;
	}

	
	@Override
	public ConvertObj queryHostStore(DataStoreObj obj) {
		ConvertObj storeObj = new ConvertObj();
		try {
			storeObj = (ConvertObj) getSqlMap().queryForObject("Convert.queryHostStore", obj);
		} catch (SQLException e) {
			logger.error("Convert.queryHostStore:" + e.getMessage()
					+ e.getClass().getName());
		}
		return storeObj;
	}
}
