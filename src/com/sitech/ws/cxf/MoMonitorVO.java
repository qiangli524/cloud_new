package com.sitech.ws.cxf;

import java.util.Date;
import java.util.List;

/**
 * 
 * <p>
 * Title: MoMonitorVO
 * </p>
 * <p>
 * Description: 数据实体类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author huojla
 * @version 1.0
 * @createtime 2013-5-7 下午3:23:01
 * 
 */
public class MoMonitorVO {
	private List<Date> dates;
	private List<Long> values;

	public List<Date> getDates() {
		return dates;
	}

	public void setDates(List<Date> dates) {
		this.dates = dates;
	}

	public List<Long> getValues() {
		return values;
	}

	public void setValues(List<Long> values) {
		this.values = values;
	}

}
