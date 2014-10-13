package com.sitech.ssd.sc.common.domain;

import java.io.Serializable;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;

/**
 * 
 * @ClassName: TbSysDictObj
 * @Description: 系统字典表
 * @author JamTau
 * @date 2014-8-27 下午2:51:25
 *
 */
@SuppressWarnings("serial")
public class TbSysDictObj extends BaseDao implements Serializable{
	
	private String dictid;//主键ID
	private String dictname;//属性名称
	private String dictcode;//属性编码
	private String dicttype;//类型
	private String dictdesc;//描述
	private String effect;//是否生效 1生效 0失效
	
	public TbSysDictObj(){
		
	}
	
	public TbSysDictObj(String dicttype){
		this.dicttype = dicttype;
	}
	
	public String getDictid() {
		return dictid;
	}
	public void setDictid(String dictid) {
		this.dictid = dictid;
	}
	public String getDictname() {
		return dictname;
	}
	public void setDictname(String dictname) {
		this.dictname = dictname;
	}
	public String getDictcode() {
		return dictcode;
	}
	public void setDictcode(String dictcode) {
		this.dictcode = dictcode;
	}
	public String getDicttype() {
		return dicttype;
	}
	public void setDicttype(String dicttype) {
		this.dicttype = dicttype;
	}
	public String getDictdesc() {
		return dictdesc;
	}
	public void setDictdesc(String dictdesc) {
		this.dictdesc = dictdesc;
	}
	public String getEffect() {
		return effect;
	}
	public void setEffect(String effect) {
		this.effect = effect;
	}
}
