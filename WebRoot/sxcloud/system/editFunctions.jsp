<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<html:html locale="true">
<%@ include file="../common/link.jsp"%>
<head>
	<title></title>
	<script type="text/javascript" src="<%=request.getContextPath()%>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
	<script type="text/script" src="<%=request.getContextPath()%>/sxcloud/js/jqueryui/jquery-1.7.2.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/formvalidate/validate.js"></script>
	<script type="text/javascript">
    var path ="";
    function Trim(str){
	return str.replace(/^\s+|\s+$/g,"");
	}
	//排序号前十位必须有两个相连的0,如果不是
	//系统管理→用户管理→系统用户管理→权限查看,会报 js错误！
	//现在的排序号校验规则是:1.只能输入10位数字 2.后两位必须为0;
	function submitRequest(thisForm){    
		var funcid =  $("#FUNCID").val();
		var funname = $("#FUNNAME").val(); 
		var funcrequest = $("#FUNCREQUEST").val();
	    if(funcid==""){
	     $("#FUNCID").attr("class","required");
	      $("#FUNCID").focus();
	     return false  ;
	    }
	    var bool1 = isnumber(funcid);
	    if(!bool1)
		{
			alert("输入十位数字");
			$("#FUNCID").focus();
			$("#FUNCID").select();
			return false;
		}else{
		    if(thisForm.FUNCID.value.length <10){
			     alert("输入十位数字！");
			     thisForm.FUNCID.focus;
			     $("#FUNCID").select();
			     return false  ;
			    }
		}
		var s = funcid.substring(8,funcid.length);
		if(s != '00'){
			alert("排序号后两位必须是0");
			 $("#FUNCID").focus();
			 $("#FUNCID").select();
			 return false;
		}
	    if(funname==""){
		     $("#FUNNAME").attr("class","required");
		     $("#FUNNAME").focus();
		     return false  ;
	    }
	    if(funcrequest == ""){
	      $("#FUNCREQUEST").attr("class","required");
	      $("#FUNCREQUEST").focus();
	     return false  ;
	    }
	    thisForm.action =" function_saveFunctions.do?ICON="+path;
	    thisForm.submit();
	}
	
	
	/* 检测字符串是否为数字或者字母 */
	function isnumber(str)
	 {
	  var number_chars = "1234567890";
	    var i;
	    for (i=0;i<str.length;i++)
	      {
	          if (number_chars.indexOf(str.charAt(i))==-1) return false;
	      }
	      return true;
	 }	
	$(function(){
	$("#picture").hide();
	$("#ICON").click(function(){
		
		    		$.dialog({
		    			id:'vdi',
		    			title:'选择图标',
		    			width: '480px',
		    			height: '380px',
		    			max: true,
		    		    min: true,
		    			content: 'url:function_listIcon.do'
		    			});
		              }); 
		             })
	//展示图标列表 
	//function show_picture(){
		//$("#picture").show();
	//}
	
	function get_icon(icon_path){
		path = icon_path;
		path = path.substring(6,path.length);
		var root = '<%=request.getContextPath()%>';
		 var img = root+path;
		//var img = "<img src=+root+path+"/>"
		$("#pic").attr("src",img);
		$("#pic").show();
		//alert(theForm.ICON.value);
	}
	 
	function validate_FUNCID(){
		var FUNCID = theForm.FUNCID.value;
		var oper = '<%=request.getAttribute("oper")%>';
		var id = theForm.ID.value;
		if (theForm.FUNCID.value!='') {
			$.getJSON("function_validateFunc.do?FUNCID="+FUNCID+"&id="+id,{'time':new Date().toString()},function(data){
				if (data.result == -1) {
					document.getElementById("show").innerHTML = "排序号已经存在,请更改";
					if(oper==0){
					theForm.FUNCID.value='';
					}
					theForm.FUNCID.focus();
				}
			});
		}
	}
	
	
	
</script>
</head>
<body class="pop-body scrollbody">
	<div class="pd-20 bgcolor-1">
      <h2 class="utt-1">功能管理</h2>
         <div class="bord-1 pd-10">
	<s:form action="function_saveFunctions" method="post" cssStyle="theForm" id="theForm" >
		<input type="hidden" name="theForm.FUNCSID" value="<%=(String)request.getParameter("FUNCSID") %>">
		<s:hidden name="theForm.ID" id="ID"/>
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr>
				    <td class="til">
						排序号 <font color="red">*</font>
					</td>
					<td>
						<s:textfield name="theForm.FUNCID" cssClass="required inpt-2"  id="FUNCID" onblur="validate_FUNCID();" maxlength="10"></s:textfield>
					    <font color="red" size="1"> 注:输入10位数字,后两位必须为0</font>  
					    <span id="show"></span>               
					</td>
					<td class="til">
						功能名称 <font color="red">*</font>
					</td>
					<td>
						<s:textfield name="theForm.FUNNAME" id="FUNNAME" cssClass="required inpt-2"></s:textfield>
					</td>
					  <td class="til">
						状态<font color="red">*</font>
					</td>
					<td>
						<s:select list="#{'0':'-失效-','1':'-生效-'}" name="theForm.STATUS" id="STATUS" cssClass="select-1">
                    	</s:select>
					</td>
				</tr>
				<tr>
				    <td class="til">
						类型 <font color="red">*</font>
					</td>
					<td >
					<s:select list="#{'1':'菜单类型','2':'按钮类型'}" name="theForm.TYPE" id="TYPE" cssClass="select-1">
                    	</s:select>
					</td>
				    <td class="til">
						连接地址 <font color="red">*</font>
					</td>
					<td>
					<s:textfield name="theForm.FUNCREQUEST" id="FUNCREQUEST" cssClass="required inpt-2"></s:textfield>
					</td>
					<td class="til">
						加载是否刷新 <font color="red">*</font>
					</td>
					<td>
					<s:select list="#{'1':'刷新','0':'不刷新'}" name="theForm.ISREFRESH" id="ISREFRESH" cssClass="select-1">
                    	</s:select>
					</td>
				</tr>
				
				<tr>
					<td class="til">
						选择图标
					</td>
					<td >
					<input type="button" name="theForm.ICON"  id="ICON" style="height: 30px;width: 60px;" value="选择图标" readonly="readonly"></input>
					<s:if test="theForm.ICON==null || theForm.ICON==''">
						<img src="<%=request.getContextPath()%>/newUI/newUI/images/icon_08.png" id="pic" style="height: 30px;width: 30px;vertical-align: -11px;"/>
					</s:if>
					</td>
				    <td class="til">
						备注
					</td>
					<td >
					<s:textarea name="theForm.REMARK" cols="50" rows="3" id="REMARK" maxlength="300" cssClass="textarea-1"></s:textarea>
					</td>		
				</tr>
				<tr>
					<td colspan="6" class="btnCenter">
						<span class="ubtn-green"><input type="button"  value="确定" onclick="javascript:submitRequest(document.getElementById('theForm'));return false;" /></span>
						<span class="ubtn-orange mgl-20"><input type="button"  value="重置" onclick="javascript:void(document.getElementById('theForm').reset());return false;"></span>
						<span class="ubtn-blue mgl-20"><input type="button"  value="返回" onclick="window.history.back()"/></span>
					</td>
				</tr>
			</table>
	</s:form>
	</div>
        </div>
    </div>
</body>

</html:html>
