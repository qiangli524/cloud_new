package com.sitech.basd.sxcloud.cloud.web.showresource.form;

import java.util.Comparator;

public class MyComparator implements Comparator<AlarmHostStatistics>{

	@Override
	public int compare(AlarmHostStatistics o1, AlarmHostStatistics o2) {
		
		return o2.getIpusage().compareTo(o1.getIpusage());
	}

}
