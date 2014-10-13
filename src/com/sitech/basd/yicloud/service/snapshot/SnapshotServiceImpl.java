package com.sitech.basd.yicloud.service.snapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import com.sitech.basd.common.ResponseCode;
import com.sitech.basd.yicloud.domain.snapshot.Snapshot;
import com.sitech.basd.yicloud.util.InvokeUtil;
import com.sitech.basd.yicloud.util.ParamParser;

public class SnapshotServiceImpl implements SnapshotService {
	private static final String RESPONSE_CODE = "responseCode";

	/**
	 * @Title:查询所有快照列表
	 * @Copyright: Copyright (c) 2012-9
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public List<Snapshot> querySnapshotList(String vmName) {
		List<Snapshot> list = new ArrayList<Snapshot>();
		if (vmName != null && !"".equals(vmName)) {
			try {
				String url = "/vmware/domain/snapshot/[op:list].[vmName:" + vmName + "]/";
				String result = InvokeUtil.invoke(url);
				list = JSONArray.toList(JSONArray.fromObject(result), Snapshot.class);
				/*
				 * if (list != null && list.size() > 0) { for (Snapshot shot :
				 * list) { shot.setDescription(URLDecoder.decode(shot
				 * .getDescription(), "utf-8")); } }
				 */
				// 查询当前快照位置
				if (list != null && list.size() > 0) {
					String currentUrl = "/vmware/domain/snapshot/[op:current].[vmName:" + vmName
							+ "]/";
					String currentResult = InvokeUtil.invoke(currentUrl);
					Map ps = ParamParser.makeup(currentResult);
					String code = (String) ps.get(ResponseCode.RESPONSE_CODE);
					if (code.equals(ResponseCode.SUCCESS)) {
						String val = (String) ps.get(ResponseCode.RESPONSEREMARK);

						if (val != null) {
							for (int i = 0; i < list.size(); i++) {
								Snapshot shot = (Snapshot) list.get(i);
								if (shot.getVal().equals(val)) {
									Snapshot currentshot = new Snapshot();
									currentshot.setName("当前的位置" + shot.getName());
									currentshot.setCurrent(true);
									list.add(i + 1, currentshot);
									break;
								}

							}
						}
					}
				}
			} catch (Exception e) {
				list = null;
			}
		}
		return list;
	}

	/**
	 * @Title:创建快照
	 * @Copyright: Copyright (c) 2012-9
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public String createSnapshot(Snapshot snapshot, boolean memSnapshot, boolean isDefault) {
		String result = "{\"result\":";
		String vmName = snapshot.getVmName();
		String snapshotname = snapshot.getName();
		String code = snapshot.getSnap_uuid();
		String desc = snapshot.getDescription();
		try {
			String url = "/vmware/domain/snapshot/[op:create].[vmName:" + code + "].[snapshotname:"
					+ snapshotname + "].[snapshotdesc:" + desc + "].[snapshotmem:" + memSnapshot
					+ "].[quiescedstate:" + isDefault + "]/";
			String invokeResult = InvokeUtil.invoke(url);
			Map ps = ParamParser.makeup(invokeResult);
			String success = (String) ps.get(RESPONSE_CODE);
			success = "\"" + success + "\"}";
			result += success;
		} catch (Exception e) {
			result += "\"-1\"}";
		}
		return result;
	}

	/**
	 * @Title:删除快照
	 * @Copyright: Copyright (c) 2012-9
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public String deleteSnapshot(Snapshot snapshot) {
		String result = "{\"result\":";
		try {
			String vmName = snapshot.getVmName();
			String name = snapshot.getName();
			String code = snapshot.getSnap_uuid();
			String url = "/vmware/domain/snapshot/[op:remove].[vmName:" + code + "].[snapshotname:"
					+ name + "]/";
			String invokeResult = InvokeUtil.invoke(url);
			Map ps = ParamParser.makeup(invokeResult);
			String success = (String) ps.get(RESPONSE_CODE);
			success = "\"" + success + "\"}";
			result += success;
		} catch (Exception e) {
			result += "\"-1\"}";
		}
		return result;
	}

	/**
	 * @Title:恢复快照
	 * @Copyright: Copyright (c) 2012-9
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public String revertSnapshot(Snapshot snapshot) {
		String result = "{\"result\":";
		try {
			String vmName = snapshot.getVmName();
			String name = snapshot.getName();
			String code = snapshot.getSnap_uuid();
			String url = "/vmware/domain/snapshot/[op:revert].[vmName:" + code + "].[snapshotname:"
					+ name + "]/";
			String invokeResult = InvokeUtil.invoke(url);
			Map ps = ParamParser.makeup(invokeResult);
			String success = (String) ps.get(RESPONSE_CODE);
			success = "\"" + success + "\"}";
			result += success;
		} catch (Exception e) {
			result += "\"-1\"}";
		}
		return result;
	}

	/**
	 * @Title:删除全部快照
	 * @Copyright: Copyright (c) 2012-9
	 * @Company: si-tech
	 * @author duangh
	 * @version 1.0
	 */
	public String deleteAllSnapshot(String vmName) {
		String result = "{\"result\":";
		try {
			String url = "/vmware/domain/snapshot/[op:revert].[vmName:" + vmName + "]/";
			String invokeResult = InvokeUtil.invoke(url);
			Map ps = ParamParser.makeup(invokeResult);
			String success = (String) ps.get(RESPONSE_CODE);
			success = "\"" + success + "\"}";
			result += success;
		} catch (Exception e) {
			result += "\"-1\"}";
		}
		return result;
	}
}
