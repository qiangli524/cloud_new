package com.sitech.basd.sxcloud.rsmu.domain.system;

public class FunctionTreeObj {

	private String strChecked;
	private String mId;
	private String mName;
	private int level;

	/**
	 * @return Returns the level.
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * @param level
	 *            The level to set.
	 */
	public void setLevel(int level) {
		this.level = level;
	}

	/**
	 * @return Returns the mId.
	 */
	public String getMId() {
		return mId;
	}

	/**
	 * @param id
	 *            The mId to set.
	 */
	public void setMId(String id) {
		mId = id;
	}

	/**
	 * @return Returns the strChecked.
	 */
	public String getStrChecked() {
		return strChecked;
	}

	/**
	 * @param strChecked
	 *            The strChecked to set.
	 */
	public void setStrChecked(String strChecked) {
		this.strChecked = strChecked;
	}

	/**
	 * @return Returns the mName.
	 */
	public String getMName() {
		return mName;
	}

	/**
	 * @param name
	 *            The mName to set.
	 */
	public void setMName(String name) {
		mName = name;
	}
}
