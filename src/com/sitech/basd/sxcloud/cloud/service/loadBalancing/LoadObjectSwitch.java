package com.sitech.basd.sxcloud.cloud.service.loadBalancing;

import radware.AppDirector.Farms.Farm;
import radware.AppDirector.Farms.FarmServer;
import radware.AppDirector.Farms.FarmServer_AdminStatus;
import radware.AppDirector.Farms.FarmServer_OperationMode;
import radware.AppDirector.Farms.FarmServer_Type;
import radware.AppDirector.Farms.Farm_ConnectivityCheckMethod;
import radware.AppDirector.Farms.Farm_DispatchMethod;
import radware.AppDirector.Farms.Farm_SessionsMode;
import radware.AppDirector.Farms.FeatureStatus;
import radware.AppDirector.Layer4Policies.L4Policy;
import radware.AppDirector.Layer4Policies.L4Policy_Application;
import radware.AppDirector.Layer4Policies.L4Policy_L4Protocol;
import radware.AppDirector.Layer4Policies.L4Policy_RedundancyStatus;

import com.sitech.basd.sxcloud.cloud.domain.loadBalancing.FarmObj;
import com.sitech.basd.sxcloud.cloud.domain.loadBalancing.FarmServerObj;
import com.sitech.basd.sxcloud.cloud.domain.loadBalancing.L4PolicyObj;
import com.sitech.basd.sxcloud.cloud.domain.loadBalancing.LoadConstant;

public class LoadObjectSwitch {

	/**
	 * 
	 * @Title: 云平台Farm 与 负载均衡接口Farm 转换
	 * 
	 * @Copyright: Copyright (c) 2012-6-8
	 * @Company: si-tech
	 * @author zhangwj
	 * @version 1.0
	 */
	public static Farm farmObjSwitch(FarmObj obj) {
		Farm farm = new Farm();
		if (null != obj) {
			// BeanUtils.copyProperties(farm,obj);
			farm.setFarmName(obj.getFarmName());
			farm.setAgingTime(obj.getAgingTime());
			farm.setConnectivityCheckPort(obj.getConnectivityCheckPort());
			farm.setConnectivityCheckInterval(obj
					.getConnectivityCheckInterval());
			farm.setConnectivityCheckRetries(obj.getConnectivityCheckRetries());
			farm.setConnectionDenials(obj.getConnectionDenials());
			farm.setExtendedCheckFrequency(obj.getExtendedCheckFrequency());
			farm.setHomePage(obj.getHomePage());
			farm.setBandwidthLimit(obj.getBandwidthLimit());
			farm.setAuthorizedUsername(obj.getAuthorizedUsername());
			farm.setAuthorizedPassword(obj.getAuthorizedPassword());

			String adminStatus = obj.getAdminStatus();
			String dispatchMethod = obj.getDispatchMethod();
			String sessionMode = obj.getSessionsMode();
			String connectivityCheckMethod = obj.getConnectivityCheckMethod();
			/*
			 * 转换 adminStatus
			 */
			if (adminStatus.equals(LoadConstant.F_AdminStatus_Enabled)) {
				farm.setAdminStatus(FeatureStatus.Enabled);
			} else {
				farm.setAdminStatus(FeatureStatus.Disabled);
			}
			/*
			 * 转换 dispatchMethod
			 */
			if (dispatchMethod.equals(LoadConstant.F_DM_Cyclic)) {
				farm.setDispatchMethod(Farm_DispatchMethod.Cyclic);
			} else if (dispatchMethod
					.equals(LoadConstant.F_DM_LeastAmountofTraffic)) {
				farm
						.setDispatchMethod(Farm_DispatchMethod.LeastAmountofTraffic);
			} else if (dispatchMethod
					.equals(LoadConstant.F_DM_FewestNumberofUsers)) {
				farm.setDispatchMethod(Farm_DispatchMethod.FewestNumberofUsers);
			} else if (dispatchMethod
					.equals(LoadConstant.F_DM_FewestNumberofUsersLocal)) {
				farm
						.setDispatchMethod(Farm_DispatchMethod.FewestNumberofUsersLocal);
			} else if (dispatchMethod
					.equals(LoadConstant.F_DM_LeastAmountofTrafficLocal)) {
				farm
						.setDispatchMethod(Farm_DispatchMethod.LeastAmountofTrafficLocal);
			} else if (dispatchMethod.equals(LoadConstant.F_DM_NT1)) {
				farm.setDispatchMethod(Farm_DispatchMethod.NT1);
			} else if (dispatchMethod.equals(LoadConstant.F_DM_NT2)) {
				farm.setDispatchMethod(Farm_DispatchMethod.NT2);
			} else if (dispatchMethod.equals(LoadConstant.F_DM_Private1)) {
				farm.setDispatchMethod(Farm_DispatchMethod.Private1);
			} else if (dispatchMethod.equals(LoadConstant.F_DM_Private2)) {
				farm.setDispatchMethod(Farm_DispatchMethod.Private2);
			} else if (dispatchMethod.equals(LoadConstant.F_DM_WeightedCyclic)) {
				farm.setDispatchMethod(Farm_DispatchMethod.WeightedCyclic);
			} else if (dispatchMethod.equals(LoadConstant.F_DM_Hashing)) {
				farm.setDispatchMethod(Farm_DispatchMethod.Hashing);
			} else if (dispatchMethod.equals(LoadConstant.F_DM_ResponseTime)) {
				farm.setDispatchMethod(Farm_DispatchMethod.ResponseTime);
			}
			/*
			 * 转换 sessionMode
			 */
			if (sessionMode.equals(LoadConstant.F_SM_Regular)) {
				farm.setSessionsMode(Farm_SessionsMode.Regular);
			} else if (sessionMode.equals(LoadConstant.F_SM_ServerPerSession)) {
				farm.setSessionsMode(Farm_SessionsMode.ServerPerSession);
			} else if (sessionMode.equals(LoadConstant.F_SM_EntryPerSession)) {
				farm.setSessionsMode(Farm_SessionsMode.EntryPerSession);
			} else if (sessionMode
					.equals(LoadConstant.F_SM_RemoveOnSessionEndEPS)) {
				farm.setSessionsMode(Farm_SessionsMode.RemoveOnSessionEndEPS);
			} else if (sessionMode
					.equals(LoadConstant.F_SM_RemoveOnSessionEndSPS)) {
				farm.setSessionsMode(Farm_SessionsMode.RemoveOnSessionEndSPS);
			}

			/*
			 * 转换 connectivityCheckMethod
			 */
			if (connectivityCheckMethod.equals(LoadConstant.F_CCM_Ping)) {
				farm
						.setConnectivityCheckMethod(Farm_ConnectivityCheckMethod.Ping);
			} else if (connectivityCheckMethod
					.equals(LoadConstant.F_CCM_NoChecks)) {
				farm
						.setConnectivityCheckMethod(Farm_ConnectivityCheckMethod.NoChecks);
			} else if (connectivityCheckMethod
					.equals(LoadConstant.F_CCM_HTTPPage)) {
				farm
						.setConnectivityCheckMethod(Farm_ConnectivityCheckMethod.HTTPPage);
			} else if (connectivityCheckMethod
					.equals(LoadConstant.F_CCM_TCPPort)) {
				farm
						.setConnectivityCheckMethod(Farm_ConnectivityCheckMethod.TCPPort);
			} else if (connectivityCheckMethod
					.equals(LoadConstant.F_CCM_UDPPort)) {
				farm
						.setConnectivityCheckMethod(Farm_ConnectivityCheckMethod.UDPPort);
			}

		}
		return farm;
	}

	/**
	 * 
	 * @Title: 云平台FarmService 与 负载均衡接口ServiceFarm 转换
	 * @Copyright: Copyright (c) 2012-6-8
	 * @Company: si-tech
	 * @author zhangwj
	 * @version 1.0
	 */
	public static FarmServer serviceObjSwitch(FarmServerObj obj) {
		FarmServer farmServer = new FarmServer();

		if (null != obj) {

			// try {
			// BeanUtils.copyProperties(farmServer, obj);
			farmServer.setFarmName(obj.getFarmName());
			farmServer.setServerAddress(obj.getServerAddress());
			farmServer.setServerPort(obj.getServerPort());
			farmServer.setServerName(obj.getServerName());
			farmServer.setWeight(obj.getWeight());
			farmServer.setConnectionLimit(obj.getConnectionLimit());
			farmServer.setBandwidthLimit(obj.getBandwidthLimit());
			farmServer.setRedirectTo(obj.getRedirectTo());
			farmServer.setServerDescription(obj.getServerDescription());
			farmServer.setResponseThreshold(obj.getResponseThreshold());
			farmServer.setBackupServerAddress(obj.getBackupServerAddress());
			farmServer.setClientNATAddressRange(obj.getClientNATAddressRange());
			farmServer.setFarmNameForLocalFarm(obj.getFarmNameForLocalFarm());

			String adminStatus = obj.getAdminStatus();
			String type = obj.getType();
			String operationMode = obj.getOperationMode();
			String clientNAT = obj.getClientNAT();
			String backPreemption = obj.getADServerBackupPreemption();

			/*
			 * 转换adminStatus
			 */
			if (adminStatus.equals(LoadConstant.FS_AdminStatus_Enable)) {
				farmServer.setAdminStatus(FarmServer_AdminStatus.Enable);
			} else if (adminStatus.equals(LoadConstant.FS_AdminStatus_Disable)) {
				farmServer.setAdminStatus(FarmServer_AdminStatus.Disable);
			} else if (adminStatus.equals(LoadConstant.FS_AdminStatus_Shutdown)) {
				farmServer.setAdminStatus(FarmServer_AdminStatus.Shutdown);
			}
			/*
			 * 转换type
			 */
			if (type.equals(LoadConstant.FS_TYPE_Regular)) {
				farmServer.setType(FarmServer_Type.Regular);
			} else if (type.equals(LoadConstant.FS_TYPE_DistributedAppDirector)) {
				farmServer.setType(FarmServer_Type.DistributedAppDirector);
			} else if (type.equals(LoadConstant.FS_TYPE_RemoteServer)) {
				farmServer.setType(FarmServer_Type.RemoteServer);
			} else if (type.equals(LoadConstant.FS_TYPE_LocalFarm)) {
				farmServer.setType(FarmServer_Type.LocalFarm);
			} else if (type.equals(LoadConstant.FS_TYPE_LocalAppDirector)) {
				farmServer.setType(FarmServer_Type.LocalAppDirector);
			} else if (type.equals(LoadConstant.FS_TYPE_LocalTriangulation)) {
				farmServer.setType(FarmServer_Type.LocalTriangulation);
			}
			/*
			 * 转换operationMode
			 */
			if (operationMode.equals(LoadConstant.FS_OM_Regular)) {
				farmServer.setOperationMode(FarmServer_OperationMode.Regular);
			} else if (operationMode.equals(LoadConstant.FS_OM_Backup)) {
				farmServer.setOperationMode(FarmServer_OperationMode.Backup);
			}
			/*
			 * 转换clientNAT
			 */
			if (clientNAT.equals(LoadConstant.FS_CNAT_Enabled)) {
				farmServer.setClientNAT(FeatureStatus.Enabled);
			} else if (clientNAT.equals(LoadConstant.FS_CNAT_Disabled)) {
				farmServer.setClientNAT(FeatureStatus.Disabled);
			}
			/*
			 * 转换backPreemption
			 */
			if (backPreemption.equals(LoadConstant.FS_BP_Enabled)) {
				farmServer.setADServerBackupPreemption(FeatureStatus.Enabled);
			} else if (backPreemption.equals(LoadConstant.FS_BP_Disabled)) {
				farmServer.setADServerBackupPreemption(FeatureStatus.Disabled);
			}
			// } catch (IllegalAccessException e) {
			// e.printStackTrace();
			// return null;
			// } catch (InvocationTargetException e) {
			// e.printStackTrace();
			// return null;
			// }
		}
		return farmServer;

	}

	/**
	 * 
	 * @Title: 云平台L4Policy 与 负载均衡接口L4Policy 转换
	 * 
	 * 
	 * @Copyright: Copyright (c) 2012-6-8
	 * @Company: si-tech
	 * @author zhangwj
	 * @version 1.0
	 */
	public static L4Policy l4ObjSwitch(L4PolicyObj obj) {
		L4Policy l4policy = new L4Policy();

		if (null != obj) {
			// try {
			// BeanUtils.copyProperties(l4policy, obj);
			l4policy.setVirtualIP(obj.getVirtualIP());
			l4policy.setL4Port(obj.getL4Port());
			l4policy.setSourceIPFrom(obj.getSourceIPFrom());
			l4policy.setSourceIPTo(obj.getSourceIPTo());
			l4policy.setL4PolicyName(obj.getL4PolicyName());
			l4policy.setL7PolicyName(obj.getL7PolicyName());
			l4policy.setFarmName(obj.getFarmName());
			l4policy.setBackendEncryptionPort(obj.getBackendEncryptionPort());
			// l4policy.setBytesOfRequestToRead(obj.getBytesOfRequestToRead());
			l4policy.setPolicyDefinedBy(obj.getPolicyDefinedBy());
			l4policy.setSegmentName(obj.getSegmentName());
			l4policy.setExplicitFarmName(obj.getExplicitFarmName());
			l4policy.setAction(obj.getAction());
			l4policy.setSSLPolicy(obj.getSSLPolicy());
			l4policy.setCompressionPolicy(obj.getCompressionPolicy());
			l4policy.setCachingPolicy(obj.getCachingPolicy());
			l4policy.setClientAuthenticationPolicy(obj
					.getClientAuthenticationPolicy());
			l4policy.setHttpPolicy(obj.getHttpPolicy());

			String application = obj.getApplication();
			String l4Protocol = obj.getL4Protocol();
			String redundancyStatus = obj.getRedundancyStatus();

			/*
			 * 转换application
			 */
			if (application.equals(LoadConstant.L4_APP_Any)) {
				l4policy.setApplication(L4Policy_Application.Any);
			} else if (application.equals(LoadConstant.L4_APP_FTPControl)) {
				l4policy.setApplication(L4Policy_Application.FTPControl);
			} else if (application.equals(LoadConstant.L4_APP_HTTP)) {
				l4policy.setApplication(L4Policy_Application.HTTP);
			} else if (application.equals(LoadConstant.L4_APP_HTTPS)) {
				l4policy.setApplication(L4Policy_Application.HTTPS);
			} else if (application.equals(LoadConstant.L4_APP_PING)) {
				l4policy.setApplication(L4Policy_Application.PING);
			} else if (application.equals(LoadConstant.L4_APP_REXEC)) {
				l4policy.setApplication(L4Policy_Application.REXEC);
			} else if (application.equals(LoadConstant.L4_APP_RSH)) {
				l4policy.setApplication(L4Policy_Application.RSH);
			} else if (application.equals(LoadConstant.L4_APP_RTSP)) {
				l4policy.setApplication(L4Policy_Application.RTSP);
			} else if (application.equals(LoadConstant.L4_APP_SIP)) {
				l4policy.setApplication(L4Policy_Application.SIP);
			} else if (application.equals(LoadConstant.L4_APP_SIPS)) {
				l4policy.setApplication(L4Policy_Application.SIPS);
			} else if (application.equals(LoadConstant.L4_APP_TSCOOKIE)) {
				l4policy.setApplication(L4Policy_Application.TSCOOKIE);
			} else if (application.equals(LoadConstant.L4_APP_RADIUS)) {
				l4policy.setApplication(L4Policy_Application.RADIUS);
			} else if (application.equals(LoadConstant.L4_APP_TCP)) {
				l4policy.setApplication(L4Policy_Application.TCP);
			} else if (application.equals(LoadConstant.L4_APP_UDP)) {
				l4policy.setApplication(L4Policy_Application.UDP);
			} else if (application.equals(LoadConstant.L4_APP_TFTP)) {
				l4policy.setApplication(L4Policy_Application.TFTP);
			} else if (application
					.equals(LoadConstant.L4_APP_VirtualIPInterface)) {
				l4policy
						.setApplication(L4Policy_Application.VirtualIPInterface);
			} else if (application.equals(LoadConstant.L4_APP_SCTP)) {
				l4policy.setApplication(L4Policy_Application.SCTP);
			} else if (application.equals(LoadConstant.L4_APP_MHSCTP)) {
				l4policy.setApplication(L4Policy_Application.MHSCTP);
			} else if (application.equals(LoadConstant.L4_APP_GenericSSL)) {
				l4policy.setApplication(L4Policy_Application.GenericSSL);
			}
			/*
			 * 转换l4Protocol
			 */
			if (l4Protocol.equals(LoadConstant.L4_PTL_TCP)) {
				l4policy.setL4Protocol(L4Policy_L4Protocol.TCP);
			} else if (l4Protocol.equals(LoadConstant.L4_PTL_UDP)) {
				l4policy.setL4Protocol(L4Policy_L4Protocol.UDP);
			} else if (l4Protocol.equals(LoadConstant.L4_PTL_ICMP)) {
				l4policy.setL4Protocol(L4Policy_L4Protocol.ICMP);
			} else if (l4Protocol.equals(LoadConstant.L4_PTL_SCTP)) {
				l4policy.setL4Protocol(L4Policy_L4Protocol.SCTP);
			} else if (l4Protocol.equals(LoadConstant.L4_PTL_Any)) {
				l4policy.setL4Protocol(L4Policy_L4Protocol.Any);
			} else if (l4Protocol.equals(LoadConstant.L4_PTL_Other)) {
				l4policy.setL4Protocol(L4Policy_L4Protocol.Other);
			}

			/*
			 * 转换redundancyStatus
			 */
			if (redundancyStatus.equals(LoadConstant.L4_RS_Primary)) {
				l4policy.setRedundancyStatus(L4Policy_RedundancyStatus.Primary);
			} else if (redundancyStatus.equals(LoadConstant.L4_RS_Backup)) {
				l4policy.setRedundancyStatus(L4Policy_RedundancyStatus.Backup);
			}
			// } catch (IllegalAccessException e) {
			// e.printStackTrace();
			// } catch (InvocationTargetException e) {
			// e.printStackTrace();
			// }

		}

		return l4policy;
	}
}
