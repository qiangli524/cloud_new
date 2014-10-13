package com.sitech.basd.sxcloud.cloud.service.feedback;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.sitech.basd.sxcloud.cloud.dao.feedback.PublishFeedBackDao;
import com.sitech.basd.sxcloud.cloud.domain.feedback.TbCloud2FeedBackObj;
import com.sitech.basd.sxcloud.cloud.domain.feedback.TbCloud2FeedBackTypeObj;
import com.sitech.basd.sxcloud.cloud.web.feedback.form.PublishFeedBackForm;
import com.sitech.basd.sxcloud.cloud.web.feedback.form.PublishFeedBackTypeForm;
import com.sitech.basd.sxcloud.rsmu.service.BaseService;
import com.sitech.basd.yicloud.dao.mytstat.MysqlTableStatusDao;

public class PublishFeedBackServiceImpl extends BaseService implements
		PublishFeedBackService {
	private MysqlTableStatusDao mysqlTableStatusDao;

	public void setMysqlTableStatusDao(MysqlTableStatusDao mysqlTableStatusDao) {
		this.mysqlTableStatusDao = mysqlTableStatusDao;
	}

	private PublishFeedBackDao publishFeedBackDao;

	public PublishFeedBackDao getPublishFeedBackDao() {
		return publishFeedBackDao;
	}

	public void setPublishFeedBackDao(PublishFeedBackDao publishFeedBackDao) {
		this.publishFeedBackDao = publishFeedBackDao;
	}

	public void add(PublishFeedBackTypeForm tblSYS_MODULE,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		publishFeedBackDao.add(tblSYS_MODULE, request);
	}

	public void addFeedBackInfo(String ID, String SUBMIT_TIME, String TITLE,
			String TYPE_ID, String DF_INFO, String SENTTO_EMPLOYE,
			String LOGIN_ID, String enable, String IF_AFFIRM, String type) {
		// TODO Auto-generated method stub
		publishFeedBackDao.addFeedBackInfo(ID, SUBMIT_TIME, TITLE, TYPE_ID,
				DF_INFO, SENTTO_EMPLOYE, LOGIN_ID, enable, IF_AFFIRM, type);
	}

	public void addHisFeedBack(String ID, String SUBMIT_TIME, String TITLE,
			String TYPE_ID, String DF_INFO, String SENTTO_EMPLOYE,
			String LOGIN_ID, String IF_AFFIRM, String HF_INFO, String ENABLE) {
		// TODO Auto-generated method stub
		publishFeedBackDao.addHisFeedBack(ID, SUBMIT_TIME, TITLE, TYPE_ID,
				DF_INFO, SENTTO_EMPLOYE, LOGIN_ID, IF_AFFIRM, HF_INFO, ENABLE);
	}

	public void deleteByPKs(String id, HttpServletRequest request) {
		// TODO Auto-generated method stub
		publishFeedBackDao.deleteByPKs(id, request);
	}

	public void deleteInfo(String Id, HttpServletRequest request) {
		// TODO Auto-generated method stub
		publishFeedBackDao.deleteInfo(Id, request);
	}

	public PublishFeedBackForm findHisObjByTITLE(String id) {
		// TODO Auto-generated method stub
		return publishFeedBackDao.findHisObjByTITLE(id);
	}

	public List findListAffiriFeedBackInfo(TbCloud2FeedBackObj obj) {
		// TODO Auto-generated method stub
		return publishFeedBackDao.findListAffiriFeedBackInfo(obj);
	}

	public List findListFeedBackInfo(TbCloud2FeedBackObj obj) {
		// TODO Auto-generated method stub
		return publishFeedBackDao.findListFeedBackInfo(obj);
	}

	public List findListFeedBackType(TbCloud2FeedBackTypeObj obj) {
		// TODO Auto-generated method stub
		return publishFeedBackDao.findListFeedBackType(obj);
	}

	public List findListHisFeedBackInfo(String sendto) {
		// TODO Auto-generated method stub
		return publishFeedBackDao.findListHisFeedBackInfo(sendto);
	}

	public List findListIfaffirm(String login_id) {
		// TODO Auto-generated method stub
		return publishFeedBackDao.findListIfaffirm(login_id);
	}

	public List findListMyFeedBackInfo(TbCloud2FeedBackObj obj) {
		// TODO Auto-generated method stub
		return publishFeedBackDao.findListMyFeedBackInfo(obj);
	}

	public List findListTypeName() {
		// TODO Auto-generated method stub
		return publishFeedBackDao.findListTypeName();
	}

	public PublishFeedBackForm findObjByID(String id) {
		// TODO Auto-generated method stub
		return publishFeedBackDao.findObjByID(id);
	}

	public PublishFeedBackTypeForm findObjByPK(String id) {
		// TODO Auto-generated method stub
		return publishFeedBackDao.findObjByPK(id);
	}

	public String getIdSequence() {
		// TODO Auto-generated method stub
		return publishFeedBackDao.getIdSequence();
	}

	public String getTypeIdSequence() {
		// TODO Auto-generated method stub
		return publishFeedBackDao.getTypeIdSequence();
	}

	public List searchHisFeedBackInfo(String beginTime, String endTime) {
		// TODO Auto-generated method stub
		return publishFeedBackDao.searchHisFeedBackInfo(beginTime, endTime);
	}

	public void update(PublishFeedBackTypeForm tblSYS_MODULE,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		publishFeedBackDao.update(tblSYS_MODULE, request);
	}

	public void updateFeedBackInfo(String ID, String HF_INFO) {
		// TODO Auto-generated method stub
		publishFeedBackDao.updateFeedBackInfo(ID, HF_INFO);
	}

	public List getAllGroupList() {
		// TODO Auto-generated method stub
		return publishFeedBackDao.getAllGroupList();
	}

	public List getUserListByGroupId(String group_id) {
		return publishFeedBackDao.getUserListByGroupId(group_id);
	}
}