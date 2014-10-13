package com.sitech.basd.yicloud.util.zhejiang;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.StringTokenizer;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.sitech.basd.sxcloud.cloud.util.DES3;

public class SSHThread implements RomoteController {

	private String host_ip;

	private int prot;

	private String login_id;

	private String password;

	private boolean autorized = false;

	private String keywords = "bomcagent";

	private String keyChecking = "no";
	String RPCCMD = "";

	// 存放命令执行结果
	private StringBuffer result = new StringBuffer();
	private StringBuffer ext_result = new StringBuffer();

	Session session;

	Channel channel;

	private String command;

	private static Logger logger = Logger.getLogger(SSHThread.class);

	public void setRPCCMD(String rpccmd) {
		RPCCMD = rpccmd;
	}

	/**
	 * 构造函数
	 * 
	 * @param host_ip
	 * @param port
	 * @param login_id
	 * @param password
	 */
	public SSHThread(String host_ip, int port, String login_id, String password) {
		this.host_ip = host_ip;
		this.prot = port;
		this.login_id = login_id;
		this.password = DES3.decrypt(password);
		// this.password = password;
	}

	public void run() {
		// TODO Auto-generated method stub
		// super.run();
	}

	public void close() {
		// TODO Auto-generated method stub
		try {
			// if(channel!=null&&channel.isConnected()){
			// channel.disconnect();
			// }
			if (session != null && session.isConnected()) {
				session.disconnect();
				logger.info("Disonnection successed!");
				logger.info("Disconnect ip=" + this.host_ip);
			} else {
				logger
						.info("Disconnect ip=has exception before"
								+ this.host_ip);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			logger.info("ERROR Disconnect ip=" + this.host_ip);
		}

	}

	/**
	 * 
	 */
	public void doCommand(String command) {
		// TODO Auto-generated method stub
		try {
			this.result = new StringBuffer();
			this.command = command;
			channel = session.openChannel("exec");
			if (command != null && !command.endsWith("\n")) {
				command = command + "\n";
			}
			if (channel != null) {
				((ChannelExec) channel).setCommand(command);
				channel.connect();
				if (channel.isClosed()) {
					logger.info("channel is closed ");
				} else {
					logger.info("channel is connect.... ");
				}
			} else {
				// 执行出错关闭
				this.close();
				logger.error("docommand has error,channel is nulll");
			}
			logger.info("ssh connection exec command=" + command);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("ssh exception command=" + command + ",error=" + e);
		}
	}

	public String getExtResult() {
		// TODO Auto-generated method stub
		try {
			InputStream in = channel.getExtInputStream();
			BufferedInputStream bin = new BufferedInputStream(in);
			int length;
			byte[] bytes = new byte[1024];
			byte[] tmp = new byte[1024];
			while (true) {
				while (in.available() > 0) {
					int i = in.read(tmp, 0, 1024);
					if (i < 0)
						break;
					logger.debug(new String(tmp, 0, i));
					this.ext_result.append(new String(tmp, 0, i));
				}
				if (channel.isClosed()) {

					logger.info("exit-status: " + channel.getExitStatus());
					break;
				}
				try {
					Thread.sleep(1000);
				} catch (Exception ee) {
				}
			}

			logger.info("end ssh connection get command result");
		} catch (Exception e) {
			// this.result = new StringBuffer();
			e.printStackTrace();
			logger.error(e);
			logger.error("ssh exception commandresult=" + this.command);
		}
		return this.result.toString();
	}

	public String getResult() {
		// TODO Auto-generated method stub
		if (channel == null || channel.isClosed()) {
			int i = 0;
			while (channel == null || channel.isClosed()) {
				this.doCommand(this.command);
				i += 1;
				if (i > 3 && (channel == null || channel.isClosed())) {
					logger.info("channel has closed,getResult fail,ip="
							+ this.host_ip);
					return "";
				}
				if (channel != null && !channel.isClosed()) {
					break;
				}
			}

		}
		InputStream in = null;
		BufferedInputStream bin = null;

		try {
			in = channel.getInputStream();
			bin = new BufferedInputStream(in);
		} catch (Throwable e) {
			e.printStackTrace();
		}

		try {
			// int length;
			// byte[] bytes = new byte[1024];
			/*
			 * while (true) { try { Thread.sleep(500); } catch (Exception ee) {
			 * ee.printStackTrace(); } while ((length=bin.read(bytes))>0) {
			 * result.append(new String(bytes,0,length)); } if
			 * (channel.isClosed()&&channel.isEOF() ) { 
			 * while ((length=bin.read(bytes))>0) { result.append(new
			 * String(bytes,0,length-1)); } logger.info("ssh
			 * command="+this.command+" exstatus ="); in.close(); bin.close();
			 *  break; } }
			 */
			byte[] tmp = new byte[1024];
			long timeout = 60000;
			long wait = 0;
			try {
				while (in != null && timeout > wait) {
					while (in.available() > 0) {
						int i = in.read(tmp, 0, 1024);
						if (i <= 0)
							break;
						this.result.append(new String(tmp, 0, i));
					}
					if (channel.isClosed() || channel.isEOF()) {
						break;
					}
					try {
						Thread.sleep(1000);
						logger.info("wait for result=ip" + this.host_ip);
						wait += 1000;
					} catch (Exception ee) {
					}
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			} catch (Throwable th) {
				th.printStackTrace();
			}
			logger.info("end ssh connection get command result");
		} catch (Exception e) {
			// this.result = new StringBuffer();
			e.printStackTrace();
			logger.error(e);
			logger.error("ssh exception commandresult=" + this.command + ",ip="
					+ this.host_ip);
		} catch (Throwable th) {
			th.printStackTrace();
			logger.error("ssh exception_th commandresult=" + this.command
					+ ",ip=" + this.host_ip);
		} finally {
			try {
				in.close();
				bin.close();
				if (channel != null && channel.isConnected()) {
					channel.disconnect();
				}
				logger.info("IP_channel=" + this.host_ip + ",close+command"
						+ this.command + "");
				logger.info("ssh close command=" + this.command + "");

			} catch (Throwable th1) {
				logger.error("stream close error" + this.command + ",ip="
						+ this.host_ip);
			}
		}
		return this.result.toString();
	}

	public void initial() throws Exception {
		// public void initial(){
		// TODO Auto-generated method stub
		try {
			JSch jsch = new JSch();
			session = jsch.getSession(login_id, host_ip, prot);
			if (session != null) {
				session.setPassword(this.password);
				session.setConfig("StrictHostKeyChecking", this.keyChecking);
				session.connect(30000); // making a connection with timeout.
				this.autorized = true;
				logger.info("ssh connect successfuly!!! ip= " + this.host_ip);
				if (session != null && !session.isConnected()) {
					this.autorized = false;
					this.close();
				}
			}
		} catch (Exception e) {
			logger.error("ssh创建session失败");
			e.printStackTrace();
			logger.error(e);
			logger.error("ssh connect fail ip=" + this.host_ip);

			throw new Exception(e);
		}
	}

	public void initial_keychecking(String keychecking) {
		// TODO Auto-generated method stub
		if (keychecking != null
				&& (keychecking.equals("yes") || (keychecking.equals("no")))) {
			this.keyChecking = keychecking;
		}
		try {
			JSch jsch = new JSch();
			session = jsch.getSession(login_id, host_ip, prot);
			session.setPassword(this.password);
			session.setConfig("StrictHostKeyChecking", this.keyChecking);
			session.connect(30000); // making a connection with timeout.

			this.autorized = true;
			logger.info("ssh connect successfuly!!! ip= " + this.host_ip);
		} catch (Exception e) {
			logger.info("ssh创建session失败");
			e.printStackTrace();
			logger.error(e);
			return;
		}
	}

	/**
	 * 
	 */
	public boolean isAuthorized() {
		// TODO Auto-generated method stub
		return this.autorized;
	}

	public boolean isExistDir(String dir) {
		// TODO Auto-generated method stub
		boolean success = true;

		this.doCommand("cd " + dir + "\n");
		try {
			InputStream in = channel.getInputStream();
			int nextChar;
			while (true) {
				while ((nextChar = in.read()) != -1) {
					result.append((char) nextChar);
				}
				if (channel.isClosed() || channel.isEOF()) {
					if (channel.getExitStatus() != 0) {
						success = false;
					}
					in.close();
					break;
				}
				try {
					Thread.sleep(500);
				} catch (Exception ee) {
					ee.printStackTrace();
				}
			}
		} catch (Exception e) {
			this.result = new StringBuffer();
			e.printStackTrace();
		}
		/*
		 * if(this.getResult().indexOf("not found")!=-1){ success = false; }
		 */

		return success;
	}

	public boolean isRunning(String keywords) {
		// TODO Auto-generated method stub
		this.keywords = keywords;
		this.doCommand("ps -ef | grep " + this.keywords);
		String result = this.getResult();
		result = result.replaceAll("grep " + this.keywords, " ");
		if (result != null && result.indexOf(this.keywords) != -1) {
			return true;
		} else {
			return false;
		}
	}

	public boolean getResultAsString(String targetString) {
		// TODO Auto-generated method stub
		try {
			InputStream in = channel.getInputStream();
			int nextChar;
			while (true) {
				while ((nextChar = in.read()) != -1) {
					result.append((char) nextChar);
					if (this.result.toString().indexOf(targetString) != -1) {
						return true;
					}
				}
				if (channel.isClosed() || channel.isEOF()) {
					in.close();
					break;
				}
				try {
					Thread.sleep(500);
				} catch (Exception ee) {
					ee.printStackTrace();
					return false;
				}
			}
		} catch (Exception e) {
			this.result = new StringBuffer();
			e.printStackTrace();
			return false;
		}
		if (this.result.toString().indexOf(targetString) != -1) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * return -1 error occured otherwise child file count of the directory
	 */
	public int childCountOfDir(String dir) {
		int count = 0;
		try {
			if (this.isExistDir(dir)) {
				this.doCommand("cd " + dir + ";ls -l | wc -l");
				String result = this.getResult();
				count = Integer.valueOf(result.trim()).intValue() - 1;
			} else {
				count = -1;
			}
		} catch (Exception e) {
			e.printStackTrace();
			count = -1;
		}
		return count;
	}

	public boolean createDir(String dir) {
		boolean isExist = false;
		if (dir != null && !dir.trim().equals("") && this.isExistDir(dir)) {
			isExist = true;
		}
		// 逐层分析目录，如果没有则创建.
		if (dir != null && !dir.trim().equals("") && !this.isExistDir(dir)) {
			String[] split_dir = dir.split("/");
			for (int i = 0; i < split_dir.length; i++) {
				String path = "";
				for (int j = 0; j <= i; j++) {
					if (split_dir[j] != null && !split_dir[j].equals("")) {
						path += "/" + split_dir[j];
					}
				}

				if (path != null && !path.equals("")) {
					if (!this.isExistDir(path)) {
						this.doCommand("mkdir " + path);
						this.getResult();
						logger.debug("creat path=" + path);
					}
					if (!this.isExistDir(path)) {
						logger.error("cat not creat path=" + path);
						return false;
					}
				}
			}
			if (this.isExistDir(dir)) {
				isExist = true;
			}
		}
		return isExist;
	}

	public String getHostName() {
		this.doCommand("hostname");
		String s = null;
		s = getResult();
		String[] s1 = s.split("\n");
		if (s1 != null && s1.length >= 2) {
			// 获取第2个
			if (s1.length == 3)
				return s1[1].trim();
			if (s1.length == 2) {
				return s1[0].trim();
			}
		} else if (s1 != null && s1.length == 1) {
			return s1[0].trim();
		}
		return "";
	}

	private String getOSType() {
		String os_type = "";
		this.doCommand("uname -a");
		String s = null;
		s = getResult();
		String[] s1 = s.split("\n");
		if (s1 != null && s1.length > 0) {
			String row_1 = s1[0];
			if (row_1 != null && row_1.startsWith("uname -a")) {
				os_type = s1[1];
			} else {
				os_type = s1[0];
			}
			os_type = split(os_type, 0);
		}
		return os_type;

	}

	public String split(String str, int pos) {
		StringTokenizer st = new StringTokenizer(str);
		Vector values = new Vector();
		while (st.hasMoreTokens()) {
			values.add(st.nextToken());
		}
		if (pos >= 0 && pos < values.size()) {
			return (String) values.elementAt(pos);
		}
		return "";
	}

	public Vector getResultAsVector(String command) {
		Vector vResult = new Vector();
		String strResult = null;
		this.doCommand(command);
		strResult = this.getResult();
		if (strResult != null) {
			String[] results = strResult.split("\n");
			int length = results.length;
			if (results != null && results.length > 1) {
				String mail = (String) results[results.length - 1];
				if (mail.indexOf("have mail in") > -1) {
					length = results.length - 1;
				}
			}
			if (results != null) {
				for (int i = 0; i < length; i++) {
					vResult.addElement(results[i]);
				}
			}
		}

		return vResult;
	}

	public String getResultAsStr(String command) throws Exception {
		String result = "";
		Vector vResult = new Vector();
		String strResult = null;
		this.doCommand(command);
		strResult = this.getResult();
		/*
		 * try{ this.initial(); this.doCommand(command); strResult =
		 * this.getResult(); }catch(Exception e){ e.printStackTrace(); //
		 * logger.info("error has ocurred when execut
		 * CMD="+this.command+",ip="+this.host_ip); }finally{ this.close(); }
		 */
		if (strResult != null) {
			String[] results = strResult.split("\n");
			int length = results.length;
			if (results != null && results.length > 1) {
				String mail = (String) results[results.length - 1];
				if (mail.indexOf("have mail in") > -1) {
					length = results.length - 1;
				}
			}
			if (results != null) {
				for (int i = 0; i < length; i++) {
					if (results[i].indexOf(command) > -1)
						continue;
					vResult.addElement(results[i]);
				}
			}
		}

		for (int i = 0; i < vResult.size(); i++) {
			if (i < vResult.size() - 1)
				result += (String) vResult.elementAt(i) + "\n";
			else
				result += (String) vResult.elementAt(i);
		}
		if (result != null && result.endsWith("\r")) {
			result = result.substring(0, result.length() - 1);
		}
		return result.trim();
	}

	public boolean isConnected() {
		if (this.session != null && this.session.isConnected()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SSHThread tt = new SSHThread("136.5.76.134", 22, "ibnms",
				"39f89c21cd78e80fae0069467ab8485d9ac4013df0345c18");
		try {
			tt.initial();
			logger.debug(tt.getResultAsStr("pwd"));
		} catch (Exception e) {
			tt.close();
			e.printStackTrace();
		}
		tt.close();
	}
}
