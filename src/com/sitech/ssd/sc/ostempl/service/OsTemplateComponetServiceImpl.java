package com.sitech.ssd.sc.ostempl.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sitech.ssd.sc.ostempl.dao.OsTemplateFileSystemDao;
import com.sitech.ssd.sc.ostempl.dao.OsTemplateGroupDao;
import com.sitech.ssd.sc.ostempl.dao.OsTemplateGroupUserDao;
import com.sitech.ssd.sc.ostempl.dao.OsTemplatePartDao;
import com.sitech.ssd.sc.ostempl.dao.OsTemplateSoftDao;
import com.sitech.ssd.sc.ostempl.dao.OsTemplateUserDao;
import com.sitech.ssd.sc.ostempl.dao.OsTemplateVolGroupDao;
import com.sitech.ssd.sc.ostempl.domain.OsTemplateFileSystem;
import com.sitech.ssd.sc.ostempl.domain.OsTemplateGroup;
import com.sitech.ssd.sc.ostempl.domain.OsTemplateGroupUser;
import com.sitech.ssd.sc.ostempl.domain.OsTemplatePart;
import com.sitech.ssd.sc.ostempl.domain.OsTemplateSoft;
import com.sitech.ssd.sc.ostempl.domain.OsTemplateUser;
import com.sitech.ssd.sc.ostempl.domain.OsTemplateVolGroup;


/**
 * 
 * @ClassName: OsTemplateComponetServiceImpl
 * @Description: OS安装模版组件 Service实现类
 * @author JamTau
 * @date 2014-8-20 下午6:01:06
 *
 */
@Service("osTemplateComponetService")
public class OsTemplateComponetServiceImpl implements OsTemplateComponetService {

	@Resource
	private OsTemplateFileSystemDao osTemplateFileSystemDao;
	@Resource
	private OsTemplateGroupDao osTemplateGroupDao;
	@Resource
	private OsTemplateGroupUserDao osTemplateGroupUserDao;
	@Resource
	private OsTemplateUserDao osTemplateUserDao;
	@Resource
	private OsTemplateSoftDao osTemplateSoftDao;
	@Resource
	private OsTemplatePartDao osTemplatePartDao;
	@Resource
	private OsTemplateVolGroupDao osTemplateVolGroupDao; 
	
	/**---------------------------FileSystem Begin-----------------------------*/
	@Override
	public int saveOsTemplateFileSystem(OsTemplateFileSystem obj) {
		return osTemplateFileSystemDao.insertOsTemplateFileSystem(obj);
	}
	@Override
	public int copyOsTemplateFileSystem(OsTemplateFileSystem obj) {
		return osTemplateFileSystemDao.copyOsTemplateFileSystem(obj);
	}
	@Override
	public int deleteOsTemplateFileSystem(OsTemplateFileSystem obj) {
		return osTemplateFileSystemDao.deleteOsTemplateFileSystem(obj);
	}
	@Override
	public int modifyOsTemplateFileSystem(OsTemplateFileSystem obj) {
		return osTemplateFileSystemDao.updateOsTemplateFileSystem(obj);
	}
	@Override
	public OsTemplateFileSystem queryOsTemplateFileSystem(
			OsTemplateFileSystem obj) {
		return osTemplateFileSystemDao.selectOsTemplateFileSystem(obj);
	}
	@Override
	public List<OsTemplateFileSystem> queryOsTemplateFileSystemList(
			OsTemplateFileSystem obj) {
		return osTemplateFileSystemDao.selectOsTemplateFileSystemList(obj);
	}
	/**=========================== FileSystem End =============================*/
	
	/**---------------------------Group Begin-----------------------------*/	
	@Override
	public int saveOsTemplateGroup(OsTemplateGroup obj) {
		return osTemplateGroupDao.insertOsTemplateGroup(obj);
	}
	@Override
	public int copyOsTemplateGroup(OsTemplateGroup obj) {
		return osTemplateGroupDao.copyOsTemplateGroup(obj);
	}
	@Override
	public int deleteOsTemplateGroup(OsTemplateGroup obj) {
		return osTemplateGroupDao.deleteOsTemplateGroup(obj);
	}
	@Override
	public int modifyOsTemplateGroup(OsTemplateGroup obj) {
		return osTemplateGroupDao.updateOsTemplateGroup(obj);
	}
	@Override
	public OsTemplateGroup queryOsTemplateGroup(OsTemplateGroup obj) {
		return osTemplateGroupDao.selectOsTemplateGroup(obj);
	}
	@Override
	public List<OsTemplateGroup> queryOsTemplateGroupList(OsTemplateGroup obj) {
		return osTemplateGroupDao.selectOsTemplateGroupList(obj);
	}
	/**=========================== Group End =============================*/
	
	/**---------------------------User Begin-----------------------------*/	
	@Override
	public int saveOsTemplateUser(OsTemplateUser obj) {
		return osTemplateUserDao.insertOsTemplateUser(obj);
	}
	@Override
	public int copyOsTemplateUser(OsTemplateUser obj) {
		return osTemplateUserDao.copyOsTemplateUser(obj);
	}
	@Override
	public int deleteOsTemplateUser(OsTemplateUser obj) {
		return osTemplateUserDao.deleteOsTemplateUser(obj);
	}
	@Override
	public int modifyOsTemplateUser(OsTemplateUser obj) {
		return osTemplateUserDao.updateOsTemplateUser(obj);
	}
	@Override
	public OsTemplateUser queryOsTemplateUser(OsTemplateUser obj) {
		return osTemplateUserDao.selectOsTemplateUser(obj);
	}
	@Override
	public List<OsTemplateUser> queryOsTemplateUserList(OsTemplateUser obj) {
		return osTemplateUserDao.selectOsTemplateUserList(obj);
	}
	/**=========================== User End =============================*/
	
	/**---------------------------GroupUser Begin-----------------------------*/
	@Override
	public int saveOsTemplateGroupUser(OsTemplateGroupUser obj) {
		return osTemplateGroupUserDao.insertOsTemplateGroupUser(obj);
	}
	@Override
	public int copyOsTemplateGroupUser(OsTemplateGroupUser obj) {
		return osTemplateGroupUserDao.copyOsTemplateGroupUser(obj);
	}
	@Override
	public int deleteOsTemplateGroupUser(OsTemplateGroupUser obj) {
		return osTemplateGroupUserDao.deleteOsTemplateGroupUser(obj);
	}
	@Override
	public int modifyOsTemplateGroupUser(OsTemplateGroupUser obj) {
		return osTemplateGroupUserDao.updateOsTemplateGroupUser(obj);
	}
	@Override
	public OsTemplateGroupUser queryOsTemplateGroupUser(OsTemplateGroupUser obj) {
		return osTemplateGroupUserDao.selectOsTemplateGroupUser(obj);
	}
	@Override
	public List<OsTemplateGroupUser> queryOsTemplateGroupUserList(
			OsTemplateGroupUser obj) {
		return osTemplateGroupUserDao.selectOsTemplateGroupUserList(obj);
	}
	/**=========================== GroupUser End =============================*/
	
	
	/**---------------------------Soft Begin-----------------------------*/
	@Override
	public int saveOsTemplateSoft(OsTemplateSoft obj) {
		return osTemplateSoftDao.insertOsTemplateSoft(obj);
	}
	@Override
	public int copyOsTemplateSoft(OsTemplateSoft obj) {
		return osTemplateSoftDao.copyOsTemplateSoft(obj);
	}
	@Override
	public int deleteOsTemplateSoft(OsTemplateSoft obj) {
		return osTemplateSoftDao.deleteOsTemplateSoft(obj);
	}
	@Override
	public int modifyOsTemplateSoft(OsTemplateSoft obj) {
		return osTemplateSoftDao.updateOsTemplateSoft(obj);
	}
	@Override
	public OsTemplateSoft queryOsTemplateSoft(OsTemplateSoft obj) {
		return osTemplateSoftDao.selectOsTemplateSoft(obj);
	}
	@Override
	public List<OsTemplateSoft> queryOsTemplateSoftList(OsTemplateSoft obj) {
		return osTemplateSoftDao.selectOsTemplateSoftList(obj);
	}
	/**=========================== Soft End =============================*/
	
	/**---------------------------Part Begin-----------------------------*/
	@Override
	public int saveOsTemplatePart(OsTemplatePart obj) {
		return osTemplatePartDao.insertOsTemplatePart(obj);
	}
	@Override
	public int copyOsTemplatePart(OsTemplatePart obj) {
		return osTemplatePartDao.copyOsTemplatePart(obj);
	}
	@Override
	public int deleteOsTemplatePart(OsTemplatePart obj) {
		return osTemplatePartDao.deleteOsTemplatePart(obj);
	}
	@Override
	public int modifyOsTemplatePart(OsTemplatePart obj) {
		return osTemplatePartDao.updateOsTemplatePart(obj);
	}
	@Override
	public OsTemplatePart queryOsTemplatePart(OsTemplatePart obj) {
		return osTemplatePartDao.selectOsTemplatePart(obj);
	}
	@Override
	public List<OsTemplatePart> queryOsTemplatePartList(OsTemplatePart obj) {
		return osTemplatePartDao.selectOsTemplatePartList(obj);
	}
	/**=========================== Part End =============================*/	
	
	/**---------------------------VolGroup Begin-----------------------------*/
	@Override
	public int saveOsTemplateVolGroup(OsTemplateVolGroup obj) {
		return osTemplateVolGroupDao.insertOsTemplateVolGroup(obj);
	}
	@Override
	public int copyOsTemplateVolGroup(OsTemplateVolGroup obj) {
		return osTemplateVolGroupDao.copyOsTemplateVolGroup(obj);
	}
	@Override
	public int deleteOsTemplateVolGroup(OsTemplateVolGroup obj) {
		return osTemplateVolGroupDao.deleteOsTemplateVolGroup(obj);
	}
	@Override
	public int modifyOsTemplateVolGroup(OsTemplateVolGroup obj) {
		return osTemplateVolGroupDao.updateOsTemplateVolGroup(obj);
	}
	@Override
	public OsTemplateVolGroup queryOsTemplateVolGroup(OsTemplateVolGroup obj) {
		return osTemplateVolGroupDao.selectOsTemplateVolGroup(obj);
	}
	@Override
	public List<OsTemplateVolGroup> queryOsTemplateVolGroupList(OsTemplateVolGroup obj) {
		return osTemplateVolGroupDao.selectOsTemplateVolGroupList(obj);
	}
	/**=========================== VolGroup End =============================*/		
}
