package com.sitech.ssd.sc.os.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

/**
 * @ClassName CobblerService
 * @Desc 
 * @Author JamTau
 * @date May 22, 2014 8:01:42 PM
 */
@Service("osAutoInstallService")
public class CobblerService implements OsAutoInstallService {

	public List getSystem(){
		List system = new ArrayList();
		HashMap map = new HashMap();
		map.put("name", "操作系统一");
		system.add(map);
		
		HashMap map2 = new HashMap();
		map2.put("name", "操作系统二");
		system.add(map2);
		return system;
	}
}
