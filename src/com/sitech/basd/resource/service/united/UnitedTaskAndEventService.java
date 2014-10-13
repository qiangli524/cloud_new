package com.sitech.basd.resource.service.united;

import java.util.List;

import com.sitech.basd.resource.domain.united.UnitedTreeObj;
import com.sitech.vo.united.EventUnitedVO;
import com.sitech.vo.united.LogUnitedVO;
import com.sitech.vo.united.TaskUnitedVO;

/**
 * <p>Title: UnitedTaskAndEventService</p>
 * <p>Description: 任务与事件通用接口
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: SI-TECH </p>
 * @author lipengpeng
 * @version 1.0
 * @createtime 2013-8-2 下午5:00:30
 *
 */
public interface UnitedTaskAndEventService {

	/**
	 * @Title: attchEvent
	 * @Description: 获取事件列表
	 * @param
	 * @return List<EventUnitedVO>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-2 下午5:02:49
	 */
	public List<EventUnitedVO> attchEvent(UnitedTreeObj obj,Integer pageSize);
	
	/**
	 * @Title: attchTask
	 * @Description: 获取任务列表
	 * @param
	 * @return List<TaskUnitedVO>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-2 下午5:02:54
	 */
	public List<TaskUnitedVO> attchTask(UnitedTreeObj obj,Integer pageSize);
	
	/**
	 * @Title: attchLogsForXen
	 * @Description: xen获取日志列表
	 * @param
	 * @return List<TaskUnitedVO>
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime 2013-8-2 下午5:02:54
	 */
	public List<LogUnitedVO> attchLogs(UnitedTreeObj obj);
	
}
