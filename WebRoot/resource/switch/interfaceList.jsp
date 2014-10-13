<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/sxcloud/common/link.jsp"%>
<%@ include file="/sxcloud/common/taglib.jsp"%>
<%@ include file="/sxcloud/common/view.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
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
			         url: "switchInterfaceAction_del.do?interfaceId="+id,
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
		         url: "switchInterfaceAction_save.do?vlanId_old="+vlanId_old,
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
    <s:form action="switchInterfaceAction_list.do" method="post" id="theForm" cssStyle="theForm">
    <div class="pd-20 bgcolor-1">
		<h2 class="utt-1">端口管理</h2>
		<div class="bord-1 pd-10">
			<div class="clearfix filtrate-area">
				<div class="filtrate-field">
					<label class="fl">端口名称：</label>
					<s:textfield name="theForm.interf_name" cssClass="inpt-1 fl" id="i_name" cssStyle="height:30px;border:solid 1px #c3c3c3;width:150px;"></s:textfield>
				 </div>
				<div class="filtrate-field">
					<label class="fl">端口状态：</label>
					<s:select cssClass="select-1 vm"  list="#{'0':'open','1':'down'}" name="theForm.interf_status" id="i_status" headerKey="-1" headerValue="--请选择--"></s:select>
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
				<a class="icon-del" href="javascript:void(0)" name = "del"  onclick="javascript:deleteConfig();"/>删除</a>
				<a class="icon-del" href="javascript:void(0)" name = "delVlan" onclick="javascript:deleteVlan();"/>删除VLAN</a>
			</div>
			
            <table id="theTable" width="915px;" border="0" cellspacing="0" class="blue-table sorttable">
            	<thead>
					<tr>
						<th>选择</th>
						<th onclick="sort(theTable,1,'string')">端口名称</th>
						<th onclick="sort(theTable,2,'string')">端口状态</th>
						<th onclick="sort(theTable,3,'string')">工作模式</th>
						<th onclick="sort(theTable,4,'string')">端口速率(Mbps)</th>
						<th onclick="sort(theTable,5,'string')">自协商</th>
						<th onclick="sort(theTable,6,'string')">VLAN名称</th>
						<th onclick="sort(theTable,7,'date')">插入时间</th>
						<th onclick="sort(theTable,8,'date')">更新时间</th>
					</tr>
				</thead>
                <tbody>
                	<s:hidden name="switchId" id="switch_id"/>
                	<s:hidden name="vlanId" id="vlan_id"/>
                	<s:iterator value="interfaceList" id="theBean">
                		<tr>
                			<td><input type="checkbox" value="<s:property value='#theBean.id'/>" name="theForm.id" id="id"/>
                			</td>
                			<td><s:property value="#theBean.interf_name"/></td>
                			<td><s:if test="#theBean.interf_status==0">up</s:if>
                				<s:elseif test="#theBean.interf_status==1">down</s:elseif>
                			</td>
                			<td>
                				<s:if test="#theBean.workModle==0">FULL</s:if>
                				<%--<s:elseif test="#theBean.workModle==1">HALF</s:elseif>--%>
                				<s:elseif test="#theBean.workModle==2">access</s:elseif>
                				<s:elseif test="#theBean.workModle==3">trunk</s:elseif>
                				<%--<s:elseif test="#theBean.workModle==4">router</s:elseif>--%>
                			</td>
                			<td><s:property value="#theBean.interf_speed"/></td>
                			<td><s:if test="#theBean.auto_negotiation==0">10M半双工</s:if>
                				<s:elseif test="#theBean.auto_negotiation==1">10M全双工</s:elseif>
                				<s:elseif test="#theBean.auto_negotiation==2">100M半双工</s:elseif>
                				<s:elseif test="#theBean.auto_negotiation==3">100M全双工</s:elseif>
                			</td>
                			<td><s:if test="vlan_name!=null">
                					<s:property value="#theBean.vlan_name"/>
                				</s:if>
                				<s:else>-</s:else>
                			</td>
                			<td><s:property value="#theBean.insert_time"/></td>
                			<td><s:property value="#theBean.update_time"/></td>
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
