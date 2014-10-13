package com.sitech.ssd.test.resource;


//@Service("testAspectj")
public class TestAspectj {
	public String print(String user) {
		String result = "20130827" + user;
		return result;
	}

	public String println(String user, TestAspectj testAspectj) {
		String result = "20130827testAspectj" + user;
		return result;
	}
}
