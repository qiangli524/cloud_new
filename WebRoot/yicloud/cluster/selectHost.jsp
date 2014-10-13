<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<html:html locale="true">
<%@ include file="../../sxcloud/common/link.jsp"%>
<head>
	<title></title>
	<script type="text/javascript" src="<%=request.getContextPath()%>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
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
	   w.getHost(len,str);
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
		<input type="hidden" name="grpForm.FUNCSID" value="<%=(String)request.getParameter("FUNCSID") %>" />
		<input type="hidden" name="grpForm.GROUPID" id="GROUPID" value="<%=(String)request.getParameter("GROUPID") %>"/>
		<input type="hidden" id="grpForm.USERIDS" name="USERIDS" value=""/>
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr>
				    <td class="til" align="center">
						可用主机
					</td>
					<td>
					<s:select list="theForm.vmList" id="user_id" name="" listKey="entityId" listValue="name" multiple="true" ondblclick="addselect('document.forms[0].user_id','document.forms[0].group_id');return false;"
					size="10" cssStyle="width:220px; height:224px; line-height:22px; border:none; border:1px solid #9dbcd9">
					</s:select>
					</td>
					<td class="til" align="center">
						故障切换主机
					</td>
					<td>
					<s:select list="theForm.forSelectList" id="group_id" name=""  multiple="true" listKey="entityId" listValue="name" ondblclick="addselect('document.forms[0].group_id','document.forms[0].user_id');return false;"
					size="10" cssStyle="width:220px; height:224px; line-height:22px; border:none; border:1px solid #9dbcd9">
					</s:select>
					</td>
				</tr>
				
			</table>
	</s:form>
</body>

</html:html>
