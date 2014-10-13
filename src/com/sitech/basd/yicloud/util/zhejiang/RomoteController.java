package com.sitech.basd.yicloud.util.zhejiang;

import java.util.Vector;

public interface RomoteController {
	 public boolean isAuthorized();
	 public void doCommand(String command);
	 public void setRPCCMD(String rpccmd);
	 public String getResult();
	 //结果中含有某个字符串返回真
	 public boolean getResultAsString(String targetString);
	 public boolean isExistDir(String dir);
	 //public void initial();
	 public void initial() throws Exception;
	 public void close();
	
	 public boolean isRunning(String keywords);
	 //得到目标目录下的文件数-包括文件目录
	 public int childCountOfDir(String dir);
	 //在远程主机主机上创建目录
	 public boolean createDir(String dir);
	 //把结果以每行作为一条记录，放到vector中
	 public Vector getResultAsVector(String command);
	 public String getResultAsStr(String command) throws Exception;
     //是否登录	 
	 public boolean isConnected();
}
