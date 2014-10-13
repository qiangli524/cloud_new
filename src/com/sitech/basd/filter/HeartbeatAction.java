package com.sitech.basd.filter;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.springframework.stereotype.Controller;

import com.sitech.basd.sxcloud.rsmu.web.CRUDBaseAction;
import com.sitech.utils.jackson.JacksonUtil;

/**
 * 
 * ClassName:HeartbeatAction Function:接受心跳信息，配置单点后无法获取，修改为cxf的rest服务
 * 
 * @author duangh
 * @version
 * @since Ver 1.0
 * @Date 2013 2013 十一月 17 19:47:50
 * @see
 */
@Path("/heartbeat")
@Produces( { MediaType.APPLICATION_JSON })
@Consumes( { MediaType.APPLICATION_JSON })
@Controller("heartbeatAction")
public class HeartbeatAction extends CRUDBaseAction {
	/**
	 * 
	 * @Title: init
	 * @Description:servlet初始化启动检查客户端连接状态线程
	 * @author duangh
	 * @date 2013 十月 19 11:16:30
	 */
	@PostConstruct
	public void init() {
		Thread timeThread = new TimeThread();
		timeThread.start();
	}

	/** serialVersionUID */
	private static final long serialVersionUID = -7634035850123079578L;
	/** 存储客户端心跳信息 */
	private static Map<String, String> clientConnIdentMap = new HashMap<String, String>();

	public synchronized Map<String, String> getClientConnIdentMap() {
		return clientConnIdentMap;
	}

	/** 心跳的超时时间,3分钟 */
	private static final long TIME_OUT = 3 * 60 * 1000;

	/**
	 * 
	 * @Title: heartbeatServer
	 * @Description:接口客户端post过来的心跳数据,以前为action，配置单点后无法获取，修改为cxf的rest服务
	 * @author duangh
	 * @date 2013 十月 19 11:41:32
	 * @throws IOException
	 */
	@POST
	@Path("/heartbeatServer")
	public synchronized Response heartbeatServer(String params) throws IOException {

		/** 读取心跳，post中的数据 */

		// BufferedReader br = request.getReader();
		// String buffer = null;
		// StringBuffer buff = new StringBuffer();
		// while ((buffer = br.readLine()) != null) {
		// buff.append(buffer + "\n");
		// }
		// br.close();
		Map<String, String> beatMap = new HashMap<String, String>();
		// beatMap.put("id", id);
		// beatMap.put("beatTime", beatTime);
		beatMap = JacksonUtil.fromJSON(params, new JacksonUtil.TypeReference<Map<String, String>>() {
		});
		dealWithBeat(beatMap);// 对心跳数据进行处理
		ResponseBuilder builder = Response.ok();// 返回200信息
		return builder.build();
	}

	private synchronized void dealWithBeat(Map<String, String> beatMap) {
		String id = beatMap.get("id");
		String beatTime = beatMap.get("beatTime");
		getClientConnIdentMap().put(id, beatTime);
	}

	/**
	 * 
	 * @Title: checkClientConnect
	 * @Description:定时检查客户端连接状态
	 * @author duangh
	 * @date 2013 十月 19 10:51:25
	 */
	public synchronized void checkClientConnect() {
		long now = (new Date()).getTime();
		Iterator<Entry<String, String>> iterator = getClientConnIdentMap().entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<String, String> entry = iterator.next();
			String key = entry.getKey();
			String connTime = entry.getValue();
			long beatTime = 0L;
			if (connTime != null && !"".equals(connTime)) {
				beatTime = Long.valueOf(connTime);
			}
			if ((now - TIME_OUT) > beatTime) {
				iterator.remove();
				getClientConnIdentMap().remove(key);
			}
		}
	}

	private class TimeThread extends Thread {
		private static final long INTERVAL = 30 * 1000;

		public void run() {
			while (!Thread.interrupted()) {
				checkClientConnect();// 检查客户端连接状态
				try {
					Thread.sleep(INTERVAL);
				} catch (InterruptedException e) {

				}
			}
		}
	}

}
