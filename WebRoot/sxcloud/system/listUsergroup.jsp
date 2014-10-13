<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" %>
<%@ include file="../common/taglib.jsp" %>
<%@ include file="../common/link4a.jsp" %>
<%@ include file="../common/view.jsp" %>
<html:html locale="true">
    <head>
        <title></title>
        <script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/js/checkbox.js"></script>
        <script type="text/javascript">

            $(function () {
                $("#datepicker1").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});
                $("#datepicker2").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});
                var msg = '<%=request.getAttribute("msg") %>';
            })

            function resetForm(theForm) {
                theForm.GROUPNAME.value = '';
                theForm.STATUS.value = '-1';
            }

            function searchRequest() {
                theForm.submit();
            }
            function addRequest() {
                theForm.action = 'ugroup_addUsergroup.do'
                theForm.submit();
            }
            function modRequest() {
                var couterNum = 0;
                var checkboxids = document.getElementsByName("checkboxid");
                if (checkboxids != null && checkboxids.length > 0) {
                    for (var i = 0; i < checkboxids.length; i++) {
                        if (checkboxids[i].checked) {
                            couterNum = couterNum + 1;
                            theForm.ID.value = checkboxids[i].value;
                        }
                    }
                }
                if (couterNum == 0) {
                    alert("请勾选一条信息");
                    return false;
                } else if (couterNum > 1) {
                    alert('只能选择一项进行修改');
                    return false;
                }
                theForm.action = 'ugroup_modUsergroup.do'
                theForm.submit();
            }
            function delRequest() {
                var couterNum = 0;
                var checkboxids = document.getElementsByName("checkboxid");
                if (checkboxids != null && checkboxids.length > 0) {
                    for (var i = 0; i < checkboxids.length; i++) {
                        if (checkboxids[i].checked) {
                            couterNum = couterNum + 1;
                            theForm.ID.value = checkboxids[i].value;
                        }
                    }
                }
                if (couterNum == 0) {
                    alert("请勾选一条信息");
                    return false;
                } else if (couterNum > 1) {
                    alert('只能选择一项进行删除');
                    return false;
                }
                if (confirm("确定要删除?")) {
                    theForm.action = 'ugroup_delUsergroup.do'
                    theForm.submit();
                }
            }
        </script>
    </head>
    <body>
    <div class="mainbody">
        <s:form action="ugroup_listUsergroup" method="post" cssStyle="theForm" id="theForm">
            <s:hidden name="theForm.ID" id="ID"></s:hidden>
        <div class="pd-20 bgcolor-1">
            <h2 class="utt-1">用户组管理</h2>

            <div class="bord-1 pd-10">
                <div class="clearfix mgt-10">
                    <label class="vm">用户组名称：</label>
                    <s:textfield name="theForm.GROUPNAME" cssClass="inpt-1 vm"
                                 id="GROUPNAME" maxlength="30"></s:textfield>
                    <label class="mgl-20 vm">状态：</label>
                    <s:select cssClass="select-1 vm" list="#{'-1':'请选择','1':'有效','0':'无效'}"
                              name="theForm.STATUS" id="STATUS"></s:select>
                    <span class="ubtn-1 mgl-20"><input type="button" onclick="javascript:searchRequest()"
                                                       value="查询"/></span>
                    <span class="ubtn-2 mgl-20"><input type="button"
                                                       onclick="javascript:resetForm(document.getElementById('theForm'))"
                                                       value="重置"/></span>
                </div>
                <div class="utt-2 mgt-20">
                    <a class="icon-add" href="javascript:void(0)" onclick="addRequest();return false;">新增</a>
                    <a class="icon-modify" href="javascript:void(0)" onclick="modRequest();return false;">修改</a>
                    <a class="icon-del" href="javascript:void(0)" onclick="delRequest();return false;">删除</a>
                </div>
                <table width="100%" cellspacing="0" border="0" class="blue-table sorttable" id="theTable">
                    <thead>
                    <tr>
                        <th>
                            选择
                        </th>
                        <th onclick="sort(theTable,1,'string')">
                            用户组名称
                        </th>
                        <th onclick="sort(theTable,2,'string')">
                            状态
                        </th>
                        <th onclick="sort(theTable,3,'string')">
                            备注
                        </th>
                        <th onclick="sort(theTable,4,'date')">
                            更新时间
                        </th>
                        <th onclick="sort(theTable,5,'string')">
                            创建人
                        </th>
                        <th>
                            成员设置
                        </th>
                        <th>
                            权限设置
                        </th>
                    </tr>
                    </thead>
                    <tbody>
                    <s:iterator value="theForm.resultList" id="theBean">
                        <tr>
                            <td>
                                <input name="checkboxid" type="checkbox"
                                       value="<s:text name="#theBean.ID"/>"/>
                            </td>
                            <td>
                                <s:property value="#theBean.GROUPNAME"/>
                            </td>
                            <td>
                                <s:if test="#theBean.STATUS==1">
                                    有效
                                </s:if>
                                <s:elseif test="#theBean.STATUS==0">
                                    无效
                                </s:elseif>
                            </td>
                            <td>
                                <s:if test="#theBean.REMARK!=null && ''!=#theBean.REMARK">
                                    <s:property value="#theBean.REMARK"/>
                                </s:if>
                                <s:else>
                                    暂无
                                </s:else>
                            </td>
                            <td>
                                <s:if test="#theBean.CREATETIME!=null">
                                    <s:property value="#theBean.CREATETIME"/>
                                </s:if>
                                <s:else>
                                    暂无
                                </s:else>
                            </td>
                            <td>
                                <s:property value="#theBean.NAME"/>
                            </td>
                            <td>
                                <a href="ugroup_editGrpmember.do?GROUPID=<s:text name="#theBean.ID"/>&FUNCSID=<%=(String) request.getParameter("FUNCSID")%>">
                                    成员设置 </a>
                            </td>
                            <td>
                                <a href="ugroup_editGroupAuthority.do?GROUPID=<s:text name="#theBean.ID"/>&FUNCSID=<%=(String) request.getParameter("FUNCSID")%>">
                                    权限设置 </a>
                            </td>
                        </tr>
                    </s:iterator>
                    </tbody>
                </table>
                <div class="pages mgb-10"><!-- 分页 -->
                    <jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm"/>
                </div>
            </div>
            </s:form>
        </div>
    </body>
</html:html>
