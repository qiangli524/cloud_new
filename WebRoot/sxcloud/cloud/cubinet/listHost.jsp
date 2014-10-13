<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../common/taglib.jsp"%>
<%@ include file="../../common/link.jsp"%>
<%@ include file="../../common/view.jsp"%>

<html locale="true">
	<head>
		<link href="<%=request.getContextPath() %>/sxcloud/cjs/ui2/nresources/common/css/default.css"
			rel="stylesheet" type="text/css" />
		<link href="<%=request.getContextPath() %>/sxcloud/cjs/ui2/nresources/common/css/location_tj.css"
			rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<%=request.getContextPath() %>/sxcloud/cjs/ui2/njs/changeColor.js"></script>
		<script type="text/javascript">
  
  function configHost(){
  	 alert("do something config!");
  	 //listHostConfig.do
  }
  function modhost(hostid)
  {
    theForm.eq_id.value=hostid;
    theForm.action = 'modHost.do' 
	theForm.submit();
  }
  function delHost(hostid){
     theForm.eq_id.value=hostid;
     if(confirm("确定要删除该主机吗！") == true)
     {
        theForm.action = "delHost.do"; 
	    theForm.submit();
     }  
  }
  function addHost(){
     theForm.action = 'addHost.do' 
	 theForm.submit();
  }
   function searchRequest() {
   		var hostip=document.theForm.eq_ip.value;
   		if(!isnumber(hostip)){
   			alert("管理IP输入不合法,只能为.和数字!");
   			return false;
   		}
		theForm.submit();
 	}
 	/* 检测输入的字符串是否符合要求 */
 	function isnumber(str)
	 {
	  var number_chars = "1234567890.";
	        var i;
	        for (i=0;i<str.length;i++)
	        {
	            if (number_chars.indexOf(str.charAt(i))==-1){
	            	
	            	document.theForm.IP.value='';
	            	document.theForm.IP.focus;
	            	return false;
	            }
	        }
	        return true;
	 }
	 function searchHost(cq_id){
	 theForm.cq_id.value=cq_id;
    theForm.action = 'cubinet_checkHostList.do' 
	theForm.submit();
	 }
	 function goback(){
	 	theForm.action = 'cubinet_getCubinetHostList.do' 
		theForm.submit();
	 }
</script>
	</head>
	<body>
		<div class="query-form">
                <table width="100%" class="querytable" border="0">
                <tr>
                    <td colspan="12" class="btns">
                    	<a href="javascript:goback();" >返回机柜列表</a>
                    </td>
                  </tr>
                </table>
                
        </div>
		<div class="scrollbody">
			<s:form action="cubinet_checkHostList.do" method="post" id="theForm">
				<div>
					<table>
						<tr>
							<td>
									<div class="tit-zzi">
									<s:text name="theForm.c_name"/>(<s:text name="theForm.c_addr" />)
								</div>
									<ul class="compose-1">
											<s:iterator id="theBean" value="theForm.list">
												<li>
													  <h1>
													  <s:text name="#theBean.eq_name"  />
													</h1>
													<div class="main">
														<div class="left-c">
														<s:if test="theForm.c_type==1"><img
																src="<%=request.getContextPath() %>/sxcloud/cjs/ui2/nresources/common/images/host-icon-1.gif"
																width="30" height="80" /></s:if>
														<s:if test="theForm.c_type==2"><img
																src="<%=request.getContextPath() %>/sxcloud/cjs/ui2/nresources/common/images/host-icon-2.gif"
																width="30" height="80" /></s:if>
														<s:if test="theForm.c_type==3"><img
																src="<%=request.getContextPath() %>/sxcloud/cjs/ui2/nresources/common/images/host-icon-3.gif"
																width="30" height="80" /></s:if>
														<s:if test="theForm.c_type==4"><img
																src="<%=request.getContextPath() %>/sxcloud/cjs/ui2/nresources/common/images/host-icon-4.gif"
																width="30" height="80" /></s:if>
														</div>
														<div >
															<p>
																主机编号:
																<s:text name="#theBean.eq_id"  />
															</p>
															<p>
																主机类型：
																<s:text name="#theBean.eq_type"  />
															</p>
															<p>
																主机IP：
																<s:text name="#theBean.eq_ip"  />
															</p>
															<p>
																是否可以虚拟化:
																<s:if test="#theBean.hasvertual==1">支持</s:if>
																<s:if test="#theBean.hasvertual!=1">不支持</s:if>
															</p>
															<p>
																注册时间:
																<s:if test="#theBean.ins_date!=null"><s:text name="#theBean.ins_date" /></s:if>
																
															</p>
														</div>
													</div>
												</li>
											</s:iterator>
									</ul>
							</td>
						</tr>
					</table>
				</div>
				<div class="clear"></div>
			</s:form>
		</div>
	</body>
</html>

