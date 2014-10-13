package com.sitech.shop.webservice.util;

import com.sitech.shop.webservice.domain.TbOrderRelationInstance;
import com.sitech.shop.webservice.domain.TbOrderRelationInstanceExample;
import com.sitech.shop.webservice.service.OrderRelation;
import com.sitech.utils.properties.PropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("OrderJob")
public class OrderJob {

    Logger logger = LoggerFactory.getLogger(OrderJob.class);

    //订购关系接口服务spring注入
    @Autowired
    private OrderRelation orderRelationService;

    public void autoSendOrder() throws Exception {
        logger.info("失败订单自动执行重新发送_方法执行_Start");
        TbOrderRelationInstanceExample obj = new TbOrderRelationInstanceExample();
        obj.createCriteria().andCreateStatusIsNull();
        List<TbOrderRelationInstance> orderRelationInstanceList = orderRelationService.selectOrderRelationList(obj);
        Map<String,String> map=new HashMap<String,String>();
        for (TbOrderRelationInstance tri:orderRelationInstanceList){
            map.put(tri.getOrderId(),tri.getBoattr1());
        }
        logger.debug("失败的订单数量："+map.size());
        for(Map.Entry<String, String> entry: map.entrySet()) {
            logger.debug("失败的订单"+entry.getKey()+"_"+entry.getValue());
            String orderId = entry.getKey();
            String is_Init =entry.getValue();
            //订单状态重置
            orderRelationService.reset(orderId);
            //重新发送
            orderRelationService.sendCloud(orderId, (is_Init.equals("1")), false);
        }
        logger.info("失败订单自动执行重新发送_方法执行_End");
    }

    /**
     * 自动发送提醒
     * @throws Exception
     */
    public void autoSendRemind() throws Exception {
        logger.info("自动执行发送产品到期提醒_方法执行_Start");
        //取得过期提醒阀值
        String overdue_reminder = PropertiesUtil.getValue("properties/public_cloud.properties", "overdue_reminder");
        TbOrderRelationInstanceExample obj = new TbOrderRelationInstanceExample();
        TbOrderRelationInstanceExample.Criteria criteria = obj.createCriteria();
        //服务开始时间和结束时间不能为空
        criteria.andServiceBeginTimeIsNotNull().andServiceEndTimeIsNotNull();
        //天数要大于0并且要小于阀值
        criteria.andBoattr2Between("0",overdue_reminder);
        // 即将到期的订购关系
         List<TbOrderRelationInstance> resultList = orderRelationService.selectOrderRelationList(obj);
        for (TbOrderRelationInstance orObj : resultList) {
            //发送到期提醒消息
            SendMessageUtil.send_Reminder_Message(orObj);
        }
        logger.info("自动执行发送产品到期提醒_方法执行_End");
    }
}
