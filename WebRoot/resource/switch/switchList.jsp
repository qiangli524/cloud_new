<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" %>
<%@ include file="/sxcloud/common/link.jsp"%>
<%@ include file="/sxcloud/common/taglib.jsp"%>
<%@ include file="/sxcloud/common/view.jsp"%>
<head>
    <title></title>
     <style type="text/css">
		div.hidden{
		width:50px;
		height:30px;
		overflow:hidden;
		white-space:nowrap;
		text-overflow:ellipsis;
		text-overflow: ellipsis;/* IE/Safari */
		-ms-text-overflow: ellipsis;
		-o-text-overflow: ellipsis;/* Opera */
		-moz-binding: url("ellipsis.xml#ellipsis");/*FireFox*/
	}
  </style>
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
    		 $("[name='interface']").bind("click",function(){
	        	var currentEdit=$(this);
	        	var id = currentEdit.attr("value");
	    		$.dialog({
	    			id:'interface',
	    			title:'配置端口',
	    			width: '1000px',
	    			height: '750px',
	    			max: true,
	    		    min: true,
	    		    lock:true,	
	    			content: 'url:switchInterfaceAction_list.do?switchId='+id       
	    			});
              });
    		 $("[name='vlan']").bind("click",function(){
	        	var currentEdit=$(this);
	        	var id = currentEdit.attr("value");
	    		$.dialog({
	    			id:'vlan',
	    			title:'配置vlan',
	    			width: '1000px',
	    			height: '750px',
	    			max: true,
	    		    min: true,
	    		    lock:true,	
	    			content: 'url:vlanAction_list.do?switchId='+id   
	    			});
              });
    		 $("[name='router']").bind("click",function(){
 	        	var currentEdit=$(this);
 	        	var id = currentEdit.attr("value");
 	    		$.dialog({
 	    			id:'staticRouter',
 	    			title:'静态路由列表',
 	    			width: '800px',
 	    			height: '500px',
 	    			max: true,
 	    		    min: true,
 	    			content: 'url:staticRouterAction_list.do?switchId='+id       
 	    			});
               });
             $("[name='add']").click(function(){
	        	currentEdit=$(this);
	    		$.dialog({
	    			id:'add',
	    			title:'添加交换机',
	    			width: '700px',
	    			height: '500px',
	    			max: true,
	    		    min: true,
	    		    lock:true,	
	    			content: 'url:resource/switch/switchEdit.jsp'       
	    			});
              });
            $("[name='detail']").click(function(){
        	currentEdit=$(this);
        	var id = currentEdit.attr("value");
    		$.dialog({
    			id:'vdi',
    			title:'详细信息',
    			width: '600px',
    			height: '380px',
    			max: true,
    		    min: true,
    		    lock:true,	
    			content: 'url:switchAction_detail.do?switchId='+id
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
	    			title:'修改交换机信息',
	    			width: '700px',
	    			height: '500px',
	    			max: true,
	    		    min: true,
	    		    lock:true,	
	    			content: 'url:switchAction_mod.do?switchId='+id
	    		});
	         });
	         $("#reset").click(function(){
	         	clear();
	         });
           });
      function searchRequest(){
			theForm.submit();
		}
		function deleteConfig(){
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
			theForm.action="switchAction_del.do?switchId="+id;
			theForm.submit();
		}
		function vlan(){
	        	var id = $(this).attr("id");
	    		$.dialog({
	    			id:'vlan',
	    			title:'配置vlan',
	    			width: '800px',
	    			height: '500px',
	    			max: true,
	    		    min: true,
	    			content: 'url:vlanAction_list.do?switchId='+id       
	    			});
		}
		function clear(){
			$("#name").attr("value","");
			$("#status").attr("value","-1");
		}
		
		function staticRouterConfig(url){
			$.ajax({
				type:"POST",
	            url:url,
	            async: false,
	            cache: false,
		        success: function(data){
		        	if(data!=-1){
		        		alert("静态路由配置成功！");
		        		searchRequest();
		        	}else{
		        		alert("静态路由配置失败！");
		        	}
	           	}
			});
		}
		
    </script>
</head>
<body class="pop-body scrollbody">
    <s:form action="switchAction_list.do" method="post" id="theForm" cssStyle="theForm">
	<div class="pd-20 bgcolor-1">
		<h2 class="utt-1">交换机管理</h2>
		<div class="bord-1 pd-10">
			<div class="clearfix filtrate-area">
				<div class="filtrate-field">
					<label class="fl">交换机名称:</label>
					<s:textfield name="theForm.switch_name" cssClass="inpt-1 fl" id="name"></s:textfield>
				 </div>
				<div class="filtrate-field">
					<label class="fl">主机状态:</label>
					<s:select cssClass="select-1 vm"  list="#{'0':'未锁定','1':'锁定'}" name="theForm.switch_status" id="status" headerKey="-1" headerValue="--请选择--"></s:select>
				</div>
				<div class="filtrate-field">
					<span class="ubtn-1 mgl-20"><input type="button" value="查询"
								onclick="javascript:searchRequest()" /></span>
					<span class="ubtn-2 mgl-20"><input type="button" value="重置" id="reset" /></span>
				</div>
			</div>
		     <div  class="utt-2 mgt-20">
				<a class="icon-add" href="javascript:void(0)"  name="add" />增加</a>
				<a class="icon-modify" href="javascript:void(0)" name="mod" />修改</a>
				<a class="icon-del" href="javascript:void(0)" name = "del" onclick="javascript:if(confirm('确定删除？'))deleteConfig();"/>删除</a>
			</div>
		     
		     
            <table id="theTable" width="100%" border="0" cellspacing="0" class="blue-table sorttable">
            	<thead>
					<tr>
						<th>选择</th>
						<th onclick="sort(theTable,1,'string')">交换机名称</th>
						<th onclick="sort(theTable,2,'string')">状态</th>
						<th onclick="sort(theTable,3,'string')">型号</th>
						<th onclick="sort(theTable,4,'date')">登记时间</th>
						<th onclick="sort(theTable,5,'date')">更新时间</th>
						<th>详细配置</th>
					</tr>
				</thead>
                <tbody>
                	<s:iterator value="switchList" id="theBean">
                		<tr>
                			<td><input type="checkbox" value="<s:property value="#theBean.id"/>" name="theForm.id" id="id"/>
							</td>
                			<td><a name="detail" href="#" value='<s:property value="#theBean.id"/>'><s:property value="#theBean.switch_name"/> </a></td>
                			<td><s:if test="#theBean.switch_status==0">unlock</s:if>
                				<s:elseif test="#theBean.switch_status==1">lock</s:elseif>
                			</td>
                			<td><s:property value="#theBean.switch_type"/></td>
                			<td><s:property value="#theBean.insert_time"/></td>
                			<td><s:property value="#theBean.update_time"/></td>
                			<td><a href="#" name="vlan" value='<s:property value="#theBean.id"/>'>VLAN</a>&nbsp;|&nbsp;
                				<a href="#" name="interface" value='<s:property value="#theBean.id"/>'>端口</a>
                				<%--&nbsp;|&nbsp;<a href="#" name="router" value='<s:property value="#theBean.id"/>'>静态路由</a>
                			--%></td>
                		</tr>
                	</s:iterator>
                </tbody>
            </table>
            <div class="pages mgb-10">
				<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
			</div>
      	</div>
    </div>
    </s:form>
</body>