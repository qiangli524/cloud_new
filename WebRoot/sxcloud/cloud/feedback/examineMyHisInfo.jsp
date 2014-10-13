<%@ page  language="java" contentType="text/html; charset=gb2312"%>
<%@ include file="/common/taglib.jsp" %>
<html:html locale="true">
<head>  
<%@ include file="/common/link.jsp" %> 
 <title><bean:message key="msg.jsp.title" arg0="用户管理"/></title>
 <script language="javascript" src="/js/ClientFunc.js"></script>
<script language="javascript">
String.prototype.Trim  = function(){return this.replace(/^\s+|\s+$/g,"");}
	function BtnSave_OnClick(thisForm){
		
			  thisForm.action_type.value = "loadListAffiriFeedBackInfo";
		 	  thisForm.submit();
		 
	      return true;
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

		if (!checkEmpty(obj.ID,'ID')) return false;
		if (!checkEmpty(obj.SUBMIT_TIME,'提交时间')) return false;
		if (!checkEmpty(obj.TYPE_ID,'反馈类型')) return false;
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
<%
      
   String id="";
   String subtime="";
   String title="";
   String type_id="";
   String df_info="";
   String sentto_employe="";
   String login_id="";
   String if_affirm="";
   String enable="";
   String enable1="";
   String moduleId=(String)session.getAttribute("moduleId");
   if ( moduleId != null){
     if(!moduleId.equals("null"))
       naviURL=NaviDisplay.getNaviURLA(moduleId,false,"main");       
	}   
	
	if(request.getAttribute("ID")!=null)
	          id=(String)request.getAttribute("ID");
	
	if(request.getAttribute("SUBMIT_TIME")!=null)
	          subtime=(String)request.getAttribute("SUBMIT_TIME");   
	
	if(request.getAttribute("TITLE")!=null)
	          title=(String)request.getAttribute("TITLE");
	
	if(request.getAttribute("TYPE_ID")!=null)
	          type_id=(String)request.getAttribute("TYPE_ID");
	
	if(request.getAttribute("DF_INFO")!=null)
	          df_info=(String)request.getAttribute("DF_INFO");
	
	if(request.getAttribute("SENTTO_EMPLOYE")!=null)
	          sentto_employe=(String)request.getAttribute("SENTTO_EMPLOYE");
	
	if(request.getAttribute("LOGIN_ID")!=null)
	          login_id=(String)request.getAttribute("LOGIN_ID");
	
	if(request.getAttribute("IF_AFFIRM")!=null)
	          if_affirm=(String)request.getAttribute("IF_AFFIRM");          
	if(request.getAttribute("ENABLE")!=null)
	          enable1=(String)request.getAttribute("ENABLE");           
	if(enable1.equals("0")){
		enable="无效";
	}else {
		enable="有效";
	}
	 
%>
</head>

<body>

<jsp:include page="/common/title.jsp" flush="true"/>
			<div id="frame">
		  <div class="tableheadbg"><img src="images/dot_tablehead.gif" width="13" height="13">信息查看<span class="darkblue"></span>
		  <font color="#FF0000">&nbsp; <logic:messagesPresent message="true">
		           <html:messages id="msg" message="true"> <%=msg%> </html:messages>
		   </logic:messagesPresent> </font>
  		</div>
	<table width="100%" height="83" border="0" cellpadding="0"
		cellspacing="0" class="input_bg">
<html:form action="/loadListAffiriFeedBackInfo" method="post" styleId="theForm">
  <bean:define  id="theForm" name="publishFeedBackForm" />
  <html:hidden property="action_type" value=""/>

  <tr>
    <td width="15%" class="input_bg">ID <font color="#FF0000"></font> ：</td>
    <td width="85%" class="input_bg" colspan="3"><html:text property="ID"  styleClass="input" value="<%=id%>" readonly="true" /></td>
    
  </tr>
  <tr>
    <td width="15%" class="input_bg">提交时间: <font color="#FF0000"></font> ：</td>
    <td width="85%" class="input_bg" colspan="3"><html:text property="SUBMIT_TIME" styleClass="input" value="<%=subtime%>" readonly="true" /></td>
    
  </tr>
  
  <tr>
    <td width="15%" class="input_bg">反馈类型: <font color="#FF0000"></font> ：</td>
    <td width="85%" class="input_bg" colspan="3"><html:text property="TYPE_ID"  styleClass="input" value="<%=type_id%>" readonly="true" /></td>
    
  </tr>
  
  <tr>
    <td width="15%" class="input_bg">反馈信息名称: <font color="#FF0000"></font> ：</td>
    <td width="85%" class="input_bg" colspan="3"><html:text property="TITLE"  styleClass="input" value="<%=title%>" readonly="true" /></td>
    
  </tr>
  <tr>
    <td class="input_bg">反馈信息：</td>
    <td class="input_bg" colspan="3"><html:textarea property="DF_INFO" cols="40" rows="3"  styleClass="input" value="<%=df_info%>" readonly="true" /></td>

  </tr>
  
  <tr>
    <td class="input_bg">反馈信息接收人：</td>
    <td class="input_bg" colspan="3"><html:text property="SENTTO_EMPLOYE"  styleClass="input" value="<%=sentto_employe%>" readonly="true" /></td>

  </tr>
  <tr>
    <td class="input_bg">反馈信息提交人：</td>
    <td class="input_bg" colspan="3"><html:text property="LOGIN_ID"  styleClass="input" value="<%=login_id%>" readonly="true" /></td>

  </tr>
  
 <tr>
    <td class="input_bg">生效：</td>
    <td colspan="3" class="input_bg"><html:text property="ENABLE"  styleClass="input" value="<%=enable%>" readonly="true" /></td>
    </tr>
</table>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr  >
    <td  >
    <div align="center">
   <input type="image" src="images/sc_button_9_3.gif"   onclick="BtnSave_OnClick(document.theForm);return false;"/>  
    </div></td></tr>
</table>


</div>
</html:form>
</body>
</html:html>
