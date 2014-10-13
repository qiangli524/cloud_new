package com.sitech.ws.cxf;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

@Service("testService")
public class TestServiceImpl implements TestService {

	public String connect() {
		return "cxf restful webservice connect ok";
	}

	public MoMonitorVO doGetMonitor(String vType) {
		System.out.println("-----------" + vType);
		MoMonitorVO vo = new MoMonitorVO();
		List<Date> dates = new ArrayList<Date>();
		List<Long> values = new ArrayList<Long>();
		dates.add(new Date());
		values.add(123444L);
		vo.setDates(dates);
		vo.setValues(values);
		return vo;
	}
}
