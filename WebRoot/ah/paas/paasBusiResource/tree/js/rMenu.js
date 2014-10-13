// 右键菜单层控制
// 右键菜单数组
var menuArr = new Array();
initMenuArr();
// 创建菜单数组
function initMenuArr() {
	//根节点
	menuArr[0] = "#addChildSys";//添加子系统
	//子系统节点
	menuArr[1] = "#addBusi";//承载业务
	menuArr[2] = "#delChildSys";//移除子系统
	//业务节点
	menuArr[3] = "#addApp";//添加服务
	menuArr[4] = "#delBusi";//移除业务
	//服务节点
	menuArr[5] = "#addHosts";//添加主机
	menuArr[6] = "#delApp";//移除服务
	menuArr[7] = "#addVmHosts";//添加虚拟主机
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