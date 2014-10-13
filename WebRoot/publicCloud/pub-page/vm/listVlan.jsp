<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" %>
<%@    taglib prefix="s" uri="/struts-tags" %>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>控制中心</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/publicCloud/pub-ui/css/default.css" type="text/css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/publicCloud/pub-ui/js/jquery/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/publicCloud/pub-ui/js/dialog/lhgdialog.min.js?skin=default"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/publicCloud/pub-ui/js/ui.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/publicCloud/pub-ui/js/order.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/publicCloud/pub-ui/js/common.js"></script>
    <script type="text/javascript">
        var api = frameElement.api;
        var w = api.opener;
        $(function () {
            api.button({
                        id: 'OkAnd',
                        name: '确定',
                        callback: selectVlan,
                        focus: true
                    },
                    {
                        id: 'cancle',
                        name: '取消'
                    });
        });

        function selectVlan() {
            var vlanId = '';
            var count = 0;
            var vmUuid = '<s:property value="obj.VH_UUID" />';
            var connectCode = '<s:property value="obj.connectId" />';
            $(":checkbox:checked").each(function () {
                vlanId = $(this).attr("objId");
                count++;
            });
            if (count > 1) {
                alertMsg('只能选择一个网络');
                return false;
            }
            var url = "vm_joinNetWork.do?obj.VH_UUID=" + vmUuid + "&obj.connectId=" + connectCode + "&obj.VH_NETWORK=" + vlanId;
            //alert(url);
            w.selectVlan(url);
        }
    </script>
</head>

<body>
<!--container star-->
<div class="container">
    <!--col-c7 star-->
    <div class="col-c7">
        <table border="0" width="100%" class="table-f4 js_table_f4">
            <tr>
                <th><input type="checkbox" class="vhid" id="checkAll"/></th>
                <th>名称</th>
                <!-- <th>IP地址</th>
                <th>子网掩码</th>
                <th>网关</th>-->
                <th>状态<!-- <b class="b-p"></b> --></th>
                <th>类型<!-- <b class="b-p"></b> --></th>
                <th>带宽(Mbps)</th>
                <th>申请时间</th>
            </tr>
            <s:iterator value="resultList" id="theBean">
                <tr>
                    <td>
                        <input type="checkbox" name="bb" class="code"
                               objId='<s:property value="#theBean.id" />'
                                />
                        </span>
                    </td>
                    <td><span class="font-blue"><s:property value="#theBean.name"/></span></td>
                        <%-- <td><span class="font-blue"><s:property value="#theBean.ip" /></span></td>
                        <td><span class="font-blue"><s:property value="#theBean.subnet_mask" /></span></td>
                        <td><span class="font-blue"><s:property value="#theBean.gateway" /></span></td> --%>
                    <td>
                        <s:if test="#theBean.isused!=0">
                            <span class="font-yellow">已使用</span>
                        </s:if>
                        <s:else>
                            <span class="font-blue">未使用</span>
                        </s:else>
                    </td>
                    <td>
                        <s:if test="#theBean.vlan_type==0">
                            <span class="font-yellow">内网</span>
                        </s:if>
                        <s:else>
                            <span class="font-blue">外网</span>
                        </s:else>
                    </td>
                    <td><span class="font-blue"><s:property value="#theBean.flow_size"/></span></td>
                    <td><span class="font-blue"><s:property value="#theBean.create_time"/></span></td>
                    </td>
                </tr>
            </s:iterator>
        </table>

        <!-- 分页start -->
        <div class="page mgt34-b25">
            <jsp:include page="../../inc/fenye.jsp?formId=theForm"/>
        </div>
        <!-- 分页end -->
        </form>
    </div>
    <!--main-c1 end-->
    <div class="clear"></div>
</div>
<!--col-c7 end-->
</div>
</body>

</html>
