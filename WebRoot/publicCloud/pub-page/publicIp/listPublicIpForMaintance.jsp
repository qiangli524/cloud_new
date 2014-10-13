<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<head>
    <link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet"
          type="text/css"/>
    <link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet"
          type="text/css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=iblue"></script>
    <%@ page import="com.sitech.basd.sxcloud.rsmu.web.util.page.image.ImageUtil" %>
    <%!
        public String getImageTag(HttpServletRequest request, String path) {
            return ImageUtil.getImageTag(request, path);
        }%>
    <title></title>
    <style type="text/css">
        div.hidden {
            width: 170px;
            height: 30px;
            overflow: hidden;
            white-space: nowrap;
            text-overflow: ellipsis;
            text-overflow: ellipsis; /* IE/Safari */
            -ms-text-overflow: ellipsis;
            -o-text-overflow: ellipsis; /* Opera */
            -moz-binding: url("ellipsis.xml#ellipsis"); /*FireFox*/
        }
    </style>
    <style type="text/css">
        .font-more {
            width: 80px;
            height: 20px;
            line-height: 20px;
            overflow: hidden;
            white-space: nowrap;
            display: block;
            -o-text-overflow: ellipsis;
            text-overflow: ellipsis;
        }
    </style>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
    <script type="text/javascript">
        $(function () {
            $check = $(":checkbox");
            $check.unbind().live("click", function () {
                $check.not(this).attr("checked", false);
            });
        })

        $(function () {
            $(".query").click(function () {
                if ($(".query-form").is(":visible")) {
                    $(".query-form").slideUp("slow");
                } else {
                    $(".query-form").slideDown("slow");
                }
            });

            $("#searchForm").click(function () {
                $("#theForm").submit();
            });

            $("#resetForm").click(function () {
                $("#ip").val(null);
                $("#inip").val(null);
                $("#ipStatus").val("-1");
            });

            $("#addForm").click(function () {
                $.dialog({
                    id: 'addip',
                    title: '添加公网ip',
                    width: '500px',
                    height: '320px',
                    lock:true,
                    content: 'url:publicip_add.do?oper=add',
                });
            });

            $("#editForm").unbind().live("click", function () {

                if ($(":checkbox:checked").length == 0) {
                    alert("你好,请至少选择一项来进行修改");
                    return false;
                }
                var dhcpid = "";
                var intra = "";
                $(":checkbox:checked").each(function () {
                    dhcpid += $(this).attr("ipaddr");
                    intra = $(this).attr("intra");
                });

                if (intra.length > 0) {
                    alert("该公网ip已经与内网ip进行了绑定，不允许修改");
                    return false;
                }

                $.dialog({
                    id: 'editip',
                    title: '编辑公网ip',
                    width: '520px',
                    height: '300px',
                    lock:true,
                    content: 'url:publicip_update.do?ipObj.ipaddress=' + dhcpid + '&oper=edit'
                });
            });

            $("#deleteForm").unbind().live("click", function () {

                if ($(":checkbox:checked").length == 0) {
                    alert("你好,请至少选择一项来进行删除");
                    return false;
                }

                var intra = "";
                var dhcpid = "";
                $(":checkbox:checked").each(function () {
                    dhcpid += $(this).attr("ipaddr");
                    intra = $(this).attr("intra");
                });

                if (intra.length > 0) {
                    alert("该公网ip已经与内网ip进行了绑定，不允许删除");
                    return false;
                }

                if (confirm("确定要删除?")) {
                    var url = "publicip_delete.do?ipObj.ipaddress=" + dhcpid;
                    $.ajax({
                        type: 'post',
                        url: url,
                        dataType: 'text',
                        success: function (msg) {
                            if (msg == -1) {
                                alert("删除失败");
                            } else {
                                $("#theForm").submit();
                            }
                        }
                    });
                }
            });
        });

        function saveDhcp(theform) {
            $.ajax({
                type: "post",
                url: "publicip_save.do?" + theform,
                dataType: "text",
                success: function (data) {
                    if (data == -1) {
                        alert("保存失败");
                    } else {
                        $("#theForm").submit();
                    }
                }
            });
        }
    </script>
</head>
<body>
<s:form action="publicip_list.do" method="post" id="theForm" cssStyle="theForm">
    <div class="scrollbody">
        <div class="query">
            <div class="title"><%=getImageTag(request, "query-icon.gif")%>
            </div>
        </div>
        <div class="box on">
            <div class="query-form">
                <table width="100%" class="querytable" border="0">
                    <tr>
                        <td class="til">公网IP地址:</td>
                        <td><s:textfield name="ipObj.ipaddress" id="ip" cssClass="txt"></s:textfield></td>
                        <td class="til">内网IP地址:</td>
                        <td><s:textfield name="ipObj.intranet_ip" id="inip" cssClass="txt"></s:textfield></td>
                        <td class="til">状态:</td>
                        <td>
                            <s:select list="#{'-1':'请选择', '0':'可用','1':'已分配'}" name="ipObj.status"
                                      id="ipStatus"></s:select>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="8" class="btns">
                            <div>
                                <input type="button" class="thickbox btn-style02" value="查询" id="searchForm"/>
                                <input type="button" class="btn-style02" value="重置" id="resetForm"/>
                            </div>
                        </td>
                    </tr>
                </table>
            </div>

            <div class="blue-wrap noborder" style="bottom:2px;top:100px;left:5px;right:5px;overflow-y:auto;">
                <div class="table-head">
                    <ul class="btns">
                        <li><input type="button" class="thickbox btn-style02" value="增加" id="addForm"/></li>
                        <li><input type="button" class="thickbox btn-style02" value="修改" id="editForm"/></li>
                        <li><input type="button" class="thickbox btn-style02" value="删除" id="deleteForm"/></li>
                    </ul>
                    <jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm"/>
                </div>
                <div class="table-ct">
                    <table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
                        <thead>
                        <tr>
                            <th>选择</th>
                            <th onclick="sort(theTable,1,'string')">公网IP地址</th>
                            <th onclick="sort(theTable,2,'string')">内网IP地址</th>
                            <th onclick="sort(theTable,3,'String')">绑定的实体</th>
                            <th onclick="sort(theTable,4,'string')">状态</th>
                            <th onclick="sort(theTable,5,'string')">端口</th>
                            <th onclick="sort(theTable,6,'string')">描述</th>
                        </tr>
                        </thead>
                        <tbody>
                        <s:iterator value="resultList" id="theBean">
                            <tr>
                                <td><input name="checkboxid" type="checkbox"
                                           ipaddr='<s:property value="#theBean.ipaddress"/>'
                                           intra='<s:property value="#theBean.intranet_ip"/>'/>
                                </td>
                                <td><s:property value="#theBean.ipaddress"/>
                                </td>
                                <td><s:property value="#theBean.intranet_ip"/>
                                </td>
                                <td><s:property value="#theBean.entity_name"/>
                                </td>
                                <td><s:if test="#theBean.status==1">已分配</s:if> <s:else>可用</s:else>
                                </td>
                                <td><s:property value="#theBean.addr_group"/>
                                </td>
                                <td align="center">
											<span style="color: black;" class="font-more"
                                                  title='<s:property value="#theBean.description"/>'>
												<s:property value="#theBean.description" default="--"/>
											</span>
                                </td>
                            </tr>
                        </s:iterator>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</s:form>
</body>
