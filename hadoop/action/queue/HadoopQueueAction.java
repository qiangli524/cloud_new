package action.queue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.InputSource;

import service.queue.HadoopQueueRelationService;
import service.queue.HadoopQueueService;
import service.users.HadoopUserGroupService;
import service.users.HadoopUserService;

import com.sitech.basd.component.domain.config.ConfigInfoObj;
import com.sitech.basd.component.service.config.ConfigService;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.utils.dom4jxml.XmlFormatUtil;
import com.sitech.utils.encrypt.DoubleEncryptUtils;
import com.sitech.utils.randomid.RandomUUID;
import com.sitech.utils.servlet.PrintWriterOut;
import com.sitech.utils.ssh.ssh2.SshConnection;
import com.sitech.utils.ssh.ssh2.SshResourceFactory;

import domain.queue.HadoopQueueObj;
import domain.queue.HadoopQueueRelationObj;
import domain.users.HadoopUserGroup;
import domain.users.HadoopUserObj;

@SuppressWarnings("serial")
@Controller("hadoopQueueAction")
@Scope("prototype")
public class HadoopQueueAction extends BaseAction{

	@Autowired
	private HadoopQueueService hadoopQueueService;
	
	@Autowired
	private HadoopQueueRelationService hadoopQueueRelationService;
	
	@Autowired
	private HadoopUserService hadoopUserService;
	
	@Autowired
	private HadoopUserGroupService hadoopUserGroupService;
	
	@Autowired
	private ConfigService configService;
	
	private HadoopQueueObj hadoopQueueObj;
	
	private ConfigInfoObj configInfoObj;
	
	private HadoopQueueRelationObj hadoopQueueRelationObj;
	
	private List<HadoopQueueObj> resultList;//结果集
	
	private List<HadoopUserObj> userList;//用户列表
	
	private List<HadoopUserGroup> userGroupList;//用户组列表
	
	private List<ConfigInfoObj> configList;//配置文件列表
	
	private String oper;//操作
	
	private String idstr;//单个或多个id组成的字符串
	
	private String queue_name;//单个或多个队列名称
	
	private int tactics;//策略 1FIFO 2Container 3Fair
	
	private String flag;//show,hide
	
	/**
	 * @Title: add
	 * @Description: 进入添加页面
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-21 下午7:42:12
	 */
	public String add(){
		return "addHadoopQueue";
	}
	
	/**
	 * @Title: delete
	 * @Description: 删除
	 * @param
	 * @return void
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-21 下午7:47:12
	 */
	public void delete(){
		//删除操作，应该先连接远程机器修改队列的配置文件，修改成功后才删除数据库中的记录，否则返回失败
		if (idstr != null) {
			String[] idarr = idstr.split(",");
			List<String> idList = new ArrayList<String>();
			for (String string : idarr) {
				idList.add(string);
			}
			
			if (hadoopQueueObj == null) {
				hadoopQueueObj = new HadoopQueueObj();
			}
			hadoopQueueObj.setIdList(idList);
			List<HadoopQueueObj> queueList = hadoopQueueService.queryForParentList(hadoopQueueObj);
			//如果队列含有子队列，则子队列也应该相应地删除
			resultList = new ArrayList<HadoopQueueObj>();
			buildResultList(queueList);//子队列
			idList.clear();
			for (HadoopQueueObj queueObj : resultList) {
				if (!idList.contains(queueObj.getId())) {
					idList.add(queueObj.getId());
				}
			}
			
			hadoopQueueObj.setIdList(idList);
			HadoopQueueRelationObj hadoopQueueRelationObj = new HadoopQueueRelationObj();
			hadoopQueueRelationObj.setQueueIdList(idList);
			
			ByteArrayOutputStream readBos = new ByteArrayOutputStream();
			ByteArrayOutputStream writeBos = new ByteArrayOutputStream();
			//TODO 删除操作 修改配置文件
			try {
				//第一步：查询数据库获取队列名称，操作系统超级用户、密码，配置文件全路径
				for(String queueId:idarr){
					String content = "";
					
					HadoopQueueObj queueObj = this.queryConfigFileInfo(queueId);//查询用户名，密码，配置文件路径
					String passWord = this.dealPwd(queueObj.getPass_word());// 处理密码
					SshResourceFactory ssh = SshResourceFactory.getInstance();
					SshConnection conn = new SshConnection(queueObj.getIp(), 22, queueObj.getUser_name(), passWord);
					ssh.downFileFromRemoteToStream(conn, queueObj.getConfig_path(), readBos);//获得配置文件内容
					content = readBos.toString();
					//第二步：登录管理节点，修改配置文件
					StringReader read = new StringReader(content);
					InputSource is = new InputSource(read);
					DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
					DocumentBuilder builder = factory.newDocumentBuilder();
					Document document = builder.parse(is);
					HadoopQueueObj queObj = new HadoopQueueObj();
					queObj.setId(queueId);
					List<HadoopQueueObj> queList = hadoopQueueService.queryForListByObj(queObj);//查询队列名称
					if (queList!=null && queList.size() > 0) {
						queObj = queList.get(0);
					}
					NodeList list = document.getElementsByTagName("queue");//获取配置文件中为<queue>,节点的集合
					for(int i = 0; i < list.getLength(); i++) {
						Node node = list.item(i);
						NamedNodeMap attrList = node.getAttributes();
						String queName = attrList.getNamedItem("name").getNodeValue();//节点name的值<queue name="value">
						if(queObj.getQueue_name().equals(queName)){//判断如果所删除的队列的名字等于配置文件中节点的name的值
							node.getParentNode().removeChild(node);//移除queue节点
						}
					}
					//修改完的配置文件进行保存
					TransformerFactory   tf   =   TransformerFactory.newInstance();
					Transformer t = tf.newTransformer();
					t.transform(new DOMSource(document), new StreamResult(writeBos));
					String xmlStr = writeBos.toString();
					String xmlNew = XmlFormatUtil.xmlFormat(xmlStr);//格式化xml
					byte[] con = xmlNew.getBytes();
					ByteArrayInputStream bis = new ByteArrayInputStream(con);
					ssh.uploadFileToRemoteToStream(conn, queueObj.getConfig_path(), bis);//写入
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}finally{
				if (readBos != null) {
					try {
						readBos.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if (writeBos != null) {
					try {
						writeBos.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			
			//第四步：更新数据库
			//首先删除关系
			hadoopQueueRelationService.deleteByObj(hadoopQueueRelationObj);
			int ret = hadoopQueueService.deleteByObj(hadoopQueueObj);

			PrintWriterOut.printWirter(response, ret);
		}
	}
	
	/**
	 * @Title: update
	 * @Description: 进入更新页面
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-21 下午7:53:06
	 */
	public String update(){
		if (hadoopQueueObj == null) {
			hadoopQueueObj = new HadoopQueueObj();
		}
		hadoopQueueObj.setId(idstr);
		List<HadoopQueueObj> queueList = hadoopQueueService.queryForListByObj(hadoopQueueObj);
		if (queueList!=null && queueList.size() > 0) {
			hadoopQueueObj = queueList.get(0);
		}
		return "addHadoopQueue";
	}
	
	/**
	 * @Title: list
	 * @Description: 展示列表
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-22 上午9:41:45
	 */
	public String list(){
		if (hadoopQueueObj == null) {
			hadoopQueueObj = new HadoopQueueObj();
		}
		hadoopQueueObj.setPagination(this.getPaginater().initPagination(request));
		if ("-1".equals(hadoopQueueObj.getTactics())) {
			hadoopQueueObj.setTactics(null);
		}
		//查询出所有的父队列
		hadoopQueueObj.setType(0);
		hadoopQueueObj.setEntity_type(2);
		List<HadoopQueueObj> tempList = hadoopQueueService.queryForParentList(hadoopQueueObj);
		resultList = new ArrayList<HadoopQueueObj>();
		//递归查询所有队列的子队列
		this.buildResultList(tempList);
		if ("add".equals(oper)) {
			return "selectParentQueue";
		}
		return "list";
	}
	
	/**
	 * @Title: buildResultList
	 * @Description: 构造结果集
	 * @param
	 * @return void
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-2-13 下午5:36:33
	 */
	private void buildResultList(List<HadoopQueueObj> tempList) {
		for (HadoopQueueObj hadoopQueueObj : tempList) {
			resultList.add(hadoopQueueObj);
			if (hadoopQueueObj.getSubQueueCount() > 0) {//如果存在子队列
				HadoopQueueObj queueObj = new HadoopQueueObj();
				queueObj.setParent_id(hadoopQueueObj.getId());
				queueObj.setEntity_type(2);
				List<HadoopQueueObj> list = hadoopQueueService.queryForSubQueueList(queueObj);
				buildResultList(list);
			}
		}
	}

	/**
	 * @Title: save
	 * @Description: 保存添加和修改的信息
	 * @param
	 * @return void
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @throws Exception 
	 * @createtime 2014-1-22 上午10:02:16
	 */
	public void save() {
		int ret = 0;
		String errorMsg = "";
		String content = "";
		String minRes = "";
		String maxRes = "";
		ByteArrayOutputStream readBos = new ByteArrayOutputStream();
		ByteArrayOutputStream writeBos = new ByteArrayOutputStream();
		
		if ("add".equals(oper)) {//添加操作
			String uuid = RandomUUID.getUuid();
			hadoopQueueObj.setId(uuid);
			if(hadoopQueueObj.getType()==0){
				hadoopQueueObj.setParent_id("0");
			}
			ret = hadoopQueueService.insertByObj(hadoopQueueObj);//插入队列表中
			if (ret == -1) {
				errorMsg += "插入队列表失败";
			} else {
				//插入关系表
				HadoopQueueRelationObj queueRelationObj = new HadoopQueueRelationObj();
				queueRelationObj.setQueue_id(uuid);
				String[] serviceIds = hadoopQueueObj.getService_id().split(",");
				for (String serviceId : serviceIds) {
					queueRelationObj.setEntity_id(serviceId);
					queueRelationObj.setEntity_type("1");//用户
					hadoopQueueRelationService.insertByObj(queueRelationObj);
				}
				
				queueRelationObj.setEntity_id(hadoopQueueObj.getConfigId());
				queueRelationObj.setEntity_type("2");//配置文件
				hadoopQueueRelationService.insertByObj(queueRelationObj);
			}
			
			//修改配置文件
			try {
				HadoopQueueObj queueObj = this.queryConfigFileInfo(uuid);//查询用户名，密码，配置文件路径
				String passWord = this.dealPwd(queueObj.getPass_word());// 处理密码
				SshResourceFactory ssh = SshResourceFactory.getInstance();
				SshConnection conn = new SshConnection(queueObj.getIp(), 22, queueObj.getUser_name(), passWord);
				ssh.downFileFromRemoteToStream(conn, queueObj.getConfig_path(), readBos);
				content = readBos.toString();
				StringReader read = new StringReader(content);
				InputSource is = new InputSource(read);
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder = factory.newDocumentBuilder();
				Document document = builder.parse(is);
				 //获取根节点
				Element eltRoot = document.getDocumentElement();
				//入队列表
				if(hadoopQueueObj.getType()==0){
					//创建叶节点
				    Element queue = document.createElement("queue");
				    queue.setAttribute("name",hadoopQueueObj.getQueue_name());
				    Element minResources = document.createElement("minResources");//创建叶节点的第一个元素
				    Element maxResources = document.createElement("maxResources");//创建叶节点的第二个元素
				    if(hadoopQueueObj.getCpu_min()!=null && !"".equals(hadoopQueueObj.getCpu_min())){
						minRes = hadoopQueueObj.getMem_min()+"mb,"+hadoopQueueObj.getCpu_min()+"vcores";
					}else{
						minRes = hadoopQueueObj.getMem_min()+"mb";
					}
					if(hadoopQueueObj.getCpu_max()!=null && !"".equals(hadoopQueueObj.getCpu_max())){
						maxRes = hadoopQueueObj.getMem_max()+"mb,"+hadoopQueueObj.getCpu_max()+"vcores";
					}else{
						maxRes = hadoopQueueObj.getMem_max()+"mb";
					}
				    Text minText = document.createTextNode(minRes);//创建叶节点的第一个元素下的文本节点
				    minResources.appendChild(minText);//把该文本节点加入到叶节点的第一个元素里面
				    Text maxText = document.createTextNode(maxRes);//创建叶节点的第二个元素下的文本节点
				    maxResources.appendChild(maxText);//把该文本节点加入到叶节点的第二个元素里面
				    
				    //把叶节点下的元素加入到叶节点下
					queue.appendChild(minResources);
					queue.appendChild(maxResources);
				    //把叶节点加入到根节点下
				    eltRoot.appendChild(queue);
					
				}else {//TODO 添加子队列
					NodeList list = document.getElementsByTagName("queue");
					HadoopQueueObj parentQueueObj = new HadoopQueueObj();
					parentQueueObj.setId(hadoopQueueObj.getParent_id());
					List<HadoopQueueObj> queueList = hadoopQueueService.queryForListByObj(parentQueueObj);
					if(queueList!=null && queueList.size()>0){
						parentQueueObj = queueList.get(0);
					}
					for(int i = 0; i < list.getLength(); i++) {
						Node node = list.item(i);
						NamedNodeMap attrList = node.getAttributes();
						String queueName = attrList.getNamedItem("name").getNodeValue();
						if(parentQueueObj.getQueue_name().equals(queueName)) {
							//创建叶节点
				            Element queue = document.createElement("queue");
				            queue.setAttribute("name",hadoopQueueObj.getQueue_name());
				            Element minResources = document.createElement("minResources");//创建叶节点的第一个元素
				            Element maxResources = document.createElement("maxResources");//创建叶节点的第二个元素
				            if(hadoopQueueObj.getCpu_min()!=null && !"".equals(hadoopQueueObj.getCpu_min())){
								minRes = hadoopQueueObj.getMem_min()+"mb,"+hadoopQueueObj.getCpu_min()+"vcores";
							}else{
								minRes = hadoopQueueObj.getMem_min()+"mb";
							}
							if(hadoopQueueObj.getCpu_max()!=null && !"".equals(hadoopQueueObj.getCpu_max())){
								maxRes = hadoopQueueObj.getMem_max()+"mb,"+hadoopQueueObj.getCpu_max()+"vcores";
							}else{
								maxRes = hadoopQueueObj.getMem_max()+"mb";
							}
				            Text minText = document.createTextNode(minRes);//创建叶节点的第一个元素下的文本节点
				            minResources.appendChild(minText);//把该文本节点加入到叶节点的第一个元素里面
				            Text maxText = document.createTextNode(maxRes);//创建叶节点的第二个元素下的文本节点
				            maxResources.appendChild(maxText);//把该文本节点加入到叶节点的第二个元素里面
				            
				            //把叶节点下的元素加入到叶节点下
							queue.appendChild(minResources);
							queue.appendChild(maxResources);
							 //获取子节点的上层节点
							Element queueElement= (Element) document.getElementsByTagName("queue").item(i);
							queueElement.appendChild(queue);//添加到节点中
						}
					}
					
				}
				
				NodeList userNodeList = document.getElementsByTagName("user");
				List<String> nameStr = new ArrayList<String>();
				for(int j=0;j<userNodeList.getLength();j++){
					NamedNodeMap nameList = userNodeList.item(j).getAttributes();
					String name = nameList.getNamedItem("name").getNodeValue();
					nameStr.add(name);
				}
				String[] serviceIdss = hadoopQueueObj.getService_id().split(",");
				HadoopUserObj userObj = new HadoopUserObj();
				for (String serviceId : serviceIdss) {
					userObj.setEntity_id(serviceId);
					userObj.setEntity_type(1);//用户
					userObj.setDeal_type(0);
					userObj.setStatus(2);
					userObj = hadoopUserService.queryUserName(userObj);
					if(userObj!=null){
						if(!nameStr.contains(userObj.getUsername())){
							 Element user = document.createElement("user");
							 user.setAttribute("name",userObj.getUsername());
							 eltRoot.appendChild(user);
						}
					}
				}
				TransformerFactory   tf   =   TransformerFactory.newInstance();
				Transformer t = tf.newTransformer();
				t.transform(new DOMSource(document), new StreamResult(writeBos));
				String xmlStr = writeBos.toString();
				String xmlNew = XmlFormatUtil.xmlFormat(xmlStr);//格式化xml
				byte[] con = xmlNew.getBytes();
				ByteArrayInputStream bis = new ByteArrayInputStream(con);
				ssh.uploadFileToRemoteToStream(conn, queueObj.getConfig_path(), bis);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				if (readBos != null) {
					try {
						readBos.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if (writeBos != null) {
					try {
						writeBos.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		} else {//修改操作
			ret = hadoopQueueService.updateByObj(hadoopQueueObj);
			if (ret == -1) {
				errorMsg = "修改队列表失败";
			}
			
			try {
				HadoopQueueObj queueObj = this.queryConfigFileInfo(hadoopQueueObj.getId());
				String passWord = this.dealPwd(queueObj.getPass_word());// 处理密码
				SshResourceFactory ssh = SshResourceFactory.getInstance();
				SshConnection conn = new SshConnection(queueObj.getIp(), 22, queueObj.getUser_name(), passWord);
				ssh.downFileFromRemoteToStream(conn, queueObj.getConfig_path(), readBos);
				content = readBos.toString();
				
				StringReader read = new StringReader(content);
				InputSource is = new InputSource(read);
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder = factory.newDocumentBuilder();
				Document document = builder.parse(is);
				
				NodeList list = document.getElementsByTagName("queue");
				
				for(int i = 0; i < list.getLength(); i++) {
					Node node = list.item(i);
					NamedNodeMap attrList = node.getAttributes();
					String queueName = attrList.getNamedItem("name").getNodeValue();
					if(hadoopQueueObj.getQueue_name().equals(queueName)) {
						if(hadoopQueueObj.getCpu_min()!=null && !"".equals(hadoopQueueObj.getCpu_min())){
							minRes = hadoopQueueObj.getMem_min()+"mb,"+hadoopQueueObj.getCpu_min()+"vcores";
						}else{
							minRes = hadoopQueueObj.getMem_min()+"mb";
						}
						if(hadoopQueueObj.getCpu_max()!=null && !"".equals(hadoopQueueObj.getCpu_max())){
							maxRes = hadoopQueueObj.getMem_max()+"mb,"+hadoopQueueObj.getCpu_max()+"vcores";
						}else{
							maxRes = hadoopQueueObj.getMem_max()+"mb";
						}
						document.getElementsByTagName("minResources").item(i).getFirstChild().setNodeValue(minRes);
						document.getElementsByTagName("maxResources").item(i).getFirstChild().setNodeValue(maxRes);
					}
				}
				
				TransformerFactory   tf   =   TransformerFactory.newInstance();
				Transformer t = tf.newTransformer();
				t.transform(new DOMSource(document), new StreamResult(writeBos));
				String xmlStr = writeBos.toString();
				String xmlNew = XmlFormatUtil.xmlFormat(xmlStr);//格式化xml
				byte[] con = xmlNew.getBytes();
				ByteArrayInputStream bis = new ByteArrayInputStream(con);
				ssh.uploadFileToRemoteToStream(conn, queueObj.getConfig_path(), bis);
			} catch (Exception e) {
				e.getMessage();
			} finally {
				if (readBos != null) {
					try {
						readBos.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if (writeBos != null) {
					try {
						writeBos.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		PrintWriterOut.printWirter(response, errorMsg);
	}
	
	/**
	 * @Title: dealPwd
	 * @Description: 密码处理类，将空格换成“+”后解密
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-12-27 下午4:26:22
	 */
	private String dealPwd(String pwd) {
		try {
			pwd = DoubleEncryptUtils.decrypt(pwd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pwd;
	}
	
	/**
	 * 
	 * @Title: queryConfigFileInfo
	 * @Description: 查询用户名，密码，配置文件路径
	 * @param
	 * @return HadoopQueueObj
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-3-10 下午4:54:53
	 */
	public HadoopQueueObj queryConfigFileInfo(String queueId){
		HadoopQueueRelationObj hqueueObj = new HadoopQueueRelationObj();
		hqueueObj.setQueue_id(queueId);
		hqueueObj.setEntity_type("2");//配置文件类型(1是用户,2是配置文件)
		//通过队列Id查询tb_hadoop_queue_user_config_relation中的配置文件ID
		List<HadoopQueueRelationObj> relationList = hadoopQueueRelationService.queryForListByObj(hqueueObj);
		if(relationList!=null && relationList.size()>0){
			hqueueObj = relationList.get(0);
		}
		HadoopQueueObj configObj =  new HadoopQueueObj();
		configObj.setConfigId(hqueueObj.getEntity_id());
		//通过配置文件ID查询用户,密码,配置文件路径
		HadoopQueueObj queueObj = hadoopQueueService.queryConfigInfo(configObj);
		return queueObj;
	}
	
	/**
	 * @Title: selectUsers
	 * @Description: 选择用户
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-23 下午3:31:19
	 */
	public String selectUsers(){
		HadoopUserObj hadoopUsers = new HadoopUserObj();
		hadoopUsers.setPagination(this.getPaginater().initPagination(request));
		hadoopUsers.setDeal_type(0);//0 创建,1删除
		hadoopUsers.setStatus(2);
		userList = hadoopUserService.queryForUserList(hadoopUsers);//查询用户
		return "listUsers";
	}
	
	/**
	 * 
	 * @Title: selectUsersGroup
	 * @Description: 选择用户组
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-3-11 下午3:32:43
	 */
	public String selectUsersGroup(){
		HadoopUserGroup hadoopUserGroup = new HadoopUserGroup();
		hadoopUserGroup.setPagination(this.getPaginater().initPagination(request));
		hadoopUserGroup.setDeal_type(0);
		hadoopUserGroup.setStatus(2);
		userGroupList = hadoopUserGroupService.queryForListByObj(hadoopUserGroup);//查询用户组
		return "listUsers";
	}
	/**
	 * @Title: selectConfigFiles
	 * @Description: 选择配置文件
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-23 下午3:32:08
	 */
	@SuppressWarnings("unchecked")
	public String selectConfigFiles(){
		ConfigInfoObj configInfoObj = new ConfigInfoObj();
		configList = configService.queryConfigInfoList(configInfoObj);
		return "listConfigFiles";
	}
	
	/**
	 * 
	 * @Title: selectConfig
	 * @Description: 查询配置文件
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-3-4 上午11:54:23
	 */
	public String  selectConfig(){
		if (configInfoObj == null) {
			configInfoObj = new ConfigInfoObj();
		}
		configInfoObj.setTactics(tactics);
		configList = configService.queryConfigInfoList(configInfoObj);
		if(configList!=null && configList.size()>0){
			configInfoObj = configList.get(0);
		}
		return "config";
	}
	
	/**
	 * 
	 * @Title: checkTactics
	 * @Description: FIFO策略只能唯一
	 * @param
	 * @return void
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-3-5 下午3:10:59
	 */
	public void checkTactics(){
		int ret = 0;
		HadoopQueueObj obj = new HadoopQueueObj();
		obj.setTactics("1");
		List<HadoopQueueObj> queueList = hadoopQueueService.queryForListByObj(obj);
		if(queueList!=null && queueList.size()>0){
			ret = -1;
		}
		try {
			PrintWriter out = response.getWriter();
			out.print(ret);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @Title: managerUsers
	 * @Description: 管理用户
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-3-5 下午5:58:39
	 */
	public String managerUsers(){
		HadoopUserObj hadoopUsers = new HadoopUserObj();
		hadoopUsers.setPagination(this.getPaginater().initPagination(request));
		hadoopUsers.setDeal_type(0);//0 创建,1删除
		hadoopUsers.setStatus(2);
		hadoopUsers.setQueue_id(idstr);
		hadoopUsers.setEntity_type(1);
		userList = hadoopUserService.queryForUserList(hadoopUsers);//查询已关联用户
		return "listUsers";
	}
	
	/**
	 * 
	 * @Title: managerUsersGroup
	 * @Description: 管理用户组
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-3-11 下午3:17:40
	 */
	public String managerUsersGroup(){
		HadoopUserGroup hadoopUserGroup = new HadoopUserGroup();
		hadoopUserGroup.setPagination(this.getPaginater().initPagination(request));
		hadoopUserGroup.setDeal_type(0);
		hadoopUserGroup.setStatus(2);
		hadoopUserGroup.setQueue_id(idstr);
		hadoopUserGroup.setQueue_entity_type("1");
		userGroupList = hadoopUserGroupService.queryForListByObj(hadoopUserGroup);//查询已关联用户组
		return "listUsers";
	}
	/**
	 * 
	 * @Title: connectUser
	 * @Description: 关联用户
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-3-5 下午6:23:05
	 */
	public String connectUser(){
		HadoopUserObj hadoopUsers = new HadoopUserObj();
		hadoopUsers.setPagination(this.getPaginater().initPagination(request));
		hadoopUsers.setDeal_type(0);//0 创建,1删除
		hadoopUsers.setStatus(2);
		//hadoopUsers.setQueue_id(idstr);
		//hadoopUsers.setEntity_type(1);
		userList = hadoopUserService.queryConnectUserList(hadoopUsers);//查询用户

		return "addConnectUsers";
	}
	/**
	 * 
	 * @Title: connectUserGroup
	 * @Description: 管理用户组
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-3-11 下午3:27:16
	 */
	public String connectUserGroup(){
		HadoopUserGroup hadoopUserGroup = new HadoopUserGroup();
		hadoopUserGroup.setPagination(this.getPaginater().initPagination(request));
		hadoopUserGroup.setDeal_type(0);
		hadoopUserGroup.setStatus(2);
		//hadoopUserGroup.setQueue_id(idstr);
		//hadoopUserGroup.setEntity_type(1);
		userGroupList = hadoopUserGroupService.queryConnectUseGroupList(hadoopUserGroup);//查询用户组
		return "addConnectUsers";
	}
	/**
	 * 
	 * @Title: saveConnectUser
	 * @Description: 插入队列服务关系表中
	 * @param
	 * @return void
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-3-5 下午8:18:21
	 */
	public void saveConnectUser(){
		//入关系表
		HadoopQueueRelationObj queueRelationObj = new HadoopQueueRelationObj();
		queueRelationObj.setQueue_id(hadoopQueueRelationObj.getQueue_id());
		String[] serviceIds = hadoopQueueRelationObj.getEntity_id().split(",");
		for (String serviceId : serviceIds) {
			queueRelationObj.setEntity_id(serviceId);
			queueRelationObj.setEntity_type("1");//用户
			hadoopQueueRelationService.insertByObj(queueRelationObj);
		}
	}
	/**
	 * 
	 * @Title: deleteConnectUser
	 * @Description: 删除队列服务关系表中数据
	 * @param
	 * @return void
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-3-5 下午8:25:51
	 */
	public void deleteConnectUser(){
		//删除关系表中数据
		HadoopQueueRelationObj queueRelationObj = new HadoopQueueRelationObj();
		String[] serviceIds = hadoopQueueRelationObj.getEntity_id().split(",");
		for (String serviceId : serviceIds) {
			queueRelationObj.setEntity_id(serviceId);
			hadoopQueueRelationService.deleteByObj(queueRelationObj);
		}
	}
	
	/**
	 * 
	 * @Title: validateQueueName
	 * @Description: 校验队列名字是否存在
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-3-10 下午3:44:29
	 */
	public String validateQueueName(){
		int ret = 0;
		HadoopQueueObj queueObj = new HadoopQueueObj();
		queueObj.setQueue_name(queue_name);
		List<HadoopQueueObj> queueNameList = hadoopQueueService.validateQueueName(queueObj);
		if(queueNameList!=null && queueNameList.size()>0){
			ret = -1;
		}
		PrintWriterOut.printWirter(response, ret);
		return null;
	}
	
	public HadoopQueueObj getHadoopQueueObj() {
		return hadoopQueueObj;
	}

	public void setHadoopQueueObj(HadoopQueueObj hadoopQueueObj) {
		this.hadoopQueueObj = hadoopQueueObj;
	}
	
	public String getOper() {
		return oper;
	}

	public void setOper(String oper) {
		this.oper = oper;
	}

	public String getIdstr() {
		return idstr;
	}

	public void setIdstr(String idstr) {
		this.idstr = idstr;
	}

	public List<HadoopQueueObj> getResultList() {
		return resultList;
	}

	public void setResultList(List<HadoopQueueObj> resultList) {
		this.resultList = resultList;
	}

	public List<HadoopUserObj> getUserList() {
		return userList;
	}

	public void setUserList(List<HadoopUserObj> userList) {
		this.userList = userList;
	}

	public List<ConfigInfoObj> getConfigList() {
		return configList;
	}

	public void setConfigList(List<ConfigInfoObj> configList) {
		this.configList = configList;
	}

	public int getTactics() {
		return tactics;
	}

	public void setTactics(int tactics) {
		this.tactics = tactics;
	}

	public ConfigInfoObj getConfigInfoObj() {
		return configInfoObj;
	}

	public void setConfigInfoObj(ConfigInfoObj configInfoObj) {
		this.configInfoObj = configInfoObj;
	}

	public List<HadoopUserGroup> getUserGroupList() {
		return userGroupList;
	}

	public void setUserGroupList(List<HadoopUserGroup> userGroupList) {
		this.userGroupList = userGroupList;
	}

	public HadoopQueueRelationObj getHadoopQueueRelationObj() {
		return hadoopQueueRelationObj;
	}

	public void setHadoopQueueRelationObj(
			HadoopQueueRelationObj hadoopQueueRelationObj) {
		this.hadoopQueueRelationObj = hadoopQueueRelationObj;
	}

	public String getQueue_name() {
		return queue_name;
	}

	public void setQueue_name(String queue_name) {
		this.queue_name = queue_name;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
	
}


