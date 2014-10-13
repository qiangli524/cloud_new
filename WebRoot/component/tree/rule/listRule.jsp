<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../../sxcloud/common/taglib.jsp"%>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>


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
  	<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
    <script type="text/javascript">
    var example_id = '<%=request.getAttribute("example_id")%>';
    var hostIP = '<%=request.getAttribute("hostIP")%>';
    var type = '<%=request.getAttribute("type")%>';
    var task_id = '<%=request.getAttribute("task_id")%>';
    	$(function(){
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
	    			content: 'url:deployconfig_listConfigContent.do?id='+id
	    		});
            });
       });   
      function searchRequest(){
			theForm.submit();
	  }
		function deleteRule(){
			$('[name=theForm.id]:checkbox:checked').each(function(){
			var  $aa = $(this);
			var id = $(this).val();
			var url = "rule_delete.do?id="+id;
			 	$.ajax({
			  		type:"GET",
	                url:url,
	                data:"text",
	                async: false,
	                cache: false,
		            success: function(msg){
               		  if(msg==null){
                	  }else{
                		$aa.parent().parent().remove();
                	  }
              		}
				});
        	 });
		}
		
		function clear(){
			theForm.name.value = '';
			theForm.type.value = '';
		}
		
		function list(){
			theForm.submit();
		}
		
		//动态增加记录
		function dynamicAdd(name,msg,types){
			var str = '';
			var config_type = '';
			if(types==0){
				config_type = '正则表达式';
			}else if(types==1){
				config_type = '关键字模糊匹配';
			}else if(types==2){
				config_type='接口探测';
			}else{
				config_type= '其他';
			}
				str = '<tr>'+
						'<td><input type="checkbox" name="theForm.id" ' + 'value=' + '"'+ msg.id +'"'+ '/>' + '</td>' +
						'<td>'+name+ '</td>' +
						'<td>'+ config_type + '</td>' +
						
						'<td>'+msg.content+'</td>'+
						'<td>'+0+'</td>'+
						'<td>' + msg.description +'</td>'+
						'<td>' + msg.report_path +'</td>'+
						'<td>'+msg.rule_maker+'</td>'+
						'</tr>';
				$("#table").append(str);
		}
		
		//动态修改记录
		function dynamicUpdate(name,types,content,path,desc){
			$('[name=theForm.id]:checkbox:checked').each(function(){
        		var config_type = '';
        		if(types==0){
				config_type = '正则表达式';
			}else if(types==1){
				config_type = '关键字模糊匹配';
			}else if(types==2){
				config_type='接口探测';
			}else{
				config_type= '其他';
			}
			$(this).parent().next().text(name);
			$(this).parent().next().next().text(config_type);
			$(this).parent().next().next().next().text(content);
			$(this).parent().next().next().next().next().next().text(desc);
			$(this).parent().next().next().next().next().next().next().text(path);
        });
	}
	
	var api = frameElement.api;
		w = api.opener;
	function addRule(){
		w.$.dialog({
    			id:'addRule',
    			title:'添加验证规则',
    			width: '600px',
    			height: '400px',
    			max: true,
    		    min: true,
    		    lock:true,
    			content: 'url:rule_edit.do?task_id='+task_id
	    		});
	}
	
	function editRule(){
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
    			id:'editRule',
    			title:'修改验证规则',
    			width: '600px',
    			height: '400px',
    			max: true,
    		    min: true,
    		    lock:true,
    			content: 'url:rule_edit.do?task_id='+task_id+'&id='+id
	    		});
	}
    </script>
</head>
<body class="pop-body scrollbody" >
    <s:form action="deployconfig_list.do" method="post" id="theForm" cssStyle="theForm">
	<div class="box on" >
    <div class="blue-wrap noborder">
	<div class="table-head">
				<ul class="btns">
                    <li colspan="4" class="btnCenter">
                           <input type="button"  value="添加"  class="btn-style02" name="add" onclick="addRule();"/>
                         <input type="button"  value="修改" name="mod" class="btn-style02" onclick="editRule()"/>
                        <input type="button"  value="删除" name="" class="btn-style02"  onclick="javascript:deleteRule();"/>
                    </li>
                </ul>
    </div>
       <div class="table-ct" >
            <table id="theTable" width="100%" border="0" cellspacing="0" class="blue-table sorttable">
            	<thead>
					<tr>
						<th>选择</th>
						<th onclick="sort(theTable,1,'string')">规则名称</th>
						<th onclick="sort(theTable,2,'string')">类型</th>
						<th onclick="sort(theTable,3,'string')">内容</th>
						<th onclick="sort(theTable,4,'int')">执行次数</th>
						<th onclick="sort(theTable,5,'string')">描述</th>
						<th onclick="sort(theTable,6,'string')">报告路径</th>
						<th onclick="sort(theTable,7,'string')">规则制定人</th>
					</tr>
				</thead>
                <tbody id="table">
                	<s:iterator value="theForm.resultList" id="theBean">
                		<tr>
                			<td><input type="checkbox" value="<s:property value="#theBean.id"/>" name="theForm.id" id="id"/></td>
                			<td><s:property value="#theBean.name"/></td>
                			<td><s:if test="#theBean.type==0">正则表达式</s:if>
                				<s:elseif test="#theBean.type==1">关键字匹配</s:elseif>
                				<s:elseif test="theBean.type==2">接口探测</s:elseif>
                				<s:else>其他</s:else>
                			</td>
                			<td><s:property value="#theBean.content"/>	</td>
                			<td><s:property value="#theBean.num"/></td>  
                			<td align="center"><div class="hidden" title='<s:property value="#theBean.description"/>'>
                				<s:property value="#theBean.description"/></div></td>
                			<td align="center"><div class="hidden" title='<s:property value="#theBean.report_path"/>'>
                				<s:property value="#theBean.report_path"/></div></td>
                			<td><s:property value="#theBean.rule_maker"/></td>
                		</tr>
                	</s:iterator>
                </tbody>
            </table>
        </div>
        </div>
    </s:form>
</body>