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
    			title:'添加主机',
    			width: '500px',
    			height: '300px',
    			max: true,
    		    min: true,
    		    lock:true,
    			content: 'url:bolhost_add.do'       
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
    			title:'修改主机信息',
    			width: '500px',
    			height: '300px',
    			max: true,
    		    min: true,
    		    lock:true,
    			content: 'url:bolhost_mod.do?hostId='+id
    		});
         });
         $("#reset").click(function(){
         	clear();
         });
       });
	  	function searchRequest(){
			theForm.submit();
		}
		function deleteHost(){
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
			theForm.action="bolhost_del.do?hostId="+id;
			theForm.submit();
		}
		function clear(){
			$("#name").attr("value","");
			$("#status").attr("value","-1");
		}
		
		function resetForm(){
			$("#ipaddress").attr("value","");
			$("#name").attr("value","");
			$("#status").attr("value","-1");
		}
		
		function activeHost(activeState,id){
			var hostId = $("a[id='"+id+"']").attr("id");
			var nodeIp = $("a[id='"+id+"']").attr("nodeIp");
			var hostCode = $("a[id='"+id+"']").attr("hostCode");
			$.ajax({
				type:"get",
	            url:"bolhost_activeHost.do?hostId=" + hostId + "&activeState=" + activeState
				+ "&nodeIp=" + nodeIp + "&hostCode=" + hostCode,
	            dataType: "json",
	            async: false,
	            cache: false,
		        success: function(data){
		        	if(data.result == 1){
		        		searchRequest();
		        	}else if(data.result == -1){
		        		bar("操作失败！");
		        	}else{
		        		bar("操作失败，原因：不能找到主机的标识！");
		        	}
	           	}
			});
		}
		
		function bar(contents){
			$.dialog({
					id:'barId',
				    title: '提示',
				    width: 200,
				    height: 100,
				    left: '100%',
				    top: '100%',
				    fixed: true,
				    max:false,
				    content:contents
				}).time(3);
		}
	
    </script>
</head>
<body class="pop-body scrollbody">
    <s:form action="bolhost_listBolHost.do" method="post" id="theForm" cssStyle="theForm">
    <div class="query">
		<div class="title"><%=getImageTag(request,"query-icon.gif")%></div>
	</div>
	<div class="box on">
		<div class="query-form">
					 <table width="100%" class="querytable" border="0">
					 	<tr>
	                    <td class="til">主机IP:</td>
	                    <td>
							<s:textfield name="theForm.ipaddress" id="ipaddress" maxlength="100" style="width:150px;   height:18px;"></s:textfield>
	                    </td>
	                    <td class="til">主机名称:</td>
	                    <td>
							<s:textfield name="theForm.name" id="name" maxlength="100" style="width:150px;   height:18px;"></s:textfield>
	                    </td>
	                    <td class="til">主机状态:</td>
	                    <td>
							<s:select name="theForm.status" list="#{'-1':'-请选择-','2':'正常','1':'空闲','0':'异常','3':'繁忙'}"  id="status" style="width:150px;   height:20px;"></s:select>
						</td>
	                    </tr>
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
	                    <li colspan="4" class="btnCenter">
	                        <input type="button"  value="添加"  class="btn-style02" name="add"/>
	                        <input type="button"  value="修改" name="mod" class="btn-style02"/>
	                        <input type="button"  value="删除" name="" class="btn-style02"  onclick="javascript:if(confirm('确定删除？'))deleteHost();"/>
	                    </li>
	                </ul>
						<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
					</div>
       <div class="table-ct" >
            <table id="theTable" width="100%" border="0" cellspacing="0" class="blue-table sorttable">
            	<thead id="table">
					<tr>
						<th>选择</th>
						<th onclick="sort(theTable,0,'string')">主机IP</th>
						<th onclick="sort(theTable,1,'string')">主机名称</th>
						<th onclick="sort(theTable,2,'string')">主机类型</th>
						<th onclick="sort(theTable,3,'string')">主机能力</th>
						<th onclick="sort(theTable,4,'string')">主机状态</th>
						<th onclick="sort(theTable,5,'string')">主机描述</th>
						<th onclick="sort(theTable,6,'string')">激活状态</th>
						<th onclick="sort(theTable,7,'string')">修改日期</th>
						<th onclick="sort(theTable,8,'string')">操作</th>
					</tr>
				</thead>
                <tbody>
                	<s:iterator value="resultList" id="theBean">
                		<tr >
                			<td>
                				<input name="checkboxid" type="checkbox" value="<s:text name="#theBean.id"/>"/>
                			</td>
                			<td>
                				<s:property value="#theBean.ipaddress"/>
                			</td>
                			<td>
                				<s:property value="#theBean.name"/>
                			</td>
                			<td>
                				<s:if test="#theBean.hostType==1">
                					BOL Master
                				</s:if>
                				<s:elseif test="#theBean.hostType==2">
                					BOL Slave
                				</s:elseif>
                				<s:else>
                					--
                				</s:else>
                			</td>
                			<td>
                				<s:property value="#theBean.capability"/>
                			</td>
                			<td>
                				<s:if test="#theBean.status==0">
                					<span style="color:red;">异常</span>   
                				</s:if>
                				<s:elseif test="#theBean.status==2">
                					<span style="color:green;">正常</span>   
                				</s:elseif>
                				<s:elseif test="#theBean.status==1">
                					空闲
                				</s:elseif>
                				<s:elseif test="#theBean.status==3">
                					<span style="color:orange;">繁忙</span>
                				</s:elseif>
                			</td>
                			<td>
                				<s:property value="#theBean.descrip"/>
                			</td>
                			<td>
                				<s:if test="#theBean.isActive==0">
                					<span style="color:red;">非激活</span>  
                				</s:if>
                				<s:elseif test="#theBean.isActive==1">
                					<span style="color:green;">已激活</span> 
                				</s:elseif>
                				<s:elseif test="#theBean.isActive==2">
                					<span style="color:orange;">维护</span>
                				</s:elseif>
                				<s:else>
                					--
                				</s:else>
                			</td>
                			<td>
                				<s:property value="#theBean.lastUpdate"/>
                			</td>
                			<td>
                				<s:if test="#theBean.isActive==0">
                					<a href="javascript:activeHost(1,'<s:property value="#theBean.id"/>');" id="<s:property value="#theBean.id"/>" nodeIp="<s:property value="#theBean.ipaddress"/>" hostCode = "<s:property value="#theBean.hostCode"/>">激活</a>
                				</s:if>
                				<s:elseif test="#theBean.isActive==1">
                					<a href="javascript:activeHost(0,'<s:property value="#theBean.id"/>');" id="<s:property value="#theBean.id"/>" nodeIp="<s:property value="#theBean.ipaddress"/>" hostCode = "<s:property value="#theBean.hostCode"/>">去激活</a>
                				</s:elseif>
                				<s:else>
                					--
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