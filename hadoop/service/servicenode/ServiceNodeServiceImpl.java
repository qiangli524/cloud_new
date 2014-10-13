package service.servicenode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.service.HadoopServiceNodeDao;
import domain.service.HadoopServiceNodeObj;

@Service("serviceNodeService")
public class ServiceNodeServiceImpl implements ServiceNodeService{

	@Autowired
	private HadoopServiceNodeDao hadoopServiceNodeDao;
	
	@Override
	public int insertByObj(HadoopServiceNodeObj hadoopServiceNodeObj) {
		return hadoopServiceNodeDao.insertByObj(hadoopServiceNodeObj);
	}

}
