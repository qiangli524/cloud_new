/*
 * Copyright 2001-2004 The Apache Software Foundation.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sitech.basd.sxcloud.rsmu.lbsms;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;

import org.apache.axis.AxisFault;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

public class LbsClient {

	String username;
	String password;
	String url;

	public LbsClient() {
	}

	public LbsClient(String username, String password, String url) {
		this.username = username;
		this.password = password;
		this.url = url;
	}

	public LbsResult AddVirtualService(VirtualServiceBean vsBean) {
		LbsResult lbsResult = new LbsResult();
		try {
			Service service = new Service();
			Call call = (Call) service.createCall();
			QName qnVirtualServiceBean = new QName("urn:LbsService",
					"VirtualServiceBean");
			call.registerTypeMapping(VirtualServiceBean.class,
					qnVirtualServiceBean,
					new org.apache.axis.encoding.ser.BeanSerializerFactory(
							VirtualServiceBean.class, qnVirtualServiceBean),
					new org.apache.axis.encoding.ser.BeanDeserializerFactory(
							VirtualServiceBean.class, qnVirtualServiceBean));

			QName qnLbsResult = new QName("urn:LbsService", "LbsResult");
			call.registerTypeMapping(LbsResult.class, qnLbsResult,
					new org.apache.axis.encoding.ser.BeanSerializerFactory(
							LbsResult.class, qnLbsResult),
					new org.apache.axis.encoding.ser.BeanDeserializerFactory(
							LbsResult.class, qnLbsResult));

			call.setTargetEndpointAddress(new java.net.URL(url));
			call.setOperationName(new QName("LbsService", "AddVirtualService"));
			call.addParameter("vsBean", qnVirtualServiceBean, ParameterMode.IN);
			call.setReturnType(qnLbsResult);

			call.getMessageContext().setUsername(username);
			call.getMessageContext().setPassword(password);

			lbsResult = (LbsResult) call.invoke(new Object[] { vsBean });
		} catch (Exception e) {
			e.printStackTrace();
			if (e instanceof AxisFault) {
				lbsResult.setCode(LbsResult.FLAG_AXIS_FAULT);
				AxisFault fault = (AxisFault) e;
				lbsResult.setInfo(fault.getMessage());
			} else {
				lbsResult.setCode(LbsResult.FLAG_EXCEPTION);
				lbsResult.setInfo(e.getMessage());
			}
		}
		return lbsResult;
	}

	public LbsResult DeleteVirtualService(VirtualServiceBean vsBean) {
		LbsResult lbsResult = new LbsResult();
		try {
			Service service = new Service();
			Call call = (Call) service.createCall();
			QName qnVirtualServiceBean = new QName("urn:LbsService",
					"VirtualServiceBean");
			call.registerTypeMapping(VirtualServiceBean.class,
					qnVirtualServiceBean,
					new org.apache.axis.encoding.ser.BeanSerializerFactory(
							VirtualServiceBean.class, qnVirtualServiceBean),
					new org.apache.axis.encoding.ser.BeanDeserializerFactory(
							VirtualServiceBean.class, qnVirtualServiceBean));

			QName qnLbsResult = new QName("urn:LbsService", "LbsResult");
			call.registerTypeMapping(LbsResult.class, qnLbsResult,
					new org.apache.axis.encoding.ser.BeanSerializerFactory(
							LbsResult.class, qnLbsResult),
					new org.apache.axis.encoding.ser.BeanDeserializerFactory(
							LbsResult.class, qnLbsResult));

			call.setTargetEndpointAddress(new java.net.URL(url));
			call.setOperationName(new QName("LbsService",
					"DeleteVirtualService"));
			call.addParameter("vsBean", qnVirtualServiceBean, ParameterMode.IN);
			call.setReturnType(qnLbsResult);

			call.getMessageContext().setUsername(username);
			call.getMessageContext().setPassword(password);

			lbsResult = (LbsResult) call.invoke(new Object[] { vsBean });

		} catch (Exception e) {
			if (e instanceof AxisFault) {
				lbsResult.setCode(LbsResult.FLAG_AXIS_FAULT);
				AxisFault fault = (AxisFault) e;
				lbsResult.setInfo(fault.getMessage());
			} else {
				lbsResult.setCode(LbsResult.FLAG_EXCEPTION);
				lbsResult.setInfo(e.getMessage());
			}
		}

		return lbsResult;
	}

	public LbsResult UpdateVirtualService(String oldName,
			String oldVirtualAddress, VirtualServiceBean vsBean) {
		LbsResult lbsResult = new LbsResult();
		try {

			Service service = new Service();
			Call call = (Call) service.createCall();
			QName qnVirtualServiceBean = new QName("urn:LbsService",
					"VirtualServiceBean");
			call.registerTypeMapping(VirtualServiceBean.class,
					qnVirtualServiceBean,
					new org.apache.axis.encoding.ser.BeanSerializerFactory(
							VirtualServiceBean.class, qnVirtualServiceBean),
					new org.apache.axis.encoding.ser.BeanDeserializerFactory(
							VirtualServiceBean.class, qnVirtualServiceBean));

			QName qnLbsResult = new QName("urn:LbsService", "LbsResult");
			call.registerTypeMapping(LbsResult.class, qnLbsResult,
					new org.apache.axis.encoding.ser.BeanSerializerFactory(
							LbsResult.class, qnLbsResult),
					new org.apache.axis.encoding.ser.BeanDeserializerFactory(
							LbsResult.class, qnLbsResult));

			call.setTargetEndpointAddress(new java.net.URL(url));
			call.setOperationName(new QName("LbsService",
					"UpdateVirtualService"));
			call.addParameter("oldName",
					org.apache.axis.encoding.XMLType.XSD_STRING,
					ParameterMode.IN);
			call.addParameter("oldVirtualAddress",
					org.apache.axis.encoding.XMLType.XSD_STRING,
					ParameterMode.IN);
			call.addParameter("vsBean", qnVirtualServiceBean, ParameterMode.IN);
			call.setReturnType(qnLbsResult);

			call.getMessageContext().setUsername(username);
			call.getMessageContext().setPassword(password);

			lbsResult = (LbsResult) call.invoke(new Object[] { oldName,
					oldVirtualAddress, vsBean });
		} catch (Exception e) {
			if (e instanceof AxisFault) {
				lbsResult.setCode(LbsResult.FLAG_AXIS_FAULT);
				AxisFault fault = (AxisFault) e;
				lbsResult.setInfo(fault.getMessage());
			} else {
				lbsResult.setCode(LbsResult.FLAG_EXCEPTION);
				lbsResult.setInfo(e.getMessage());
			}
		}

		return lbsResult;
	}

	public LbsResult AddRealServer(RealServerBean rsBean) {
		LbsResult lbsResult = new LbsResult();
		try {
			Service service = new Service();
			Call call = (Call) service.createCall();
			QName qnRealServerBean = new QName("urn:LbsService",
					"RealServerBean");
			call.registerTypeMapping(RealServerBean.class, qnRealServerBean,
					new org.apache.axis.encoding.ser.BeanSerializerFactory(
							RealServerBean.class, qnRealServerBean),
					new org.apache.axis.encoding.ser.BeanDeserializerFactory(
							RealServerBean.class, qnRealServerBean));

			QName qnLbsResult = new QName("urn:LbsService", "LbsResult");
			call.registerTypeMapping(LbsResult.class, qnLbsResult,
					new org.apache.axis.encoding.ser.BeanSerializerFactory(
							LbsResult.class, qnLbsResult),
					new org.apache.axis.encoding.ser.BeanDeserializerFactory(
							LbsResult.class, qnLbsResult));

			call.setTargetEndpointAddress(new java.net.URL(url));
			call.setOperationName(new QName("LbsService", "AddRealServer"));
			call.addParameter("rsBean", qnRealServerBean, ParameterMode.IN);
			call.setReturnType(qnLbsResult);

			call.getMessageContext().setUsername(username);
			call.getMessageContext().setPassword(password);

			lbsResult = (LbsResult) call.invoke(new Object[] { rsBean });

		} catch (Exception e) {
			if (e instanceof AxisFault) {
				lbsResult.setCode(LbsResult.FLAG_AXIS_FAULT);
				AxisFault fault = (AxisFault) e;
				lbsResult.setInfo(fault.getMessage());
			} else {
				lbsResult.setCode(LbsResult.FLAG_EXCEPTION);
				lbsResult.setInfo(e.getMessage());
			}
		}

		return lbsResult;
	}

	public LbsResult DeleteRealServer(RealServerBean rsBean) {
		LbsResult lbsResult = new LbsResult();
		try {
			Service service = new Service();
			Call call = (Call) service.createCall();
			QName qnRealServerBean = new QName("urn:LbsService",
					"RealServerBean");
			call.registerTypeMapping(RealServerBean.class, qnRealServerBean,
					new org.apache.axis.encoding.ser.BeanSerializerFactory(
							RealServerBean.class, qnRealServerBean),
					new org.apache.axis.encoding.ser.BeanDeserializerFactory(
							RealServerBean.class, qnRealServerBean));

			QName qnLbsResult = new QName("urn:LbsService", "LbsResult");
			call.registerTypeMapping(LbsResult.class, qnLbsResult,
					new org.apache.axis.encoding.ser.BeanSerializerFactory(
							LbsResult.class, qnLbsResult),
					new org.apache.axis.encoding.ser.BeanDeserializerFactory(
							LbsResult.class, qnLbsResult));

			call.setTargetEndpointAddress(new java.net.URL(url));

			call.setOperationName(new QName("LbsService", "DeleteRealServer"));

			call.addParameter("rsBean", qnRealServerBean, ParameterMode.IN);
			call.setReturnType(qnLbsResult);

			call.getMessageContext().setUsername(username);
			call.getMessageContext().setPassword(password);

			lbsResult = (LbsResult) call.invoke(new Object[] { rsBean });

		} catch (Exception e) {
			if (e instanceof AxisFault) {
				lbsResult.setCode(LbsResult.FLAG_AXIS_FAULT);
				AxisFault fault = (AxisFault) e;
				lbsResult.setInfo(fault.getMessage());
			} else {
				lbsResult.setCode(LbsResult.FLAG_EXCEPTION);
				lbsResult.setInfo(e.getMessage());
			}
		}

		return lbsResult;
	}

	public LbsResult UpdateRealServer(String virtualAddress, String oldName,
			String oldRealAddress, RealServerBean rsBean) {
		LbsResult lbsResult = new LbsResult();
		try {
			Service service = new Service();
			Call call = (Call) service.createCall();
			QName qnRealServerBean = new QName("urn:LbsService",
					"RealServerBean");
			call.registerTypeMapping(RealServerBean.class, qnRealServerBean,
					new org.apache.axis.encoding.ser.BeanSerializerFactory(
							RealServerBean.class, qnRealServerBean),
					new org.apache.axis.encoding.ser.BeanDeserializerFactory(
							RealServerBean.class, qnRealServerBean));

			QName qnLbsResult = new QName("urn:LbsService", "LbsResult");
			call.registerTypeMapping(LbsResult.class, qnLbsResult,
					new org.apache.axis.encoding.ser.BeanSerializerFactory(
							LbsResult.class, qnLbsResult),
					new org.apache.axis.encoding.ser.BeanDeserializerFactory(
							LbsResult.class, qnLbsResult));

			call.setTargetEndpointAddress(new java.net.URL(url));
			call.setOperationName(new QName("LbsService", "UpdateRealServer"));
			call.addParameter("virtualAddress",
					org.apache.axis.encoding.XMLType.XSD_STRING,
					ParameterMode.IN);
			call.addParameter("oldName",
					org.apache.axis.encoding.XMLType.XSD_STRING,
					ParameterMode.IN);
			call.addParameter("oldRealAddress",
					org.apache.axis.encoding.XMLType.XSD_STRING,
					ParameterMode.IN);
			call.addParameter("rsBean", qnRealServerBean, ParameterMode.IN);

			call.setReturnType(qnLbsResult);

			call.getMessageContext().setUsername(username);
			call.getMessageContext().setPassword(password);

			lbsResult = (LbsResult) call.invoke(new Object[] { virtualAddress,
					oldName, oldRealAddress, rsBean });

		} catch (Exception e) {
			if (e instanceof AxisFault) {
				lbsResult.setCode(LbsResult.FLAG_AXIS_FAULT);
				AxisFault fault = (AxisFault) e;
				lbsResult.setInfo(fault.getMessage());
			} else {
				lbsResult.setCode(LbsResult.FLAG_EXCEPTION);
				lbsResult.setInfo(e.getMessage());
			}
		}

		return lbsResult;
	}

	public LbsResult ReleaseConfig(String username) {
		LbsResult lbsResult = new LbsResult();
		try {
			Service service = new Service();
			Call call = (Call) service.createCall();
			
			QName qnLbsResult = new QName("urn:LbsService", "LbsResult");
			call.registerTypeMapping(LbsResult.class, qnLbsResult,
					new org.apache.axis.encoding.ser.BeanSerializerFactory(
							LbsResult.class, qnLbsResult),
					new org.apache.axis.encoding.ser.BeanDeserializerFactory(
							LbsResult.class, qnLbsResult));
			
			call.setTargetEndpointAddress(new java.net.URL(url));
			call.setOperationName(new QName("LbsService", "ReleaseConfig"));
			call.addParameter("username",
					org.apache.axis.encoding.XMLType.XSD_STRING,
					ParameterMode.IN);
			call.setReturnType(qnLbsResult);
			
			call.getMessageContext().setUsername(username);
			call.getMessageContext().setPassword(password);

			lbsResult = (LbsResult) call.invoke(new Object[] { username });
		} catch (Exception e) {
			if (e instanceof AxisFault) {
				lbsResult.setCode(LbsResult.FLAG_AXIS_FAULT);
				AxisFault fault = (AxisFault) e;
				lbsResult.setInfo(fault.getMessage());
			} else {
				lbsResult.setCode(LbsResult.FLAG_EXCEPTION);
				lbsResult.setInfo(e.getMessage());
			}
		}
		return lbsResult;
	}
 public static void main(String []args){
	 
	 LbsClient client = new LbsClient("u1", "1234", "http://172.21.3.161:8090/lbsms/services/LbsService");
	 VirtualServiceBean vsBean = new VirtualServiceBean();
	 RealServerBean realBeab = new RealServerBean();
	 realBeab.setName("cloud3");
	 realBeab.setRealAddress("172.21.3.34:8080");
	 realBeab.setVirtualAddress("172.21.3.56:8080");
	 realBeab.setLoginNo("root");
	 realBeab.setId("20");
	 realBeab.setWeight("11");
	 
//		vsBean.setId("172.21.3.50:8080");
//		vsBean.setLoginNo("root");
		LbsResult result = client.AddRealServer(realBeab);
	 
 }
}
