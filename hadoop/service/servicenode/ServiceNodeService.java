package service.servicenode;

import domain.service.HadoopServiceNodeObj;

/**
 * <p>Title: ServiceNodeService</p>
 * <p>Description: 服务节点逻辑层</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: SI-TECH </p>
 * @author lipengpeng
 * @version 1.0
 * @createtime 2014-1-6 下午2:59:30
 *
 */
public interface ServiceNodeService {

	public int insertByObj(HadoopServiceNodeObj hadoopServiceNodeObj);
}
