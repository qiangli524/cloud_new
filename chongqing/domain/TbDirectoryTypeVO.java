package domain;

/**
 * 
 * <p>
 * Title: TbDirectoryTypeVO
 * </p>
 * <p>
 * Description: 数据字典类型VO，tb_directory_type表
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
public class TbDirectoryTypeVO {
	// 主键UUID
	private String id;
	// 字典类型编码
	private String code;
	// 字典类型名称
	private String name;
	// 字典类型描述
	private String description;

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
