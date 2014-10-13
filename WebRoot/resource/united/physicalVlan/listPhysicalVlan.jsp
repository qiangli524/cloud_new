<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" %>
<%@ include file="/sxcloud/common/link4a.jsp" %>
<%@ include file="/sxcloud/common/taglib.jsp" %>
<%@ include file="/sxcloud/common/view.jsp" %>
<html:html locale="true">
    <head>
        <title></title>
    <link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
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
    		
    		//添加Vlan
            $("[name='add']").click(function(){
	    		$.dialog({
	    			id:'add',
	    			title:'添加物理vlan',
	    			width: '500px',
	    			height: '300px',
	    			max: true,
	    		    min: true,
	    		    lock:true,
	    			content: 'url:physicalVlan_addPhysicalVlanPage.do'
	    			});
	        });
	        
	        //删除Vlan
	        $("#del").click(function(){
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
				if(confirm('确定删除？')){
					$.ajax({
			         type: "post",
			         url: 'physicalVlan_deletePhysicalVlan.do?obj.id='+id,
			         dataType: "json",
			         success:function(data){
						$.dialog({
						    time: 2,
						    title:'操作结果',
						    content: data.result
						});
						searchRequest();
			        }
					});
			}
	        });
	        
	        //回收Vlan
	        $("#collect").click(function(){
	        	var id='';
	     		var lenth=0;
				$('[name=theForm.id]:checkbox:checked').each(function(){
	        		id +=$(this).val();
	        		lenth +=1;
	        	 });
	        	if(id==null || id ==''){
					alert('请先选择一项进行回收');
					return false;
				}else if(lenth>1){
					alert('只能选择一项进行回收');
					return false;
				}
				if(confirm('确定回收？')){
					$.ajax({
			         type: "post",
			         url: 'physicalVlan_collectPhysicalVlan.do?obj.id='+id,
			         dataType: "json",
			         success : function(data){
						$.dialog({
						    time: 2,
						    title:'操作结果',
						    content: data.result
						});
						searchRequest();
			        }
					}); 
				}
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
	    			title:'修改vlan信息',
	    			width: '500px',
	    			height: '300px',
	    			max: true,
	    		    min: true,
	    		    lock:true,
	    			content: 'url:physicalVlan_editPhysicalVlan.do?obj.id='+id
	    		});
	         });
	         
	         
	         $("#reset").click(function(){
	         	clear();
	         });
	 
           });
           
      	function searchRequest(){
			$("#theForm").submit();
		}
		
		function updatePhysicalVlan(url){
			bar("12","正在更新物理Vlan，请稍后……");
			 $.ajax({
				  type:"POST",
	              url:url,
	              dataType:"json",
	              async: true,
	              cache: false,
		          success: function(msg){
	                if(msg==-1){
	                	bar("12",msg);
	                }else{
	                	bar("12",msg);
	                	searchRequest();
	                }
	              }
			});
		}
		
		function savePhysicalVlan(url){
			bar("12","正在添加物理Vlan，请稍后……");
			 $.ajax({
				  type:"POST",
	              url:url,
	              dataType:"json",
	              async: true,
	              cache: false,
		          success: function(msg){
	                if(msg==-1){
	                	bar("12",msg);
	                }else{
	                	bar("12",msg);
	                	searchRequest();
	                }
	              }
			});
		}
		
		function clear(){
			$("#vlan_name").attr("value","");
			$("#area_id").attr("value","");
			$("#isused").attr("value","");
			$("#vlan_id").attr("value","");
		}
		
		function bar(idstr,contents){
			$.dialog({
					id:idstr,
				    title: '提示',
				    width: 200,
				    height: 100,
				    left: '100%',
				    top: '100%',
				    fixed: true,
				    max:false,
				    content:contents
			});
		}
		
		function barEnd(idstr,contents){
			$.dialog.list[idstr].content(contents,false,false);
			$.dialog.list[idstr].time(25);
		}
    </script>
</head>

 <body>
    <div class="mainbody">
        <div class="pd-20 bgcolor-1">
            <h2 class="utt-1">物理VLAN管理</h2>
<s:form action="physicalVlan_listPhysicalVlan.do" method="post" id="theForm" cssStyle="theForm">
             <s:hidden name="theForm.ID" id="ID"></s:hidden>
            <div class="bord-1 pd-10">
                <div class="clearfix mgt-10">
                    <label class="vm">VLAN ID：</label>
                    <s:textfield name="obj.vlan_id" id="vlan_id" cssClass="inpt-1 vm" maxlength="30"></s:textfield>
                    <label class="mgl-20 vm">VLAN名称：</label>
                    <s:textfield name="obj.name" id="vlan_name" cssClass="inpt-1 vm" maxlength="30"></s:textfield>
            		<label class="vm">状态：</label>
					<s:select cssClass="select-1 vm" name="obj.isused" list="#{'0':'未使用','1':'已使用'}"  id="isused" headerKey="" headerValue="--请选择--"></s:select>
					 <label class="vm">地域：</label>
					<s:select cssClass="select-1 vm" name="obj.area_id" list="#{'1':'北京东部','2':'北京西部'}"  id="area_id" headerKey="" headerValue="--请选择--"></s:select>
                    <span class="ubtn-1 mgl-20"><input type="button" onclick="javascript:searchRequest()" value="查询"/></span>
                    <span class="ubtn-2 mgl-20"><input type="button" onclick="javascript:clear();" value="重置"/></span>
                </div>
                <div class="utt-2 mgt-20">
                    <a class="icon-add" href="javascript:void(0)" name="add">新增</a>
                    <a class="icon-modify" href="javascript:void(0)" name="mod">修改</a>
                    <a class="icon-del" href="javascript:void(0)" id="del">删除</a>
                    <a class="icon-release" href="javascript:void(0)" id="collect">回收</a>
                </div>
			
            <table width="100%" cellspacing="0" border="0" class="blue-table sorttable" id="theTable">
            	<thead>
					<tr>
						<th width="10%">选择</th>
						<%--<th width="10%" onclick="sort(theTable,1,'string')">ID</th>
						--%>
						<th width="10%" onclick="sort(theTable,1,'string')">VLAN ID</th>
						<th width="10%" onclick="sort(theTable,2,'string')">VLAN名称</th>
						<th width="10%" onclick="sort(theTable,3,'string')">IP地址</th>
						<th width="10%" onclick="sort(theTable,4,'string')">网关</th>
						<th width="10%" onclick="sort(theTable,5,'string')">子网掩码</th>
						<th width="10%" onclick="sort(theTable,7,'string')">用户ID</th>
						<th width="10%" onclick="sort(theTable,8,'string')">带宽</th>
						<th width="10%" onclick="sort(theTable,9,'string')">状态</th>
						<th width="10%" onclick="sort(theTable,10,'string')">地域</th>
					</tr>
				</thead>
                <tbody>
                	<s:iterator value="resultList" id="theBean">
                		<tr>
                			<td><input type="checkbox" value="<s:property value='#theBean.id'/>" title="<s:property value="#theBean.vlan_id"/>" name="theForm.id" id="id"/></td>
                			<%--<td><s:property value="#theBean.id"/></td>
                			--%>
                			<td><s:property value="#theBean.vlan_id"/></td>
                			<td><s:property value="#theBean.name"/></td>
                			<td>
                				<s:if test="#theBean.ip!=null">
                					<s:property value="#theBean.ip"/>
                				</s:if>
                				<s:else>无</s:else>
                			</td>
                			<td><s:property value="#theBean.gateway"/></td>
                			<td><s:property value="#theBean.subnet_mask"/></td>
                			
                			<td><s:property value="#theBean.user_id"/></td>
                			<td><s:property value="#theBean.flow_size"/></td>
                			
                			<td><s:if test="#theBean.isused==0">未使用</s:if>
                				<s:elseif test="#theBean.isused==1">已使用</s:elseif>
                			</td>
                			<td><s:if test="#theBean.area_id==1">北京东部</s:if>
                				<s:elseif test="#theBean.isused==2">北京西部</s:elseif>
                			</td>
                		</tr>
                	</s:iterator>
                </tbody>
            </table>
            
       			<div class="pages mgb-10"><!-- 分页 -->
                    <jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm"/>
                </div>
                </s:form>
            </div>
            
        </div>
    </body>
</html:html>
