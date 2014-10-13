package com.sitech.basd.syslog.domain;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

import java.io.Serializable;
import java.sql.Timestamp;

public class TbUserOperationLog extends BaseObj implements Serializable {

    private static final long serialVersionUID = 1L;

    // 主键ID
    private String id;
    // 操作人ID
    private String operatorId;
    // 操作人姓名
    private String operatorName;
    // 操作类型
    private String operationType;
    // 操作结果
    private Integer operationResult;
    // 操作对象
    private String operationObject;
    // 操作内容
    private String operationContent;
    // 操作开始时间
    private Timestamp operationStartDate;
    // 操作结束时间
    private Timestamp operationEndDate;
    // 消耗时间
    private String elapsedTime;
    // 预留字段１
    private String boattr1;
    // 预留字段２
    private String boattr2;
    // 预留字段３
    private String boattr3;
    // 预留字段４
    private String boattr4;
    // 预留字段５
    private String boattr5;

    /**
     * 获得主键ID
     *
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * 设置主键ID
     *
     * @param id
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 获取操作人ID
     *
     * @return
     */
    public String getOperatorId() {
        return operatorId;
    }

    /**
     * 设置操作人ID
     *
     * @param operatorId
     */
    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId == null ? null : operatorId.trim();
    }

    /**
     * 获取操作人姓名
     *
     * @return
     */
    public String getOperatorName() {
        return operatorName;
    }

    /**
     * 设置操作人姓名
     *
     * @param operatorName
     */
    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName == null ? null : operatorName.trim();
    }

    /**
     * 获取操作类型
     *
     * @return
     */
    public String getOperationType() {
        return operationType;
    }

    /**
     * 设置操作类型
     *
     * @param operationType
     */
    public void setOperationType(String operationType) {
        this.operationType = operationType == null ? null : operationType.trim();
    }

    /**
     * 获取操作结果
     *
     * @return
     */
    public Integer getOperationResult() {
        return operationResult;
    }

    /**
     * 设置操作结果
     *
     * @param operationResult
     */
    public void setOperationResult(Integer operationResult) {
        this.operationResult = operationResult;
    }

    /**
     * 获取操作对象
     *
     * @return
     */
    public String getOperationObject() {
        return operationObject;
    }

    /**
     * 设置操作对象
     *
     * @param operationObject
     */
    public void setOperationObject(String operationObject) {
        this.operationObject = operationObject == null ? null : operationObject.trim();
    }

    /**
     * 获取操作内容
     *
     * @return
     */
    public String getOperationContent() {
        return operationContent;
    }

    /**
     * 设置操作内容
     *
     * @param operationContent
     */
    public void setOperationContent(String operationContent) {
        this.operationContent = operationContent == null ? null : operationContent.trim();
    }

    /**
     * 获取操作开始时间
     *
     * @return
     */
    public Timestamp getOperationStartDate() {
        return operationStartDate;
    }

    /**
     * 设置操作开始时间
     *
     * @param operationStartDate
     */
    public void setOperationStartDate(Timestamp operationStartDate) {
        this.operationStartDate = operationStartDate;
    }

    /**
     * 获取操作结束时间
     *
     * @return
     */
    public Timestamp getOperationEndDate() {
        return operationEndDate;
    }

    /**
     * 设置操作结束时间
     *
     * @param operationEndDate
     */
    public void setOperationEndDate(Timestamp operationEndDate) {
        this.operationEndDate = operationEndDate;
    }

    /**
     * 获取时长
     *
     * @return
     */
    public String getElapsedTime() {
        return elapsedTime;
    }

    /**
     * 设置时长
     *
     * @param elapsedTime
     */
    public void setElapsedTime(String elapsedTime) {
        this.elapsedTime = elapsedTime == null ? null : elapsedTime.trim();
    }

    /**
     * 获取预留字段1
     *
     * @return
     */
    public String getBoattr1() {
        return boattr1;
    }

    /**
     * 设置预留字段1
     *
     * @param boattr1
     */
    public void setBoattr1(String boattr1) {
        this.boattr1 = boattr1 == null ? null : boattr1.trim();
    }

    /**
     * 获取预留字段2
     *
     * @return
     */
    public String getBoattr2() {
        return boattr2;
    }

    /**
     * 设置预留字段2
     *
     * @param boattr2
     */
    public void setBoattr2(String boattr2) {
        this.boattr2 = boattr2 == null ? null : boattr2.trim();
    }

    /**
     * 获取预留字段3
     *
     * @return
     */
    public String getBoattr3() {
        return boattr3;
    }

    /**
     * 设置预留字段3
     *
     * @param boattr3
     */
    public void setBoattr3(String boattr3) {
        this.boattr3 = boattr3 == null ? null : boattr3.trim();
    }

    /**
     * 获取预留字段4
     *
     * @return
     */
    public String getBoattr4() {
        return boattr4;
    }

    /**
     * 设置预留字段4
     *
     * @param boattr4
     */
    public void setBoattr4(String boattr4) {
        this.boattr4 = boattr4 == null ? null : boattr4.trim();
    }

    /**
     * 获取预留字段5
     *
     * @return
     */
    public String getBoattr5() {
        return boattr5;
    }

    /**
     * 设置预留字段5
     *
     * @param boattr5
     */
    public void setBoattr5(String boattr5) {
        this.boattr5 = boattr5 == null ? null : boattr5.trim();
    }
}