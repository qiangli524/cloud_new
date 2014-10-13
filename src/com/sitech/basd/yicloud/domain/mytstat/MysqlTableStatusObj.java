package com.sitech.basd.yicloud.domain.mytstat;

import java.io.Serializable;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

/**
 * 
 * <p>
 * Title: MysqlTableStatusObj
 * </p>
 * <p>
 * Description: Mysql数据库表状态信息
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author huojla
 * @version 1.0
 * @createtime Jun 15, 2012 2:34:52 PM
 * 
 */
public class MysqlTableStatusObj extends BaseObj implements Serializable,
		Cloneable {
	/** 表的名称 */
	private String name;
	/** 表的存储引擎。在MySQL 4.1.2之前，本值被标记为Type。 */
	private String engine;
	/** 表的.frm文件的版本号。 */
	private String version;
	/** 行存储格式（Fixed,Dynamic,Compressed,Redundant,Compact）。InnoDB表的格式被报告为Redundant或Compact。 */
	private String rowFormat;
	/** 行的数目。部分存储引擎，如MyISAM，存储精确的数目。 */
	private String rows;
	/** 平均的行长度 */
	private String avgRowLength;
	/** 数据文件的长度。 */
	private String dataLength;
	/** 数 据文件的最大长度 */
	private String maxDataLength;
	/** 索引文件的长度。 */
	private String indexLength;
	/** 被整序，但是未使用的字节的数目。 */
	private String dataFree;
	/** 下一个AUTO_INCREMENT值。 */
	private String autoIncrement;
	/** 什么时候表被创建。 */
	private String createTime;
	/** 什么时候数据文件被最后一次更新。 */
	private String updateTime;
	/** 什么时候表被最后一次检查。不是所有的存储引擎此时都更新，在此情况下，值为NULL。 */
	private String checkTime;
	/** 表的字符集和整序。 */
	private String collation;
	/** 活性校验和值。 */
	private String checksum;
	/** 和CREATE TABLE同时使用的额外选项。 */
	private String createOptions;
	/** 建表时使用的评注（或者有关为什么MySQL可以访问表信息的说明）。 */
	private String comment;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEngine() {
		return engine;
	}

	public void setEngine(String engine) {
		this.engine = engine;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getRowFormat() {
		return rowFormat;
	}

	public void setRowFormat(String rowFormat) {
		this.rowFormat = rowFormat;
	}

	public String getRows() {
		return rows;
	}

	public void setRows(String rows) {
		this.rows = rows;
	}

	public String getAvgRowLength() {
		return avgRowLength;
	}

	public void setAvgRowLength(String avgRowLength) {
		this.avgRowLength = avgRowLength;
	}

	public String getDataLength() {
		return dataLength;
	}

	public void setDataLength(String dataLength) {
		this.dataLength = dataLength;
	}

	public String getMaxDataLength() {
		return maxDataLength;
	}

	public void setMaxDataLength(String maxDataLength) {
		this.maxDataLength = maxDataLength;
	}

	public String getIndexLength() {
		return indexLength;
	}

	public void setIndexLength(String indexLength) {
		this.indexLength = indexLength;
	}

	public String getDataFree() {
		return dataFree;
	}

	public void setDataFree(String dataFree) {
		this.dataFree = dataFree;
	}

	public String getAutoIncrement() {
		return autoIncrement;
	}

	public void setAutoIncrement(String autoIncrement) {
		this.autoIncrement = autoIncrement;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(String checkTime) {
		this.checkTime = checkTime;
	}

	public String getCollation() {
		return collation;
	}

	public void setCollation(String collation) {
		this.collation = collation;
	}

	public String getChecksum() {
		return checksum;
	}

	public void setChecksum(String checksum) {
		this.checksum = checksum;
	}

	public String getCreateOptions() {
		return createOptions;
	}

	public void setCreateOptions(String createOptions) {
		this.createOptions = createOptions;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
