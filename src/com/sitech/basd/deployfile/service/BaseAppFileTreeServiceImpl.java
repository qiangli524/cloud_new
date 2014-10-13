package com.sitech.basd.deployfile.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.ethz.ssh2.Connection;

import com.sitech.basd.deployfile.dao.TbBaseAppFileTreeDao;
import com.sitech.basd.deployfile.domain.BaseAppFileTreeVO;
import com.sitech.utils.capture.vo.StandardAppVO;
import com.sitech.utils.exception.RabbitMQException;
import com.sitech.utils.rabbitmq.RabbitMQUtil;
import com.sitech.utils.ssh.ganymed.AppGanymedSshUtil;

import deploy.AppFileTreeObj;

/**
 * 
 * <p>
 * Title: BaseAppFileTreeService
 * </p>
 * <p>
 * Description: 基准应用文件树
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author huojla
 * @version 1.0
 * @createtime 2014-8-25 上午11:15:01
 * 
 */
@Service("baseAppFileTreeService")
public class BaseAppFileTreeServiceImpl implements BaseAppFileTreeService {
	private final static Logger LOG = Logger.getLogger(BaseAppFileTreeService.class);
	private final static String FILE_TYPE = "file";
	private final static String DIRECTORY_TYPE = "directory";
	@Autowired
	private TbBaseAppFileTreeDao tbBaseAppFileTreeDao;
	@Autowired
	private RabbitMQUtil rabbitMQUtil;

	/*
	 * <p>Title: initTreelist</p> <p>Description: </p>
	 * 
	 * @param resultList
	 * 
	 * @return
	 * 
	 * @throws SQLException
	 * 
	 * @see
	 * com.sitech.basd.deployfile.service.BaseAppFileTreeService#initTreelist
	 * (java.util.List)
	 */
	@Override
	public List<BaseAppFileTreeVO> initTreelist(List<BaseAppFileTreeVO> resultList)
			throws SQLException {
		List<BaseAppFileTreeVO> list = new ArrayList<BaseAppFileTreeVO>();
		if (resultList != null && resultList.size() > 0) {
			/*
			 * 查询基准应用信息
			 */
			StandardAppVO baseAppVO = new StandardAppVO();
			baseAppVO.setStandardAppId(Integer.parseInt(resultList.get(0).getBaseappId()));
			baseAppVO = tbBaseAppFileTreeDao.queryBaseAppInfo(baseAppVO);
			String baseAppPath = baseAppVO.getBasePath();
			// 处理树形结构
			BaseAppFileTreeVO tempObj = new BaseAppFileTreeVO();
			for (BaseAppFileTreeVO obj : resultList) {
				BaseAppFileTreeVO tObj = new BaseAppFileTreeVO();
				tObj.setId(obj.getId());
				tObj.setName(obj.getName());
				tObj.setParent_id(obj.getParent_id());
				tObj.setBaseappId(obj.getBaseappId());
				tObj.setAlias(obj.getAlias());
				tObj.setStatus(obj.getStatus());

				String fileUrl = obj.getFile_url();
				if (fileUrl.equals(baseAppPath)) {
					tObj.setFile_url(obj.getFile_url());
					tObj.setNocheck(true);
				} else if (fileUrl.contains(baseAppPath)) {
					tObj.setFile_url(obj.getFile_url().substring(baseAppPath.length() + 1));
				} else {// 非应用文件
					tObj.setFile_url(obj.getFile_url());
					tObj.setNocheck(true);
				}

				tempObj.setParent_id(obj.getId());
				// 判断是不是父节点
				List<BaseAppFileTreeVO> lst = queryForTree(tempObj);
				if (lst == null || lst.size() == 0) {
					tObj.setIsParent(false);
				}
				// 设置图标
				list.add(tObj);
			}
		}
		return list;
	}

	/*
	 * <p>Title: updateForTree</p> <p>Description: </p>
	 * 
	 * @param obj
	 * 
	 * @return
	 * 
	 * @throws SQLException
	 * 
	 * @see
	 * com.sitech.basd.deployfile.service.BaseAppFileTreeService#updateForTree
	 * (com.sitech.basd.deployfile.domain.BaseAppFileTreeVO)
	 */
	@Override
	public int updateForTree(BaseAppFileTreeVO obj) throws SQLException {
		return tbBaseAppFileTreeDao.updateByObj(obj);
	}

	/*
	 * <p>Title: queryForTree</p> <p>Description: </p>
	 * 
	 * @param obj
	 * 
	 * @return
	 * 
	 * @throws SQLException
	 * 
	 * @see
	 * com.sitech.basd.deployfile.service.BaseAppFileTreeService#queryForTree
	 * (com.sitech.basd.deployfile.domain.BaseAppFileTreeVO)
	 */
	@Override
	public List<BaseAppFileTreeVO> queryForTree(BaseAppFileTreeVO obj) throws SQLException {
		return tbBaseAppFileTreeDao.queryForTree(obj);
	}

	/*
	 * <p>Title: initBaseAppFileTree</p> <p>Description: </p>
	 * 
	 * @param host
	 * 
	 * @param port
	 * 
	 * @param username
	 * 
	 * @param password
	 * 
	 * @param path
	 * 
	 * @param baseappid
	 * 
	 * @throws SQLException
	 * 
	 * @see
	 * com.sitech.basd.deployfile.service.BaseAppFileTreeService#initBaseAppFileTree
	 * (java.lang.String, java.lang.Integer, java.lang.String, java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public void initBaseAppFileTree(String host, Integer port, String username, String password,
			String path, String baseappid) throws SQLException {
		LOG.debug("开始分析基准应用路径文件树!" + username + "@" + host + ":" + path);
		Map<String, String> filepathMap = analysisBaseAppFilePath(host, port, username, password,
				path);

		LOG.debug("分析基准应用路径文件树Map完成!" + username + "@" + host + ":" + path);

		Set<String> set = filepathMap.keySet();
		for (String file : set) {
			if ("/".equals(file)) {// 根节点
				BaseAppFileTreeVO obj = structureTreeObj(file, file, "root", baseappid,
						filepathMap.get(file));
				tbBaseAppFileTreeDao.insertTree(obj);
			} else if (file.lastIndexOf("/") != 0) {// 二级以上目录
				String fullPath = file;
				String parentPath = file.substring(0, file.lastIndexOf("/"));
				String filename = file.substring(file.lastIndexOf("/") + 1);
				// 保存树节点
				saveTreeObj(filename, fullPath, parentPath, baseappid, filepathMap.get(file));
			} else {// 一级目录
				String fullPath = file;
				String parentPath = "/";
				String filename = file.substring(1);

				// 保存树节点
				saveTreeObj(filename, fullPath, parentPath, baseappid, filepathMap.get(file));// 类型（1
																								// 文件，2
																								// 目录）
			}
		}

		LOG.debug("分析基准应用路径文件树完成，入库结束!" + username + "@" + host + ":" + path);
	}

	/**
	 * 
	 * @Title: structureTreeObj
	 * @Description: 构造树形Obj
	 * @param
	 * @return BaseAppFileTreeVO
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-7-17 上午10:21:37
	 */
	private BaseAppFileTreeVO structureTreeObj(String name, String file_url, String parent_id,
			String baseappId, String itype) {
		BaseAppFileTreeVO obj = new BaseAppFileTreeVO();
		obj.setBaseappId(baseappId);
		obj.setFile_url(file_url);
		obj.setName(name);
		obj.setParent_id(parent_id);
		obj.setStatus("1");// 默认是正常状态
		obj.setItype(itype); // 类型（1 文件，2 目录）
		return obj;
	}

	/**
	 * 
	 * @Title: saveTreeObj
	 * @Description: 保存树节点
	 * @param
	 * @return void
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-7-17 上午10:34:44
	 */
	private void saveTreeObj(String filename, String fullPath, String parentPath, String baseappId,
			String itype) throws SQLException {
		BaseAppFileTreeVO parentObj = new BaseAppFileTreeVO();
		parentObj.setBaseappId(baseappId);
		parentObj.setFile_url(parentPath);
		parentObj = tbBaseAppFileTreeDao.queryTreeObj(parentObj);

		BaseAppFileTreeVO obj = structureTreeObj(filename, fullPath, parentObj.getId(), baseappId,
				itype);
		tbBaseAppFileTreeDao.insertTree(obj);
	}

	/**
	 * 
	 * @Title: analysisBaseAppFilePath
	 * @Description: 根据基准应用路径分析文件树形结构Map
	 * @param
	 * @return Map<String,String>
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-7-17 上午10:13:22
	 */
	private Map<String, String> analysisBaseAppFilePath(String host, Integer port, String username,
			String password, String path) {
		Map<String, String> map = new LinkedHashMap<String, String>();
		Connection conn = AppGanymedSshUtil.getConnection(host, username, password, port);

		List<String> files = AppGanymedSshUtil.executeShellCmdReturnLists(conn, "du -a " + path
				+ " | awk '{print $2}'"); // 取所有文件
		List<String> folders = AppGanymedSshUtil.executeShellCmdReturnLists(conn, "du -h " + path
				+ " | awk '{print $2}'"); // 取所有目录
		files.removeAll(folders);// 去除目录，仅剩文件
		for (String file : files) {
			if (file.indexOf("/") != -1) {
				String[] filepaths = file.split("/");
				for (int i = 0; i < filepaths.length; i++) {
					String r1 = filepaths[i];
					if (!"".equals(r1)) {
						if (i > 1 && i != (filepaths.length - 1)) {
							r1 = "/" + r1 + "/";
						} else if (i == (filepaths.length - 1)) {
							r1 = "/" + r1;
						} else {

						}
						String filepath = file.substring(0,
								file.indexOf(r1, getStringStartIndex(filepaths, i)));
						map.put(filepath, DIRECTORY_TYPE); // 类型（1 文件，2 目录）
						// if (filepath.lastIndexOf("/") != 0) {
						// String value = filepath.substring(0,
						// filepath.lastIndexOf("/"));
						// map.put(value, DIRECTORY_TYPE); // 类型（1 文件，2 目录）
						// } else {
						// map.put(filepath, DIRECTORY_TYPE); // 类型（1 文件，2 目录）
						// }
					}
				}
			}
			map.put(file, FILE_TYPE); // 类型（1 文件，2 目录）
		}
		AppGanymedSshUtil.closeConnection(conn);
		return map;
	}

	/**
	 * 
	 * @Title: getStringStartIndex
	 * @Description: 实例开始截取index
	 * @param
	 * @return int
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-8-25 下午3:06:41
	 */
	private int getStringStartIndex(String[] filepaths, int i) {
		int result = 0;
		for (int j = 0; j < i; j++) {
			result += filepaths[j].length();
		}
		return result;
	}

	/**
	 * 
	 * @Title: CreateFileTreeByAppid
	 * @Description: 为基准应用生成文件树
	 * @param
	 * @return String
	 * @throws
	 * @author liuqi
	 * @version 1.0
	 * @createtime 2014-9-11 下午4:46:36
	 */
	public String CreateFileTreeByAppid(AppFileTreeObj obj) {
		String result = "1";
		try {
			rabbitMQUtil.publishMessage("", "CreateFileTreeByAppid.queue", obj);

		} catch (RabbitMQException e) {
			// TODO Auto-generated catch block
			result = "0";
			e.printStackTrace();

		}
		return result;

	}
}
