package com.sitech.ssd.sc.ostempl.domain;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

/**
 * 
 * @ClassName: OsTemplateFileSystem
 * @Description: OS安装模版 -- 文件系统
 * @author JamTau
 * @date 2014-8-20 下午4:47:37
 *
 */
public class OsTemplateFileSystem extends BaseObj {
	
	private String id;//主键ID
	private String template_id;//系统安装模版ID
	private String filesystem_name;//文件系统名
	private String filesystem_type;//文件系统类型
	private String filesystem_size;//文件系统大小
	private String filesystem_desc;//描述信息
	private String volume_group;//卷组
	private String logical_volume;//逻辑卷
	
	public OsTemplateFileSystem(){}
	
	public OsTemplateFileSystem(String id,String template_id){
		this.id = id;
		this.template_id = template_id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTemplate_id() {
		return template_id;
	}

	public void setTemplate_id(String template_id) {
		this.template_id = template_id;
	}

	public String getFilesystem_name() {
		return filesystem_name;
	}

	public void setFilesystem_name(String filesystem_name) {
		this.filesystem_name = filesystem_name;
	}

	public String getFilesystem_type() {
		return filesystem_type;
	}

	public void setFilesystem_type(String filesystem_type) {
		this.filesystem_type = filesystem_type;
	}

	public String getFilesystem_size() {
		return filesystem_size;
	}

	public void setFilesystem_size(String filesystem_size) {
		this.filesystem_size = filesystem_size;
	}

	public String getFilesystem_desc() {
		return filesystem_desc;
	}

	public void setFilesystem_desc(String filesystem_desc) {
		this.filesystem_desc = filesystem_desc;
	}

	public String getVolume_group() {
		return volume_group;
	}

	public void setVolume_group(String volume_group) {
		this.volume_group = volume_group;
	}

	public String getLogical_volume() {
		return logical_volume;
	}

	public void setLogical_volume(String logical_volume) {
		this.logical_volume = logical_volume;
	}

}
