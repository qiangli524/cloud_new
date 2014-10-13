package com.sitech.basd.sxcloud.cloud.domain.loadBalancing;

/**
 * 负载均衡radware常量
 * 
 * @author zhangwj
 * 
 */
public class LoadConstant {

	/**
	 * 
	 * Farm
	 * 
	 */

	// adminStatus
	public static final String F_AdminStatus_Enabled = "1"; // Enabled
	public static final String F_AdminStatus_Disabled = "2"; // Disabled

	// dispatchMethod
	public static final String F_DM_Cyclic = "1"; // Cyclic
	public static final String F_DM_LeastAmountofTraffic = "2"; // Least Amount
																// of Traffic
	public static final String F_DM_FewestNumberofUsers = "3"; // Fewest Number
																// of Users
	public static final String F_DM_LeastAmountofTrafficLocal = "4"; // Least
																		// Amount
																		// of
																		// Traffic
																		// -
																		// Local
	public static final String F_DM_FewestNumberofUsersLocal = "5"; // Fewest
																	// Number of
																	// Users -
																	// Local
	public static final String F_DM_NT1 = "6"; // NT-"1"
	public static final String F_DM_NT2 = "7"; // NT-"2"
	public static final String F_DM_Private1 = "8"; // Private-"1"
	public static final String F_DM_Private2 = "9"; // Private-"2"
	public static final String F_DM_WeightedCyclic = "10";// Weighted Cyclic
	public static final String F_DM_Hashing = "11";// Hashing
	public static final String F_DM_ResponseTime = "12";// Response Time

	// sessionsMode
	public static final String F_SM_Regular = "1"; // Regular
	public static final String F_SM_EntryPerSession = "2"; // EntryPerSession
	public static final String F_SM_ServerPerSession = "3"; // ServerPerSession
	public static final String F_SM_RemoveOnSessionEndEPS = "4"; // RemoveOnSessionEnd-EPS
	public static final String F_SM_RemoveOnSessionEndSPS = "5"; // RemoveOnSessionEnd-SPS

	// connectivityCheckMethod
	public static final String F_CCM_Ping = "1"; // Ping
	public static final String F_CCM_NoChecks = "2"; // No Checks
	public static final String F_CCM_HTTPPage = "3"; // HTTP Page
	public static final String F_CCM_TCPPort = "4"; // TCP Port
	public static final String F_CCM_UDPPort = "5"; // UDP Port

	// operationalStatus
	public static final String F_OS_Active = "1"; // Active
	public static final String F_OS_NotInService = "2"; // Not In Service

	// content
	public static final String F_CT_NonSIP = "1"; // NonSIP
	public static final String F_CT_SIP = "2"; // SIP

	/**
	 * FarmServer
	 */

	// adminStatus
	public static final String FS_AdminStatus_Enable = "1"; // Enable
	public static final String FS_AdminStatus_Disable = "2"; // Disable
	public static final String FS_AdminStatus_Shutdown = "3"; // Shutdown

	// operationalStatus
	public static final String FS_OS_Active = "1"; // Active
	public static final String FS_OS_NotInService = "2"; // Not In Service
	public static final String FS_OS_NoNewSessions = "3"; // No New Sessions
	public static final String FS_OS_InRecovery = "4"; // In Recovery

	// operationMode
	public static final String FS_OM_Regular = "1"; // Regular
	public static final String FS_OM_Backup = "2"; // Backup

	// type
	public static final String FS_TYPE_Regular = "1"; // Regular
	public static final String FS_TYPE_DistributedAppDirector = "2"; // Distributed
																		// AppDirector
	public static final String FS_TYPE_RemoteServer = "3"; // Remote Server
	public static final String FS_TYPE_LocalTriangulation = "4"; // Local
																	// Triangulation
	public static final String FS_TYPE_LocalFarm = "5"; // Local Farm
	public static final String FS_TYPE_LocalAppDirector = "6"; // Local
																// AppDirector

	// clientNAT
	public static final String FS_CNAT_Enabled = "1"; // Enabled
	public static final String FS_CNAT_Disabled = "2"; // Disabled

	// ADServerBackupPreemption
	public static final String FS_BP_Enabled = "1"; // Enabled
	public static final String FS_BP_Disabled = "2"; // Disabled

	/**
	 * L"4"Policy
	 */

	// l"4"Protocol
	public static final String L4_PTL_TCP = "1"; // TCP
	public static final String L4_PTL_UDP = "2"; // UDP
	public static final String L4_PTL_ICMP = "3"; // ICMP
	public static final String L4_PTL_SCTP = "4"; // SCTP
	public static final String L4_PTL_Any = "5"; // Any
	public static final String L4_PTL_Other = "6"; // Other

	// application
	public static final String L4_APP_Any = "1"; // Any
	public static final String L4_APP_FTPControl = "2"; // FTP Control
	public static final String L4_APP_HTTP = "3"; // HTTP
	public static final String L4_APP_HTTPS = "4"; // HTTPS
	public static final String L4_APP_PING = "5"; // PING
	public static final String L4_APP_REXEC = "6"; // REXEC
	public static final String L4_APP_RSH = "7"; // RSH
	public static final String L4_APP_RTSP = "8"; // RTSP
	public static final String L4_APP_SIP = "9"; // SIP
	public static final String L4_APP_SIPS = "10";// SIPS
	public static final String L4_APP_TSCOOKIE = "11";// TS COOKIE
	public static final String L4_APP_RADIUS = "12";// RADIUS
	public static final String L4_APP_TCP = "13";// TCP
	public static final String L4_APP_TFTP = "14";// TFTP
	public static final String L4_APP_UDP = "15";// UDP
	public static final String L4_APP_VirtualIPInterface = "16";// Virtual IP
																// Stringerface
	public static final String L4_APP_SCTP = "17";// SCTP
	public static final String L4_APP_MHSCTP = "18";// MH-SCTP
	public static final String L4_APP_GenericSSL = "19";// Generic-SSL

	// redundancyStatus
	public static final String L4_RS_Primary = "1"; // Primary
	public static final String L4_RS_Backup = "2"; // Backup

	// POSTClassificationInput
	public static final String L4_PS_Header = "1"; // Header
	public static final String L4_PS_Body = "2"; // Body
	public static final String L4_PS_HeaderandBody = "3"; // Header and Body

	// HTTPNormalization
	public static final String L4_HN_Enabled = "1"; // Enabled
	public static final String L4_HN_Disabled = "2"; // Disabled

	// actionType
	public static final String L4_AT_Farm = "1"; // Farm
	public static final String L4_AT_L7Policy = "2"; // L7 Policy
	public static final String L4_AT_URI = "3"; // URI
	public static final String L4_AT_IP = "4"; // IP
	public static final String L4_AT_ReplyCode = "5"; // Reply Code
	public static final String L4_AT_None = "6"; // None

	//
}
