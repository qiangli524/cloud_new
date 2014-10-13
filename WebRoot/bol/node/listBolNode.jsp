<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" %>
<%@ include file="../../sxcloud/common/view.jsp" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
	<title></title>
    <link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common_bol.css" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework_bol.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=iblue"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/js/checkbox.js"></script>
    <script type="text/javascript">
    
    function searchRequest(){
    	bolNodeVO.submit();
    }
    
    function resetForm(){
    	$("#name").attr("value","");
    	$("#ipaddress").attr("value","");
    	$("#clusterName").attr("value","");
    }
  	//添加
    function add() {
 	    $.dialog({
 			id:'add',
 			title:'增加信息',
 			height:'400px',
 			width:'500px',
 			lock:true,
 			content:'url:bolnode_add.do'
 		});
 	}
 	//修改
    function mod(){
 	    var lenth = 0;
 	    var id = '';
 	    $('[name=checkboxid]:checkbox:checked').each(function(){
     		id +=$(this).val();
     		lenth +=1;
     	 });
 	    if(id==null || id == ''){
 	    	alert("请勾选一条信息！");
 	 	    return false ;
 	    }else if(lenth>1){
 	   		alert("一次只能修改一条信息");
 	    return false ;
 	    }
 	   $.dialog({
 			id:'update',
 			title:'修改信息',
 			height:'400px',
 			width:'500px',
 			lock:true,
 			content:'url:bolnode_mod.do?bolNodeVO.id='+id
 		});
    }
 	//删除
    function del(){
 		var id='';
 		var lenth=0;
 		$('[name=checkboxid]:checkbox:checked').each(function(){
    		id +=$(this).val();
    		lenth +=1;
 	   	 });
 	   		if(id==null || id ==''){
 	   			alert("请勾选一条信息！");
 				return false;
 			}else if(lenth>1){
 				alert('只能选择一项进行删除');
 				return false;
 			}
 	   		$.dialog.confirm('你确定要删除吗？', function(){
 				var url = "bolnode_del.do?bolNodeVO.id="+id;
 				$.ajax({
 			  		type:"POST",
 	          		url:url,
 	          		async: false,
 	          		cache: false,
 	          		success: function(msg){
 	          			searchRequest();	
 	          		}
 				});
 	   		});
 	}
 	
 	function saveBolNode(url,data){
 		$.ajax({
		  	type:"POST",
       		url:url,
       		data:data,
       		async: false,
       		cache: false,
       		success: function(msg){
       			searchRequest();	
       		}
			});
 	}
    </script>
</head>
<body class="pop-body scrollbody">
<s:form action="bolnode_listBolNode.do" method="post" id="bolNodeVO" cssStyle="bolNodeVO">
<s:hidden name="bolNodeVO.id" id="id"></s:hidden>
	<div class="query">
		<div class="title"><%=getImageTag(request,"query-icon.gif")%></div>
	</div>
	<div class="box on">
		<div class="query-form">
			<table width="100%" class="querytable" border="0">
	              <tr>
                    <td class="til">云节点名称:</td>
                    <td>
						<s:textfield name="bolNodeVO.name" id="name" maxlength="100"></s:textfield>
                    </td>
                    <td class="til">主机IP:</td>
                    <td>
						<s:textfield name="bolNodeVO.ipaddress" id="ipaddress" maxlength="100"></s:textfield>
                    </td>
                    <td class="til">集群名称:</td>
                    <td>
						<s:textfield name="bolNodeVO.clusterName" id="clusterName" maxlength="100"></s:textfield>
                    </td>
                    <%--<td class="til">节点状态:</td>
                   	<td>
					   <s:select list="#{'1':'正常','0':'异常'}" name="bolNodeVO.status" id="status"></s:select>
					</td>
                  --%></tr>
                  <tr>
                    <td colspan="8" class="btns">
                        <div>
							<input type = "button" class="thickbox btn-style02" value = "查询" onclick = "javascript:searchRequest()" />
							<input type = "button" class="btn-style02" value = "重置" onclick = "javascript:resetForm()" />
                        </div>
                    </td>
                  </tr>
	         </table>
		</div>
      <div class="blue-wrap noborder">
      				<div class="table-head">
			      		<ul class="btns">
							<li><input type="button" class="thickbox btn-style02" value="增加" onclick = "add();return false;" /></li>
							<li><input type="button" class="thickbox btn-style02" value="修改" onclick = "mod();return false;" /></li>
							<li><input type="button" class="thickbox btn-style02" value="删除" onclick = "del();return false;" /></li>
						</ul>
						<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=bolNodeVO" />
					</div>
       <div class="table-ct" >
            <table id="theTable" width="100%" border="0" cellspacing="0" class="blue-table sorttable">
            	<thead id="table">
					<tr>
						<th>选择</th>
						<th onclick="sort(theTable,0,'string')">云节点名称</th>
						<th onclick="sort(theTable,1,'string')">集群名称</th>
						<th onclick="sort(theTable,2,'string')">主机IP</th>
						<th onclick="sort(theTable,3,'string')">云节点状态</th>
						<th onclick="sort(theTable,4,'string')">云节点描述</th>
						<th onclick="sort(theTable,5,'string')">云节点日期</th>
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
                				<s:property value="#theBean.clusterName"/>
                			</td>
                			<td>
                				<s:property value="#theBean.ipaddress"/>
                			</td>
                			<td>
                				<s:if test="#theBean.status==1">
                					<span style="color:green;">正常</span>   
                				</s:if>
                				<s:else>
                					<span style="color:red;">异常</span>   
                				</s:else>
                			</td>
                			<td>
                				<s:if test="#theBean.descrip!=null && #theBean.descrip!='' ">
                					<s:property value="#theBean.descrip"/>
                				</s:if>
                				<s:else>
                					-
                				</s:else>
                			</td>
                			<td>
                				<s:property value="#theBean.lastUpdate"/>
                			</td>
                		</tr>
                	</s:iterator>
                </tbody>
            </table>
        </div>
       </div><!--blue-wrap end -->
    </div><!--box end -->
</s:form>
</body>