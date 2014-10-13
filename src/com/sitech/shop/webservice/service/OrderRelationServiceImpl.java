package com.sitech.shop.webservice.service;

import com.sitech.basd.fusioncharts.util.JacksonUtil;
import com.sitech.shop.webservice.dao.TbOrderRelationDAO;
import com.sitech.shop.webservice.dao.TbOrderRelationInstanceDAO;
import com.sitech.shop.webservice.dao.TbOrderStandardDAO;
import com.sitech.shop.webservice.dao.TbSysUserinfoShopDAO;
import com.sitech.shop.webservice.domain.*;
import com.sitech.shop.webservice.orderClient.ClientUtil;
import com.sitech.shop.webservice.util.SendMessageUtil;
import com.sitech.ssd.billing.vo.order.*;
import com.sitech.ssd.billing.vo.orderentityrelation.OrderEntityRelationVO;
import com.sitech.ssd.billing.vo.orderentityrelation.OrderEntityVO;
import com.sitech.ssd.billing.vo.resourceInfo.VmInfo;
import com.sitech.trade4.bean.MalltExtPrdctInstcBean;
import com.sitech.trade4.bean.MalltPrdctInstc;
import com.sitech.trade4.bean.MalltPrdctInstcRelation;
import com.sitech.trade4.bean.MalltPrdctInstcSpec;
import com.sitech.utils.common.CommonUtil;
import com.sitech.utils.publicShop.OrderConstant;
import com.sitech.utils.publicShop.PublicCloudUtil;
import com.sitech.utils.rabbitmq.RabbitMQUtil;
import com.sitech.utils.randomid.RandomUUID;
import com.sitech.vo.united.VirtualDiskUnitedVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import publiccloud.UserOperType;
import rabbitmq.QueueDefind;







import javax.annotation.Resource;

import java.util.*;

/**
 * 商城和云平台直接的服务接口
 *
 * @author liming_bj
 */
@Service("orderRelationService")
public class OrderRelationServiceImpl implements OrderRelation {

    Logger logger = LoggerFactory.getLogger(OrderRelationServiceImpl.class);

    /**
     * 用户操作DAO
     */
    @Resource
    private TbSysUserinfoShopDAO sysUserinfoShopDao;

    /**
     * 订购关系实例表
     */
    @Autowired
    private TbOrderRelationInstanceDAO orderRelationInstanceDao;

    /**
     * 订购关系的关系表
     */
    @Autowired
    private TbOrderRelationDAO orderRelationDao;

    /**
     * 实例规格表
     */
    @Autowired
    private TbOrderStandardDAO orderStandardDao;

    /**
     * 消息队列工具类
     */
    @Autowired
    private RabbitMQUtil rabbitmqUtil;

    /**
     * 接收商城传过来的订购关系对象
     *
     * @param orderVO 订购关系对象
     * @return 返回给商城是否成功
     * @throws Exception
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public OrderVO processing(OrderVO orderVO) throws Exception {
        logger.info("接收商城订购关系_方法执行_Start");
        logger.debug("商城传送过来的对象信息：" + JacksonUtil.formatJson(JacksonUtil.toJson(orderVO)));
        OrderVO order = new OrderVO();
        try {
            //订购关系保存入库_方法调用
            boolean is_Init = saveOrderRelationDate(orderVO);
           //将订购关系发送给云平台_方法调用
            logger.debug("订单ID：" + orderVO.getOrderId() + "是否第一次购买" + is_Init);
            sendCloud(orderVO.getOrderId(), is_Init,true);
            order.setIsSuccess(true);
        } catch (Exception e) {
            order.setIsSuccess(false);
            logger.error("订单出错需要删除订单中的订购关系"+orderVO.getOrderId(),e);
            throw new Exception(e);
        }
        logger.debug("返回给商城对象信息：" + JacksonUtil.formatJson(JacksonUtil.toJson(order)));
        logger.info("接收商城订购关系_方法执行_End");
        return order;
    }

    /**
     * 接受订购关系状态变更
     *
     * @param orderVO 订购关系对象
     * @return 返回给商城是否成功
     */
    public OrderVO processingRelationStatusChange(OrderVO orderVO) {
        logger.info("接收订购关系状态变更_方法执行_Start");
        logger.debug("商城传送过来的订购关系状态变更对象信息：" + JacksonUtil.formatJson(JacksonUtil.toJson(orderVO)));
        OrderVO order = new OrderVO();
        try {
            sendStatusChangeToCloud(orderVO);
            order.setIsSuccess(true);
        } catch (Exception e) {
            order.setIsSuccess(false);
            logger.error("订购关系状态变更异常", e);
        }
        logger.debug("返回给商城订购关系状态变更对象信息：" + JacksonUtil.formatJson(JacksonUtil.toJson(order)));
        logger.info("接收订购关系变更_方法执行_End");
        return order;
    }

    /**
     * 接受服务时长变更
     *
     * @param orderVO 订购关系对象
     * @return 返回给商城是否成功
     */
    public OrderVO processingRelationTimeChange(OrderVO orderVO) {
        logger.info("接收服务时长变更_方法执行_Start");
        logger.debug("商城传送过来的服务时长变更对象信息：" + JacksonUtil.formatJson(JacksonUtil.toJson(orderVO)));
        OrderVO order = new OrderVO();
        try {
            //服务时长变更_方法调用
            sendTimeChangeToCloud(orderVO);
            order.setIsSuccess(true);
        } catch (Exception e) {
            order.setIsSuccess(false);
            logger.error("服务时长变更异常", e);
        }
        logger.info("接收服务时长变更_方法执行_End");
        return order;
    }

    /**
     * 删除所有订购关系数据
     *
     * @param orderId 订单ID
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void deleteAll(String orderId) throws Exception {
        logger.info("删除所有订购关系数据_方法执行_Start");
        logger.debug("订单ID：" + orderId);
        TbOrderRelationInstanceExample ori = new TbOrderRelationInstanceExample();
        ori.createCriteria().andOrderIdEqualTo(orderId);
        TbOrderRelationExample or = new TbOrderRelationExample();
        or.createCriteria().andOrderIdEqualTo(orderId);
        TbOrderStandardExample os = new TbOrderStandardExample();
        os.createCriteria().andOrderIdEqualTo(orderId);

        logger.debug("删除关系实例表_方法调用");
        orderRelationInstanceDao.deleteByExample(ori);
        logger.debug("删除关系表_方法调用");
        orderRelationDao.deleteByExample(or);
        logger.debug("删除规格表_方法调用");
        orderStandardDao.deleteByExample(os);
        logger.info("删除所有订购关系数据_方法执行_End");
    }

    /**
     * 发送给云平台状态变更
     *
     * @param orderVO 状态变更的对象
     * @throws Exception
     */
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    private void sendStatusChangeToCloud(OrderVO orderVO) throws Exception {
        logger.info("主机状态变更发送给云平台_方法执行_Start");
        List<ProInsVO> piList = orderVO.getPiList();
        // 遍历订购关系集合
        for (ProInsVO proInsVO : piList) {

            TbOrderRelationInstanceExample example = new TbOrderRelationInstanceExample();
            example.createCriteria().andProductInstanceIdEqualTo(proInsVO.getProduct_instance_id());
            List<TbOrderRelationInstance> tbOrderRelationInstanceList = orderRelationInstanceDao.selectByExample(example);

            for (TbOrderRelationInstance obj : tbOrderRelationInstanceList) {

                //更新主机状态入库
                obj.setStatus(proInsVO.getStatus());
                orderRelationInstanceDao.updateByPrimaryKeySelective(obj);

                //实例化vm对象信息
                VmInfo info = new VmInfo();

                //状态变更要加入产品类型判断
                if (obj.getProductId().equals(OrderConstant.PRODUCT_ID_1)) {
                    //云主机
                    info.setVmCode(obj.getVmId());
                    info.setConnectCode(obj.getvCenterCode());
                    info.setVmProductInstanceId(obj.getProductInstanceId());
                } else if (obj.getProductId().equals(OrderConstant.PRODUCT_ID_4)) {
                    // 存储
                    VirtualDiskUnitedVO diskUnitedVo = new VirtualDiskUnitedVO();
                    TbOrderStandardExample ose = new TbOrderStandardExample();
                    ose.createCriteria().andProductInstanceIdEqualTo(obj.getProductInstanceId()).andBoattr2EqualTo(OrderConstant.PRODUCT_ID_4);
                    List<TbOrderStandard> osdList = orderStandardDao.selectByExample(ose);
                    if (osdList.size() > 0) {
                        diskUnitedVo.setVmdk_id(osdList.get(0).getSpecValueDesc());
                        diskUnitedVo.setVmdkProduceInstaceId(obj.getProductInstanceId());
                        List<VirtualDiskUnitedVO> vmdkList = new ArrayList<VirtualDiskUnitedVO>();
                        vmdkList.add(diskUnitedVo);
                        info.setVmdkList(vmdkList);
                    }
                } else if (obj.getProductId().equals(OrderConstant.PRODUCT_ID_5)) {
                    // Vlan
                    info.setVlan_id(obj.getIpAddress());
                    info.setvLanProductInstanceId(obj.getProductInstanceId());
                } else if (obj.getProductId().equals(OrderConstant.PRODUCT_ID_7)) {
                    // 公网IP
                    info.setInternet_ip(obj.getPublicIpAddress());
                    info.setIpProductInstanceId(obj.getProductInstanceId());
                } else if (obj.getProductId().equals(OrderConstant.PRODUCT_ID_8)) {
                    // 负载均衡
                    info.setLoadId(obj.getIpAddress());
                    info.setLoadProductInstanceId(obj.getProductInstanceId());
                }

                try {
                    info.setStatus(PublicCloudUtil.getCloudStatus(proInsVO.getStatus()));
                    logger.debug("VmCode：" + obj.getVmId() + "ConnectCode：" + obj.getvCenterCode() + "商城的状态：" + proInsVO.getStatus() + "云平台的状态：" + info.getStatus());
                    logger.debug("发送队列：" + QueueDefind.BJ_CHANGEVMSTATE_QUEUE);
                    logger.debug("发送到队列的JSON：" + JacksonUtil.formatJson(JacksonUtil.toJson(info)));
                    rabbitmqUtil.publishMessage("", QueueDefind.BJ_CHANGEVMSTATE_QUEUE, info);
                    updateRelationInstanceStatus(proInsVO.getProduct_instance_id(), null, OrderConstant.STATUS_16);
                } catch (Exception e) {
                    updateRelationInstanceStatus(proInsVO.getProduct_instance_id(), null, OrderConstant.STATUS_17);
                    logger.error("发送队列:到" + QueueDefind.BJ_CHANGEVMSTATE_QUEUE + "ERROR", e);
                }
            }
        }
        logger.info("主机状态变更发送给云平台_方法执行_End");
    }

    /**
     * 发送给云平台状态变更
     *
     * @param orderVO 状态变更的对象
     * @throws Exception
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    private void sendTimeChangeToCloud(OrderVO orderVO) throws Exception {
        logger.info("服务时长变更发送给云平台_方法执行_Start");
        logger.debug("商城传送过来的对象信息：" + JacksonUtil.formatJson(JacksonUtil.toJson(orderVO)));
        List<ProInsVO> piList = orderVO.getPiList();
        // 遍历订购关系集合
        for (ProInsVO proInsVO : piList) {
            if (CommonUtil.isNotEmpty(proInsVO.getProduct_instance_id())) {
                TbOrderRelationInstanceExample selectExample = new TbOrderRelationInstanceExample();
                selectExample.createCriteria().andProductInstanceIdEqualTo(proInsVO.getProduct_instance_id());
                List<TbOrderRelationInstance> tbOrderRelationInstanceList = orderRelationInstanceDao.selectByExample(selectExample);

                for (TbOrderRelationInstance ori : tbOrderRelationInstanceList) {

                    //更新主机时长入库
                    ori.setServiceBeginTime(proInsVO.getService_begin_time());
                    ori.setServiceEndTime(proInsVO.getService_end_time());
                    orderRelationInstanceDao.updateByPrimaryKeySelective(ori);

                    //是主机才发送
                    if (ori.getProductId().equals(OrderConstant.PRODUCT_ID_1)) {
                        TbSysUserinfoExample userExample = new TbSysUserinfoExample();
                        userExample.createCriteria().andShopUserIdEqualTo(ori.getCustomerId());
                        // 读取用户信息
                        List<TbSysUserinfo> userInfoList = sysUserinfoShopDao.selectByExample(userExample);
                        for (TbSysUserinfo ui : userInfoList) {
                            try {
                                VmInfo vminfo = new VmInfo();
                                //用户ID
                                vminfo.setUser_id(String.valueOf(ui.getId()));
                                // 虚拟机ID
                                vminfo.setVmCode(ori.getVmId());
                                // 主机订购关系ID
                                vminfo.setVmProductInstanceId(ori.getProductInstanceId());
                                // CenterCode
                                vminfo.setConnectCode(ori.getvCenterCode());
                                //服务开始时间
                                vminfo.setService_begin_time(proInsVO.getService_begin_time());
                                //服务结束时间
                                vminfo.setService_end_time(proInsVO.getService_end_time());
                                logger.debug("主机时长变更发送队列：" + QueueDefind.BJ_CHANGESERVERTIME_QUEUE);
                                logger.debug("主机时长变更发送到队列的JSON：" + JacksonUtil.formatJson(JacksonUtil.toJson(vminfo)));
                                rabbitmqUtil.publishMessage("", QueueDefind.BJ_CHANGESERVERTIME_QUEUE, vminfo);
                                updateRelationInstanceStatus(proInsVO.getProduct_instance_id(), null, OrderConstant.STATUS_12);
                            } catch (Exception e) {
                                updateRelationInstanceStatus(proInsVO.getProduct_instance_id(), null, OrderConstant.STATUS_13);
                                logger.error("主机时长变更发送队列:到" + QueueDefind.BJ_CHANGESERVERTIME_QUEUE + "ERROR", e);
                            }
                        }
                    } else if (ori.getProductId().equals(OrderConstant.PRODUCT_ID_4)) {
                        //磁盘时长
                        VmInfo vminfo = new VmInfo();
                        VirtualDiskUnitedVO diskUnitedVo = new VirtualDiskUnitedVO();

                        TbOrderStandardExample ose = new TbOrderStandardExample();
                        ose.createCriteria().andProductInstanceIdEqualTo(ori.getProductInstanceId()).andBoattr2EqualTo(OrderConstant.PRODUCT_ID_4);
                        List<TbOrderStandard> osdList = orderStandardDao.selectByExample(ose);
                        if (osdList.size() > 0) {
                            diskUnitedVo.setVmdk_id(osdList.get(0).getSpecValueDesc());
                            diskUnitedVo.setVmdkProduceInstaceId(osdList.get(0).getProductInstanceId());
                            List<VirtualDiskUnitedVO> vmdkList = new ArrayList<VirtualDiskUnitedVO>();
                            vmdkList.add(diskUnitedVo);
                            vminfo.setVmdkList(vmdkList);
                            //服务开始时间
                            vminfo.setService_begin_time(proInsVO.getService_begin_time());
                            //服务结束时间
                            vminfo.setService_end_time(proInsVO.getService_end_time());
                            try {
                                logger.debug("磁盘时长发送队列：" + QueueDefind.BJ_CHANGESERVERTIME_QUEUE);
                                logger.debug("磁盘时长发送到队列的JSON：" + JacksonUtil.formatJson(JacksonUtil.toJson(vminfo)));
                                rabbitmqUtil.publishMessage("", QueueDefind.BJ_CHANGESERVERTIME_QUEUE, vminfo);
                                updateRelationInstanceStatus(proInsVO.getProduct_instance_id(), null, OrderConstant.STATUS_12);
                            } catch (Exception e) {
                                updateRelationInstanceStatus(proInsVO.getProduct_instance_id(), null, OrderConstant.STATUS_13);
                                logger.error("磁盘时长发送队列:到" + QueueDefind.BJ_CHANGESERVERTIME_QUEUE + "ERROR", e);
                            }
                        }
                    } else if (ori.getProductId().equals(OrderConstant.PRODUCT_ID_5)) {
                        //带宽时长
                        VmInfo vminfo = new VmInfo();
                        vminfo.setVlan_id(ori.getIpAddress());
                        vminfo.setvLanProductInstanceId(ori.getProductInstanceId());
                        vminfo.setService_begin_time(proInsVO.getService_begin_time());
                        //服务结束时间
                        vminfo.setService_end_time(proInsVO.getService_end_time());
                        try {
                            logger.debug("带宽时长发送队列：" + QueueDefind.BJ_CHANGESERVERTIME_QUEUE);
                            logger.debug("带宽时长发送到队列的JSON：" + JacksonUtil.formatJson(JacksonUtil.toJson(vminfo)));
                            rabbitmqUtil.publishMessage("", QueueDefind.BJ_CHANGESERVERTIME_QUEUE, vminfo);
                            updateRelationInstanceStatus(proInsVO.getProduct_instance_id(), null, OrderConstant.STATUS_12);
                        } catch (Exception e) {
                            updateRelationInstanceStatus(proInsVO.getProduct_instance_id(), null, OrderConstant.STATUS_13);
                            logger.error("带宽时长发送队列:到" + QueueDefind.BJ_CHANGESERVERTIME_QUEUE + "ERROR", e);
                        }
                    } else if (ori.getProductId().equals(OrderConstant.PRODUCT_ID_7)) {
                        //IP时长
                        VmInfo vminfo = new VmInfo();
                        vminfo.setInternet_ip(ori.getPublicIpAddress());
                        vminfo.setIpProductInstanceId(ori.getProductInstanceId());
                        vminfo.setService_begin_time(proInsVO.getService_begin_time());
                        //服务结束时间
                        vminfo.setService_end_time(proInsVO.getService_end_time());
                        try {
                            logger.debug("IP时长发送队列：" + QueueDefind.BJ_CHANGESERVERTIME_QUEUE);
                            logger.debug("IP时长发送到队列的JSON：" + JacksonUtil.formatJson(JacksonUtil.toJson(vminfo)));
                            rabbitmqUtil.publishMessage("", QueueDefind.BJ_CHANGESERVERTIME_QUEUE, vminfo);
                            updateRelationInstanceStatus(proInsVO.getProduct_instance_id(), null, OrderConstant.STATUS_12);
                        } catch (Exception e) {
                            updateRelationInstanceStatus(proInsVO.getProduct_instance_id(), null, OrderConstant.STATUS_13);
                            logger.error("IP时长发送队列:到" + QueueDefind.BJ_CHANGESERVERTIME_QUEUE + "ERROR", e);
                        }
                    } else if (ori.getProductId().equals(OrderConstant.PRODUCT_ID_8)) {
                        //负载时长
                        VmInfo vminfo = new VmInfo();
                        vminfo.setLoadId(ori.getIpAddress());
                        vminfo.setLoadProductInstanceId(ori.getProductInstanceId());
                        vminfo.setService_begin_time(proInsVO.getService_begin_time());
                        //服务结束时间
                        vminfo.setService_end_time(proInsVO.getService_end_time());
                        try {
                            logger.debug("负载时长发送队列：" + QueueDefind.BJ_CHANGESERVERTIME_QUEUE);
                            logger.debug("负载时长发送到队列的JSON：" + JacksonUtil.formatJson(JacksonUtil.toJson(vminfo)));
                            rabbitmqUtil.publishMessage("", QueueDefind.BJ_CHANGESERVERTIME_QUEUE, vminfo);
                            updateRelationInstanceStatus(proInsVO.getProduct_instance_id(), null, OrderConstant.STATUS_12);
                        } catch (Exception e) {
                            updateRelationInstanceStatus(proInsVO.getProduct_instance_id(), null, OrderConstant.STATUS_13);
                            logger.error("负载时长发送队列:到" + QueueDefind.BJ_CHANGESERVERTIME_QUEUE + "ERROR", e);
                        }
                    }
                }
            }
        }
        logger.info("服务时长变更发送给云平台_方法执行_End");
    }

    /**
     * 更新订购关系状态
     *
     * @param RelationInstanceId 订购关系ID
     * @param orderId            订单ID
     * @param Status             状态
     * @throws Exception
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updateRelationInstanceStatus(String RelationInstanceId, String orderId, String Status) throws Exception {
        logger.info("更新订购关系状态(云平台标注的状态)_方法执行_Start");
        logger.debug("订购关系ID：" + RelationInstanceId+ "订单ID：" + orderId + "状态变更为：" + Status);
        TbOrderRelationInstanceExample example = new TbOrderRelationInstanceExample();
        if (CommonUtil.isNotEmpty(RelationInstanceId)) {
            example.createCriteria().andProductInstanceIdEqualTo(RelationInstanceId);
        }
        if (CommonUtil.isNotEmpty(orderId)) {
            example.createCriteria().andOrderIdEqualTo(orderId);
        }
        TbOrderRelationInstance ori = new TbOrderRelationInstance();
        ori.setChangeStatus(Status);
        orderRelationInstanceDao.updateByExampleSelective(ori, example);
        logger.info("更新订购关系状态(云平台标注的状态)_方法执行_End");
    }

    /**
     * 将订购关系入库
     *
     * @param orderVO 订购关系对象
     * @return 是否第一次购买
     * @throws Exception
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    private boolean saveOrderRelationDate(OrderVO orderVO) throws Exception {
        logger.info("商城订购关系保存入库_方法执行_Start");

        String UserId = orderVO.getPiList().get(0).getCustomer_id();
        logger.debug("用户ID：" + UserId);

        //判断用户是否第一次购买
        boolean is_Inti = getOrder_Init(UserId);
        logger.debug("是否第一次购买：" + is_Inti);

        String orderID = orderVO.getOrderId();
        logger.debug("订单ID：" + orderID);

        // 获取订购关系集合
        List<ProInsVO> piList = orderVO.getPiList();
        // 遍历订购关系集合
        for (ProInsVO proInsVO : piList) {
            // 订购关系 入库**********************************************************************
            TbOrderRelationInstance ori = new TbOrderRelationInstance();
            // 主键(自动生成)
            ori.setUuid(RandomUUID.getUuid());
            // 订单ID
            ori.setOrderId(orderID);
            // 订购关系id
            ori.setProductInstanceId(String.valueOf(proInsVO.getProduct_instance_id()));
            // 用户id
            ori.setCustomerId(proInsVO.getCustomer_id());
            // 是否主产品
            ori.setIfPrimary(proInsVO.getIf_primary());
            // 产品id
            ori.setProductId(proInsVO.getProduct_id());
            // 状态
            ori.setStatus(proInsVO.getStatus());
            // 账户id
            ori.setAcctId(proInsVO.getAcct_id());
            // 产品实例标识
            ori.setServId(proInsVO.getServ_id());
            //接收时间
            ori.setCreateDate(new Date());
            //接收成功状态
            ori.setChangeStatus(OrderConstant.STATUS_1);
            //是否第一次购买
            ori.setBoattr5(is_Inti ? "1" : "0");
            // 保存订购关系
            orderRelationInstanceDao.insertSelective(ori);
            // 遍历订购关系账本列表
            for (ProInsCashVO proInsSpecVO : proInsVO.getPicList()) {
                // 遍历订订购关系账本--产品规格列表
                for (ProInsCashSpecVO proInsCashSpecVO : proInsSpecVO.getPicsList()) {
                    TbOrderStandard tbs = new TbOrderStandard();
                    // 主键ID(自动生成)
                    tbs.setUuid(RandomUUID.getUuid());
                    // 订单ID
                    tbs.setOrderId(orderID);
                    // 订购关系id
                    tbs.setProductInstanceId(proInsSpecVO.getProduct_instance_id());
                    // 产品规格id
                    tbs.setPrdctSpecId(String.valueOf(proInsCashSpecVO.getPrdct_instc_cash_spec_id()));
                    // 用户id
                    tbs.setUserId(ori.getCustomerId());
                    // 规格属性id
                    tbs.setSpecId(proInsCashSpecVO.getSpec_id());
                    // 规格属性值
                    tbs.setSpecValue(proInsCashSpecVO.getSpec_value());
                    // 规格属性描述
                    tbs.setSpecValueDesc(proInsCashSpecVO.getSpec_value_desc());
                    // 购买方式 1：按小时 2：包月
                    tbs.setDurationUnit(proInsSpecVO.getDuration_unit());
                    //产品ID
                    tbs.setBoattr2(proInsVO.getProduct_id());
                    //放入购买类型 1:后付费 2：预付费
                    tbs.setPayDuration(proInsSpecVO.getIf_protocol_price());
                    //创建时间
                    tbs.setCreateDate(new Date());
                    orderStandardDao.insertSelective(tbs);
                }
            }
        }
        // 订购关系的关系表入库******************************************************************
        for (ProInsRelationVO pir : orderVO.getPirList()) {
            TbOrderRelation tor = new TbOrderRelation();
            // 主键(自动生成)
            tor.setUuid(RandomUUID.getUuid());
            // 订单ID
            tor.setOrderId(orderID);
            // 商城的主键
            tor.setOrderRelationInsid(String.valueOf(pir.getPrdct_instc_relation_id()));
            // 主关系
            tor.setMasterRelationId(pir.getB_product_instance_id());
            // 附属关系
            tor.setChildRelationId(pir.getA_product_instance_id());
            // 关系类型
            tor.setRelationType(pir.getRelation_type());
            //创建时间
            tor.setCreateDate(new Date());
            orderRelationDao.insertSelective(tor);
        }
        logger.info("商城订购关系保存入库_方法执行_End");
        return is_Inti;
    }

    /**
     * 调用创建资源的方法
     *
     * @param orderId 订单ID
     * @param is_Init 是否是第一次购买
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void sendCloud(String orderId, boolean is_Init,boolean send_type) throws Exception {
        logger.info("订购关系解析并发送给云平台_方法执行_Start");
        logger.debug("传入参数：订单ID" + orderId + "是否第一次购买：" + is_Init);

        //发送创建主机信息
        sendCreateVmInfo(orderId, is_Init,send_type);
        //发送修改主机Cpu信息
        sendEditVmInfo(orderId,OrderConstant.PRODUCT_ID_2,send_type);
        //发送修改主机内存信息
        sendEditVmInfo(orderId,OrderConstant.PRODUCT_ID_3,send_type);
        //发送数据盘信息
        sendDiskInfo(orderId,send_type);
        //发送带宽信息
        sendBandWidthInfo(orderId,send_type);
        //发送公网ip信息
        sendIpInfo(orderId,send_type);
        //发送负载均衡信息
        senLoadInfo(orderId,send_type);

        logger.info("订购关系解析并发送给云平台_方法执行_End");
    }

    /**
     * 发送修改CPU信息
     * @param orderId 订单ID
     *  @param productId  产品ID
     * @throws Exception
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    private void sendEditVmInfo(String orderId,String productId,boolean send_type) throws Exception {
        logger.info("向管理平台发送主机 修改主机 信息_方法执行_Start");
        Map<String, List<String>> productInstanceIdMap;
        logger.debug("订单ID："+orderId+(productId.equals(OrderConstant.PRODUCT_ID_2)?"修改CPU":"修改内存"));
        productInstanceIdMap= getComputerConfigurationRelationIdMap(orderId, null, false,productId);

        // 主机订购关系的ID
        Set<String> keySet = productInstanceIdMap.keySet();
        // 遍历主机的配置
        for (String key : keySet) {
            VmInfo vmInfo;
            // 查询的条件对象
            TbOrderRelationExample orExample = new TbOrderRelationExample();
            // 条件为主产品的订购关系ID
            orExample.createCriteria().andChildRelationIdEqualTo(key);
            List<?> orList = orderRelationDao.selectByExample(orExample);
            vmInfo = getComputerConfigurationInfo(key, productInstanceIdMap,orderId);
            //关系的关系表记录大于1才属于变更
            if (orList.size() > 1) {
                TbOrderRelation orObj = (TbOrderRelation) orList.get(0);
                TbOrderRelationInstanceExample oriExample = new TbOrderRelationInstanceExample();
                oriExample.createCriteria().andProductInstanceIdEqualTo(orObj.getMasterRelationId());
                List<TbOrderRelationInstance> orderRelationInstanceList = orderRelationInstanceDao.selectByExample(oriExample);
                TbOrderRelationInstance orderRelationInstance = orderRelationInstanceList.get(0);
                vmInfo.setVmCode(orderRelationInstance.getVmId());
                vmInfo.setConnectCode(orderRelationInstance.getvCenterCode());
                try {
                    logger.debug((productId.equals(OrderConstant.PRODUCT_ID_2)?"修改CPU":"修改内存")+"信息为：" + JacksonUtil.formatJson(JacksonUtil.toJson(vmInfo)));
                    updateRelationInstanceStatus(key, null, OrderConstant.STATUS_2);
                    logger.debug("订单 ID：" + orderId + "，更新状态：2 配置解析成功");
                } catch (Exception e) {
                    logger.error("主机解析失败：主订购关系ID：" + key + "，更新状态：3 配置解析失败", e);
                    updateRelationInstanceStatus(key, null, OrderConstant.STATUS_3);
                    throw new Exception(e);
                }
                try {
                    vmInfo.setSend_Type(send_type);
                    rabbitmqUtil.publishMessage("", QueueDefind.BJ_CHANGEVM_QUEUE, vmInfo);
                    logger.debug("将修改主机信息发送到队列" + QueueDefind.BJ_CHANGEVM_QUEUE + "中：");
                    updateRelationInstanceStatus(key, null, OrderConstant.STATUS_10);
                    logger.debug("订单 ID：" + orderId + "，更新状态：10 配置变更成功" );
                } catch (Exception e) {
                    logger.error("状态发送失败：订单 ID：" + orderId + "，更新状态：11 配置发送失败", e);
                    updateRelationInstanceStatus(key, null, OrderConstant.STATUS_11);
                    throw new Exception(e);
                }
            }
        }
        logger.info("向管理平台发送 修改主机 信息_方法执行_End");
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    private void senLoadInfo(String orderId,boolean send_type) throws Exception {
        logger.info("向云平台发送 负载均衡 信息_方法执行_Start");
        Map<String, List<String>> ProductInstanceIdMap = getComputerConfigurationRelationIdMap(orderId, null, false, OrderConstant.PRODUCT_ID_8);

        //负载均衡订购关系的ID
        Set<String> keySet = ProductInstanceIdMap.keySet();
        // 遍历主机的配置
        for (String key : keySet) {
            VmInfo vmInfo = null;
            try {
                vmInfo = getComputerConfigurationInfo(key, ProductInstanceIdMap,orderId);
                logger.debug("负载均衡 信息为：" + JacksonUtil.formatJson(JacksonUtil.toJson(vmInfo)));
                updateRelationInstanceStatus(key, null, OrderConstant.STATUS_2);
                logger.debug("负载均衡 解析成功  订单 ID：" + orderId + "，更新状态：" + OrderConstant.STATUS_2);
            } catch (Exception e) {
                logger.error("负载均衡 解析失败：主订购关系ID：" + key + "，更新状态：" + OrderConstant.STATUS_3, e);
                updateRelationInstanceStatus(key, null, OrderConstant.STATUS_3);
            }

            //发送负载均衡新购
            try {
                if (vmInfo != null) {vmInfo.setSend_Type(send_type);}
                rabbitmqUtil.publishMessage("", QueueDefind.BJ_LOADBALANCE, vmInfo);
                logger.debug("将 负载均衡 信息发送到队列" + QueueDefind.BJ_LOADBALANCE + "中：");
                updateRelationInstanceStatus(key, null, OrderConstant.STATUS_4);
                logger.debug("负载均衡 信息发送成功 订单 ID：" + orderId + "，更新状态：" + OrderConstant.STATUS_4);
            } catch (Exception e) {
                logger.error("负载均衡 信息发送失败：订单 ID：" + orderId + "，更新状态：" + OrderConstant.STATUS_5, e);
                updateRelationInstanceStatus(key, null, OrderConstant.STATUS_5);
            }
        }
        logger.info("向云平台发送负载均衡信息_方法执行_End");
    }

    /**
     *发送IP信息给云平台
     * @param orderId 订单ID
     * @throws Exception
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    private void sendIpInfo(String orderId,boolean send_type)  throws Exception {
        logger.info("向云平台发送IP信息_方法执行_Start");
        Map<String, List<String>> ProductInstanceIdMap = getComputerConfigurationRelationIdMap(orderId, null, false, OrderConstant.PRODUCT_ID_7);

        // IP订购关系的ID
        Set<String> keySet = ProductInstanceIdMap.keySet();
        // 遍历主机的配置
        for (String key : keySet) {
            VmInfo vmInfo;
            try {
                vmInfo = getComputerConfigurationInfo(key, ProductInstanceIdMap,orderId);
                logger.debug("IP信息为：" + JacksonUtil.formatJson(JacksonUtil.toJson(vmInfo)));
                updateRelationInstanceStatus(key, null, OrderConstant.STATUS_2);
                logger.debug("IP解析成功 订单 ID：" + orderId + "，更新状态：" + OrderConstant.STATUS_2);
            } catch (Exception e) {
                logger.error("IP解析失败：主订购关系ID：" + key + "，更新状态：" + OrderConstant.STATUS_3, e);
                updateRelationInstanceStatus(key, null, OrderConstant.STATUS_3);
                throw new Exception(e);
            }

            //发送IP新购
            try {
                vmInfo.setSend_Type(send_type);
                rabbitmqUtil.publishMessage("", QueueDefind.BJ_PUBLICIP, vmInfo);
                logger.debug("将IP信息发送到队列" + QueueDefind.BJ_PUBLICIP + "中：");
                updateRelationInstanceStatus(key, null, OrderConstant.STATUS_4);
                logger.debug("状态发送成功 订单 ID：" + orderId + "，更新状态：" + OrderConstant.STATUS_4);
            } catch (Exception e) {
                logger.error("状态发送失败：订单 ID：" + orderId + "，更新状态：" + OrderConstant.STATUS_5, e);
                updateRelationInstanceStatus(key, null, OrderConstant.STATUS_5);
                throw new Exception(e);
            }
        }
        logger.info("向云平台发送IP信息_方法执行_End");
    }

    /**
     *发送带宽信息给云平台
     *
     * @param orderId 订单ID
     * @throws Exception
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    private void sendBandWidthInfo(String orderId,boolean send_type)  throws Exception{
        logger.info("向云平台发送 带宽 信息_方法执行_Start");
        Map<String, List<String>> ProductInstanceIdMap = getComputerConfigurationRelationIdMap(orderId, null, false, OrderConstant.PRODUCT_ID_5);

        // 主机订购关系的ID
        Set<String> keySet = ProductInstanceIdMap.keySet();
        // 遍历带宽的配置
        for (String key : keySet) {
            VmInfo vmInfo;
            try {
                vmInfo = getComputerConfigurationInfo(key, ProductInstanceIdMap,orderId);
                //判断是新增带宽还是变更带宽
                vmInfo = ifCreateOrUpdate(vmInfo,OrderConstant.PRODUCT_ID_5);
                logger.debug("带宽操作类型(1:新增，2：修改)：" + vmInfo.getActionType());
                logger.debug("带宽信息为：" + JacksonUtil.formatJson(JacksonUtil.toJson(vmInfo)));
                updateRelationInstanceStatus(key, null, OrderConstant.STATUS_2);
                logger.debug("带宽解析成功，更新状态 订单 ID：" + orderId + "，更新状态：" + OrderConstant.STATUS_2);
            } catch (Exception e) {
                logger.error("带宽解析失败：主订购关系ID：" + key + "，更新状态：" + OrderConstant.STATUS_3, e);
                updateRelationInstanceStatus(key, null, OrderConstant.STATUS_3);
                throw new Exception(e);
            }

            //发送带宽新购
            try {
                //新增带宽
                if (vmInfo.getActionType().equals("1")) {
                    vmInfo.setSend_Type(send_type);
                    rabbitmqUtil.publishMessage("", QueueDefind.BJ_BANDWIDTH, vmInfo);
                    logger.debug("将带宽信息发送到队列" + QueueDefind.BJ_BANDWIDTH + "中：");
                    updateRelationInstanceStatus(key, null,OrderConstant.STATUS_4);
                }
                //修改带宽
                if (vmInfo.getActionType().equals("2")) {
                    vmInfo.setSend_Type(send_type);
                    rabbitmqUtil.publishMessage("", QueueDefind.BJ_EDIT_BANDWIDTH, vmInfo);
                    logger.debug("将带宽信息发送到队列" + QueueDefind.BJ_EDIT_BANDWIDTH + "中：");
                    updateRelationInstanceStatus(key, null,OrderConstant.STATUS_10);
                }
                logger.debug("带宽信息发送成功 订单 ID：" + orderId + "，更新状态：" + OrderConstant.STATUS_10);
            } catch (Exception e) {
                logger.error("状态发送失败：订单 ID：" + orderId + "，更新状态：" + OrderConstant.STATUS_5, e);
                updateRelationInstanceStatus(key, null, OrderConstant.STATUS_11);
                throw new Exception(e);
            }
        }
        logger.info("向云平台发送带宽信息_方法执行_End");
    }

    /**
     * 向云平台发送主机数据盘信息
     * @param orderId 订单ID
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    private void sendDiskInfo(String orderId,boolean send_type)  throws Exception {
		logger.info("向云平台发送主机数据盘信息_方法执行_Start");
		Map<String, List<String>> productInstanceIdMap;
		productInstanceIdMap = getComputerConfigurationRelationIdMap(orderId, null, false, OrderConstant.PRODUCT_ID_4);
		// 单买的 TODO 
		// 主机订购关系的ID
		Set<String> keySet = productInstanceIdMap.keySet();
		// 遍历主机的配置
		for (String key : keySet) {
			VmInfo vmInfo;
			// 查询的条件对象
			TbOrderRelationExample orExample = new TbOrderRelationExample();
			// 条件为主产品的订购关系ID
			orExample.createCriteria().andChildRelationIdEqualTo(key);
			List<?> orList = orderRelationDao.selectByExample(orExample);
			if (orList.size() > 0) {
				vmInfo = getComputerConfigurationInfo(key, productInstanceIdMap, orderId);
				TbOrderRelation orObj = (TbOrderRelation) orList.get(0);
				TbOrderRelationInstanceExample oriExample = new TbOrderRelationInstanceExample();
				oriExample.createCriteria().andProductInstanceIdEqualTo(orObj.getMasterRelationId());
				List<TbOrderRelationInstance> orderRelationInstanceList = orderRelationInstanceDao.selectByExample(oriExample);
				TbOrderRelationInstance orderRelationInstance = orderRelationInstanceList.get(0);
				vmInfo.setVmCode(orderRelationInstance.getVmId());
				vmInfo.setConnectCode(orderRelationInstance.getvCenterCode());
				try {
					logger.debug("磁盘信息为：" + JacksonUtil.formatJson(JacksonUtil.toJson(vmInfo)));
					updateRelationInstanceStatus(key, null, OrderConstant.STATUS_2);
					logger.debug("磁盘信息解析成功 订单 ID：" + orderId + "，更新状态：" + OrderConstant.STATUS_2);
				} catch (Exception e) {
					logger.error("磁盘信息解析失败：主订购关系ID：" + key + "，更新状态：" + OrderConstant.STATUS_3, e);
					updateRelationInstanceStatus(key, null, OrderConstant.STATUS_3);
					throw new Exception(e);
				}
				try {
                    vmInfo.setSend_Type(send_type);
					rabbitmqUtil.publishMessage("", QueueDefind.BJ_CHANGEVM_QUEUE, vmInfo);
					logger.debug("将磁盘信息发送到队列" + QueueDefind.BJ_CHANGEVM_QUEUE + "中：");
					updateRelationInstanceStatus(key, null, OrderConstant.STATUS_4);
					logger.debug("磁盘信息发送成功 订单 ID：" + orderId + "，更新状态：" + OrderConstant.STATUS_4);
				} catch (Exception e) {
					logger.error("磁盘信息发送失败：订单 ID：" + orderId + "，更新状态：" + OrderConstant.STATUS_5, e);
					updateRelationInstanceStatus(key, null, OrderConstant.STATUS_5);
					throw new Exception(e);
				}
			}
		}
		logger.info("向云平台发送主机数据盘信息_方法执行_End");
	}

    /**
     * 向管理平台发送主机信息
     * @param orderId 订单ID
     * @param is_init 是否第一次
     * @throws Exception
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    private void sendCreateVmInfo(String orderId, boolean is_init,boolean send_type)  throws Exception {
        logger.info("向管理平台发送新购主机信息_方法执行_Start");
        Map<String, List<String>> productInstanceIdMap;
        productInstanceIdMap= getComputerConfigurationRelationIdMap(orderId, null, false,OrderConstant.PRODUCT_ID_1);
        int count=0;
        // 主机订购关系的ID
        Set<String> keySet = productInstanceIdMap.keySet();
        // 遍历主机的配置
        for (String key : keySet) {
            count++;
            VmInfo vmInfo;
            try {
                vmInfo = getComputerConfigurationInfo(key, productInstanceIdMap,orderId);
                vmInfo.setOper_type(UserOperType.buy_vm);
                vmInfo.setIf_Init(is_init&&count==1);
                logger.debug("新购主机信息为：" + JacksonUtil.formatJson(JacksonUtil.toJson(vmInfo)));
                updateRelationInstanceStatus(key, null, OrderConstant.STATUS_2);
                logger.debug("新购主机解析成功 订单 ID：" + orderId + "，更新状态：" + OrderConstant.STATUS_2);
            } catch (Exception e) {
                logger.error("新购主机解析失败 订购关系ID：" + key + "，更新状态：" + OrderConstant.STATUS_3, e);
                updateRelationInstanceStatus(key, null, OrderConstant.STATUS_3);
                throw new Exception();
            }

            try {
                //判断是商城发送的还是运维重发的
                vmInfo.setSend_Type(send_type);
                rabbitmqUtil.publishMessage("", QueueDefind.BJ_CREATEVM_QUEUE, vmInfo);
                logger.debug("将主机信息发送到队列" + QueueDefind.BJ_CREATEVM_QUEUE + "中：");
                Set<String> keySet2 = productInstanceIdMap.keySet();
                // 遍历主机的配置
                for (String key2 : keySet2) {
                    //主订购关系更新正状态已经发送
                    updateRelationInstanceStatus(key2, null, OrderConstant.STATUS_4);
                    //子订购关系也更新状态为已经发送
                    List <String> sonList=productInstanceIdMap.get(key2);
                    for (String key3 : sonList) {
                        updateRelationInstanceStatus(key3, null, OrderConstant.STATUS_4);
                    }
                }
                logger.debug("新购主机信息发送到运维平台 成功  订单 ID：" + orderId + "，更新状态4");
            } catch (Exception e) {
                logger.error("新购主机信息发送到运维平台 失败：订单 ID：" + orderId + "，更新状态5", e);
                updateRelationInstanceStatus(key, null, OrderConstant.STATUS_5);
                throw new Exception(e);
            }
        }
        logger.info("向管理平台发送新购主机信息_方法执行_End");
    }

    /**
     * 判断新增还是配置变更
     *
     * @param newVmInfo 新订单中的主机信息对象
     * @param productId 产品ID
     * @return 修改后的vmInfo 对象
     */
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    private VmInfo ifCreateOrUpdate(VmInfo newVmInfo, String productId) {
        logger.info("判断新增还是配置变更_方法执行_Start");
        logger.debug("传入的对象：" + JacksonUtil.formatJson(JacksonUtil.toJson(newVmInfo)));
        VmInfo vmInfo = null;
        try {
            //带宽Map
            if(OrderConstant.PRODUCT_ID_5.equals(productId)){
                // 产品实例表条件
                TbOrderRelationInstanceExample example = new TbOrderRelationInstanceExample();
                // 条件：用户ID
                example.createCriteria().andProductInstanceIdEqualTo(newVmInfo.getvLanProductInstanceId()).andProductIdEqualTo(productId);
                int count = orderRelationInstanceDao.countByExample(example);
                if ( count > 1) {
                    vmInfo=newVmInfo.clone();
                    //放入修改标示
                    vmInfo.setActionType("2");
                } else {
                    vmInfo=newVmInfo.clone();
                    //主机新增标示
                    vmInfo.setActionType("1");
                    logger.debug("新增：直接返回");
                }
            }
        } catch (Exception e) {
            logger.error(" 判断新增还是配置变更ERROR", e);
        }
        logger.debug("返回的对象：" + JacksonUtil.formatJson(JacksonUtil.toJson(newVmInfo)));
        logger.info("判断新增还是配置变更_方法执行_End");
        return vmInfo;
    }

    /**
     * 解析订购关系
     *
     * @param key 主机订购关系ID
     * @param ProductInstanceIdMap 订单中所有订单关系的Map
     * @param orderId 订单ID
     * @return VmInfo 主机信息对象
     */
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public VmInfo getComputerConfigurationInfo(String key, Map<String, List<String>> ProductInstanceIdMap,String orderId) throws Exception {
        logger.info("订购关系解析成VmInfo_方法执行_Start");
        // 主机配置信息实体
        VmInfo vminfo = new VmInfo();

        // 用户ID
        String userId = "";

        // 取出主机的配置
        TbOrderStandardExample ose = new TbOrderStandardExample();
        ose.createCriteria().andProductInstanceIdEqualTo(key).andOrderIdEqualTo(orderId);
        List<?> osdList = orderStandardDao.selectByExample(ose);

        for (Object anOsdList : osdList) {
            TbOrderStandard osdObj = (TbOrderStandard) anOsdList;
            if (osdObj.getSpecId().equals(OrderConstant.STANDARD_ID_1)) {
                vminfo.setOper_system(PublicCloudUtil.getOsName(osdObj.getSpecValue()));
                // 主机购关系ID
                vminfo.setVmProductInstanceId(osdObj.getProductInstanceId());
                //操作系统类型
                vminfo.setOs_type(osdObj.getSpecValue().substring(0, 1));
            }
            // 放入用户ID
            vminfo.setUser_id(osdObj.getUserId());
            // 用户ID存入临时变量
            userId = osdObj.getUserId();
            // 放入订单ID
            vminfo.setOrderId(osdObj.getOrderId());
            // 用户付费类型
            vminfo.setPayment_type(osdObj.getPayDuration());
            // 购买方式 1：按小时 2：包月
            vminfo.setDuration_unit(osdObj.getDurationUnit());

            // 放入地域
            if (osdObj.getSpecId().equals(OrderConstant.STANDARD_ID_2)) {
                vminfo.setArea_id(osdObj.getSpecValue());
            }

            // 带宽
            if (osdObj.getSpecId().equals(OrderConstant.STANDARD_ID_5)&&osdObj.getBoattr2().equals(OrderConstant.PRODUCT_ID_5)) {
                vminfo.setBand_width(Integer.valueOf(osdObj.getSpecValue()));
                vminfo.setvLanProductInstanceId(osdObj.getProductInstanceId());
            }

            // 负载均衡
            if (osdObj.getSpecId().equals(OrderConstant.STANDARD_ID_8)&&osdObj.getBoattr2().equals(OrderConstant.PRODUCT_ID_8)) {
                vminfo.setLoadType(osdObj.getSpecValue());
                vminfo.setLoadProductInstanceId(osdObj.getProductInstanceId());
            }

            // IP
            if (osdObj.getBoattr2().equals(OrderConstant.PRODUCT_ID_7)) {
                vminfo.setIp_sum(1);
                vminfo.setIpProductInstanceId(osdObj.getProductInstanceId());
            }

        }

        // 取出主机其他配置信息订购关系ID
        List<String> childRelationIdList = ProductInstanceIdMap.get(key);

        List<VirtualDiskUnitedVO> vmdkList = new ArrayList<VirtualDiskUnitedVO>();
        for (String o : childRelationIdList) {

            // 取出规格信息对象
            TbOrderStandard tosObj = getComputerConfiguration(o,orderId);

            // CPU
            if (tosObj.getSpecId().equals(OrderConstant.STANDARD_ID_3)&&tosObj.getBoattr2().equals(OrderConstant.PRODUCT_ID_2)) {
                vminfo.setCpu(Integer.valueOf(tosObj.getSpecValue()));
                vminfo.setCpuProductInstanceId(tosObj.getProductInstanceId());
            }
            // 内存
            if (tosObj.getSpecId().equals(OrderConstant.STANDARD_ID_4)&&tosObj.getBoattr2().equals(OrderConstant.PRODUCT_ID_3)) {
                vminfo.setMemoryInMb(Integer.valueOf(tosObj.getSpecValue()) * 1024);
                vminfo.setMemoryProductInstanceId(tosObj.getProductInstanceId());
            }

            // 存储
            if (tosObj.getSpecId().equals(OrderConstant.STANDARD_ID_5)&&tosObj.getBoattr2().equals(OrderConstant.PRODUCT_ID_4)) {
                VirtualDiskUnitedVO disk = new VirtualDiskUnitedVO();
                disk.setCapacityInMB(Long.parseLong(tosObj.getSpecValue())*1024);
                disk.setVmdkProduceInstaceId(tosObj.getProductInstanceId());
                vmdkList.add(disk);
            }

        }
        // 放入存储信息
        if(vmdkList.size()>0){
            vminfo.setVmdkList(vmdkList);
        }

        TbSysUserinfoExample tbSysUserinfoExample = new TbSysUserinfoExample();
        tbSysUserinfoExample.createCriteria().andShopUserIdEqualTo(userId);

        // 读取用户信息
        List<TbSysUserinfo> userInfoList = sysUserinfoShopDao.selectByExample(tbSysUserinfoExample);
        if (userInfoList.size() > 0) {
            TbSysUserinfo userinfo = userInfoList.get(0);
            // 用户ID
            vminfo.setUser_id(String.valueOf(userinfo.getId()));
            // 用户姓名
            vminfo.setUser_Name(userinfo.getName());
            // 用户手机
            vminfo.setMobile(userinfo.getMobile());
            // 用户邮箱
            vminfo.setEmail(userinfo.getEmail());
            // 放入用户类别
            vminfo.setUser_Type(userinfo.getPaymentType());
            // 用户级别
            vminfo.setUser_level(userinfo.getUserlevel());
        }
        logger.info("订购关系解析成VmInfo_方法执行_End");
        return vminfo;
    }

    /**
     * 判读是否是第一次购买
     *
     * @param userId 用户ID
     * @return 是否是第一次
     */
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    private boolean getOrder_Init(String userId) {
        logger.info("判断用户是否第一次购买_方法执行_Start");
        logger.debug("用户ID：" + userId);
        // 产品实例表条件
        TbOrderRelationInstanceExample example = new TbOrderRelationInstanceExample();
        // 条件：用户ID
        example.createCriteria().andCustomerIdEqualTo(userId);
        int count = orderRelationInstanceDao.countByExample(example);
        logger.debug("记录数：" + count);
        logger.info("判断用户是否第一次购买_方法执行_End");
        return count == 0;
    }

    /**
     * 取得规格对象
     *
     * @param id 订购关系ID
     * @param orderId 订单ID
     * @return 规格对象
     */
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    private TbOrderStandard getComputerConfiguration(String id, String orderId) {
        logger.info("取得规格对象_方法执行_Start");
        logger.info("订购关系ID"+id+" 订单ID"+orderId);
        TbOrderStandardExample example = new TbOrderStandardExample();
        TbOrderStandardExample.Criteria criteria=example.createCriteria();
        if(CommonUtil.isNotEmpty(id)){
            criteria.andProductInstanceIdEqualTo(id);
        }
        if(CommonUtil.isNotEmpty(orderId)){
            criteria.andOrderIdEqualTo(orderId);
        }
        List<?> osList = orderStandardDao.selectByExample(example);
        if (osList.size() > 0) {
            return (TbOrderStandard) osList.get(0);
        }
        logger.info("取得规格对象_方法执行_End");
        return new TbOrderStandard();
    }

    /**
     * 通过订单ID或产品的订购关系 ID 获取主机的配置的订购关系Map
     *
     * @param orderId    订单ID
     * @param relationId 主机订购关系ID
     * @param isComputer 主机是否完成
     * @param productId 产品ID
     *
     * @return ProductInstanceIdMap Key：主机的订购关系ID Value：配置信息的订购关系IdList
     */
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public Map<String, List<String>> getComputerConfigurationRelationIdMap(String orderId, String relationId, boolean isComputer,String productId) throws Exception {
        logger.info("获取主机配置Map_方法执行_Start");
        logger.debug("订单ID：" + orderId + "订购关系 ID:" + relationId +"是否创建完成："+ isComputer+ "产品ID："+productId);
        Map<String, List<String>> ProductInstanceIdMap = new HashMap<String, List<String>>();


        // 取出云主机的订购关系集合
        TbOrderRelationInstanceExample oriExample = new TbOrderRelationInstanceExample();
        TbOrderRelationInstanceExample.Criteria criteria = oriExample.createCriteria();

        // 条件：订单ID、产品
        if (CommonUtil.isNotEmpty(orderId)) {
            criteria.andOrderIdEqualTo(orderId).andProductIdEqualTo(productId);
        }

        // 条件：主机订购关系ID
        if (CommonUtil.isNotEmpty(relationId)) {
            criteria.andProductInstanceIdEqualTo(relationId).andProductIdEqualTo(productId);
        }

        if (!isComputer) {
            criteria.andCreateStatusIsNull().andChangeStatusNotEqualTo(OrderConstant.STATUS_2).andChangeStatusNotEqualTo(OrderConstant.STATUS_3).andChangeStatusNotEqualTo(OrderConstant.STATUS_4).andChangeStatusNotEqualTo(OrderConstant.STATUS_5);
        }

        // 查找主产品订购关系ID
        List<TbOrderRelationInstance> orderRelationInstanceList = orderRelationInstanceDao.selectByExample(oriExample);

        for (TbOrderRelationInstance oriObj : orderRelationInstanceList) {
            //假如是主机map放入子订购关系集合
            if (productId.equals(OrderConstant.PRODUCT_ID_1)) {
                // 主机配置订购关系
                List<String> childRelationIdList = new ArrayList<String>();
                // 查询的条件对象
                TbOrderRelationExample orExample = new TbOrderRelationExample();
                // 条件为主产品的订购关系ID
                orExample.createCriteria().andMasterRelationIdEqualTo(oriObj.getProductInstanceId());
                List<?> orList = orderRelationDao.selectByExample(orExample);
                for (Object o : orList) {
                    TbOrderRelation orObj = (TbOrderRelation) o;
                    childRelationIdList.add(orObj.getChildRelationId());
                }
                ProductInstanceIdMap.put(oriObj.getProductInstanceId(), childRelationIdList);
            }else {
                //其他主产品
                List<String> childRelationIdList = new ArrayList<String>();
                childRelationIdList.add(oriObj.getProductInstanceId());
                ProductInstanceIdMap.put(oriObj.getProductInstanceId(), childRelationIdList);
            }
        }
        logger.info("获取主机配置Map_方法执行_End");
        return ProductInstanceIdMap;
    }

    /**
     * 云平台创建完成回调接口方法 （陶雪要调用这个方法）
     *
     * @param vmInfoStr  主机信息对象字符串
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void processResult(String vmInfoStr) throws Exception {
        VmInfo vmInfo = JacksonUtil.fromJson(vmInfoStr, VmInfo.class);
        logger.info("接收平台竣工通知_方法执行_Start");
        logger.debug("创建 资源 完成后返回的信息：" + JacksonUtil.formatJson(JacksonUtil.toJson(vmInfo)));

        //返回给商城的对象初始化
        MalltExtPrdctInstcBean returnOrderVo = null;
        //是订单信息才更新状态
        if (CommonUtil.isNotEmpty(vmInfo.getOrderId()) && vmInfo.getIsSuccess()) {
            boolean isComplete;

            //更新我接口表里的信息
            updateCreateStatic(vmInfo);

            isComplete = checkOrderIsComplete(vmInfo.getOrderId());
            logger.debug("竣工状态：" + isComplete);

            // 是否创建完成
            if (isComplete) {
                // 构建返回对象
                try {
                    returnOrderVo = buildOrderVo(vmInfo.getOrderId());
                    updateRelationInstanceStatus(null, vmInfo.getOrderId(), OrderConstant.STATUS_6);
                    logger.debug("返回商城对象构建成功");
                } catch (Exception e) {
                    updateRelationInstanceStatus(null, vmInfo.getOrderId(), OrderConstant.STATUS_7);
                    logger.error("构建返回商城对象失败", e);
                }

                //发送给商城
                try {
                    boolean isSendState = ClientUtil.sendOrderRelationToShop(returnOrderVo, OrderConstant.METHODMARK_01);
                    logger.debug("向商城发送通知 状态："+isSendState);
                    if (isSendState) {
                        updateRelationInstanceStatus(null, vmInfo.getOrderId(), OrderConstant.STATUS_8);
                        //发送短信邮件信息
                        sendMessage(vmInfo.getOrderId());
                    } else {
                        updateRelationInstanceStatus(null, vmInfo.getOrderId(), OrderConstant.STATUS_9);
                    }
                } catch (Exception e) {
                    logger.error("向商城发送通知异常", e);
                }
            }
        }
        //更新时常和状态用
        if (CommonUtil.isEmpty(vmInfo.getOrderId()) && vmInfo.getIsSuccess()) {
            //主机 vmCode
            if (CommonUtil.isNotEmpty(vmInfo.getVmProductInstanceId())) {
                updateRelationInstanceStatus(vmInfo.getVmProductInstanceId(), null, OrderConstant.STATUS_14);
            }
            //IP internet_ip
            if (CommonUtil.isNotEmpty(vmInfo.getIpProductInstanceId())) {
                updateRelationInstanceStatus(vmInfo.getIpProductInstanceId(), null, OrderConstant.STATUS_14);
            }
            //带宽 vlan_id
            if (CommonUtil.isNotEmpty(vmInfo.getvLanProductInstanceId())) {
                updateRelationInstanceStatus(vmInfo.getvLanProductInstanceId(), null, OrderConstant.STATUS_14);
            }
            //负载均衡 loadId
            if (CommonUtil.isNotEmpty(vmInfo.getLoadProductInstanceId())) {
                updateRelationInstanceStatus(vmInfo.getLoadProductInstanceId(), null, OrderConstant.STATUS_14);
            }
            //存贮
            if (CommonUtil.isNotEmpty(vmInfo.getVmdkList())&&vmInfo.getVmdkList().size()>0) {
                String  ProduceInstaceId=vmInfo.getVmdkList().get(0).getVmdkProduceInstaceId();
                if(CommonUtil.isNotEmpty(ProduceInstaceId)){
                    updateRelationInstanceStatus(ProduceInstaceId, null, OrderConstant.STATUS_14);
                }
            }
            //更新状态
            if (CommonUtil.isNotEmpty(vmInfo.getStatus())) {
                updateRelationInstanceStatus(vmInfo.getVmProductInstanceId(), null, OrderConstant.STATUS_18);
            }
        }

        //订单创建失败
        if((CommonUtil.isEmpty(vmInfo.getIsSuccess())||vmInfo.getIsSuccess().equals(false))&&CommonUtil.isNotEmpty(vmInfo.getOrderId())){
              //重置订单
              logger.debug("订单创建失败_重置工单数据");
              reset(vmInfo.getOrderId());
        }
        logger.info("接收平台竣工通知_方法执行_End");
    }

    /**
     * 发送短信和邮件
     *
     * @param orderId 订单ID
     */
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	private void sendMessage(String orderId) {
        logger.info("发送短信和邮件_方法执行_Start");
        logger.debug("订单信息：" + orderId);
        //查找这个订单里所有主机信息
        TbOrderRelationInstanceExample oriExample = new TbOrderRelationInstanceExample();
        oriExample.createCriteria().andOrderIdEqualTo(orderId).andProductIdEqualTo(OrderConstant.PRODUCT_ID_1);
        List<TbOrderRelationInstance> orderRelationInstanceList = orderRelationInstanceDao.selectByExample(oriExample);
        for (TbOrderRelationInstance orderRelationInstanceObj : orderRelationInstanceList) {
            VmInfo  vminfoObj=new VmInfo();
            //主机名称
            vminfoObj.setVmName(orderRelationInstanceObj.getVmName());
            //主机登陆账号
            vminfoObj.setLoginName(orderRelationInstanceObj.getVmLoginName());
            //主机登陆秘密啊
            vminfoObj.setLoginPassword(orderRelationInstanceObj.getVmLoginPassword());
            TbSysUserinfoExample  example=new TbSysUserinfoExample();
            example.createCriteria().andShopUserIdEqualTo(orderRelationInstanceObj.getCustomerId());
            List <TbSysUserinfo> tbSysUserinfoList=sysUserinfoShopDao.selectByExample(example);
            if(tbSysUserinfoList.size()>0){
                TbSysUserinfo userobj=tbSysUserinfoList.get(0);
                vminfoObj.setEmail(userobj.getEmail());
                vminfoObj.setUser_Name(userobj.getAccount());
                vminfoObj.setMobile(userobj.getMobile());
            }
            boolean sms = SendMessageUtil.sendSystemMessage_Sms_emplate(vminfoObj);
            boolean mail = SendMessageUtil.sendSystemMessage_Email_emplate(vminfoObj, OrderConstant.TEXT_EMAIL);
            logger.debug("短信状态：" + sms);
            logger.debug("邮件状态：" + mail);
        }
        logger.info("发送短信和邮件_方法执行_End");
    }

    /**
     * 构建返回给商城的订购关系对象
     *
     * @param orderId 订单ID
     */
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    private MalltExtPrdctInstcBean buildOrderVo(String orderId) {
        logger.info("构建返回商城订购关系对象_方法执行_Start");
        logger.debug("订单ID：" + orderId);
        MalltExtPrdctInstcBean orderVo = new MalltExtPrdctInstcBean();
        // 订购关系实例化
        List<MalltPrdctInstc> malltPrdctInstc = new ArrayList<MalltPrdctInstc>();
        // 关系集合 实例化
        List<MalltPrdctInstcRelation> malltPrdctInstcRelation = new ArrayList<MalltPrdctInstcRelation>();

        // 取出订购关系集合******************************************************************
        TbOrderRelationInstanceExample oriExample = new TbOrderRelationInstanceExample();
        TbOrderRelationInstanceExample.Criteria orderRelationInstanceCriteria = oriExample.createCriteria();
        orderRelationInstanceCriteria.andOrderIdEqualTo(orderId);
        List<TbOrderRelationInstance> orderRelationInstanceList = orderRelationInstanceDao.selectByExample(oriExample);

        for (TbOrderRelationInstance object : orderRelationInstanceList) {
            // 实例化商城端订购关系对象
            MalltPrdctInstc malltPrdctInstcInObj = new MalltPrdctInstc();
            // 订购关系ID
            malltPrdctInstcInObj.setProduct_instance_id(object.getProductInstanceId());
            // 用户id
            malltPrdctInstcInObj.setCustomer_id(object.getCustomerId());
            // 是否主产品
            malltPrdctInstcInObj.setIf_primary(object.getIfPrimary());
            // 产品ID
            malltPrdctInstcInObj.setProduct_id(object.getProductId());

            List<MalltPrdctInstcSpec> malltPrdctInstcSpecList = new ArrayList<MalltPrdctInstcSpec>();
            // 取出产品规格信息
            TbOrderStandardExample tbOrderStandardExample = new TbOrderStandardExample();
            TbOrderStandardExample.Criteria orderStandardCriteria = tbOrderStandardExample.createCriteria();
            orderStandardCriteria.andOrderIdEqualTo(object.getOrderId()).andProductInstanceIdEqualTo(object.getProductInstanceId());
            List<TbOrderStandard> tbOrderStandardList = orderStandardDao.selectByExample(tbOrderStandardExample);
            // 放入规格属性
            for (TbOrderStandard tbOrderStandard : tbOrderStandardList) {
                MalltPrdctInstcSpec malltPrdctInstcSpecObj = new MalltPrdctInstcSpec();
                malltPrdctInstcSpecObj.setProduct_instance_id(tbOrderStandard.getProductInstanceId());
                malltPrdctInstcSpecObj.setPrdct_instc_spec_id(tbOrderStandard.getPrdctSpecId());
                malltPrdctInstcSpecObj.setSpec_id(tbOrderStandard.getSpecId());
                malltPrdctInstcSpecObj.setSpec_value(tbOrderStandard.getSpecValue());
                malltPrdctInstcSpecList.add(malltPrdctInstcSpecObj);
            }

            // 放入该订购关系的规格列表
            malltPrdctInstcInObj.setMalltPrdctInstcSpec(malltPrdctInstcSpecList);
            // 加入到订购关系的List中去
            malltPrdctInstc.add(malltPrdctInstcInObj);
        }

        // ******************************************************************取出订购关系的关系******************************************************************
        TbOrderRelationExample tbOrderRelationExample = new TbOrderRelationExample();
        TbOrderRelationExample.Criteria orderRelationCriteria = tbOrderRelationExample.createCriteria();
        orderRelationCriteria.andOrderIdEqualTo(orderId);
        List<TbOrderRelation> orderRelationList = orderRelationDao.selectByExample(tbOrderRelationExample);
        for (TbOrderRelation anOrderRelationList : orderRelationList) {
            // 实例化商城端订购关系的关系对象
            MalltPrdctInstcRelation malltPrdctInstcRelationInObj = new MalltPrdctInstcRelation();

            malltPrdctInstcRelationInObj.setPrdct_instc_relation_id(anOrderRelationList.getOrderRelationInsid());
            // 子订购关系
            malltPrdctInstcRelationInObj.setA_product_instance_id(anOrderRelationList.getChildRelationId());
            // 主订购关系
            malltPrdctInstcRelationInObj.setB_product_instance_id(anOrderRelationList.getMasterRelationId());
            malltPrdctInstcRelationInObj.setRelation_type(anOrderRelationList.getRelationType());
            // 加入到关系集合List中去
            malltPrdctInstcRelation.add(malltPrdctInstcRelationInObj);
        }

        // 放入订单ID
        orderVo.setOrder_id(orderId);
        // 放入订购关系
        orderVo.setMalltPrdctInstc(malltPrdctInstc);
        // 放入订购关系的关系
        orderVo.setMalltPrdctInstcRelation(malltPrdctInstcRelation);

        logger.debug("返回给商城端的订购关系对象：" + JacksonUtil.formatJson(JacksonUtil.toJson(orderVo)));
        logger.info("构建返回商城订购关系对象_方法执行_End");
        return orderVo;
    }

    /**
     * 将资源创建成功的跟新主机信息
     *
     * @param vmInfo 主机对象
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    private void updateCreateStatic(VmInfo vmInfo) throws Exception {
        logger.info("资源操作成功更新资源信息_方法执行_Start");
        if(CommonUtil.isNotEmpty(vmInfo.getVmProductInstanceId())) {
            logger.debug("预更新的主机信息：" + JacksonUtil.formatJson(JacksonUtil.toJson(vmInfo)));
            TbOrderRelationInstance obj = new TbOrderRelationInstance();
            // 主机唯一标示
            obj.setVmId(vmInfo.getVmCode());
            // 创建成功
            obj.setCreateStatus("1");
            // 机器名称
            obj.setVmName(vmInfo.getVmName());
            // 登陆名称
            obj.setVmLoginName(vmInfo.getLoginName());
            // 登陆密码
            obj.setVmLoginPassword(vmInfo.getLoginPassword());
            // ConnectCode
            obj.setvCenterCode(vmInfo.getConnectCode());
            //更新时间
            obj.setLastUpdateDate(new Date());
            TbOrderRelationInstanceExample objExample = new TbOrderRelationInstanceExample();
            objExample.createCriteria().andProductInstanceIdEqualTo(vmInfo.getVmProductInstanceId());
            orderRelationInstanceDao.updateByExampleSelective(obj, objExample);

            // 更新主机下的子订购关系的状态*****************************************************
            List<String> childRelationIdList = new ArrayList<String>();
            // 查询的条件对象
            TbOrderRelationExample orExample = new TbOrderRelationExample();
            // 条件为主产品的订购关系ID
            orExample.createCriteria().andMasterRelationIdEqualTo(vmInfo.getVmProductInstanceId());
            List<?> orList = orderRelationDao.selectByExample(orExample);
            for (Object o : orList) {
                TbOrderRelation orObj = (TbOrderRelation) o;
                childRelationIdList.add(orObj.getChildRelationId());
            }
            TbOrderRelationInstance obj2 = new TbOrderRelationInstance();
            //子订购关系也要更新状态
            obj2.setCreateStatus("1");
            //更新时间
            obj2.setLastUpdateDate(new Date());
            if(childRelationIdList.size()>0){
                TbOrderRelationInstanceExample obj2Example = new TbOrderRelationInstanceExample();
                obj2Example.createCriteria().andProductInstanceIdIn(childRelationIdList);
                orderRelationInstanceDao.updateByExampleSelective(obj2, obj2Example);
            }
        }


        List<String> RelationIdList = new ArrayList<String>();

        if(CommonUtil.isNotEmpty(vmInfo.getCpuProductInstanceId())) {
            RelationIdList.add(vmInfo.getCpuProductInstanceId());
        }

        if(CommonUtil.isNotEmpty(vmInfo.getMemoryProductInstanceId())) {
            RelationIdList.add(vmInfo.getMemoryProductInstanceId());
        }

        
        if(CommonUtil.isNotEmpty(vmInfo.getVmdkList())&&vmInfo.getVmdkList().size()>0) {
            for (VirtualDiskUnitedVO o : vmInfo.getVmdkList()) {
                RelationIdList.add(o.getVmdkProduceInstaceId());
                updateOrderRelation(o.getVmdk_id(), o.getVmdkProduceInstaceId(),String.valueOf(o.getCapacityInMB()/1024));
            }
        }

        if(CommonUtil.isNotEmpty(vmInfo.getIpProductInstanceId())) {
            RelationIdList.add(vmInfo.getIpProductInstanceId());
            TbOrderRelationInstance obj2 = new TbOrderRelationInstance();
            obj2.setPublicIpAddress(vmInfo.getInternet_ip());
            TbOrderRelationInstanceExample example = new TbOrderRelationInstanceExample();
            example.createCriteria().andProductInstanceIdEqualTo(vmInfo.getIpProductInstanceId());
            orderRelationInstanceDao.updateByExampleSelective(obj2, example);
        }
        
        if(CommonUtil.isNotEmpty(vmInfo.getvLanProductInstanceId())) {
            RelationIdList.add(vmInfo.getvLanProductInstanceId());
            TbOrderRelationInstance obj3 = new TbOrderRelationInstance();
            obj3.setIpAddress(vmInfo.getVlan_id());
            TbOrderRelationInstanceExample example = new TbOrderRelationInstanceExample();
            example.createCriteria().andProductInstanceIdEqualTo(vmInfo.getvLanProductInstanceId());
            orderRelationInstanceDao.updateByExampleSelective(obj3, example);
        }

        if(CommonUtil.isNotEmpty(vmInfo.getLoadProductInstanceId())) {
            RelationIdList.add(vmInfo.getLoadProductInstanceId());
            TbOrderRelationInstance obj4 = new TbOrderRelationInstance();
            obj4.setIpAddress(vmInfo.getLoadId());
            TbOrderRelationInstanceExample example = new TbOrderRelationInstanceExample();
            example.createCriteria().andProductInstanceIdEqualTo(vmInfo.getLoadProductInstanceId());
            orderRelationInstanceDao.updateByExampleSelective(obj4, example);
        }

        TbOrderRelationInstance obj3 = new TbOrderRelationInstance();
        //副产品订购关系也要更新状态
        obj3.setCreateStatus("1");
        //更新时间
        obj3.setLastUpdateDate(new Date());

        if(RelationIdList.size()>0){
            TbOrderRelationInstanceExample obj2Example = new TbOrderRelationInstanceExample();
            obj2Example.createCriteria().andProductInstanceIdIn(RelationIdList).andOrderIdEqualTo(vmInfo.getOrderId());
            orderRelationInstanceDao.updateByExampleSelective(obj3, obj2Example);
        }


        // TODO 更新子订购关系放入最新的配置信息)******************************************************忘记有用没用了
        if (CommonUtil.isNotEmpty(vmInfo.getCpuProductInstanceId())) {
            logger.debug("更新主机信息到规格表_CPU");
            updateOrderRelation(vmInfo.getCpuProductInstanceId(), String.valueOf(vmInfo.getCpu()));
        }

        if (CommonUtil.isNotEmpty(vmInfo.getMemoryProductInstanceId())) {
            logger.debug("更新主机信息到规格表_内存");
            updateOrderRelation(vmInfo.getMemoryProductInstanceId(), String.valueOf(vmInfo.getMemoryInMb() / 1024));
        }

        logger.info("资源操作成功更新资源信息_方法执行_End");
    }

    /**
     * 更新规格表中的值
     *
     * @param productInstanceId 订购关系ID
     * @param value             要修改的值
     * @throws Exception
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    private void updateOrderRelation(String productInstanceId, String value) throws Exception {
        logger.info("更新规格表里的值_方法执行_Start");
        logger.debug("订购关系ID： " + productInstanceId + "预更新的值" + value);
        TbOrderStandardExample osExample = new TbOrderStandardExample();
        TbOrderStandard record = new TbOrderStandard();
        record.setSpecValue(value);
        //更新时间
        record.setLastUpdateDate(new Date());
        // 条件为主产品的订购关系ID
        osExample.createCriteria().andProductInstanceIdEqualTo(productInstanceId);
        orderStandardDao.updateByExampleSelective(record, osExample);
        logger.info("更新规格表里的值_方法执行_End");
    }

    /**
     * 更新规格表中的值(磁盘信息专用)
     *
     * @param vmdk_id           磁盘标识
     * @param productInstanceId 订购关系ID
     * @param value             要修改的值
     * @throws Exception
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    private void updateOrderRelation(String vmdk_id, String productInstanceId, String value) throws Exception {
        logger.info("更新规格表里的值(磁盘信息专用)_方法执行_Start");
        logger.debug("磁盘标识" + vmdk_id + "订购关系ID： " + productInstanceId + "预更新的值" + value);
        TbOrderStandardExample osExample = new TbOrderStandardExample();
        TbOrderStandard record = new TbOrderStandard();
        record.setSpecValue(value);
        //这个描述里放的是磁盘唯一标示
        record.setSpecValueDesc(vmdk_id);
        //更新时间
        record.setLastUpdateDate(new Date());
        // 条件为主产品的订购关系ID
        osExample.createCriteria().andProductInstanceIdEqualTo(productInstanceId);
        orderStandardDao.updateByExampleSelective(record, osExample);
        logger.info("更新规格表里的值(磁盘信息专用)_方法执行_End");
    }

    /**
     * 检查订单的内容是否全部创建完成
     *
     * @param orderId 订单ID
     * @return isComplete
     */
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    private boolean checkOrderIsComplete(String orderId) throws Exception {
        logger.info("检查订单是否完成_方法执行_Start");
        logger.debug("订单ID：" + orderId);
        TbOrderRelationInstanceExample example = new TbOrderRelationInstanceExample();
        TbOrderRelationInstanceExample.Criteria criteria = example.createCriteria();
        criteria.andOrderIdEqualTo(orderId);
        int orderSize = orderRelationInstanceDao.countByExample(example);
        criteria.andCreateStatusEqualTo("1");
        int completeOrderSize = orderRelationInstanceDao.countByExample(example);
        logger.debug("订购数量：" + orderSize);
        logger.debug("竣工数量：" + completeOrderSize);
        logger.info("检查订单是否完成_方法执行_End");
        return orderSize == completeOrderSize;
    }

    /**
     * 通过订购关系查询机器信息（计费专用）
     *
     * @param orderEntityVO 订购关系对象
     * @return orderEntityVO 返回给商城的订购关系对象
     */
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public OrderEntityVO findVmInfo(OrderEntityVO orderEntityVO) {
        logger.info("通过订购关系查询机器信息_方法执行_Start");
        logger.debug("计费传过来的订购关系ID集合" + JacksonUtil.formatJson(JacksonUtil.toJson(orderEntityVO)));
        OrderEntityVO outobj = new OrderEntityVO();
        List<OrderEntityRelationVO> voList = new ArrayList<OrderEntityRelationVO>();

        if (orderEntityVO.getPiIdList().size() > 0) {
            TbOrderRelationInstanceExample example = new TbOrderRelationInstanceExample();
            example.createCriteria().andProductInstanceIdIn(orderEntityVO.getPiIdList());
            List<TbOrderRelationInstance> tbOrderRelationInstanceList = orderRelationInstanceDao.selectByExample(example);
            for (TbOrderRelationInstance tbOrderRelationInstance : tbOrderRelationInstanceList) {
                OrderEntityRelationVO inObj = new OrderEntityRelationVO();
                inObj.setProduct_instance_id(tbOrderRelationInstance.getProductInstanceId());
                inObj.setConnectCode(tbOrderRelationInstance.getvCenterCode());
                inObj.setEntityCode(tbOrderRelationInstance.getVmId());
                voList.add(inObj);
            }
        }
        outobj.setVoList(voList);
        outobj.setIsSuccess(true);
        logger.debug("返回给计费的主机信息" + JacksonUtil.formatJson(JacksonUtil.toJson(outobj)));
        logger.info("通过订购关系查询机器信息_方法执行_End");
        return outobj;
    }

    /**
     * 磁盘卸载
     *
     * @param vmId   主机唯一标示
     * @param diskId 磁盘唯一标示
     * @throws Exception
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public boolean uninstallDisk(String connectCode, String vmId, String diskId) throws Exception {
		logger.info("磁盘卸载_订购关系删除_方法执行_Start");
		logger.debug("ConnectCode:" + connectCode + "VmId:" + vmId + "DiskId：" + diskId);
		try {
			String[] RelationIds = getVmAndDiskRelationId(connectCode, vmId, diskId);
			logger.debug("主机订购关系：" + RelationIds[0]);
			logger.debug("磁盘订购关系：" + RelationIds[1]);

			TbOrderRelationExample ore = new TbOrderRelationExample();
			ore.createCriteria().andMasterRelationIdEqualTo(RelationIds[0]).andChildRelationIdEqualTo(RelationIds[1]);

			List<?> orList = orderRelationDao.selectByExample(ore);
			if (orList.size() > 0) {
				TbOrderRelation oldOr = orderRelationDao.selectByExample(ore).get(0);

				// 发送给商城磁盘订购关系换了_发送给商城成功了之后跟新平台的关系
				MalltPrdctInstcRelation mpr = new MalltPrdctInstcRelation();
				mpr.setPrdct_instc_relation_id(oldOr.getOrderRelationInsid());
				mpr.setB_product_instance_id(oldOr.getMasterRelationId());
				mpr.setA_product_instance_id(oldOr.getChildRelationId());
				mpr.setRelation_type(oldOr.getRelationType());
				mpr.setOpr_type("2");

				logger.debug("发送给商城的磁盘卸载对象" + JacksonUtil.formatJson(JacksonUtil.toJson(getReturnBean(mpr))));
				if (ClientUtil.sendOrderRelationToShop(getReturnBean(mpr), OrderConstant.METHODMARK_03)) {
					logger.debug("发送成功-删除我接口表数据");
					orderRelationDao.deleteByExample(ore);
				} else {
					logger.debug("发送失败-关系的关系表标注一下");
					logger.debug("主机订购关系：" + RelationIds[0]);
					logger.debug("磁盘订购关系：" + RelationIds[1]);
					oldOr.setBoattr1("DELETE_ERROR");
					orderRelationDao.updateByExampleSelective(oldOr, ore);
				}
			}
            logger.info("磁盘卸载_订购关系删除_方法执行_End");
			return  true;
		} catch (Exception e) {
			logger.error("磁盘卸载_订购关系删除_方法执行异常",e);
			return  false;
		}
	}

    /**
     * @param diskId 磁盘唯一标示
     * @param vmId   主机唯一标示
     * @throws Exception
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public boolean mountDisk(String connectCode, String vmId, String diskId) throws Exception {
		logger.info("磁盘挂载_订购关系新增_方法执行_Start");
		logger.debug("ConnectCode:" + connectCode + "VmId:" + vmId + "DiskId：" + diskId);
		try {

			String[] RelationIds = getVmAndDiskRelationId(connectCode, vmId, diskId);

			TbOrderRelation newOr = new TbOrderRelation();
			newOr.setUuid(RandomUUID.getUuid());
			newOr.setMasterRelationId(RelationIds[0]);
			newOr.setChildRelationId(RelationIds[1]);
			newOr.setCreateDate(new Date());
			newOr.setRelationType("1");

			MalltPrdctInstcRelation mpr = new MalltPrdctInstcRelation();
			mpr.setB_product_instance_id(RelationIds[0]);
			mpr.setA_product_instance_id(RelationIds[1]);
			mpr.setRelation_type("1");
			mpr.setOpr_type("1");

			logger.debug("发送给商城的磁盘挂载对象" + JacksonUtil.formatJson(JacksonUtil.toJson(getReturnBean(mpr))));
			if (ClientUtil.sendOrderRelationToShop(getReturnBean(mpr), OrderConstant.METHODMARK_03)) {
				logger.debug("发送成功-插入我接口表数据");
				orderRelationDao.insertSelective(newOr);
			} else {
				logger.debug("发送失败-关系的关系表标注一下");
				logger.debug("主机订购关系：" + RelationIds[0]);
				logger.debug("磁盘订购关系：" + RelationIds[1]);
				newOr.setBoattr1("INSERT_ERROR");
				orderRelationDao.insertSelective(newOr);
			}
			logger.info("磁盘挂载_订购关系新增_方法执行_End");
			return true;
		} catch (Exception e) {
			logger.error("磁盘卸载_订购关系删除_方法执行异常", e);
			return false;
		}
	}

    private MalltExtPrdctInstcBean getReturnBean(MalltPrdctInstcRelation mpr) {
        MalltExtPrdctInstcBean returnBean = new MalltExtPrdctInstcBean();
        List<MalltPrdctInstcRelation> list = new ArrayList<MalltPrdctInstcRelation>();
        list.add(mpr);
        returnBean.setMalltPrdctInstcRelation(list);
        return returnBean;
    }

    /**
     * 查询主机和磁盘的订购关系ID
     *
     * @param connectCode 连接标示
     * @param vmId        主机唯一标示
     * @param diskId      磁盘唯一标示
     * @return 查询主机和磁盘的订购关系ID 数组
     */
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public String[] getVmAndDiskRelationId(String connectCode, String vmId, String diskId) throws Exception {
        logger.info("查询主机和磁盘的订购关系ID_方法执行_Start");
        logger.debug("ConnectCode：" + connectCode + "vmId：" + vmId + "diskId：" + diskId);
        String vmRelationId = "";
        String diskIdRelationId = "";
        if (CommonUtil.isNotEmpty(connectCode) && CommonUtil.isNotEmpty(vmId)) {
            //查询主机订购关系ID
            TbOrderRelationInstanceExample orie = new TbOrderRelationInstanceExample();
            orie.createCriteria().andVmIdEqualTo(vmId).andVCenterCodeEqualTo(connectCode);
            List<TbOrderRelationInstance> vmRelationIdList = orderRelationInstanceDao.selectByExample(orie);
            vmRelationId = vmRelationIdList.size() > 0 ? vmRelationIdList.get(0).getProductInstanceId() : null;
            logger.debug("主机订购关系 ID：" + vmRelationId);
        }
        if (CommonUtil.isNotEmpty(diskId)&&(!diskId.equals("null"))) {
            //查询磁盘的订购关系ID
            TbOrderStandardExample ose = new TbOrderStandardExample();
            ose.createCriteria().andSpecValueDescEqualTo(diskId);
            List<TbOrderStandard> diskIdRelationIdList = orderStandardDao.selectByExample(ose);
            diskIdRelationId = diskIdRelationIdList.size() > 0 ? diskIdRelationIdList.get(0).getProductInstanceId() : null;
            logger.debug("磁盘订购关系 ID：" + diskIdRelationId);
        }
        logger.info("查询主机和磁盘的订购关系ID_方法执行_End");
        return new String[]{vmRelationId, diskIdRelationId};
    }

    /**
     * 获取Vlan订购关系ID
     * @param vLanId  vLanId
     * @return Vlan订购关系ID
     * @throws Exception
     */
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public String getVlanId(String vLanId) throws Exception {
        logger.info("获取Vlan订购关系ID_方法执行_Start");
        logger.debug("VlanId:"+vLanId);
        TbOrderRelationInstanceExample orie = new TbOrderRelationInstanceExample();
        orie.createCriteria().andIpAddressEqualTo(vLanId);
        List<TbOrderRelationInstance> vmRelationIdList = orderRelationInstanceDao.selectByExample(orie);
        if(vmRelationIdList.size()>0){
            logger.debug("VlanRelationId:"+vmRelationIdList.get(0).getProductInstanceId());
        	return vmRelationIdList.get(0).getProductInstanceId();
        }
        logger.info("获取Vlan订购关系ID_方法执行_End");
		return "";
	}

    /**
     *获取IP订购关系ID
     * @param ipId  IP地址
     * @return IP订购关系ID
     * @throws Exception
     */
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public String getIPId(String ipId) throws Exception {
        logger.info("获取IP订购关系ID_方法执行_Start");
        logger.debug("IP 地址："+ipId);
        TbOrderRelationInstanceExample orie = new TbOrderRelationInstanceExample();
        orie.createCriteria().andPublicIpAddressEqualTo(ipId);
        List<TbOrderRelationInstance> vmRelationIdList = orderRelationInstanceDao.selectByExample(orie);
        if(vmRelationIdList.size()>0){
            logger.debug("IP 订购关系ID："+vmRelationIdList.get(0).getProductInstanceId());
            return vmRelationIdList.get(0).getProductInstanceId();
        }
        logger.info("获取IP订购关系ID_方法执行_End");
        return "";
    }

    /**
     *"获取负载均衡订购关系ID
     * @param loadId   负载均衡ID
     * @return 负载均衡订购关系ID
     * @throws Exception
     */
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public String getLoadId(String loadId) throws Exception {
        logger.info("获取负载均衡订购关系ID_方法执行_Start");
        logger.debug("负载均衡ID："+loadId);
        TbOrderRelationInstanceExample orie = new TbOrderRelationInstanceExample();
        orie.createCriteria().andIpAddressEqualTo(loadId);
        List<TbOrderRelationInstance> vmRelationIdList = orderRelationInstanceDao.selectByExample(orie);
        if(vmRelationIdList.size()>0){
            logger.debug("负载均衡订购关系："+ vmRelationIdList.get(0).getProductInstanceId());
            return vmRelationIdList.get(0).getProductInstanceId();
        }
        logger.info("获取负载均衡订购关系ID_方法执行_End");
        return "";
    }

    /**
     * 订单状态重置
     * @param orderId 订单ID
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void reset(String orderId) throws Exception{
        logger.info("订单状态重置_方法执行_Start");
        logger.debug("订单ID"+orderId);
        if (CommonUtil.isNotEmpty(orderId)) {
            TbOrderRelationInstanceExample example = new TbOrderRelationInstanceExample();
            example.createCriteria().andOrderIdEqualTo(orderId).andCreateStatusIsNull();
            TbOrderRelationInstance ori = new TbOrderRelationInstance();
            ori.setChangeStatus("");
            orderRelationInstanceDao.updateByExampleSelective(ori, example);
        }
        logger.info("订单状态重置_方法执行_End");
    }

    /**
     * 订购关系分页条件查询
     * @param obj 条件对象
     * @return 订购关系列表
     */
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<TbOrderRelationInstance> selectOrderRelationList(TbOrderRelationInstanceExample obj) {
        logger.info("订购关系分页条件查询_方法执行_Start");
		return orderRelationInstanceDao.selectByExamplePage(obj);
	}

}
