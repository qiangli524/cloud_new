1. 启动命令    ./websockify --web .././ --target-config=/opt/one 29789
 	目录：./noVnc/utils/目录执行
2. vnc_auto.jsp 配置参数
//websockify服务端IP地址
host = "192.168.232.131";
//websockify服务端端口
port = "29789";
//VNC服务端登录密码
password = "admin1234";

3. rfb.js中将VNC密码写死

that.connect = function(host, port, password, path) {
    //Util.Debug(">> connect");

    rfb_host       = host;
    rfb_port       = port;
    rfb_password   = (password !== undefined)   ? password : "";
    //VNC 连接密码暂时写死
    rfb_password   = "admin1234";
    rfb_path       = (path !== undefined) ? path : "";

    if ((!rfb_host) || (!rfb_port)) {
        return fail("Must set host and port");
    }

    updateState('connect');
    //Util.Debug("<< connect");

};
 