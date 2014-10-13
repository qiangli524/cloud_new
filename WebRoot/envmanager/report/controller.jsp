<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*,java.util.*"%>
<%@ page import="sitech.ssd.env.grid.gt.server.*"%>
<%!
	String url = "";
	String uname = "";
	String pwd = "";
	String tname = "";

	void setValue(String turl,String tuname,String tpwd,String ttname){
		url = turl;
		uname = tuname;
		pwd = tpwd;
		tname = ttname;
	}

	Connection getConnection(){
		
//		String url="jdbc:oracle:thin:@172.21.0.33:1521:ocs";
		Connection conn= null;
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
			conn = DriverManager.getConnection(url,uname,pwd);
		}catch(Exception e){
			e.printStackTrace();
		}
		return conn;
	}

	void closeConnection(Connection conn){
		try{
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

    String getRSValue(String str)
	{
		if(str==null)
			return "";
		else
			return str;
	}

	List listOrders(String sql){
	   Connection conn = getConnection();
	   if(conn==null) 
	 		return new ArrayList();
	   Statement stmt = null;
	   
	   ResultSet rs = null;
	   List list = new ArrayList();
	   
	   try{
		   stmt = conn.createStatement();
		   rs = stmt.executeQuery(sql);
			int count=0;
		   while(rs.next()){
				Map map = new HashMap();
				map.put("ROWID", rs.getString("ROWID"));
				map.put("ENV", rs.getInt("CF_ENV"));
				map.put("DOMAIN", getRSValue(rs.getString("CF_DOMAIN")));
				map.put("RESOURCE", getRSValue(rs.getString("CF_RESOURCE")));
				map.put("CLASS", getRSValue(rs.getString("CF_CLASS")));
				map.put("HOSTTYPE", getRSValue(rs.getString("CF_HOSTTYPE")));
				map.put("HOSTNUM", getRSValue(rs.getString("CF_HOSTNUM")));
				map.put("DESCRIPTION", getRSValue(rs.getString("CF_DESCRIPTION")));
				map.put("SYSTEM", getRSValue(rs.getString("CF_SYSTEM")));
				map.put("HOSTNAME", getRSValue(rs.getString("CF_HOSTNAME")));
				map.put("IP", getRSValue(rs.getString("CF_IP")));
				map.put("PRODUCT", getRSValue(rs.getString("CF_PRODUCT")));
				map.put("DEVEPROD", getRSValue(rs.getString("CF_DEVEPROD")));
				map.put("SID", getRSValue(rs.getString("CF_SID")));
				map.put("TABLESPACE", getRSValue(rs.getString("CF_TABLESPACE")));
				map.put("FILESYSNAM", getRSValue(rs.getString("CF_FILESYSNAM")));
				map.put("FILEAPPNUM", getRSValue(rs.getString("CF_FILEAPPNUM")));
				map.put("CPUUSED", getRSValue(rs.getString("CF_CPUUSED")));
				map.put("MEM", getRSValue(rs.getString("CF_MEM")));
				map.put("FILEUSERD", getRSValue(rs.getString("CF_FILEUSERD")));
				map.put("FILEUSEPER", getRSValue(rs.getString("CF_FILEUSEPER")));
				map.put("SID1", getRSValue(rs.getString("CF_SID1")));
				map.put("TABSPAUSED", getRSValue(rs.getString("CF_TABSPAUSED")));
				map.put("TABSPAUSEPER", getRSValue(rs.getString("CF_TABSPAUSEPER")));
				map.put("SGA", getRSValue(rs.getString("CF_SGA")));
				map.put("CPULEFT", getRSValue(rs.getString("CF_CPULEFT")));
				map.put("MEMLEFT", getRSValue(rs.getString("CF_MEMLEFT")));
				map.put("STORAGE", getRSValue(rs.getString("CF_STORAGE")));  
				map.put("CFDATE", getRSValue(rs.getString("CF_DATE"))); 
				list.add(map);
				count++;
		   }
		   rs.close();
		   stmt.close();
	   }catch(Exception e){
		   e.printStackTrace();
	   }
	   closeConnection(conn);
	   return list;
	}
	
	int[] insertOrders( List updatedList){
		
		int[] opresults=null;
		String sql="INSERT INTO "+tname+" (cf_env,cf_domain,cf_resource,cf_class,cf_hosttype,cf_hostnum,cf_description,cf_system,cf_hostname,cf_ip,cf_product,cf_deveprod,cf_sid,cf_tablespace,cf_filesysnam,cf_fileappnum,cf_cpuused,cf_mem,cf_fileuserd,cf_fileuseper,cf_sid1,cf_tabspaused,cf_tabspauseper,cf_sga,cf_cpuleft,cf_memleft,cf_storage,cf_date) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			Connection conn=null;
			PreparedStatement pstmt = null;
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				for (int i=0;i<updatedList.size();i++){
					Map record= (Map)updatedList.get(i);
					pstmt.setString(1 ,String.valueOf(record.get("ENV")));  
					pstmt.setString(2 ,String.valueOf(record.get("DOMAIN")));      
					pstmt.setString(3 ,String.valueOf(record.get("RESOURCE")));    
					pstmt.setString(4 ,String.valueOf(record.get("CLASS")));       
					pstmt.setString(5 ,String.valueOf(record.get("HOSTTYPE")));    
					pstmt.setString(6 ,String.valueOf(record.get("HOSTNUM")));     
					pstmt.setString(7 ,String.valueOf(record.get("DESCRIPTION"))); 
					pstmt.setString(8 ,String.valueOf(record.get("SYSTEM")));      
					pstmt.setString(9 ,String.valueOf(record.get("HOSTNAME")));    
					pstmt.setString(10 ,String.valueOf(record.get("IP")));          
					pstmt.setString(11,String.valueOf(record.get("PRODUCT")));     
					pstmt.setString(12,String.valueOf(record.get("DEVEPROD")));    
					pstmt.setString(13,String.valueOf(record.get("SID")));         
					pstmt.setString(14,String.valueOf(record.get("TABLESPACE")));  
					pstmt.setString(15,String.valueOf(record.get("FILESYSNAM")));  
					pstmt.setString(16,String.valueOf(record.get("FILEAPPNUM")));  
					pstmt.setString(17,String.valueOf(record.get("CPUUSED")));     
					pstmt.setString(18,String.valueOf(record.get("MEM")));         
					pstmt.setString(19,String.valueOf(record.get("FILEUSERD")));   
					pstmt.setString(20,String.valueOf(record.get("FILEUSEPER")));  
					pstmt.setString(21,String.valueOf(record.get("SID1")));        
					pstmt.setString(22,String.valueOf(record.get("TABSPAUSED")));  
					pstmt.setString(23,String.valueOf(record.get("TABSPAUSEPER")));  
					pstmt.setString(24,String.valueOf(record.get("SGA")));         
					pstmt.setString(25,String.valueOf(record.get("CPULEFT")));     
					pstmt.setString(26,String.valueOf(record.get("MEMLEFT")));     
					pstmt.setString(27,String.valueOf(record.get("STORAGE")));     
					pstmt.setString(28,String.valueOf(record.get("CFDATE")));     
					pstmt.addBatch();
				}
				opresults = pstmt.executeBatch();
			} catch (SQLException e) {
				opresults=null;
				e.printStackTrace();
			}finally{
				closeConnection(conn);
			}
	return opresults;
}
	
	int[] updateOrders( List updatedList){
			
			int[] opresults=null;
			String sql="UPDATE "+tname+" SET CF_ENV= ?,cf_domain=?,cf_resource=?,cf_class=?,cf_hosttype=?,cf_hostnum=?,cf_description=?,cf_system=?,cf_hostname=?,cf_ip=?,cf_product=?,cf_deveprod=?,cf_sid=?,cf_tablespace=?,cf_filesysnam=?,cf_fileappnum=?,cf_cpuused=?,cf_mem=?,cf_fileuserd=?,cf_fileuseper=?,cf_sid1=?,cf_tabspaused=?,cf_tabspauseper=?,cf_sga=?,cf_cpuleft=?,cf_memleft=?,cf_storage=?,cf_date=? WHERE ROWID=? ";
			
				Connection conn=null;
				PreparedStatement pstmt = null;
				try {
					conn = getConnection();
					pstmt = conn.prepareStatement(sql);
					for (int i=0;i<updatedList.size();i++){
						Map record= (Map)updatedList.get(i);
						pstmt.setString(1,String.valueOf(record.get("ENV")));
						pstmt.setString(2,String.valueOf(record.get("DOMAIN")));      
						pstmt.setString(3,String.valueOf(record.get("RESOURCE")));    
						pstmt.setString(4,String.valueOf(record.get("CLASS")));       
						pstmt.setString(5,String.valueOf(record.get("HOSTTYPE")));    
						pstmt.setString(6,String.valueOf(record.get("HOSTNUM")));     
						pstmt.setString(7,String.valueOf(record.get("DESCRIPTION"))); 
						pstmt.setString(8,String.valueOf(record.get("SYSTEM")));      
						pstmt.setString(9,String.valueOf(record.get("HOSTNAME")));    
						pstmt.setString(10,String.valueOf(record.get("IP")));          
						pstmt.setString(11,String.valueOf(record.get("PRODUCT")));     
						pstmt.setString(12,String.valueOf(record.get("DEVEPROD")));   
						pstmt.setString(13,String.valueOf(record.get("SID")));         
						pstmt.setString(14,String.valueOf(record.get("TABLESPACE")));  
						pstmt.setString(15,String.valueOf(record.get("FILESYSNAM")));  
						pstmt.setString(16,String.valueOf(record.get("FILEAPPNUM")));  
						pstmt.setString(17,String.valueOf(record.get("CPUUSED")));     
						pstmt.setString(18,String.valueOf(record.get("MEM")));         
						pstmt.setString(19,String.valueOf(record.get("FILEUSERD")));   
						pstmt.setString(20,String.valueOf(record.get("FILEUSEPER")));  
						pstmt.setString(21,String.valueOf(record.get("SID1")));        
						pstmt.setString(22,String.valueOf(record.get("TABSPAUSED")));  
						pstmt.setString(23,String.valueOf(record.get("TABSPAUSEPER")));  
						pstmt.setString(24,String.valueOf(record.get("SGA")));         
						pstmt.setString(25,String.valueOf(record.get("CPULEFT")));     
						pstmt.setString(26,String.valueOf(record.get("MEMLEFT")));     
						pstmt.setString(27,String.valueOf(record.get("STORAGE")));     
						pstmt.setString(28,String.valueOf(record.get("CFDATE")));   
						pstmt.setString(29,String.valueOf(record.get("ROWID")));   
						pstmt.addBatch();
					}
					opresults = pstmt.executeBatch();
				} catch (SQLException e) {
					opresults=null;
					e.printStackTrace();
				}finally{
					closeConnection(conn);
				}
		return opresults;
	}
	
	
	int[] deleteOrders( List updatedList){
			
			int[] opresults=null;
			String sql="DELETE FROM "+tname+" WHERE ROWID= ? ";
				Connection conn=null;
				PreparedStatement pstmt = null;
				try {
					conn = getConnection();
					pstmt = conn.prepareStatement(sql);
					for (int i=0;i<updatedList.size();i++){
						Map record= (Map)updatedList.get(i);
						pstmt.setString(1,String.valueOf(record.get("ROWID")));
						pstmt.addBatch();
					}
					opresults = pstmt.executeBatch();
				} catch (SQLException e) {
					opresults=null;
					e.printStackTrace();
				}finally{
					closeConnection(conn);
				}
		return opresults;
	}
	
	boolean saveOrders(List insertedRecords , List updatedList, List deletedRecords){
		//you can control transaction, commit, rollback here
		int[] insertCodes = insertOrders(insertedRecords);
		int[] updateCodes = updateOrders(updatedList);
		int[] deleteCodes = deleteOrders(deletedRecords);
		boolean success=insertCodes!=null && updateCodes!=null && deleteCodes!=null;
		return success;
	}

%>
<%
	request.setCharacterEncoding("UTF-8"); 

	// GridServerHandler is server side wrapper, you can get all the info posted to server in your Java way instead of JavaScript
	GridServerHandler gridServerHandler=new GridServerHandler(request,response);
	
	String operation = request.getParameter("actionMethod");
	String domain = request.getParameter("domain");

	String hostip = (String)session.getAttribute("ssd_hostip");
	String dbport = (String)session.getAttribute("ssd_dbport");
	String dbname = (String)session.getAttribute("ssd_dbname");
	String uname = (String)session.getAttribute("ssd_uname");
	String pwd = (String)session.getAttribute("ssd_pwd");
	String tname = "monitor.si_configure";

	String url = "jdbc:oracle:thin:@"+hostip+":"+dbport+":"+dbname;

	setValue(url,uname,pwd,tname);

	if("save".equals(operation)){

		boolean success=true;
			
		//to get the appended records here. Every record is in a map 
		List insertedRecords = gridServerHandler.getInsertedRecords();
		//to get the updated records here. Every record is in a map 
		List updatedList = gridServerHandler.getUpdatedRecords();
		//to get the deleted records here. Every record is in a map 
		List deletedRecords = gridServerHandler.getDeletedRecords();

	
		// if you are using beanm, you could get records with : xxx.getXXXXXXRecords(Class beanClass);
		//example - List updateList = gridServerHandler.getUpdatedRecords(OrderVO.class);
		
		//to do update delete insert on real database
		success = saveOrders(insertedRecords , updatedList,  deletedRecords );

		
		//set result
		gridServerHandler.setSuccess(success);
		
		//if failure, you could send some message to client 
    //gridServerHandler.setSuccess(false);
    //gridServerHandler.setException("... exception info ...");

		//to print out JSON string to client
		out.print(gridServerHandler.getSaveResponseText());
		
	}else { //client is retrieving data
//		String sql = "select rowid,cf_env,cf_domain,cf_resource,cf_class,cf_hosttype,cf_hostnum,cf_description,cf_system,cf_hostname,cf_ip,cf_product,cf_deveprod,cf_sid,cf_tablespace,cf_filesysnam,cf_fileappnum,cf_cpuused,cf_mem,cf_fileuserd,cf_fileuseper,cf_sid1,cf_tabspaused,cf_tabspauseper,cf_sga,cf_cpuleft,cf_memleft,cf_storage,cf_date from MONITOR.SI_CONFIGURE_TEMP where cf_domain='"+domain+"' order by CF_RESOURCE,CF_CLASS,CF_HOSTTYPE,CF_HOSTNUM,CF_DESCRIPTION,CF_SYSTEM,CF_HOSTNAME,CF_IP,CF_PRODUCT,CF_SID,CF_FILESYSNAM,CF_SID1";
		
		String sql = "select t.*,t.rowid from "+tname+" t";
		if(domain!=null&&!domain.equals("")&&!domain.equals("4"))
			sql = sql + " where t.cf_domain='"+domain+"' ";
		
		List list = listOrders(sql);
		
    //get how many records we are sending
		int totalRowNum= list.size();
		gridServerHandler.setTotalRowNum(totalRowNum);
		
		//if you would like paginal output on server side, you may interested in the following 4 methods
		// gridServerHandler.getStartRowNum() first record no of current page
		// gridServerHandler.getEndRowNum() last record no of current page
		// gridServerHandler.getPageSize() how many records per page holds
		// gridServerHandler.getTotalRowNum() how many records in total
		
		
		// we take map as this sample, you need to use gridServerHelp.setData(list,BeanClass.class); to deal with bean
		gridServerHandler.setData(list);
	  // gridServerHandler.setException("your exception message");
		
		//print out JSON string to client
		out.print(gridServerHandler.getLoadResponseText());
		
		//you could get the posted data by calling gridServerHandler.getLoadResponseText() and obtain more flexibility, such as chaning contentType or encoding of response.
	
		

	}
%>