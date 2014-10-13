<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<html:html locale="true">
<%@ include file="../../sxcloud/common/link.jsp"%>
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
	<script type="text/javascript">
		var api = frameElement.api;
	 	var w = api.opener;
	 	api.button({
	     	id:'Ok',
	     	callback:add,
	     	name: '确定',
	     	focus: true
		 },
		 {
	     id:'cancle',
	     name: '取消'
	 	});
   /**
    *check object
    */
    function cf_isNotObject(obj)
    {
      if(typeof(obj) != "object")
        {
          alert(obj+"\nis not a object");
          return true;
        }
        return false;
    }
 function submitRequest(thisForm){
 	   var group_id = '<%=request.getAttribute("id")%>';
	   var str='';
	    $('[name=theForm.id]:checkbox:checked').each(function(){
        	 	str +=$(this).val()+",";
        	});
		w.selectMember(group_id,str);
	}
	
	function bar(idstr,contents){
	$.dialog({
			id:idstr,
		    title: '提示',
		    width: 150,
		    height: 80,
		    left: '100%',
		    top: '100%',
		    fixed: true,
		    max:false,
		    content:contents
		});
	}

	function barEnd(idstr,contents){
	$.dialog.list[idstr].content(contents,false,false);
	$.dialog.list[idstr].time(2);
	}
	
	
	function add(){
		submitRequest(document.forms[0].group_id);
	}
	
</script>
</head>
<body class="pop-body scrollbody">
<s:form action="ugroup_saveGrpmember" method="post" cssStyle="theForm"
		id="theForm">
			 <div class="table-ct">
            <table id="theTable" width="100%" border="0" cellspacing="0" class="blue-table sorttable">
			<!--  
				<tr>
				    <td class="til" align="center">
						备选配置文件
					</td>
					<td>
					<s:select list="theForm.resultList" id="user_id" name="" listKey="id" listValue="name" multiple="true" ondblclick="addselect('document.forms[0].user_id','document.forms[0].group_id');return false;"
					size="10" cssStyle="width:180px; height:224px; line-height:22px; border:none; border:1px solid #9dbcd9">
					</s:select>
					</td>
					<td class="til" align="center">
						已选配置文件
					</td>
					<td>
					<s:select list="theForm.selectedList" id="group_id" name=""  multiple="true" listKey="id" listValue="name" ondblclick="addselect('document.forms[0].group_id','document.forms[0].user_id');return false;"
					size="10" cssStyle="width:180px; height:224px; line-height:22px; border:none; border:1px solid #9dbcd9">
					</s:select>
					</td>
				</tr>
				-->
				<thead>
					<tr>
						<th >选择</th>
						<th onclick="sort(theTable,1,'string')">配置文件名称</th>
						<th onclick="sort(theTable,2,'string')">主机/用户</th>
						<!-- 
						<th>用户名</th>
						 -->
						
						<th onclick="sort(theTable,3,'string')">类型</th>
						<th onclick="sort(theTable,4,'string')">类别</th>
						<th onclick="sort(theTable,5,'string')" width="10%">路径</th>
						<th onclick="sort(theTable,6,'string')">描述</th>
					</tr>
				</thead>
                <tbody>
                <s:iterator value="theForm.selectedList" id="selectedList">
                		<tr>
                			<td><input type="checkbox" value="<s:property value="#selectedList.id"/>" name="theForm.id" id="id" checked="checked"/></td>
                			<td><s:property value="#selectedList.name"/></td>
                			<td><s:property value="#selectedList.ip"/>/<s:property value="#selectedList.username"/></td>
                			<!--  
                			<td><s:property value="#theBean.username"/></td>
                			-->
                			
                			<td><s:if test="#selectedList.type==0">xml</s:if>
                				<s:elseif test="#selectedList.type==1">properties</s:elseif>
                				<s:else>其他</s:else>
                			</td>
                			<td>
								<s:if test="#selectedList.category==0">通用</s:if>
								<s:elseif test="#selectedList.category==1">部署使用</s:elseif>
								<s:else>其他</s:else>
                			</td> 
                			<td align="center"><div class="hidden" title='<s:property value="#selectedList.path"/>'>
                				<s:property value="#selectedList.path"/></div></td>
                			<td><s:property value="#selectedList.description"/></td>
                		</tr>
                	</s:iterator>
				<s:iterator value="theForm.resultList" id="theBean">
                		<tr>
                			<td><input type="checkbox" value="<s:property value="#theBean.id"/>" name="theForm.id" id="id"/></td>
                			<td><s:property value="#theBean.name"/></td>
                			<td><s:property value="#theBean.ip"/>/<s:property value="#theBean.username"/></td>
                			<!--  
                			<td><s:property value="#theBean.username"/></td>
                			-->
                			
                			<td><s:if test="#theBean.type==0">xml</s:if>
                				<s:elseif test="#theBean.type==1">properties</s:elseif>
                				<s:else>其他</s:else>
                			</td>
                			<td>
								<s:if test="#theBean.category==0">通用</s:if>
								<s:elseif test="#theBean.category==1">部署使用</s:elseif>
								<s:else>其他</s:else>
                			</td> 
                			<td align="center"><div class="hidden" title='<s:property value="#theBean.path"/>'>
                				<s:property value="#theBean.path"/></div></td>
                			<td><s:property value="#theBean.description"/></td>
                		</tr>
                	</s:iterator>
                </tbody>
			</table>
			</div>
	</s:form>
</body>

</html:html>
