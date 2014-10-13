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
		     callback:selectClu,
		     focus: true
		 },
		 {
		     id:'cancle',
		     name: '取消'
		 });
	});

   function selectClu(){
	   var cluname = "";
	   var connectid = "";
	   var cluid = "";
	   $(":checkbox:checked").each(function(){
		   cluname = $(this).attr("cluname");
		   connectid = $(this).attr("connectid");
		   cluid = $(this).attr("cluid");
	   });
	   var oper = $("#oper").val();
	   if (oper == "add") {
		   api.get("addResource").chooseCluster(cluname,connectid,cluid);
		} else {
		   api.get("editResource").chooseCluster(cluname,connectid,cluid);
		}
   }
   
   $(function(){
	   $("#searchForm").click(function(){
		   $("#theForm").submit();
	   });
	   
	   $("#resetForm").click(function(){
		   $("#entityname").val(null);
		   $("#vtype").val("0");
	   });
   });
    </script>
</head>
<body class="pop-body scrollbody">
    <s:form action="workorder/workorder_selectCluster.do" method="post" id="theForm" cssStyle="theForm">
    <s:hidden name="oper" id="oper"></s:hidden>
	<div class="box on">
		<div class="query-form">
					 <table width="100%" class="querytable" border="0">
		                  <tr>
		                  		<td class="til">集群名称:</td>
		                  		<td>
		                  			<s:textfield name="entityname" id="entityname"></s:textfield> 
		                  		</td>
		                  		<td class="til">虚拟化类型:</td>
		                  		<td>
		                  			<s:select list="#{'0':'请选择','1':'vmware','2':'xen' }" name="vtype" id="vtype"></s:select>
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
       <div class="table-ct" >
            <table id="theTable" width="100%" border="0" cellspacing="0" class="blue-table sorttable">
            	<thead id="table">
					<tr>
						<th>选择</th>
						<th onclick="sort(theTable,1,'string')">集群名称</th>
						<th onclick="sort(theTable,2,'string')">虚拟化类型</th>
						<th onclick="sort(theTable,3,'string')">所属数据中心</th>
					</tr>
				</thead>
                <tbody>
                	<s:iterator value="unitedTreeList" id="theBean">
                		<tr >
                			<td>
                				<input type="checkbox" cluid='<s:property value="#theBean.id" />' 
                				connectid='<s:property value="#theBean.connect_id" />'
                				cluname='<s:property value="#theBean.name" />'/>
                			</td>
                			<td>
                				<s:property value="#theBean.name"></s:property>
                			</td>
                			<td>
                				<s:if test="#theBean.vtype==1">
									vmware
								</s:if>
								<s:elseif test="#theBean.vtype==2">
									xen
								</s:elseif>
                			</td>
                			<td>
								<s:property value="#theBean.parent_uuid" />
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