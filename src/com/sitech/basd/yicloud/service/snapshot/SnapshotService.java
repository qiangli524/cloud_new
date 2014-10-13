package com.sitech.basd.yicloud.service.snapshot;

import java.util.List;

import com.sitech.basd.yicloud.domain.snapshot.Snapshot;

public interface SnapshotService {
	/**
	 * @Title:查询所有快照列表
	 * @Copyright: Copyright (c) 2012-9
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public List<Snapshot> querySnapshotList(String vmName);

	/**
	 * @Title:创建快照
	 * @Copyright: Copyright (c) 2012-9
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public String createSnapshot(Snapshot snapshot, boolean memSnapshot,
			boolean isDefault);

	/**
	 * @Title:删除快照
	 * @Copyright: Copyright (c) 2012-9
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public String deleteSnapshot(Snapshot snapshot);

	/**
	 * @Title:恢复快照
	 * @Copyright: Copyright (c) 2012-9
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public String revertSnapshot(Snapshot snapshot);

	/**
	 * @Title:删除全部快照
	 * @Copyright: Copyright (c) 2012-9
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public String deleteAllSnapshot(String vmName);
}
