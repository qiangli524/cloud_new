package com.sitech.basd.sxcloud.cloud.dao.feedback;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ibatis.dao.client.DaoException;
import com.sitech.basd.sxcloud.cloud.domain.feedback.TbCloud2FeedBackObj;
import com.sitech.basd.sxcloud.cloud.domain.feedback.TbCloud2FeedBackTypeObj;
import com.sitech.basd.sxcloud.cloud.domain.globalconfig.TbGlobalConfigObj;
import com.sitech.basd.sxcloud.cloud.web.feedback.form.PublishFeedBackForm;
import com.sitech.basd.sxcloud.cloud.web.feedback.form.PublishFeedBackTypeForm;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;

public class PublishFeedBackDaoImpl extends BaseDao implements
		PublishFeedBackDao {

	public List findListFeedBackType(TbCloud2FeedBackTypeObj obj) {
		List lst = new ArrayList();
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"FeedBack.FeedBackTypeListCount", obj))
								.intValue());
			}
			lst = getSqlMap().queryForList("FeedBack.FeedBackTypeList", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("FeedBack.FeedBackTypeList:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	public void add(PublishFeedBackTypeForm tblSYS_MODULE,
			HttpServletRequest request) {
		String[] strID = new String[] { tblSYS_MODULE.getTYPE_CODE() };
		try {
			getSqlMap().insert("FeedBack.insertForTB_PUBLISH_FEEDBACKTYPE",
					tblSYS_MODULE);
		} catch (Exception sqlexception) {
			throw new DaoException("Error creating TblSYS_MODULE.  Cause: "
					+ sqlexception);
		}
	}

	public void update(PublishFeedBackTypeForm tblSYS_MODULE,
			HttpServletRequest request) {
		String[] strID = new String[] { tblSYS_MODULE.getTYPE_CODE() };
		try {
			getSqlMap().update("FeedBack.updateTB_PUBLISH_FEEDBACKTYPE",
					tblSYS_MODULE);
		} catch (Exception sqlexception) {
			throw new DaoException("Error saving TblSYS_MODULE.  Cause: "
					+ sqlexception);
		}
	}

	public PublishFeedBackTypeForm findObjByPK(String mId) {
		try {
			return (PublishFeedBackTypeForm) getSqlMap().queryForObject(
					"FeedBack.findObjTB_PUBLISH_FEEDBACKTYPE", mId);
		} catch (Exception sqlexception) {
			throw new DaoException("Error finding new s.  Cause: "
					+ sqlexception);
		}
	}

	public void deleteByPKs(String tId, HttpServletRequest request) {
		String[] strID = new String[] { tId };
		try {
			getSqlMap()
					.delete("FeedBack.deleteForTB_PUBLISH_FEEDBACKTYPE", tId);
		} catch (Exception sqlexception) {
			throw new DaoException("Error removing TblSYS_MODULE.  Cause: "
					+ sqlexception);
		}
	}

	public PublishFeedBackForm findObjByID(String mId) {
		try {
			return (PublishFeedBackForm) getSqlMap().queryForObject(
					"FeedBack.findObjTB_PUBLISH_FEEDBACK", mId);
		} catch (Exception sqlexception) {
			throw new DaoException("Error finding new s.  Cause: "
					+ sqlexception);
		}
	}

	public void addFeedBackInfo(String ID, String SUBMIT_TIME, String TITLE,
			String TYPE_ID, String DF_INFO, String SENTTO_EMPLOYE,
			String LOGIN_ID, String enable, String IF_AFFIRM, String type) {

		String sql = "insert into TB_CLOUD2_PUBLISH_FEEDBACK (ID,SUBMIT_TIME,TITLE,TYPE_ID,DF_INFO,"
				+ "SENTTO_EMPLOYE,LOGIN_ID,ENABLE,IF_AFFIRM,type) values('"
				+ ID
				+ "',DATE_FORMAT(now(),'%Y-%m-%d %H:%i:%S'),'"
				+ TITLE
				+ "','"
				+ TYPE_ID
				+ "','"
				+ DF_INFO
				+ "','"
				+ SENTTO_EMPLOYE
				+ "','"
				+ LOGIN_ID
				+ "','1','"
				+ IF_AFFIRM
				+ "','"
				+ type
				+ "')";
		try {
			getSqlMap().insert("FeedBack.insertForTB_PUBLISH_FEEDBACK", sql);
		} catch (Exception sqlexception) {
			throw new DaoException("Error finding new s.  Cause: "
					+ sqlexception);
		}
	}

	public List findListFeedBackInfo(TbCloud2FeedBackObj obj) {

		List lst = new ArrayList();
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj.getPagination().setTotalCount(
						((Integer) getSqlMap().queryForObject(
								"FeedBack.findListPublishFeedBackInfoByCount",
								obj)).intValue());
			}
			lst = getSqlMap().queryForList(
					"FeedBack.findListPublishFeedBackInfo", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("FeedBack.findListPublishFeedBackInfo:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;

	}

	public void deleteInfo(String Id, HttpServletRequest request) {
		String[] strID = new String[] { Id };
		try {
			getSqlMap().delete("FeedBack.deleteForTB_PUBLISH_FEEDBACKInfo", Id);
		} catch (Exception sqlexception) {
			throw new DaoException("Error removing TblSYS_MODULE.  Cause: "
					+ sqlexception);
		}
	}

	public void addHisFeedBack(String ID, String SUBMIT_TIME, String TITLE,
			String TYPE_ID, String DF_INFO, String SENTTO_EMPLOYE,
			String LOGIN_ID, String IF_AFFIRM, String HF_INFO, String ENABLE) {
		String sql = "insert into TB_CLOUD2_HIS_PUBLISH_FEEDBACK (ID,SUBMIT_TIME,TITLE,TYPE_ID,DF_INFO,SENTTO_EMPLOYE,LOGIN_ID,IF_AFFIRM,HF_INFO,ENABLE) "
				+ "values('"
				+ ID
				+ "',to_date('"
				+ SUBMIT_TIME
				+ "','yyyy-mm-dd hh24:mi:ss'),'"
				+ TITLE
				+ "','"
				+ TYPE_ID
				+ "','"
				+ DF_INFO
				+ "','"
				+ SENTTO_EMPLOYE
				+ "','"
				+ LOGIN_ID
				+ "','" + IF_AFFIRM + "','" + HF_INFO + "','" + ENABLE + "')";
		try {
			getSqlMap()
					.insert("FeedBack.insertForTB_HIS_PUBLISH_FEEDBACK", sql);
		} catch (Exception sqlexception) {
			throw new DaoException("Error finding new s.  Cause: "
					+ sqlexception);
		}
	}

	public List findListHisFeedBackInfo(String sendto) {
		try {
			String mySqlCount = "";
			String mySql = "";
			mySqlCount = "select * from TB_CLOUD2_HIS_PUBLISH_FEEDBACK where SENTTO_EMPLOYE='"
					+ sendto + "'order by submit_time desc";
			// mySql = this.getLimitString(mySqlCount, this.pagination);
			// pagination.setTotalCount(((Integer)getSqlMap().queryForObject("findListPublishHisFeedBackInfoByCount",mySqlCount)).intValue());
			return getSqlMap().queryForList(
					"FeedBack.findListPublishHisFeedBackInfo", mySql);

		} catch (Exception sqlexception) {
			throw new DaoException("Error finding new s.  Cause: "
					+ sqlexception);
		}
	}

	public List searchHisFeedBackInfo(String beginTime, String endTime) {
		try {
			String mySqlCount = "";
			String mySql = "";
			mySqlCount = "select * from TB_CLOUD2_HIS_PUBLISH_FEEDBACK where SUBMIT_TIME between to_date('"
					+ beginTime
					+ "','yyyy-mm-dd hh24:mi:ss') and to_date('"
					+ endTime + "','yyyy-mm-dd hh24:mi:ss')";
			// mySql = this.getLimitString(mySqlCount, this.pagination);
			// pagination.setTotalCount(((Integer)getSqlMap().queryForObject("searchHisFeedBackInfoByCount",mySqlCount)).intValue());
			return getSqlMap().queryForList("FeedBack.searchHisFeedBackInfo",
					mySql);

		} catch (Exception sqlexception) {
			throw new DaoException("Error finding new s.  Cause: "
					+ sqlexception);
		}
	}

	public List findListTypeName() {
		try {
			return getSqlMap().queryForList(
					"FeedBack.findTypeNameForTblPUBLISH_FEEDBACKTYPE", null);
		} catch (Exception sqlexception) {
			throw new DaoException("Error finding new s.  Cause: "
					+ sqlexception);
		}
	}

	public PublishFeedBackForm findHisObjByTITLE(String id) {
		try {
			return (PublishFeedBackForm) getSqlMap().queryForObject(
					"FeedBack.findObjTB_HIS_PUBLISH_FEEDBACK", id);
		} catch (Exception sqlexception) {
			throw new DaoException("Error finding new s.  Cause: "
					+ sqlexception);
		}
	}

	public String getIdSequence() {
		try {

			return (String) getSqlMap()
					.queryForObject("FeedBack.getIdSequence");

		} catch (Exception sqlexception) {
			throw new DaoException("Error finding getTaskidSequence. Cause: "
					+ sqlexception);
		}
	}

	public String getTypeIdSequence() {
		int sequence = 0;
		try{
			TbGlobalConfigObj globalObj = new TbGlobalConfigObj(); 
			globalObj.setKEY("CLOUD2_PUBLISH_FEEDBACK_SEQUENCE");
			Object obj = getSqlMap().queryForObject("TbGlobalConfig.queryForObjByObj", globalObj);
			TbGlobalConfigObj reObj = null;
			if(obj!=null){
				reObj = (TbGlobalConfigObj)obj;
				sequence = Integer.parseInt(reObj.getVALUE());
			}
			if(obj==null){
				globalObj.setVALUE("2");
				Object insertObj = getSqlMap().insert("TbGlobalConfig.insertByObj", globalObj);
				return "1";
			}else if(sequence >= 999999){
				globalObj.setVALUE("2");
				globalObj.setID(reObj.getID());
				Object updateObj = getSqlMap().insert("TbGlobalConfig.updateByObj", globalObj);
				return "1";
			}else{
				globalObj.setVALUE((sequence+1)+"");
				globalObj.setID(reObj.getID());
				Object updateObj = getSqlMap().insert("TbGlobalConfig.updateByObj", globalObj);
			}
		}catch(Exception e){
			LogHelper.error("TbGlobalConfig.operate:"
					+ e.getMessage() + getClass().getName());
		}
		return sequence+"";
	}

	public List findListMyFeedBackInfo(TbCloud2FeedBackObj obj) {
		List lst = new ArrayList();
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj
						.getPagination()
						.setTotalCount(
								((Integer) getSqlMap()
										.queryForObject(
												"FeedBack.findListPublishMyFeedBackInfoByCount",
												obj)).intValue());
			}
			lst = getSqlMap().queryForList(
					"FeedBack.findListPublishMyFeedBackInfo", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("FileManager.helpFileList:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	public List findListIfaffirm(String login_id) {
		try {
			String mySql = "(select IF_AFFIRM from TB_CLOUD2_PUBLISH_FEEDBACK where LOGIN_ID = '"
					+ login_id
					+ "') union (select IF_AFFIRM from TB_CLOUD2_HIS_PUBLISH_FEEDBACK where LOGIN_ID = '"
					+ login_id + "')";
			return getSqlMap().queryForList("FeedBack.findListIfaffirm", mySql);
		} catch (Exception sqlexception) {
			throw new DaoException("Error finding new s.  Cause: "
					+ sqlexception);
		}
	}

	public void updateFeedBackInfo(String ID, String HF_INFO) {
		String sql = "update TB_CLOUD2_PUBLISH_FEEDBACK f set f.hf_info='"
				+ HF_INFO
				+ "',f.if_affirm=1,hf_time=DATE_FORMAT(now(),'%Y-%m-%d %H:%i:%S') where f.id = "
				+ ID;
		try {
			getSqlMap().update("FeedBack.updateTB_PUBLISH_FEEDBACK", sql);
		} catch (Exception sqlexception) {
			throw new DaoException("Error finding new s.  Cause: "
					+ sqlexception);
		}
	}

	public List findListAffiriFeedBackInfo(TbCloud2FeedBackObj obj) {
		List lst = new ArrayList();
		try {
			if (obj.getPagination() != null) {
				obj.setFIRSTROWNUM(obj.getPagination().getFirstRownum());
				obj.setPAGESIZE(obj.getPagination().getPageSize());
				obj
						.getPagination()
						.setTotalCount(
								((Integer) getSqlMap()
										.queryForObject(
												"FeedBack.findListPublishAffiriFeedBackInfoByCount",
												obj)).intValue());
			}
			lst = getSqlMap().queryForList(
					"FeedBack.findListPublishAffiriFeedBackInfo", obj);
		} catch (Exception sqlexception) {
			LogHelper.error("FeedBack.findListPublishFeedBackInfo:"
					+ sqlexception.getMessage() + getClass().getName());
		}
		return lst;
	}

	/**
	 * 获得所有用户组
	 * 
	 * @return
	 */
	public List getAllGroupList() {

		try {
			return getSqlMap().queryForList("FeedBack.getAllGroupList");

		} catch (Exception sqlexception) {
			throw new DaoException("Error Cause: " + sqlexception);
		}
	}

	/**
	 * 获得某一厂商的目录列表
	 * 
	 * @param string
	 * @param si_id
	 * @return
	 */
	public List getUserListByGroupId(String group_id) {

		try {
			return getSqlMap().queryForList("FeedBack.getUserListByGroupId",
					group_id);

		} catch (Exception sqlexception) {
			throw new DaoException("Error Cause: " + sqlexception);
		}
	}
}