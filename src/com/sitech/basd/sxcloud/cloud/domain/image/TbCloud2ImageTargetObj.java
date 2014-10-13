package com.sitech.basd.sxcloud.cloud.domain.image;

/**
 * 
 * <p>
 * Title: TbCloud2ImageTargetObj
 * </p>
 * <p>
 * Description: 虚拟镜像-映像目标
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author huojla
 * @version 1.0
 * @createtime 2012-3-1 下午04:11:18
 * 
 */
public class TbCloud2ImageTargetObj {
	/** 映像目标ID */
	private String id;
	/** 映像目标名称 */
	private String name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
