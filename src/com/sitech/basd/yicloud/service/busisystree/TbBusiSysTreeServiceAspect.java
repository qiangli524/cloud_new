package com.sitech.basd.yicloud.service.busisystree;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.sitech.basd.util.session.UserSession;
import com.sitech.basd.yicloud.domain.busisystree.TbBusiSysTree;
import com.sitech.basd.yicloud.domain.busisystree.TbBusiSysTreeLimit;

@Component
@Aspect
public class TbBusiSysTreeServiceAspect {
	@Resource
	private TbBusiSysTreeLimitService tbBusiSysTreeLimitService;

	/**
	 * 
	 * insertAuthRelation:当向树中插入一条数据后，会向权限表中插入一条数据
	 * 
	 * @since duangh Ver 1.0
	 */

	@AfterReturning(pointcut = "execution (* com.sitech.basd.yicloud.service.busisystree.TbBusiSysTreeServiceImpl.insertTbBusiSysTree(..))", returning = "result")
	public void insertAuthRelation(String result) {
		// 获取当前系统登录Session
		HttpSession session = UserSession.getHttpSession();
		String account = session.getAttribute("account").toString();
		TbBusiSysTreeLimit obj = new TbBusiSysTreeLimit();
		obj.setTreeNodeId(result);
		obj.setUsername(account);
		tbBusiSysTreeLimitService.insertTbBusiSysTreeLimit(obj);
	}

	/**
	 * 
	 * deleteAuthRelation:删除一个节点后删除权限关系表中的关系
	 * 
	 * @since duangh Ver 1.0
	 */
	@AfterReturning(pointcut = "execution (* com.sitech.basd.yicloud.service.busisystree.TbBusiSysTreeServiceImpl.deleteTbBusiSysTreeById(..)) && args(obj)", returning = "result")
	public void deleteAuthRelation(TbBusiSysTree obj, int result) {
		if (result != -1) {
			// 获取当前系统登录Session
			HttpSession session = UserSession.getHttpSession();
			String account = session.getAttribute("account").toString();
			TbBusiSysTreeLimit limit = new TbBusiSysTreeLimit();
			limit.setTreeNodeId(obj.getId());
			limit.setUsername(account);
			tbBusiSysTreeLimitService.deleteOneTbBusiSysTreeLimit(limit);
		}
	}
}
