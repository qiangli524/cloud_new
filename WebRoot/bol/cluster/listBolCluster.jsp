<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" %>
<%@ include file="../../sxcloud/common/view.jsp" %>
<%@	taglib prefix="s" uri="/struts-tags" %>
<head>
    <title></title>
    <link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common_bol.css" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework_bol.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=iblue"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/js/checkbox.js"></script>
    <script type="text/javascript">
	
    $(function(){
		$("#query").click(function(){
			if($("#query-form").is(":visible")){
				$("#query-form").slideUp("slow");
			}else{
				$("#query-form").slideDown("slow");
			}
		});
         $("[name='add']").click(function(){
        	currentEdit=$(this);
    		$.dialog({
    			id:'add',
    			title:'添加集群',
    			width: '500px',
    			height: '300px',
    			max: true,
    		    min: true,
    		    lock:true,
    			content: 'url:bolcluster_add.do'       
    			});
          });
         $("[name='mod']").click(function(){
        	currentEdit=$(this);
        	var id ='';
        	var lenth = 0;
        	$('[name=theForm.id]:checkbox:checked').each(function(){
        		id +=$(this).val();
        		lenth +=1;
        	 });
        	if(id==null || id ==''){
				alert('请先选择一项进行修改');
				return false;
			}else if(lenth>1){
				alert('只能选择一项进行修改');
				return false;
			}
    		$.dialog({
    			id:'vdi',
    			title:'修改集群信息',
    			width: '500px',
    			height: '300px',
    			max: true,
    		    min: true,
    		    lock:true,
    			content: 'url:bolcluster_mod.do?clusterId='+id
    		});
         });
         $("#reset").click(function(){
         	clear();
         });
       });
	  	function searchRequest(){
			theForm.submit();
		}
		function deleteCluster(){
			var id='';
	 		var lenth=0;
			$('[name=theForm.id]:checkbox:checked').each(function(){
	    		id +=$(this).val();
	    		lenth +=1;
	    	 });
	    	if(id==null || id ==''){
				alert('请先选择一项进行删除');
				return false;
			}else if(lenth>1){
				alert('只能选择一项进行删除');
				return false;
			}
			theForm.action="bolcluster_del.do?clusterId="+id;
			theForm.submit();
		}
		function clear(){
			$("#name").attr("value","");
			$("#status").attr("value","-1");
		}
		function resetForm(){
			$("#name").attr("value","");
			$("#status").attr("value","-1");
		}
		
    </script>
</head>
<body class="pop-body scrollbody">
    <s:form action="bolcluster_listBolCluster.do" method="post" id="theForm" cssStyle="theForm">
	<div class="query">
		<div class="title"><%=getImageTag(request,"query-icon.gif")%></div>
	</div>
	<div class="box on">
		<div class="query-form">
					<table width="100%" class="querytable" border="0">
	                	<tr>
                    <td class="til">集群名称:</td>
                    <td>
						<s:textfield name="theForm.name" id="name" maxlength="100" style="width:150px;   height:18px;"></s:textfield>
                    </td>
                    <td class="til">集群状态:</td>
                    <td>
						<s:select name="theForm.status" list="#{'-1':'-请选择-','1':'正常','0':'异常'}"  id="status" style="width:150px;   height:20px;"></s:select>
					</td>
                    </tr>
                  <tr>
                    <td colspan="8" class="btns">
                        <div>
							<input type = "button" class="btn-style02" value = "查询" onclick = "javascript:searchRequest()" />
							<input type = "button" class="btn-style02" value = "重置" onclick = "javascript:resetForm()" />
                        </div>
                    </td>
                  </tr>
	                </table>
			</div>
      <div class="blue-wrap noborder">
      				<div class="table-head">
      					<ul class="btns">
		                    <li colspan="4" class="btnCenter">
		                        <input type="button"  value="添加"  class="btn-style02" name="add"/>
		                        <input type="button"  value="修改" name="mod" class="btn-style02"/>
		                        <input type="button"  value="删除" name="" class="btn-style02"  onclick="javascript:if(confirm('确定删除？'))deleteCluster();"/>
		                    </li>
		                </ul>
						<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
					</div>
       <div class="table-ct" >
            <table id="theTable" width="100%" border="0" cellspacing="0" class="blue-table sorttable">
            	<thead id="table">
					<tr>
						<th>选择</th>
						<th onclick="sort(theTable,0,'string')">集群名称</th>
						<th onclick="sort(theTable,1,'string')">集群状态</th>
						<th onclick="sort(theTable,2,'string')">集群描述</th>
						<th onclick="sort(theTable,3,'string')">修改日期</th>
					</tr>
				</thead>
                <tbody>
                	<s:iterator value="resultList" id="theBean">
                		<tr >
                			<td>
                				<input name="checkboxid" type="checkbox" value="<s:text name="#theBean.id"/>"/>
                			</td>
                			<td>
                				<s:property value="#theBean.name"/>
                			</td>
                			<td>
                				<s:if test="#theBean.status==0">
                					<span style="color:red;">异常</span>   
                				</s:if>
                				<s:elseif test="#theBean.status==1">
                					<span style="color:green;">正常</span>   
                				</s:elseif>
                			</td>
                			<td>
                				<s:property value="#theBean.descrip"/>
                			</td>
                			<td>
                				<s:property value="#theBean.lastUpdate"/>
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