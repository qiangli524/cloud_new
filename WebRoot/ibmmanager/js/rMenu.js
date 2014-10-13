// 右键菜单层控制
// 右键菜单数组
var menuArr = new Array();
initMenuArr();
// 创建菜单数组
function initMenuArr() {
	menuArr[0] = "#addIBMManager";//添加小型机
	menuArr[1] = "#updateIBMManager";//修改小型机
	menuArr[2] = "#deleteIBMManager";//删除小型机
	menuArr[3] = "#addPower";//添加Power
	menuArr[4] = "#updatePower";//修改过power
	menuArr[5] = "#delPower";//删除power
	menuArr[6] = "#addLogicPartition";//添加逻辑分区
	menuArr[7] = "#updateLogicPartition";//修改过逻辑分区
	menuArr[8] = "#delLogicPartition";//删除逻辑分区
	menuArr[9] = "#updateData";//更新数据
	
	menuArr[10] = "#addCluster";//添加集群
	
	menuArr[11] = "#delCluster";//移除集群
	menuArr[12] = "#addHost";//添加主机
	
	menuArr[13] = "#addnakedVM";//创建裸机
	menuArr[14] = "#addVM";//添加虚拟机
	menuArr[15] = "#rebootHost";//重启主机
	menuArr[16] = "#shutdownHost";//关闭主机电源
	menuArr[17] = "#delHost";//移除主机
	
	menuArr[18] = "#migrateVM";//迁移虚拟机
	menuArr[19] = "#editVM";//编辑设置虚拟机
	menuArr[20] = "#cloneVM";//克隆虚拟机
	menuArr[21] = "#delVM";//移除虚拟机
	
}
// 隐藏所有邮件菜单
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