package com.sitech.basd.sxcloud.rsmu.domain;

import com.sitech.basd.sxcloud.rsmu.web.util.page.Pagination;

public class BaseObj {
	protected String DATAAUTHORITY = null;// Ȩ�ޱ���
	protected int FIRSTROWNUM = 0;// ��ҳ��һ��
	protected int PAGESIZE = 0; // ��ҳ��С
	protected Pagination pagination = null;

	public String getDATAAUTHORITY() {
		return DATAAUTHORITY;
	}

	public void setDATAAUTHORITY(String dataauthority) {
		DATAAUTHORITY = dataauthority;
	}

	public int getFIRSTROWNUM() {
		return FIRSTROWNUM;
	}

	public void setFIRSTROWNUM(int firstrownum) {
		FIRSTROWNUM = firstrownum;
	}

	public int getPAGESIZE() {
		return PAGESIZE;
	}

	public void setPAGESIZE(int pagesize) {
		PAGESIZE = pagesize;
	}

	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

}
