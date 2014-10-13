/**
 * SX4AUsers.java
 * com.sitech.ssd.sx.aaaa.junit
 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2014 三月 25 		duangh
 *
 * Copyright (c) 2014, si-tech All Rights Reserved.
 */

package com.sitech.ssd.sx.aaaa.junit;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.encoding.XMLType;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.junit.Assert;
import org.junit.Test;

import com.sitech.ssd.sx.aaaa.domain.AddRoleResponse;
import com.sitech.ssd.sx.aaaa.domain.AddUserResponse;
import com.sitech.ssd.sx.aaaa.domain.DelRoleResponse;
import com.sitech.ssd.sx.aaaa.domain.DelUserResponse;
import com.sitech.ssd.sx.aaaa.domain.EditRoleResponse;
import com.sitech.ssd.sx.aaaa.domain.EditUserResponse;
import com.sitech.ssd.sx.aaaa.domain.OperationUserPwdResponse;
import com.sitech.ssd.sx.aaaa.domain.OperationUserResponse;
import com.sitech.ssd.sx.aaaa.domain.SelAllUserResponse;
import com.sitech.ssd.sx.aaaa.domain.SelRoleResponse;
import com.sitech.ssd.sx.aaaa.domain.SelUserRequest;
import com.sitech.ssd.sx.aaaa.util.JAXBMarshaller;
import com.sitech.ssd.sx.aaaa.util.JAXBUnmarshaller;

/**
 * ClassName:SX4AUsers Function: 测试4A用户webservice 接口
 * 
 * @author duangh
 * @version
 * @since Ver 1.0
 * @Date 2014 三月 25 15:34:42
 */
public class TestEndpoint {

	// @Test
	public void queryUserTest() {// 查询单个用户测试类
		try {
			String selUserRequest = "<?xml version='1.0' encoding='UTF-8'?><SelUserRequest><uid>admin</uid></SelUserRequest>";
			Service service = new Service();
			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress("http://127.0.0.1:8080/cloud/WebService/users?wsdl");
			QName qName = new QName("http://service.aaaa.sx.ssd.sitech.com/", "selUser");
			call.setOperationName(qName);
			call.setUseSOAPAction(true);
			// 这下面两行一定要加上，否则接收在服务器端收不到。
			call.addParameter("selUserRequest", XMLType.XSD_STRING, ParameterMode.IN);
			call.setReturnType(XMLType.XSD_STRING);
			String result = (String) call.invoke(new Object[] { selUserRequest });
			System.out.println(result);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// @Test
	public void queryAllUser() {// 查询所有用户
		String xml = "<?xml version='1.0' encoding='UTF-8'?><SelAllUserRequest><uid>all</uid></SelAllUserRequest>";
		String result = axisCall(xml, "http://127.0.0.1:8080/cloud/WebService/users?wsdl", "selAllUserRequest",
				"selAllUser");
		SelAllUserResponse reponse = JAXBUnmarshaller.xml2java(SelAllUserResponse.class, result);
		// System.out.print(reponse.getRole());
		Assert.assertNotNull(reponse.getUser());
	}

	// @Test
	public void addUser() {// 添加用户
		String xml = "<AddUserRequest><User><uid>Zhangsan</uid><employeenumber>324</employeenumber><cn>张三</cn><role>3611</role><mobile>13500000000</mobile><enable>1</enable><pwd>secret</pwd><startdate>2010-05-06</startdate><enddate>2010-05-06</enddate><updatedate>2010-05-06 10:00:00</updatedate><position>position1@position2@position3</position><job>job1@job2@job3</job><org>00410088002900000000@00410088002900000001@00410088002900000002</org><sort>00410000000000000000/10@00410000000000000000/20</sort><misassperid>1127</misassperid></User></AddUserRequest>";
		String result = axisCall(xml, "http://127.0.0.1:8080/cloud/WebService/users?wsdl", "addUserRequest", "addUser");
		// 返回结果
		// <?xml version="1.0" encoding="UTF-8"
		// standalone="yes"?><AddUserResponse><Ret><code>true</code><message>创建帐号成功!</message></Ret></AddUserResponse>
		AddUserResponse reponse = JAXBUnmarshaller.xml2java(AddUserResponse.class, result);
		Assert.assertEquals(reponse.getRet().getCode(), "true");

	}

	// @Test
	public void editUser() {// 编辑用户
		String xml = "<EditUserRequest><User><uid>Zhangsan</uid><employeenumber>324</employeenumber><cn>张三</cn><role>3611</role><mobile>13511111111</mobile><enable>1</enable><pwd>secret</pwd><startdate>2010-05-06</startdate><enddate>2010-05-06</enddate><updatedate>2010-05-06 10:00:00</updatedate><position>position1@position2@position3</position><job>job1@job2@job3</job><org>00410088002900000000@00410088002900000001@00410088002900000002</org><sort>00410000000000000000/10@00410000000000000000/20</sort><misassperid>1127</misassperid></User></EditUserRequest>";
		String result = axisCall(xml, "http://127.0.0.1:8080/cloud/WebService/users?wsdl", "editUserRequest",
				"editUser");
		// 返回结果
		// <?xml version="1.0" encoding="UTF-8"
		// standalone="yes"?><EditUserResponse><Ret><code>true</code><message>修改帐号成功!</message></Ret></EditUserResponse>
		System.out.println(result);
		EditUserResponse reponse = JAXBUnmarshaller.xml2java(EditUserResponse.class, result);
		Assert.assertEquals(reponse.getRet().getCode(), "true");
	}

	// @Test
	public void deleteUser() {// 删除用户
		String xml = "<DelUserRequest><uid>Zhangsan</uid></DelUserRequest>";
		String result = axisCall(xml, "http://127.0.0.1:8080/cloud/WebService/users?wsdl", "delUserRequest", "delUser");
		// 返回结果
		// <?xml version="1.0" encoding="UTF-8"
		// standalone="yes"?><DelUserResponse><Ret><code>true</code><message>删除帐号成功!</message></Ret></DelUserResponse>
		DelUserResponse reponse = JAXBUnmarshaller.xml2java(DelUserResponse.class, result);
		Assert.assertEquals(reponse.getRet().getCode(), "true");
	}

	// @Test
	public void operationUser() {// 暂挂、恢复帐号
		String xml = "<OperationUserRequest><uid>Zhangsan</uid><enable>0</enable></OperationUserRequest>";
		String result = axisCall(xml, "http://127.0.0.1:8080/cloud/WebService/users?wsdl", "operationUserRequest",
				"operationUser");
		// 返回结果
		// <?xml version="1.0" encoding="UTF-8"
		// standalone="yes"?><OperationUserResponse><Ret><code>true</code><message>操作成功!</message></Ret></OperationUserResponse>
		OperationUserResponse response = JAXBUnmarshaller.xml2java(OperationUserResponse.class, result);
		Assert.assertEquals(response.getRet().getCode(), "true");

	}

	// @Test
	public void operationPwd() {// 更改密码
		String xml = "<OperationUserPwdRequest><uid>Zhangsan</uid><pwd>0</pwd></OperationUserPwdRequest>";
		String result = axisCall(xml, "http://127.0.0.1:8080/cloud/WebService/users?wsdl", "operationUserPwdRequest",
				"operationUserPwd");
		// 返回结果
		// <?xml version="1.0" encoding="UTF-8"
		// standalone="yes"?><OperationUserPwdResponse><Ret><code>true</code><message>操作成功！</message></Ret></OperationUserPwdResponse>
		OperationUserPwdResponse response = JAXBUnmarshaller.xml2java(OperationUserPwdResponse.class, result);
		Assert.assertEquals(response.getRet().getCode(), "true");

	}

	// @Test
	public void queryRole() {// 查询角色
		String xml = "<SelRoleRequest><roleid>all</roleid></SelRoleRequest>";
		String result = axisCall(xml, "http://127.0.0.1:8080/cloud/WebService/roles?wsdl", "selRoleRequest", "selRole");
		SelRoleResponse reponse = JAXBUnmarshaller.xml2java(SelRoleResponse.class, result);
		// System.out.print(reponse.getRole());
		Assert.assertNotNull(reponse.getRole());
	}

	// @Test
	public void addRole() {// 增加角色
		String xml = "<AddRoleRequest><Role><roleid>001</roleid><rolename>管理员</rolename><roledes>用户管理系统的超级用户</roledes><permissionid>quanxian1@quanxian2@quanxian3</permissionid></Role></AddRoleRequest>";
		String result = axisCall(xml, "http://127.0.0.1:8080/cloud/WebService/roles?wsdl", "addRoleRequest", "addRole");
		// 返回结果
		// <?xml version="1.0" encoding="UTF-8"
		// standalone="yes"?><AddRoleResponse><Ret><code>true</code><message>创建成功!</message></Ret></AddRoleResponse>
		AddRoleResponse response = JAXBUnmarshaller.xml2java(AddRoleResponse.class, result);
		Assert.assertEquals(response.getRet().getCode(), "true");
	}

	// @Test
	public void editRole() {// 编辑角色
		String xml = "<EditRoleRequest><Role><roleid>001</roleid><rolename>管理员</rolename><roledes>用户管理系统的超级用户</roledes>	<permissionid>quanxian1@quanxian2@quanxian3</permissionid></Role></EditRoleRequest>";
		String result = axisCall(xml, "http://127.0.0.1:8080/cloud/WebService/roles?wsdl", "editRoleRequest",
				"editRole");
		// 返回结果
		// <?xml version="1.0" encoding="UTF-8"
		// standalone="yes"?><EditRoleResponse><Ret><code>true</code><message>修改成功!</message></Ret></EditRoleResponse>
		EditRoleResponse response = JAXBUnmarshaller.xml2java(EditRoleResponse.class, result);
		Assert.assertEquals(response.getRet().getCode(), "true");
	}

	@Test
	public void delRole() { // 删除角色
		String xml = "<DelRoleRequest><roleid>1080</roleid></DelRoleRequest>";
		String result = axisCall(xml, "http://127.0.0.1:8080/cloud/WebService/roles?wsdl", "delRoleRequest", "delRole");

		// 返回结果
		// <?xml version="1.0" encoding="UTF-8"
		// standalone="yes"?><DelRoleResponse><Ret><code>true</code><message>删除成功!</message></Ret></DelRoleResponse>
		DelRoleResponse response = JAXBUnmarshaller.xml2java(DelRoleResponse.class, result);
		Assert.assertEquals(response.getRet().getCode(), "true");

	}

	// @Test
	public void testXml2Java() {// 将xml转化为java对象,利用CXF自动转化取不到属性中的值
		String xml = "<?xml version='1.0' encoding='UTF-8'?><SelUserRequest><uid>admin</uid></SelUserRequest>";
		SelUserRequest sel = JAXBUnmarshaller.xml2java(SelUserRequest.class, xml);
		Assert.assertEquals("admin", sel.getUid());
	}

	// @Test
	public void testJava2Xml() {// 将java对象转为xml,利用CXF自动转化取不到属性中的值
		String str = JAXBMarshaller.java2xml(new SelUserRequest());
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><SelUserRequest/>";
		Assert.assertEquals(xml, str);
	}

	private String axisCall(String xmlRequest, String wsdlAddress, String parameterName, String methodName) {// 利用axis作为client调用CXF发布的webservice服务
		String result = null;
		try {
			Service service = new Service();
			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress(wsdlAddress);
			QName qName = new QName("http://service.aaaa.sx.ssd.sitech.com/", methodName);
			call.setOperationName(qName);
			call.setUseSOAPAction(true);
			// 这下面两行一定要加上，否则接收在服务器端收不到。
			call.addParameter(parameterName, XMLType.XSD_STRING, ParameterMode.IN);
			call.setReturnType(XMLType.XSD_STRING);
			result = (String) call.invoke(new Object[] { xmlRequest });
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
