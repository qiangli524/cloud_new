package com.sitech.ssd.bpm.domain;

import java.util.Date;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;


/**
 * 工单实体
 * @author wanglei_bj
 *
 */

public class CloudWorkorder extends BaseObj implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    //工单id
    private String workorderId;
    //工单名称
    private String workorderName;
    //问题类型
    private String questionType;
    //问题内容
    private String questionContent;
    //附件
    private String attachment;
    //联系电话
    private String contactPhone;
    //邮箱
    private String contactEmail;
    //提交日期
    private java.util.Date commitDate;
    //工单归属人
    private String user_id;
    //工单提交人（客服帮用户代填是使用）
    private String apply_user_id;
    //流程实例Id
    private Long workflowId;
    //故障类型
    private String malfunctionType;
    //故障停机时间
    private Float dateRange;
    //时间单位
    private String dateUnit;
    //是否转赔付
    private Integer isMakeUp = 0;
    //问题是否解决
    private Integer isOk = 0;
    //工单确定时间
    private Date conformDate;
    //评价结果 满意1 不满意0 未评价2
    private Integer  satisfaction= 2;
    public Integer getIsMakeUp() {
		return isMakeUp;
	}

	public void setIsMakeUp(Integer isMakeUp) {
		this.isMakeUp = isMakeUp;
	}

	public Integer getIsOk() {
		return isOk;
	}

	public void setIsOk(Integer isOk) {
		this.isOk = isOk;
	}

	public Integer getSatisfaction() {
		return satisfaction;
	}

	public void setSatisfaction(Integer satisfaction) {
		this.satisfaction = satisfaction;
	}

	//评价内容
    private String evaluateText;
    //工单评价时间
    private Date evaluateDate;

    /**
     * 取得工单id
     * @return workorderId
     */
    public String getWorkorderId() {
        return workorderId;
    }

    /**
     * 设置工单id
     * @param workorderId
     */
    public void setWorkorderId(String workorderId) {
        this.workorderId = workorderId;
    }
    /**
     * 取得工单名称
     * @return workorderName
     */
    public String getWorkorderName() {
        return workorderName;
    }

    /**
     * 设置工单名称
     * @param workorderName
     */
    public void setWorkorderName(String workorderName) {
        this.workorderName = workorderName;
    }
    /**
     * 取得问题类型
     * @return questionType
     */
    public String getQuestionType() {
        return questionType;
    }

    /**
     * 设置问题类型
     * @param questionType
     */
    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }
    /**
     * 取得问题内容
     * @return questionContent
     */
    public String getQuestionContent() {
        return questionContent;
    }

    /**
     * 设置问题内容
     * @param questionContent
     */
    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }
    /**
     * 取得附件
     * @return attachment
     */
    public String getAttachment() {
        return attachment;
    }

    /**
     * 设置附件
     * @param attachment
     */
    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }
    /**
     * 取得联系电话
     * @return contactPhone
     */
    public String getContactPhone() {
        return contactPhone;
    }

    /**
     * 设置联系电话
     * @param contactPhone
     */
    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }
    /**
     * 取得邮箱
     * @return contactEmail
     */
    public String getContactEmail() {
        return contactEmail;
    }

    /**
     * 设置邮箱
     * @param contactEmail
     */
    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }
    /**
     * 取得提交日期
     * @return commitDate
     */
    public java.util.Date getCommitDate() {
        return commitDate;
    }

    /**
     * 设置提交日期
     * @param commitDate
     */
    public void setCommitDate(java.util.Date commitDate) {
        this.commitDate = commitDate;
    }


	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public Long getWorkflowId() {
		return workflowId;
	}

	public void setWorkflowId(Long workflowId) {
		this.workflowId = workflowId;
	}

	public String getMalfunctionType() {
		return malfunctionType;
	}

	public void setMalfunctionType(String malfunctionType) {
		this.malfunctionType = malfunctionType;
	}

	public Float getDateRange() {
		return dateRange;
	}

	public void setDateRange(Float dateRange) {
		this.dateRange = dateRange;
	}

	public String getDateUnit() {
		return dateUnit;
	}

	public void setDateUnit(String dateUnit) {
		this.dateUnit = dateUnit;
	}

	public Date getConformDate() {
		return conformDate;
	}

	public void setConformDate(java.util.Date date) {
		this.conformDate = date;
	}

	public String getEvaluateText() {
		return evaluateText;
	}

	public void setEvaluateText(String evaluateText) {
		this.evaluateText = evaluateText;
	}

	public Date getEvaluateDate() {
		return evaluateDate;
	}

	public void setEvaluateDate(Date date) {
		this.evaluateDate = date;
	}

	public String getApply_user_id() {
		return apply_user_id;
	}

	public void setApply_user_id(String apply_user_id) {
		this.apply_user_id = apply_user_id;
	}


}