package com.sitech.ssd.sc.ostempl.web;

import java.util.List;

import com.sitech.ssd.sc.common.domain.TbSysDictObj;
import com.sitech.ssd.sc.ostempl.domain.OsTemplateFileSystem;
import com.sitech.ssd.sc.ostempl.domain.OsTemplateGroup;
import com.sitech.ssd.sc.ostempl.domain.OsTemplateGroupUser;
import com.sitech.ssd.sc.ostempl.domain.OsTemplatePart;
import com.sitech.ssd.sc.ostempl.domain.OsTemplateSoft;
import com.sitech.ssd.sc.ostempl.domain.OsTemplateUser;
import com.sitech.ssd.sc.ostempl.domain.OsTemplateVolGroup;

/**
 * 
 * @ClassName: OsTemplateComponetForm
 * @Description: OS安装模版 组件Form：文件系统、用户、组、用户与组
 * @author JamTau
 * @date 2014-8-26 上午10:32:28
 *
 */
public class OsTemplateComponetForm {
	
	private String id;//主键ID
	private String template_id;//系统安装模版ID
	
	private String part_name;//名称
	private String part_fstype;//文件类型
	private String part_grow;//自增长
	private String part_ondisk;//disk 
	private String part_size;//大小
	private String part_desc;//描述
	
	private String vg_name;//VG名称
	private String vg_pvno;//PV编号
	private String vg_pesize;//大小
	private String vg_desc;//描述
	
	private String filesystem_name;//文件系统名
	private String filesystem_type;//文件系统类型
	private String filesystem_size;//文件系统大小
	private String filesystem_desc;//描述信息
	private String volume_group;//卷组
	private String logical_volume;//逻辑卷
	
	private String group_id;//组ID
	private String group_name;//组标识
	private String group_desc;//组描述
	
	private String user_name;//用户标识
	private String pass_word;//用户口令
	private String primary_group;//主组
	private String home_dir;//家目录（输入）
	private String user_desc;//描述信息
	
	private String user_id;//用户标识
	private String flag;//标识用户与组关系类型如主组、附加组
	private String mask;
	
	private String soft_type;
	private String soft_name;
	private String soft_desc;
	
	private List<OsTemplateFileSystem> otfsList;
	private List<OsTemplateGroup> groupList;
	private List<OsTemplateUser> userList; 
	private List<OsTemplateGroupUser> guList;
	private List<OsTemplateSoft> softList;
	private List<OsTemplatePart> partList;
	private List<OsTemplateVolGroup> vgList;
	private List<TbSysDictObj> sdList;
	
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
	public String getVg_name() {
		return vg_name;
	}
	public void setVg_name(String vg_name) {
		this.vg_name = vg_name;
	}
	public String getVg_pvno() {
		return vg_pvno;
	}
	public void setVg_pvno(String vg_pvno) {
		this.vg_pvno = vg_pvno;
	}
	public String getVg_pesize() {
		return vg_pesize;
	}
	public void setVg_pesize(String vg_pesize) {
		this.vg_pesize = vg_pesize;
	}
	public String getVg_desc() {
		return vg_desc;
	}
	public void setVg_desc(String vg_desc) {
		this.vg_desc = vg_desc;
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
	public String getGroup_id() {
		return group_id;
	}
	public void setGroup_id(String group_id) {
		this.group_id = group_id;
	}
	public String getGroup_name() {
		return group_name;
	}
	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}
	public String getGroup_desc() {
		return group_desc;
	}
	public void setGroup_desc(String group_desc) {
		this.group_desc = group_desc;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getPass_word() {
		return pass_word;
	}
	public void setPass_word(String pass_word) {
		this.pass_word = pass_word;
	}
	public String getPrimary_group() {
		return primary_group;
	}
	public void setPrimary_group(String primary_group) {
		this.primary_group = primary_group;
	}
	public String getHome_dir() {
		return home_dir;
	}
	public void setHome_dir(String home_dir) {
		this.home_dir = home_dir;
	}
	public String getUser_desc() {
		return user_desc;
	}
	public void setUser_desc(String user_desc) {
		this.user_desc = user_desc;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	public String getMask() {
		return mask;
	}
	public void setMask(String mask) {
		this.mask = mask;
	}
	public String getSoft_type() {
		return soft_type;
	}
	public void setSoft_type(String soft_type) {
		this.soft_type = soft_type;
	}
	public String getSoft_name() {
		return soft_name;
	}
	public void setSoft_name(String soft_name) {
		this.soft_name = soft_name;
	}
	public String getSoft_desc() {
		return soft_desc;
	}
	public void setSoft_desc(String soft_desc) {
		this.soft_desc = soft_desc;
	}
	public List<OsTemplateFileSystem> getOtfsList() {
		return otfsList;
	}
	public void setOtfsList(List<OsTemplateFileSystem> otfsList) {
		this.otfsList = otfsList;
	}
	public List<OsTemplateGroup> getGroupList() {
		return groupList;
	}
	public void setGroupList(List<OsTemplateGroup> groupList) {
		this.groupList = groupList;
	}
	public List<OsTemplateUser> getUserList() {
		return userList;
	}
	public void setUserList(List<OsTemplateUser> userList) {
		this.userList = userList;
	}
	public List<OsTemplateGroupUser> getGuList() {
		return guList;
	}
	public void setGuList(List<OsTemplateGroupUser> guList) {
		this.guList = guList;
	}
	public List<TbSysDictObj> getSdList() {
		return sdList;
	}
	public void setSdList(List<TbSysDictObj> sdList) {
		this.sdList = sdList;
	}
	public List<OsTemplateSoft> getSoftList() {
		return softList;
	}
	public void setSoftList(List<OsTemplateSoft> softList) {
		this.softList = softList;
	}
	public List<OsTemplatePart> getPartList() {
		return partList;
	}
	public void setPartList(List<OsTemplatePart> partList) {
		this.partList = partList;
	}
	public List<OsTemplateVolGroup> getVgList() {
		return vgList;
	}
	public void setVgList(List<OsTemplateVolGroup> vgList) {
		this.vgList = vgList;
	}
}
