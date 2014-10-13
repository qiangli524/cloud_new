<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" %>
<%@	taglib prefix="s" uri="/struts-tags" %>
<head>
    <title></title>
     <style type="text/css">
		.font-more{ width:140px;height:20px;line-height:20px;overflow: hidden;
					white-space: nowrap;
					display: block;
					-o-text-overflow: ellipsis; 
					text-overflow: ellipsis;}
    </style>
    <link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
    <script type="text/javascript">
    $(function(){
		$check = $(":checkbox");
		$check.unbind().live("click",function(){
			$check.not(this).attr("checked",false);
		});
	})
 	var api = frameElement.api;
	var w = api.opener;
	$(function(){
		 api.button({
		     id:'OkAnd',
		     name: '确定',
		     callback:selectTem,
		     focus: true
		 },
		 {
		     id:'cancle',
		     name: '取消'
		 });
	});

   function selectTem(){
	   var temid = "";
	   var connectid = "";
	   var temtype = "";
	   var cpunum = "";
	   var memsize = "";
	   var storesize = "";
	   var temname = "";
	   $(":checkbox:checked").each(function(){
		   temid += $(this).attr("templateCode");
		   connectid += $(this).attr("connectid");
		   temtype += $(this).attr("temtype");
		   cpunum += $(this).attr("cpunum");
		   memsize += $(this).attr("memsize");
		   storesize += $(this).attr("storesize");
		   temname += $(this).attr("temname");
	   });
	   if (temtype == 1) {
			temtype = "HOST";
	   } else {
		   temtype = "VM";
	   }
	   var oper = $("#oper").val();
	   if (oper == "add") {
		   api.get("addResource").selectTem(temid,connectid,temtype,cpunum,memsize,storesize,temname);
		} else {
			 api.get("editResource").selectTem(temid,connectid,temtype,cpunum,memsize,storesize,temname);
		}
   }
   
   $(function(){
	   $("#searchForm").click(function(){
		   $("#theForm").submit();
	   });
	   
	   $("#resetForm").click(function(){
		   $("#temmanname").val("");
	   });
   });
    </script>
</head>
<body class="pop-body scrollbody">
    <s:form action="workorder_selectTemMan.do" method="post" id="theForm" cssStyle="theForm">
    <s:hidden name="oper" id="oper"></s:hidden>
	<div class="box on">
			<div class="query-form">
					 <table width="100%" class="querytable" border="0">
		                  <tr>
		                  		<td class="til">模板名称:</td>
		                  		<td>
		                  			<s:textfield name="temManObj.name" id="temmanname"></s:textfield> 
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
						<th>选择</th>
						<th onclick="sort(theTable,1,'string')">名称</th>
						<th onclick="sort(theTable,2,'string')">类型</th>
						<th onclick="sort(theTable,3,'string')">CPU个数</th>
						<th onclick="sort(theTable,4,'string')">内存大小</th>
						<th onclick="sort(theTable,5,'string')">存储大小</th>
						<th onclick="sort(theTable,6,'string')">是否公有</th>
						<th onclick="sort(theTable,7,'string')">是否物理模板</th>
						<th onclick="sort(theTable,8,'string')">模板说明</th>
					</tr>
				</thead>
                <tbody>
                	<s:iterator value="temList" id="theBean">
                		<tr>
                			<td>
                				<input type="checkbox" temid="<s:property value="#theBean.id"/>"  id="id" templateCode="<s:property value="#theBean.templateCode"/>"
                					temtype='<s:property value="#theBean.isPhysical"/>' connectid="<s:property value="#theBean.connectId"/>"
                					cpunum='<s:property value="#theBean.cpu"/>' memsize='<s:property value="#theBean.mem"/>' 
                					storesize='<s:property value="#theBean.store"/>' temname='<s:property value="#theBean.name"/>'/>
                			</td>
                			<td align="center">
	                			<s:property value="#theBean.name"/>
                			</td>
                			<td>
                				<s:if test="#theBean.type==1">Vmware模板</s:if>
                				<s:elseif test="#theBean.type==2">Xen模板</s:elseif>
                				<s:else>OVF模板</s:else>
                			</td>
                			<td>
                				<s:property value="#theBean.cpu"/>核
                			</td>
                			<td>
                				<s:property value="#theBean.mem"/>M
                			</td>
                			<td>
                				<s:property value="#theBean.store"/>M
                			</td>
                			<td>
								<s:if test="#theBean.isPublic==0">公有</s:if>
								<s:else>私有</s:else>
                			</td>
                			<td>
                				<s:if test="#theBean.isPhysical==1" >是</s:if>
                				<s:else>否</s:else>
                			</td>
                			<td align="center">
	                			<s:if test="#theBean.remark==null || #theBean.remark==''">--</s:if>
	                			<s:else>
	                				<span style="color:black;" class="font-more" title='<s:property value="#theBean.remark"/>'>
	                					<s:property value="#theBean.remark"/>
                					</span>
	                			</s:else>
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