package com.sitech.shop.webservice.service;

import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sitech.shop.webservice.domain.TbOrderRelationInstance;
import com.sitech.shop.webservice.domain.TbOrderRelationInstanceExample;
import com.sitech.ssd.billing.vo.order.OrderVO;
import com.sitech.ssd.billing.vo.orderentityrelation.OrderEntityVO;
import com.sitech.ssd.billing.vo.resourceInfo.VmInfo;

/**
 * 通过订购关系操作资源
 */
@Path("/orderRelation")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface OrderRelation {

    /**
     * 处理商场发送过来的订购关系
     *
     * @param orderVO 商城发送过来的订单对象
     * @return 返回的订单对象
     * @throws Exception
     */
    @POST
    @Path("/processingOrder")
    public OrderVO processing(OrderVO orderVO) throws Exception;

    /**
     * 处理商场发送过来的主机状态变更
     *
     * @param orderVO 订购关系对象
     * @return 返回给商城的订购关系对象
     */
    @POST
    @Path("/processingRelationStatusChange")
    public OrderVO processingRelationStatusChange(OrderVO orderVO);

    /**
     * 处理商场发送过来的服务时长变更
     *
     * @param orderVO 订购关系对象
     * @return 返回给商城的订购关系对象
     */
    @POST
    @Path("/processingRelationTimeChange")
    public OrderVO processingRelationTimeChange(OrderVO orderVO);

    /**
     * 通过订购关系查询机器信息（李鹏鹏用）
     *
     * @param orderEntityVO 订单实体
     * @return 订单实体
     */
    @POST
    @Path("/findVmInfo")
    public OrderEntityVO findVmInfo(OrderEntityVO orderEntityVO);


    /**
     * 平台资源创建完成通知
     *
     * @param vmInfoStr 运维平台处理完成后给我的结果集字符串
     * @throws Exception
     */
    public void processResult(String vmInfoStr) throws Exception;


    /**
     * 卸载磁盘
     *
     * @param connectCode 连接代码
     * @param vmId        主机ID
     * @param diskId      磁盘ID
     * @throws Exception
     */
    @GET
    @Path("/uninstallDisk/{connectCode}/{vmId}/{diskId}")
    public boolean uninstallDisk(String connectCode, String vmId, String diskId) throws Exception;


    /**
     * 挂载磁盘
     *
     * @param connectCode 连接代码
     * @param vmId        主机ID
     * @param diskId      磁盘ID
     * @throws Exception
     */
    @GET
    @Path("/mountDisk/{connectCode}/{vmId}/{diskId}")
    public boolean mountDisk(String connectCode, String vmId, String diskId) throws Exception;

    /**
     * 通过主机ID获得订购关系ID
     *
     * @param connectCode 连接信息
     * @param vmId        主机标示
     * @param diskId      磁盘唯一标示
     * @return 订购关系数组 String[]{vmRelationId, diskIdRelationId};
     * @throws Exception
     */
    @GET
    @Path("/getVmAndDiskRelationId/{connectCode}/{vmId}/{diskId}")
    public String[] getVmAndDiskRelationId(@PathParam("connectCode") String connectCode, @PathParam("vmId") String vmId, @PathParam("diskId") String diskId) throws Exception;


    /**
     * 通过vLanId 取得订购关系ID
     *
     * @param vLanId vLanId
     * @return 订购关系ID
     * @throws Exception
     */
    @GET
    @Path("/getVlanId/{vLanId}")
    public String getVlanId(@PathParam("vLanId") String vLanId) throws Exception;


    /**
     * 通过公网IP取得订购关系ID
     *
     * @param ipId IP地址
     * @return 订购关系ID
     * @throws Exception
     */
    @GET
    @Path("/getIPId/{ipId}")
    public String getIPId(@PathParam("ipId") String ipId) throws Exception;


    /**
     * 通过负载均衡Id取得订购关系ID
     *
     * @param loadId loadId 负载均衡ID
     * @return 订购关系ID
     * @throws Exception
     */
    @GET
    @Path("/getLoadId/{loadId}")
    public String getLoadId(@PathParam("loadId") String loadId) throws Exception;

    /**
     *订购关系分页条件查询
     * @param obj 条件对象
     * @return 订购关系列表
     */
    public List<TbOrderRelationInstance> selectOrderRelationList(TbOrderRelationInstanceExample obj);

    /**
     * 发送订单给云平台
     * @param orderId 订单ID
     * @param is_Init 是否第一次购买
     * @throws Exception
     */
    public void sendCloud(String orderId, boolean is_Init,boolean send_type) throws Exception;

    /**
     *删除订单
     * @param orderId 订单ID
     * @throws Exception
     */
    public void deleteAll(String orderId) throws Exception;

    /**
     *重置订单状态
     * @param orderId 订单ID
     * @throws Exception
     */
    public void reset(String orderId) throws Exception;

    public Map<String, List<String>> getComputerConfigurationRelationIdMap(String orderId, String relationId, boolean isComputer,String productId) throws Exception;

    public VmInfo getComputerConfigurationInfo(String key, Map<String, List<String>> ProductInstanceIdMap,String orderId) throws Exception;
}
