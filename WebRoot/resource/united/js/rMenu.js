// 右键菜单层控制
// 右键菜单数组
var menuArr = new Array();
initMenuArr();
// 创建菜单数组
function initMenuArr() {
	menuArr[0] = "#addDataCenter";//添加数据中心
	menuArr[1] = "#editDataCenter";//编辑数据中心
	menuArr[2] = "#delDataCenter";//移除数据中心
	menuArr[3] = "#addCluster";//添加集群
	menuArr[4] = "#delCluster";//移除集群
	menuArr[5] = "#addHost";//添加主机
	menuArr[6] = "#addVM";//添加虚拟机
	menuArr[7] = "#rebootHost";//重启主机
	menuArr[8] = "#delHost";//移除主机
	menuArr[9] = "#startVM";//开启虚拟机
	menuArr[10] = "#shutdownVM";//关闭虚拟机
	menuArr[11] = "#hangupVM";//挂起虚拟机
	menuArr[12] = "#recoverVM";//恢复虚拟机
	menuArr[13] = "#rebootVM";//重启虚拟机
	menuArr[14] = "#migrateVM";//迁移虚拟机
	menuArr[15] = "#editVM";//编辑设置虚拟机
	menuArr[16] = "#cloneVM";//克隆虚拟机
	menuArr[17] = "#snapshotVM";//快照虚拟机
	menuArr[18] = "#console";//虚拟机控制台
	menuArr[19] = "#updateData";//更新数据
	menuArr[20] = "#destroyVM";//移除虚拟机
	menuArr[21] = "#shutdownHost";//关闭主机电源
	menuArr[22] = "#addnakedVM";//创建裸机
	menuArr[23] = "#addDomain";//添加网络域
	menuArr[24] = "#addSubDomain";//添加子网络域
	menuArr[25] = "#addVlan";//添加Vlan
	menuArr[26] = "#addStorageDevice";//添加存储设备
	menuArr[27] = "#searchNode";//搜索
	menuArr[28] = "#delDomain";//移除网络域
	menuArr[29] = "#delSubDomain";//移除子网络域
	menuArr[30] = "#delVlan";//移除Vlan
	menuArr[31] = "#delStorageDevice";//移除存储设备
	menuArr[32] = "#delByTypeNet";//移除类型是网络的数据中心
	menuArr[33] = "#delDataCenterResource";//移除类型是预配资源的数据中心
	menuArr[34] = "#delClusterResource";//移除类型是预配资源的集群
	menuArr[35] = "#delDataStorage";//移除类型是存储的数据中心
	menuArr[36] = "#delDataCenterNotVir";//移除类型是非虚拟化的数据中心
	menuArr[37] = "#addClusterNotVir";//添加类型是非虚拟化的集群
	menuArr[38] = "#delClusterNotVir";//移除类型是非虚拟化的集群
	menuArr[39] = "#addHostNotVir";//添加类型是非虚拟化的主机
	menuArr[40] = "#updHostNotVir";//修改类型是非虚拟化的主机
	menuArr[41] = "#delHostNotVir";//移除类型是非虚拟化的主机
	menuArr[42] = "#updVlan";//修改Vlan
	menuArr[43] = "#updStorageDevice";//修改存储设备
	menuArr[44] = "#updHostRes";//修改主机类型是预留
	menuArr[45] = "#syncClusterNotVir";//xen同步集群
	menuArr[46] = "#markAsTemplate";//将虚拟机转化为模板
	menuArr[47] = "#addArea";//添加地域（在安徽移动节点上增加地域）
	menuArr[48] = "#renameVM";//虚拟机进行重命名操作
	menuArr[49] = "#addXenCluster";//虚拟机进行重命名操作
	menuArr[50] = "#installOrUpdateTool";//安装或升级vmware tool
	menuArr[51] = "#enterMaintenanceMode";//主机进入维护模式
	menuArr[52] = "#exitMaintenanceMode";//主机退出维护模式
	menuArr[53] = "#bootHost";//远程开启主机
	menuArr[54] = "#enterAwaitMode";//主机进入维护模式
	menuArr[55] = "#exitAwaitMode";//主机退出待机模式
	menuArr[56] = "#disConnectHost";//断开主机
	menuArr[57] = "#editCluster";//编辑集群
	menuArr[58] = "#connectHost";//连接主机
	menuArr[59] = "#importOvf";//导入OVF模板
	menuArr[60] = "#addStore";//添加存储
	menuArr[61] = "#relocateVM";//重定位虚拟机
	menuArr[62] = "#resetVM";//重置虚拟机
	menuArr[63] = "#reboot_guest";//重启客户机
	menuArr[64] = "#recycleHostNotVir";//回收非虚拟主机：从资源池中移除资源到空闲池
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