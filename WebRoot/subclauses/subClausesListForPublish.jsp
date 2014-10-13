<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp" %>
<%@ include file="../../sxcloud/common/link_export.jsp"%>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/thickbox.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/js/checkbox.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/contextmenu.r2.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/ui16rc5.packed.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/ui/ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath }/js/My97DatePicker/WdatePicker.js"></script>
<%@ include file="../../sxcloud/common/view.jsp"%>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>
<head>

<title></title>
<script type="text/javascript">
	   $(function(){
		 	$(".query").click(function(){
    			if($(".query-form").is(":visible")){
    				$(".query-form").slideUp("slow");
    			}else{
    				$(".query-form").slideDown("slow");
    			}
			});
			
	    //添加条目
		$("#addSubClauses").click(function() {
		$.dialog({
		 	id:'addSubClauses',
		 	title:'添加条目',
		 	width: '800px',
		 	height: '500px',
		 	content: 'url:service_viewSubClause.do?viewType=1'
		 	}); 
		});
		
		//修改条目
		$("#viewSubClauses").click(function() {
			var couterNum = 0;
			var checkid;
			var checkboxids = document.getElementsByName("checkboxid");
			if (checkboxids != null && checkboxids.length > 0) {
				for ( var i = 0; i < checkboxids.length; i++) {
					if (checkboxids[i].checked) {
						couterNum = couterNum + 1;
						checkid = $(checkboxids[i]).attr("id");
					}
				}
			}
			if (couterNum != 1) {
				$.dialog({title: '提示',content: '请勾选一条需要修改的记录',width:200,height:80,ok:true,min:false,max:false});
			}else{
				$.dialog({
		 			id:'viewSubClauses',
		 			title:'修改条目',
		 			width: '800px',
		 			height: '500px',
		 			content: 'url:service_viewSubClause.do?viewType=2&checkid='+checkid
		 		}); 
			}
		});
		
		//删除条目
		$("#deleteSubClauses").click(function() {
			var couterNum = 0;
			var checkboxids = document.getElementsByName("checkboxid");
			if (checkboxids != null && checkboxids.length > 0) {
				for ( var i = 0; i < checkboxids.length; i++) {
					if (checkboxids[i].checked) {
						couterNum = couterNum + 1;
						checkid = $(checkboxids[i]).attr("id");
					}
				}
			}
			if (couterNum == 0) {
				$.dialog({title: '提示',content: '请勾选一条信息',width:200,height:80,ok:true,min:false,max:false});
			}else{
				$.dialog.confirm('确定要删除?', function(){
					$.ajax({
			              type: "post",
		                  url: "service_deleteSubClause.do?idstr="+checkid,
				          dataType:"text",
                          async: true,
                          cache: false,
			              success : function(data){
				          }
		            });
		    		searchRequest();
				});
			}
		});
		
		

	    //重置表单
		$("#resetButton").click(function() {
			$("#state").val("-1");
			$("#id").val("");
			$("#name").val("");
			$("#indate").val("");
			$("#role").val("");
			$("#resource_name").val("");
			$("#publish_state").val("");
		});

		//全选 
		$("#selectall").click(function() { 
			$("input[type='checkbox']").attr("checked", true);
		});
	});
	
	function saveInfo(url){
			$.ajax({
			      type: "post",
		          url: url,
				  dataType:"json",
                  async: true,
                  cache: false,
			      success : function(data){
				  }
		    });
		searchRequest();
	}
	    
	function searchRequest() {
		theForm.submit();
	}
	
	function approve(str){
	 var stats = str.split(",");
	 var msg = "";
	 if(stats[1]==1){
	   msg = "此条目为已发布，确定要取消发布？";
	 }else{
	   msg = "确定要发布此条目？";
	 }
	 $.dialog.confirm(msg, function(){
			$.ajax({
			      type: "post",
		          url: "service_publishState.do?clauseId="+str,
				  dataType:"text",
                  async: true,
                  cache: false,
			      success : function(data){
				  }
		    });
		    searchRequest();
	 });
	}
</script>
</head>
<body class="scrollbody">
<s:form action="service_subClausesListForPublish.do" method="post" cssStyle="theForm" id="theForm">
<s:hidden name="theForm.acType" id="acType" />
<s:hidden name="theForm.resource_id" id="resource_id" />
<div>
	<div class="query">
		<div class="title"><%=getImageTag(request,"query-icon.gif")%></div>
	</div>
	<div class="box on">
        <div class="query-form">
                <table width="100%" class="querytable" border="0">
                  <tr>
                  	<td class="til">状态:</td>
                    <td><s:select id="state"  name="theForm.state" list="#{'-1':'--请选择--','0':'无效','1':'有效'}"></s:select></td>
                    <td class="til">服务编码:</td>
                    <td><s:textfield name="theForm.id" id="id" maxlength="20" cssStyle="txt"/></td>
                    <td class="til">服务名称:</td>
                    <td><s:textfield name="theForm.name" id="name" maxlength="30" cssStyle="txt"/></td>
	                <td class="til">有效时间:</td>
                    <td><s:textfield name="theForm.indate" id="indate" maxlength="30" cssStyle="txt"  onFocus="WdatePicker({minDate:'1900-01-01',maxDate:'2100-10-01',dateFmt:'yyyy-MM-dd '})"/></td>
                  </tr>
                  <tr>
                  <!--  	<td class="til">权限:</td>
                    <td><s:textfield name="theForm.role" id="role" maxlength="20" cssStyle="txt"/></td>
                  -->
                  <!--  <td class="til">资源模板编码:</td>
                    <td><s:textfield name="theForm.resource_id" id="resource_id" maxlength="20" cssStyle="txt"/></td>
                  --> 
                    <td class="til">资源模板名称:</td>
                    <td><s:textfield name="theForm.resource_name" id="resource_name" maxlength="30" cssStyle="txt"/></td>
                  	<td class="til">发布状态:</td>
                    <td><s:select id="state"  name="theForm.publish_state" list="#{'':'全部','1':'已发布'}"></s:select></td>
                  </tr>
                  <tr>
                    <td colspan="8" class="btns" >
                        <div>
							<input type="button" class="thickbox btn-style02" value = "查询"  onclick="searchRequest();"  />
							<input id="resetButton" type = "button" class="btn-style02" value = "重置"   />
                        </div>
                    </td>
                  </tr>
                </table>
        </div><!--query-form end -->
	
	<div class="blue-wrap noborder">
		<div class="table-head">
			<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
		</div>
		<div class="table-ct">
			<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
			  <thead>
			  <tr>
				   <th onclick="sort(theTable,0,'string')">服务编码</th>
				   <th onclick="sort(theTable,1,'string')">服务名称</th>
                   <!--<th onclick="sort(theTable,2,'string')">服务权限</th>-->
                   
                   <th onclick="sort(theTable,4,'string')">资源模板类别</th>
                   <th onclick="sort(theTable,5,'string')">资源模板名称</th>
                   <th onclick="sort(theTable,6,'string')">资源规格</th>
                   <th onclick="sort(theTable,3,'string')">服务说明</th>
	               <th onclick="sort(theTable,7,'string')">状态</th>
	               <th onclick="sort(theTable,8,'string')">有效时间</th>
	               <th onclick="sort(theTable,9,'string')">发布状态</th>
	               <th>操作</th>
			  </tr>
			  </thead>
			  <tbody>
				 <s:iterator value="theForm.resultList"   id="theBean">	
						<tr>
							<td><s:property value="#theBean.id"/></td>
							<td><s:property value="#theBean.name"/></td>
							<!--<td><s:property value="#theBean.role"/></td>-->
							
							<td>
								<s:if test="#theBean.resource_type==1">虚拟模板</s:if>
							    <s:else>其他</s:else>
							</td>
							<td><s:property value="#theBean.resource_name"/></td>
 							<td><s:property value="#theBean.resource_info"/></td>
 							<td><s:property value="#theBean.declare"/></td>
                            <td>
                            	<s:if test="#theBean.state==0">无效</s:if>
							    <s:elseif test="#theBean.state==1">有效</s:elseif>
							    <s:else>未选择</s:else>
                            </td>
                            <td><s:property value="#theBean.indate"/></td> 
                            <td>
                            	<s:if test="#theBean.publish_state==0">未发布</s:if>
							    <s:elseif test="#theBean.publish_state==1">已发布</s:elseif>
							    <s:else>未选择</s:else>
                            </td>
                             <td><a href="javascript:approve(<s:property value="#theBean.id"/>+','+<s:property value="#theBean.publish_state"/>)" class="viewMore" >操作</a></td> 
						</tr>
				 </s:iterator>
			  </tbody>
			</table>
		</div>
	</div><!--blue-wrap end -->
    </div><!--box end -->
</div>
</s:form>
</body>