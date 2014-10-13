<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8" %>
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
    		$("#query").click(function(){
    			if($("#query-form").is(":visible")){
    				$("#query-form").slideUp("slow");
    			}else{
    				$("#query-form").slideDown("slow");
    			}
    		});
            $("[name='add']").click(function(){
            	var id = $("#switch_id").attr("value");
	    		w.$.dialog({
	    			id:'add',
	    			title:'添加vlan',
	    			width: '500px',
	    			height: '300px',
	    			max: true,
	    		    min: true,
	    		    lock:true,	
	    			content: 'url:vlanAction_add.do?switchId='+id
	    			});
	           });
             $("[name='mod']").click(function(){
             	var mid = $("#switch_id").attr("value");
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
	    			title:'修改vlan信息',
	    			width: '500px',
	    			height: '300px',
	    			max: true,
	    		    min: true,
	    		    lock:true,	
	    			content: 'url:vlanAction_mod.do?vlanId='+id+'&switchId='+mid
	    		});
	         });
	         $("#reset").click(function(){
	         	clear();
	         });
	         
	         $("[name='interfaceId']").click(function(){
	        	 var currentEdit=$(this);
	        	 var id = currentEdit.attr("value");
	        	 var switch_id = $("#switch_id").val();
	    		 w.$.dialog({
	    			id:'interface',
	    			title:'Vlan对应端口',
	    			width: '800px',
	    			height: '500px',
	    			max: true,
	    		    min: true,
	    		    lock: true,
	    			content: 'url:switchInterfaceAction_list.do?switchId='+switch_id+'&vlanId='+id       
	    		});
	         });
           });
      function searchRequest(){
			$("#theForm").submit();
		}
		function deleteConfig(){
			var id='';
			var vlanID = '';
     		var lenth=0;
			$('[name=theForm.id]:checkbox:checked').each(function(){
        		id +=$(this).val();
        		vlanID +=$(this).attr("title");
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
		         url: "vlanAction_del.do?vlanId="+id+"&vlan_id="+vlanID,
		         dataType: "json",
		         success : function(data){
					searchRequest();
		        }
				});
			}
			
		}
		function deleteIPAddr(){
			var id='';
			var vlanID = '';
     		var lenth=0;
			$('[name=theForm.id]:checkbox:checked').each(function(){
        		id +=$(this).val();
        		vlanID +=$(this).attr("title");
        		lenth +=1;
        	 });
        	if(id==null || id ==''){
				alert('请先选择一项进行删除');
				return false;
			}else if(lenth>1){
				alert('只能选择一项进行删除');
				return false;
			}
			if(confirm('确定删除IP地址？')){
				$.ajax({
		         type: "post",
		         url: "vlanAction_delIPAddr.do?vlanId="+id+"&vlan_id="+vlanID,
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
			$("#vlan_name").attr("value","");
			$("#v_status").attr("value","-1");
			$("#vlan_id").attr("value","");
		}
		function saveOrUpdate(form,interf_id,interf_name,ip_old){
			var data = form.serialize()+"&interf_id="+interf_id+"&interf_name="+interf_name+"&ip_old="+ip_old;
			$.ajax({
		         type: "post",
		         url: "vlanAction_save.do",
				 data: data,
		         dataType: "json",
		         success : function(data){
					searchRequest();
		        }
			});
		}
    </script>
</head>
<body class="pop-body scrollbody">
    <s:form action="vlanAction_list.do" method="post" id="theForm" cssStyle="theForm">
    <s:hidden id="switch_id" name="switchId"/>
	<div class="pd-20 bgcolor-1">
		<h2 class="utt-1">Vlan管理</h2>
		<div class="bord-1 pd-10">
			<div class="clearfix filtrate-area">
				<div class="filtrate-field">
					<label class="fl">VLAN ID：</label>
					<s:textfield name="theForm.vlan_id" cssClass="inpt-1 fl" id="vlan_id" cssStyle="height:30px;border:solid 1px #c3c3c3;width:150px;"></s:textfield>
				 </div>
				 <div class="filtrate-field">
					<label class="fl">VLAN名称：</label>
					<s:textfield name="theForm.vlan_name" cssClass="inpt-1 fl" id="vlan_name" cssStyle="height:30px;border:solid 1px #c3c3c3;width:150px;"></s:textfield>
				 </div>
				<div class="filtrate-field">
					<label class="fl">VLAN状态：</label>
					<s:select cssClass="select-1 vm"  list="#{'0':'static','1':'dynamic'}" name="theForm.vlan_status" id="v_status" headerKey="-1" headerValue="--请选择--"></s:select>
				</div>
				<div class="filtrate-field">
					<span class="ubtn-1 mgl-20"><input type="button" value="查询"
								onclick="javascript:searchRequest()" />
					</span>
					<span class="ubtn-2 mgl-20"><input type="button" value="重置" id="reset" />
					</span>
				</div>
			</div>
		     <div  class="utt-2 mgt-20">
				<a class="icon-add" href="javascript:void(0)"  name="add" />增加</a>
				<a class="icon-modify" href="javascript:void(0)" name="mod" />修改</a>
				<a class="icon-del" href="javascript:void(0)" name = "del"  onclick="javascript:deleteConfig();"/>删除</a>
				<a class="icon-del" href="javascript:void(0)" name = "delIP" onclick="javascript:deleteIPAddr();"/>删除IP</a>
			</div>
			
	           <table id="theTable" width="915px;" border="0" cellspacing="0" class="blue-table sorttable">
	            	<thead>
						<tr>
							<th>选择</th>
							<th>VLAN ID</th>
							<th>VLAN名称</th>
							<th>VLAN状态</th>
							<th>IP地址</th>
							<th>端口</th>
						</tr>
					</thead>
	                <tbody>
	                	<s:iterator value="vlanList" id="theBean">
	                		<tr>
	                			<td><input type="checkbox" value="<s:property value='#theBean.id'/>" title="<s:property value="#theBean.vlan_id"/>" name="theForm.id" id="id"/>
	                			</td>
	                			<td><s:property value="#theBean.vlan_id"/></td>
	                			<td><s:property value="#theBean.vlan_name"/></td>
	                			<td><s:if test="#theBean.vlan_status==0">static</s:if>
	                				<s:elseif test="#theBean.vlan_status==1">dynamic</s:elseif>
	                			</td>
	                			<td>
	                				<s:if test="IPADDRESS!=null">
	                					<s:property value="#theBean.IPADDRESS"/>
	                				</s:if>
	                				<s:else>无</s:else>
	                			</td>
	                			<td><a href="#" name="interfaceId" value='<s:property value="#theBean.id"/>'>端口</a></td>
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
