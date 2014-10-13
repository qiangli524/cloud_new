package com.sitech.basd.fusioncharts.vo;

import java.util.List;

/**
 * <p>Title: Styles</p>
 * <p>Description: TODO(用一句话描述该文件做什么)</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: SI-TECH </p>
 * @author lipengpeng
 * @version 1.0
 * @createtime 2014-1-6 下午5:00:59
 *
 */
public class Styles {

	private List<Definition> definition;
	
	private List<Application> application;

	public List<Definition> getDefinition() {
		return definition;
	}

	public void setDefinition(List<Definition> definition) {
		this.definition = definition;
	}

	public List<Application> getApplication() {
		return application;
	}

	public void setApplication(List<Application> application) {
		this.application = application;
	}
	
}
