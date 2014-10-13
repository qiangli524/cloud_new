<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" %>
<%@ include file="../../sxcloud/common/taglib.jsp" %>
<%@ include file="../../sxcloud/common/view.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
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
	<style type="text/css">
		.font-more{ width:80px;height:20px;line-height:20px;overflow: hidden;
					white-space: nowrap;
					display: block;
					-o-text-overflow: ellipsis; 
					text-overflow: ellipsis;}

  </style>
    <link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=iblue"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
   	<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/js/checkbox.js"></script>
    <script type="text/javascript">
    	var api = frameElement.api;
		var w = api.opener;
    	$(function(){
    		$(".query").click(function(){
    			if($(".query-form").is(":visible")){
    				$(".query-form").slideUp("slow");
    			}else{
    				$(".query-form").slideDown("slow");
    			}
    		});
            $("[name='add']").click(function(){
            	var id = $("#switch_id").attr("value");
	    		w.$.dialog({
	    			id:'add',
	    			title:'添加端口',
	    			width: '500px',
	    			height: '300px',
	    			max: true,
	    		    min: true,
	    		    lock:true,
	    			content: 'url:switchInterfaceAction_add.do?switchId='+id
	    			});
	           });
             $("[name='mod']").click(function(){
             	var sid = $("#switch_id").attr("value");
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
	    		w.$.dialog({
	    			id:'vdi',
	    			title:'修改端口信息',
	    			width: '500px',
	    			height: '300px',
	    			max: true,
	    		    min: true,
	    		    lock:true,
	    			content: 'url:switchInterfaceAction_mod.do?interfaceId='+id+'&switchId='+sid
	    		});
	         });
	         $("#reset").click(function(){
	         	clear();
	         });
           });
      	function searchRequest(){
			$("#theForm").submit();
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
			if(confirm('确定删除？')){
				$.ajax({
			         type: "get",
			         url: "switchInterfaceAction_ah_del.do?interfaceId="+id,
			         dataType: "json",
			         success : function(data){
						searchRequest();
			        }
				});
			}
		}
		function deleteVlan(){
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
			if(confirm('确定删除VLAN？')){
				$.ajax({
		         type: "get",
		         url: "switchInterfaceAction_delVlan.do?interfaceId="+id,
		         dataType: "json",
				 async: false,
  				 cache: false,
		         success : function(data){
					searchRequest();
		        }
				});
			}
		}
		function clear(){
			$("#i_name").attr("value","");
			$("#i_status").attr("value","-1");
		}
		function saveOrUpdate(theForm,vlanId_old){
			$.ajax({
		         type: "post",
		         url: "switchInterfaceAction_ah_save.do?vlanId_old="+vlanId_old,
				 data: theForm.serialize(),
		         dataType: "json",
		         success : function(data){
					searchRequest();
		        }
			});
		}
    </script>
</head>
<body class="pop-body scrollbody">
    <s:form action="switchInterfaceAction_ah_list.do" method="post" id="theForm" cssStyle="theForm">
  	<div class="query" id="query">
	<div class="title" id="title"><%=getImageTag(request, "query-icon.gif")%></div>
	</div>
	<div class="box on">
	<div class="query-form" id="query-form">
	<table width="100%" class="querytable" border="0">
		<tr>
			<td class="til">端口名称：</td>
			<td>
				<s:textfield name="theForm.interf_name" id="i_name"></s:textfield>
			</td>
			<td class="til">端口状态：</td>
			<td>
				<s:select name="theForm.interf_status" list="#{'0':'open','1':'down'}"  id="i_status" headerKey="-1" headerValue="--请选择--"></s:select>
			</td>
		</tr>
		<tr>
			<td colspan="8" class="btns">
			<div><input type="button" class="thickbox btn-style02"
				value="查询" onclick="javascript:searchRequest();" />
				 <input type="button" class="btn-style02" value="重置" id="reset"/>
			</div>
			</td>
		</tr>
	</table>
	</div>
	<!--query-form end -->
    <div class="blue-wrap noborder">
	<div class="table-head">
				<ul class="btns">
                    <li colspan="4" class="btnCenter">
                           <input type="button"  value="添加"  class="btn-style02" name="add"/>
                         <input type="button"  value="修改" name="mod" class="btn-style02"/>
                        <input type="button"  value="删除" name="" class="btn-style02"  onclick="javascript:deleteConfig();"/>
                        <input type="button"  value="删除VLAN" name="" class="btn-style02-75"  onclick="javascript:deleteVlan();"/>
                    </li>
                </ul>
     <jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" /></div>
       <div class="table-ct">
            <table id="theTable" width="100%" border="0" cellspacing="0" class="blue-table sorttable">
            	<thead>
					<tr>
						<th width="10%">选择</th>
						<th width="10%" onclick="sort(theTable,1,'string')">端口名称</th>
						<th width="15%" onclick="sort(theTable,2,'string')">端口状态</th>
						<th width="10%" onclick="sort(theTable,3,'string')">工作模式</th>
						<th width="15%" onclick="sort(theTable,4,'string')">端口速率(Mbps)</th>
						<th width="10%" onclick="sort(theTable,5,'string')">自协商</th>
						<th width="10%" onclick="sort(theTable,6,'string')">VLAN名称</th>
						<th width="15%" onclick="sort(theTable,7,'date')">插入时间</th>
						<th width="10%" onclick="sort(theTable,8,'date')">更新时间</th>
						<th width="10%" onclick="sort(theTable,9,'string')">描述</th>
					</tr>
				</thead>
                <tbody>
                	<s:hidden name="switchId" id="switch_id"/>
                	<s:hidden name="vlanId" id="vlan_id"/>
                	<s:iterator value="interfaceList" id="theBean">
                		<tr>
                			<td><input type="checkbox" value="<s:property value='#theBean.id'/>" name="theForm.id" id="id"/></td>
                			<td><s:property value="#theBean.interf_name"/></td>
                			<td><s:if test="#theBean.interf_status==0">open</s:if>
                				<s:elseif test="#theBean.interf_status==1">down</s:elseif>
                			</td>
                			<td><s:if test="#theBean.workModle==0">FULL</s:if>
                				<s:elseif test="#theBean.workModle==1">HALF</s:elseif>
                				<s:elseif test="#theBean.workModle==2">access</s:elseif>
                				<s:elseif test="#theBean.workModle==3">trunk</s:elseif>
                				<s:elseif test="#theBean.workModle==4">router</s:elseif>
                			</td>
                			<td><s:property value="#theBean.interf_speed"/></td>
                			<td><s:if test="#theBean.auto_negotiation==0">true</s:if>
                				<s:elseif test="#theBean.auto_negotiation==1">false</s:elseif>
                			</td>
                			<td><s:if test="vlan_name!=null">
                					<s:property value="#theBean.vlan_name"/>
                				</s:if>
                				<s:else>-</s:else>
                			</td>
                			<td><s:property value="#theBean.insert_time"/></td>
                			<td><s:property value="#theBean.update_time"/></td>
                			<td align="center">
							  <div class="hidden" title='<s:property value="#theBean.descript"/>'>
							  	<s:property value="#theBean.descript"/>
							  </div>
	                		</td>
                		</tr>
                	</s:iterator>
                </tbody>
            </table>
        </div>
        </div>
    </s:form>
</body>
