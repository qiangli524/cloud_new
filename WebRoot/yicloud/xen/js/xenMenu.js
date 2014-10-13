// 右键菜单层控制
// 右键菜单数组
var menuArr = new Array();
initMenuArr();
// 创建菜单数组
function initMenuArr() {
	menuArr[0] = "#addDataCenter";
	menuArr[1] = "#delDataCenter";
	menuArr[2] = "#conDataCenter";
	menuArr[3] = "#addCluster";
	menuArr[4] = "#delCluster";
	menuArr[5] = "#conCluster";
	menuArr[6] = "#virtual_add";
	menuArr[7] = "#app_deploy";
	menuArr[8] = "#host_conn";
	menuArr[9] = "#virtual_del";
	menuArr[10] = "#virtual_start";
	menuArr[11] = "#virtual_stop";
	menuArr[12] = "#virtual_pause";
	menuArr[13] = "#virtual_resume";
	menuArr[14] = "#virtual_reboot";
	menuArr[15] = "#virtual_conn";
	menuArr[16] = "#virtual_view";
	menuArr[17] = "#virtual_setting";
	menuArr[18] = "#host_add";
	menuArr[19] = "#host_del";
	menuArr[20] = "#virtual_monitor";
	menuArr[21] = "#host_monitor";
	menuArr[22] = "#add_nake";
	menuArr[23] = "#migrate";
	menuArr[24] = "#clone";
	menuArr[25] = "#templet";
	menuArr[26] = "#suspend";
	menuArr[27] = "#rename";
	menuArr[28] = "#login";
	menuArr[29] = "#fromModel";
	menuArr[30] = "#creatBareMachine";
	menuArr[31] = "#cloneVirtualMechine";
	menuArr[32] = "#snapshot";
	menuArr[33] = "#toTemplet";
	menuArr[34] = "#removeTem";
	menuArr[35] = "#moveToDataCenter";
	menuArr[36] = "#enterMaintenanceMode";
	menuArr[37] = "#creatVirtualMachine";
	menuArr[38] = "#exitMaintenanceMode";
	menuArr[39] = "#console";
	menuArr[40] = "#deleteData";
	menuArr[41] = "#addXenDataCenter";
	menuArr[42] = "#conXenCluster";
	menuArr[43] = "#connectCluster";
	menuArr[44] = "#operatingSystem";
	menuArr[45] = "#host_reboot";
	menuArr[46] = "#synchroData";
	menuArr[47] = "#add_sr";
	menuArr[48] = "#host_shutdown";
	menuArr[49] = "#addnic";
	menuArr[50] = "#wakeup";
	menuArr[51] = "#delXenDataCenter";
	menuArr[52] = "#virtual_console";
	menuArr[53] = "#createVMByTem";
	menuArr[54] = "#xendata";
	menuArr[55] = "#detachStore";
	menuArr[56] = "#forgetStore";
	menuArr[57] = "#destroyStore";
	menuArr[58] = "#reConnect";
	menuArr[59] = "#fixStore";
	menuArr[60] = "#move";
	menuArr[61] = "#export";
	menuArr[62] = "#cloneVMOnHost";
	menuArr[100]='#operting';
}
// 隐藏所有右键菜单
function hideMenuDiv() {
	// 隐藏所有 class="hide" 的 <li> 元素
	$("li.hide").hide();
}
// 根据参数显示右键--参数格式1:2:3:4
function showRm(menuStr) {
	var menu = menuStr;
	var arr = menu.split(':');
	hideMenuDiv();
	for (i = 0; i < arr.length; i++) {
		$(menuArr[arr[i]]).show();
	}
}