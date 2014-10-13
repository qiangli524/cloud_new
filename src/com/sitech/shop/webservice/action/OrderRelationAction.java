package com.sitech.shop.webservice.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.sitech.basd.fusioncharts.util.JacksonUtil;
import com.sitech.basd.sxcloud.rsmu.web.CRUDBaseAction;
import com.sitech.basd.sxcloud.rsmu.web.util.exception.BaseException;
import com.sitech.basd.sxcloud.rsmu.web.util.page.Pagination;
import com.sitech.shop.webservice.domain.TbOrderRelationInstance;
import com.sitech.shop.webservice.domain.TbOrderRelationInstanceExample;
import com.sitech.shop.webservice.service.OrderRelation;
import com.sitech.ssd.billing.vo.resourceInfo.VmInfo;
import com.sitech.utils.common.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * 订购关系接口表 手动控制界面
 *  
 */
@Controller("orderRelationAction")
@Scope("prototype")
public class OrderRelationAction extends CRUDBaseAction {
	
	Logger logger = LoggerFactory.getLogger(OrderRelationAction.class);

	private static final long serialVersionUID = 1L;
	
	//订购关系接口服务
	@Autowired
	private OrderRelation orderRelationService;
	
	private List<?> resultList;
	private String orderId;

	private String productInstanceId;

	private String customerId;

	private String ifPrimary;

	private String productId;

	private String status;

    private String returnMessage;

    /**
	 * 查询订单列表
	 * 
	 * @return list 跳转到列表页面
	 * @throws BaseException
	 */
	public String orderRelationList() throws BaseException {

        TbOrderRelationInstanceExample obj = new TbOrderRelationInstanceExample();
        TbOrderRelationInstanceExample.Criteria criteria = obj.createCriteria();

        //按照时间降序
        obj.setOrderByClause("create_date DESC");
        //订单ID
        if (CommonUtil.isNotEmpty(orderId)) {
            criteria.andOrderIdEqualTo(orderId);
        }

        //订购关系ID
        if (CommonUtil.isNotEmpty(productInstanceId)) {
            criteria.andProductInstanceIdEqualTo(productInstanceId);
        }

        //商城用户ID
        if (CommonUtil.isNotEmpty(customerId)) {
            criteria.andBoattr1EqualTo(customerId);
        }

        //商品ID
        if (CommonUtil.isNotEmpty(productId)) {
            criteria.andProductIdEqualTo(productId);
        }

        //商城状态
        if (CommonUtil.isNotEmpty(status)) {
            criteria.andStatusEqualTo(status);
        }

        // 分页对象
        Pagination pagination = this.getPaginater().initPagination(request);
        obj.setPagination(pagination);

        // 取得结果集
        resultList = orderRelationService.selectOrderRelationList(obj);

        return LIST;
    }
	/**
	 * 将订单信息重新发送给云平台
	 * 
	 * @return list 跳转到列表页面
	 * @throws BaseException
	 */
	public String deleteOrder() throws BaseException {
		logger.info("删除订单_方法执行_Start");
		// 订购关系记录主键ID
		String uuid = request.getParameter("Ids");
		try {
			if (CommonUtil.isNotEmpty(uuid)) {
				TbOrderRelationInstanceExample obj = new TbOrderRelationInstanceExample();
				TbOrderRelationInstanceExample.Criteria criteria = obj.createCriteria();
				criteria.andUuidEqualTo(uuid);
				List<TbOrderRelationInstance> list = orderRelationService.selectOrderRelationList(obj);
				if (list.size() > 0) {
					String orderId = list.get(0).getOrderId();
					orderRelationService.deleteAll(orderId);
				}
			}
			this.returnMessage = "删除成功";
		} catch (Exception e) {
			this.returnMessage = "删除失败";
			logger.error("删除订单异常",e);
		}
		logger.info("删除订单_方法执行_End");
		return SUCCESS;
	}

	/**
	 * 将订单信息重新发送给云平台
	 * 
	 * @return list 跳转到列表页面
	 * @throws BaseException
	 */
	public String sendCloud() throws BaseException {
		logger.info("重新调用发送订单给云平台_方法执行_Start");
		// 订购关系记录主键ID
		String uuid = request.getParameter("Ids");
		try {
			if (CommonUtil.isNotEmpty(uuid)) {
				TbOrderRelationInstanceExample obj = new TbOrderRelationInstanceExample();
				TbOrderRelationInstanceExample.Criteria criteria = obj.createCriteria();
				criteria.andUuidEqualTo(uuid);
				List<TbOrderRelationInstance> list = orderRelationService.selectOrderRelationList(obj);
				if (list.size() > 0) {
					String orderId = list.get(0).getOrderId();
					String is_Init = list.get(0).getBoattr5();
					//订单状态重置
					orderRelationService.reset(orderId);
					//重新发送
					orderRelationService.sendCloud(orderId, (is_Init.equals("1")),false);
				}
			}
			this.returnMessage = "发送成功";
		} catch (Exception e) {
			this.returnMessage = "发送失败";
			logger.error("重新发送订购关系给云平台异常",e);
		}
		logger.info("重新调用发送订单给云平台_方法执行_End");
		return SUCCESS;
	}

	
	/**
	 * 查看订购关系明细
	 * @return
	 * @throws Exception 
	 */
	public String orderRelationDetail() throws Exception {
		logger.info("查看订购关系明细_方法执行_Start");
		String orderId = request.getParameter("orderId");
		String productId = request.getParameter("productId");
		Map<String, List<String>> productInstanceIdMap;
		productInstanceIdMap=orderRelationService.getComputerConfigurationRelationIdMap(orderId, null, true, productId);
		Set<String> keySet = productInstanceIdMap.keySet();
		ArrayList<VmInfo> vminfoList=new ArrayList<VmInfo>();
        for (String key : keySet) {
        	 VmInfo vmInfo;
        	 vmInfo=orderRelationService.getComputerConfigurationInfo(key, productInstanceIdMap, orderId);
        	 if(CommonUtil.isNotEmpty(vmInfo.getVmProductInstanceId())){
 				TbOrderRelationInstanceExample obj = new TbOrderRelationInstanceExample();
 				TbOrderRelationInstanceExample.Criteria criteria = obj.createCriteria();
 				criteria.andProductInstanceIdEqualTo(vmInfo.getVmProductInstanceId());
 				List<TbOrderRelationInstance> list = orderRelationService.selectOrderRelationList(obj);
 				if(list.size()>0){
 					TbOrderRelationInstance o=list.get(0);
 					 //主机ID
 					vmInfo.setVmCode(o.getVmId());
 					//连接code
 					vmInfo.setConnectCode(o.getvCenterCode());
 					 //VLANID
 					vmInfo.setVlan_id(o.getIpAddress());
 				}
        	 }

            if(CommonUtil.isNotEmpty(vmInfo.getvLanProductInstanceId())){
                TbOrderRelationInstanceExample obj = new TbOrderRelationInstanceExample();
                TbOrderRelationInstanceExample.Criteria criteria = obj.createCriteria();
                criteria.andProductInstanceIdEqualTo(vmInfo.getvLanProductInstanceId());
                List<TbOrderRelationInstance> list = orderRelationService.selectOrderRelationList(obj);
                if(list.size()>0){
                    TbOrderRelationInstance o=list.get(0);
                    //VLANID
                    vmInfo.setVlan_id(o.getIpAddress());
                }
            }
        	 vminfoList.add(vmInfo);
        	 logger.info("信息为：" + JacksonUtil.formatJson(JacksonUtil.toJson(vmInfo)));
        }
        request.setAttribute("vminfoList", vminfoList);
		logger.info("查看订购关系明细_方法执行_End");
		return "orderDetail";
	}


	public List<?> getResultList() {
		return resultList;
	}

	public void setResultList(List<?> resultList) {
		this.resultList = resultList;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getProductInstanceId() {
		return productInstanceId;
	}

	public void setProductInstanceId(String productInstanceId) {
		this.productInstanceId = productInstanceId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getIfPrimary() {
		return ifPrimary;
	}

	public void setIfPrimary(String ifPrimary) {
		this.ifPrimary = ifPrimary;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

    public String getReturnMessage() {
        return returnMessage;
    }

    public void setReturnMessage(String returnMessage) {
        this.returnMessage = returnMessage;
    }
}
