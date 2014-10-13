<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../../sxcloud/common/taglib.jsp"%>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>
<%@ page import="com.sitech.basd.sxcloud.rsmu.web.util.page.image.ImageUtil" %>
<%!public String getImageTag(HttpServletRequest request, String path) {
		return ImageUtil.getImageTag(request, path);
	}%>
<html:html locale="true">
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
	
	<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
  	<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/js/checkbox.js"></script>
    <script type="text/javascript">
    var example_id = '<s:property value="example_id"/>';
    var hostIP = '<s:property value="hostIP"/>';
    var type = '<s:property value="type"/>';
		//更新tabs页面
		function updatePage(){
			theForm.action="deployconfig_listHadoopConfig.do?example_id="+example_id+"&hostIP="+hostIP+"&type="+type;
			theForm.submit();
		}
    	$(function(){
    		if (type == 11) {
				$("#addForm").show();
			} else {
				$("#addForm").hide();
			}
    		
              $("[name='addConfig']").click(function(){
	        	currentEdit=$(this);
	    		$.dialog({
	    			id:'add',
	    			title:'添加配置文件',
	    			width: '500px',
	    			height: '300px',
	    			max: true,
	    		    min: true,
	    		    lock:true,
	    			content: 'url:deployconfig_addHadoopConfig.do?example_id='+example_id+'&hostIP='+hostIP+'&type='+type
	    			});
              });
              
              
            $("[name='detail']").unbind().live("click",function(){
        	currentEdit=$(this);
        	var id = currentEdit.attr("value");
    		$.dialog({
    			id:'vdi',
    			title:'详细信息',
    			width: '1000px',
    			height: '550px',
    			max: true,
    		    min: true,
    		    lock:true,
    			content: 'url:deployconfig_listConfigContent.do?id='+id
    			});
              });
             
             $("[name='modConfig']").click(function(){
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
	    			title:'修改配置文件',
	    			width: '500px',
	    			height: '300px',
	    			max: true,
	    		    min: true,
	    		    lock:true,
	    			content: 'url:deployconfig_modConfig.do?id='+id
	    			});
	              });
           });   
      function searchRequest(){
    	    theForm.action="deployconfig_listHadoopConfig.do?example_id="+example_id+"&hostIP="+hostIP+"&type="+type;
			theForm.submit();
		}
		function deleteConfig(){
			var id = $("input['name=theForm.id']:checked").val();
			if(confirm('你确定要删除吗？')){
				var url = "deployconfig_deleteConfig.do?id="+id+'&example_id='+example_id+'&type='+type;
				 $.ajax({
					  type:"GET",
		              url:url,
		              data:"text",
		              async: false,
		              cache: false,
			          success: function(msg){
		                if(msg==null){
		                }else{
		                	updatePage();
		                }
		              }
				});
			}
		}
		function clear(){
			theForm.name.value = '';
			theForm.type.value = '';
		}
		
		function list(){
			theForm.action="deployconfig_listHadoopConfig.do?example_id="+example_id+"&hostIP="+hostIP+"&type="+type;
			theForm.submit();
		}
		
	
		function resetForm(){
			$("#name").attr("value","");
			$("#type").attr("value","");
			$("#description").attr("value","");
		}
    </script>
</head>
<body class="pop-body scrollbody" >
<s:form action="" method="post" id="theForm" cssStyle="theForm">
<div class="pd-20 bgcolor-1">
			<h2 class="utt-1">配置文件管理</h2>
			<div class="bord-1 pd-10">			
			<div class="clearfix mgt-10">
                 <label class="vm">配置文件名称：</label>
						<s:textfield name="theForm.name" id="name" maxlength="100"></s:textfield>
                 <label class="mgl-20 vm">类型：</label>
						<s:select cssClass="select-1 vm"  list="#{'':'--请选择--','0':'xml','1':'properties','2':'其他'}" name="theForm.type" id="type"></s:select>
                 <label class="vm">描述：</label>
						<s:textfield name="theForm.description" id="description"></s:textfield>
                   
                  <span class="ubtn-1 mgl-20"><input type="button" onclick="javascript:searchRequest()" value="查询" /></span>
				<span class="ubtn-2 mgl-20"><input type="button" onclick="javascript:resetForm()" value="重置" /></span>
			</div>
			
    <div class="utt-2 mgt-20">
    
				<a name="addConfig" class="icon-add" href="javascript:void(0)">添加</a>
				<a name="modConfig" class="icon-modify" href="javascript:void(0)">修改</a>
				<a class="icon-del" href="javascript:void(0)" onclick="javascript:deleteConfig();" >删除</a>
			</div>	
       <div class="table-ct" >
            <table id="theTable" width="100%" border="0" cellspacing="0" class="blue-table sorttable">
            	<thead>
					<tr>
						<th onclick="sort(theTable,0,'string')">选择</th>
						<th onclick="sort(theTable,1,'string')">配置文件名称</th>
						<th onclick="sort(theTable,2,'string')">主机/用户名</th>
						<th onclick="sort(theTable,3,'string')">类型</th>
						<th onclick="sort(theTable,4,'string')" width="10%">路径</th>
						<th onclick="sort(theTable,5,'string')">描述</th>
					</tr>
				</thead>
                <tbody id="table">
                	<s:iterator value="theForm.resultList" id="theBean">
                		<tr>
                			<td><input type="checkbox" value="<s:property value="#theBean.id"/>" name="theForm.id" id="id"/></td>
                			<td><s:property value="#theBean.name"/></td>
                			<td><s:property value="#theBean.ip"/>/<s:property value="#theBean.username"/></td>
                			<td><s:if test="#theBean.type==0">xml</s:if>
                				<s:elseif test="#theBean.type==1">properties</s:elseif>
                				<s:else>其他</s:else>
                			</td>
                			<td align="center"><a name="detail" href="javascript:;" value='<s:property value="#theBean.id"/>'><div class="hidden" title='<s:property value="#theBean.path"/>'>
                				<s:property value="#theBean.path"/></div></a></td>
                			<td id="desc"><s:property value="#theBean.description"/></td>
                		</tr>
                	</s:iterator>
                </tbody>
            </table>
            <div class="pages">
		<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
			</div>	
        </div>
           
        </div>
      </div>

    </s:form>
</body>
</html:html>