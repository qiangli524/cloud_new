<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../common/taglib.jsp"%>
<%@ include file="../../common/link.jsp"%>
<%@ include file="../../common/view.jsp"%>
	<head>
		<link href="sxcloud/cjs/ui2/nresources/common/css/default.css"
			rel="stylesheet" type="text/css" />
		<link href="sxcloud/cjs/ui2/nresources/common/css/location_tj.css"
			rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="sxcloud/cjs/ui2/njs/changeColor.js"></script>
		<script type="text/javascript">
  function modVirtual(VH_ID)
  {
    theForm.VH_ID.value=VH_ID;
    theForm.action = 'virpool_modVirtualFromPool.do?VH_ID' + theForm.VH_ID.value; 
	theForm.submit();
  }
  function delVirtual(VH_ID){
  	
  		theForm.VH_ID.value=VH_ID;
   		if(confirm("确定要删除该虚拟机吗！") == true){
  			var url = "virpool_checkVirtualCanDel.do?VH_ID="+VH_ID+"&Date"+(new Date());
  			$.getJSON(url,function(data){
  				if(data=="NO"){
  					alert("请先删除虚拟机里的用户配置！");
  				}else if(data=="YES"){
  					theForm.action = "virpool_delVirtualFromPool.do"; 
	  				theForm.submit();
  				}
  			}
  		)
  	} 
    
  }
  
  function addVirtual(){
     theForm.action = 'virpool_addVirtualToPool.do'; 
	 theForm.submit();
  }
   function searchRequest() {
		theForm.submit();
 	}
</script>
	</head>
	<body>
		<div class="scrollbody">
		<s:form action="virpool_listVirtualPool.do" method="post" cssClass="theForm" id="theForm">
				<div class="gray-area">
					<table width="100%" class="" border="0">
						<tr>
							<td>
								<a href="javascript:addVirtual()">增加</a>
							</td>
							<td width="">
								虚拟机编号:
								<s:textfield name="theForm.VH_ID" cssClass="txt" id="VH_ID"></s:textfield>
							</td>
							<td width="">
								虚拟机名称:
								<s:textfield name="theForm.VH_NAME" cssClass="txt" id="VH_NAME"></s:textfield>
							</td>
							<td width="">
								<input type="button" class="thickbox btn-style02" value="查询"
									onclick="javascript:searchRequest()" />
							</td>
						</tr>
					</table>
				</div>
				
				<!--主机列表 start -->

				<table>
					<tr>
						<td>
							<div>
								<div class="tit-001">IBM虚拟机</div>
		  							<ul class="compose-1">
		  							<s:iterator value="theForm.resultList" id="theBean">
											<li>
												<h1>

													<span class="tool_bar"> 
													<a
														href="virpool_listVirPoolConfig.do?VH_ID=<s:property value="#theBean.VH_ID"/>"><img
																src="sxcloud/cjs/ui2/nresources/common/icon/icon-user.gif"
																alt="虚拟机用户管理" width="10" height="10" />
													</a> <a
														href="javascript:delVirtual('<s:property value="#theBean.VH_ID"/>')"><img
																src="sxcloud/cjs/ui2/nresources/common/icon/icon-del.gif"
																alt="虚拟机删除" width="10" height="10" />
													</a> <a
														href="javascript:modVirtual('<s:property value="#theBean.VH_ID"/>')"><img
																src="sxcloud/cjs/ui2/nresources/common/icon/icon-set.gif"
																alt="虚拟机修改" width="10" height="10" />
													</a> 
													</span> IBM虚拟机<!-- (
													<bean:write name="theBean" property="VH_NAME" />
													) -->
												</h1>
												<div class="main">
													<div class="left-c">
														<img
															src="sxcloud/cjs/ui2/nresources/common/images/host-icon-1.gif"
															width="30" height="80" />
													</div>
													<div class="right-c" >
														<p style="overflow:hidden; text-overflow:ellipsis; white-space:nowrap;" title = "<s:property value="#theBean.VH_ID"/>">
															虚拟机编号:
															<s:property value="#theBean.VH_ID"/>
														</p>
														<p style="overflow:hidden; text-overflow:ellipsis; white-space:nowrap;" title = "<s:property value="#theBean.VH_NAME"/>">
															虚拟机名称：
															<s:property value="#theBean.VH_NAME"/>
														</p>
														<p style="overflow:hidden; text-overflow:ellipsis; white-space:nowrap;" title = "<s:property value="#theBean.eq_id"/>" >
															所属物理主机编号：
															<s:property value="#theBean.eq_id"/>
														</p>
														<p style="overflow:hidden; text-overflow:ellipsis; white-space:nowrap;" title = "<s:property value="#theBean.VH_IP"/>">
															虚拟机IP：
															<s:property value="#theBean.VH_IP"/>
														</p>
														<p>
															监控类型:
															<s:if test='#theBean.PROTOCOL=="ssh"'>
																ssh
															</s:if>
					            							<s:if test='#theBean.PROTOCOL=="telnet"'>
					            								telnet
					            							</s:if>
														</p>
														<p>
															端口:
															<s:property value="#theBean.PORT"/>
														</p>
													</div>
												</div>
											</li>
								</s:iterator>
								</ul>
							</div>
						</td>
					</tr>
				</table>
				<div class="clear"></div>
			</s:form>
			</div>
	</body>
