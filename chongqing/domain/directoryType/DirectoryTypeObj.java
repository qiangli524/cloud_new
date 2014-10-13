package domain.directoryType;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

/**
 * @author chenyu_bj:2014-05-13 10:53:36
 * 
 */
public class DirectoryTypeObj extends BaseObj {

	/*
	 * id
	 */
	private String id;
	/*
	 * 编码
	 */
	private String code;
	/*
	 * 名称
	 */
	private String name;
	/*
	 * 描述
	 */
	private String description;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

}
