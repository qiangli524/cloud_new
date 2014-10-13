package com.sitech.shop.webservice.util;

import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.shop.webservice.domain.TbOrderRelationInstance;
import com.sitech.ssd.billing.vo.resourceInfo.VmInfo;
import com.sitech.ssd.billing.vo.sendMessage.MailSenderInfo;
import com.sitech.utils.http.HttpClientCustomUtil;
import com.sitech.utils.jackson.JacksonUtil;
import com.sitech.utils.properties.PropertiesUtil;
import com.sitech.utils.publicShop.OrderConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;

public class SendMessageUtil {


    private static final Logger log = LoggerFactory.getLogger(SendMessageUtil.class);

    /**
     * 发送系统消息_基础方法
     *
     * @param mailInfo    消息对象
     * @param MessageType 消息类型 OrderConstant.SMS_MESSAGE 、OrderConstant.TEXT_EMAIL 、OrderConstant.HTML_EMAIL
     * @return boolean 发送是否成功
     */

    public static boolean sendSystemMessage(MailSenderInfo mailInfo, String MessageType) {

        MailSenderInfo returnObj = new MailSenderInfo();
        try {
            String baseUrl = PropertiesUtil.getValue("properties/shop_client_info.properties", "sendMessage_url");
            returnObj = HttpClientCustomUtil.post(baseUrl + MessageType, mailInfo, new JacksonUtil.TypeReference<MailSenderInfo>() {
            });
        } catch (Exception e) {
            returnObj.setIsSuccess(false);
            log.error("发送系统信息异常", e);
        }
        return returnObj.getIsSuccess();
    }


    /**
     * 发送系统消息_增加短信模板
     *
     * @param vmInfo 主机对象
     * @return 是否发送 成功
     */
    public static boolean sendSystemMessage_Sms_emplate(VmInfo vmInfo) {
        MailSenderInfo mailInfo = new MailSenderInfo();
        String formatString;
        try {
            //尊敬 {0} 您好！ 您的主机{1}已经开通,登陆账号：{2} 登陆密码：{3}
            formatString = PropertiesUtil.getValue("properties/message_template.properties", "sms_template");
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
        //要填充的信息
        Object[] fmtargs = {vmInfo.getUser_Name(), vmInfo.getVmName(), vmInfo.getLoginName(), vmInfo.getLoginPassword()};
        //设置发送内容
        mailInfo.setSmsContent(MessageFormat.format(formatString, fmtargs));
        //接收人手机号
        mailInfo.setPhoneNum(vmInfo.getMobile());

        return sendSystemMessage(mailInfo, OrderConstant.SMS_MESSAGE);
    }


    /**
     * 发送系统消息_增加邮件模板
     *
     * @param MessageType 消息类型 OrderConstant.TEXT_EMAIL 、OrderConstant.HTML_EMAIL
     * @return boolean 发送是否成功
     */
    public static boolean sendSystemMessage_Email_emplate(VmInfo vmInfo, String MessageType) {
        MailSenderInfo mailInfo = new MailSenderInfo();
        String formatString;
        try {
            //尊敬 {0} 您好！ 您的主机{1}已经开通,登陆账号：{2} 登陆密码：{3}
            formatString = PropertiesUtil.getValue("properties/message_template.properties", (MessageType.equals(OrderConstant.TEXT_EMAIL) ? "email_template_text" : "email_template_html"));

        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
        //要填充的信息
        Object[] fmtargs = {vmInfo.getUser_Name(), vmInfo.getVmName(), vmInfo.getLoginName(), vmInfo.getLoginPassword()};
        //设置标题
        mailInfo.setSubject("订单竣工通知");
        //设置邮件接收人
        mailInfo.setToAddress(vmInfo.getEmail());
        //设置发送内容
        mailInfo.setContent(MessageFormat.format(formatString, fmtargs));

        return sendSystemMessage(mailInfo, MessageType);
    }

    /**
     * 发送工单完成消息:发送邮件和短信模板
     *
     * @param mailInfo 消息对象
     * @return boolean 是否成功
     */
    public static boolean sendMessage_template(MailSenderInfo mailInfo) {
        String formatString;
        try {
            //您好！您的工单（{0}）已经处理完成，请登陆控制台查看！【北京电信云计算】
            formatString = PropertiesUtil.getValue("properties/message_template.properties", "msg_template");

        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
        //要填充的信息
        Object[] fmtargs = {mailInfo.getContent()};
        //设置标题
        mailInfo.setSubject("工单完成通知");
        //设置发送内容
        mailInfo.setContent(MessageFormat.format(formatString, fmtargs));

        LogHelper.debug("正在发送消息：" + JacksonUtil.formatJson(JacksonUtil.toJson(mailInfo)));

        Boolean sendSmsRetult = sendSystemMessage(mailInfo, OrderConstant.SMS_MESSAGE);
        Boolean sendEmailRetult = sendSystemMessage(mailInfo, OrderConstant.TEXT_EMAIL);

        if (!sendSmsRetult) {
            LogHelper.error("短信发送失败：" + JacksonUtil.formatJson(JacksonUtil.toJson(mailInfo)));
        }
        if (!sendEmailRetult) {
            LogHelper.error("邮件发送失败：" + JacksonUtil.formatJson(JacksonUtil.toJson(mailInfo)));
        }

        return sendSmsRetult && sendEmailRetult;
    }

    /**
     * 发送到期提醒消息:发送邮件和短信模板
     *
     * @param orObj 到期的记录对象
     */
    public static void send_Reminder_Message(TbOrderRelationInstance orObj) {
        //初始化信息实体
        MailSenderInfo mailInfo = new MailSenderInfo();
        String formatString;
        try {
            //用户 {0} : 您好！ 您的{1}还有{2}天到期，为了避免您的损失，请您及时续费。【北京电信云计算】
            formatString = PropertiesUtil.getValue("properties/message_template.properties", "reminder_sms_template");
            //要发送的内容信息
            Object[] fmtargs = {orObj.getBoattr1(), getProductvlue(orObj.getProductId(), orObj.getBoattr4()), orObj.getBoattr2()};
            //设置提醒接收邮箱
            mailInfo.setToAddress(orObj.getBoattr1());
            //设置手机号
            mailInfo.setPhoneNum(orObj.getBoattr3());
            //设置标题
            mailInfo.setSubject("产品到期提醒通知");
            //设置发送短信内容
            mailInfo.setSmsContent(MessageFormat.format(formatString, fmtargs));
            //设置发送邮件内容
            mailInfo.setContent(MessageFormat.format(formatString, fmtargs));
            //接收人手机号
            mailInfo.setPhoneNum(mailInfo.getPhoneNum());
            sendSystemMessage(mailInfo, OrderConstant.SMS_MESSAGE);
            sendSystemMessage(mailInfo, OrderConstant.TEXT_EMAIL);
        } catch (Exception e) {
            log.error("发送到期提醒消息异常", e);
        }
    }

    /**
     * 填充值的转换
     *
     * @param productId 产品ID
     * @param boattr4   主机名称
     * @return 转换后的字符串
     */
    public static Object getProductvlue(String productId, String boattr4) {
        // TODO 这个方法后期可能需要改造，暂时这样写
        String returnString = "";
        if (productId.equals(OrderConstant.PRODUCT_ID_1)) {
            //主机
            returnString = "主机：" + boattr4;
        } else if (productId.equals(OrderConstant.PRODUCT_ID_4)) {
            //存储
            returnString = "存储";
        } else if (productId.equals(OrderConstant.PRODUCT_ID_5)) {
            //带宽
            returnString = "带宽";
        } else if (productId.equals(OrderConstant.PRODUCT_ID_7)) {
            //公网ip
            returnString = "公网ip";
        } else if (productId.equals(OrderConstant.PRODUCT_ID_8)) {
            // 负载均衡
            returnString = "负载均衡";
        }
        return returnString;
    }
}
