package com.sitech.ssd.sc.ostempl.web;

import java.util.List;

import com.sitech.ssd.sc.common.domain.TbSysDictObj;
import com.sitech.ssd.sc.ostempl.domain.OsTemplate;
import com.sitech.ssd.sc.ostempl.domain.OsTemplateFileSystem;
import com.sitech.ssd.sc.ostempl.domain.OsTemplateGroup;
import com.sitech.ssd.sc.ostempl.domain.OsTemplateGroupUser;
import com.sitech.ssd.sc.ostempl.domain.OsTemplatePart;
import com.sitech.ssd.sc.ostempl.domain.OsTemplateSoft;
import com.sitech.ssd.sc.ostempl.domain.OsTemplateUser;
import com.sitech.ssd.sc.ostempl.domain.OsTemplateVolGroup;

/**
 * 
 * @ClassName: OsTemplateForm
 * @Description: 安装模版
 * @author JamTau
 * @date 2014-8-21 下午5:33:44
 *
 */
public class OsTemplateForm {
	
	private List<OsTemplate> templList;
	
	private String id;//安装模版ID,
	private String templ_name;//模版名称
	private String templ_desc;//模版描述
	private String os_version;//OS版本
	
	private List<OsTemplateFileSystem> otfsList;
	private List<OsTemplateGroup> groupList;
	private List<OsTemplateUser> userList; 
	private List<OsTemplateGroupUser> guList;
	private List<OsTemplateSoft> softList;
	private List<OsTemplatePart> partList;
	private List<OsTemplateVolGroup> vgList;
	private List<TbSysDictObj> sdList;  

	public List<OsTemplate> getTemplList() {
		return templList;
	}

	public void setTemplList(List<OsTemplate> templList) {
		this.templList = templList;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTempl_name() {
		return templ_name;
	}

	public void setTempl_name(String templ_name) {
		this.templ_name = templ_name;
	}

	public String getTempl_desc() {
		return templ_desc;
	}

	public void setTempl_desc(String templ_desc) {
		this.templ_desc = templ_desc;
	}

	public String getOs_version() {
		return os_version;
	}

	public void setOs_version(String os_version) {
		this.os_version = os_version;
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
