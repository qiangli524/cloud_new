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

public class TestClient {

	public static void main(String[] args) throws Exception {

		// String url = "http://localhost:8080/lbsms/services/LbsService";

		TestClient testClient = new TestClient();
		
		
//		testClient.testAddVirtualService(username, password, url);
//		testClient.testUpdateVirtualService(username, password, url);
//		testClient.testDeleteVirtualService(username, password, url);
//
//		testClient.testAddRealServer(username, password, url);
//		testClient.testUpdateRealServer(username, password, url);
//		testClient.testDeleteRealServer(username, password, url);

//		testClient.testReleaseConfig(username, password, url);
		
	}

	public void testDeleteVirtualService(String username, String password,
			String url) {
		LbsClient client = new LbsClient(username, password, url);

		VirtualServiceBean vsBean = new VirtualServiceBean();
		vsBean.setName("virtual service #1.1");
		vsBean.setVirtualAddress("127.0.0.1:56789");

		vsBean.setLoginNo(username);

		LbsResult result = client.DeleteVirtualService(vsBean);
	}

	public void testUpdateVirtualService(String username, String password,
			String url) {

		LbsClient client = new LbsClient(username, password, url);
		VirtualServiceBean vsBean = new VirtualServiceBean();
		vsBean.setName("virtual service #1.1");
		vsBean.setVirtualAddress("127.0.0.1:56789");
		vsBean.setNetmask("255.255.255.255");
		vsBean.setProtocol("tcp");
		vsBean.setPersistentType(""); // 1:yes; 0:no
		vsBean.setPersistent("600");
		vsBean.setScheduler("dh");
		vsBean.setFallback("127.0.0.1:80");
		vsBean.setChecktype("connect");
		vsBean.setCheckcyc("10");
		vsBean.setService("http");
		vsBean.setVirtualHost("127.0.0.1");
		vsBean.setCheckport("80");
		vsBean.setLogin("oliver");
		vsBean.setPassword("password");
		vsBean.setCheckdb("");
		vsBean.setHttpMethod("GET");
		vsBean.setRequest("hello");
		vsBean.setReceive("ok");
		vsBean.setEmailalert("");
		vsBean.setEmailalertfreq("");
		vsBean.setForwardingMethod("");
		vsBean.setFeedbackMethod("");

		vsBean.setLoginNo(username);

		LbsResult result = client.UpdateVirtualService("virtual service #1",
				"127.0.0.1:5678", vsBean);
		
	}

	public void testAddVirtualService(String username, String password,
			String url) {
		LbsClient client = new LbsClient(username, password, url);
		VirtualServiceBean vsBean = new VirtualServiceBean();
		vsBean.setName("virtual service #1");
		vsBean.setVirtualAddress("127.0.0.1:5678");
		vsBean.setNetmask("255.255.255.255");
		vsBean.setProtocol("tcp");
		vsBean.setPersistentType(""); // 1:yes; 0:no
		vsBean.setPersistent("600");
		vsBean.setScheduler("dh");
		vsBean.setFallback("127.0.0.1:80");
		vsBean.setChecktype("connect");
		vsBean.setCheckcyc("10");
		vsBean.setService("http");
		vsBean.setVirtualHost("127.0.0.1");
		vsBean.setCheckport("80");
		vsBean.setLogin("oliver");
		vsBean.setPassword("password");
		vsBean.setCheckdb("");
		vsBean.setHttpMethod("GET");
		vsBean.setRequest("hello");
		vsBean.setReceive("ok");
		vsBean.setEmailalert("");
		vsBean.setEmailalertfreq("");
		vsBean.setForwardingMethod("");
		vsBean.setFeedbackMethod("");
		vsBean.setLoginNo(username);

		// ��ӳɹ�,infoֵΪ����������ݿ�id��
		LbsResult result = client.AddVirtualService(vsBean);
	}

	public void testAddRealServer(String username, String password, String url) {

		LbsClient client = new LbsClient(username, password, url);
		RealServerBean rsBean = new RealServerBean();
		rsBean.setVirtualAddress("25");//���������ID
		rsBean.setName("real server #1");
		rsBean.setRealAddress("127.0.0.1:5678");
		rsBean.setWeight("30");
		rsBean.setRequest("request");
		rsBean.setReceive("reveive");

		rsBean.setLoginNo(username);

		// ��ӳɹ�,infoֵΪ����������ݿ�id��
		LbsResult result = client.AddRealServer(rsBean);
	}

	public void testUpdateRealServer(String username, String password,
			String url) {

		LbsClient client = new LbsClient(username, password, url);
		RealServerBean rsBean = new RealServerBean();
		rsBean.setVirtualAddress("25");
		rsBean.setName("real server #2");
		rsBean.setRealAddress("127.0.0.1:2222");
		rsBean.setWeight("88");
		rsBean.setRequest("request updated");
		rsBean.setReceive("reveive updated");

		String virtualAddress = "25";//���������ID
		String oldName = "real server #1";
		String oldRealAddress = "127.0.0.1:5678";

		rsBean.setLoginNo(username);

		LbsResult result = client.UpdateRealServer(virtualAddress, oldName,
				oldRealAddress, rsBean);
	}

	public void testDeleteRealServer(String username, String password,
			String url) {

		LbsClient client = new LbsClient(username, password, url);
		RealServerBean rsBean = new RealServerBean();
		rsBean.setVirtualAddress("1");
		rsBean.setName("real server #2");
		rsBean.setRealAddress("127.0.0.1:2222");

		rsBean.setLoginNo(username);
		rsBean.setId("18");
		LbsResult result = client.DeleteRealServer(rsBean);
	}

	public void testReleaseConfig(String username, String password, String url) {

		LbsClient client = new LbsClient(username, password, url);
		LbsResult result = client.ReleaseConfig(username);
	}
}
