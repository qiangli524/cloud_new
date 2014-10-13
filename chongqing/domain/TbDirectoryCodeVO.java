package domain;

/**
 * 
 * <p>
 * Title: TbDirectoryCodeVO
 * </p>
 * <p>
 * Description: 数据字典表VO，tb_directory_code表
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author huojla
 * @version 1.0
 * @createtime 2014-5-5 上午11:10:52
 * 
 */
public class TbDirectoryCodeVO {
	// 主键UUID
	private String id;
	// 字典编码
	private String code;
	// 字典名称
	private String name;
	// 字典描述
	private String description;
	// 字典对应字典类型编码，tb_directory_type 中code字段
	private String ditypecode;

	public String getDitypecode() {
		return ditypecode;
	}

	public void setDitypecode(String ditypecode) {
		this.ditypecode = ditypecode;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
