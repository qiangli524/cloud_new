package com.sitech.console.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.basd.sxcloud.cloud.domain.resource.TbCloud2HostInfoObj;
import com.sitech.basd.sxcloud.cloud.service.resource.HostInfoService;
import com.sitech.console.dao.VncPortDao;
import com.sitech.console.domain.TbVncPortVO;

/**
 * 
 * <p>
 * Title: VncPortServiceImpl
 * </p>
 * <p>
 * Description: VNC端口配置逻辑实现类
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
 * @createtime 2014-7-31 上午9:34:01
 * 
 */
@Service("vncPortService")
public class VncPortServiceImpl implements VncPortService {
	@Autowired
	private VncPortDao vncPortDao;
	@Autowired
	private HostInfoService hostInfoService;

	/**
	 * 
	 * @Title: queryVncPortList
	 * @Description: 查询列表
	 * @param
	 * @return List<TbVncPortVO>
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-7-31 上午10:01:24
	 */
	public List<TbVncPortVO> queryVncPortList(TbVncPortVO vo) throws SQLException {
		return vncPortDao.queryForListByObj(vo);
	}

	/**
	 * 
	 * @Title: updateVncPort
	 * @Description: 更新数据
	 * @param
	 * @return void
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-7-31 上午10:01:32
	 */
	public void updateVncPort(TbVncPortVO vo) throws SQLException {
		vncPortDao.updateByObj(vo);
	}

	/**
	 * 
	 * @Title: saveVncPort
	 * @Description: 保存vnc端口
	 * @param
	 * @return void
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-7-31 下午1:31:47
	 */
	@Override
	public void saveVncPort(TbVncPortVO vo) throws SQLException {
		String hostId = vo.getHost_code();
		TbCloud2HostInfoObj obj = new TbCloud2HostInfoObj();
		obj.setEq_id(hostId);
		obj = hostInfoService.queryByObj(obj);
		String startPort = vo.getStartport();
		String endport = vo.getEndport();
		for (int i = Integer.parseInt(startPort); i <= Integer.parseInt(endport); i++) {
			TbVncPortVO portVO = new TbVncPortVO();
			portVO.setConnect_id(obj.getConnectId());
			portVO.setHost_code(obj.getH_uuid());
			portVO.setHost_ip(obj.getEq_ip());
			portVO.setPort(i + "");
			portVO.setIfused("0");
			portVO.setUpdatetime(new Date());
			vncPortDao.insertByObj(portVO);
		}
	}

	/**
	 * 
	 * @Title: initPortList
	 * @Description: 实例端口列表
	 * @param
	 * @return List<String>
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2014-7-31 下午1:37:56
	 */
	public List<String> initPortList() {
		List<String> list = new ArrayList<String>();
		for (int i = 5900; i < 5999; i++) {
			list.add(i + "");
		}
		return list;
	}

	@Override
	public void deleteVncPort(TbVncPortVO vo) throws SQLException {
		vncPortDao.deleteByObj(vo);
	}
}
