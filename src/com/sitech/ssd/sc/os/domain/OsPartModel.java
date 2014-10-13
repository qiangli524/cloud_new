package com.sitech.ssd.sc.os.domain;

import com.sitech.basd.sxcloud.rsmu.domain.BaseObj;

/**
 * 
 * @ClassName: OsPartModel
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author JamTau
 * @date 2014-9-17 上午12:53:03
 *
 */
public class OsPartModel extends BaseObj {
	
	private String id;//主键ID
	private String os_host_id;//主机ID
	private String part_name;//名称
	private String part_fstype;//文件类型
	private String part_grow;//自增长
	private String part_ondisk;//disk 
	private String part_size;//大小
	private String part_desc;//描述
	private String flag;//
	
	public OsPartModel(){};
	
	public OsPartModel(String os_host_id){
		this.os_host_id = os_host_id;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOs_host_id() {
		return os_host_id;
	}
	public void setOs_host_id(String os_host_id) {
		this.os_host_id = os_host_id;
	}
	public String getPart_name() {
		return part_name;
	}
	public void setPart_name(String part_name) {
		this.part_name = part_name;
	}
	public String getPart_fstype() {
		return part_fstype;
	}
	public void setPart_fstype(String part_fstype) {
		this.part_fstype = part_fstype;
	}
	public String getPart_grow() {
		return part_grow;
	}
	public void setPart_grow(String part_grow) {
		this.part_grow = part_grow;
	}
	public String getPart_ondisk() {
		return part_ondisk;
	}
	public void setPart_ondisk(String part_ondisk) {
		this.part_ondisk = part_ondisk;
	}
	public String getPart_size() {
		return part_size;
	}
	public void setPart_size(String part_size) {
		this.part_size = part_size;
	}
	public String getPart_desc() {
		return part_desc;
	}
	public void setPart_desc(String part_desc) {
		this.part_desc = part_desc;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	
}
