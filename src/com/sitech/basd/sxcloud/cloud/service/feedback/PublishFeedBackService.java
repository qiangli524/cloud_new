package com.sitech.basd.sxcloud.cloud.service.feedback;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.sitech.basd.sxcloud.cloud.domain.feedback.TbCloud2FeedBackObj;
import com.sitech.basd.sxcloud.cloud.domain.feedback.TbCloud2FeedBackTypeObj;
import com.sitech.basd.sxcloud.cloud.web.feedback.form.PublishFeedBackForm;
import com.sitech.basd.sxcloud.cloud.web.feedback.form.PublishFeedBackTypeForm;

public interface PublishFeedBackService {

	public List findListFeedBackType(TbCloud2FeedBackTypeObj obj);

	public void add(PublishFeedBackTypeForm tblSYS_MODULE,
			HttpServletRequest request);

	public void update(PublishFeedBackTypeForm tblSYS_MODULE,
			HttpServletRequest request);

	public PublishFeedBackTypeForm findObjByPK(String mId);

	public void deleteByPKs(String tId, HttpServletRequest request);

	public PublishFeedBackForm findObjByID(String mId);

	public void addFeedBackInfo(String ID, String SUBMIT_TIME, String TITLE,
			String TYPE_ID, String DF_INFO, String SENTTO_EMPLOYE,
			String LOGIN_ID, String enable, String IF_AFFIRM, String type);

	public List findListFeedBackInfo(TbCloud2FeedBackObj obj);

	public void deleteInfo(String Id, HttpServletRequest request);

	public void addHisFeedBack(String ID, String SUBMIT_TIME, String TITLE,
			String TYPE_ID, String DF_INFO, String SENTTO_EMPLOYE,
			String LOGIN_ID, String IF_AFFIRM, String HF_INFO, String ENABLE);

	public List findListHisFeedBackInfo(String sendto);

	public List searchHisFeedBackInfo(String beginTime, String endTime);

	public List findListTypeName();

	public PublishFeedBackForm findHisObjByTITLE(String id);

	public String getIdSequence();

	public String getTypeIdSequence();

	public List findListMyFeedBackInfo(TbCloud2FeedBackObj obj);

	public List findListIfaffirm(String login_id);

	public void updateFeedBackInfo(String ID, String HF_INFO);

	public List findListAffiriFeedBackInfo(TbCloud2FeedBackObj obj);

	public List getAllGroupList();

	public List getUserListByGroupId(String group_id);

}
