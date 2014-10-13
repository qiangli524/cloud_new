package com.sitech.ssd.sc.ostempl.service;

import java.util.List;

import com.sitech.ssd.sc.ostempl.domain.OsTemplateFileSystem;
import com.sitech.ssd.sc.ostempl.domain.OsTemplateGroup;
import com.sitech.ssd.sc.ostempl.domain.OsTemplateGroupUser;
import com.sitech.ssd.sc.ostempl.domain.OsTemplatePart;
import com.sitech.ssd.sc.ostempl.domain.OsTemplateSoft;
import com.sitech.ssd.sc.ostempl.domain.OsTemplateUser;
import com.sitech.ssd.sc.ostempl.domain.OsTemplateVolGroup;

/**
 * 
 * @ClassName: OsTemplateComponetService
 * @Description: OS安装模版 组件Service类：文件系统、用户、组、用户与组
 * @author JamTau
 * @date 2014-8-20 下午5:41:16
 *
 */
public interface OsTemplateComponetService {
	
	/**---------------------------FileSystem Begin-----------------------------*/
	/**
	 * 
	 * @Title: saveOsTemplateFileSystem
	 * @Description: save OsTemplateFileSystem
	 * @param OsTemplateFileSystem 输入参数
	 * @return int 返回类型
	 * @throws
	 */
	public int saveOsTemplateFileSystem(OsTemplateFileSystem obj);
	
	/**
	 * 
	 * @Title: copyOsTemplateFileSystem
	 * @Description: 拷贝模版 
	 * @param  OsTemplateFileSystem 输入参数
	 * @return int 返回类型
	 * @throws
	 */
	public int copyOsTemplateFileSystem(OsTemplateFileSystem obj);

	/**
	 * 
	 * @Title: deleteOsTemplateFileSystem
	 * @Description: deleteOsTemplateFileSystem
	 * @param OsTemplateFileSystem 输入参数
	 * @return int 返回类型
	 * @throws
	 */
	public int deleteOsTemplateFileSystem(OsTemplateFileSystem obj);

	/**
	 * 
	 * @Title: modifyOsTemplateFileSystem
	 * @Description: modifyOsTemplateFileSystem
	 * @param OsTemplateFileSystem 输入参数
	 * @return int 返回类型
	 * @throws
	 */
	public int modifyOsTemplateFileSystem(OsTemplateFileSystem obj);

	/**
	 * 
	 * @Title: queryOsTemplateFileSystem
	 * @Description: queryOsTemplateFileSystem
	 * @param OsTemplateFileSystem 输入参数
	 * @return OsTemplateFileSystem 返回类型
	 * @throws
	 */
	public OsTemplateFileSystem queryOsTemplateFileSystem(OsTemplateFileSystem obj);

	/**
	 * 
	 * @Title: queryOsTemplateFileSystemList
	 * @Description: queryOsTemplateFileSystemList
	 * @param OsTemplateFileSystem 输入参数
	 * @return List<OsTemplateFileSystem> 返回类型
	 * @throws
	 */
	public List<OsTemplateFileSystem> queryOsTemplateFileSystemList(OsTemplateFileSystem obj);
	/**=========================== FileSystem End =============================*/
	
	/**---------------------------Group Begin-----------------------------*/
	/**
	 * 
	 * @Title: saveOsTemplateGroup
	 * @Description: save OsTemplateGroup
	 * @param OsTemplateGroup 输入参数
	 * @return int 返回类型
	 * @throws
	 */
	public int saveOsTemplateGroup(OsTemplateGroup obj);
	
	/**
	 * 
	 * @Title: copyOsTemplateGroup
	 * @Description: copyOsTemplateGroup
	 * @param  OsTemplateGroup 输入参数
	 * @return int 返回类型
	 * @throws
	 */
	public int copyOsTemplateGroup(OsTemplateGroup obj);
	/**
	 * 
	 * @Title: deleteOsTemplateGroup
	 * @Description: deleteOsTemplateGroup
	 * @param OsTemplateGroup 输入参数
	 * @return int 返回类型
	 * @throws
	 */
	public int deleteOsTemplateGroup(OsTemplateGroup obj);
	
	/**
	 * 
	 * @Title: modifyOsTemplateGroup
	 * @Description: modifyOsTemplateGroup
	 * @param OsTemplateGroup 输入参数
	 * @return int 返回类型
	 * @throws
	 */
	public int modifyOsTemplateGroup(OsTemplateGroup obj);
	
	/**
	 * 
	 * @Title: queryOsTemplateGroup
	 * @Description: queryOsTemplateGroup
	 * @param OsTemplateGroup 输入参数
	 * @return OsTemplateGroup 返回类型
	 * @throws
	 */
	public OsTemplateGroup queryOsTemplateGroup(OsTemplateGroup obj);
	
	/**
	 * 
	 * @Title: queryOsTemplateGroupList
	 * @Description: queryOsTemplateGroupList
	 * @param OsTemplateGroup 输入参数
	 * @return List<OsTemplateGroup> 返回类型
	 * @throws
	 */
	public List<OsTemplateGroup> queryOsTemplateGroupList(OsTemplateGroup obj);	
	/**=========================== Group End =============================*/
	
	/**---------------------------User Begin-----------------------------*/
	/**
	 * 
	 * @Title: saveOsTemplateUser
	 * @Description: save OsTemplateUser
	 * @param OsTemplateUser 输入参数
	 * @return int 返回类型
	 * @throws
	 */
	public int saveOsTemplateUser(OsTemplateUser obj);
	
	/**
	 * 
	 * @Title: saveOsTemplateUser
	 * @Description: saveOsTemplateUser
	 * @param  OsTemplateUser 输入参数
	 * @return int 返回类型
	 * @throws
	 */
	public int copyOsTemplateUser(OsTemplateUser obj);
	
	/**
	 * 
	 * @Title: deleteOsTemplateUser
	 * @Description: deleteOsTemplateUser
	 * @param OsTemplateUser 输入参数
	 * @return int 返回类型
	 * @throws
	 */
	public int deleteOsTemplateUser(OsTemplateUser obj);
	
	/**
	 * 
	 * @Title: modifyOsTemplateUser
	 * @Description: modifyOsTemplateUser
	 * @param OsTemplateUser 输入参数
	 * @return int 返回类型
	 * @throws
	 */
	public int modifyOsTemplateUser(OsTemplateUser obj);
	
	/**
	 * 
	 * @Title: queryOsTemplateUser
	 * @Description: queryOsTemplateUser
	 * @param OsTemplateUser 输入参数
	 * @return OsTemplateUser 返回类型
	 * @throws
	 */
	public OsTemplateUser queryOsTemplateUser(OsTemplateUser obj);
	
	/**
	 * 
	 * @Title: queryOsTemplateUserList
	 * @Description: queryOsTemplateUserList
	 * @param OsTemplateUser 输入参数
	 * @return List<OsTemplateUser> 返回类型
	 * @throws
	 */
	public List<OsTemplateUser> queryOsTemplateUserList(OsTemplateUser obj);	
	/**=========================== User End =============================*/
	
	/**---------------------------GroupUser Begin-----------------------------*/
	/**
	 * 
	 * @Title: saveOsTemplateGroupUser
	 * @Description: save OsTemplateGroupUser
	 * @param OsTemplateGroupUser 输入参数
	 * @return int 返回类型
	 * @throws
	 */
	public int saveOsTemplateGroupUser(OsTemplateGroupUser obj);
	
	/**
	 * 
	 * @Title: copyOsTemplateGroupUser
	 * @Description: copyOsTemplateGroupUser
	 * @param  OsTemplateGroupUser 输入参数
	 * @return int 返回类型
	 * @throws
	 */
	public int copyOsTemplateGroupUser(OsTemplateGroupUser obj);
	
	/**
	 * 
	 * @Title: deleteOsTemplateGroupUser
	 * @Description: deleteOsTemplateGroupUser
	 * @param OsTemplateGroupUser 输入参数
	 * @return int 返回类型
	 * @throws
	 */
	public int deleteOsTemplateGroupUser(OsTemplateGroupUser obj);
	
	/**
	 * 
	 * @Title: modifyOsTemplateGroupUser
	 * @Description: modifyOsTemplateGroupUser
	 * @param OsTemplateGroupUser 输入参数
	 * @return int 返回类型
	 * @throws
	 */
	public int modifyOsTemplateGroupUser(OsTemplateGroupUser obj);
	
	/**
	 * 
	 * @Title: queryOsTemplateGroupUser
	 * @Description: queryOsTemplateGroupUser
	 * @param OsTemplateGroupUser 输入参数
	 * @return OsTemplateGroupUser 返回类型
	 * @throws
	 */
	public OsTemplateGroupUser queryOsTemplateGroupUser(OsTemplateGroupUser obj);
	
	/**
	 * 
	 * @Title: queryOsTemplateGroupUserList
	 * @Description: queryOsTemplateGroupUserList
	 * @param OsTemplateGroupUser 输入参数
	 * @return List<OsTemplateGroupUser> 返回类型
	 * @throws
	 */
	public List<OsTemplateGroupUser> queryOsTemplateGroupUserList(OsTemplateGroupUser obj);
	/**=========================== GroupUser End =============================*/
	
	/**---------------------------Soft Begin-----------------------------*/
	/**
	 * 
	 * @Title: saveOsTemplateSoft
	 * @Description: save OsTemplateSoft
	 * @param OsTemplateSoft 输入参数
	 * @return int 返回类型
	 * @throws
	 */
	public int saveOsTemplateSoft(OsTemplateSoft obj);
	
	/**
	 * 
	 * @Title: copyOsTemplateSoft
	 * @Description: copyOsTemplateSoft
	 * @param  OsTemplateSoft 输入参数
	 * @return int 返回类型
	 * @throws
	 */
	public int copyOsTemplateSoft(OsTemplateSoft obj);
	
	/**
	 * 
	 * @Title: deleteOsTemplateSoft
	 * @Description: deleteOsTemplateSoft
	 * @param OsTemplateSoft 输入参数
	 * @return int 返回类型
	 * @throws
	 */
	public int deleteOsTemplateSoft(OsTemplateSoft obj);
	
	/**
	 * 
	 * @Title: modifyOsTemplateSoft
	 * @Description: modifyOsTemplateSoft
	 * @param OsTemplateSoft 输入参数
	 * @return int 返回类型
	 * @throws
	 */
	public int modifyOsTemplateSoft(OsTemplateSoft obj);
	
	/**
	 * 
	 * @Title: queryOsTemplateSoft
	 * @Description: queryOsTemplateSoft
	 * @param OsTemplateSoft 输入参数
	 * @return OsTemplateSoft 返回类型
	 * @throws
	 */
	public OsTemplateSoft queryOsTemplateSoft(OsTemplateSoft obj);
	
	/**
	 * 
	 * @Title: queryOsTemplateSoftList
	 * @Description: queryOsTemplateSoftList
	 * @param OsTemplateSoft 输入参数
	 * @return List<OsTemplateSoft> 返回类型
	 * @throws
	 */
	public List<OsTemplateSoft> queryOsTemplateSoftList(OsTemplateSoft obj);
	/**=========================== Soft End =============================*/
	
	/**---------------------------Part Begin-----------------------------*/
	/**
	 * 
	 * @Title: saveOsTemplatePart
	 * @Description: save OsTemplatePart
	 * @param OsTemplatePart 输入参数
	 * @return int 返回类型
	 * @throws
	 */
	public int saveOsTemplatePart(OsTemplatePart obj);
	
	/**
	 * 
	 * @Title: copyOsTemplatePart
	 * @Description: copyOsTemplatePart
	 * @param  OsTemplatePart 输入参数
	 * @return int 返回类型
	 * @throws
	 */
	public int copyOsTemplatePart(OsTemplatePart obj);
	
	/**
	 * 
	 * @Title: deleteOsTemplatePart
	 * @Description: deleteOsTemplatePart
	 * @param OsTemplatePart 输入参数
	 * @return int 返回类型
	 * @throws
	 */
	public int deleteOsTemplatePart(OsTemplatePart obj);
	
	/**
	 * 
	 * @Title: modifyOsTemplatePart
	 * @Description: modifyOsTemplatePart
	 * @param OsTemplatePart 输入参数
	 * @return int 返回类型
	 * @throws
	 */
	public int modifyOsTemplatePart(OsTemplatePart obj);
	
	/**
	 * 
	 * @Title: queryOsTemplatePart
	 * @Description: queryOsTemplatePart
	 * @param OsTemplatePart 输入参数
	 * @return OsTemplatePart 返回类型
	 * @throws
	 */
	public OsTemplatePart queryOsTemplatePart(OsTemplatePart obj);
	
	/**
	 * 
	 * @Title: queryOsTemplatePartList
	 * @Description: queryOsTemplatePartList
	 * @param OsTemplatePart 输入参数
	 * @return List<OsTemplatePart> 返回类型
	 * @throws
	 */
	public List<OsTemplatePart> queryOsTemplatePartList(OsTemplatePart obj);
	/**=========================== Part End =============================*/
	
	/**---------------------------VolGroup Begin-----------------------------*/
	/**
	 * 
	 * @Title: saveOsTemplateVolGroup
	 * @Description: save OsTemplateVolGroup
	 * @param OsTemplateVolGroup 输入参数
	 * @return int 返回类型
	 * @throws
	 */
	public int saveOsTemplateVolGroup(OsTemplateVolGroup obj);
	
	/**
	 * 
	 * @Title: copyOsTemplateVolGroup
	 * @Description: copyOsTemplateVolGroup
	 * @param  OsTemplateVolGroup 输入参数
	 * @return int 返回类型
	 * @throws
	 */
	public int copyOsTemplateVolGroup(OsTemplateVolGroup obj);
	
	/**
	 * 
	 * @Title: deleteOsTemplateVolGroup
	 * @Description: deleteOsTemplateVolGroup
	 * @param OsTemplateVolGroup 输入参数
	 * @return int 返回类型
	 * @throws
	 */
	public int deleteOsTemplateVolGroup(OsTemplateVolGroup obj);
	
	/**
	 * 
	 * @Title: modifyOsTemplateVolGroup
	 * @Description: modifyOsTemplateVolGroup
	 * @param OsTemplateVolGroup 输入参数
	 * @return int 返回类型
	 * @throws
	 */
	public int modifyOsTemplateVolGroup(OsTemplateVolGroup obj);
	
	/**
	 * 
	 * @Title: queryOsTemplateVolGroup
	 * @Description: queryOsTemplateVolGroup
	 * @param OsTemplateVolGroup 输入参数
	 * @return OsTemplateVolGroup 返回类型
	 * @throws
	 */
	public OsTemplateVolGroup queryOsTemplateVolGroup(OsTemplateVolGroup obj);
	
	/**
	 * 
	 * @Title: queryOsTemplateVolGroupList
	 * @Description: queryOsTemplateVolGroupList
	 * @param OsTemplateVolGroup 输入参数
	 * @return List<OsTemplateVolGroup> 返回类型
	 * @throws
	 */
	public List<OsTemplateVolGroup> queryOsTemplateVolGroupList(OsTemplateVolGroup obj);
	/**=========================== VolGroup End =============================*/
}
