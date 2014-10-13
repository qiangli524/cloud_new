package com.sitech.basd.dbmanager.domain.dbtablespace;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

public class DBTableSpaceObj extends BaseObj {
	private String id;
	private String space_name;
	private String space_size;
	private String example_id;
	private int is_expand;
	private String data_file_path;
	private int is_execute;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSpace_name() {
		return space_name;
	}

	public void setSpace_name(String space_name) {
		this.space_name = space_name;
	}

	public String getSpace_size() {
		return space_size;
	}

	public void setSpace_size(String space_size) {
		this.space_size = space_size;
	}

	public String getExample_id() {
		return example_id;
	}

	public void setExample_id(String example_id) {
		this.example_id = example_id;
	}

	public int getIs_expand() {
		return is_expand;
	}

	public void setIs_expand(int is_expand) {
		this.is_expand = is_expand;
	}

	public String getData_file_path() {
		return data_file_path;
	}

	public void setData_file_path(String data_file_path) {
		this.data_file_path = data_file_path;
	}

	public int getIs_execute() {
		return is_execute;
	}

	public void setIs_execute(int is_execute) {
		this.is_execute = is_execute;
	}

}
