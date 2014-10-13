package com.sitech.basd.sxcloud.rsmu.web.system.form;

import java.util.List;

public class GrpmemberForm {
	private String user_id;
	private String group_id;
	private List userForSelect = null;
	private List selectedUsers = null;

	public List getSelectedUsers() {
		return selectedUsers;
	}

	public void setSelectedUsers(List selectedUsers) {
		this.selectedUsers = selectedUsers;
	}

	public List getUserForSelect() {
		return userForSelect;
	}

	public void setUserForSelect(List userForSelect) {
		this.userForSelect = userForSelect;
	}

	public String getGroup_id() {
		return group_id;
	}

	public void setGroup_id(String group_id) {
		this.group_id = group_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	/*
	 * 清空ActionForm
	 */
	// public void reset(ActionMapping mapping, HttpServletRequest request) {
	// super.reset(mapping, request);
	// this.user_id = null;
	// this.group_id = null;
	// this.userForSelect = null;
	// this.selectedUsers = null;
	// }
}
