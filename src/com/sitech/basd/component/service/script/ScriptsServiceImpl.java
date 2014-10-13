package com.sitech.basd.component.service.script;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.ethz.ssh2.Connection;

import com.sitech.basd.component.dao.script.ScriptsDao;
import com.sitech.basd.component.domain.script.ScriptGroupObj;
import com.sitech.basd.component.domain.script.ScriptObj;
import com.sitech.basd.component.domain.script.ScriptRelationObj;
import com.sitech.basd.yicloud.dao.busisystree.TbBusiSysTreeDao;
import com.sitech.basd.yicloud.domain.busisystree.TbBusiSysTree;
import com.sitech.utils.idformat.DeployIdFormat;
import com.sitech.utils.ssh.ganymed.GanymedSshEncodeUtil;
import com.sitech.utils.ssh.ganymed.GanymedSshUtil;

@Service("scriptsService")
public class ScriptsServiceImpl implements ScriptsService {
	/** 打印日志 **/
	private static final Logger logger = Logger.getLogger(ScriptsServiceImpl.class);
	@Autowired
	private ScriptsDao scriptDao;
	@Autowired
	private TbBusiSysTreeDao tbBusiSysTreeDao;

	/**
	 * 
	 * @Title: queryForList
	 * @Description:查询列表
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jun 8, 2013 10:48:35 AM
	 */
	@Override
	public List queryForList(ScriptObj obj) {
		return scriptDao.queryForList(obj);
	}

	/**
	 * 
	 * @Title: ConfigGroupList
	 * @Description: 查询配置文件组列表
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime May 22, 2013 10:08:12 AM
	 */
	@Override
	public List queryScriptGroupList(ScriptGroupObj obj) {
		return scriptDao.queryScriptGroupList(obj);
	}

	/**
	 * 
	 * @Title: insertByObj
	 * @Description: 增加一条记录
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jun 8, 2013 10:49:18 AM
	 */
	@Override
	public int insertByObj(ScriptObj obj) {
		return scriptDao.insertByObj(obj);
	}

	/**
	 * 
	 * @Title: insertByObj
	 * @Description: 增加一条组记录
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jun 8, 2013 10:49:18 AM
	 */
	@Override
	public int insertGroup(ScriptGroupObj obj) {
		return scriptDao.insertGroup(obj);
	}

	/**
	 * 
	 * @Title:
	 * @Description: 修改一条记录
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jun 8, 2013 10:49:18 AM
	 */
	@Override
	public int updateByObj(ScriptObj obj) {
		return scriptDao.updateByObj(obj);
	}

	/**
	 * 
	 * @Title:
	 * @Description: 修改一条组记录
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jun 8, 2013 10:49:18 AM
	 */
	@Override
	public int updateGroup(ScriptGroupObj obj) {
		return scriptDao.updateGroup(obj);
	}

	/**
	 * 
	 * @Title:
	 * @Description: 删除一条记录
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jun 8, 2013 10:49:18 AM
	 */
	@Override
	public int deleteByObj(ScriptObj obj) {
		return scriptDao.deleteByObj(obj);
	}

	/**
	 * 
	 * @Title:deleteRelation
	 * @Description: 删除一条记录
	 * @param
	 * @return int
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime Aug 29, 2013 10:49:18 AM
	 */
	@Override
	public int deleteRelation(ScriptObj obj) {
		return scriptDao.deleteRelation(obj);
	}

	/**
	 * 
	 * @Title:
	 * @Description: 删除一条记录
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jun 8, 2013 10:49:18 AM
	 */
	@Override
	public int deleteGroup(ScriptGroupObj obj) {
		return scriptDao.deleteGroup(obj);
	}

	/**
	 * 
	 * @Title: querySelectedList
	 * @Description: 查询已选的配置文件列表
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime May 23, 2013 6:28:06 PM
	 */
	@Override
	public List querySelectedList(ScriptRelationObj obj) {
		return scriptDao.querySelectedList(obj);
	}

	/**
	 * 
	 * @Title: queryRemainList
	 * @Description: 查询备选成员列表
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime May 24, 2013 9:16:08 AM
	 */
	@Override
	public List queryRemainList(ScriptRelationObj obj) {
		return scriptDao.queryRemainList(obj);
	}

	/**
	 * 
	 * @Title: deleteGroupMember
	 * @Description: 删除组的所有成员
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime May 24, 2013 11:29:14 AM
	 */
	@Override
	public int deleteGroupMember(ScriptRelationObj obj) {
		return scriptDao.deleteGroupMember(obj);
	}

	/**
	 * 
	 * @Title: deleteGroupMember
	 * @Description: 增加组的成员
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime May 24, 2013 11:29:14 AM
	 */
	@Override
	public int insertGroupMember(ScriptRelationObj obj) {
		return scriptDao.insertGroupMember(obj);
	}

	/**
	 * 
	 * @Title: queryForDeployList
	 * @Description: 查询部署实例对应的配置文件
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime May 28, 2013 4:46:38 PM
	 */
	@Override
	public List queryForDeployList(ScriptObj obj) {
		return scriptDao.queryForDeployList(obj);
	}

	/**
	 * 
	 * @Title: getSysAppChildIdStr
	 * @Description: T部署实例ID转码后拼接，已,分割，用于查询业务中心树中基准应用上脚本列表
	 * @param
	 * @return String
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-7-16 上午11:23:22
	 */
	@Override
	public String getSysAppChildIdStr(String sysAppId) {
		// 基准应用ID
		TbBusiSysTree tbBusiSysTree = new TbBusiSysTree();
		tbBusiSysTree.setParentId(sysAppId);
		List<TbBusiSysTree> list = tbBusiSysTreeDao.queryForTree(tbBusiSysTree);
		String exampleEncode = "";
		if (list != null && list.size() != 0) {
			for (TbBusiSysTree exampleNode : list) {
				exampleEncode += "'"
						+ DeployIdFormat.generateAppMapKey(DeployIdFormat.DEPLOY_EXAMPLE,
								Integer.parseInt(exampleNode.getBusiId())) + "'" + ",";
			}
		}
		int index = exampleEncode.lastIndexOf(",");
		if (index != -1) {
			exampleEncode = exampleEncode.substring(0, exampleEncode.lastIndexOf(","));
		} else {
			exampleEncode = "''";
		}
		return exampleEncode;
	}

	/**
	 * 
	 * @Title: queryForAppScript
	 * @Description: 查询基准应用下部署实例对应的所有的脚本
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 9, 2013 9:49:15 AM
	 */
	@Override
	public List queryForAppScript(ScriptObj obj) {
		// 基准应用ID
		String sysAppId = obj.getExample_id();
		String sysAppChildIdStr = getSysAppChildIdStr(sysAppId);
		obj.setEncodeExampleStr(sysAppChildIdStr);
		return scriptDao.queryForAppScript(obj);
	}

	/**
	 * 
	 * @Title: queryLessGradeScript
	 * @Description: 查询实例对应的小于当前级别的所有脚本
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 11, 2013 4:15:41 PM
	 */
	@Override
	public List queryLessGradeScript(ScriptObj obj) {
		return scriptDao.queryLessGradeScript(obj);
	}

	/**
	 * 
	 * @Title: executeScript
	 * @Description: 执行脚本
	 * @param
	 * @return String
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime 2013-8-28 上午10:23:22
	 */
	@Override
	public String executeScript(ScriptObj obj) {
		List<String> msg = new ArrayList<String>();
		// List<String> msg1 = new ArrayList<String>();
		StringBuffer buf = new StringBuffer();
		String shellCmd = "";
		Connection con = null;
		try {
			// // ssh远程执行脚本
			// SshConnection con = new SshConnection(obj.getIp(), 22,
			// obj.getUsername(),
			// obj.getPassword());
			// SshResourceFactory ssh = SshResourceFactory.getInstance();
			// if ("0".equals(obj.getApp_type())) {
			// logger.info("*******开始调度主机[" + obj.getIp() + "]脚本" + "*******");
			// }
			shellCmd = "sh" + " " + obj.getPath();
			// msg = ssh.executeCommand(con, shellCmd);
			// // 处理执行结果
			// for (String s : msg) {
			// System.out.println(s);
			// buf.append(s + "\r\n");
			// }
			// System.out.println(buf);
			if ("0".equals(obj.getApp_type())) {
				logger.info("开始调度主机[" + obj.getIp() + "]脚本\r\n" + "*******执行开始：*******");
			}
			con = GanymedSshEncodeUtil.getConnection("172.21.2.76", "ocsapp", "ocsapp", 22);
			List<String> executeShellCmdReturnLists = new ArrayList<String>();
			executeShellCmdReturnLists = GanymedSshEncodeUtil.executeShellCmdReturnLists(con,
					shellCmd, "UTF-8");
			for (String s : executeShellCmdReturnLists) {
				System.out.println(s);
				buf.append(s + "\r\n");
			}
			// 如果是Boss云化类型脚本，则将运行结果写入日志
			if ("0".equals(obj.getApp_type())) {
				logger.info(buf + "*******执行结束！*******" + "\r\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				GanymedSshUtil.closeConnection(con);
			}
		}
		return buf.toString();
	}

	/**
	 * @Title: queryByObj
	 * @Description: 查询一条记录
	 * @param
	 * @return ScriptObj
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @param obj
	 * @createtime 2013-12-31 下午5:27:13
	 */
	@Override
	public ScriptObj queryByObj(ScriptObj obj) {
		return scriptDao.queryByObj(obj);
	}
}
