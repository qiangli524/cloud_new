<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<html:html locale="true">
<%@ include file="../common/link.jsp"%>
<head>
	<title></title>
	<script type="text/javascript">
$(function(){
		$("#datepicker1").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});
		$("#datepicker2").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});

	})
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
    //按添加按钮时触发的函数
    function addselect(forselectForm,selectedForm) {
    var forForm = eval(forselectForm);
	var selectedForm = eval(selectedForm);
    if (cf_isNotObject(forForm))
       return false;
	if (cf_isNotObject(selectedForm))
       return false;
    var k;
    var len;
    len = selectedForm.length;
    for (k = 0; k < forForm.length; k++)
	{
       if (forForm.options[k].selected)
	   {
          olength = selectedForm.length;
     	  otext = forForm.options[k].text;
          ovalue = forForm.options[k].value;
          tag = 0
          for (i = 0; i < olength; i++)
		  {
            if (ovalue == selectedForm.options[i].value)
			{
               tag = 1;
            }
          }
          if (tag == 0)
		  {
             selectedForm.options[len] = new Option(otext, olength);
             selectedForm.options[len].value = ovalue;
             selectedForm.options[len].selected = true;
             len = len + 1;
          }
       }
    }
        deleteselected(forselectForm); 
  }
  //按删除按钮实现的功能
  function deleteselected(selectedForm){
   var objForm = eval(selectedForm);
   if(cf_isNotObject(objForm)) return false;
   var olength=objForm.length;
   var s=olength - 1;
   var j=0;
   for(i=s; i>=0; i--)
   {
	 if (objForm.options[i].selected)
	 {
       objForm.options[i]=null;
	   j=j+1;
 	 }
   }
   objForm.length=olength-j;
 }
 function submitRequest(thisForm){    
	   var selectedForm = eval(thisForm);
	   var   str = "" ;
	   len = selectedForm.length;
       for (k = 0; k < len; k++)
	   {
	      if(k>0)
	      str = str + ",";
          str = str + selectedForm.options[k].value;
	   }
	    document.getElementById('APPIDS').value = str ;
	    var USERID = theForm.USERID.value;
	    var ACCOUNT = theForm.ACCOUNT.value;
	    theForm.action='user_saveUserDataAuthority.do?USERID='+USERID+'&APPIDS='+str+'&ACCOUNT='+ACCOUNT;
	    document.getElementById('theForm').submit();
	}	
</script>
</head>
<body class="pop-body scrollbody">
<s:form action="user_saveUserDataAuthority" method="post" cssStyle="theForm"
		id="theForm">
		<input type="hidden" name="grpForm.FUNCSID" value="<%=(String)request.getParameter("FUNCSID") %>"/>
		<input type="hidden" name="grpForm.USERID" id="USERID" value="<%=(String)request.getParameter("USERID") %>"/>
		<input type="hidden" name="grpForm.ACCOUNT" id="ACCOUNT"  value="<%=(String)request.getParameter("ACCOUNT") %>"/>
		<input type="hidden" id="APPIDS" name="grpForm.APPIDS" value=""/>
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr>
				    <td class="til">
						备选应用
					</td>
					<td>
					<s:select list="grpForm.selectedUsers" id="user_id" name="" listKey="ID" listValue="APPNAME" multiple="true" ondblclick="addselect('document.forms[0].user_id','document.forms[0].group_id');return false;"
					size="10" cssStyle="width:150px; height:224px; line-height:22px; border:none; border:1px solid #9dbcd9">
					</s:select>
					</td>
					<td class="til">
						已选应用
					</td>
					<td>
					<s:select list="grpForm.userForSelect" id="group_id" name="" listKey="ID" listValue="APPNAME" multiple="true" ondblclick="addselect('document.forms[0].group_id','document.forms[0].user_id');return false;"
					size="10" cssStyle="width:150px; height:224px; line-height:22px; border:none; border:1px solid #9dbcd9">
					</s:select>
					</td>
				</tr>
				<tr>
					<td colspan="10" class="btnCenter">
						<input type="button" class="thickbox btn-style02" style="margin-left: 10" value="确定"
							onclick="javascript:submitRequest(document.forms[0].group_id);return false;" />
						<input type="button" class="thickbox btn-style02" style="margin-right: 10"  value="返回"
							onclick="window.history.back()" />
					</td>
				</tr>
			</table>
	</s:form>
</body>

</html:html>
