<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8" %>
<%@ include file="../../sxcloud/common/taglib.jsp" %>
<%@ include file="../../sxcloud/common/view.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<head>
    <title></title>
    <link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/js/checkbox.js"></script>
    
    <script type="text/javascript">
	    var api = frameElement.api;
		var w = api.opener;
		function searchRequest(){
			obj.submit();
		}
    	$(function(){
            $("[name='add']").click(function(){
            	var id = $("#switch_id").attr("value");
	    		w.$.dialog({
	    			id:'add',
	    			title:'添加静态路由',
	    			width: '500px',
	    			height: '300px',
	    			max: true,
	    		    min: true,
	    		    lock:true,
	    			content: 'url:staticRouterAction_add.do?switchId='+id
	    			});
	           });
            
            $("[name='mod']").click(function(){
             	var mid = $("#switch_id").attr("value");
	        	currentEdit=$(this);
	        	var id ='';
	        	var lenth = 0;
	        	$('[name=id]:checkbox:checked').each(function(){
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
	    			title:'修改静态路由',
	    			width: '500px',
	    			height: '300px',
	    			max: true,
	    		    min: true,
	    		    lock:true,
	    			content: 'url:staticRouterAction_mod.do?obj.id='+id+'&switchId='+mid
	    		});
	         });
            
            $("[name='del']").click(function(){
     			var mid = $("#switch_id").attr("value");
     			var id='';
     			var source_ip = '';
     			var subnet_mask = '';
     			var ip = '';
          		var lenth=0;
     			$('[name=id]:checkbox:checked').each(function(){
             		id +=$(this).val();
             		source_ip +=$(this).attr("source_ip");
             		subnet_mask +=$(this).attr("subnet_mask");
             		ip +=$(this).attr("ip");
             		lenth +=1;
             	 });
             	if(id==null || id ==''){
     				alert('请先选择一项进行删除');
     				return false;
     			}else if(lenth>1){
     				alert('只能选择一项进行删除');
     				return false;
     			}
             	var url = 'staticRouterAction_del.do?obj.id='+id+'&obj.source_ip='+source_ip+'&obj.subnet_mask='+subnet_mask+'&obj.ip='+ip+'&switchId='+mid;
     			if(confirm('确定删除？')){
     				$.ajax({
     			         type: "post",
     			         url: url,
     			         dataType: "json",
     			         success : function(data){
     						searchRequest();
     			        }
     				});
     			}
 			});
        });
    	
    	function staticRouterConfig(url){
			$.ajax({
		         type: "post",
		         url: url,
		         dataType: "json",
		         success : function(data){
					searchRequest();
		        }
			});
		}
    	
    	function resetObj(){
    		$("#source_ip").attr("value","");
			$("#subnet_mask").attr("value","");
			$("#ip").attr("value","");
    	}
    </script>
</head>
<body class="pop-body scrollbody">
    <s:form action="staticRouterAction_list.do" method="post" id="obj" cssStyle="obj">
  	<div class="query" id="query">
	<div class="title" id="title"><%=getImageTag(request, "query-icon.gif")%></div>
	</div>
	<div class="box on">
	<div class="query-form" id="query-form">
	<table width="100%" class="querytable" border="0">
		<tr>
			<td class="til">目的地址：</td>
			<td>
				<s:textfield name="obj.source_ip" id="source_ip"></s:textfield>
			</td>
			<td class="til">子网掩码：</td>
			<td>
				<s:textfield name="obj.subnet_mask" id="subnet_mask"></s:textfield>
			</td>
			<td class="til">网关地址：</td>
			<td>
				<s:textfield name="obj.ip" id="ip"></s:textfield>
			</td>
		</tr>
		<tr>
			<td colspan="8" class="btns">
			<div><input type="button" class="thickbox btn-style02"
				value="查询" onclick="javascript:searchRequest();" />
				 <input type="button" class="btn-style02" id="reset" value="重置" onclick="resetObj();"/>
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
                         <input type="button"  value="添加" name="add" class="btn-style02">
                         <input type="button"  value="修改" name="mod" class="btn-style02"/>
                         <input type="button"  value="删除" name="del" class="btn-style02" />
                    </li>
                </ul>
     <jsp:include page="/sxcloud/inc/Pagination.jsp?formId=obj" /></div>
       <div class="table-ct">
            <table id="theTable" width="100%" border="0" cellspacing="0" class="blue-table sorttable">
            	<thead>
					<tr>
						<th width="10%">选择</th>
						<th width="10%" onclick="sort(theTable,1,'string')">目的地址</th>
						<th width="15%" onclick="sort(theTable,2,'string')">子网掩码</th>
						<th width="10%" onclick="sort(theTable,3,'string')">网关地址</th>
					</tr>
				</thead>
                <tbody>
                	<s:hidden id="switch_id" name="switchId"/>
                	<s:iterator value="resultList" id="theBean">
                		<tr>
                			<td><input type="checkbox" value="<s:property value='#theBean.id'/>"
                				 source_ip="<s:property value="#theBean.source_ip"/>"
                				 subnet_mask="<s:property value="#theBean.subnet_mask"/>"
                				 ip="<s:property value="#theBean.ip"/>" name="id" id="id"/></td>
                			<td><s:property value="#theBean.source_ip"/></td>
                			<td><s:property value="#theBean.subnet_mask"/></td>
                			<td><s:property value="#theBean.ip"/></td>
                		</tr>
                	</s:iterator>
                </tbody>
            </table>
        </div>
        </div>
    </s:form>
</body>
