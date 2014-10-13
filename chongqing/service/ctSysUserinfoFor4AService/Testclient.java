package service.ctSysUserinfoFor4AService;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean; 
import service.ctSysUserinfoFor4AService.CtSysUserinfoFor4AWebService;


public class Testclient {

	public static void main(String[] args) {
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean(); 
		factory.setServiceClass(CtSysUserinfoFor4AWebService.class); 
		factory.setAddress("http://localhost:8080/cloud/WebService/userinfoManageForCT4A"); 
		CtSysUserinfoFor4AWebService service = (CtSysUserinfoFor4AWebService) factory.create();
		System.out.println("#############Client getUser##############"); 
		String userxml = service.findUser("it_test"); 
		//String userxml = service.queryUsers(); 
 
        System.out.println(userxml); 
        
      String xml="<account><accId>it_test111</accId><userPasswordMD5>aXRhZG1pbjEyMzQh</userPasswordMD5><name>test测试</name><email>test@si-tech.com.cn</email><gender></gender><mobile>15023212190</mobile><idCardNumber></idCardNumber></account> ";
       //userxml=userxml.replace("<accounts>","");
       //userxml=userxml.replace("</accounts>","");
        String result=service.addUserInfo(xml);
        
        System.out.println(result); 
        
        //String result2=service.delUser("");
      
         // System.out.println(result2); 
        
        
        
     
}
	
	
}

