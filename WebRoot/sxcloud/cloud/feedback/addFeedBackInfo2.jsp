<%@ page  language="java" contentType="text/html; charset=gb2312"%>
<%@ include file="/common/taglib.jsp" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<html:html locale="true">
<head>  
<%@ include file="/common/link.jsp" %> 
 <title><bean:message key="msg.jsp.title" arg0="用户管理"/></title> 
 <script language="javascript" src="/js/ClientFunc.js"></script>
<script language="javascript">
String.prototype.Trim  = function(){return this.replace(/^\s+|\s+$/g,"");}
	function BtnSave_OnClick(thisForm){
		if( confirm("确定要保存吗？")  && checkValid(thisForm) ){
		     document.theForm.action = "/saveFeedBackInfo.do";
			document.theForm.submit();
			
	      return true;
		}
		return false;
	}
	function BtnSelect_OnSelectAll(obj){
		if(obj != null && obj.length > 0 ){
           for(var i=0;i<obj.length;i++){
               obj[i].selected="true";
           }
        }
	}
	function BtnNew_OnClick(thisForm){
		thisForm.submit();
		return true;
	}

	function checkValid(obj){

		if (!checkEmpty(obj.SUBMIT_TIME,'提交时间')) return false;
		if (!checkEmpty(obj.TYPE_ID,'类型ID')) return false;
		if (!checkEmpty(obj.TITLE,'反馈信息名称')) return false;
		if (!checkEmpty(obj.DF_INFO,'反馈信息')) return false;
		if (!checkEmpty(obj.SENTTO_EMPLOYE,'反馈信息接收人')) return false;
		if (!checkEmpty(obj.LOGIN_ID,'反馈信息提交人')) return false;
		if (!checkEmpty(obj.IF_AFFIRM,'生效')) return false;
	
		return true;
	}
	 function isdigit(s)
            {
            var r,re;
            re = /\d*/i;  
            r = s.match(re);
            return (r==s)?1:0;
            }
</script>

<script language="JavaScript">

	var xmlHttp;  
	var i=0         
	var a;
	var currentInfo = "";          
	var counter = 1;              
	var isReading = true;       
	var check;            
	function createXmlHttp() {
	    if (window.XMLHttpRequest) {
	       xmlHttp = new XMLHttpRequest();                
	    } else {
	       xmlHttp = new ActiveXObject("Microsoft.XMLHTTP"); 
	    }
	}
	
	function getSelect(ID) {
	    createXmlHttp();
	 
	
	    xmlHttp.open("GET", "/common/GetLoginByDept.jsp?ID="+ID, false);
	    xmlHttp.setRequestHeader("If-Modified-Since","0");       
	    xmlHttp.send(null);
	
	    if (xmlHttp.readyState == 4) {
	
	            var pageInfo = eval("("+xmlHttp.responseText+")");       
	            var SelectNode=document.getElementById("SENTTO_EMPLOYE");
	            SelectNode.length=0;
	            for(var o in pageInfo){
	              SelectNode.appendChild(createSelect(o,pageInfo[o]));
	              }
	              
	          }
	}
	function getSelectCopy(ID) {
    createXmlHttp();
 

    xmlHttp.open("GET", "/common/GetLoginByDeptForCopy.jsp?ID="+ID, false);      
    xmlHttp.send(null);

    if (xmlHttp.readyState == 4) {
            var pageInfo = eval("("+xmlHttp.responseText+")");       
	            var SelectNode=document.getElementById("SENTTO_EMPLOYE");
	            SelectNode.length=0;
	            for(var o in pageInfo){
	              SelectNode.appendChild(createSelect(o,pageInfo[o]));
	              }
              
          }
}
	function createSelect(value,text){
	  var opt=document.createElement("option");
	  opt.setAttribute("value",value);
	  opt.appendChild(document.createTextNode(text));
	
	  if(check == value){
	    opt.selected=true;
	  }
	  i=i+1;
	  return opt;
	}
</script>

<script language="JavaScript" src="/js/popcalendar.js"></script>
<script language="JavaScript">
	function ChoiceUser()
	{
	  if(document.theForm.TODOSTATUS.value == 1){
	    dept.style.display = 'block';
	    user.style.display = 'block';
	  }else{
	    dept.style.display = 'none';
	    user.style.display = 'none';
	  }
	  if(document.theForm.TODOSTATUS.value == 3){
	  finish.style.display = 'block';
	  }else{
	  finish.style.display = 'none';
	  }
	}
	function init(){
	   finish.style.display = 'none';
	}
	
	dateFormat='yyyy-mm-dd'
</script>
<%
      
   List type = (List)request.getAttribute("type");
   String login_id="";
   String subtime="";
   String moduleId=(String)session.getAttribute("moduleId");
   if ( moduleId != null){
     if(!moduleId.equals("null"))
       naviURL=NaviDisplay.getNaviURLA(moduleId,false,"main");       
	}   
	
	if(request.getAttribute("login_id")!=null)
	          login_id=(String)request.getAttribute("login_id");
	          
	if(request.getAttribute("subtime")!=null)
	          subtime=(String)request.getAttribute("subtime");    
%>
</head>

<body>

<jsp:include page="/common/title.jsp" flush="true"/>
			<div id="frame">
		  <div class="tableheadbg"><img src="images/dot_tablehead.gif" width="13" height="13">新建信息<span class="darkblue"></span>
		  <font color="#FF0000">&nbsp; <logic:messagesPresent message="true">
		           <html:messages id="msg" message="true"> <%=msg%> </html:messages>
		   </logic:messagesPresent> </font>
  		</div>
	<table width="100%" height="83" border="0" cellpadding="0"
		cellspacing="0" class="input_bg">
<html:form action="/saveFeedBackInfo" method="post" styleId="theForm">
  <bean:define  id="theForm" name="publishFeedBackForm" />
  <html:hidden property="action_type" value=""/>
  <input type=hidden name="type"   value="firstpage"/>
  

  <tr>
    <td width="15%" class="input_bg">提交时间: <font color="#FF0000"></font> ：</td>
    <td width="85%" class="input_bg" colspan="3"><html:text property="SUBMIT_TIME" styleClass="input" readonly="true" value="<%=subtime%>" /></td>
    
  </tr>
  
  <tr>
    <td width="15%" class="input_bg">反馈类型: <font color="#FF0000"></font> ：</td>
    <td colspan="3" class="input_bg">
	 <html:select name="theForm" property="TYPE_ID">
    <%
       for(int x = 0;x<type.size();x++){
       String typeName = (String)type.get(x); 
    %>	   
	      <html:option value="<%=typeName%>"></html:option>     	   
    <% 
    	}
    %>
     </html:select>
	</td>
  </tr>
  
  <tr>
    <td width="15%" class="input_bg">反馈信息名称: <font color="#FF0000"></font> ：</td>
   <td width="85%" class="input_bg" colspan="3"><html:text property="TITLE"  styleClass="input"/></td>
  </tr>
  <tr>
    <td class="input_bg">反馈信息：</td>
    <td class="input_bg" colspan="3"><html:textarea property="DF_INFO" cols="40" rows="3"  styleClass="input"/></td>

  </tr>
  
 
  <tr>
		<td class="input_bg">
			<font color="#FF0000">&nbsp;</font>反馈信息接收人：
		</td>
		<td class="input_bg">
			<html:select name="theForm" property="SENTTO_EMPLOYE">
				<html:option value="sysadmin">sysadmin</html:option>
			</html:select>
		</td>
  </tr>
				
  <tr>
    <td class="input_bg">反馈信息提交人：</td>
    <td class="input_bg" colspan="3"><html:text property="LOGIN_ID"  styleClass="input" value="<%=login_id%>" /></td>

  </tr>
  
 <tr>
    <td class="input_bg">生效：</td>
    <td colspan="3" class="input_bg">
    <html:select name="theForm" property="ENABLE">
      <html:option value="1">有效</html:option>
     	<html:option value="0">无效</html:option>
      
    </html:select>
    </td>
    </tr>
</table>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr  >
    <td  >
      <div align="center">
  <input type="image" src="images/sc_button_9_11.gif"   onclick="BtnSave_OnClick(document.theForm);return false;"/>
&nbsp;&nbsp;
  <input type="image" src="images/sc_button_9_2.gif"   onclick="void(document.theForm.reset());return false;"/>
   
    </div></td></tr>
</table>

</div>

</html:form>
</body>
</html:html>
