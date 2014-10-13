package com.sitech.basd.sxcloud.cloud.web.leader.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sitech.basd.sxcloud.cloud.domain.leader.HostBusiSysObj;
import com.sitech.basd.sxcloud.cloud.domain.leader.MonitoringObj;
import com.sitech.basd.sxcloud.cloud.domain.leader.TbCloud2BizsysInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.leader.TbCloud2CubinetInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.leader.TbCloud2EquipmentObj;
import com.sitech.basd.sxcloud.cloud.domain.leader.TbCloud2HostInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.leader.TbCloud2MroomInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.leader.TbCloud2RoomInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.leader.TbCloud2VmhostInfoObj;
import com.sitech.basd.sxcloud.cloud.service.leader.LeaderViewBusinessService;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.basd.sxcloud.util.LeaderViewBusinessConstant;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.utils.servlet.PrintWriterOut;

/**
 * 领导视图 Action类
 * 
 * @author zhangwj
 * @Date 2011年11月30日
 * 
 */
public class LeaderViewBusinessAction extends BaseAction {

	/**
	 * 
	 * @Title: 进入领导视图页面
	 * @Copyright: Copyright (c) 2011-11-30
	 * @Company: si-tech
	 * @author zhangwj
	 * @version 1.0
	 */
	public String gotoLeaderViewMainPage() {
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		try {
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			// 获得所有业务系统信息
			List<TbCloud2BizsysInfoObj> bizsysInfoList = leaderViewBusinessService
					.queryAllBizSysList();

			// 获得地图中有机房的地区
			List<TbCloud2MroomInfoObj> mRoomInfoList = leaderViewBusinessService
					.queryAllMroomList();
			// 组装成JSON数据
			StringBuilder sb = new StringBuilder("{");
			// 地图中机房集合
			if (null != mRoomInfoList && !mRoomInfoList.isEmpty()) {
				sb.append("\"map\":");
				sb.append("[");
				for (int i = 0; i < mRoomInfoList.size(); i++) {
					TbCloud2MroomInfoObj obj = mRoomInfoList.get(i);
					if (i > 0) {
						sb.append(",");
					}
					sb.append("{");
					sb.append("\"m_id\":\"" + obj.getM_id());
					sb.append("\",\"m_name\":\"" + URLEncoder.encode(obj.getM_name(), "utf-8"));
					// sb.append("\",\"m_total\":\"" + obj.getM_total());
					// sb.append("\",\"m_use\":\"" + obj.getM_use());
					sb.append("\",\"dist_id\":\"" + obj.getDist_id());
					sb.append("\"}");

				}
				sb.append("]");
				sb.append(",");
			}
			// 业务系统集合
			if (null != bizsysInfoList && !bizsysInfoList.isEmpty()) {
				sb.append("\"sys\":");
				sb.append("[");
				for (int i = 0; i < bizsysInfoList.size(); i++) {
					TbCloud2BizsysInfoObj obj = bizsysInfoList.get(i);
					if (i > 0) {
						sb.append(",");
					}
					sb.append("{");
					sb.append("\"sys_id\":\"" + obj.getSYS_ID());
					sb.append("\",\"sys_name\":\"" + URLEncoder.encode(obj.getSYS_NAME(), "utf-8"));
					sb.append("\",\"region_id\":\"" + obj.getREGION_ID());
					sb.append("\",\"status\":\"" + String.valueOf(obj.getSTATU()));
					sb.append("\"}");
				}
				sb.append("]");
			}
			sb.append("}");

			// 传输数据 到FLEX
			// PrintWriter out = response.getWriter();
			// out.println(sb.toString());
			// out.flush();
			// out.close();
			PrintWriterOut.printWirter(response, sb.toString());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @Title: 机房详细信息
	 * @Copyright: Copyright (c) 2011-11-30
	 * @Company: si-tech
	 * @author zhangwj
	 * @version 1.0
	 */
	public String getComputerRoomInfo() {
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		try {
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			TbCloud2MroomInfoObj roomObj = new TbCloud2MroomInfoObj();
			String roomId = request.getParameter("roomId"); // 获得机房编号
			String sysId = request.getParameter("sysId"); // 获得业务系统编号
			roomObj.setM_id(roomId);
			// 获得选中的机房的详细信息
			TbCloud2MroomInfoObj mroomInfoObj = leaderViewBusinessService
					.queryMRoomInfoByObj(roomObj);
			TbCloud2CubinetInfoObj obj = new TbCloud2CubinetInfoObj();
			if (null != roomId && !"".equals(roomId)) {
				obj.setR_id(roomId);
			}
			// 获得所有的机柜
			List<TbCloud2CubinetInfoObj> cabinetList = leaderViewBusinessService
					.queryCubListByRoomId(obj);

			// 获得系统相关的机柜
			List<String> sysAssoCabList = null;
			if (null != sysId && !"".equals(sysId)) {
				sysAssoCabList = leaderViewBusinessService.querySysAssoCabListBySysId(sysId);

			}
			// 传输数据 到FLEX
			StringBuilder sb = new StringBuilder("{");
			sb.append("\"mroom\":{");
			if (null != mroomInfoObj) {

				sb.append("\"m_id\":\"" + mroomInfoObj.getM_id());
				sb.append("\",\"m_name\":\"" + URLEncoder.encode(mroomInfoObj.getM_name(), "utf-8"));
				sb.append("\",\"m_man\":\"" + URLEncoder.encode(mroomInfoObj.getM_man(), "utf-8"));
				sb.append("\",\"m_tel\":\"" + mroomInfoObj.getM_tel());
				sb.append("\",\"m_email\":\"" + mroomInfoObj.getM_email());
				// sb.append("\",\"m_total\":\"" + mroomInfoObj.getM_total());
				// sb.append("\",\"m_use\":\"" + mroomInfoObj.getM_use());
				sb.append("\"");
			}
			sb.append("}");
			if (null != cabinetList && !cabinetList.isEmpty()) {
				sb.append(",\"cabinet\":[");
				for (int i = 0; i < cabinetList.size(); i++) {
					TbCloud2CubinetInfoObj cObj = cabinetList.get(i);
					if (i > 0) {
						sb.append(",");
					}
					sb.append("{");
					sb.append("\"cid\":\"" + cObj.getC_id());
					sb.append("\",\"cname\":\"" + URLEncoder.encode(cObj.getC_name(), "utf-8"));
					sb.append("\",\"cdesc\":\"" + URLEncoder.encode(cObj.getC_desc(), "utf-8"));
					sb.append("\",\"ctype\":\"" + cObj.getC_type());
					sb.append("\",\"caddr\":\"" + cObj.getC_addr());
					if (null != sysAssoCabList && sysAssoCabList.size() > 0
							&& sysAssoCabList.contains(cObj.getC_id())) {
						sb.append("\",\"casso\":\"1"); // 业务系统相关机柜
					} else {
						sb.append("\",\"casso\":\"0"); // 业务系统无关机柜
					}
					sb.append("\"}");
				}
				sb.append("]");
			}
			sb.append("}");
			// PrintWriter out = response.getWriter();
			// out.println(sb.toString());
			// out.flush();
			// out.close();

			PrintWriterOut.printWirter(response, sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @Title: 获得设备所占比
	 * @Copyright: Copyright (c) 2012-2-14
	 * @Company: si-tech
	 * @author zhangwj
	 * @version 1.0
	 */
	public String getEquipmentPercent() {
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		try {
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			String roomId = request.getParameter("id");// 房间id
			// 获取设备所占比例
			if (null == roomId || "".equals(roomId)) {
				return null;
			}
			TbCloud2EquipmentObj equip = new TbCloud2EquipmentObj();
			equip.setRoomId(roomId);
			List<TbCloud2EquipmentObj> equipList = leaderViewBusinessService
					.queryEquipmentPerctent(equip);
			// 传输数据 到FLEX
			StringBuilder sb = new StringBuilder(
					"<chart showLabels=\"1\" showValues=\"0\" numberPrefix=\"\" useRoundEdges=\"1\" bgColor=\"FFFFFF,FFFFFF\" showBorder=\"0\">");
			if (null != equipList && !equipList.isEmpty()) {
				for (int i = 0; i < equipList.size(); i++) {
					TbCloud2EquipmentObj obj = equipList.get(i);
					if (obj.getType() == 1) {
						sb.append("<set label=\"IBM Power\" value=\"" + obj.getCount() + "\"/>");
					} else if (obj.getType() == 2) {
						sb.append("<set label=\"IBM\" value=\"" + obj.getCount() + "\"/>");
					} else if (obj.getType() == 3) {
						sb.append("<set label=\"HP\" value=\"" + obj.getCount() + "\"/>");
					} else if (obj.getType() == 4) {
						sb.append("<set label=\"STOR\" value=\"" + obj.getCount() + "\"/>");
					} else if (obj.getType() == 5) {
						sb.append("<set label=\"NET\" value=\"" + obj.getCount() + "\"/>");
					} else if (obj.getType() == 6) {
						sb.append("<set label=\"OTHER\" value=\"" + obj.getCount() + "\"/>");
					}
				}
			}
			sb.append("</chart>");

			// PrintWriter out = response.getWriter();
			// out.println(sb.toString());
			// out.flush();
			// out.close();

			PrintWriterOut.printWirter(response, sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * 
	 * @Title: 机房中机柜列表
	 * @Copyright: Copyright (c) 2011-11-30
	 * @Company: si-tech
	 * @author zhangwj
	 * @version 1.0
	 */
	public String getRoomCabinetInfo() {
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		try {
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			String roomId = request.getParameter("roomId");
			// 获得房间的中的机柜
			TbCloud2CubinetInfoObj obj = new TbCloud2CubinetInfoObj();
			if (null != roomId && !"".equals(roomId)) {
				obj.setR_id(roomId);
			}
			List<TbCloud2CubinetInfoObj> cabinetList = leaderViewBusinessService
					.queryCubListByRoomId(obj);
			List<TbCloud2HostInfoObj> sourceList = leaderViewBusinessService
					.queryHostCabinetByRid(roomId + '%');

			// 传输数据
			StringBuilder sb = new StringBuilder("{\"cabinet\":[");
			if (null != cabinetList && !cabinetList.isEmpty()) {
				for (int i = 0; i < cabinetList.size(); i++) {
					TbCloud2CubinetInfoObj cObj = cabinetList.get(i);
					/** 获取机柜告警或正常状态 */
					Map params = new HashMap();
					params.put("CQ_ID", cObj.getC_id());
					int count = leaderViewBusinessService.queryHostStatusNum(params);
					/** 定义状态变量：默认-1，正常；异常-0 */
					int status = 1;
					// 若主机包含大于1种状态，则是存在异常
					if (count > 0) {
						status = 0;
					}
					if (i > 0) {
						sb.append(",");
					}
					sb.append("{");
					sb.append("\"cid\":\"" + cObj.getC_id());
					sb.append("\",\"cname\":\"" + URLEncoder.encode(cObj.getC_name(), "utf-8"));
					sb.append("\",\"cdesc\":\"" + URLEncoder.encode(cObj.getC_desc(), "utf-8"));
					sb.append("\",\"ctype\":\"" + cObj.getC_type());
					sb.append("\",\"caddr\":\"" + cObj.getC_addr());
					sb.append("\",\"c_x\":\"" + cObj.getC_x());
					sb.append("\",\"c_y\":\"" + cObj.getC_y());
					if (cObj.getC_addr() != null) {
						String hd = leaderViewBusinessService.queryHpData(cObj.getC_addr(), "1");
						if (null == hd || 0 == hd.length()) {
							sb.append("\",\"temp1\":\"" + 21);
						} else {
							sb.append("\",\"temp1\":\"" + Integer.parseInt(hd));
						}

						hd = leaderViewBusinessService.queryHpData(cObj.getC_addr(), "2");
						if (null == hd || 0 == hd.length()) {
							sb.append("\",\"temp2\":\"" + 26);
						} else {
							sb.append("\",\"temp2\":\"" + Integer.parseInt(hd));
						}

					} else {
						sb.append("\",\"temp1\":\"" + 21);
						sb.append("\",\"temp2\":\"" + 26);
					}

					sb.append("\",\"status\":\"" + status);
					sb.append("\"}");
				}
			}

			sb.append("]");

			if (null != sourceList && !sourceList.isEmpty()) {

				List<String> idxs = new ArrayList<String>();
				List<TbCloud2HostInfoObj> hosts = new ArrayList<TbCloud2HostInfoObj>();
				Map<String, Integer> counts = new HashMap<String, Integer>();
				Map<String, StringBuilder> sources = new HashMap<String, StringBuilder>();

				for (int i = 0; i < sourceList.size(); i++) {
					TbCloud2HostInfoObj cObj = sourceList.get(i);

					String eqType = cObj.getPool_id();// cObj.getEq_type();

					if (!counts.containsKey(eqType)) {
						counts.put(eqType, 1);
						StringBuilder tmp = new StringBuilder();
						idxs.add(eqType);
						hosts.add(cObj);
						sources.put(eqType, tmp);

						tmp.append("\"" + eqType + "\":[");
						tmp.append("{");
						tmp.append("\"cq_id\":\"" + cObj.getCq_id());
						tmp.append("\",\"c_addr\":\"" + cObj.getC_addr());
						tmp.append("\"}");

					} else {

						StringBuilder tmp = sources.get(eqType);
						tmp.append(",");
						tmp.append("{");
						tmp.append("\"cq_id\":\"" + cObj.getCq_id());
						tmp.append("\",\"c_addr\":\"" + cObj.getC_addr());
						tmp.append("\"}");

					}

				}

				String idxCount = "";
				for (String idx : idxs) {
					StringBuilder source = sources.get(idx);
					source.append("]");
					sb.append(",").append(source);

					idxCount += idx + ",";
				}

				if (idxCount.length() > 0) {
					idxCount = idxCount.substring(0, idxCount.length() - 1);

					StringBuilder tmp = new StringBuilder();
					tmp.append("\"resources\":[");
					tmp.append("{");
					tmp.append("\"idxs\":\"" + idxCount);
					tmp.append("\"}");
					tmp.append("]");

					sb.append(",").append(tmp);
				}

				if (hosts.size() > 0) {

					for (TbCloud2HostInfoObj host : hosts) {
						StringBuilder tmp = new StringBuilder();

						tmp.append("\"" + host.getPool_id() + "_detail" + "\":[");
						tmp.append("{");
						tmp.append("\"desc\":\"" + URLEncoder.encode(host.getPool_desc(), "utf-8"));
						tmp.append("\",\"icon\":\""
								+ URLEncoder.encode(host.getPool_icon(), "utf-8"));
						tmp.append("\",\"name\":\""
								+ URLEncoder.encode(host.getPool_name(), "utf-8"));
						tmp.append("\"}");
						tmp.append("]");

						sb.append(",").append(tmp);

					}

				}

			}

			sb.append("}");
			// PrintWriter out = response.getWriter();
			// out.println(sb.toString());
			// out.flush();
			// out.close();
			PrintWriterOut.printWirter(response, sb.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 
	 * @Title: 获得Iaas资源池、Web资源池信息
	 * @Copyright: Copyright (c) 2012-3-24
	 * @Company: si-tech
	 * @author liys
	 * @version 1.0
	 */
	public String getSourceCabinet() {
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		try {
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");

			String roomId = request.getParameter("roomId");
			/*
			 * 获得机房中可以管理的主机
			 */
			List<TbCloud2HostInfoObj> cabinetList = leaderViewBusinessService
					.queryHostCabinetByRid(roomId);
			// 传输数据
			StringBuilder sb = new StringBuilder("{");
			StringBuilder sb1 = new StringBuilder();
			StringBuilder sb2 = new StringBuilder();
			if (null != cabinetList && !cabinetList.isEmpty()) {
				int count1 = 0;
				int count2 = 0;
				for (int i = 0; i < cabinetList.size(); i++) {
					TbCloud2HostInfoObj cObj = cabinetList.get(i);
					if ("1".equals(cObj.getEq_type())) { // 类型为IAAS资源
						if (count1 == 0) {
							sb1.append("\"issa\":[");
						}
						if (count1++ > 0) {
							sb1.append(",");
						}
						sb1.append("{");
						sb1.append("\"cq_id\":\"" + cObj.getCq_id());
						sb1.append("\",\"c_addr\":\"" + cObj.getCq_id());
						sb1.append("\"}");
					} else if ("2".equals(cObj.getEq_type())) {// 类型为WEB资源
						if (count2 == 0) {
							sb2.append("\"web\":[");
						}
						if (count2++ > 0) {
							sb2.append(",");
						}
						sb2.append("{");
						sb2.append("\"cq_id\":\"" + cObj.getCq_id());
						sb2.append("\",\"c_addr\":\"" + cObj.getCq_id());
						sb2.append("\"}");
					}
				}
			}
			sb1.append("],");
			sb2.append("]");
			sb.append(sb1).append(sb2).append("}");
			// PrintWriter out = response.getWriter();
			// out.println(sb.toString());
			// out.flush();
			// out.close();
			PrintWriterOut.printWirter(response, sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 
	 * @Title: 机柜中主机以及虚拟机列表详细信息
	 * @Copyright: Copyright (c) 2011-11-30
	 * @Company: si-tech
	 * @author zhangwj
	 * @version 1.0
	 */
	public String getComputerCabinetInfo() {
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		try {
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");

			String cabinetId = request.getParameter("cabinetId"); // 机柜的ID
			if (null == cabinetId || "".equals(cabinetId)) {
				return null;
			}
			String cabinetType = request.getParameter("cabinetType"); // 机柜类型
			if (null == cabinetType || "".equals(cabinetType)) {
				return null;
			}
			// 查询机柜中主机对应的详细信息
			List<TbCloud2HostInfoObj> hostMachList = leaderViewBusinessService
					.queryHostMachListByCid(cabinetId);

			// 获得该机柜中所有虚拟机列表
			List<TbCloud2VmhostInfoObj> vmhostList = null;
			List<HostBusiSysObj> busiList = null;
			if ("1".equals(cabinetType) || "2".equals(cabinetType)) { // 获得虚拟机列表
				vmhostList = leaderViewBusinessService.queryVmhostListByCid(cabinetId);
			} else {// 获得主机列表
				busiList = leaderViewBusinessService.queryHostBusiSysByCid(cabinetId);

			}

			// 传输数据 到FLEX
			StringBuilder sb = new StringBuilder("[");
			if (null != hostMachList && hostMachList.size() > 0) {
				for (int i = 0; i < hostMachList.size(); i++) {
					TbCloud2HostInfoObj obj = hostMachList.get(i);
					if (i > 0) {
						sb.append(",");
					}
					sb.append("{");
					sb.append("\"hid\":\"" + obj.getEq_id());
					sb.append("\",\"hname\":\"" + URLEncoder.encode(obj.getEq_name(), "utf-8"));
					sb.append("\",\"htype\":\"" + obj.getEq_type());
					sb.append("\",\"hip\":\"" + obj.getEq_ip());
					sb.append("\",\"hstname\":\""
							+ URLEncoder.encode(obj.getEq_hostname(), "utf-8"));
					sb.append("\",\"position\":\"" + obj.getPosition());
					sb.append("\",\"sn\":\"" + obj.getSn());
					sb.append("\",\"status\":\"" + obj.getStatus());
					sb.append("\",\"temperature\":\"" + obj.getTemperature());

					if ("1".equals(cabinetType) || "2".equals(cabinetType)) { // 添加虚拟机列表
						sb.append("\",\"vmlist\":[");
						StringBuilder temp = new StringBuilder();
						if (null != vmhostList && vmhostList.size() > 0) {
							int count = 0;
							for (int j = 0; j < vmhostList.size(); j++) {
								TbCloud2VmhostInfoObj vmobj = vmhostList.get(j);
								if (obj.getEq_id().equals(vmobj.getEQ_ID())) {
									if (count++ > 0) {
										temp.append(",");
									}
									int domain = leaderViewBusinessService.getVhostDomainByIp(vmobj
											.getVH_IP());// 虚拟机所属的域
									temp.append("{");
									temp.append("\"vid\":\"" + vmobj.getVH_ID());
									temp.append("\",\"vname\":\""
											+ URLEncoder.encode(vmobj.getVH_NAME(), "utf-8"));
									temp.append("\",\"hname\":\""
											+ URLEncoder.encode(vmobj.getEQ_NAME(), "utf-8"));
									// 添加非空判断
									String system = vmobj.getVH_SYSTEM() == null ? "暂无" : vmobj
											.getVH_SYSTEM();
									temp.append("\",\"system\":\""
											+ URLEncoder.encode(system, "utf-8"));
									temp.append("\",\"vip\":\"" + vmobj.getVH_IP());
									temp.append("\",\"vstart\":\"" + vmobj.getVH_STARTED());
									temp.append("\",\"vcomplet\":\"" + vmobj.getVH_COMPLETED());
									temp.append("\",\"vdeploy\":\"" + vmobj.getVH_DEPLOYETIME());
									temp.append("\",\"vupdate\":\"" + vmobj.getVH_UPTIME());
									String status = vmobj.getVH_STATUS();
									if (null == status || 0 == status.length()) {
										temp.append("\",\"status\":\"" + "0");
									} else {
										temp.append("\",\"status\":\"" + status);
									}

									temp.append("\",\"domain\":\"" + domain);
									temp.append("\"}");
								}
							}
						}
						if (!"".equals(temp.toString())) {
							sb.append(temp.toString());
						}
						sb.append("]");
					} else {
						sb.append("\",\"busilist\":[");
						StringBuilder temp = new StringBuilder();
						if (null != busiList && busiList.size() > 0) {
							int count = 0;
							for (int j = 0; j < busiList.size(); j++) {
								HostBusiSysObj busiObj = busiList.get(j);
								if (obj.getEq_id().equals(busiObj.getEq_id())) {
									if (count++ > 0) {
										temp.append(",");
									}
									temp.append("{");
									temp.append("\"id\":\"" + busiObj.getApp_id());
									temp.append("\",\"name\":\""
											+ URLEncoder.encode(busiObj.getApp_name(), "utf-8"));
									temp.append("\",\"desc\":\""
											+ URLEncoder.encode(busiObj.getApp_desc(), "utf-8"));
									temp.append("\",\"process\":\"" + busiObj.getProcess());
									temp.append("\",\"mem\":\"" + busiObj.getMem());
									temp.append("\",\"hid\":\"" + busiObj.getHost_id());
									temp.append("\",\"hip\":\"" + busiObj.getHost_ip());
									temp.append("\",\"sid\":\"" + busiObj.getSys_id());
									temp.append("\",\"sname\":\""
											+ URLEncoder.encode(busiObj.getSys_name(), "utf-8"));
									temp.append("\",\"eid\":\"" + busiObj.getEq_id());
									temp.append("\"}");
								}
							}
						}
						if (!"".equals(temp.toString())) {
							sb.append(temp.toString());
						}
						sb.append("]");
					}

					sb.append("}");
				}
			}
			sb.append("]");
			// PrintWriter out = response.getWriter();
			// out.println(sb.toString());
			// out.flush();
			// out.close();
			PrintWriterOut.printWirter(response, sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @Title: 获得主机监控信息
	 * @Copyright: Copyright (c) 2012-3-20
	 * @Company: si-tech
	 * @author zhangwj
	 * @version 1.0
	 */
	public String getHostMonitoringInfo() {
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		try {
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			// 主机编号
			String hostId = request.getParameter("id");
			if (null == hostId || "".equals(hostId)) {
				return null;
			}
			Date date = new Date();// 当前时间
			String coll_time = null;
			// CPU监控时间区间设置
			coll_time = leaderViewBusinessService.queryDateRegion(
					LeaderViewBusinessConstant.MONITORING_GAP_CPU, date);
			MonitoringObj obj = new MonitoringObj();
			obj.setId(hostId);
			// 主机CPU监控指标
			obj.setKpi_id(LeaderViewBusinessConstant.MONITORING_HM_KPI_CPU);
			obj.setColl_time(coll_time);
			/*
			 * 主机CPU监控
			 */
			List<MonitoringObj> cpuMonitor = leaderViewBusinessService
					.queryHostMonitoringByObj(obj);
			/*
			 * 主机MEM监控
			 */
			obj.setKpi_id(LeaderViewBusinessConstant.MONITORING_HM_KPI_MEM);
			coll_time = leaderViewBusinessService.queryDateRegion(
					LeaderViewBusinessConstant.MONITORING_GAP_MEM, date);
			obj.setColl_time(coll_time);
			List<MonitoringObj> memMonitor = leaderViewBusinessService
					.queryHostMonitoringByObj(obj);
			/*
			 * 主机存储监控
			 */
			obj.setKpi_id(LeaderViewBusinessConstant.MONITORING_HM_KPI_STOR);
			coll_time = leaderViewBusinessService.queryDateRegion(
					LeaderViewBusinessConstant.MONITORING_GAP_STOR, date);
			obj.setColl_time(coll_time);
			List<MonitoringObj> storMonitor = leaderViewBusinessService
					.queryHostMonitoringByObj(obj);
			/*
			 * 主机网络监控
			 */
			obj.setKpi_id(LeaderViewBusinessConstant.MONITORING_HM_KPI_NET);
			coll_time = leaderViewBusinessService.queryDateRegion(
					LeaderViewBusinessConstant.MONITORING_GAP_NET, date);
			obj.setColl_time(coll_time);
			List<MonitoringObj> netMonitor = leaderViewBusinessService
					.queryHostMonitoringByObj(obj);

			/*
			 * 监控区间的 CPU、MEM、STOR、NET值
			 */
			String cpuStr = getMonitoringStr(cpuMonitor, "cpu");
			String memStr = getMonitoringStr(memMonitor, "mem");
			String storStr = getMonitoringStr(storMonitor, "stor");
			String netStr = getMonitoringStr(netMonitor, "net");

			/*
			 * 当前的 CPU、MEM、STOR、NET值
			 */
			String lastMonitor = getLatestMonitoringStr(cpuMonitor, memMonitor, storMonitor,
					netMonitor);
			/*
			 * 主机监控字符串拼接
			 */
			StringBuilder monitorStr = new StringBuilder("{");
			monitorStr.append(cpuStr).append(",");
			monitorStr.append(memStr).append(",");
			monitorStr.append(storStr).append(",");
			monitorStr.append(netStr).append(",");
			monitorStr.append(lastMonitor);
			monitorStr.append("}");
			/*
			 * JSON字符串传递
			 */
			// PrintWriter out = response.getWriter();
			// out.println(monitorStr.toString());
			// out.flush();
			// out.close();

			PrintWriterOut.printWirter(response, monitorStr.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @Title: 监控指标拼接 list:监控数据集合 type：监控类型 CPU、MEM、STOR、NET
	 * @Copyright: Copyright (c) 2012-3-28
	 * @Company: si-tech
	 * @author zhangwj
	 * @version 1.0
	 */
	private String getMonitoringStr(List<MonitoringObj> list, String type) {
		StringBuilder sb = new StringBuilder("\"" + type + "\":[");
		if (null != list && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				MonitoringObj obj = list.get(i);
				String value = obj.getKpi_value();
				String time = obj.getColl_time();
				if (i > 0) {
					sb.append(",");
				}
				sb.append("{");
				sb.append("\"time\":\"" + time);
				sb.append("\",\"value\":\"" + value);
				sb.append("\"}");
			}
		}
		sb.append("]");
		return sb.toString();
	}

	/**
	 * 
	 * @Title: 获得当前监控指标 参数依此是：cpuList cpu监控集合、memList 内存监控集合、storList
	 *         存储监控集合、netList网络监控集合
	 * @Copyright: Copyright (c) 2012-3-28
	 * @Company: si-tech
	 * @author zhangwj
	 * @version 1.0
	 */
	private String getLatestMonitoringStr(List<MonitoringObj> cpuList, List<MonitoringObj> memList,
			List<MonitoringObj> storList, List<MonitoringObj> netList) {
		String cpu = "0";
		String mem = "0";
		String stor = "0";
		String net = "0";
		if (null != cpuList && cpuList.size() > 0) {
			cpu = ((MonitoringObj) cpuList.get(cpuList.size() - 1)).getKpi_value();
		}
		if (null != memList && memList.size() > 0) {
			mem = ((MonitoringObj) memList.get(memList.size() - 1)).getKpi_value();
		}
		if (null != storList && storList.size() > 0) {
			stor = ((MonitoringObj) storList.get(storList.size() - 1)).getKpi_value();
		}
		if (null != netList && netList.size() > 0) {
			net = ((MonitoringObj) netList.get(netList.size() - 1)).getKpi_value();
		}
		StringBuilder sb = new StringBuilder("\"now\":{");
		sb.append("\"cpu\":\"" + cpu);
		sb.append("\",\"mem\":\"" + mem);
		sb.append("\",\"stor\":\"" + stor);
		sb.append("\",\"net\":\"" + net);
		sb.append("\"}");
		return sb.toString();
	}

	/**
	 * 
	 * @Title: 获得虚拟机监控信息
	 * 
	 *         监控的指标有：CPU 6个小时、MEMORY 12小时、STORGY 24 小时、NET 24小时
	 * @Copyright: Copyright (c) 2012-3-20
	 * @Company: si-tech
	 * @author zhangwj
	 * @version 1.0
	 */
	public String getVhostMonitoringInfo() {
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		try {
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			// 虚拟机编号
			String vhostId = request.getParameter("id");
			if (null == vhostId || "".equals(vhostId)) {
				return null;
			}
			Date date = new Date();// 当前时间
			String coll_time = null;
			MonitoringObj obj = new MonitoringObj();
			obj.setId(vhostId);
			/*
			 * 主机CPU监控
			 */
			obj.setKpi_id(LeaderViewBusinessConstant.MONITORING_VM_KPI_CPU);
			coll_time = leaderViewBusinessService.queryDateRegion(
					LeaderViewBusinessConstant.MONITORING_GAP_CPU, date); // CPU监控时间区间设置
			obj.setColl_time(coll_time);
			List<MonitoringObj> cpuMonitor = leaderViewBusinessService
					.queryVhostMonitoringByObj(obj); // 获得当前虚拟机监控信息
			/*
			 * 主机MEM监控
			 */
			obj.setKpi_id(LeaderViewBusinessConstant.MONITORING_VM_KPI_MEM);
			coll_time = leaderViewBusinessService.queryDateRegion(
					LeaderViewBusinessConstant.MONITORING_GAP_MEM, date);
			obj.setColl_time(coll_time);
			List<MonitoringObj> memMonitor = leaderViewBusinessService
					.queryVhostMonitoringByObj(obj);
			/*
			 * 主机存储监控
			 */
			obj.setKpi_id(LeaderViewBusinessConstant.MONITORING_VM_KPI_STOR);
			coll_time = leaderViewBusinessService.queryDateRegion(
					LeaderViewBusinessConstant.MONITORING_GAP_STOR, date);
			obj.setColl_time(coll_time);
			List<MonitoringObj> storMonitor = leaderViewBusinessService
					.queryVhostMonitoringByObj(obj);
			/*
			 * 主机网络监控
			 */
			obj.setKpi_id(LeaderViewBusinessConstant.MONITORING_VM_KPI_NET);
			coll_time = leaderViewBusinessService.queryDateRegion(
					LeaderViewBusinessConstant.MONITORING_GAP_NET, date);
			obj.setColl_time(coll_time);
			List<MonitoringObj> netMonitor = leaderViewBusinessService
					.queryVhostMonitoringByObj(obj);

			/*
			 * 监控区间的 CPU、MEM、STOR、NET值
			 */
			String cpuStr = getMonitoringStr(cpuMonitor, "cpu");
			String memStr = getMonitoringStr(memMonitor, "mem");
			String storStr = getMonitoringStr(storMonitor, "stor");
			String netStr = getMonitoringStr(netMonitor, "net");
			/*
			 * 当前的 CPU、MEM、STOR、NET值
			 */
			String lastMonitor = getLatestMonitoringStr(cpuMonitor, memMonitor, storMonitor,
					netMonitor);
			/*
			 * 主机监控字符串拼接
			 */
			StringBuilder monitorStr = new StringBuilder("{");
			monitorStr.append(cpuStr).append(",");
			monitorStr.append(memStr).append(",");
			monitorStr.append(storStr).append(",");
			monitorStr.append(netStr).append(",");
			monitorStr.append(lastMonitor);
			monitorStr.append("}");
			/*
			 * JSON字符串传递
			 */
			// PrintWriter out = response.getWriter();
			// out.println(monitorStr.toString());
			// out.flush();
			// out.close();
			PrintWriterOut.printWirter(response, monitorStr.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @Title: 获得应用监控信息
	 * 
	 *         指标有CPU 6个小时、内存12个小时
	 * @Copyright: Copyright (c) 2012-3-20
	 * @Company: si-tech
	 * @author zhangwj
	 * @version 1.0
	 */
	public String getAppMonitoringInfo() {
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		try {
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			// 应用编号
			String appId = request.getParameter("id");
			if (null == appId || "".equals(appId)) {
			}
			Date date = new Date();// 当前时间
			String coll_time = null;
			MonitoringObj obj = new MonitoringObj();
			obj.setId(appId);
			/*
			 * 主机CPU监控
			 */
			obj.setKpi_id(LeaderViewBusinessConstant.MONITORING_APP_KPI_CPU);
			coll_time = leaderViewBusinessService.queryDateRegion(
					LeaderViewBusinessConstant.MONITORING_GAP_CPU, date); // CPU监控时间区间设置
			obj.setColl_time(coll_time);
			List<MonitoringObj> cpuMonitor = leaderViewBusinessService.queryAppMonitoringByObj(obj); // 获得当前虚拟机监控信息
			/*
			 * 主机MEM监控
			 */
			obj.setKpi_id(LeaderViewBusinessConstant.MONITORING_APP_KPI_MEM);
			coll_time = leaderViewBusinessService.queryDateRegion(
					LeaderViewBusinessConstant.MONITORING_GAP_MEM, date);
			obj.setColl_time(coll_time);
			List<MonitoringObj> memMonitor = leaderViewBusinessService.queryAppMonitoringByObj(obj);

			/*
			 * 监控区间的 CPU、MEM值
			 */
			String cpuStr = getMonitoringStr(cpuMonitor, "cpu");
			String memStr = getMonitoringStr(memMonitor, "mem");
			/*
			 * 当前的 CPU、MEM值
			 */
			String lastMonitor = getLatestMonitoringStr(cpuMonitor, memMonitor, null, null);
			/*
			 * 主机监控字符串拼接
			 */
			StringBuilder monitorStr = new StringBuilder("{");
			monitorStr.append(cpuStr).append(",");
			monitorStr.append(memStr).append(",");
			monitorStr.append(lastMonitor);
			monitorStr.append("}");
			/*
			 * JSON字符串传递
			 */
			// PrintWriter out = response.getWriter();
			// out.println(monitorStr.toString());
			// out.flush();
			// out.close();
			PrintWriterOut.printWirter(response, monitorStr.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @Title: 获得领导视图第一个页面中的数据
	 * @Copyright: Copyright (c) 2012-3-20
	 * @Company: si-tech
	 * @author zhangwj
	 * @version 1.0
	 */
	public String getDataForLeaderView1() {
		HttpServletResponse response = Struts2Utils.getResponse();
		// 获得地图中需要显示的信息
		TbCloud2MroomInfoObj obj = new TbCloud2MroomInfoObj();
		obj.setM_id("1");// 设置为杏花岭区
		TbCloud2MroomInfoObj mroomObj = leaderViewBusinessService.queryMRoomInfoByObj(obj);
		// 获得机房设备总是、房间总数
		List<TbCloud2RoomInfoObj> roomList = leaderViewBusinessService.queryRoomListByMid("1");

		int roomCount = roomList.size();// 房间数量
		int m_total = 0; // 设备总数
		for (TbCloud2RoomInfoObj o : roomList) {
			m_total += o.getM_TOTAL();
		}
		StringBuilder sb = new StringBuilder("{");
		sb.append("\"map\":{");
		sb.append("\"m_id\":\"" + mroomObj.getM_id());
		String m_name = "";
		String man = "";
		try {
			m_name = URLEncoder.encode(mroomObj.getM_name(), "utf-8");
			man = URLEncoder.encode(mroomObj.getM_man(), "utf-8");
		} catch (Exception e) {
			m_name = "unknow";
			man = "admin";
		}
		sb.append("\",\"m_name\":\"" + m_name);
		sb.append("\",\"num1\":\"" + roomCount);
		sb.append("\",\"num2\":\"" + m_total);
		sb.append("\",\"man\":\"" + man);
		sb.append("\",\"phone\":\"" + mroomObj.getM_tel());
		sb.append("\",\"mail\":\"" + mroomObj.getM_email());
		sb.append("\"},\"room\":[");
		if (null != roomList && roomList.size() > 0) {
			int count = 0;
			for (TbCloud2RoomInfoObj o : roomList) {
				if (++count > 1) {
					sb.append(",");
				}
				sb.append("{");
				sb.append("\"r_id\":\"" + o.getR_ID());
				sb.append("\",\"r_name\":\"" + o.getR_NAME());

				if (o.getR_NAME() != null) {
					String hd = leaderViewBusinessService.queryHpData(o.getR_NAME(), "1");
					if (null == hd || 0 == hd.length()) {
						sb.append("\",\"temp\":\"" + o.getTEMPERATURE());
					} else {
						sb.append("\",\"temp\":\"" + Integer.parseInt(hd));
					}

					hd = leaderViewBusinessService.queryHpData(o.getR_NAME(), "2");

					if (null == hd || 0 == hd.length()) {
						sb.append("\",\"humi\":\"" + o.getHUMIDITY());
					} else {
						sb.append("\",\"humi\":\"" + Integer.parseInt(hd));
					}

				} else {
					sb.append("\",\"temp\":\"" + o.getTEMPERATURE());
					sb.append("\",\"humi\":\"" + o.getHUMIDITY());
				}

				sb.append("\",\"total\":\"" + o.getM_TOTAL());
				sb.append("\",\"used\":\"" + o.getM_USED());
				sb.append("\"}");
			}
		}
		sb.append("]");
		sb.append("}");
		// PrintWriter out = response.getWriter();
		// out.println(sb.toString());
		// out.flush();
		// out.close();
		PrintWriterOut.printWirter(response, sb.toString());
		return null;
	}

	private LeaderViewBusinessService leaderViewBusinessService;

	public void setLeaderViewBusinessService(LeaderViewBusinessService leaderViewBusinessService) {
		this.leaderViewBusinessService = leaderViewBusinessService;
	}
}
