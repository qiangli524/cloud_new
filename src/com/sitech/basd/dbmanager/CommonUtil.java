package com.sitech.basd.dbmanager;

import java.util.ArrayList;
import java.util.List;


import com.sitech.utils.ssh.ssh2.SshConnection;
import com.sitech.utils.ssh.ssh2.SshResourceFactory;

public class CommonUtil {
	
	/**
	 * 执行命令
	 * @param ip
	 * @param port
	 * @param username
	 * @param password
	 * @param shellCmd
	 * @return
	 */
	public static List<String> executeCommand(String ip,int port,String username,String password,String shellCmd){
		try{
			SshResourceFactory ssh = SshResourceFactory.getInstance();
			SshConnection conn=new SshConnection(ip, port, username, password);
			return ssh.executeCommand(conn,shellCmd);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new ArrayList<String>();
	}
	
	public static String executeCommands(String ip,int port,String username,String password,String shellCmd){
		try{
			SshResourceFactory ssh = SshResourceFactory.getInstance();
			SshConnection conn=new SshConnection(ip, port, username, password);
			String str=ssh.executeCommands(conn,shellCmd);
			ssh.executeCommands(conn,"exit");
			return str;
		}catch(Exception e){
			e.printStackTrace();
		}
		return "";
	}
	
	public static void main(String[] args) {
		long temp=System.currentTimeMillis();
		String shellCmd="nohup /ora10g/admin/xiecd/creatTS.sh cloud cloud_data01 /oradata/cloud 10 OFF &";
		String str=executeCommands("172.21.3.189",22,"ora10g","111111",shellCmd);
//		Connection conn=GanymedSshUtil.getConnection("172.21.3.189", "ora10g", "111111");
//		GanymedSshUtil.executeShellCmd(conn, shellCmd);
	}
	
}
