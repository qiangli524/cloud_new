<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" %>
<%@ include file="../../sxcloud/common/taglib.jsp" %>
<%@ include file="../../sxcloud/common/view.jsp" %>
<head>
    <title></title>
    <link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
    <script type="text/javascript">
        var userids='<%=request.getAttribute("userids")%>';//用户的id
     	var api = frameElement.api;
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
    		$("#query").click(function(){
	    			if($("#query-form").is(":visible")){
	    				$("#query-form").slideUp("slow");
	    			}else{
	    				$("#query-form").slideDown("slow");
	    			}
	    	});
	    	$("#reset").click(function(){
	    			clear();
	    	});
    	    if(userids!=null&&userids!=""){
    	        var array=userids.split(",");
    	        for(var i=0;i<array.length;i++){
    	            $("[value='"+array[i]+"']").attr("checked",true);
    	        }
    	    }
       });   

       //点击添加用户时候，把用户id和对应ip形成已逗号分隔的字符串返回，注：id ip一一对应。
       function selectUsers(){
       		var userids ="";//选择用户的id
       		var userips="";//选择用户的ip
       		$(":checkbox:checked").each(function(){
       			userids +=$(this).attr("value")+","; 
       			userips+=$(this).parent().next().text()+",";
        	 });
       		api.get("add").addUser(userids,userips);//需要调用窗口的id，大家都一样，都叫add
       }
      function searchRequest(){
      		theForm.action="usermanage_list.do";
			theForm.submit();
		}
		
		function clear(){
			$("#ip").attr("value","");
			$("#username").attr("value","");
		}
		function list(){
			theForm.action="usermanage_list.do";
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
    </script>
</head>
</head>
<body class="pop-body scrollbody">
    <s:form action="usermanage_list.do" method="post" id="theForm" cssStyle="theForm">
    <div class="pd-20 bgcolor-1">
    	 <h2 class="utt-1">用户列表</h2>
    	 <div class="clearfix mgt-10">
			<label class="vm">IP地址:</label>
				<s:textfield name="theForm.ip" id="ip"></s:textfield>
			<label class="mgl-20 vm">用户名:</label> 
				<s:textfield name="theForm.username" id="username"></s:textfield>
			<span class="ubtn-1 mgl-20"><input type="button" onclick="javascript:searchRequest()"value="查询"/></span>
            <span class="ubtn-2 mgl-20"><input type="button" onclick="javascript:void(document.getElementById('theForm').reset());return false;" value="重置"/></span>
		</div>
		<div class="utt-2 mgt-20">
                    <a class="icon-add" href="javascript:void(0)" onclick="javascript:add();">新增</a>
                    <a class="icon-del" href="javascript:void(0)" onclick="javascript:save();">保存</a>
        </div>
        <table id="theTable" width="100%" border="0" cellspacing="0" class="blue-table sorttable">
            	<thead id="table">
					<tr>
						<th>选择</th>
						<th onclick="sort(theTable,1,'string')">IP地址</th>
						<th onclick="sort(theTable,2,'string')">用户名</th>
						<th onclick="sort(theTable,3,'string')">密码</th>
					</tr>
				</thead>
                <tbody>
                	<s:iterator value="theForm.resultList" id="theBean">
                		<tr >
                			<td><input type="checkbox" value="<s:property value="#theBean.id"/>" name="checked" id="id"/></td>
                			<td><s:property value="#theBean.ip"/></td>
                			<td><s:property value="#theBean.username"/></td>
                			<td><s:property value="#theBean.password"/></td>
                		</tr>
                	</s:iterator>
                </tbody>
         </table>
    </div>
    </s:form>
</body>