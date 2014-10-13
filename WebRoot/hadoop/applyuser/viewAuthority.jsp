<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" %>
<%@	taglib prefix="s" uri="/struts-tags" %>
<head>
    <title></title>
    <link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
    <script type="text/javascript">
   	 	var api = frameElement.api;
		var w = api.opener;
	   $(function(){
		   var entity_id = "";
		   var serviceType = "";
		   var clusterId = "";
		   
		   
		  var groupId = $("#groupId").val(); 
		  if (groupId.length == 0) {
			  theForm.action = "hadoopUser_viewAuthority.do";
				entity_id = $("#userId").val();
				serviceType = $("#userServiceType").val();
				clusterId = $("#userClu").val();
		  } else {
			  theForm.action = "hadoopUserGroup_viewAuthority.do";
				entity_id = $("#groupId").val();
				serviceType = $("#groupServiceType").val();
				clusterId = $("#groupClu").val();
		  }
		   
		   $("#searchForm").click(function(){
			   $("#theForm").submit();
		   });
		   
		   $("#resetForm").click(function(){
			   $("#authpath").val(null);
		   });
		   
		   $("[name='delAuth']").unbind().live("click",function(){
			   var $td = $(this).parent();
			   var authId = $td.attr("authId");
			   var url = "";
			   if (groupId.length == 0) {
					url = 'url:hadoopUser_editAuthority.do?hadoopUserObj.id='+entity_id+'&hadoopUserObj.service_type='+serviceType
							+'&hadoopUserObj.cluster_id='+clusterId+'&authPath='+authId;
			   } else {
				   url = 'url:hadoopUserGroup_editAuthority.do?hadoopUserGroup.id='+entity_id+'&hadoopUserGroup.service_type='+serviceType
							+'&hadoopUserGroup.cluster_id='+clusterId+'&authPath='+authId;
			   }
			   
			   w.$.dialog({
	        		id:'editStra',
	        		title:'修改权限',
	        		width: '500px',
	    			height: '300px',
	    		    lock:true,
	    		    content:url
	        	});
		   });
	   });
	   
	   function delPath(authId,entity_id,serviceType,clusterId,autho,flag){
		   var url ="";
		   if (flag == "user") {
			   url = 'hadoopUser_delAuthority.do?hadoopUserObj.id='+entity_id+'&hadoopUserObj.service_type='+serviceType
				+'&hadoopUserObj.cluster_id='+clusterId+'&authPath='+authId+'&autho='+autho;
		   } else {
			   url = 'hadoopUserGroup_delAuthority.do?hadoopUserGroup.id='+entity_id+'&hadoopUserGroup.service_type='+serviceType
					+'&hadoopUserGroup.cluster_id='+clusterId+'&authPath='+authId+'&autho='+autho;
		   }
		   $.ajax({
	  			type:'post',
	  			url:url,
	  			success:function (msg){
	  				$("#theForm").submit();
	  			}
	  		});
	   }
    </script>
</head>
<body class="pop-body scrollbody">
    <s:form action="" method="post" id="theForm" cssStyle="theForm">
    	<s:hidden name="hadoopUserGroup.id" id="groupId"></s:hidden>
    	<s:hidden name="hadoopUserGroup.service_type" id="groupServiceType"></s:hidden>
    	<s:hidden name="hadoopUserGroup.cluster_id" id="groupClu"></s:hidden>
    	<s:hidden name="hadoopUserObj.id" id="userId"></s:hidden>
    	<s:hidden name="hadoopUserObj.service_type" id="userServiceType"></s:hidden>
    	<s:hidden name="hadoopUserObj.cluster_id" id="userClu"></s:hidden>
		<div class="box on">
			<div class="query-form">
						 <table width="100%" class="querytable" border="0">
			                  <tr>
			                  		<td class="til">路径:</td>
			                  		<td>
			                  			<s:textfield name="authPath" id="authpath"></s:textfield> 
			                  		</td>
			                  </tr>
			                  <tr>
			                    <td colspan="10" class="btns">
			                        <div>
										<input type = "button" class="thickbox btn-style02" value = "查询" id="searchForm" />
										<input type = "button" class="btn-style02" value = "重置" id="resetForm" />
			                        </div>
			                    </td>
			                  </tr>
		                </table>
				</div>
		      <div class="blue-wrap noborder">
		      		<div class="table-head">
						<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
					</div>
			       <div class="table-ct" >
			            <table id="theTable" width="100%" border="0" cellspacing="0" class="blue-table sorttable">
			            	<thead id="table">
								<tr>
									<th onclick="sort(theTable,0,'string')">路径</th>
									<th>权限</th>
									<th>权限变更</th>
								</tr>
							</thead>
			                <tbody>
			                	<s:iterator value="auList" id="theBean">
			                		<tr >
			                			<td>
			                				<s:property value="#theBean.path"></s:property>
			                			</td>
			                			<td>
			                				<s:property value="#theBean.authority"/>
			                			</td>
			                			<td authId="<s:property value='#theBean.id'/>">
			                				<a href="javascript:;" name="delAuth">
			                					变更
			                				</a>
			                			</td>
			                		</tr>
			                	</s:iterator>
			                </tbody>
			            </table>
			        </div>
		       </div>
	    </div>
    </s:form>
</body>