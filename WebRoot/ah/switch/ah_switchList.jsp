<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" %>
<%@ include file="../../sxcloud/common/taglib.jsp" %>
<%@ include file="../../sxcloud/common/view.jsp" %>

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
    <link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=iblue"></script>
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
	    			width: '800px',
	    			height: '500px',
	    			max: true,
	    		    min: true,
	    		    lock:true,
	    			content: 'url:switchInterfaceAction_ah_list.do?switchId='+id       
	    			});
              });
    		 $("[name='vlan']").bind("click",function(){
	        	var currentEdit=$(this);
	        	var id = currentEdit.attr("value");
	    		$.dialog({
	    			id:'vlan',
	    			title:'配置vlan',
	    			width: '800px',
	    			height: '500px',
	    			max: true,
	    		    min: true,
	    		    lock:true,
	    			content: 'url:vlanAction_list.do?switchId='+id   
	    			});
              });
             $("[name='add']").click(function(){
	        	currentEdit=$(this);
	    		$.dialog({
	    			id:'add',
	    			title:'添加交换机',
	    			width: '500px',
	    			height: '300px',
	    			max: true,
	    		    min: true,
	    		    lock:true,
	    			content: 'url:ah/switch/ah_switchEdit.jsp'       
	    			});
              });
            $("[name='detail']").click(function(){
        	currentEdit=$(this);
        	var id = currentEdit.attr("value");
    		$.dialog({
    			id:'vdi',
    			title:'详细信息',
    			width: '600px',
    			height: '300px',
    			max: true,
    		    min: true,
    		    lock:true,
    			content: 'url:switchAction_detail.do?switchId='+id
    			});
              });
             
             $("[name='ah_mod']").click(function(){
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
	    			width: '500px',
	    			height: '300px',
	    			max: true,
	    		    min: true,
	    		    lock:true,
	    			content: 'url:switchAction_ah_mod.do?switchId='+id
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
	    		    lock:true,
	    			content: 'url:vlanAction_list.do?switchId='+id       
	    			});
		}
		function clear(){
			$("#name").attr("value","");
			$("#status").attr("value","-1");
		}
		
    </script>
</head>
<body class="pop-body scrollbody">
    <s:form action="switchAction_ah_list.do" method="post" id="theForm" cssStyle="theForm">
  	<div class="query" id="query">
	<div class="title" id="title"><%=getImageTag(request, "query-icon.gif")%></div>
	</div>
	<div class="box on">
	<div class="query-form" id="query-form">
	<table width="100%" class="querytable" border="0">
		<tr>
			<td class="til">交换机名称:</td>
			<td>
				<s:textfield name="theForm.switch_name" id="name"></s:textfield>
			</td>
			<td class="til">交换机状态:</td>
			<td>
				<s:select list="#{'0':'未锁定','1':'锁定'}" name="theForm.switch_status" id="status" headerKey="-1" headerValue="--请选择--"></s:select>
			</td>
		</tr>
		<tr>
			<td colspan="8" class="btns">
			<div><input type="button" class="thickbox btn-style02"
				value="查询" onclick="javascript:searchRequest();" />
				 <input type="button" class="btn-style02" value="重置" id="reset" />
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
                         <input type="button"  value="修改" name="ah_mod" class="btn-style02"/>
                        <input type="button"  value="删除" name="" class="btn-style02"  onclick="javascript:if(confirm('确定删除？'))deleteConfig();"/>
                    </li>
                </ul>
     <jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" /></div>
       <div class="table-ct">
            <table id="theTable" width="100%" border="0" cellspacing="0" class="blue-table sorttable">
            	<thead>
					<tr>
						<th width="10%">选择</th>
						<th width="15%" onclick="sort(theTable,1,'string')">交换机名称</th>
						<th width="10%" onclick="sort(theTable,2,'string')">状态</th>
						<th width="10%" onclick="sort(theTable,3,'string')">型号</th>
						<th width="20%" onclick="sort(theTable,4,'date')">登记时间</th>
						<th width="20%" onclick="sort(theTable,5,'date')">更新时间</th>
						<th width="15%">详细配置</th>
					</tr>
				</thead>
                <tbody>
                	<s:iterator value="switchList" id="theBean">
                		<tr>
                			<td><input type="checkbox" value="<s:property value="#theBean.id"/>" name="theForm.id" id="id"/></td>
                			<td><a name="detail" href="#" value='<s:property value="#theBean.id"/>'><s:property value="#theBean.switch_name"/> </a></td>
                			<td><s:if test="#theBean.switch_status==0">unlock</s:if>
                				<s:elseif test="#theBean.switch_status==1">lock</s:elseif>
                			</td>
                			<td><s:property value="#theBean.switch_type"/></td>
                			<td><s:property value="#theBean.insert_time"/></td>
                			<td><s:property value="#theBean.update_time"/></td>
                			<td><a href="#" name="vlan" value='<s:property value="#theBean.id"/>'>VLAN</a>&nbsp;|&nbsp;<a href="#" name="interface" value='<s:property value="#theBean.id"/>'>端口</a></td>
                		</tr>
                	</s:iterator>
                </tbody>
            </table>
        </div>
        </div>
    </s:form>
</body>