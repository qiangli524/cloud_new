<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" %>
<%@ include file="../../../sxcloud/common/taglib.jsp" %>
<%@ include file="../../../sxcloud/common/view.jsp" %>
<head>
    <title></title>
    <link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
    <script type="text/javascript">
     	var userids='<%=request.getAttribute("userids")%>';//用户的id
     	var hostIP = '<%=request.getAttribute("hostIP")%>';//对应主机IP
     	var example_id = '<%=request.getAttribute("example_id")%>';
     	var example_ids = '<%=request.getAttribute("example_ids")%>';
     	var type = '<%=request.getAttribute("type")%>';//节点类型
     	var api = frameElement.api;
		var w = api.wer;
		 api.button({
		     id:'OkAnd',
		     name: '确定',
		     callback:selectUsers,
		     focus: true
		 },
		 {
		     id:'cancle',
		     name: '取消'
		 });

		 //把已经选择的用户的复选框，状态标志为已选中。
    	$(function(){
    	    if(example_ids!=null&&example_ids!=""){
    	        var array=example_ids.split(",");
    	        for(var i=0;i<array.length;i++){
    	            $("[example_id='"+array[i]+"']").attr("checked",true);
    	        }
    	    }
       });   

       //点击添加用户时候，把用户id和对应ip形成已逗号分隔的字符串返回，注：id ip一一对应。
       function selectUsers(){
       		var userids ="";//选择用户的id
       		var userips="";//选择用户的ip
       		var usernames = "";//用户的用户名
       		var example_ids = "";
       		$(":checkbox:checked").each(function(){
       			userids +=$(this).attr("value")+","; 
       			userips+=$(this).parent().next().text()+",";
       			usernames +=$(this).parent().next().next().text()+",";
       			example_ids +=$(this).attr("example_id")+",";
        	 });
        	
       		api.get("add").addUser(userids,userips,usernames,example_ids);//需要调用窗口的id，大家都一样，都叫add
       }
      function searchRequest(){
      		theForm.action="deployuser_list.do?hostIP="+hostIP;
			theForm.submit();
		}
		
		function clear(){
			theForm.ip.value = '';
			theForm.username.value = '';
		}
		function list(){
			theForm.action="deployuser_list.do?"+hostIP;
			theForm.submit();
		}
		
		//添加一条新纪录
		function add(){
			var str = '<tr name="new">'+'<td><input type="checkbox" name="checked"/></td>'+
			'<td>'+'<input type="text" name="ip"/>'+'</td>'+'<td>'+
			'<input type="text" name="username"/>'+'</td>'+
			'<td>'+'<input type="text" name="password"/>'+'</td>'+'</tr>';
			$("#table").after(str);
		}
		
		//保存新添加的记录
		function save(){
		var ip = '';
		var username ='';
		var password = '';
		$("[name='new']").each(function(index,data){
		 ip = $(data).children(":eq(1)").children("input").val();
		 username = $(data).children(":eq(2)").children("input").val();
		 password = $(data).children(":eq(3)").children("input").val();
		 $.ajax({
			  type:"GET",
              url:"usermanage_save.do?ip="+ip + '&username=' + username+'&password='+password,
              data:"text",
              async: false,
              cache: false,
	          success: function(msg){
                if(msg==-1){
                	alert(ip+"用户"+username+"已经存在,请更换");
                }else{
                	list();
                }
              }
		});
		});
		}
		//确定是否展示添加和保存按钮
		$(function(){
			if(type==2 || type ==3){
				$("#add").hide();
				$("#save").hide();
			}else{
				$("#add").show();
				$("#save").show();
			}
		})
    </script>
</head>
<body class="pop-body scrollbody">
    <s:form action="usermanage_list.do" method="post" id="theForm" cssStyle="theForm">
  	<%-- <div class="query">
	<div class="title"><%=getImageTag(request, "query-icon.gif")%></div>
	</div> --%>
	<div class="box on">
	<div class="query-form">
	<table width="100%" class="querytable" border="0">
		<%-- <tr>
			<td class="til">IP地址:</td>
			<td>
				<s:textfield name="theForm.ip" id="ip"></s:textfield>
			</td>
			<td class="til">用户名:</td>
			<td>
				<s:textfield name="theForm.username" id="username"></s:textfield>
			</td>
		</tr> --%>
		<tr>
			<td colspan="8" class="btns">
			<div><!-- <input type="button" class="thickbox btn-style02"
				value="查询" onclick="javascript:searchRequest();" />
				 <input type="button" class="btn-style02" value="重置"
				onclick="javascript:clear();" /> -->
				<input type="button" class="btn-style02" value="添加"
				onclick="javascript:add();" id="add"/>
				<input type="button" class="btn-style02" value="确定"
				onclick="javascript:save();" id="save"/>
			</div>
			</td>
		</tr>
	</table>
	</div>
	<!--query-form end -->
    <div class="blue-wrap noborder">
	<div class="table-head">
       <div class="table-ct" >
            <table id="theTable" width="100%" border="0" cellspacing="0" class="blue-table sorttable">
            	<thead id="table">
					<tr>
						<th>选择</th>
						<th onclick="sort(theTable,1,'string')">实例名称</th>
						<th onclick="sort(theTable,2,'string')">IP地址</th>
						<th onclick="sort(theTable,3,'string')">用户名</th>
						<th onclick="sort(theTable,4,'string')">密码</th>
					</tr>
				</thead>
                <tbody>
                	<s:iterator value="theForm.resultList" id="theBean">
                		<tr >
                			<td><input type="checkbox" value="<s:property value="#theBean.id"/>" name="checked" id="id" example_id='<s:property value="#theBean.example_id"/>'/></td>
                			<td><s:property value="#theBean.example_name"/></td>
                			<td><s:property value="#theBean.ip"/></td>
                			<td><s:property value="#theBean.username"/></td>
                			<td><s:property value="#theBean.password"/></td>
                		</tr>
                	</s:iterator>
                </tbody>
            </table>
        </div>
        </div>
    </s:form>
</body>